package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.MyFansList;
import com.secretk.move.ui.adapter.HomeFansAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 作者： litongge
 * 时间： 2018/6/21 17:54
 * 邮箱；ltg263@126.com
 * 描述：我的粉丝
 */
public class HomeFansActivity extends BaseActivity {

    @BindView(R.id.rv_collect)
    RecyclerView rvCollect;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_icon)
    ImageView tvIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.rl_top_theme)
    RelativeLayout rlTopTheme;
    private int pageIndex = 1;
    private HomeFansAdapter adapter;
    int userId = 0;
    int projectId = 0 ;
    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_collect;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        // 类型 0用户 1 项目
        String FansType = getIntent().getStringExtra("type");
        String userName = getIntent().getStringExtra("name");
        String id = getIntent().getStringExtra("id");
        mHeadView.setTitle(userName+"粉丝");
        if(FansType.equals("0")){
            userId = Integer.valueOf(id);
            if(Integer.valueOf(id)==baseUserId){
                mHeadView.setTitle("我的粉丝");
            }
        }else{
            projectId = Integer.valueOf(id);
        }
        setVerticalManager(rvCollect);
        adapter = new HomeFansAdapter(this);
        rvCollect.setAdapter(adapter);
        initRefresh();
        loadingDialog.show();
        rlTopTheme.setVisibility(View.VISIBLE);
        tvIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_data));
        tvName.setText("无粉丝");
        tvSubmit.setText(getResources().getString(R.string.not_go_look));
    }

    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                initData();
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                initData();
            }
        });
    }

    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("userId", userId);
            node.put("projectId", projectId);
            if (userId!=0){
                node.put("followType", 3);//关注类型：1-关注项目;2-关注帖子；3-关注用户',
            }else{
                node.put("followType", 1);
            }
            node.put("pageIndex", pageIndex++);//帖子ID
            node.put("pageSize", 20);//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_MY_FAN_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, MyFansList.class, new HttpCallBackImpl<MyFansList>() {
            @Override
            public void onCompleted(MyFansList bean) {
                MyFansList.DataBean.MyFansBean detailsBean = bean.getData().getMyFans();
                if(detailsBean==null){
                    findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    refreshLayout.setVisibility(View.GONE);
                    return;
                }
                //{"status":null,"msg":"Success","code":0,"reason":null,"fromuri":null,"token":null,"data":
                // {"myFans":null},"serverDatetime":1534730124390}
                if (detailsBean.getRows() == null || detailsBean.getRows().size() == 0) {
                    findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    refreshLayout.setVisibility(View.GONE);
                    return;
                }
                if (detailsBean.getCurPageNum() == detailsBean.getPageSize()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                if (pageIndex > 2) {
                    adapter.setAddData(detailsBean.getRows());
                } else {
                    adapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onFinish() {
//                if (refreshLayout.isRefreshing()) {
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
//                if (refreshLayout.isLoading()) {
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore(true);
                }
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        IntentUtil.startActivity(MainActivity.class);
        finish();
    }
}
