package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DetailsDiscussBase;
import com.secretk.move.bean.DiscussNewInfoBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.ui.adapter.DetailsDiscussAdapter;
import com.secretk.move.ui.adapter.ImagesAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.Clickable;
import com.secretk.move.view.RecycleScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 作者： litongge
 * 时间： 2018/5/2 14:12
 * 邮箱；ltg263@126.com
 * 描述：讨论详情.
 */
public class DetailsDiscussActivity extends BaseActivity {

    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.rv_hot_review)
    RecyclerView rvkHotReview;
    @BindView(R.id.rcv)
    RecycleScrollView rcv;
    @BindView(R.id.rv_new_review)
    RecyclerView rvNewReview;
    @BindView(R.id.tv_post_title)
    TextView tvPostTitle;
    @BindView(R.id.iv_create_user_icon)
    ImageView ivCreateUserIcon;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.ll_rm)
    LinearLayout llRm;
    @BindView(R.id.ll_zx)
    LinearLayout llZx;
    @BindView(R.id.tv_rm)
    TextView tvRm;
    @BindView(R.id.tv_zx)
    TextView tvZx;
    @BindView(R.id.tv_create_user_name)
    TextView tvCreateUserName;
    @BindView(R.id.tv_create_user_signature)
    TextView tvCreateUserSignature;
    @BindView(R.id.iv_post_small_images)
    ImageView ivPostSmallImages;
    @BindView(R.id.tv_follow_status)
    TextView tvFollowStatus;
    @BindView(R.id.tv_post_short_desc)
    TextView tvPostShortDesc;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_tag_name)
    TextView tvTagName;
    @BindView(R.id.et_comment_content)
    EditText etCommentContent;
    @BindView(R.id.but_login)
    Button butLogin;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.have_data)
    RelativeLayout haveData;
    private DetailsDiscussAdapter adapter;
    private DetailsDiscussAdapter adapterNew;
    private String postId;
    private ImagesAdapter imagesadapter;
    private String imgUrl;
    private String imgName;
    private boolean isFinish = false;
    private int userId;
    private int createUserId;
    int pageIndex = 1;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_details_discuss;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.getImageView().setVisibility(View.VISIBLE);
        mMenuInfos.add(0, new MenuInfo(R.string.share, "分享", R.drawable.ic_share));
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
//        refreshLayout.setEnableRefresh(false);//关闭下拉刷新
        postId = getIntent().getStringExtra("postId");
        setHorizontalManager(rvImg);
        imagesadapter = new ImagesAdapter(this);
        rvImg.setAdapter(imagesadapter);

        setVerticalManager(rvkHotReview);
        adapter = new DetailsDiscussAdapter(this);
        rvkHotReview.setAdapter(adapter);

        setVerticalManager(rvNewReview);
        adapterNew = new DetailsDiscussAdapter(this);
        rvNewReview.setAdapter(adapterNew);
        initRefresh();
    }

    @OnClick({R.id.tv_follow_status, R.id.iv_post_small_images, R.id.but_login, R.id.rl_ge_ren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_follow_status:
                tvFollowStatus.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatus,
                        Constants.SaveFollow.USER, Integer.valueOf(userId), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvFollowStatus.setEnabled(true);
                                if (!str.equals(Constants.FOLLOW_ERROR)) {
                                    tvFollowStatus.setText(str);
                                }
                            }
                        });
                break;
            case R.id.iv_post_small_images:
                String key[] = {"imgUrl", "imgName"};
                String values[] = {imgUrl, imgName};
                IntentUtil.startActivity(TemporaryIV.class, key, values);
                break;
            case R.id.rl_ge_ren:
                IntentUtil.startHomeActivity(createUserId);
                break;
            case R.id.but_login:
                String str = etCommentContent.getText().toString().trim();
                if (StringUtil.isNotBlank(str)) {
                    saveComment(str);
                } else {
                    ToastUtils.getInstance().show("内容不能为空");
                }
                break;
        }
    }

    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                refreshLayout.setLoadmoreFinished(false);
                if (!NetUtil.isNetworkAvailable()) {
                    ToastUtils.getInstance().show(getString(R.string.network_error));
                    return;
                }
                initDataList();

                initNewsDataList();
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                initNewsDataList();
            }
        });
    }

    private void saveComment(String content) {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        pageIndex = 1;
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("commentContent", content);//帖子ID
            node.put("postId", Integer.valueOf(postId));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SAVE_COMMENT)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onError(String message) {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }

            @Override
            public void onCompleted(String str) {
                ToastUtils.getInstance().show("评论成功");
                etCommentContent.setText("");
                rcv.fullScroll(ScrollView.FOCUS_UP);
                initNewsDataList();
            }
        });
    }

    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        loadingDialog.show();
        initDataList();

        initNewsDataList();
    }


    /**
     * 详情信息
     */
    public void initDataList() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", Integer.valueOf(postId));//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.DISCUSS_DETAIL)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, DetailsDiscussBase.class, new HttpCallBackImpl<DetailsDiscussBase>() {

            @Override
            public void onFinish() {
                if (isFinish && loadingDialog.isShowing()) {
                    haveData.setVisibility(View.VISIBLE);
                    findViewById(R.id.no_data).setVisibility(View.GONE);
                    loadingDialog.dismiss();
                }
                isFinish = true;
            }

            @Override
            public void onCompleted(DetailsDiscussBase bean) {
                DetailsDiscussBase.DataBean.DiscussDetailBean discussDetail = bean.getData().getDiscussDetail();
                createUserId = discussDetail.getCreateUserId();
                mHeadView.setTitle(discussDetail.getProjectCode());
                mHeadView.setTitleVice("/" + discussDetail.getProjectEnglishName());
                mHeadView.setToolbarListener(discussDetail.getProjectId());
                tvProjectCode.setText(discussDetail.getProjectCode());
                GlideUtils.loadCircleUrl(mHeadView.getImageView(), Constants.BASE_IMG_URL + discussDetail.getProjectIcon());
                tvPostTitle.setText(discussDetail.getPostTitle());
                GlideUtils.loadCircleUrl(ivCreateUserIcon, Constants.BASE_IMG_URL + discussDetail.getCreateUserIcon());
                tvCreateUserName.setText(discussDetail.getCreateUserName());
                userId = discussDetail.getCreateUserId();
                tvCreateUserSignature.setText(discussDetail.getCreateUserSignature());
                //关注状态  "//0 未关注；1-已关注；2-不显示关注按钮"
                if (discussDetail.getFollowStatus() == 1) {
                    tvFollowStatus.setSelected(false);
                    tvFollowStatus.setText(getResources().getString(R.string.follow_status_0));
                } else if (discussDetail.getFollowStatus() == 0) {
                    tvFollowStatus.setSelected(true);
                    tvFollowStatus.setText(getResources().getString(R.string.follow_status_1));
                } else {
                    tvFollowStatus.setVisibility(View.GONE);
                }
                tvPostShortDesc.setText(discussDetail.getDisscussContents());
                tvCreateTime.setText(StringUtil.getTimeToM(discussDetail.getCreateTime()));
                try {
                    //{"fileUrl":"/upload/posts/201805/1.jpg","fileName":"进度讨论","extension":"jpg"},
                    JSONArray images = new JSONArray(discussDetail.getPostSmallImages());
                    List<PostDataInfo> lists = new ArrayList<>();
                    for (int i = 0; i < images.length(); i++) {
                        JSONObject strObj = images.getJSONObject(i);
                        PostDataInfo info = new PostDataInfo();
                        info.setUrl(StringUtil.getBeanString(strObj.getString("fileUrl")));
                        info.setName(StringUtil.getBeanString(strObj.getString("fileName")));
                        info.setTitle(StringUtil.getBeanString(strObj.getString("extension")));
                        lists.add(info);
                    }
                    if (lists.size() != 0) {
                        if (lists.size() == 1) {
                            imgUrl = lists.get(0).getUrl();
                            imgName = lists.get(0).getName();
                            ivPostSmallImages.setVisibility(View.VISIBLE);
                            GlideUtils.loadImage(DetailsDiscussActivity.this, ivPostSmallImages, Constants.BASE_IMG_URL + imgUrl);
                        } else {
                            rvImg.setVisibility(View.VISIBLE);
                            imagesadapter.setData(lists);
                        }
                    }
                    if (discussDetail.getTagInfos() != null) {
                        JSONArray object = new JSONArray(discussDetail.getTagInfos());
                        //[{"tagId":1,"tagName":"进度讨论"},{"tagId":3,"tagName":"项目前景讨论"},{"tagId":4,"tagName":"打假"}]
                        String tagAll = "";
                        String tagOnly[] = new String[object.length()];
                        for (int i = 0; i < object.length(); i++) {
                            JSONObject strObj = object.getJSONObject(i);
                            tagOnly[i] = "#" + strObj.getString("tagName") + "#";
                            tagAll += "#" + strObj.getString("tagName") + "#   ";
                        }
                        Clickable.getSpannableString(tagAll, tagOnly, tvTagName, new Clickable.ClickListener() {
                            @Override
                            public void setOnClick(String name) {
                                //ToastUtils.getInstance().show(name);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (discussDetail.getHotComments() != null && discussDetail.getHotComments().size() > 0) {
                    llRm.setVisibility(View.VISIBLE);
                    tvRm.setText("热门测评(" + discussDetail.getHotComments().size() + ")");
                    adapter.setData(discussDetail.getHotComments());
                }
            }
        });

    }

    /**
     * 评论最新
     */
    public void initNewsDataList() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", Integer.valueOf(postId));//帖子ID
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.HOME_DISCUSS_COMMENT_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, DiscussNewInfoBean.class, new HttpCallBackImpl<DiscussNewInfoBean>() {
            @Override
            public void onCompleted(DiscussNewInfoBean newInfoBean) {
                DiscussNewInfoBean.DataBean.CommentsBean commentsBean = newInfoBean.getData().getComments();
                if (commentsBean != null) {
                    if (commentsBean.getRows() != null && commentsBean.getRows().size() > 0) {
                        llZx.setVisibility(View.VISIBLE);
                        tvZx.setText("最新测评(" + commentsBean.getRowCount() + ")");
                        if (pageIndex > 2) {
                            adapterNew.addData(commentsBean.getRows());
                        } else {
                            adapterNew.setData(commentsBean.getRows());
                        }
                    }
                    if (commentsBean.getCurPageNum() == commentsBean.getPageSize()) {
                        refreshLayout.setLoadmoreFinished(true);
                    }
                } else {
                    refreshLayout.setLoadmoreFinished(true);
                }
            }

            @Override
            public void onFinish() {
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isLoading()) {
                    refreshLayout.finishLoadmore(true);
                }
                if (isFinish && loadingDialog.isShowing()) {
                    haveData.setVisibility(View.VISIBLE);
                    findViewById(R.id.no_data).setVisibility(View.GONE);
                    loadingDialog.dismiss();
                }
                isFinish = true;
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            if (data.getExtras().getBoolean(Constants.REQUEST_CODE)) {
                pageIndex = 1;
                initData();
            }
        }
    }
}
