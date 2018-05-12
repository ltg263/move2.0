package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.CommonCommentsBean;
import com.secretk.move.bean.DetailsArticleBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.ui.adapter.ImagesAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.PileLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间： 2018/5/2 14:12
 * 邮箱；ltg263@126.com
 * 描述: 文章详情.
 */
public class DetailsArticleActivity extends BaseActivity {
    @BindView(R.id.tv_post_title)
    TextView tvPostTitle;
    @BindView(R.id.iv_create_user_icon)
    ImageView ivCreateUserIcon;
    @BindView(R.id.tv_create_user_name)
    TextView tvCreateUserName;
    @BindView(R.id.tv_create_user_signature)
    TextView tvCreateUserSignature;
    @BindView(R.id.tv_follow_status)
    TextView tvFollowStatus;
    @BindView(R.id.tv_post_short_desc)
    TextView tvPostShortDesc;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.pile_layout)
    PileLayout pileLayout;
    @BindView(R.id.tv_donate_num)
    TextView tvDonateNum;
    @BindView(R.id.tv_praise_status)
    TextView tvPraiseStatus;
    @BindView(R.id.tv_collect_status)
    TextView tvCollectStatus;
    @BindView(R.id.tv_commendation_Num)
    TextView tvCommendationNum;
    @BindView(R.id.tv_comments_num)
    TextView tvCommentsNum;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    private String postId;
    private ImagesAdapter adapter;
    private List<CommonCommentsBean> hotComments;
    private int createUserId;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_details_article;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mMenuInfos.add(0, new MenuInfo(R.string.share, "分享", R.drawable.ic_share));
        return mHeadView;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        postId = getIntent().getStringExtra("postId");
        setVerticalManager(rvImg);
        adapter = new ImagesAdapter(this);
        rvImg.setAdapter(adapter);
    }

    protected void initData() {
        if(!NetUtil.isNetworkAvailable()){
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", Integer.valueOf(postId));//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.ARTICLE_DETAIL)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, DetailsArticleBean.class, new HttpCallBackImpl<DetailsArticleBean>() {
            @Override
            public void onCompleted(DetailsArticleBean bean) {
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss();
                }
                if (bean.getData() != null) {
                    setInitData(bean.getData().getArticleDetail());
                }
                hotComments = bean.getData().getHotComments();
            }
        });
    }


    public void setInitData(DetailsArticleBean.DataBean.ArticleDetailBean initData) {
        mHeadView.setTitle(initData.getProjectCode());
        mHeadView.setTitleVice("/" + initData.getProjectChineseName());
        mHeadView.setToolbarListener(initData.getProjectId());
        tvPostTitle.setText(initData.getPostTitle());
        GlideUtils.loadCircleUrl(ivCreateUserIcon, Constants.BASE_IMG_URL + initData.getCreateUserIcon());
        tvCreateUserName.setText(initData.getCreateUserName());
        tvCreateUserSignature.setText(initData.getCreateUserSignature());
        createUserId = initData.getCreateUserId();
        //,//"0 未关注；1-已关注；2-不显示关注按钮"\
        if (initData.getFollowStatus() == 0) {
            tvFollowStatus.setText(getString(R.string.follow_status_0));
        } else if (initData.getFollowStatus() == 1) {
            tvFollowStatus.setText(getString(R.string.follow_status_1));
        } else {
            tvFollowStatus.setVisibility(View.GONE);
        }
        tvPostShortDesc.setText(initData.getPostShortDesc());
        tvProjectCode.setText(initData.getProjectCode());
        tvCreateTime.setText(StringUtil.getTimeToM(initData.getCreateTime()));
        tvDonateNum.setText(initData.getDonateNum() + "人已赞助");
        tvPraiseStatus.setText("赞" + String.valueOf(initData.getPraiseNum()));
        ///0-未点赞，1-已点赞，数字
        if (initData.getPraiseStatus() == 0) {
            tvPraiseStatus.setSelected(true);
        } else {
            tvPraiseStatus.setSelected(false);
        }
        //0-未收藏，1-已收藏，数字
        if (initData.getCollectStatus() == 0) {
            tvCollectStatus.setSelected(true);
        } else {
            tvCollectStatus.setSelected(false);
        }
        tvCommendationNum.setText("赞助" + String.valueOf(initData.getCommendationNum()));
        tvCommentsNum.setText("评论" + String.valueOf(initData.getCollectNum()));
        List<PostDataInfo> lists = new ArrayList<>();
        try {
            JSONArray images = new JSONArray(initData.getPostSmallImages());
            for (int i = 0; i < images.length(); i++) {
                JSONObject strObj = images.getJSONObject(i);
                PostDataInfo info = new PostDataInfo();
                info.setUrl(strObj.getString("fileUrl"));
                info.setName(strObj.getString("fileName"));
                info.setTitle(strObj.getString("extension"));
                lists.add(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.setData(lists);
        List<DetailsArticleBean.DataBean.ArticleDetailBean.CommendationListBean> pileLists = initData.getCommendationList();
        if (pileLists != null) {
            initPraises(pileLists);
        }
    }

    /**
     * 设置捐款人头像
     *
     * @param pileLists
     */
    public void initPraises(List<DetailsArticleBean.DataBean.ArticleDetailBean.CommendationListBean> pileLists) {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < pileLists.size(); i++) {
            ImageView imageView = (ImageView) inflater.inflate(R.layout.item_praise, pileLayout, false);
            GlideUtils.loadCircleUrl(imageView, Constants.BASE_IMG_URL + pileLists.get(i).getSendUserIcon());
            pileLayout.addView(imageView);
        }
    }

    @OnClick({R.id.tv_follow_status, R.id.tv_praise_status, R.id.tv_collect_status, R.id.tv_commendation_Num, R.id.tv_comments_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_ge_ren:
                IntentUtil.startHomeActivity(createUserId);
                break;
            case R.id.tv_follow_status:
                tvFollowStatus.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatus.getText().toString().trim(),
                        Constants.SaveFollow.USER,Integer.valueOf(createUserId), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvFollowStatus.setEnabled(true);
                                if(!str.equals(Constants.FOLLOW_ERROR)){
                                    tvFollowStatus.setText(str);
                                }
                            }
                        });
                break;
            case R.id.tv_praise_status:
                tvPraiseStatus.setEnabled(false);
                setPraise(tvPraiseStatus.isSelected(),Integer.valueOf(postId));
                break;
            case R.id.tv_collect_status:
                tvCollectStatus.setEnabled(false);
                NetUtil.saveCollect(!tvCollectStatus.isSelected(),
                        Integer.valueOf(postId), new NetUtil.SaveCollectImp() {
                            @Override
                            public void finishCollect(String str,boolean status) {
                                tvCollectStatus.setEnabled(true);
                                if(!str.equals(Constants.COLLECT_ERROR)){
                                    tvCollectStatus.setSelected(status);
                                }
                            }
                        });

                break;
            case R.id.tv_commendation_Num:
                break;
            case R.id.tv_comments_num:
                Intent intent = new Intent(this,DetailsArticleCommentActivity.class);
                intent.putExtra("postId",String.valueOf(postId));
                intent.putParcelableArrayListExtra("hotComments", (ArrayList<? extends Parcelable>) hotComments);
                startActivity(intent);
                break;
        }
    }
    private void setPraise(boolean isPraise, int postId) {
        NetUtil.setPraise(isPraise, postId, new NetUtil.SaveFollowImpl() {
            @Override
            public void finishFollow(String praiseNum,boolean status) {
                tvPraiseStatus.setEnabled(true);
                ////点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
                if(!praiseNum.equals(Constants.PRAISE_ERROR)){
                    tvPraiseStatus.setSelected(status);
                    tvPraiseStatus.setText("赞"+praiseNum);
                }

            }
        });
    }
}
