package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.ui.adapter.EvaluationCompileAdapter;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.ProgressBarStyleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/19 14:42
 * 邮箱；ltg263@126.com
 * 描述：专业评测-完整版
 */
public class EvaluationCompileListActivity extends BaseActivity {
    @BindView(R.id.pb_comprehensive_evaluation)
    ProgressBarStyleView pbComprehensiveEvaluation;
    @BindView(R.id.rv_evaluation_list)
    RecyclerView rvEvaluationList;
    private EvaluationCompileAdapter adapter;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle(getString(R.string.evaluation_professional));
        mMenuInfos.add(0, new MenuInfo(R.string.evaluation_next, getString(R.string.evaluation_next), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_evaluation_compile_list;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        setVerticalManager(rvEvaluationList);
        adapter = new EvaluationCompileAdapter(this);
        rvEvaluationList.setAdapter(adapter);

        pbComprehensiveEvaluation.setTvOne(getResources().getString(R.string.comprehensive_evaluation),0,
                getResources().getColor(R.color.title_gray));
        pbComprehensiveEvaluation.setTvThree(7.8,16,R.color.app_background);
        pbComprehensiveEvaluation.setPbProgressMaxVisible();

        List<BaseRes> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BaseRes res = new BaseRes();
            res.setMsg("项目类型：" + i);

            res.setCode(89-i);
            list.add(res);
        }
        adapter.setData(list);

    }

    @Override
    protected void initData() {

    }
}
