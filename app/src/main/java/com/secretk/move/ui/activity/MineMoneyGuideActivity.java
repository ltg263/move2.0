package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.MoneyGuide;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.ShareView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/9/6 14:47
 * 邮箱；ltg263@126.com
 * 描述：赚币指南
 */
public class MineMoneyGuideActivity extends BaseActivity {
    @BindView(R.id.tv_jrzb_num)
    TextView tvJrzbNum;
    @BindView(R.id.tv_zb_qfs)
    TextView tvZbQfs;
    @BindView(R.id.tv_dl_find)
    TextView tvDlFind;
    @BindView(R.id.tv_top_find)
    TextView tvTopFind;
    @BindView(R.id.tv_find)
    TextView tvFind;
    @BindView(R.id.tv_pl_go)
    TextView tvPlGo;
    @BindView(R.id.tv_pl_income)
    TextView tvPlIncome;
    @BindView(R.id.tv_pl_two)
    TextView tvPlTwo;
    @BindView(R.id.pb_pl_jd)
    ProgressBar pbPlJd;
    @BindView(R.id.tv_pl_num)
    TextView tvPlNum;
    @BindView(R.id.tv_dz_go)
    TextView tvDzGo;
    @BindView(R.id.tv_dz_income)
    TextView tvDzIncome;
    @BindView(R.id.tv_dz_two)
    TextView tvDzTwo;
    @BindView(R.id.pb_dz_jd)
    ProgressBar pbDzJd;
    @BindView(R.id.tv_dz_num)
    TextView tvDzNum;
    @BindView(R.id.tv_fx_go)
    TextView tvFxGo;
    @BindView(R.id.tv_fx_income)
    TextView tvFxIncome;
    @BindView(R.id.tv_fx_two)
    TextView tvFxTwo;
    @BindView(R.id.pb_fx_jd)
    ProgressBar pbFxJd;
    @BindView(R.id.tv_fx_num)
    TextView tvFxNum;
    @BindView(R.id.tv_pc_go)
    TextView tvPcGo;
    @BindView(R.id.tv_pc_income)
    TextView tvPcIncome;
    @BindView(R.id.tv_pc_two)
    TextView tvPcTwo;
    @BindView(R.id.pb_pc_jd)
    ProgressBar pbPcJd;
    @BindView(R.id.tv_pc_num)
    TextView tvPcNum;
    @BindView(R.id.tv_yd_go)
    TextView tvYdGo;
    @BindView(R.id.tv_yd_income)
    TextView tvYdIncome;
    @BindView(R.id.tv_yd_two)
    TextView tvYdTwo;
    @BindView(R.id.pb_yd_jd)
    ProgressBar pbYdJd;
    @BindView(R.id.tv_yd_num)
    TextView tvYdNum;
    private int hierarchyType;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_money_guide;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_MEMBER)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, MoneyGuide.class, new HttpCallBackImpl<MoneyGuide>() {
            @Override
            public void onCompleted(MoneyGuide bean) {
                MoneyGuide.DataBean.ResultBean resultBean = bean.getData().getResult();
                initUiData(resultBean);
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
    }

    //(所有领取状态1 表示已领取 0 表示去完成)
    private void initUiData(MoneyGuide.DataBean.ResultBean resultBean) {
        hierarchyType = resultBean.getStatusHierarchyType();
        tvJrzbNum.setText(resultBean.getTodayAward()+"");
        tvZbQfs.setText("区分指数"+resultBean.getStatusHierarchyType());
        tvDlFind.setText(resultBean.getLoginAward()+"FIND");
        tvTopFind.setText("今日+"+resultBean.getInvaAward());
        tvFind.setText("每邀请一个好友赚"+resultBean.getInvaEachAward()+"FIND");

        tvPlIncome.setText(""+resultBean.getCommentAwardSum());
        tvPlTwo.setText(resultBean.getCommentAward()+"FIND／次，首次"+resultBean.getCommentFirstAward()+"FIND");
        pbPlJd.setMax(resultBean.getCommentSumDegr());
        pbPlJd.setProgress(resultBean.getCommentDegr());
        tvPlNum.setText(resultBean.getCommentDegr()+"/"+resultBean.getCommentSumDegr());
        if(resultBean.getCommentReceStatus()==1){
            tvPlGo.setText("已领取");
            tvPlGo.setSelected(true);
        }else{
            tvPlGo.setText("去完成");
            tvPlGo.setSelected(false);
        }

        tvDzIncome.setText(resultBean.getPraiseAwardSum()+"");
        tvDzTwo.setText(resultBean.getPraiseAward()+"FIND／次");
        pbDzJd.setMax(resultBean.getPraiseSumDegr());
        pbDzJd.setProgress(resultBean.getPraiseDegr());
        tvDzNum.setText(resultBean.getPraiseDegr()+"/"+resultBean.getPraiseSumDegr());
        if(resultBean.getPraiseReceStatus()==1){
            tvDzGo.setText("已领取");
            tvDzGo.setSelected(true);
        }else{
            tvDzGo.setText("去完成");
            tvDzGo.setSelected(false);
        }

        tvFxIncome.setText(resultBean.getSharePostAwardSum()+"");
        tvFxTwo.setText(resultBean.getSharePostAward()+"FIND／次");
        pbFxJd.setMax(resultBean.getSharePostSumDegr());
        pbFxJd.setProgress(resultBean.getSharePostDegr());
        tvFxNum.setText(resultBean.getSharePostDegr()+"/"+resultBean.getSharePostSumDegr());
        if(resultBean.getSharePostReceStatus()==1){
            tvFxGo.setText("已领取");
            tvFxGo.setSelected(true);
        }else{
            tvFxGo.setText("去完成");
            tvFxGo.setSelected(false);
        }

        tvPcIncome.setText(resultBean.getEvaAwardSum()+"");
        tvPcTwo.setText("专业评测"+resultBean.getEvaAward()+"FIND／篇");
        pbPcJd.setMax(resultBean.getEvaAwardSumDegr());
        pbPcJd.setProgress(resultBean.getEvaDegr());
        tvPcNum.setText(resultBean.getEvaDegr()+"/"+resultBean.getEvaAwardSumDegr());
        if(resultBean.getEvaReceStatus()==1){
            tvPcGo.setText("已领取");
            tvPcGo.setSelected(true);
        }else{
            tvPcGo.setText("去完成");
            tvPcGo.setSelected(false);
        }

        tvYdIncome.setText(resultBean.getReadingAwardSum()+"");
        tvYdTwo.setText("阅读"+resultBean.getReadingSumDegr()+"篇评测(已完成"+resultBean.getReadingDegr()+"次)");
        if(resultBean.getReadingReceStatus()==1){
            tvYdGo.setText("已领取");
            tvYdGo.setSelected(true);
        }else{
            tvYdGo.setText("去完成");
            tvYdGo.setSelected(false);
        }

//        pbYdJd.setProgress(20);
//        tvYdNum.setText("1/88");
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("赚币指南");
        return mHeadView;
    }

    @OnClick({R.id.iv_wh, R.id.tv_dl_find, R.id.tv_yqhy_wh, R.id.tv_ljyq, R.id.tv_pl_go, R.id.tv_dz_go, R.id.tv_fx_go, R.id.tv_pc_go, R.id.tv_yd_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_wh:
//                ToastUtils.getInstance().show("问号");
                break;
            case R.id.tv_dl_find:
                IntentUtil.startActivity(MineAssetDistributedActivity.class);
//                ToastUtils.getInstance().show("登录奖励");
                break;
            case R.id.tv_yqhy_wh:
//                ToastUtils.getInstance().show("邀请好友问号");
                break;
            case R.id.tv_ljyq:
                if(hierarchyType==0){
                    IntentUtil.startActivity(MineApproveSubmitiCertificateActivity.class);
                    return;
                }
                ShareView.showShare(this, mHeadView, "", Constants.INVITE_FRIENDS + SharedUtils.singleton().get("invaUIH",""),
                        "免费领取价值500RMB的数字货币", "点击领取红包",Constants.SHARE_HONGBAO_IOCN, 0);
                break;
            case R.id.tv_pl_go:
                IntentUtil.go2DetailsByType(5,"");
//                ToastUtils.getInstance().show("评论");
                break;
            case R.id.tv_dz_go:
                IntentUtil.go2DetailsByType(5,"");
//                ToastUtils.getInstance().show("点赞");
                break;
            case R.id.tv_fx_go:
                IntentUtil.go2DetailsByType(5,"");
//                ToastUtils.getInstance().show("分享");
                break;
            case R.id.tv_pc_go:
                IntentUtil.go2DetailsByType(5,"");
//                ToastUtils.getInstance().show("评测");
                break;
            case R.id.tv_yd_go:
                IntentUtil.go2DetailsByType(5,"");
//                ToastUtils.getInstance().show("阅读");
                break;
        }
    }
}
