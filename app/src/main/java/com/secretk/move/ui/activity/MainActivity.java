package com.secretk.move.ui.activity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.MvpBaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.VersionBean;
import com.secretk.move.contract.ActivityMainContract;
import com.secretk.move.customview.TabViewpager;
import com.secretk.move.presenter.impl.MainPresenterImpl;
import com.secretk.move.ui.adapter.MainActivityPagerAdapter;
import com.secretk.move.ui.fragment.MainBlueFxFragment;
import com.secretk.move.ui.fragment.MainBlueGzFragment;
import com.secretk.move.utils.DownloadService;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.DialogUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;


public class MainActivity extends MvpBaseActivity<MainPresenterImpl> implements ActivityMainContract.View {
    @BindView(R.id.vp_main)
    TabViewpager vp_main;
    @BindView(R.id.rg_main_parent)
    RadioGroup rg_main_parent;
    MainActivityPagerAdapter adapter;
    ActivityMainContract.Presenter presenter;
    @BindView(R.id.rb_main)
    RadioButton rbMain;
    @BindView(R.id.rb_project)
    RadioButton rbProject;
    @BindView(R.id.rb_topic)
    RadioButton rbTopic;
    @BindView(R.id.rb_message)
    RadioButton rbMessage;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    private String postId;
    private String type;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenterImpl initPresenter() {
        return new MainPresenterImpl(this);
    }

    long shuangji = 0;
    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.main_background), 0);
        SharedUtils.singleton().put("isFollower", true);
        //友盟统计
        UMConfigure.setLogEnabled(true);
        UMConfigure.setEncryptEnabled(true);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_DUM_NORMAL);
        MobclickAgent.setSessionContinueMillis(1000);
        if(SharedUtils.getLoginZt()){
//            getLoadData();
//            getReportData();
        }
        //当用户使用自有账号登录时，可以这样统计：
        MobclickAgent.onProfileSignIn("1");
        adapter = new MainActivityPagerAdapter(getSupportFragmentManager());
        vp_main.setAdapter(adapter);
        vp_main.setOffscreenPageLimit(5);
        presenter = new MainPresenterImpl(this);
        presenter.initialized();
        postId = getIntent().getStringExtra("postId");
        type = getIntent().getStringExtra("type");
        vp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 4 || position==0) {
                    StatusBarUtil.setLightMode(MainActivity.this);
                    StatusBarUtil.setColor(MainActivity.this, UiUtils.getColor(R.color.white), 0);
                } else {
                    StatusBarUtil.setLightMode(MainActivity.this);
                    StatusBarUtil.setColor(MainActivity.this, UiUtils.getColor(R.color.main_background), 0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rbMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((System.currentTimeMillis() - shuangji) > 2000) {
                    shuangji = System.currentTimeMillis();
                } else {
                    shuangji=0;
                    FragmentManager fragmentManager = adapter.getItem(vp_main.getCurrentItem()).getChildFragmentManager();
                    if (fragmentManager==null) {
                        return;
                    }
                    List<Fragment> fragments = fragmentManager.getFragments();
                    for(Fragment fragment : fragments){
                        if(fragment instanceof MainBlueFxFragment) {
                            if (fragment.isAdded()) {
                                ((MainBlueFxFragment)fragment).dblclickRefresh();
                            }
                        }else if(fragment instanceof MainBlueGzFragment){
                            if (fragment.isAdded()) {
                                ((MainBlueGzFragment)fragment).dblclickRefresh();
                            }
                        }
                    }
                }
            }
        });

        rg_main_parent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_main:
                        MobclickAgent.onEvent(MainActivity.this,"home_id");
                        vp_main.setCurrentItem(0);
                        break;
                    case R.id.rb_project:
