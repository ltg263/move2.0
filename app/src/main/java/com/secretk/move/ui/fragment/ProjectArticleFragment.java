package com.secretk.move.ui.fragment;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
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
import com.secretk.move.ui.activity.ProjectActivity;
import com.secretk.move.ui.adapter.HomeRecommendAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:04
 * 邮箱；ltg263@126.com
 * 描述：项目主页--文章
 */


public class ProjectArticleFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_sort)
    TextView tvSort;

    private HomeRecommendAdapter adapter;
    public boolean isHaveData = true;
    private int pageIndex = 1;
    private String projectId;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_project_article;
    }

    @Override
    public void initViews() {
        setVerticalManager(rvReview);
        adapter = new HomeRecommendAdapter();
        rvReview.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onFirstUserVisible() {
        getLoadData(null);
    }


    @Override
    public void onItemClick(View view, int postion) {
        Toast.makeText(getActivity(), "文章界面：我是第：" + postion, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        projectId = ((ProjectActivity)context).getProjectId();
    }

    public void getLoadData(final RefreshLayout refreshlayout) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", projectId);
            node.put("pageIndex", pageIndex++);
            //node.put("sortField", pageIndex++);//按点赞数排序，需要传值 "praiseNum"
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.PROJECT_ARTICLE_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, HomeReviewBase.class, new HttpCallBackImpl<HomeReviewBase>() {
            @Override
            public void onCompleted(HomeReviewBase bean) {
                if(pageIndex==4){
                    isHaveData=false;
                }
                List<HomeReviewBase> list = new ArrayList<>();
                HomeReviewBase base = new HomeReviewBase();
                base.setDiyi("张三");
                base.setEr("李四");
                base.setSan("周五");
                base.setIndex(2);
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
}
