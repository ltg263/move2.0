package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.InfoBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.MainFindWkAdapter;
import com.secretk.move.ui.fragment.FindKwFragment;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.CustomViewPager;
import com.secretk.move.view.MagicIndicatorUtils;
import com.secretk.move.view.ViewPagerFixed;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/7/2 18:34
 * 邮箱；ltg263@126.com
 * 描述：点评挖矿列表
 */

public class FindWkActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_head_state)
    ImageView ivHeadState;
    @BindView(R.id.viewpager)
    CustomViewPager viewpager;
    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    @BindView(R.id.vp_main_children)
    ViewPagerFixed vpMainChildren;
    private List<InfoBean.DataBeanX.DataBean.RowsBean> rows;
    private ArrayList<String> imageAdList;
    private MainFindWkAdapter adapter;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("点评挖矿");
        mMenuInfos.add(0, new MenuInfo(R.string.home_find_wk_3, getString(R.string.home_find_wk_3), 0));
        return mHeadView;
    }

    @Override
    protected void OnToolbarRightListener() {
        IntentUtil.startActivity(FindWksyActivity.class);
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_wk_find;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initRefresh();
        adapter = new MainFindWkAdapter(getSupportFragmentManager());
        vpMainChildren.setAdapter(adapter);
    }


    @Override
    protected void initData() {
        List<String> list = new ArrayList<>();
        list.add("进行中");
        list.add("已结束");
        MagicIndicatorUtils.initMagicIndicatorTitle(this, list, vpMainChildren, magicIndicatorTitle);
        adapter.setData(list);
        loadData();
    }


    private void initRefresh() {
        refreshLayout.setEnableLoadMore(false);
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadData();
                FindKwFragment fragment = adapter.getmCurrentFragment();
                fragment.setRefresh(refreshLayout);
            }
        });
        ivHeadState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStatePage();
            }
        });
    }

    private void startStatePage() {
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
                    if(data != null ){
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

    private void loadData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        //轮播图
//        getNewsFlashImgList();

    }

    private void getNewsFlashImgList() {
        JSONObject node = new JSONObject();
        try {
            node.put("pageIndex", 1);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_NEWS_FLASH_IMG_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, InfoBean.class, new HttpCallBackImpl<InfoBean>() {
            @Override
            public void onCompleted(InfoBean bean) {
                InfoBean.DataBeanX.DataBean data = bean.getData().getData();
                if (data == null) {
                    return;
                }
                rows = bean.getData().getData().getRows();
                if (rows != null && rows.size() > 0) {
                    imageAdList = new ArrayList<>();
                    for (int i = 0; i < rows.size(); i++) {
                        imageAdList.add(rows.get(i).getImgPath());
                    }
                    viewpager.setImageResources(imageAdList, mAdCycleViewListener);
                }
            }
        });
    }

    private CustomViewPager.ImageCycleViewListener mAdCycleViewListener = new CustomViewPager.ImageCycleViewListener() {
        @Override
        public void onImageClick(int position, View imageView) {
            //  ：0-完整版专业评测，1-自定义评测，2-文章，3-打假，4-单项评测
            if (rows == null || rows.size() == 0) {
                return;
            }
            InfoBean.DataBeanX.DataBean.RowsBean row = rows.get(viewpager.getCurPos(0));
            int type = row.getType();

            if (type == 5 && StringUtil.isNotBlank(row.getOutUrl())) {
                IntentUtil.startWebViewActivity(row.getOutUrl(), getString(R.string.app_name));
                return;
            }
            int postId = row.getArticleId();
            if (row.getIsCheckDetails() == 1 || row.getArticleId() == 0) {
                return;
            }
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

        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            // TODO 加载显示图片
            imageView.setTag(null);
            Glide.with(FindWkActivity.this).load(imageURL).into(imageView);
        }
    };
}
