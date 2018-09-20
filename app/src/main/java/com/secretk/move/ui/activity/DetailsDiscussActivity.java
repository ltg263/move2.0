package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
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
import com.secretk.move.bean.DetailsDiscussBase;
import com.secretk.move.bean.DiscussNewInfoBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.ui.adapter.DetailsDiscussAdapter;
import com.secretk.move.ui.adapter.ImagesAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.GridSpacingItemDecoration;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.KeybordS;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;
import com.secretk.move.view.FixGridLayout;
import com.secretk.move.view.InputMethodLayout;
import com.secretk.move.view.PileLayout;
import com.secretk.move.view.PopupWindowUtils;
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
 * 描述：讨论详情.
 */
public class DetailsDiscussActivity extends BaseActivity {

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
    @BindView(R.id.tv_sort_new)
    TextView tvSortNew;
    @BindView(R.id.tv_sort_time)
    TextView tvSortTime;
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
    @BindView(R.id.rl_not_content)
    RelativeLayout rlNotContent;
    @BindView(R.id.tv_xs_find)
    TextView tvXsFind;
    @BindView(R.id.tv_xs_1)
    TextView tvXs1;
    @BindView(R.id.tv_xs_2)
    TextView tvXs2;
    @BindView(R.id.tv_xs_3)
    TextView tvXs3;
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
    private DetailsDiscussAdapter adapter;
    private DetailsDiscussAdapter adapterNew;
    private String postId;
    private int postIdToReward;
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
    private int postType=2;

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
        String url = Constants.DISCUSS_SHARE;
        if(postType==4){
            url=Constants.ANSWER;
        }
        ShareView.showShare(this, mHeadView, activityId, url + Integer.valueOf(postId),
                postShortDesc, postShortDesc, imgUrl, Integer.valueOf(postId));
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
//        refreshLayout.setEnableRefresh(false);//关闭下拉刷新
        postId = getIntent().getStringExtra("postId");
        activityId = getIntent().getStringExtra("activityId");
        setHorizontalManager(rvImg);
        rvImg.addItemDecoration(new GridSpacingItemDecoration());
        imagesadapter = new ImagesAdapter(this);
        rvImg.setAdapter(imagesadapter);

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
        tvProjectCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startProjectActivity(projectId);
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
            R.id.tv_sort_new, R.id.tv_sort_time,R.id.ll_xs,R.id.rl_xs_xsgc})
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
                                    DialogUtils.showDialogPraise(DetailsDiscussActivity.this, 2, status, 0);
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
            case R.id.ll_xs:
                    if (SharedUtils.getLoginZt()) {
                        IntentUtil.go2DetailsByType(10, String.valueOf(postIdToReward));
                    } else {
                        IntentUtil.startActivity(LoginHomeActivity.class);
                    }
                break;
            case R.id.rl_xs_xsgc:
                IntentUtil.startActivity(RewardSquareActivity.class);
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
                    DialogUtils.showDialogPraise(DetailsDiscussActivity.this, 6, isShare, amount);
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
                postIdToReward = discussDetail.getPostId();
                createUserId = discussDetail.getCreateUserId();
                mHeadView.setTitle(discussDetail.getProjectCode());
                postShortDesc = discussDetail.getDisscussContents();
                mHeadView.setTitleVice("/" + discussDetail.getProjectChineseName());
                if (discussDetail.getCommentsNum() != 0) {
                    tvPlNum.setVisibility(View.VISIBLE);
                    tvPlNum.setText(String.valueOf(discussDetail.getCommentsNum()));
                }
