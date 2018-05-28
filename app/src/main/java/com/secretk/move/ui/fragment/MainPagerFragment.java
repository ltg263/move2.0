package com.secretk.move.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.ui.activity.EvaluationWriteActivity;
import com.secretk.move.ui.activity.ReleaseArticleActivity;
import com.secretk.move.ui.activity.ReleaseDiscussActivity;
import com.secretk.move.ui.activity.ReleaseEvaluationActivity;
import com.secretk.move.ui.activity.SearchActivity;
import com.secretk.move.ui.adapter.MainFragmentPagerAdapter;
import com.secretk.move.utils.UiUtils;

import java.lang.reflect.Field;

import butterknife.BindView;

/**
 * Created by zc on 2018/4/5.
 * <p>
 * 主页fragment 下面还有3个子fragment
 * 分别是
 * MainRfFragment
 * MainFollowFragment
 * MainBlueSkyFragment
 */

public class MainPagerFragment extends LazyFragment implements Toolbar.OnMenuItemClickListener {
    @BindView(R.id.vp_main_children)
    ViewPager vp_main_children;
    @BindView(R.id.tool_bar)
    Toolbar tool_bar;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    MainFragmentPagerAdapter adapter;
    public String[] head_list;
    Dialog dialog;
    Intent intent;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_main;
    }

    @Override
    public void initViews() {
        tool_bar.inflateMenu(R.menu.activity_main_menu);
        tool_bar.setNavigationIcon(R.drawable.main_search);
        adapter = new MainFragmentPagerAdapter(getChildFragmentManager());
        vp_main_children.setAdapter(adapter);
        vp_main_children.setOffscreenPageLimit(3);
        tab_layout.setupWithViewPager(vp_main_children);
        tab_layout.setTabMode(TabLayout.MODE_FIXED);
        tool_bar.setOnMenuItemClickListener(this);

        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        TabLayoutLenth();
    }

    @Override
    public void onFirstUserVisible() {
        head_list = UiUtils.getStringArray(R.array.fragment_main_titles);
        for (String name : head_list) {
            tab_layout.addTab(tab_layout.newTab().setText(name));
        }
        vp_main_children.setOffscreenPageLimit(head_list.length);
        adapter.setData(head_list);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {

        initDailog();
        return false;
    }


    private void initDailog() {
        dialog = new Dialog(getContext(), R.style.Dialog_Fullscreen);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_release, null);
        view.findViewById(R.id.ll_evaluation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), EvaluationWriteActivity.class);
                intent.putExtra("professionalEvaDetail","professionalEvaDetail");
                intent.putExtra("totalScore",0);
                intent.putExtra("projectId",1);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.ll_article).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), ReleaseArticleActivity.class);
                intent.putExtra("projectId",1);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.ll_discuss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), ReleaseDiscussActivity.class);
                intent.putExtra("projectId",1);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

    public void TabLayoutLenth() {
        tab_layout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tab_layout.getChildAt(0);

                    int dp10 = UiUtils.dip2px(10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
