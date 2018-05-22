package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.EvaluationNewBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.EvaluationNewAdapter;
import com.secretk.move.view.AddDimensionalityPopupWindow;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.ProgressBarStyleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/5/19 17:15
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class EvaluationNewActivity extends BaseActivity {

    @BindView(R.id.rv_msg_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.pbs_comprehensive)
    ProgressBarStyleView pbsComprehensive;
    @BindView(R.id.tv_evaluation_object)
    TextView tvEvaluationObject;
    private EvaluationNewAdapter adapter;


    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle(getString(R.string.evaluation_btn_new));
        mMenuInfos.add(0, new MenuInfo(R.string.evaluation_next, getString(R.string.evaluation_next), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_evaluation_new;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        pbsComprehensive.setPbProgressMaxVisible();
        pbsComprehensive.setTvOne(getResources().getString(R.string.comprehensive_evaluation),0,
                getResources().getColor(R.color.title_gray));
        pbsComprehensive.setTvThree(0,16,R.color.app_background);

        setVerticalManager(mRecyclerView);
        adapter = new EvaluationNewAdapter(this);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    List<EvaluationNewBean> list = new ArrayList<>();

    @OnClick(R.id.tv_add_dimensionality)
    public void onViewClicked() {
        showPopupWindow(-1);

    }

    public void setCompileOnClick(int position) {
        showPopupWindow(position);
    }

    public void setDeleteOnClick(int position) {
        list.remove(position);
        adapter.notifyDataSetChanged();
        double zGrade=getComprehensiveGrade();
        pbsComprehensive.setTvThree(zGrade,16,R.color.app_background);
    }

    private void showPopupWindow(final int position) {
        AddDimensionalityPopupWindow window = new AddDimensionalityPopupWindow(this, new AddDimensionalityPopupWindow.PopupOnClickListener() {
            @Override
            public void popupOnClick(View view, String name, float weight, float grade) {
                if (position != -1) {
                    EvaluationNewBean newBean = list.get(position);
                    newBean.setName(name);
                    newBean.setGrade(grade);
                    newBean.setWeight(weight);
                    adapter.notifyDataSetChanged();
                }else {
                    EvaluationNewBean bean = new EvaluationNewBean();
                    bean.setGrade(grade);
                    bean.setName(name);
                    bean.setWeight(weight);
                    list.add(bean);
                    adapter.setData(list);
                }
                double zGrade=getComprehensiveGrade();
                pbsComprehensive.setTvThree(zGrade,16,R.color.app_background);
            }
        });
        if (position != -1) {
            EvaluationNewBean newBean = list.get(position);
            window.setEtValue(newBean.getName(), newBean.getGrade(), newBean.getWeight());
        }
//        window.setSoftInputMode(1);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.showAtLocation(findViewById(R.id.head_app_server),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                0); // 设置layout在PopupWindow中显示的位置
    }

    //综合分数
    private double getComprehensiveGrade(){
        float comprehensiveGrade = 0;
        for(int i=0;i<list.size();i++){
            float grade =  list.get(i).getGrade();
            float weight =  list.get(i).getWeight();
            comprehensiveGrade+= grade*weight;
        }
        return Double.valueOf(String.format("%.1f", comprehensiveGrade));
    }
}
