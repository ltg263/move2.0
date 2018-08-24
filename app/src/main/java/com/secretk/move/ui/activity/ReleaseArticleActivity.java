package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DiscussLabelListbean;
import com.secretk.move.bean.PicBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.ReleaseArticleLabelAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PicUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.DialogUtils;
import com.secretk.move.view.LoadingDialog;
import com.secretk.move.view.RichTextEditor;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*发表文章*/
public class ReleaseArticleActivity extends AppCompatActivity implements ItemClickListener {

    @BindView(R.id.rv_evaluation_tags)
    RecyclerView recycler_horizontal;
    @BindView(R.id.ed_title)
    EditText ed_title;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_num)
    TextView tvNum;

    ReleaseArticleLabelAdapter releaseArticleLabelAdapter;
    LinearLayoutManager layoutManager;

    LoadingDialog loadingDialog;
    int projectId;
    private JSONArray sonArray;
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
        MoveApplication.getContext().addActivity(this);
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.main_background), 0);
        releaseArticleLabelAdapter = new ReleaseArticleLabelAdapter();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_horizontal.setLayoutManager(layoutManager);
        recycler_horizontal.setAdapter(releaseArticleLabelAdapter);
        releaseArticleLabelAdapter.setItemListener(this);
//        ed_title.setHint(Html.fromHtml("请输入标题 <small>(6-60字之间)</small>"));
        richtext_editor.setHintText("请写下您对项目的深度解读，做个分析师！");

        StringUtil.etSearchChangedListener(ed_title, null, new StringUtil.EtChange() {
            @Override
            public void etYes() {
                tvNum.setText(ed_title.getText().toString().trim().length()+ "/60");
            }
            @Override
            public void etNo() {
                tvNum.setText("0/60");
            }
        });
        loadingDialog = new LoadingDialog(this);
        projectId = getIntent().getIntExtra("projectId", 0);

        beans = new DiscussLabelListbean.TagList();
        beans.setTagId(String.valueOf(projectId));
        beans.setTagName(getIntent().getStringExtra("projectPay"));
        arrayTags.put(-1,beans);
    }
    public void setTextViewNum(){
        list = richtext_editor.buildEditData();
        tv2.setText(richtext_editor.getBlankEdLeng(list) + "/5000");
        if(richtext_editor.getBlankEdLeng(list)>=100){
            tv1.setVisibility(View.INVISIBLE);
            return;
        }
        tv1.setVisibility(View.VISIBLE);
        tv1.setText("加油，还差"+(100-richtext_editor.getBlankEdLeng(list))+"个字");
    }

    @OnClick(R.id.img_return)
    public void img_return() {
        finish();
    }

    private void tv_release() {
        list = richtext_editor.buildEditData();
        if (StringUtil.isBlank(getEdTitle())) {
            ToastUtils.getInstance().show("请输入标题");
            return;
        }
        if (getEdTitle().length() < 6) {
            ToastUtils.getInstance().show("标题不能少于6个汉字");
            return;
        }
        if (richtext_editor.isBlankEd(list)) {
            ToastUtils.getInstance().show("文章内容不能为空");
            return;
        }
        if (richtext_editor.isBlankEdLeng(list)) {
            ToastUtils.getInstance().show("评测字数最少100个字");
            return;
        }
        setPostSmallImages();
    }

    List<RichTextEditor.EditData> list;
    JSONArray postSmallImages;

    public void setPostSmallImages() {
        postSmallImages = new JSONArray();

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
//        if(sonArray.length()==0){
//            ToastUtils.getInstance().show("请选择#标签#");
//            return;
//        }
        qiToken="";
        loadingDialog.show();
        uploadImgFile(0, list.size());
    }

    String qiToken = "";
    String userId = "";
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
        final File file = new File(path);
        if (!file.exists()) {
            uploadImgFile(index + 1, size);
            return;
        }
        LogUtil.w("当前文件大小："+ PicUtil.getPrintSize(file.length()));
        if(StringUtil.isNotBlank(qiToken) && StringUtil.isNotBlank(userId)){
            NetUtil.sendQiniuImgUrl(file, qiToken, NetUtil.getQiniuImgName("posts",userId,0), new NetUtil.QiniuImgUpload() {
                @Override
                public void uploadStatus(String imgUrl, boolean status) {
                    if(status){
                        try {
                            JSONObject postObject = new JSONObject();
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
                    }else{
                        ToastUtils.getInstance().show("照片上传失败，请重新上传");
                        loadingDialog.dismiss();
                    }
                }
            });
        }else{
            NetUtil.getQiniuToken(new NetUtil.SaveCommendationImp() {
                @Override
                public void finishCommendation(String mUserId,String mQiToken, boolean status) {
                    qiToken=mQiToken;
                    userId=mUserId;
                    if(!status){
                        ToastUtils.getInstance().show("服务器出错了");
                        loadingDialog.dismiss();
                        return;
                    }
                    NetUtil.sendQiniuImgUrl(file, mQiToken, NetUtil.getQiniuImgName("avatars",mUserId,index), new NetUtil.QiniuImgUpload() {
                        @Override
                        public void uploadStatus(String imgUrl, boolean status) {
                            if(status){
                                try {
                                    JSONObject postObject = new JSONObject();
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
                            }else{
                                ToastUtils.getInstance().show("照片上传失败，请重新上传");
                                loadingDialog.dismiss();
                            }
                        }
                    });
                }
            });
        }
    }

    private void saveArticle() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", projectId);
            node.put("postTitle", getEdTitle());
            node.put("articleContents", richtext_editor.getEditData(list));
            node.put("tagInfos", sonArray.toString());
            if (!TextUtils.isEmpty(postSmallImages.toString())) {
                node.put("discussImages", postSmallImages.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadingDialog.show();
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.RELEASE_ARTICLE)
                .method(RxHttpParams.HttpMethod.POST)
                .addPart("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addPart("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject object = new JSONObject(str);
                    int postId = object.getJSONObject("data").getInt("postId");
                    IntentUtil.startPublishSucceedActivity(String.valueOf(postId),
                            "发表文章", getResources().getString(R.string.article_succeed),getResources().getString(R.string.not_go_look), Constants.PublishSucceed.ARTICLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    @OnClick({R.id.localphoto,R.id.addlabel, R.id.tv_release})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.localphoto:
                openChangeHeadDialog();
                break;
            case R.id.addlabel:
                intent = new Intent(this, AddLabelActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_release:
                tv_release();
                break;
        }
    }

    int REQUEST_CODE_CAMERA = 199;
    String picPath;
    private void openChangeHeadDialog() {
        String[] srt = {"上传图片", "从手机相册中选择", "拍照上传"};
        DialogUtils.ShowAlertDialog(this, srt, new DialogUtils.AlertDialogInterface() {
            @Override
            public void btnLineListener(int index) {
                if (index == 1) {//从手机相册中选择
                    localphoto();
                } else if (index == 2) {//拍照上传
                    takephoto();
                }
            }
        });
    }
    private void localphoto() {
        Intent intent = new Intent(this, SelectedPicActivity.class);
        intent.putExtra("max_pic", 99);
//        intent.putExtra("current_pic", releasePicAdapter.getItemCount());
        intent.putExtra("current_pic", 0);
        startActivity(intent);
    }


    private void takephoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        picPath = getExternalFilesDir(null).getAbsolutePath() + "/" + System.currentTimeMillis() + ".png";
        Uri uri = Uri.fromFile(new File(picPath));
        //为拍摄的图片指定一个存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }


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

    SparseArray<DiscussLabelListbean.TagList> arrayTags = new SparseArray<>();//默认标签;//默认标签
    DiscussLabelListbean.TagList beans;//
    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.w("当前的Class名称:"+StringUtil.getCurrentClassName(this));
        MobclickAgent.onPageStart(StringUtil.getCurrentClassName(this));
        MobclickAgent.onResume(this);
        if (AddLabelActivity.array != null) {
            if(arrayTags!=null){
                arrayTags.clear();
            }
            beans = new DiscussLabelListbean.TagList();
            beans.setTagId(String.valueOf(projectId));
            beans.setTagName(getIntent().getStringExtra("projectPay"));
            arrayTags.put(-1,beans);
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
        if(SelectProjectActivity.staticProjectId!=0){
            projectId = SelectProjectActivity.staticProjectId;
            releaseArticleLabelAdapter.amendCode(projectId,SelectProjectActivity.staticProjectCode);
            SelectProjectActivity.staticProjectId=0;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(StringUtil.getCurrentClassName(this));
        MobclickAgent.onPause(this);
    }
    public String getEdTitle() {
        return ed_title.getText().toString().trim();
    }

}
