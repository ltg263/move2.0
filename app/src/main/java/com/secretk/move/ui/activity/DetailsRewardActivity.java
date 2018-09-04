package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.CommonListBase;
import com.secretk.move.bean.DetailsDiscussBase;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.ui.adapter.ImagesAdapter;
import com.secretk.move.ui.adapter.MainBlFragmentRecyclerAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.GridSpacingItemDecoration;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.FixGridLayout;
import com.secretk.move.view.RecycleScrollView;
import com.secretk.move.view.ShareView;

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
 * 描述：悬赏详情.
 */
public class DetailsRewardActivity extends BaseActivity {

    @BindView(R.id.rv_img)
    RecyclerView rvImg;
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
    @BindView(R.id.ll_zx)
    LinearLayout llZx;
    @BindView(R.id.tv_create_user_name)
    TextView tvCreateUserName;
    @BindView(R.id.iv_model_icon)
    ImageView ivModelIcon;
    @BindView(R.id.iv_model_icon_d)
    ImageView ivModelIconD;
    @BindView(R.id.iv_post_small_images)
    ImageView ivPostSmallImages;
    @BindView(R.id.tv_follow_status)
    TextView tvFollowStatus;
    @BindView(R.id.tv_post_short_desc)
    TextView tvPostShortDesc;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.have_data)
    RelativeLayout haveData;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.ll_add_view)
    FixGridLayout llAddView;
    @BindView(R.id.tv_sort_new)
    TextView tvSortNew;
    @BindView(R.id.tv_sort_time)
    TextView tvSortTime;
    @BindView(R.id.rl_not_content)
    RelativeLayout rlNotContent;
    @BindView(R.id.tv_xs_find)
    TextView tvXsFind;
    @BindView(R.id.tv_xs_1)
    TextView tvXs1;
    @BindView(R.id.tv_xs_2)
    TextView tvXs2;
    @BindView(R.id.tv_go_hd)
    TextView tvGoHd;
    @BindView(R.id.ll_xs)
    LinearLayout llXs;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_xs_title)
    TextView tvXsTitle;
    @BindView(R.id.tv_xs_con)
    TextView tvXsCon;
    @BindView(R.id.rl_xs_xsgc)
    RelativeLayout rlXsXsgc;
    @BindView(R.id.ll_bl_xs)
    LinearLayout llBlXs;
    private MainBlFragmentRecyclerAdapter adapterNew;
    private int postId;
    private ImagesAdapter imagesadapter;
    private String imgUrl;
    private String imgName;
    private boolean isFinish = false;
    private int userId;
    private int createUserId;
    int pageIndex = 1;
    private int projectId;
    String postShortDesc;
    private String activityId;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_details_reward;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("悬赏尽调");
        mMenuInfos.add(0, new MenuInfo(R.string.share, getResources().getString(R.string.share), R.drawable.ic_share));
        return mHeadView;
    }

    @Override
    protected void OnToolbarRightListener() {
        ShareView.showShare(this, mHeadView, activityId, Constants.DISCUSS_SHARE + Integer.valueOf(postId),
                tvPostTitle.getText().toString(), postShortDesc, imgUrl, Integer.valueOf(postId));
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
//        refreshLayout.setEnableRefresh(false);//关闭下拉刷新
        llBlXs.setVisibility(View.VISIBLE);
        postId = getIntent().getIntExtra("postId",0);
        activityId = getIntent().getStringExtra("activityId");
        setHorizontalManager(rvImg);
        rvImg.addItemDecoration(new GridSpacingItemDecoration());
        imagesadapter = new ImagesAdapter(this);
        rvImg.setAdapter(imagesadapter);

        setVerticalManager(rvNewReview);
        adapterNew = new MainBlFragmentRecyclerAdapter(this);
        rvNewReview.setAdapter(adapterNew);
        initRefresh();
        loadingDialog.show();
        tvProjectCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startProjectActivity(projectId);
            }
        });
    }


    @OnClick({R.id.tv_follow_status, R.id.iv_post_small_images,R.id.rl_ge_ren,
            R.id.tv_sort_new, R.id.tv_sort_time})
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

                Intent intent = new Intent(this, ImageViewVpAcivity.class);
                intent.putParcelableArrayListExtra("lists", (ArrayList<? extends Parcelable>) imageLists);
                intent.putExtra("position", 0);
                startActivity(intent);
