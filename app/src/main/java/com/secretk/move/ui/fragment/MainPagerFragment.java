package com.secretk.move.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.ui.activity.SearchActivity;
import com.secretk.move.ui.activity.SelectProjectActivity;
import com.secretk.move.ui.adapter.MainFragmentPagerAdapter;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.ViewPagerFixed;

import java.util.List;

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
    ViewPagerFixed vp_main_children;
    @BindView(R.id.tool_bar)
    Toolbar tool_bar;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    MainFragmentPagerAdapter adapter;
    public String[] head_list;
    Dialog dialog;
    Intent intent;
    @BindView(R.id.main_content)
    CoordinatorLayout main_content;

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
        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(getActivity());
       rl_parent.setBackground(new BitmapDrawable(getResources(),blurBitmap(bitmap,25)));


        view.findViewById(R.id.ll_evaluation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), SelectProjectActivity.class);
                intent.putExtra("publication_type",1);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.ll_article).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), SelectProjectActivity.class);
                intent.putExtra("publication_type",2);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.ll_discuss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), SelectProjectActivity.class);
                intent.putExtra("publication_type",3);
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

    @Override
    public void onUserVisible() {
        super.onUserVisible();
        if(this.isVisible()){
            FragmentManager fragmentManager = this.getChildFragmentManager();
            if (fragmentManager==null) {
                return;
            }
            List<Fragment> fragments = fragmentManager.getFragments();
            for(Fragment fragment : fragments){
                if(fragment instanceof MainBlueSkyFragment)
                    ((MainBlueSkyFragment)fragment).onUserVisible();
            }
        }
    }

    public void TabLayoutLenth() {
        tab_layout.post(new Runnable() {
            @Override
            public void run() {
                StatusBarUtil.setIndicator(tab_layout, 10, 10);
            }
        });

    }



    // 图片缩放比例(即模糊度)
    private static final float BITMAP_SCALE = 0.1f;

    public  Bitmap blurBitmap( Bitmap image, float blurRadius) {
        // 计算图片缩小后的长宽
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        // 将缩小后的图片做为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        // 创建一张渲染后的输出图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(getActivity());
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(blurRadius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);
        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }



    public static Bitmap screenShotWithoutStatusBar(Activity activity) {
        //通过window的源码可以看出：检索顶层窗口的装饰视图，可以作为一个窗口添加到窗口管理器
        View view = activity.getWindow().getDecorView();
        //启用或禁用绘图缓存
        view.setDrawingCacheEnabled(true);
        //创建绘图缓存
        view.buildDrawingCache();
        //拿到绘图缓存
        Bitmap bitmap = view.getDrawingCache();

        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

        //状态栏高度
        int statusBarHeight = frame.top;
        int width = UiUtils.getWindowWidth();
        int height = UiUtils.getWindowHeight();

        Bitmap bp = null;
        bp = Bitmap.createBitmap(bitmap, 0, 0, width, height );
        view.destroyDrawingCache();
        return bp;
    }
}
