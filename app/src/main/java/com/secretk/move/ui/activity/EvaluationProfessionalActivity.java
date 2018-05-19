package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.ui.adapter.EvaluationTypeAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.Clickable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/17 19:27
 * 邮箱；ltg263@126.com
 * 描述：专业测评
 */
public class EvaluationProfessionalActivity extends BaseActivity {


    @BindView(R.id.rv_type_lists)
    RecyclerView rvTypeLists;
    EvaluationTypeAdapter adapter;
    @BindView(R.id.tv_status)
    TextView textView;
    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle(getString(R.string.evaluation_professional));
        mMenuInfos.add(0, new MenuInfo(R.string.evaluation_simpleness, getString(R.string.evaluation_simpleness), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_evaluation_professional;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        setVerticalManager(rvTypeLists);
        adapter = new EvaluationTypeAdapter(this);
        rvTypeLists.setAdapter(adapter);

        String tagAll = "经过科学筛选，系统提供以下几个评测纬度，可完整评测，" +
                "也可部分评测；您也可以自己新建模型进行测评。"
                +getResources().getString(R.string.evaluation_state);
        String tagOnly[]= new String[1];
        tagOnly[0]=getResources().getString(R.string.evaluation_state);
        Clickable.getSpannableString(tagAll, tagOnly, textView, new Clickable.ClickListener() {
            @Override
            public void setOnClick(String name) {
                ToastUtils.getInstance().show(name);
            }
        });
    }

    @Override
    protected void initData() {
        List<BaseRes> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            BaseRes res = new BaseRes();
            res.setMsg("我是"+i);
            list.add(res);
        }
        adapter.setData(list);

    }


    @Override
    protected void OnToolbarRightListener() {
        IntentUtil.startActivity(EvaluationSimplenessActivity.class);
    }

}