//                String key[] = {"imgUrl", "imgName"};
//                String values[] = {imgUrl, imgName};
//                IntentUtil.startActivity(TemporaryIV.class, key, values);
                break;
            case R.id.rl_ge_ren:
                IntentUtil.startHomeActivity(createUserId);
                break;
            case R.id.tv_sort_new:
                if (!isSortField) {
                    pageIndex = 1;
                    isSortField = true;
                    refreshLayout.setNoMoreData(false);
                    initNewsDataList();
                }
                break;
            case R.id.tv_sort_time:
                if (isSortField) {
                    pageIndex = 1;
                    isSortField = false;
                    refreshLayout.setNoMoreData(false);
                    initNewsDataList();
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
                if (!NetUtil.isNetworkAvailable()) {
                    ToastUtils.getInstance().show(getString(R.string.network_error));
                    return;
                }
                initDataList();
                pageIndex = 1;
                isSortField = false;
                initNewsDataList();
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                initNewsDataList();
            }
        });
    }


    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        initDataList();

        initNewsDataList();
    }

    List<PostDataInfo> imageLists;

    /**
     * 详情信息
     */
    public void initDataList() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", postId);//帖子ID
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
                postShortDesc = discussDetail.getPostShortDesc();
                projectId = discussDetail.getProjectId();
                if (StringUtil.isNotBlank(discussDetail.getProjectCode())) {
                    tvProjectCode.setVisibility(View.VISIBLE);
                    tvProjectCode.setText(discussDetail.getProjectCode());
                }
                if (StringUtil.isNotBlank(discussDetail.getPostTitle())) {
                    tvPostTitle.setVisibility(View.VISIBLE);
                    tvPostTitle.setText(discussDetail.getPostTitle());
                }
                GlideUtils.loadCircleUserUrl(DetailsRewardActivity.this, ivCreateUserIcon, Constants.BASE_IMG_URL + discussDetail.getCreateUserIcon());
                if (discussDetail.getUserType() != 1) {
//                    ivModelIcon.setVisibility(View.VISIBLE);
//                    StringUtil.getUserType(discussDetail.getUserType(),ivModelIcon);
//                    ivModelIconD.setVisibility(View.VISIBLE);
                    StringUtil.getUserType(discussDetail.getUserType(), ivModelIconD);
                }
                tvCreateUserName.setText(discussDetail.getCreateUserName());
                userId = discussDetail.getCreateUserId();
                //关注状态  "//0 未关注；1-已关注；2-不显示关注按钮"
                if (discussDetail.getFollowStatus() == 0) {
                    tvFollowStatus.setSelected(false);
                    tvFollowStatus.setText(getResources().getString(R.string.follow_status_0));
                } else if (discussDetail.getFollowStatus() == 1) {
                    tvFollowStatus.setSelected(true);
                    tvFollowStatus.setText(getResources().getString(R.string.follow_status_1));
                } else {
                    tvFollowStatus.setVisibility(View.GONE);
                }
                if (baseUserId == discussDetail.getCreateUserId()) {
                    tvFollowStatus.setVisibility(View.GONE);
                }
                String a = StringUtil.getBeanString(discussDetail.getPostShortDesc());
                String b = "【奖励1000FIND】";
                String c = "<font color=\"#ff2851\">" + b + "</font>" + a;
                tvPostShortDesc.setText(Html.fromHtml(c));
