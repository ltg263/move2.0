package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.PopupWindowUtilsReward;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/8/31 16:34
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class ReleaseRewardOneActivity extends BaseActivity {
    @BindView(R.id.tv_reward_find)
    TextView tvRewardFind;
    @BindView(R.id.et_day)
    EditText etDay;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_reward_state)
    TextView tvRewardState;

    String data;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.white);
        isLoginUi = true;
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle("发布悬赏");
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_release_reward_one;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        tvRewardFind.setFocusable(true);
        tvRewardFind.setFocusableInTouchMode(true);
        tvRewardFind.requestFocus();
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.app_background), 0);
        MoveApplication.getContext().addActivity(this);
        tvDay.setText(Html.fromHtml(getResources().getString(R.string.sy_day)));
        StringUtil.etSearchChangedListener(etDay, null, new StringUtil.EtChange() {
            @Override
            public void etYes() {
                String currentHH = StringUtil.getTimeToHH(System.currentTimeMillis());
                LogUtil.w("currentHH：" + currentHH);
                if (Integer.valueOf(currentHH) < 12) {
                    data = StringUtil.plusDay2(Integer.valueOf(etDay.getText().toString())) + " 12:00";
                } else {
                    data = StringUtil.plusDay2(Integer.valueOf(etDay.getText().toString())) + " 00:00";
                }
                tvTime.setText("截止于 " + data);
            }
        });
        etDay.setText("7");
        tvRewardFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindowUtilsReward window = new PopupWindowUtilsReward(ReleaseRewardOneActivity.this, new PopupWindowUtilsReward.GiveDialogInterface() {
                    @Override
                    public void btnConfirm(String season) {
                        tvRewardFind.setText(season);
                    }
                });
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                window.showAtLocation(findViewById(R.id.head_app_server), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        tvEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtil.isBlank(tvRewardFind.getText().toString())) {
                    ToastUtils.getInstance().show("请输入悬赏金额");
                    return;
                }
                int dayNum = Integer.valueOf(etDay.getText().toString());
                if (0 == dayNum || dayNum > 15) {
                    ToastUtils.getInstance().show("有限期限最少1天，最多15天");
                    return;
                }
                Intent intent = new Intent(ReleaseRewardOneActivity.this, SelectProjectActivity.class);
                intent.putExtra("publication_type",4);
                Bundle bundle = new Bundle();
                bundle.putString("reward_find",tvRewardFind.getText().toString());
                bundle.putString("reward_day",etDay.getText().toString());
                bundle.putString("reward_time",data);
                intent.putExtra("reward",bundle);
                startActivity(intent);
            }
        });
        tvRewardState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startWebViewActivity(Constants.REGULATION,"悬赏规则");
            }
        });
    }

    @Override
    protected void initData() {

    }
}
