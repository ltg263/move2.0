package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.UpImgBean;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.ReleaseArticleLabelAdapter;
import com.secretk.move.ui.adapter.ReleasePicAdapter;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.LoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*发表文章*/
public class ReleaseArticleActivity extends AppCompatActivity implements ItemClickListener {
    InputMethodManager imm;
    private List<String> picList;
    @BindView(R.id.recycler_pic)
    RecyclerView recycler_pic;
    ReleasePicAdapter releasePicAdapter;
    @BindView(R.id.recycler_horizontal)
    RecyclerView recycler_horizontal;
    @BindView(R.id.ed_title)
    EditText ed_title;

    @BindView(R.id.ed_content)
    EditText ed_content;
    ReleaseArticleLabelAdapter releaseArticleLabelAdapter;
    LinearLayoutManager layoutManager;
    List<String> serverImgList = new ArrayList<>();
    LoadingDialog loadingDialog;
    String token = SharedUtils.singleton().get("token", "");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_article);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.main_background), 0);
        imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        picList = new ArrayList<>();

        releasePicAdapter = new ReleasePicAdapter();
        recycler_pic.setLayoutManager(new GridLayoutManager(this, 3));
        recycler_pic.setAdapter(releasePicAdapter);
        releasePicAdapter.setItemListener(this);

        releaseArticleLabelAdapter = new ReleaseArticleLabelAdapter();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_horizontal.setLayoutManager(layoutManager);
        recycler_horizontal.setAdapter(releaseArticleLabelAdapter);
        ed_title.setHint(Html.fromHtml("请输入标题 <small>(6-30字之间)</small>"));
        loadingDialog=new LoadingDialog(this);
    }

    @OnClick(R.id.img_return)
    public void img_return() {
        finish();
    }

    @OnClick(R.id.tv_release)
    public void tv_release() {
        if (TextUtils.isEmpty(getEdTitle())) {
            ToastUtils.getInstance().show("请输入标题");
            return;
        }
        if (TextUtils.isEmpty(getEdContent())) {
            ToastUtils.getInstance().show("请输入内容");
            return;
        }
        if (getEdTitle().length() < 6) {
            ToastUtils.getInstance().show("标题不能少于6个汉字");
            return;
        }
        List<String> adapterImgList = releasePicAdapter.getData();
        for (int i = 0; i < adapterImgList.size(); i++) {
            if (i==0){
                loadingDialog.show();
            }
            String str = adapterImgList.get(i);
            upImgHttp(str);
            return;
        }
        loadingDialog.show();
        httpRelease();
    }

    @OnClick(R.id.localphoto)
    public void localphoto(View view) {
        Intent intent = new Intent(this, SelectedPicActivity.class);
        intent.putExtra("max_pic", 3);
        intent.putExtra("current_pic", releasePicAdapter.getItemCount());
        startActivity(intent);
    }

    int REQUEST_CODE_CAMERA = 199;
    String picPath;

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @OnClick(R.id.takephoto)
    public void takephoto(View view) {
        if (releasePicAdapter.getItemCount() >= 3) {
            ToastUtils.getInstance().show("最多选择三张张图片");
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        picPath = getExternalFilesDir(null).getAbsolutePath() + "/" + System.currentTimeMillis() + ".png";
        Uri uri = Uri.fromFile(new File(picPath));
        //为拍摄的图片指定一个存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    @OnClick(R.id.addlabel)
    public void addlabel(View view) {
        Intent intent = new Intent(this, AddLabelActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.swithKeyboard)
    public void swithKeyboard(View view) {
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    // takephoto addlabel
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            File file = new File(picPath);
            if (file.exists()) {
                releasePicAdapter.addData(picPath);
            }
        }
    }

    @Override
    public void onItemClick(View view, int postion) {

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AddLabelActivity.array != null) {
            releaseArticleLabelAdapter.setData(AddLabelActivity.array);
        }
        if (SelectedPicActivity.picArray != null) {
            releasePicAdapter.addSparseData(SelectedPicActivity.picArray);
            SelectedPicActivity.picArray = null;
        }
    }

    String postSmallImages = null;

    public void httpRelease() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", 1);
            node.put("postTitle", getEdTitle());
            node.put("articleContents", getEdContent());
            if (!TextUtils.isEmpty(postSmallImages)) {
                node.put("discussImages", postSmallImages);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.RELEASE_ARTICLE)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, BaseRes.class, new HttpCallBackImpl<BaseRes>() {
            @Override
            public void onCompleted(BaseRes bean) {
                loadingDialog.dismiss();
                int code = bean.getCode();
                finish();
                Log.e("jyh_onCompleted", "code=" + code);
            }

            @Override
            public void onError(String message) {
                super.onError(message);
                Log.e("jyh_e", message);
                loadingDialog.dismiss();
            }
        });
    }

    public void upImgHttp(String path) {
        File file = new File(path);
        LogUtil.w("file.exists(:" + file.exists());
        if (!file.exists()) {
            return;
        }

        if (TextUtils.isEmpty(token)){
            ToastUtils.getInstance().show("请先登录账号");
            return;
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.UPLOAD_IMG_FILE)
                .addPart("token", token)
                .addPart("uploadfile", "multipart/form-data", file)
                .addPart("imgtype", "3")
                .build();
        RetrofitUtil.request(params, UpImgBean.class, new HttpCallBackImpl<UpImgBean>() {
            @Override
            public void onCompleted(UpImgBean data) {
                String srt = data.getData().getImgUrl();
                serverImgList.add(srt);
                if (serverImgList.size() == releasePicAdapter.getItemCount()) {
                    try {
                        generatePostSmallImages(serverImgList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.e("jyh_onCompleted", data.getMsg());
            }
            @Override
            public void onError(String message) {
                super.onError(message);
                Log.e("jyh_onError", message);
                loadingDialog.dismiss();
            }
        });
    }
    JSONArray array=new JSONArray();
    public void generatePostSmallImages(List<String> list) throws JSONException {
        for (int i=0;i<list.size();i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fileName", "");
            String str=list.get(i);
            jsonObject.put("fileUrl", str);
            jsonObject.put("size", "");
            jsonObject.put("extension", "");
            array.put(jsonObject);
            if (i==list.size()-1){
                postSmallImages=array.toString();
                httpRelease();
            }
        }
    }

    public String getEdTitle() {
        return ed_title.getText().toString().trim();
    }

    public String getEdContent() {
        return ed_content.getText().toString().trim();
    }
}
