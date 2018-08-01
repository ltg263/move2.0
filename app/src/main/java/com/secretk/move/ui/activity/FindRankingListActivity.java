package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.FindRankingUserBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.FindRankingListAdapter;
import com.secretk.move.ui.adapter.FindRankingUserAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.ViewPagerFixed;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/7/31 15:29
 * 邮箱；ltg263@126.com
 * 描述：发现排行榜
 */
public class FindRankingListActivity extends BaseActivity {
    @BindView(R.id.vp_main_children)
    ViewPagerFixed vp_main_children;
    FindRankingListAdapter adapter;
    FindRankingUserAdapter adapterUser;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    int pageIndex = 1;
    @BindView(R.id.tv_icon)
    ImageView tvIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_p)
    TextView tv_p;
    @BindView(R.id.rl_top_theme)
    RelativeLayout rlTopTheme;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        isLoginUi = true;
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle("排行榜");
        mHeadView.setTitleColor(R.color.white);
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_find_ranking_list;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.app_background), 0);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);
        setVerticalManager(recycler);
        adapter = new FindRankingListAdapter(getSupportFragmentManager());
        vp_main_children.setAdapter(adapter);
        adapterUser = new FindRankingUserAdapter(this);
        recycler.setAdapter(adapterUser);
        List<String> list = new ArrayList<>();
        list.add("项目评分榜");
        list.add("KLO榜单");
        list.add("最佳项目榜");
        adapter.setData(list);
        vp_main_children.setCurrentItem(1);
        StringUtil.getVpPosition(vp_main_children, new StringUtil.VpPageSelected() {
            @Override
            public void getVpPageSelected(int position) {
                pageIndex=1;
                if(position==0){
                    tv_p.setVisibility(View.VISIBLE);
                    getDataUrl(Constants.GET_EVA_PROJECT_PAGE, 0);
                }
                if(position==1){
                    tv_p.setVisibility(View.GONE);
                    getDataUrl(Constants.GET_KOL_PROJECT_PAGE, 1);
                }
                if(position==2){
                    tv_p.setVisibility(View.VISIBLE);
                    getDataUrl(Constants.GET_HOT_PROJECT_PAGE, 2);
                }
            }
        });
    }

    @Override
    protected void initData() {
        tv_p.setVisibility(View.GONE);
        getDataUrl(Constants.GET_KOL_PROJECT_PAGE,1);

    }


    public void getDataUrl(String url, final int index) {
        final String token = SharedUtils.singleton().get("token", "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", 20);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(url)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, FindRankingUserBean.class, new HttpCallBackImpl<FindRankingUserBean>() {
            @Override
            public void onCompleted(FindRankingUserBean bean) {

                FindRankingUserBean.DataBean.KFFUserPageBean detailsBean=null;
                if(index==0){
                    detailsBean = bean.getData().getEvaProjectPage();
                }
                if(index==1){
                    detailsBean = bean.getData().getKFFUserPage();
                }
                if(index==2){
                    detailsBean = bean.getData().getProjectHot();
                }
                if ((detailsBean == null || detailsBean.getRows() == null) && pageIndex == 2) {
//                    convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
//                    refreshLayout.setVisibility(View.GONE);
                    rlTopTheme.setVisibility(View.VISIBLE);
                    tvIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_gznr));
                    tvName.setVisibility(View.INVISIBLE);
                    tvSubmit.setText(getResources().getString(R.string.not_refresh));
                    return;
                }
                if (detailsBean.getCurPageNum() == detailsBean.getPageSize()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                refreshLayout.setVisibility(View.VISIBLE);
                findViewById(R.id.no_data).setVisibility(View.GONE);
                if (pageIndex > 2) {
                    adapterUser.setAddData(detailsBean.getRows());
                } else {
                    adapterUser.setData(detailsBean.getRows(),index);
                }
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore();
                }
            }
        });
    }
}