//                tvPostShortDesc.setText(discussDetail.getDisscussContents());
                tvCreateTime.setText(StringUtil.getTimeToM(discussDetail.getCreateTime()));
                if (StringUtil.isNotBlank(discussDetail.getPostSmallImages())) {
                    try {
                        //{"fileUrl":"/upload/posts/201805/1.jpg","fileName":"进度讨论","extension":"jpg"},
                        JSONArray images = new JSONArray(discussDetail.getPostSmallImages());
                        imageLists = new ArrayList<>();
                        for (int i = 0; i < images.length(); i++) {
                            JSONObject strObj = images.getJSONObject(i);
                            PostDataInfo info = new PostDataInfo();
                            info.setUrl(StringUtil.getBeanString(strObj.getString("fileUrl")));
                            info.setName(StringUtil.getBeanString(strObj.getString("fileName")));
                            info.setTitle(StringUtil.getBeanString(strObj.getString("extension")));
                            imageLists.add(info);
                        }
                        if (imageLists.size() != 0) {
                            imgName = imageLists.get(0).getName();
                            imgUrl = imageLists.get(0).getUrl();
                            if (imageLists.size() == 1) {
                                ivPostSmallImages.setVisibility(View.VISIBLE);
                                GlideUtils.loadSideMaxImage(DetailsRewardActivity.this, ivPostSmallImages, Constants.BASE_IMG_URL + imgUrl);
                            } else {
                                rvImg.setVisibility(View.VISIBLE);
                                imagesadapter.setData(imageLists);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtil.isNotBlank(discussDetail.getTagInfos()) && discussDetail.getTagInfos().contains("tagName")) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(15, 10, 15, 10);
                    try {
                        JSONArray array = new JSONArray(discussDetail.getTagInfos());
                        if (llAddView.getChildCount() > 0) {
                            llAddView.removeAllViews();
                        }
                        for (int i = 0; i < array.length(); i++) {
                            final JSONObject object = array.getJSONObject(i);
                            if (StringUtil.isNotBlank(object.getString("tagName"))) {
                                final TextView crack_down = new TextView(DetailsRewardActivity.this);
                                crack_down.setPadding(20, 10, 20, 10);
                                crack_down.setBackground(getResources().getDrawable(R.drawable.shape_add_label_selected));
                                crack_down.setTextColor(getResources().getColor(R.color.app_background));
                                crack_down.setTextSize(14);
                                crack_down.setText(object.getString("tagName"));
                                crack_down.setLayoutParams(params);
                                llAddView.addView(crack_down);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    boolean isSortField = false;

    /**
     * 评论最新
     */
    public void initNewsDataList() {
        tvSortNew.setTextColor(getResources().getColor(R.color.title_gray_66));
        tvSortTime.setTextColor(getResources().getColor(R.color.app_background));
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", Integer.valueOf(postId));//帖子ID
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
            if (isSortField) {
                tvSortNew.setTextColor(getResources().getColor(R.color.app_background));
                tvSortTime.setTextColor(getResources().getColor(R.color.title_gray_66));
//                node.put("sortField", "praise_num");//需要按点赞数倒序排序的话 增加传入参数ortField 值为字符串“praise_num”
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.MAIN_DISCUSS)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, CommonListBase.class, new HttpCallBackImpl<CommonListBase>() {
            @Override
            public void onCompleted(CommonListBase newInfoBean) {
                CommonListBase.DataBean.DetailsBean commentsBean = newInfoBean.getData().getRecommends();
                if (commentsBean != null) {
                    if (commentsBean.getRows() != null && commentsBean.getRows().size() > 0) {
                        rlNotContent.setVisibility(View.GONE);
                        llZx.setVisibility(View.VISIBLE);
                        if (pageIndex > 2) {
                            adapterNew.setAddData(commentsBean.getRows());
                        } else {
                            adapterNew.setData(commentsBean.getRows());
                        }
                    }
                    if (commentsBean.getCurPageNum() == commentsBean.getPageSize()) {
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }
                } else {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                if (rlNotContent.getVisibility() == View.VISIBLE) {
                    refreshLayout.setEnableLoadMore(false);
                } else {
                    refreshLayout.setEnableLoadMore(true);
                }
            }

            @Override
            public void onFinish() {
//                if (refreshLayout.isRefreshing()) {
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
//                if (refreshLayout.isLoading()) {
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore(true);
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
}
