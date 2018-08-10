package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.FindWkDetailsBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间： 2018/7/2 18:34
 * 邮箱；ltg263@126.com
 * 描述：主页话题
 */

public class FindWkDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_find_num)
    TextView tvFindNum;
    @BindView(R.id.tv_find_yd)
    TextView tvFindYd;
    @BindView(R.id.tv_jl_num)
    TextView tvJlNum;
    @BindView(R.id.tv_sy_num)
    TextView tvSyNum;
    @BindView(R.id.tv_go_follow)
    TextView tvGoFollow;
    @BindView(R.id.tv_activity_remark)
    TextView tvActivityRemark;
    @BindView(R.id.tv_go_comment)
    TextView tvGoComment;
    @BindView(R.id.tv_go_share)
    TextView tvGoShare;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_go_lqjl)
    TextView tvGoLqjl;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownCounters;
    private FindWkDetailsBean.DataBeanX.DataBean data;
    private int activityId;
    private String projectIcon;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("点评挖矿");
        mMenuInfos.add(0, new MenuInfo(R.string.home_find_wk_2, getString(R.string.home_find_wk_2), 0));
        return mHeadView;
    }

    @Override
    protected void OnToolbarRightListener() {
        JSONObject node = new JSONObject();
        try {
            node.put("pageIndex", 1);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_EXPLAIN_ACTIVITY)
//                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
//                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONArray data = new JSONObject(str).getJSONArray("data");
                    if(data != null){
                        JSONObject obj = data.getJSONObject(0);
                        int type = obj.getInt("type");
                        int postId = obj.getInt("articleId");
                        if (type == 0 || type == 1 || type == 4) {
                            type = 1;
                        } else if (type == 3) {
                            type = 2;
                        } else if (type == 2) {
                            type = 3;
                        } else {
                            ToastUtils.getInstance().show("类型出错");
                            return;
                        }
                        IntentUtil.go2DetailsByType(type, String.valueOf(postId));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_wk_details_find;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        this.countDownCounters = new SparseArray<>();
        activityId =  Integer.valueOf(getIntent().getStringExtra("id"));
        projectIcon =  getIntent().getStringExtra("projectIcon");
        initRefresh();
    }


    @Override
    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("id", activityId);
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_MINING_ACTIVITY_DETAIL)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, FindWkDetailsBean.class, new HttpCallBackImpl<FindWkDetailsBean>() {
            @Override
            public void onCompleted(FindWkDetailsBean bean) {
                data = bean.getData().getData();
                if (data == null) {
                    return;
                }
                GlideUtils.loadCircleProjectUrl(FindWkDetailsActivity.this, ivIcon, Constants.BASE_IMG_URL + StringUtil.getBeanString(projectIcon));
                tvTitle.setText(StringUtil.getBeanString(data.getTitle()));
                tvFindNum.setText(String.valueOf(data.getTokenCount()) + data.getTokenName());
                tvFindYd.setText("≈¥" + String.valueOf(data.getTokenCash()));
                tvJlNum.setText(String.valueOf(data.getTokenNum()) + "份");
                tvSyNum.setText(String.valueOf(data.getTokenSurplusNum() + "份"));
                tvActivityRemark.setText(StringUtil.getBeanString(data.getActivityRemark()));
                //"activityStep": "0,2,",//活动步骤：0-关注项目，1-点评项目，2-分享活动
                tv1.setVisibility(View.GONE);
                tvGoFollow.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tvGoComment.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tvGoShare.setVisibility(View.GONE);
                int index=0;
                if (data.getActivityStep().contains("0")) {
                    index+=1;
                    tv1.setText(index+".关注项目");
                    tv1.setVisibility(View.VISIBLE);
                    tvGoFollow.setVisibility(View.VISIBLE);
                }

                if (data.getActivityStep().contains("1")) {
                    index+=1;
                    tv2.setText(index+".点评项目（精评比短评挖矿算力更高）");
                    tv2.setVisibility(View.VISIBLE);
                    tvGoComment.setVisibility(View.VISIBLE);
                }

                if (data.getActivityStep().contains("2")) {
                    index+=1;
                    tv3.setText(index+".分享活动（分享后返回区分）");
                    tv3.setVisibility(View.VISIBLE);
                    tvGoShare.setVisibility(View.VISIBLE);
                }


                //  "followType": 0,//判断用户是否关注了该项目:0-未关注，1-已关注，
                if (data.getFollowType() == 0) {
                    tvGoFollow.setText("去关注项目");
                    tvGoFollow.setSelected(false);
                } else {
                    tvGoFollow.setSelected(true);
                    tvGoFollow.setText("已完成");
                }
                //0-未点评，1-已点评
                if (data.getCommentType() == 0) {
                    tvGoComment.setText("去点评项目");
                    tvGoComment.setSelected(false);
                } else {
                    tvGoComment.setText("已完成");
                    tvGoComment.setSelected(true);
                }
                //：0-未分享，1-已分享
                if (data.getShareType() == 1) {
                    tvGoShare.setSelected(true);
                    tvGoShare.setText("已完成");
                } else {
                    tvGoShare.setText("去分享");
                    tvGoShare.setSelected(false);
                }
                long surplusTime = 0;
                final Date date = new Date(StringUtil.getMsToTime(data.getBeginDt()));
                final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
                // status:0,//活动状态：0-未开始，1-进行中，2-已结束，3-已终止,4-已挖完
                if (data.getStatus() == 0) {
                    surplusTime = StringUtil.getSurplusTime(data.getBeginDt(), "0");
                    tvGoLqjl.setText("未开始");
                    tvGoLqjl.setBackgroundColor(getResources().getColor(R.color.title_gray_db));
                    tvTime.setText(Html.fromHtml("倒计时<font color=\"#ffe866\">" + StringUtil.getSytime(surplusTime) + "</font>  " + sdf.format(date).toString() + "开始"));
                } else if (data.getStatus() == 1) {
                    surplusTime = StringUtil.getSurplusTime("0", data.getEndDt());
                    tvTime.setText(Html.fromHtml("倒计时<font color=\"#ffe866\">" + StringUtil.getSytime(surplusTime) + "</font>  " + "进行中"));
                    if (data.getReceiveType() == 0) {
                        tvGoLqjl.setText("领取奖励");
                        tvGoLqjl.setBackgroundColor(getResources().getColor(R.color.title_gray_db));
                        boolean isAll = false;
                        if(data.getActivityStep().contains("0")){
                            isAll=false;
                            if(data.getFollowType() == 1){
                                isAll=true;
                            }
                        }
                        if(data.getActivityStep().contains("1")){
                            isAll=false;
                            if(data.getCommentType() == 1 && data.getFollowType() == 1){
                                isAll=true;
                            }
                        }
                        if(data.getActivityStep().contains("2")){
                            isAll=false;
                            if(data.getShareType() == 1 && data.getCommentType() == 1 && data.getFollowType() == 1){
                                isAll=true;
                            }
                        }
                        if(isAll){
                            tvGoLqjl.setBackgroundColor(getResources().getColor(R.color.app_background));
                        }
                    } else {
                        tvGoLqjl.setText("领取成功");
                        tvGoLqjl.setBackgroundColor(getResources().getColor(R.color.title_gray_db));
                    }
                } else if (data.getStatus() == 2) {
                    tvTime.setText("活动已结束");
                    tvGoLqjl.setText("活动已结束");
                    tvGoLqjl.setBackgroundColor(getResources().getColor(R.color.title_gray_db));
                } else if (data.getStatus() == 3) {
                    tvTime.setText("活动已终止");
                    tvGoLqjl.setText("活动已终止");
                    tvGoLqjl.setBackgroundColor(getResources().getColor(R.color.title_gray_db));
                } else if (data.getStatus() == 4) {
                    surplusTime = StringUtil.getSurplusTime("0", data.getEndDt());
                    tvTime.setText(Html.fromHtml("倒计时<font color=\"#ffe866\">" + StringUtil.getSytime(surplusTime) + "</font>  " + "进行中"));
                    tvGoLqjl.setText("已挖完");
                    tvGoLqjl.setBackgroundColor(getResources().getColor(R.color.title_gray_db));
                }
                CountDownTimer countDownTimer = null;
                if (data.getStatus() == 0 || data.getStatus() == 1 || data.getStatus() == 4) {
                    countDownTimer = countDownCounters.get(tvTime.hashCode());
                    if (countDownTimer != null) {
                        //将复用的倒计时清除
                        countDownTimer.cancel();
                    }
                    if (surplusTime > 0) {
                        countDownTimer = new CountDownTimer(surplusTime, 1000) {
                            public void onTick(long millisUntilFinished) {
                                if (data.getStatus() == 0) {
                                    tvTime.setText(Html.fromHtml("倒计时<font color=\"#ffe866\">" + StringUtil.getSytime(millisUntilFinished) + "</font>  " + sdf.format(date).toString() + "开始"));
                                } else if (data.getStatus() == 1) {
                                    tvTime.setText(Html.fromHtml("倒计时<font color=\"#ffe866\">" + StringUtil.getSytime(millisUntilFinished) + "</font>  " + "进行中"));
                                }
                            }

                            public void onFinish() {
                                // status:0,//活动状态：0-未开始，1-进行中，2-已结束，3-已终止,4-已挖完
                                if (data.getStatus() == 0 || data.getStatus() == 1 || data.getStatus() == 4) {
                                    initData();
                                }
                            }
                        }.start();
                        //将此 countDownTimer 放入list.
                        countDownCounters.put(tvTime.hashCode(), countDownTimer);
                    }
                }
            }
        });
    }


    private void initRefresh() {
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);

        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
            }
        });
    }


    @OnClick({R.id.tv_go_follow, R.id.tv_go_comment, R.id.tv_go_share, R.id.tv_go_lqjl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_follow:
//                if(tvGoFollow.isSelected()){
//                    return;
//                }
                IntentUtil.startProjectActivity(data.getProjectId());
                break;
            case R.id.tv_go_comment:
//                if(tvGoComment.isSelected()){
//                    return;
//                }
                IntentUtil.startProjectActivity(data.getProjectId());
                break;
            case R.id.tv_go_share:
//                if(tvGoShare.isSelected()){
//                    return;
//                }
                // "type": 2,//(当activity_step为2时才会有：)类型：0-完整版专业评测，1-自定义评测，2-文章，3-打假，4-单项评测
                int typeId = data.getType();
                if (typeId == 0 || typeId == 1 || typeId == 4) {
                    typeId = 1;
                } else if (typeId == 3) {
                    typeId = 2;
                } else if (typeId == 2) {
                    typeId = 3;
                } else {
                    return;
                }
                go2DetailsByType(typeId, String.valueOf(data.getArticleId()),String.valueOf(data.getId()));
                break;
            case R.id.tv_go_lqjl:
                getFindJl();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    public void go2DetailsByType(int type, String postId, String activityId) {
        Intent intent=null;
        switch (type) {
            case 1:
                intent=new Intent(this, DetailsReviewAllActivity.class);
                intent.putExtra("postId",postId);
                intent.putExtra("activityId",activityId);
                startActivity(intent);
                break;
            case 2://
                intent=new Intent(this, DetailsDiscussActivity.class);
                intent.putExtra("postId",postId);
                intent.putExtra("activityId",activityId);
                startActivity(intent);
                break;
            case 3:
                intent=new Intent(this, DetailsArticleActivity.class);
                intent.putExtra("postId",postId);
                intent.putExtra("activityId",activityId);
                startActivity(intent);
                break;
        }
    }

    public void getFindJl() {
        // status:0,//活动状态：0-未开始，1-进行中，2-已结束，3-已终止,4-已挖完
        if (data == null) {
            return;
        }
        if(tvGoLqjl.getText().toString().trim().equals("领取成功")){
            return;
        }
        if (data.getStatus() == 0) {
//            ToastUtils.getInstance().show("活动还未开始");
            return;
        }
        if (data.getStatus() == 2) {
//            ToastUtils.getInstance().show("活动已结束");
            return;
        }
        if (data.getStatus() == 3) {
//            ToastUtils.getInstance().show("活动已终止");
            return;
        }
        if (data.getStatus() == 4) {
//            ToastUtils.getInstance().show("已挖完");
            return;
        }
        if ((data.getActivityStep().contains("0") && data.getFollowType() != 1)
                || (data.getActivityStep().contains("1") && data.getCommentType() != 1)
                || (data.getActivityStep().contains("2") && data.getShareType() != 1)) {
            ToastUtils.getInstance().show("请先完成挖矿任务");
            return;

        }
        JSONObject node = new JSONObject();
        try {
            node.put("activityId", activityId);
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.ADD_REWARD_DETAIL)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {

                DialogUtils.showDialogGetFind(FindWkDetailsActivity.this,
                        "已成功领取" + data.getTokenEveryCount() + data.getTokenName(),
                        new DialogUtils.ErrorDialogInterface() {
                            @Override
                            public void btnConfirm() {
                                tvGoLqjl.setText("领取成功");
                                tvGoLqjl.setBackgroundColor(getResources().getColor(R.color.title_gray_db));
                            }
                        });
            }
        });
    }
}
