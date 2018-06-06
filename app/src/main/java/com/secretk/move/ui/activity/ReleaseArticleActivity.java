package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DiscussLabelListbean;
import com.secretk.move.bean.PicBean;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.ReleaseArticleLabelAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.LoadingDialog;
import com.secretk.move.view.RichTextEditor;

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

    @BindView(R.id.recycler_horizontal)
    RecyclerView recycler_horizontal;
    @BindView(R.id.ed_title)
    EditText ed_title;

    @BindView(R.id.ed_content)
    EditText ed_content;
    ReleaseArticleLabelAdapter releaseArticleLabelAdapter;
    LinearLayoutManager layoutManager;

    LoadingDialog loadingDialog;
    int projectId;
    String token = SharedUtils.singleton().get("token", "");

    @BindView(R.id.richtext_editor)
    RichTextEditor richtext_editor;

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

        setPostSmallImages();
    }

    List<RichTextEditor.EditData> list;
    JSONArray postSmallImages;

    public void setPostSmallImages() {
        postSmallImages = new JSONArray();

        loadingDialog.show();
        list = richtext_editor.buildEditData();
        uploadImgFile(0, list.size());
    }

    private void uploadImgFile(final int index, final int size) {
        if (index >= size) {
            saveArticle();
            return;
        }
        String path = list.get(index).imagePath;
        if (StringUtil.isBlank(path)) {
            uploadImgFile(index + 1, size);
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            uploadImgFile(index + 1, size);
            return;
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.UPLOAD_USER_ICON_FILE)
                .addPart("token", token)
                .addPart("uploadfile ", StringUtil.getMimeType(file.getName()), file)
                .addPart(Constants.UPLOADIMG_TYPE.IMG_TYPE_KEY, Constants.UPLOADIMG_TYPE.PROJECT_ICON)
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject postObject = new JSONObject();
                    String imgUrl = new JSONObject(str).getJSONObject("data").getString("imgUrl");
                    if (StringUtil.isNotBlank(imgUrl)) {
                        postObject.put("fileUrl", imgUrl);
                        postObject.put("fileName", "");
                        postObject.put("extension", StringUtil.getFileSuffix(imgUrl));
                    }
                    list.get(index).imagePath = Constants.BASE_IMG_URL + imgUrl;
                    postSmallImages.put(postObject);
                    uploadImgFile(index + 1, size);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String message) {
                super.onError(message);
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    private void saveArticle() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", projectId);
            node.put("postTitle", getEdTitle());
            node.put("articleContents", richtext_editor.getEditData(list));
            if (!TextUtils.isEmpty(postSmallImages.toString())) {
                node.put("discussImages", postSmallImages.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadingDialog.show();
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.RELEASE_ARTICLE)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, BaseRes.class, new HttpCallBackImpl<BaseRes>() {
            @Override
            public void onCompleted(BaseRes str) {
                ToastUtils.getInstance().show("发布文章成功");
                finish();
            }
            @Override
            public void onFinish() {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    @OnClick(R.id.localphoto)
    public void localphoto(View view) {
        Intent intent = new Intent(this, SelectedPicActivity.class);
        intent.putExtra("max_pic", 3);
//        intent.putExtra("current_pic", releasePicAdapter.getItemCount());
        intent.putExtra("current_pic", 0);
        startActivity(intent);
    }

    int REQUEST_CODE_CAMERA = 199;
    String picPath;

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @OnClick(R.id.takephoto)
    public void takephoto(View view) {

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
                richtext_editor.insertImage(null,picPath);
            }
        }
    }

    @Override
    public void onItemClick(View view, int postion) {
//        if(postion==0){
//            Intent intent = new Intent(this, SelectProjectActivity.class);
//            intent.putExtra("publication_type",2);
//            intent.putExtra("projectId",projectId);
//            startActivity(intent);
//        }
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
            LongSparseArray<PicBean> picArray = SelectedPicActivity.picArray;
            for(int i= 0 ;i<picArray.size();i++){
                richtext_editor.insertImage(null,picArray.get(picArray.keyAt(i)).getPath());
            }
            SelectedPicActivity.picArray = null;
        }
    }



    public String getEdTitle() {
        return ed_title.getText().toString().trim();
    }

    public String getEdContent() {
        return ed_content.getText().toString().trim();
    }
}
