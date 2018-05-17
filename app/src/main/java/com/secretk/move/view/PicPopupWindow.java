package com.secretk.move.view;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.secretk.move.R;
import com.secretk.move.bean.AreaListBean;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.view.widget.OnWheelChangedListener;
import com.secretk.move.view.widget.WheelView;
import com.secretk.move.view.widget.adapters.ArrayWheelAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.OnItemClick;

/**
 * 发表主题底部 PopupWindow
 *
 * @author liyeyu
 * @time 2014/11/5
 */
public class PicPopupWindow extends PopupWindow  implements  OnWheelChangedListener{

    /**
     * 所有省的编码集合
     */
    protected List<String>  mProvinceCode;
    /**
     * 所有省
     */
    protected String[] mProvinceDatas;
    /**
     * key - 省 value - 市
     */
    protected Map<String, String[]> mCitisDatasMap = new HashMap<>();


    /**
     * 当前省的Code
     */
    protected String mCurrentProviceCode="";
    /**
     * 当前省的名称
     */
    protected String mCurrentProviceName="浙江";
    /**
     * 当前市的名称
     */
    protected String mCurrentCityName="杭州市";
    private final WheelView mViewProvince;
    private final WheelView mViewCity;
    private final TextView mBtnConfirm;
    private View mMenuView;
    private Activity activity;

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        }
    }

    public static interface OnItemClick {

        public void onclick(String result);
    }



    public PicPopupWindow(Activity context, View view , final OnItemClick itemsOnClick) {
        super(context);
        this.activity = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_window_pic, null);
        mViewProvince =  mMenuView.findViewById(R.id.id_province);
        mViewCity =  mMenuView.findViewById(R.id.id_city);
        mBtnConfirm =  mMenuView.findViewById(R.id.btn_confirm);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点�?
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.showAtLocation(view, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
        setEvent();
        mBtnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                itemsOnClick.onclick(mCurrentProviceName+"  "+mCurrentCityName);
                dismiss();
            }
        });
    }


    private void setEvent() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<>(activity, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(5);
        mViewCity.setVisibleItems(5);
        updateCities();
        updateAreas();

        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
    }
    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceCode =  mProvinceCode.get(pCurrent);
        String[] cities = mCitisDatasMap.get(mCurrentProviceCode);
        mCurrentProviceName = mProvinceDatas[pCurrent];
        if (cities == null) {
            cities = new String[] { "" };
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<>(activity, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }
    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceCode)[pCurrent];
    }

    /**
     * 解析省市区的XML数据
     */
    private void initProvinceDatas() {
        String a = getJson("areas_lists.json");
        AreaListBean gson = new Gson().fromJson(a, AreaListBean.class);
        List<AreaListBean.RECORDSBean> beans = gson.getRECORDS();
        mProvinceCode = new ArrayList<>();
        for(int i= 0;i<beans.size();i++){
            AreaListBean.RECORDSBean bean = beans.get(i);
            if(bean.getCode().length()==2){
                mProvinceCode.add(bean.getCode());
            }
        }
        mProvinceDatas=new String[mProvinceCode.size()];
        for(int i= 0;i<mProvinceCode.size();i++){
            String code = mProvinceCode.get(i);
            List<String> list = new ArrayList<>();
            for(int j=0;j<beans.size();j++){
                String c = beans.get(j).getCode().substring(0,2);
                if(c.equals(code)){
                    list.add(beans.get(j).getName());
                }
                if(code.equals(beans.get(j).getCode())){
                    mProvinceDatas[i]=beans.get(j).getName();
                }
                mCitisDatasMap.put(code,list.toArray(new String[0]));
            }
        }
    }

    public String getJson(String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = activity.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
