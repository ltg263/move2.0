package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;

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

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_money_guide;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        tvJrzbNum.setText("11111");
        tvZbQfs.setText("区分指数100");
        tvDlFind.setText("500FIND");
        tvTopFind.setText("今日+3000");
        tvFind.setText("每邀请一个好友赚1500FIND");

        tvPlIncome.setText("2222");
        tvPlTwo.setText("2FIND／次，首次5FIND");
        tvPlGo.setText("去完成");
        pbPlJd.setProgress(20);
        tvPlNum.setText("1/88");

        tvDzIncome.setText("2222");
        tvDzTwo.setText("2FIND／次，首次5FIND");
        tvDzGo.setText("去完成");
        pbDzJd.setProgress(20);
        tvDzNum.setText("1/88");

        tvFxIncome.setText("2222");
        tvFxTwo.setText("2FIND／次，首次5FIND");
        tvFxGo.setText("去完成");
        pbFxJd.setProgress(20);
        tvFxNum.setText("1/88");

        tvPcIncome.setText("2222");
        tvPcTwo.setText("2FIND／次，首次5FIND");
        tvPcGo.setText("去完成");
        pbPcJd.setProgress(20);
        tvPcNum.setText("1/88");

        tvYdIncome.setText("2222");
        tvYdTwo.setText("2FIND／次，首次5FIND");
        tvYdGo.setText("去完成");
        pbYdJd.setProgress(20);
        tvYdNum.setText("1/88");
    }

    @Override
    protected void initData() {

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
                ToastUtils.getInstance().show("问号");
                break;
            case R.id.tv_dl_find:
                ToastUtils.getInstance().show("登录奖励");
                break;
            case R.id.tv_yqhy_wh:
                ToastUtils.getInstance().show("邀请好友问号");
                break;
            case R.id.tv_ljyq:
                ToastUtils.getInstance().show("立即邀请");
                break;
            case R.id.tv_pl_go:
                ToastUtils.getInstance().show("评论");
                break;
            case R.id.tv_dz_go:
                ToastUtils.getInstance().show("点赞");
                break;
            case R.id.tv_fx_go:
                ToastUtils.getInstance().show("分享");
                break;
            case R.id.tv_pc_go:
                ToastUtils.getInstance().show("评测");
                break;
            case R.id.tv_yd_go:
                ToastUtils.getInstance().show("阅读");
                break;
        }
    }
}
