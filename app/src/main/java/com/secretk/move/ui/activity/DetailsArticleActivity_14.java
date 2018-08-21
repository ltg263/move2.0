package com.secretk.move.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DetailsArticleBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.ui.adapter.ImagesAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.GridSpacingItemDecoration;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.Clickable;
import com.secretk.move.view.DialogUtils;
import com.secretk.move.view.PileLayout;
import com.secretk.move.view.PopupWindowUtils;
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
 * 描述: 文章详情.
 */
public class DetailsArticleActivity_14 extends BaseActivity {

    @BindView(R.id.head_app_server)
    AppBarHeadView headAppServer;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tv_icon)
    ImageView tvIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.rl_top_theme)
    RelativeLayout rlTopTheme;
    @BindView(R.id.tv_post_title)
    TextView tvPostTitle;
    @BindView(R.id.iv_create_user_icon)
    ImageView ivCreateUserIcon;
    @BindView(R.id.iv_model_icon_d)
    ImageView ivModelIconD;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_create_user_name)
    TextView tvCreateUserName;
    @BindView(R.id.iv_model_icon)
    ImageView ivModelIcon;
    @BindView(R.id.tv_create_user_signature)
    TextView tvCreateUserSignature;
    @BindView(R.id.tv_follow_status)
    TextView tvFollowStatus;
    @BindView(R.id.rl_ge_ren)
    RelativeLayout rlGeRen;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.tv_post_short_desc)
    TextView tvPostShortDesc;
    @BindView(R.id.wv_post_short_desc)
    WebView wvPostShortDesc;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.tv_crack_down)
    TextView tvCrackDown;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.pile_layout)
    PileLayout pileLayout;
    @BindView(R.id.tv_donate_num)
    TextView tvDonateNum;
    @BindView(R.id.view_bottom)
    View viewBottom;
    @BindView(R.id.tv_praise_status)
    TextView tvPraiseStatus;
    @BindView(R.id.tv_collect_status)
    TextView tvCollectStatus;
    @BindView(R.id.tv_commendation_Num)
    TextView tvCommendationNum;
    @BindView(R.id.tv_comments_num)
    TextView tvCommentsNum;
    @BindView(R.id.ll_bottom_bnt)
    LinearLayout llBottomBnt;
    @BindView(R.id.yes_data)
    RelativeLayout yesData;
    private String postId;
    private ImagesAdapter adapter;
    private int createUserId;
    private int projectId;
    private int praiseNum;
    String postShortDesc = "";
    String imgUrl = "";
    private String activityId;

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
    protected void OnToolbarRightListener() {
        ShareView.showShare(this, mHeadView, activityId, Constants.ARTICLE_SHARE + Integer.valueOf(postId),
                tvPostTitle.getText().toString(), postShortDesc, imgUrl, Integer.valueOf(postId));
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        postId = getIntent().getStringExtra("postId");
        activityId = getIntent().getStringExtra("activityId");
        setVerticalManager(rvImg);
        rvImg.addItemDecoration(new GridSpacingItemDecoration());
        adapter = new ImagesAdapter(this);
        rvImg.setAdapter(adapter);
        WebSettings webSettings = wvPostShortDesc.getSettings();//获取webview设置属性
        webSettings.setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
        webSettings.setBlockNetworkImage(false); // 解决图片不显示

        //支持javascript
        webSettings.setJavaScriptEnabled(true);
//        // 设置可以支持缩放
//        webSettings.setSupportZoom(true);
//        // 设置出现缩放工具
//        webSettings.setBuiltInZoomControls(true);
//        //扩大比例的缩放
//        webSettings.setUseWideViewPort(true);
//        //自适应屏幕
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        webSettings.setLoadWithOverviewMode(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //如果不设置WebViewClient，请求会跳转系统浏览器
        wvPostShortDesc.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                LogUtil.w("11:" + url);
                if (StringUtil.isNotBlank(url)) {
                    IntentUtil.startWebViewActivity(url.toString(), getString(R.string.app_name));
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    LogUtil.w("request.getUrl().toString():" + request.getUrl().toString());
                    if (request.getUrl().toString().contains("sina.cn")) {
//                        view.loadUrl("http://ask.csdn.net/questions/178242");
                        return true;
                    }
                }
                return false;
            }

        });
        loadingDialog.show();
    }

    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            loadingDialog.dismiss();
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
        RetrofitUtil.request(params, DetailsArticleBean.class, new HttpCallBackImpl<DetailsArticleBean>() {
            @Override
            public void onCompleted(DetailsArticleBean bean) {
                if (bean.getData() != null) {
                    setInitData(bean.getData().getArticleDetail());
                }
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
                findViewById(R.id.yes_data).setVisibility(View.VISIBLE);
                findViewById(R.id.no_data).setVisibility(View.GONE);
            }
        });
    }


    public void setInitData(DetailsArticleBean.DataBean.ArticleDetailBean initData) {
        mHeadView.setTitle(StringUtil.getBeanString(initData.getProjectCode()));
        mHeadView.setTitleVice("/" + StringUtil.getBeanString(initData.getProjectChineseName()));
        mHeadView.setToolbarListener(initData.getProjectId());
        projectId = initData.getProjectId();
        tvPostTitle.setText(StringUtil.getBeanString(initData.getPostTitle()));
        postShortDesc = initData.getPostShortDesc();
        GlideUtils.loadCircleUserUrl(this, ivCreateUserIcon, Constants.BASE_IMG_URL + initData.getCreateUserIcon());
        tvCreateUserName.setText(StringUtil.getBeanString(initData.getCreateUserName()));
        tvCreateUserSignature.setText(StringUtil.getBeanString(initData.getCreateUserSignature()));
        if (initData.getUserType() != 1) {
//            ivModelIcon.setVisibility(View.VISIBLE);
//            StringUtil.getUserType(initData.getUserType(), ivModelIcon);
//            ivModelIconD.setVisibility(View.VISIBLE);
            StringUtil.getUserType(initData.getUserType(), ivModelIconD);
        }
        createUserId = initData.getCreateUserId();
        //,//"0 未关注；1-已关注；2-不显示关注按钮"\
        if (initData.getFollowStatus() == 0) {
            tvFollowStatus.setSelected(false);
            tvFollowStatus.setText(getResources().getString(R.string.follow_status_0));
        } else if (initData.getFollowStatus() == 1) {
            tvFollowStatus.setSelected(true);
            tvFollowStatus.setText(getResources().getString(R.string.follow_status_1));
        } else {
            tvFollowStatus.setVisibility(View.GONE);
        }
        if (baseUserId == initData.getCreateUserId()) {
            tvFollowStatus.setVisibility(View.GONE);
        }
//        tvPostShortDesc.setText(StringUtil.getBeanString(initData.getArticleContents()));
//        wvPostShortDesc.loadData(StringUtil.getNewContent(StringUtil.getBeanString(initData.getArticleContents())), "text/html; charset=UTF-8", null);//这种写法可以正确解码
        wvPostShortDesc.loadData(StringUtil.getNewContent(initData.getArticleContents()), "text/html; charset=UTF-8", null);//这种写法可以正确解码
        tvProjectCode.setText(StringUtil.getBeanString(initData.getProjectCode()));
        tvCreateTime.setText("发布于 " + StringUtil.getTimeToM(initData.getCreateTime()));
        if (initData.getDonateNum() > 0) {
            pileLayout.setVisibility(View.VISIBLE);
            tvDonateNum.setVisibility(View.VISIBLE);
            tvDonateNum.setText(initData.getDonateNum() + getString(R.string.sponsor_num));
        }
        praiseNum = initData.getPraiseNum();
        tvPraiseStatus.setText(getString(R.string.like) + String.valueOf(praiseNum));
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
        tvCommendationNum.setText("赞助" + String.valueOf(new Double(initData.getCommendationNum()).intValue()));

        tvCommentsNum.setText("评论" + String.valueOf(initData.getCommentsNum()));
        if (StringUtil.isNotBlank(initData.getTagInfos()) && initData.getTagInfos().contains("tagName")) {
            try {
                JSONArray object = new JSONArray(initData.getTagInfos());
                //[{"tagId":1,"tagName":"进度讨论"},{"tagId":3,"tagName":"项目前景讨论"},{"tagId":4,"tagName":"打假"}]
                String tagAll = "";
                String tagOnly[] = new String[object.length()];
                for (int i = 0; i < object.length(); i++) {
                    JSONObject strObj = object.getJSONObject(i);
                    tagOnly[i] = "#" + strObj.getString("tagName") + "#";
                    tagAll += "#" + strObj.getString("tagName") + "#   ";
                }
                Clickable.getSpannableString(tagAll, tagOnly, tvCrackDown, new Clickable.ClickListener() {
                    @Override
                    public void setOnClick(String name) {
                        //ToastUtils.getInstance().show(name);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        List<PostDataInfo> lists = new ArrayList<>();
        try {
            if (StringUtil.isNotBlank(initData.getPostSmallImages())) {
                JSONArray images = new JSONArray(initData.getPostSmallImages());
                for (int i = 0; i < images.length(); i++) {
                    JSONObject strObj = images.getJSONObject(i);
                    PostDataInfo info = new PostDataInfo();
                    info.setUrl(strObj.getString("fileUrl"));
                    info.setName(strObj.getString("fileName"));
                    info.setTitle(strObj.getString("extension"));
                    lists.add(info);
                }
                if (lists.size() > 0) {
                    imgUrl = lists.get(0).getUrl();
                }
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
        LayoutInflater nflater = LayoutInflater.from(this);
        if (pileLayout != null) {
            pileLayout.removeAllViews();
        }
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < pileLists.size() && i < 7; i++) {
            if (!lists.contains(String.valueOf(pileLists.get(i).getSendUserId()))) {
                ImageView imageView = (ImageView) nflater.inflate(R.layout.item_praise, pileLayout, false);
                GlideUtils.loadCircleUserUrl(this, imageView, Constants.BASE_IMG_URL + pileLists.get(i).getSendUserIcon());
                pileLayout.addView(imageView);
            }
            lists.add(String.valueOf(pileLists.get(i).getSendUserId()));
        }
    }

    @OnClick({R.id.rl_ge_ren, R.id.tv_follow_status, R.id.tv_praise_status, R.id.tv_collect_status, R.id.tv_commendation_Num, R.id.tv_comments_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_ge_ren:
                IntentUtil.startHomeActivity(createUserId);
                break;
            case R.id.tv_follow_status:
                tvFollowStatus.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatus,
                        Constants.SaveFollow.USER, Integer.valueOf(createUserId), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvFollowStatus.setEnabled(true);
                                if (!str.equals(Constants.FOLLOW_ERROR)) {
                                    tvFollowStatus.setText(str);
                                }
                            }
                        });
                break;
            case R.id.tv_praise_status:
                if (!tvPraiseStatus.isSelected()) {
                    return;
                }

                if (!NetUtil.isPraise(createUserId, baseUserId)) {
                    return;
                }
                tvPraiseStatus.setEnabled(false);
                String str;
                if (tvPraiseStatus.isSelected()) {
                    str = getString(R.string.like) + String.valueOf(praiseNum + 1);
                } else {
                    str = getString(R.string.like) + String.valueOf(praiseNum - 1);
                }
                tvPraiseStatus.setText(str);
                tvPraiseStatus.setSelected(!tvPraiseStatus.isSelected());
                setPraise(!tvPraiseStatus.isSelected(), Integer.valueOf(postId));
                break;
            case R.id.tv_collect_status:
                tvCollectStatus.setEnabled(false);
                tvCollectStatus.setSelected(!tvCollectStatus.isSelected());
                NetUtil.saveCollect(tvCollectStatus.isSelected(),
                        Integer.valueOf(postId), new NetUtil.SaveCollectImp() {
                            @Override
                            public void finishCollect(String str, boolean status) {
                                tvCollectStatus.setEnabled(true);
                                if (!str.equals(Constants.COLLECT_ERROR)) {
                                    DialogUtils.showDialogPraise(DetailsArticleActivity_14.this, 2, status, 0);
                                    tvCollectStatus.setSelected(status);
                                }
                            }
                        });

                break;
            case R.id.tv_commendation_Num:
                if (!NetUtil.isSponsor(createUserId, baseUserId)) {
                    return;
                }
                PopupWindowUtils window = new PopupWindowUtils(this, new PopupWindowUtils.GiveDialogInterface() {
                    @Override
                    public void btnConfirm(String season) {
                        NetUtil.commendation(Integer.valueOf(postId), createUserId, Double.valueOf(season), projectId, new NetUtil.SaveCommendationImp() {
                            @Override
                            public void finishCommendation(String commendationNum, String donateNum, boolean status) {
                                if (status) {
//                                    tvCommendationNum.setText(getString(R.string.sponsor) + commendationNum);
//                                    tvDonateNum.setText(donateNum + getString(R.string.sponsor_num));
                                    initData();
                                }
                            }
                        });
                    }
                });
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                window.showAtLocation(findViewById(R.id.head_app_server), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                break;
            case R.id.tv_comments_num:
                IntentUtil.startCommentActivity(String.valueOf(postId), Constants.ARTICLE_COMMENT_LIST,
                        Constants.ARTICLE_SHARE + Integer.valueOf(postId), tvPostTitle.getText().toString(), postShortDesc);
                break;
        }
    }

    private void setPraise(boolean isPraise, int postId) {
        NetUtil.setPraise(isPraise, postId, new NetUtil.SaveFollowImpl() {
            @Override
            public void finishFollow(String praiseNum, boolean status, double find) {
                tvPraiseStatus.setEnabled(true);
                ////点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
                if (!praiseNum.equals(Constants.PRAISE_ERROR)) {
                    DialogUtils.showDialogPraise(DetailsArticleActivity_14.this, 1, true, find);
                    tvPraiseStatus.setSelected(status);
                    DetailsArticleActivity_14.this.praiseNum = Integer.valueOf(praiseNum);
                    tvPraiseStatus.setText(getString(R.string.like) + praiseNum);
                }

            }
        });
    }
}
