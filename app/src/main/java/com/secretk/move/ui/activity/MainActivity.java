package com.secretk.move.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
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
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.DialogUtils;

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
    @BindView(R.id.rb_topic)
    RadioButton rbTopic;
    @BindView(R.id.rb_message)
    RadioButton rbMessage;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;

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
        adapter = new MainActivityPagerAdapter(getSupportFragmentManager());
        vp_main.setAdapter(adapter);
        vp_main.setOffscreenPageLimit(4);
        presenter = new MainPresenterImpl(this);
        presenter.initialized();
        vp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    StatusBarUtil.setDarkMode(MainActivity.this);
                    StatusBarUtil.setColor(MainActivity.this, UiUtils.getColor(R.color.app_background), 0);
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
                        vp_main.setCurrentItem(0);
                        break;
                    case R.id.rb_topic:
                        vp_main.setCurrentItem(1);
                        break;
                    case R.id.rb_message:
                        vp_main.setCurrentItem(2);
                        break;
                    case R.id.rb_mine:
                        vp_main.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!SharedUtils.singleton().get("isShowJlWind", false)) {
            if (SharedUtils.getLoginZt() && StringUtil.isNotBlank(SharedUtils.getToken())) {
                showJlWind(SharedUtils.getToken());
            }
        }
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
                SharedUtils.singleton().put("isShowJlWind", true);
                DialogUtils.showDialogHint(MainActivity.this, "今日领取 " + tokenTodaySum + " FIND", true, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                    }
                });
            }
        });
    }

    @Override
    public void showDialog(final VersionBean.DataBean str, final boolean force) {
//        DialogUtils.showDialogAppUpdate(this, force, str.getUpExplain(), new DialogUtils.ErrorDialogInterface() {
//            @Override
//            public void btnConfirm() {
////                presenter.downLoadApk();
//                Intent service = new Intent(MainActivity.this, DownloadService.class);
//                if(force){
//                    service.putExtra("Url",str.getGuideUrl());
//                }else{
//                    service.putExtra("Url",str.getUpgradeUrl());
//                }
//                startService(service);
//            }
//        });
    }

//    private long exitTime = 0;
//    private int index = 0;
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if (index != vp_main.getCurrentItem()) {
//                rbMain.setChecked(true);
//            } else {
//                if ((System.currentTimeMillis() - exitTime) > 2000) {
//                    Toast.makeText(this, "再按一次退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
//                    exitTime = System.currentTimeMillis();
//                } else {
//                    finish();
//                }
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
