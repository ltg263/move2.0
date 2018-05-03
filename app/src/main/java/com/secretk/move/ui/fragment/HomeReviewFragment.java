package com.secretk.move.ui.fragment;

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
import com.secretk.move.ui.activity.MoreCommentsActivity;
import com.secretk.move.ui.adapter.HomeRecommendAdapter;
import com.secretk.move.utils.IntentUtil;
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
    public void onFirstUserVisible() {
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY, "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            //node.put("userId", token);
            node.put("pageIndex", pageIndex);
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
                HomeReviewBase.DataBean.EvaluationsBean evaluations = bean.getData().getEvaluations();
//                if(evaluations.getCurPageNum()==evaluations.getPageSize()){
//                    Toast.makeText(getActivity(), "已经没有了更多禁止上啦", Toast.LENGTH_SHORT).show()
//                }
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
        });
    }

    @Override
    public void onItemClick(View view, int postion) {
        IntentUtil.startActivity(MoreCommentsActivity.class);
        Toast.makeText(getActivity(), "评测揭秘那  我是第："+postion, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
