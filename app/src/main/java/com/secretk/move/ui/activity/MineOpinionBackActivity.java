package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/31 16:27
 * 邮箱；ltg263@126.com
 * 描述：意见反馈
 */
public class MineOpinionBackActivity extends BaseActivity {
    @BindView(R.id.et_contact)
    EditText etContact;
    @BindView(R.id.et_relation)
    EditText etRelation;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("意见反馈");
        mMenuInfos.add(0,new MenuInfo(R.string.mine_submit, getString(R.string.mine_submit), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_opinion_back;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

    }

    @Override
    protected void OnToolbarRightListener() {
        String contact = etContact.getText().toString().trim();
        String relation = etRelation.getText().toString().trim();
        if(StringUtil.isBlank(contact)){
            ToastUtils.getInstance().show("建议内容不能为空");
            return;
        }
        if(StringUtil.isBlank(relation)){
            ToastUtils.getInstance().show("联系方式不能为空");
            return;

        }
        if(!NetUtil.isNetworkAvailable()){
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("content", contact);
            node.put("contactInfo", relation);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SUBMIT_SUGGEST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                ToastUtils.getInstance().show("反馈成功");
                MineOpinionBackActivity.this.finish();
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
    }

    @Override
    protected void initData() {

    }

}
