package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DiscussLabelListbean;
import com.secretk.move.bean.UpImgBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.ReleaseArticleLabelAdapter;
import com.secretk.move.ui.adapter.ReleasePicAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PicUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
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

/*发表讨论*/
public class ReleaseDiscussActivity extends AppCompatActivity implements ItemClickListener {
    InputMethodManager imm;
    private List<String> picList;
    @BindView(R.id.recycler_pic)
    RecyclerView recycler_pic;
    ReleasePicAdapter releasePicAdapter;
    @BindView(R.id.recycler_horizontal)
    RecyclerView recycler_horizontal;
    @BindView(R.id.ed_title)
    EditText ed_title;
    ReleaseArticleLabelAdapter releaseArticleLabelAdapter;
    LinearLayoutManager layoutManager;

    @BindView(R.id.ed_content)
    EditText ed_content;
    List<String> serverImgList = new ArrayList<>();
    LoadingDialog loadingDialog;
    int projectId;
    String token = SharedUtils.singleton().get("token", "");
    private JSONArray sonArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_discuss);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        MoveApplication.getContext().addActivity(this);
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
        releaseArticleLabelAdapter.setItemListener(this);

        ed_title.setHint(Html.fromHtml("请输入标题 <small>(6-30字之间)</small>"));
        loadingDialog = new LoadingDialog(this);
        projectId = getIntent().getIntExtra("projectId", 0);
    }

    @OnClick(R.id.img_return)
    public void img_return() {
        finish();
    }
    List<String> adapterImgList;
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
        // tagId,tagName
        sonArray = new JSONArray();
        if(arrayTags!=null){
            for(int i=0;i<arrayTags.size();i++){
                JSONObject object = new JSONObject();
                DiscussLabelListbean.TagList aa = arrayTags.get(arrayTags.keyAt(i));
                try {
                    object.put("tagId",aa.getTagId());
                    object.put("tagName",aa.getTagName());
                    if(i!=0){
                        sonArray.put(object);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        if(sonArray.length()==0){
            ToastUtils.getInstance().show("请选择#标签#");
            return;
        }
        loadingDialog.show();
        discussImages="";
        adapterImgList= releasePicAdapter.getData();
        if (adapterImgList!=null&&adapterImgList.size()!=0){
            upImgHttp(adapterImgList.get(0),0);
        }else{
            httpRelease();
        }

    }

    @OnClick(R.id.localphoto)
    public void localphoto(View view) {
        Intent intent = new Intent(this, SelectedPicActivity.class);
        intent.putExtra("max_pic", 9);
        intent.putExtra("current_pic", releasePicAdapter.getItemCount());
        startActivity(intent);
    }

    int REQUEST_CODE_CAMERA = 199;
    String picPath;

    @OnClick(R.id.takephoto)
    public void takephoto(View view) {
        if (releasePicAdapter.getItemCount() >= 9) {
            ToastUtils.getInstance().show("最多选择九张图片");
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
        releasePicAdapter.removeIndex(postion);
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

    SparseArray<DiscussLabelListbean.TagList> arrayTags;//默认标签
    DiscussLabelListbean.TagList beans;//
    @Override
    protected void onResume() {
        super.onResume();
        arrayTags = new SparseArray<>();
        beans = new DiscussLabelListbean.TagList();
        beans.setTagId(String.valueOf(projectId));
        beans.setTagName(getIntent().getStringExtra("projectPay"));
        arrayTags.put(-1,beans);

        if (AddLabelActivity.array != null) {
            for (int i = 0; i < AddLabelActivity.array.size(); i++) {
                DiscussLabelListbean.TagList bean = AddLabelActivity.array.get(AddLabelActivity.array.keyAt(i));
                arrayTags.put(AddLabelActivity.array.keyAt(i),bean);
            }
            AddLabelActivity.array = null;
        }
        releaseArticleLabelAdapter.setData(arrayTags);

        if (SelectedPicActivity.picArray != null) {
            releasePicAdapter.addSparseData(SelectedPicActivity.picArray);
            SelectedPicActivity.picArray=null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    String discussImages = "";

    public void httpRelease() {

        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", projectId);
            node.put("postTitle", getEdTitle());
            node.put("disscussContents", getEdContent());
            node.put("tagInfos", sonArray.toString());
            if (StringUtil.isNotBlank(discussImages)) {
                node.put("discussImages", discussImages);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.RELEASE_DISCUSS)
                .addPart("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addPart("sign", MD5.Md5(node.toString()))
                .build();
        params.setMethod(RxHttpParams.HttpMethod.POST);
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject object = new JSONObject(str);
                    int postId = object.getJSONObject("data").getInt("postId");
                    IntentUtil.startPublishSucceedActivity(String.valueOf(postId),
                            "发表打假", getResources().getString(R.string.discuss_succeed),getResources().getString(R.string.not_go_look), Constants.PublishSucceed.DISCUSS);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
    }

    public void upImgHttp(String path, final int position) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (TextUtils.isEmpty(token)) {
            ToastUtils.getInstance().show("请先登录账号");
            return;
        }
        LogUtil.w("当前文件大小："+ PicUtil.getPrintSize(file.length()));
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.UPLOAD_USER_ICON_FILE)
                .method(RxHttpParams.HttpMethod.POST)
                .addPart("token", token)
                .addPart("uploadfile", StringUtil.getMimeType(file.getName()), file)
                .addPart(Constants.UPLOADIMG_TYPE.IMG_TYPE_KEY, Constants.UPLOADIMG_TYPE.POST_ICON)
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
                }else {
                    upImgHttp(adapterImgList.get(position+1),position+1);
                }
            }

            @Override
            public void onError(String message) {
                super.onError(message);
                loadingDialog.dismiss();
            }
        });
    }

    JSONArray array = new JSONArray();

    public void generatePostSmallImages(List<String> list) throws JSONException {
        for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fileName", "");

            String str = list.get(i);
            jsonObject.put("fileUrl", str);
            jsonObject.put("size", "");
            jsonObject.put("extension", "");
            array.put(jsonObject);
            if (i == list.size() - 1) {
                discussImages = array.toString();
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
