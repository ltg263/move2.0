package com.secretk.move.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.customview.QuickIndexBar;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.activity.SearchActivity;
import com.secretk.move.ui.activity.SubmitProjectActivity;
import com.secretk.move.ui.adapter.FindFragmentRecyclerAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PatternUtils;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间： 2018/6/7 16:03
 * 邮箱；ltg263@126.com
 * 描述：主页话题
 */
public class FindFragment extends LazyFragment implements ItemClickListener, QuickIndexBar.OnLetterChangeListener {

    @BindView(R.id.qbar)
    QuickIndexBar qbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
//    @BindView(R.id.refreshLayout)
//    SmartRefreshLayout refreshLayout;

    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.tv_sort_name)
    TextView tv_sort_name;
    @BindView(R.id.tv_sort_follow)
    TextView tv_sort_follow;
    @BindView(R.id.fab)
    FloatingActionButton fab;
//    @BindView(R.id.tv_icon)
//    ImageView tvIcon;
//    @BindView(R.id.tv_name)
//    TextView tvName;
//    @BindView(R.id.tv_submit)
//    TextView tvSubmit;
//    @BindView(R.id.rl_top_theme)
//    RelativeLayout rlTopTheme;
//    @BindView(R.id.ll_have_data)
//    LinearLayout llHaveData;
    private FindFragmentRecyclerAdapter adapter;
    private LoadingDialog loadingDialog;
    String lsToken="";
    boolean showFragment = false;
    private int currentType = Constants.TOPIC_SORT_BY_NUM;
    @Override
    public int setFragmentView() {
        return R.layout.fragment_find;
    }

    @Override
    public void initViews() {
//        initRefresh();
        setVerticalManager(recycler);
        adapter = new FindFragmentRecyclerAdapter(getContext());
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
        qbar.setOnLetterChangeListener(this);
        loadingDialog = new LoadingDialog(getActivity());
//        rlTopTheme.setVisibility(View.VISIBLE);
//        tvName.setText(getActivity().getResources().getString(R.string.not_currency));
//        tvSubmit.setText(getActivity().getResources().getString(R.string.not_refresh));
    }
//    private void initRefresh() {
////        loadingDialog = new LoadingDialog(getActivity());
//        refreshLayout.setEnableLoadmore(false);
//        refreshLayout.setEnableRefresh(false);
//        /**
//         * 下拉刷新
//         */
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                http(currentType);
//            }
//        });
//    }
    //1-按关注数量倒序；2-按名称排序
    @Override
    public void onFirstUserVisible() {
        if (true) {
//        if (isLoginZt){
            http(Constants.TOPIC_SORT_BY_NUM);
        } else {
            ToastUtils.getInstance().show("账号未登录,请先登录账号");
        }
    }

    @Override
    public void onItemClick(View view, int postion) {
        if(isLoginZt){
            int id = adapter.getData().get(postion).getProjectId();
            IntentUtil.startProjectActivity(id);
        }else{
            IntentUtil.startActivity(LoginHomeActivity.class);
        }
    }

    @Override
    public void onItemLongClick(View view, int postion) {
//        ToastUtils.getInstance().show("onItemLongClick postion=" + postion);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!lsToken.equals(token) && showFragment){
            lsToken = token;
            http(currentType);
        }
    }

    @Override
    public void onLetterChange(String letter) {
        List<SearchedBean.Projects> list = adapter.getData();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).getProjectCode().charAt(0) + "";
            if (letter.equals(str.trim().toUpperCase())) {
                recycler.scrollToPosition(i);
                LinearLayoutManager manager = (LinearLayoutManager) recycler.getLayoutManager();
                manager.scrollToPositionWithOffset(i, 0);
                break;
            } else if (letter.equals("#") && PatternUtils.isLetter(str) == false) {
                recycler.scrollToPosition(0);
            }
        }
    }


    public void http(final int type) {
        if(!showFragment){
            loadingDialog.show();
        }
        showFragment=true;
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectCode", "");
            node.put("sortType", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SEARCH_PROJECTS)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, SearchedBean.class, new HttpCallBackImpl<SearchedBean>() {
            @Override
            public void onCompleted(SearchedBean bean) {
                SharedUtils.singleton().put("isFollowerFx",false);
                if (bean.getCode() == 0) {
                    List<SearchedBean.Projects> list = bean.getData().getProjects();
                    tv_count.setText("共" + list.size() + "个币种");
//                    if (list.size() > 0) {
//                        convertView.findViewById(R.id.no_data).setVisibility(View.GONE);
//                        llHaveData.setVisibility(View.VISIBLE);
//                    } else {
//                        llHaveData.setVisibility(View.GONE);
//                        convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
//                    }
                    adapter.setData(list, type);
                    if (type == Constants.TOPIC_SORT_BY_NAME) {
                        qbar.setDatax(list);
                    }
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
//                if (refreshLayout.isRefreshing()) {
//                    refreshLayout.finishRefresh();
//                }
                loadingDialog.dismiss();
            }
        });
    }

    @OnClick({R.id.fab,R.id.toolbar,R.id.tv_sort_follow,R.id.tv_sort_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if(isLoginZt){
                    IntentUtil.startActivity(SubmitProjectActivity.class);
                    return;
                }
                IntentUtil.startActivity(LoginHomeActivity.class);
                break;
            case R.id.toolbar:
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_sort_follow:
                if(currentType==Constants.TOPIC_SORT_BY_NAME){
                    sortFollow();
                }
                break;
            case R.id.tv_sort_name:
                if(currentType==Constants.TOPIC_SORT_BY_NUM){
                    sortName();
                }
                break;
        }
    }

    private void sortName() {
        currentType=Constants.TOPIC_SORT_BY_NAME;
        fab.setVisibility(View.VISIBLE);
        qbar.setVisibility(View.VISIBLE);
//        tv_count.setText("共" + 0 + "个币种");
        tv_sort_name.setTextColor(Color.parseColor("#3b88f6"));
        tv_sort_follow.setTextColor(Color.parseColor("#dddddd"));
        List<SearchedBean.Projects> list = adapter.getDataByType(currentType);
        boolean isFollower = SharedUtils.singleton().get("isFollower",true);
        if (list == null || list.size() == 0 || true) {
            http(currentType);
            return;
        }
        adapter.swithData(currentType);
        int count = adapter.getDataByType(currentType).size();
        tv_count.setText("共" + count + "个币种");
    }

    private void sortFollow() {
        currentType=Constants.TOPIC_SORT_BY_NUM;
        fab.setVisibility(View.VISIBLE);
        qbar.setVisibility(View.GONE);
//        tv_count.setText("共" + 0 + "个币种");
        tv_sort_name.setTextColor(Color.parseColor("#dddddd"));
        tv_sort_follow.setTextColor(Color.parseColor("#3b88f6"));
        List<SearchedBean.Projects> list = adapter.getDataByType(currentType);
        boolean isFollower = SharedUtils.singleton().get("isFollower",true);
        if (list == null || list.size() == 0 || true) {
            http(currentType);
            return;
        }
        adapter.swithData(currentType);
        int count = adapter.getDataByType(currentType).size();
        tv_count.setText("共" + count + "个币种");
    }

    @Override
    public void onUserVisible() {
        boolean isFollower = SharedUtils.singleton().get("isFollowerFx",false);
        if (isFollower){
            if(currentType==Constants.TOPIC_SORT_BY_NAME){
                sortName();
            }else{
                sortFollow();
            }
        }
        super.onUserVisible();
    }
}
