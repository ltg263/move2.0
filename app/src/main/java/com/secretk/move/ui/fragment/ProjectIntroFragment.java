package com.secretk.move.ui.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.HomeReviewBase;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.ProjectActivity;
import com.secretk.move.ui.adapter.ProjectIntroAdapter;
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
 * 描述：项目主页--简介
 */


public class ProjectIntroFragment extends LazyFragment  implements ItemClickListener {
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.rv_review1)
    RecyclerView rvReview1;

    private ProjectIntroAdapter adapter;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_project_intro;
    }

    @Override
    public void initViews() {
        setVerticalManager(rvReview);
        setVerticalManager(rvReview1);
        adapter = new ProjectIntroAdapter();
        rvReview.setAdapter(adapter);
        rvReview1.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onFirstUserVisible() {
        List<HomeReviewBase> list = new ArrayList<>();
        HomeReviewBase base = new HomeReviewBase();
        base.setDiyi("张三");
        base.setEr("李四");
        base.setSan("周五");
        base.setIndex(2);
        list.add(base);
        list.add(base);
        list.add(base);
        adapter.setData(list);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        String xx = ((ProjectActivity)context).getProjectIntro();
    }

    @Override
    public void onItemClick(View view, int postion) {
        if(view.getId()==R.id.tvFollws){

        }else{
            Toast.makeText(getActivity(), "进入下一个界面", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

}