package com.secretk.move.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.HomeReviewBase;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.DetailsReviewAllActivity;
import com.secretk.move.ui.activity.HomeActivity;
import com.secretk.move.ui.activity.MoreCommentsActivity;
import com.secretk.move.ui.adapter.HomeRecommendAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:03
 * 邮箱；ltg263@126.com
 * 描述：我的主页--测评
 */

public class HomeReviewFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    private HomeRecommendAdapter adapter;
    int pageIndex = 1;//
    public Boolean isHaveData = true;//是否还有数据
    String userId;
    @Override
    public int setFragmentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {
        setVerticalManager(rvReview);
        adapter = new HomeRecommendAdapter();
        rvReview.setAdapter(adapter);
        adapter.setItemListener(this);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        userId = ((HomeActivity)context).getUserId();
    }

    @Override
    public void onFirstUserVisible() {
        getLoadData(null);

    }
    public void getLoadData(final RefreshLayout refreshlayout){
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            if(StringUtil.isNotBlank(userId)){
                node.put("userId", userId);
            }
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.USERHOME_EVALUATION_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, HomeReviewBase.class, new HttpCallBackImpl<HomeReviewBase>() {
            @Override
            public void onCompleted(HomeReviewBase bean) {
                if(pageIndex==3){//当前也等于总页
                    isHaveData=false;
                }
                HomeReviewBase.DataBean.EvaluationsBean evaluations = bean.getData().getEvaluations();
                List<HomeReviewBase> list = new ArrayList<>();
                HomeReviewBase base = new HomeReviewBase();
                base.setDiyi("张三");
                base.setEr("李四");
                base.setSan("周五");
                base.setIndex(0);
                list.add(base);
                list.add(base);
                list.add(base);
                adapter.setData(list);
            }

            @Override
            public void onFinish() {
                if(refreshlayout!=null){
                    refreshlayout.finishLoadmore();
                }
            }
        });
    }

    @Override
    public void onItemClick(View view, int postion) {
        IntentUtil.startActivity(DetailsReviewAllActivity.class);
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
