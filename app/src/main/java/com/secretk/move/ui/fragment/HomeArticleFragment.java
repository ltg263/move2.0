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
import com.secretk.move.bean.CommonListBase;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.DetailsArticleActivity;
import com.secretk.move.ui.activity.HomeActivity;
import com.secretk.move.ui.adapter.HomeListAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:04
 * 邮箱；ltg263@126.com
 * 描述：我的主页--文章
 */


public class HomeArticleFragment extends LazyFragment{
    @BindView(R.id.rv_review)
    RecyclerView rvReview;

    private HomeListAdapter adapter;
    public Boolean isHaveData = true;//是否还有数据
    public int pageIndex = 1;
    private String userId;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {
        setVerticalManager(rvReview);
        adapter = new HomeListAdapter();
        rvReview.setAdapter(adapter);
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
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.USERHOME_ARTICLE_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, CommonListBase.class, new HttpCallBackImpl<CommonListBase>() {
            @Override
            public void onCompleted(CommonListBase bean) {
                CommonListBase.DataBean.DetailsBean detailsBean = bean.getData().getArticles();
                if(detailsBean.getPageSize()==detailsBean.getCurPageNum()){
                    isHaveData=false;
                }
                adapter.setData(detailsBean.getRows());
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
    public void onAttach(Context context) {
        super.onAttach(context);
        userId = ((HomeActivity)context).getUserId();
    }

}
