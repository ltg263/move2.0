package com.secretk.move.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.ProjectTabBean;
import com.secretk.move.ui.activity.SearchActivity;
import com.secretk.move.ui.adapter.MainProjectFragmentPagerAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.view.MagicIndicatorUtils;
import com.secretk.move.view.ViewPagerFixed;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/6/7 16:03
 * 邮箱；ltg263@126.com
 * 描述：主页项目
 */
public class ProjectFragment extends LazyFragment {

    @BindView(R.id.vp_main_children)
    ViewPagerFixed vp_main_children;
    @BindView(R.id.tool_bar)
    Toolbar tool_bar;
    MainProjectFragmentPagerAdapter adapter;

    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    private List<ProjectTabBean.DataBean.TabsBean> tabs;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_project;
    }

    @Override
    public void initViews() {
        tool_bar.setNavigationIcon(R.drawable.main_search);
        adapter = new MainProjectFragmentPagerAdapter(getChildFragmentManager());
        vp_main_children.setAdapter(adapter);

        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onFirstUserVisible() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_PROJECT_TAB)
//                .url(Constants.TOKEN_POP)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, ProjectTabBean.class, new HttpCallBackImpl<ProjectTabBean>() {
            @Override
            public void onCompleted(ProjectTabBean bean) {
                tabs = bean.getData().getTabs();
                vp_main_children.setOffscreenPageLimit(tabs.size());
                adapter.setData(tabs);
                List<String> list = new ArrayList<>();
                int defaultOpen=0;
                for(int i=0;i<tabs.size();i++){
                    list.add(tabs.get(i).getTabTitle());
                    if(tabs.get(i).getIsDefaultOpen()==1){
                        defaultOpen=i;
                    }
                }
                MagicIndicatorUtils.initMagicIndicatorTitle(getActivity(),list,vp_main_children,magicIndicatorTitle);
                vp_main_children.setCurrentItem(defaultOpen);
            }
        });
    }
}
