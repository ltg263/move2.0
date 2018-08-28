package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import com.secretk.move.bean.CommonCommentsBean;
import com.secretk.move.bean.DetailsReviewBean;
import com.secretk.move.bean.DiscussNewInfoBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.ui.adapter.DetailsDiscussAdapter;
import com.secretk.move.ui.adapter.ImagesAdapter;
import com.secretk.move.ui.adapter.ProgressAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.GridSpacingItemDecoration;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.KeybordS;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;
import com.secretk.move.view.FixGridLayout;
import com.secretk.move.view.InputMethodLayout;
import com.secretk.move.view.PileLayout;
import com.secretk.move.view.PopupWindowUtils;
import com.secretk.move.view.ProgressBarStyleView;
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
 * 描述：文章详情.
 */
public class DetailsReviewAllActivity extends BaseActivity {

    @BindView(R.id.input_method_layout)
    InputMethodLayout inputMethodLayout;
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
    @BindView(R.id.iv_model_icon)
    ImageView ivModelIcon;
    @BindView(R.id.iv_model_icon_d)
    ImageView ivModelIconD;
    @BindView(R.id.tv_create_user_signature)
    TextView tvCreateUserSignature;
    @BindView(R.id.iv_post_small_images)
    ImageView ivPostSmallImages;
    @BindView(R.id.tv_follow_status)
    TextView tvFollowStatus;
    @BindView(R.id.tv_post_short_desc)
    TextView tvPostShortDesc;
    @BindView(R.id.wv_post_short_desc)
    WebView wvPostShortDesc;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_tag_name)
    TextView tvTagName;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.have_data)
    RelativeLayout haveData;
    @BindView(R.id.iv_sc)
    ImageView ivSc;
    @BindView(R.id.iv_dz)
    ImageView ivDz;
    @BindView(R.id.tv_dz_num)
    TextView tvDzNum;
    @BindView(R.id.tvRead)
    TextView tvRead;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.ll_add_view)
    FixGridLayout llAddView;
    @BindView(R.id.pile_layout)
    PileLayout pileLayout;
    @BindView(R.id.tv_donate_num)
    TextView tvDonateNum;
    @BindView(R.id.tv_pl_num)
    TextView tvPlNum;
    @BindView(R.id.iv_pl)
    ImageView ivPl;
    @BindView(R.id.rl_pl)
    RelativeLayout rlPl;
    @BindView(R.id.rl_sc)
    RelativeLayout rlSc;
    @BindView(R.id.rl_dz)
    RelativeLayout rlDz;
    @BindView(R.id.rl_select_yse)
    RelativeLayout rlSelectYse;
    @BindView(R.id.rl_select_no)
    RelativeLayout rlSelectNo;
    @BindView(R.id.ll_review)
    LinearLayout llReview;
    @BindView(R.id.tv_sort_new)
    TextView tvSortNew;
    @BindView(R.id.tv_sort_time)
    TextView tvSortTime;
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.rl_not_content)
    RelativeLayout rlNotContent;
    @BindView(R.id.pb_comprehensive_evaluation)
    ProgressBarStyleView pbComprehensiveEvaluation;
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
    private int projectId;
    String postShortDesc;
    private String activityId;
    private ProgressAdapter adapterProgress;

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
        initWebView();
        llReview.setVisibility(View.VISIBLE);
        postId = getIntent().getStringExtra("postId");
        activityId = getIntent().getStringExtra("activityId");
        setHorizontalManager(rvImg);
        rvImg.addItemDecoration(new GridSpacingItemDecoration());
        imagesadapter = new ImagesAdapter(this);
        rvImg.setAdapter(imagesadapter);


        setVerticalManager(rvReview);
        adapterProgress = new ProgressAdapter(this);
        rvReview.setAdapter(adapterProgress);

        setVerticalManager(rvkHotReview);
        adapter = new DetailsDiscussAdapter(this);
        rvkHotReview.setAdapter(adapter);

        setVerticalManager(rvNewReview);
        adapterNew = new DetailsDiscussAdapter(this);
        rvNewReview.setAdapter(adapterNew);
        initRefresh();
        inputMethod();
        StringUtil.etSearchChangedListener(etContent, null, new StringUtil.EtChange() {
            @Override
            public void etYes() {
                if (!etContent.getText().toString().contains(strLs) && !strLs.equals("find_apk")) {
                    parentCommentsId = 0;
                    strLs = "find_apk";
                    etContent.setText("");
                }
            }
        });
        loadingDialog.show();
    }

    private void initWebView() {
        WebSettings webSettings = wvPostShortDesc.getSettings();//获取webview设置属性
        webSettings.setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
        webSettings.setBlockNetworkImage(false); // 解决图片不显示
        //支持javascript
        webSettings.setJavaScriptEnabled(true);

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
    }

    private void inputMethod() {
        inputMethodLayout.setOnkeyboarddStateListener(new InputMethodLayout.onKeyboardsChangeListener() {// 监听软键盘状态

            @Override
            public void onKeyBoardStateChange(int state) {
                // TODO Auto-generated method stub
                switch (state) {
                    case InputMethodLayout.KEYBOARD_STATE_SHOW:
                        etContent.setFocusable(true);
                        etContent.setFocusableInTouchMode(true);
                        etContent.requestFocus();
                        rlSelectYse.setVisibility(View.VISIBLE);
                        rlSelectNo.setVisibility(View.GONE);
                        break;
                    case InputMethodLayout.KEYBOARD_STATE_HIDE:
                        rlSelectYse.setVisibility(View.GONE);
                        rlSelectNo.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.tv_follow_status, R.id.iv_post_small_images, R.id.tv_send, R.id.rl_ge_ren,
            R.id.tv_commendation_Num, R.id.rl_sc, R.id.rl_dz, R.id.rl_pl, R.id.tv_content,
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
            case R.id.tv_send:
                String str = etContent.getText().toString().trim();
                if (StringUtil.isNotBlank(str)) {
                    if (str.contains(strLs)) {
                        str = str.replaceAll(strLs, "");
                    }
                    KeybordS.closeKeybord(etContent, this);
                    saveComment(str);
                } else {
                    ToastUtils.getInstance().show("内容不能为空");
                }
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
                                    initDataList();
//                                    tvCommendationNum.setText(getString(R.string.sponsor)+ commendationNum);
//                                    tvDonateNum.setText(donateNum+getString(R.string.sponsor_num));
                                }
                            }
                        });
                    }
                });
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                window.showAtLocation(findViewById(R.id.head_app_server), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                break;
            case R.id.rl_sc:
                rlSc.setEnabled(false);
                ivSc.setSelected(!ivSc.isSelected());
                NetUtil.saveCollect(ivSc.isSelected(),
                        Integer.valueOf(postId), new NetUtil.SaveCollectImp() {
                            @Override
                            public void finishCollect(String str, boolean status) {
                                rlSc.setEnabled(true);
                                if (!str.equals(Constants.COLLECT_ERROR)) {
                                    DialogUtils.showDialogPraise(DetailsReviewAllActivity.this, 2, status, 0);
                                    ivSc.setSelected(status);
                                }
                            }
                        });

                break;
            case R.id.rl_dz:
                if (!ivDz.isSelected()) {
                    return;
                }
                if (!NetUtil.isPraise(createUserId, baseUserId)) {
                    return;
                }
                rlDz.setEnabled(false);
                String strS;
                if (ivDz.isSelected()) {
                    strS = String.valueOf(praiseNum + 1);
                } else {
                    strS = String.valueOf(praiseNum - 1);
                }
                tvDzNum.setText(strS);
                ivDz.setSelected(!ivDz.isSelected());
                setPraise(!ivDz.isSelected(), Integer.valueOf(postId));
                break;
            case R.id.rl_pl:
//                rcv.fullScroll(ScrollView.FOCUS_UP);
                if (llRm.getVisibility() == View.VISIBLE) {
                    if (rcv.getScrollY() < llRm.getTop()) {
                        rcv.scrollTo(0, llRm.getTop());
                    } else {
                        rcv.fullScroll(ScrollView.FOCUS_UP);
                    }
                } else if (llZx.getVisibility() == View.VISIBLE) {
                    if (rcv.getScrollY() < llZx.getTop()) {
                        rcv.scrollTo(0, llZx.getTop());
                    } else {
                        rcv.fullScroll(ScrollView.FOCUS_UP);
                    }
                }
                break;
            case R.id.tv_content:
                KeybordS.openKeybord(etContent, this);
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
                pageIndex = 1;
                isSortField = false;
                initDataList();

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

    int parentCommentsId = 0;

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
            if (parentCommentsId != 0) {
                node.put("parentCommentsId", parentCommentsId);//parentCommentsId 未null
                node.put("becommentedId", parentCommentsId);//becommentedId 未null
            }

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
                ToastUtils.getInstance().show(message);
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }

            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject object = new JSONObject(str).getJSONObject("data");
                    boolean isShare = object.getBoolean("isCommentAward");
                    double amount = 0;
                    if (isShare) {
                        amount = object.getDouble("amount");
                    }
                    DialogUtils.showDialogPraise(DetailsReviewAllActivity.this, 6, isShare, amount);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                etContent.setText("");

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
            node.put("postId", Integer.valueOf(postId));//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.EVALUATION_DETAIL)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, DetailsReviewBean.class, new HttpCallBackImpl<DetailsReviewBean>() {
            @Override
            public void onFinish() {
                if (isFinish && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
                isFinish = true;
            }

            @Override
            public void onCompleted(DetailsReviewBean bean) {
                DetailsReviewBean.DataBean.EvaluationDetailBean evaluationDetail = bean.getData().getEvaluationDetail();
                haveData.setVisibility(View.VISIBLE);
                findViewById(R.id.no_data).setVisibility(View.GONE);
                createUserId = evaluationDetail.getCreateUserId();
                mHeadView.setTitle(evaluationDetail.getProjectCode());
                postShortDesc = evaluationDetail.getPostShortDesc();
                mHeadView.setTitleVice("/" + evaluationDetail.getProjectChineseName());
                //modelType = 1-简单评测；2-全面系统专业评测;3-部分系统专业评测；4-专业评测-自定义类型
                //进度名称
                try {
                    String modelType = getResources().getString(R.string.comprehensive_evaluation);
                    if (evaluationDetail.getModelType() == Constants.ModelType.MODEL_TYPE_PART) {
                        String evaDetail = evaluationDetail.getProfessionalEvaDetail();
                        if (StringUtil.isNotBlank(evaDetail)) {
                            JSONArray array = new JSONArray(evaDetail);
                            modelType = array.getJSONObject(0).getString("modelName");
                        }
                    }
                    pbComprehensiveEvaluation.setTvOne(modelType, 0, getResources().getColor(R.color.title_gray));
                    pbComprehensiveEvaluation.setTvThree(evaluationDetail.getTotalScore(), 16, R.color.app_background);
                    pbComprehensiveEvaluation.setPbProgressMaxVisible();
                    if (evaluationDetail.getModelType() == Constants.ModelType.MODEL_TYPE_SIMPLENESS) {
                        if (StringUtil.isNotBlank(evaluationDetail.getPostSmallImages())) {
                            try {
                                //{"fileUrl":"/upload/posts/201805/1.jpg","fileName":"进度讨论","extension":"jpg"},
                                JSONArray images = new JSONArray(evaluationDetail.getPostSmallImages());
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
                                        GlideUtils.loadSideMaxImage(DetailsReviewAllActivity.this, ivPostSmallImages, Constants.BASE_IMG_URL + imgUrl);
                                    } else {
                                        rvImg.setVisibility(View.VISIBLE);
                                        imagesadapter.setData(imageLists);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (evaluationDetail.getModelType() == Constants.ModelType.MODEL_TYPE_ALL
                        || evaluationDetail.getModelType() == Constants.ModelType.MODEL_TYPE_ALL_NEW) {
                    try {
                        initProfessionalData(evaluationDetail.getProfessionalEvaDetail());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (evaluationDetail.getCommentsNum() != 0) {
                    tvPlNum.setVisibility(View.VISIBLE);
                    tvPlNum.setText(String.valueOf(evaluationDetail.getCommentsNum()));
                }
//共有8人赞助10000FIND
                if (evaluationDetail.getDonateNum() > 0) {
                    pileLayout.setVisibility(View.VISIBLE);
                    tvDonateNum.setText("共有" + evaluationDetail.getDonateNum() + "人赞助" + String.valueOf(new Double(evaluationDetail.getCommendationNum()).intValue()) + "FIND");
                }
                projectId = evaluationDetail.getProjectId();
                mHeadView.setToolbarListener(projectId);
                if (StringUtil.isNotBlank(evaluationDetail.getProjectCode())) {
                    tvProjectCode.setVisibility(View.VISIBLE);
                    tvProjectCode.setText(evaluationDetail.getProjectCode());
                }
                GlideUtils.loadCircleProjectUrl(DetailsReviewAllActivity.this, mHeadView.getImageView(), Constants.BASE_IMG_URL + evaluationDetail.getProjectIcon());
                if (StringUtil.isNotBlank(evaluationDetail.getPostTitle())) {
                    tvPostTitle.setVisibility(View.VISIBLE);
                    tvPostTitle.setText(evaluationDetail.getPostTitle());
                }
                GlideUtils.loadCircleUserUrl(DetailsReviewAllActivity.this, ivCreateUserIcon, Constants.BASE_IMG_URL + evaluationDetail.getCreateUserIcon());
                if (evaluationDetail.getUserType() != 1) {
//                    ivModelIcon.setVisibility(View.VISIBLE);
//                    StringUtil.getUserType(discussDetail.getUserType(),ivModelIcon);
//                    ivModelIconD.setVisibility(View.VISIBLE);
                    StringUtil.getUserType(evaluationDetail.getUserType(), ivModelIconD);
                }
                tvCreateUserName.setText(evaluationDetail.getCreateUserName());
                userId = evaluationDetail.getCreateUserId();
                tvCreateUserSignature.setText(evaluationDetail.getCreateUserSignature());
                //0-未收藏，1-已收藏，数字
                if (evaluationDetail.getCollectStatus() == 0) {
                    ivSc.setSelected(true);
                } else {
                    ivSc.setSelected(false);
                }
                if (evaluationDetail.getPraiseNum() != 0) {
                    tvDzNum.setVisibility(View.VISIBLE);
                    tvDzNum.setText(String.valueOf(evaluationDetail.getPraiseNum()));
                }

                if (evaluationDetail.getPostTotalIncome() == 0) {
                    tvRead.setText("待结算");
                } else {
                    if (evaluationDetail.getPostTotalIncome() == (int) evaluationDetail.getPostTotalIncome()) {
                        tvRead.setText((int) evaluationDetail.getPostTotalIncome() + "");
                    } else {
                        tvRead.setText(evaluationDetail.getPostTotalIncome() + "");
                    }
                }
                ///0-未点赞，1-已点赞，数字
                if (evaluationDetail.getPraiseStatus() == 0) {
                    ivDz.setSelected(true);
                } else {
                    ivDz.setSelected(false);
                }
                //关注状态  "//0 未关注；1-已关注；2-不显示关注按钮"
                if (evaluationDetail.getFollowStatus() == 0) {
                    tvFollowStatus.setSelected(false);
                    tvFollowStatus.setText(getResources().getString(R.string.follow_status_0));
                } else if (evaluationDetail.getFollowStatus() == 1) {
                    tvFollowStatus.setSelected(true);
                    tvFollowStatus.setText(getResources().getString(R.string.follow_status_1));
                } else {
                    tvFollowStatus.setVisibility(View.GONE);
                }
                if (baseUserId == evaluationDetail.getCreateUserId()) {
                    tvFollowStatus.setVisibility(View.GONE);
                }
                tvCreateTime.setText(StringUtil.getTimeToM(evaluationDetail.getCreateTime()));

                wvPostShortDesc.loadData(StringUtil.getNewContent(evaluationDetail.getEvauationContent()), "text/html; charset=UTF-8", null);//这种写法可以正确解码
                wvPostShortDesc.setVisibility(View.VISIBLE);
                tvPostShortDesc.setVisibility(View.GONE);

                List<DetailsReviewBean.DataBean.EvaluationDetailBean.CommendationListBean> pileLists = evaluationDetail.getCommendationList();
                if (pileLists != null) {
                    initPraises(pileLists);
                }
//                if (StringUtil.isNotBlank(discussDetail.getTagInfos()) && discussDetail.getTagInfos().contains("tagName")) {
//                    try {
//                        JSONArray object = new JSONArray(discussDetail.getTagInfos());
//                        //[{"tagId":1,"tagName":"进度讨论"},{"tagId":3,"tagName":"项目前景讨论"},{"tagId":4,"tagName":"打假"}]
//                        String tagAll = "";
//                        String tagOnly[] = new String[object.length()];
//                        for (int i = 0; i < object.length(); i++) {
//                            JSONObject strObj = object.getJSONObject(i);
//                            tagOnly[i] = "#" + strObj.getString("tagName") + "#";
//                            tagAll += "#" + strObj.getString("tagName") + "#   ";
//                        }
//                        Clickable.getSpannableString(tagAll, tagOnly, tvTagName, new Clickable.ClickListener() {
//                            @Override
//                            public void setOnClick(String name) {
//                                //ToastUtils.getInstance().show(name);
//                            }
//                        });
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }

                if (StringUtil.isNotBlank(evaluationDetail.getEvaluationTags()) && evaluationDetail.getEvaluationTags().contains("tagName")) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(15, 10, 15, 10);
                    try {
                        JSONArray array = new JSONArray(evaluationDetail.getEvaluationTags());
                        if (llAddView.getChildCount() > 0) {
                            llAddView.removeAllViews();
                        }
                        for (int i = 0; i < array.length(); i++) {
                            final JSONObject object = array.getJSONObject(i);
                            if (StringUtil.isNotBlank(object.getString("tagName"))) {
                                final TextView crack_down = new TextView(DetailsReviewAllActivity.this);
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
                //热门评测
//                if (discussDetail.getHotComments() != null && discussDetail.getHotComments().size() > 0) {
//                    llRm.setVisibility(View.VISIBLE);
//                    tvRm.setText("热门评测(" + discussDetail.getHotComments().size() + ")");
//                    adapter.setData(discussDetail.getHotComments());
//                }
            }
        });

    }

    List<String> listPd = new ArrayList<>();
    /**
     * 设置评分进度
     *
     * @param eva
     * @throws JSONException
     */
    private void initProfessionalData(String eva) throws JSONException {
        if (StringUtil.isBlank(eva) || listPd.size()>0) {
            return;
        }
        JSONArray object = new JSONArray(eva);
        for (int i = 0; i < object.length(); i++) {
            listPd.add(object.getJSONObject(i).toString());
        }

        if (listPd.size() > 0) {
            rvReview.setVisibility(View.VISIBLE);
            adapterProgress.setData(listPd);
        }
    }

    /**
     * 设置捐款人头像
     *
     * @param pileLists
     */
    public void initPraises(List<DetailsReviewBean.DataBean.EvaluationDetailBean.CommendationListBean> pileLists) {
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
                node.put("sortField", "praise_num");//需要按点赞数倒序排序的话 增加传入参数ortField 值为字符串“praise_num”
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.EVLUATION_COMMENT_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, DiscussNewInfoBean.class, new HttpCallBackImpl<DiscussNewInfoBean>() {
            @Override
            public void onCompleted(DiscussNewInfoBean newInfoBean) {
                DiscussNewInfoBean.DataBean.CommentsBean commentsBean = newInfoBean.getData().getNewestComments();
                if (commentsBean != null) {
                    if (commentsBean.getRows() != null && commentsBean.getRows().size() > 0) {
                        llZx.setVisibility(View.VISIBLE);
                        rlNotContent.setVisibility(View.GONE);
                        tvZx.setText("评论 (" + commentsBean.getRowCount() + ")");
                        if (pageIndex > 2) {
                            adapterNew.addData(commentsBean.getRows());
                        } else {
                            adapterNew.setData(commentsBean.getRows());
                        }
                    } else {
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }
                    if (commentsBean.getCurPageNum() == commentsBean.getPageSize()) {
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }
                } else {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                if(rlNotContent.getVisibility() ==View.VISIBLE){
                    refreshLayout.setEnableLoadMore(false);
                }else{
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

    private int praiseNum;

    private void setPraise(boolean isPraise, int postId) {
        NetUtil.setAnimation(rlDz);
        NetUtil.setPraise(isPraise, postId, new NetUtil.SaveFollowImpl() {
            @Override
            public void finishFollow(String praiseNum, boolean status, double find, double postTotalIncome) {
                rlDz.setEnabled(true);
                ////点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
                if (!praiseNum.equals(Constants.PRAISE_ERROR)) {
                    DialogUtils.showDialogPraise(DetailsReviewAllActivity.this, 1, true, find);
                    ivDz.setSelected(status);
                    DetailsReviewAllActivity.this.praiseNum = Integer.valueOf(praiseNum);
                    tvDzNum.setVisibility(View.VISIBLE);
                    tvDzNum.setText(praiseNum);
                    if (postTotalIncome != 0) {
                        if (postTotalIncome == postTotalIncome) {
                            tvRead.setText((int) postTotalIncome + "");
                        } else {
                            tvRead.setText(postTotalIncome + "");
                        }
                    }
                }
            }
        });
    }

    String strLs = "find_apk";

    public void setIntput(String str, int commentsId) {
        if (commentsId == 0) {
            ToastUtils.getInstance().show("错误数据：parentCommentsId==0");
            return;
        }
        strLs = "@" + str + ":";
        etContent.setText(strLs);
        this.parentCommentsId = commentsId;
        StringUtil.showSoftInputFromWindow(this, etContent);
    }

    public void setCommentsIdPraise(DetailsDiscussAdapter mAdapter, int commentsId, int praiseNum, int praiseStatus) {
        List<CommonCommentsBean> commonCommentsBeans;
        if (adapter == mAdapter) {
            commonCommentsBeans = adapterNew.getData();
        } else {
            commonCommentsBeans = adapter.getData();
        }
        if (commonCommentsBeans != null && commonCommentsBeans.size() > 0) {
            for (int i = 0; i < commonCommentsBeans.size(); i++) {
                if (commentsId == commonCommentsBeans.get(i).getCommentsId()) {
                    commonCommentsBeans.get(i).setPraiseNum(praiseNum);
                    commonCommentsBeans.get(i).setPraiseStatus(praiseStatus);
                    if (adapter == mAdapter) {
                        adapterNew.notifyDataSetChanged();
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                    break;
                }
            }
        }
    }
}