//                        MobclickAgent.onEvent(MainActivity.this,"project_id");
                        vp_main.setCurrentItem(1);
                        break;
                    case R.id.rb_topic:
                        MobclickAgent.onEvent(MainActivity.this,"find_id");
                        vp_main.setCurrentItem(2);
                        break;
                    case R.id.rb_message:
                        MobclickAgent.onEvent(MainActivity.this,"info_id");
                        vp_main.setCurrentItem(3);
                        break;
                    case R.id.rb_mine:
                        MobclickAgent.onEvent(MainActivity.this,"mine_id");
                        vp_main.setCurrentItem(4);
                        break;
                    default:
                        break;
                }
            }
        });
        onStartTz();
    }

    private void onStartTz() {
        if (StringUtil.isNotBlank(type) && StringUtil.isNotBlank(postId)) {
            if(!SharedUtils.getLoginZt() || StringUtil.isBlank(SharedUtils.getToken())){
                IntentUtil.startActivity(LoginActivity.class);
                return;
            }
            int typei = Integer.valueOf(type);
            if (typei == 0 || typei == 1 || typei == 4) {
                typei = 1;
            } else if (typei == 3) {
                typei = 2;
            } else if (typei == 2) {
                typei = 3;
            } else {
                return;
            }
            IntentUtil.go2DetailsByType(typei, postId);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(SharedUtils.getLoginZt()){
//            获1取CNY
            getLoadData();
//            获取举报列表
            getReportData();
//            奖励弹框
            String current = StringUtil.getTimeToM(System.currentTimeMillis());
            int userCardStatus = SharedUtils.singleton().get("userCardStatus", 0);
            if (SharedUtils.getLoginZt() && StringUtil.isNotBlank(SharedUtils.getToken()) && userCardStatus==2) {
                if (!current.equals(SharedUtils.singleton().get("isShowJlWind", ""))) {
                    showJlWind(SharedUtils.getToken());
                }
            }
//            实名认证弹框
            if (SharedUtils.getLoginZt() && StringUtil.isNotBlank(SharedUtils.getToken())) {
                if (!current.equals(SharedUtils.singleton().get("isShowSmWind", ""))
                        && (userCardStatus==4 || userCardStatus==3)) {
                    SharedUtils.singleton().put("isShowSmWind", StringUtil.getTimeToM(System.currentTimeMillis()));
                    showSmWind();
                }
            }
        }
    }

    private void showSmWind() {
        DialogUtils.showDialogAuthentication(this, new DialogUtils.ErrorDialogInterface() {
            @Override
            public void btnConfirm() {
                IntentUtil.startActivity(MineApproveSubmitiCertificateActivity.class);
            }
        });
    }

    /**
     * 每日奖励
     *
     * @param token
     */
    private void showJlWind(String token) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.TOKEN_POP)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                double tokenTodaySum = 0;
                int pop = 1;//1不弹  0弹出
                try {
                    JSONObject data = new JSONObject(str).getJSONObject("data");
                    if (data != null) {
                        tokenTodaySum = data.getDouble("tokenTodaySum");
                        pop = data.getInt("pop");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (pop == 1 || tokenTodaySum == 0) {
                    return;
                }
                SharedUtils.singleton().put("isShowJlWind", StringUtil.getTimeToM(System.currentTimeMillis()));
                DialogUtils.showDialogAwardFind(MainActivity.this, "今日领取"+tokenTodaySum+"FIND");
            }
        });
    }

    @Override
    public void showDialog(final VersionBean.DataBean str, final boolean force) {
        if(true){
            return;
        }
        DialogUtils.showDialogAppUpdate(this, force, str.getUpExplain(), new DialogUtils.ErrorDialogInterface() {
            @Override
            public void btnConfirm() {
//                presenter.downLoadApk();
                Intent service = new Intent(MainActivity.this, DownloadService.class);
                if(force){
                    service.putExtra("Url",str.getGuideUrl());
                }else{
                    service.putExtra("Url",str.getUpgradeUrl());
                }
                startService(service);
            }
        });
    }

    private long exitTime = 0;
    private int index = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (index != vp_main.getCurrentItem()) {
                rbMain.setChecked(true);
            } else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    ToastUtils.getInstance().show("再按一次退出" + getString(R.string.app_name));
                    exitTime = System.currentTimeMillis();
                } else {
                    MobclickAgent.onKillProcess(this);
                    finish();
                    System.exit(0);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 获1取CNY
     */
    public void getLoadData() {
        RxHttpParams params = new RxHttpParams.Build()
                .url("https://data.block.cc/api/v1/exchange_rate")
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject object = new JSONObject(str);
                    if(object.getInt("code")==0){
                        JSONObject rates = object.getJSONObject("data").getJSONObject("rates");
                        if(rates!=null){
                            String CNY = String.valueOf(rates.getDouble("CNY"));
                            SharedUtils.singleton().put("EXCHANGE_RATE_CNY",CNY);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 获取举报的List
     */
    public void getReportData() {
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_REPORT_LIST)
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject object = new JSONObject(str);
                    if(object.getInt("code")==0){
                        JSONArray rates = object.getJSONObject("data").getJSONArray("getReportModelList");
                        if(rates!=null){
                            SharedUtils.singleton().put("getReportModelList",rates.toString());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