//共有8人赞助10000FIND
                if (discussDetail.getDonateNum() > 0) {
                    pileLayout.setVisibility(View.VISIBLE);
                    tvDonateNum.setText("共有" + discussDetail.getDonateNum() + "人赞助" + String.valueOf(new Double(discussDetail.getCommendationNum()).intValue()) + "FIND");
                }
                projectId = discussDetail.getProjectId();
                mHeadView.setToolbarListener(projectId);
                if (StringUtil.isNotBlank(discussDetail.getProjectCode())) {
                    tvProjectCode.setVisibility(View.VISIBLE);
                    tvProjectCode.setText(discussDetail.getProjectCode());
                }
                GlideUtils.loadCircleProjectUrl(DetailsDiscussActivity.this, mHeadView.getImageView(), Constants.BASE_IMG_URL + discussDetail.getProjectIcon());
                postType =discussDetail.getPostType();
                if (StringUtil.isNotBlank(discussDetail.getPostTitle()) && discussDetail.getPostType()!=4) {
                    tvPostTitle.setVisibility(View.VISIBLE);
                    tvPostTitle.setText(discussDetail.getPostTitle());
                }
                GlideUtils.loadCircleUserUrl(DetailsDiscussActivity.this, ivCreateUserIcon, Constants.BASE_IMG_URL + discussDetail.getCreateUserIcon());
                if (discussDetail.getUserType() != 1) {
//                    ivModelIcon.setVisibility(View.VISIBLE);
//                    StringUtil.getUserType(discussDetail.getUserType(),ivModelIcon);
//                    ivModelIconD.setVisibility(View.VISIBLE);
                    StringUtil.getUserType(discussDetail.getUserType(), ivModelIconD);
                }
                tvCreateUserName.setText(discussDetail.getCreateUserName());
                userId = discussDetail.getCreateUserId();
                tvCreateUserSignature.setText(discussDetail.getCreateUserSignature());
                //0-未收藏，1-已收藏，数字
                if (discussDetail.getCollectStatus() == 0) {
                    ivSc.setSelected(true);
                } else {
                    ivSc.setSelected(false);
                }
                if (discussDetail.getPraiseNum() != 0) {
                    tvDzNum.setVisibility(View.VISIBLE);
                    tvDzNum.setText(String.valueOf(discussDetail.getPraiseNum()));
                }
                if (discussDetail.getPostTotalIncome() == 0) {
                    tvRead.setText("待结算");
                } else {
                    if (discussDetail.getPostTotalIncome() == (int) discussDetail.getPostTotalIncome()) {
                        tvRead.setText((int) discussDetail.getPostTotalIncome() + "");
                    } else {
                        tvRead.setText(discussDetail.getPostTotalIncome() + "");
                    }
                }

                ///0-未点赞，1-已点赞，数字
                if (discussDetail.getPraiseStatus() == 0) {
                    ivDz.setSelected(true);
                } else {
                    ivDz.setSelected(false);
                }
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

                tvPostShortDesc.setText(Html.fromHtml(StringUtil.getBeanString(discussDetail.getDisscussContents())));
                if(discussDetail.getPostType()==4){
                    llBlXs.setVisibility(View.VISIBLE);
                    tvXsFind.setText(" 悬赏"+discussDetail.getRewardMoney()+"FIND】");
                    tvXs1.setVisibility(View.GONE);
                    if(StringUtil.isNotBlank(discussDetail.getPostTitle())){
                        tvXs1.setVisibility(View.VISIBLE);
                        tvXs1.setText(discussDetail.getPostTitle());
                    }
                    tvXs2.setText(discussDetail.getPostShortDesc());
                    tvXs3.setVisibility(View.VISIBLE);
                    if(discussDetail.getRewardMoneyToOne()>0){
                        tvPostShortDesc.setText(Html.fromHtml("<font color=\"#ff2851\">【奖励"+discussDetail.getRewardMoneyToOne()+"FIND】</font>"+discussDetail.getDisscussContents()));
                    }
                }

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
                                GlideUtils.loadSideMaxImage(DetailsDiscussActivity.this, ivPostSmallImages, Constants.BASE_IMG_URL + imgUrl);
                            } else {
                                rvImg.setVisibility(View.VISIBLE);
                                imagesadapter.setData(imageLists);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                List<DetailsDiscussBase.DataBean.CommendationListBean> pileLists = discussDetail.getCommendationList();
                if (pileLists != null) {
                    initPraises(pileLists);
                }
                if (StringUtil.isNotBlank(discussDetail.getTagInfos()) && discussDetail.getTagInfos().contains("tagName")) {
                    IntentUtil.setTagInfos(DetailsDiscussActivity.this,llAddView,discussDetail.getTagInfos());
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

    /**
     * 设置捐款人头像
     *
     * @param pileLists
     */
    public void initPraises(List<DetailsDiscussBase.DataBean.CommendationListBean> pileLists) {
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
                        rlNotContent.setVisibility(View.GONE);
                        llZx.setVisibility(View.VISIBLE);
                        tvZx.setText("评论 (" + commentsBean.getRowCount() + ")");
                        if (pageIndex > 2) {
                            adapterNew.addData(commentsBean.getRows());
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
                    DialogUtils.showDialogPraise(DetailsDiscussActivity.this, 1, true, find);
                    ivDz.setSelected(status);
                    DetailsDiscussActivity.this.praiseNum = Integer.valueOf(praiseNum);
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
