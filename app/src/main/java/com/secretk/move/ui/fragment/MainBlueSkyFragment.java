package com.secretk.move.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.BlueSkyBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.adapter.MainBlueSkyFragmentRecyclerAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 作者： litongge
 * 时间： 2018/6/8 13:46
 * 邮箱；ltg263@126.com
 * 描述：主页 --青天榜
 */
public class MainBlueSkyFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.rl_title)
    RelativeLayout rl_title;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private LinearLayoutManager layoutManager;
    private MainBlueSkyFragmentRecyclerAdapter adapter;
    protected LoadingDialog loadingDialog;
    private String tokenLs = "";
    boolean showFragment = false;//需要弹框

    @Override
    public int setFragmentView() {
        return R.layout.fragment_main_bluesky;
    }

    @Override
    public void initViews() {
        initRefresh();
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new MainBlueSkyFragmentRecyclerAdapter(getActivity());
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
        loadingDialog = new LoadingDialog(getActivity());
    }
    private void initRefresh() {
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                onFirstUserVisible();
            }
        });
    }

    @Override
    public void onFirstUserVisible() {
        if(!showFragment){
            loadingDialog.show();
        }
        showFragment=true;
        tokenLs = token;
        final String token = SharedUtils.singleton().get("token", "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.MAIN_BLUE_SKY)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, BlueSkyBean.class, new HttpCallBackImpl<BlueSkyBean>() {
            @Override
            public void onCompleted(BlueSkyBean bean) {
                List<BlueSkyBean.RankList> list = bean.getData().getRankList();
                rl_title.setVisibility(View.VISIBLE);
                adapter.setData(list);
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (showFragment && !tokenLs.equals(token)) {
            onFirstUserVisible();
        }
    }

    @Override
    public void onItemClick(View view, int postion) {
        if (isLoginZt) {
            BlueSkyBean.RankList bean = adapter.getDataIndex(postion);
            IntentUtil.startProjectActivity(bean.getProjectId());
        } else {
            IntentUtil.startActivity(LoginHomeActivity.class);
        }
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
