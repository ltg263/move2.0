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
import com.secretk.move.ui.adapter.ProjectRecommendAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.view.ProgressBarStyleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:03
 * 邮箱；ltg263@126.com
 * 描述：项目主页--测评
 */

public class ProjectReviewFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.pb_comprehensive_evaluation)
    ProgressBarStyleView pbComprehensiveEvaluation;//综合评分
    @BindView(R.id.pb_project_location)
    ProgressBarStyleView pbProjectLocation;//项目定位
    @BindView(R.id.pb_technical_framework)
    ProgressBarStyleView pbTechnicalFramework;//技术框架
    @BindView(R.id.pb_team_strength)
    ProgressBarStyleView pbTeamStrength;//团队实力
    @BindView(R.id.pb_project_schedule)
    ProgressBarStyleView pbProjectSchedule;//项目进度
    @BindView(R.id.pb_speculative_risk)
    ProgressBarStyleView pbSpeculativeRisk;//投资风险
    Unbinder unbinder;

    private ProjectRecommendAdapter adapter;
    int pageIndex = 1;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_project_review;
    }

    @Override
    public void initViews() {
        //设置评分样式
        pbComprehensiveEvaluation.setTvOne(getResources().getString(R.string.comprehensive_evaluation),0,
                getResources().getColor(R.color.title_gray));
        pbComprehensiveEvaluation.setTvTwo("(5278人)",0,0);
        pbComprehensiveEvaluation.setTvThree(8.5,16,R.color.app_background);

        pbProjectLocation.setProgressDrawable(R.drawable.pb_view_xmdw,R.color.xmdw);
        pbProjectLocation.setAllTv(getResources().getString(R.string.comprehensive_evaluation),
                "/ 20% (2018人)",8.1);
        pbTechnicalFramework.setProgressDrawable(R.drawable.pb_view_jskj,R.color.jskj);
        pbTechnicalFramework.setAllTv(getResources().getString(R.string.comprehensive_evaluation),
                "/ 20% (2018人)",7.2);
        pbTeamStrength.setProgressDrawable(R.drawable.pb_view_tdsl,R.color.tdsl);
        pbTeamStrength.setAllTv(getResources().getString(R.string.comprehensive_evaluation),
                "/ 20% (2018人)",5.5);
        pbProjectSchedule.setProgressDrawable(R.drawable.pb_view_xmjd,R.color.xmjd);
        pbProjectSchedule.setAllTv(getResources().getString(R.string.comprehensive_evaluation),
                "/ 20% (2018人)",10);
        pbSpeculativeRisk.setProgressDrawable(R.drawable.pb_view_tzfx,R.color.tzfx);
        pbSpeculativeRisk.setAllTv(getResources().getString(R.string.comprehensive_evaluation),
                "/ 20% (2018人)",0);

        setVerticalManager(rvReview);
        adapter = new ProjectRecommendAdapter();
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
            node.put("projectId", "");//查看的项目ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.PROJECT_EVALUATION_LIST)
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
        Toast.makeText(getActivity(), "评测揭秘那  我是第：" + postion, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
