package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/7/28 10:21
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class HttpDomeActivity extends BaseActivity {
    @BindView(R.id.ip)
    EditText ip;
    @BindView(R.id.j_kou)
    EditText jKou;
    @BindView(R.id.token)
    EditText token;
    @BindView(R.id.et_input_03)
    EditText etInput03;
    @BindView(R.id.key1)
    EditText key1;
    @BindView(R.id.var1)
    EditText var1;
    @BindView(R.id.key2)
    EditText key2;
    @BindView(R.id.var2)
    EditText var2;
    @BindView(R.id.key3)
    EditText key3;
    @BindView(R.id.var3)
    EditText var3;
    @BindView(R.id.key4)
    EditText key4;
    @BindView(R.id.var4)
    EditText var4;
    @BindView(R.id.key5)
    EditText key5;
    @BindView(R.id.var5)
    EditText var5;
    @BindView(R.id.btn)
    TextView btn;
    @BindView(R.id.tv_ip)
    TextView tvIp;
    @BindView(R.id.tv_ip_1)
    TextView tvIp1;
    @BindView(R.id.tv_ip_2)
    TextView tvIp2;
    @BindView(R.id.tv_con)
    TextView tvCon;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_http_dome;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        return null;
    }

    @OnClick({R.id.tv_ip, R.id.tv_ip_1, R.id.tv_ip_2,R.id.btn,R.id.tv_ken})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ip:
                ip.setText("");
                ip.setText(tvIp.getText().toString().trim());
                break;
            case R.id.tv_ip_1:
                ip.setText("");
                ip.setText(tvIp1.getText().toString().trim());
                break;
            case R.id.tv_ip_2:
                ip.setText("");
                ip.setText(tvIp2.getText().toString().trim());
                break;
            case R.id.tv_ken:
                if(StringUtil.isBlank(SharedUtils.getToken())){
                    ToastUtils.getInstance().show("先登陆");
                    return;
                }
                token.setText(SharedUtils.getToken());
                break;
            case R.id.btn:
                initData1();
                break;
        }
    }

    private void initData1() {
        if(StringUtil.isBlank(getString(jKou)) || StringUtil.isBlank(getString(ip))){
            ToastUtils.getInstance().show("接口获取ip不能为空");
            return;
        }
        JSONObject node = new JSONObject();
        try {
            if(StringUtil.isNotBlank(token.getText().toString().trim())){
                node.put("token", token.getText().toString().trim());
            }
            if(StringUtil.isNotBlank(etInput03.getText().toString().trim())){
                node.put("pageIndex", Integer.valueOf(etInput03.getText().toString().trim()));
                node.put("pageSize", Constants.PAGE_SIZE);
            }
            if(StringUtil.isNotBlank(getString(key1))){
                if(isNumeric(getString(var1))){
                    node.put(getString(key1), Integer.valueOf(getString(var1)));
                }else{
                    node.put(getString(key1), getString(var1));
                }
            }
            if(StringUtil.isNotBlank(getString(key2))){
                if(isNumeric(getString(var2))){
                    node.put(getString(key2), Integer.valueOf(getString(var2)));
                }else{
                    node.put(getString(key2), getString(var2));
                }
            }
            if(StringUtil.isNotBlank(getString(key3))){
                if(isNumeric(getString(var3))){
                    node.put(getString(key3), Integer.valueOf(getString(var3)));
                }else{
                    node.put(getString(key3), getString(var3));
                }
            }
            if(StringUtil.isNotBlank(getString(key4))){
                if(isNumeric(getString(var4))){
                    node.put(getString(key4), Integer.valueOf(getString(var4)));
                }else{
                    node.put(getString(key4), getString(var4));
                }
            }
            if(StringUtil.isNotBlank(getString(key5))){
                if(isNumeric(getString(var5))){
                    node.put(getString(key5), Integer.valueOf(getString(var5)));
                }else{
                    node.put(getString(key5), getString(var5));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(getString(ip)+getString(jKou))
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String bean) {
                try {
                    JSONObject object = new JSONObject(bean);
                    tvCon.setText(object.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private String getString(EditText et){
        return et.getText().toString().trim();
    }


    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
