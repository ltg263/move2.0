package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DiscussLabelListbean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.PicBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.ReleaseArticleLabelAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.ProgressBarStyleView;
import com.secretk.move.view.RichTextEditor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/5/23 10:03
 * 邮箱；ltg263@126.com
 * 描述：写评测  专业
 */
public class EvaluationWriteActivity extends BaseActivity  implements ItemClickListener {
    InputMethodManager imm;
    private List<String> picList;
    //    ReleasePicAdapter releasePicAdapter;
    @BindView(R.id.rv_evaluation_tags)
    RecyclerView rvEvaluationTags;
    @BindView(R.id.pb_project_location)
    ProgressBarStyleView pbProjectLocation;
    ReleaseArticleLabelAdapter releaseArticleLabelAdapter;
    LinearLayoutManager layoutManager;
    @BindView(R.id.richtext_editor)
    RichTextEditor etNewContent;

    String modelPbTitle;

    int projectId;
    int modelType;
    String postTitle;
    String professionalEvaDetail;

    JSONArray postSmallImages;
    private String totalScore;
    String evaluationTags = "";

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle(getString(R.string.evaluation_write));
        mMenuInfos.add(0, new MenuInfo(R.string.evaluation_issue, getString(R.string.evaluation_issue), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_evaluation_write;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        MoveApplication.getContext().addActivity(this);
        modelPbTitle = getResources().getString(R.string.comprehensive_evaluation);
        imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        picList = new ArrayList<>();

//        releasePicAdapter = new ReleasePicAdapter();
//        rvPostSmallImages.setLayoutManager(new GridLayoutManager(this, 3));
//        rvPostSmallImages.setAdapter(releasePicAdapter);
//        releasePicAdapter.setItemListener(this);

        releaseArticleLabelAdapter = new ReleaseArticleLabelAdapter();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvEvaluationTags.setLayoutManager(layoutManager);
        rvEvaluationTags.setAdapter(releaseArticleLabelAdapter);
        releaseArticleLabelAdapter.setItemListener(this);
    }

    @Override
    protected void OnToolbarRightListener() {
        list = etNewContent.buildEditData();
        if (etNewContent.isBlankEd(list)) {
            ToastUtils.getInstance().show("评测内容不能为空");
            return;
        }

        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        setPostSmallImages();
    }

    @Override
    protected void initData() {
        projectId = Integer.valueOf(getIntent().getStringExtra("projectId"));
        totalScore = getIntent().getStringExtra("totalScore");
        modelType = Integer.valueOf(getIntent().getStringExtra(Constants.ModelType.MODEL_TYPE));
        professionalEvaDetail = getIntent().getStringExtra("professionalEvaDetail");
        //	modelType = 1-简单评测；2-全面系统专业评测;3-部分系统专业评测；4-专业评测-自定义类型
        //modelType=1对应为值为“简单评测", 2 为 "ALL-专业评测" 3 为 "PART—项目立项、核心团队" 4 为 "ALL-专业评测"
        if (modelType == Constants.ModelType.MODEL_TYPE_SIMPLENESS) {
            postTitle = "简单评测";
        }
        if (modelType == Constants.ModelType.MODEL_TYPE_ALL) {
            postTitle = "ALL-专业评测";
        }
        if (modelType == Constants.ModelType.MODEL_TYPE_PART) {
            modelPbTitle = getIntent().getStringExtra("modelName");
            postTitle = "PART -" + modelPbTitle;
        }
        if (modelType == Constants.ModelType.MODEL_TYPE_ALL_NEW) {
            postTitle = "ALL-专业评测";
        }

        pbProjectLocation.setTvOne(modelPbTitle, 0, getResources().getColor(R.color.title_gray));
        pbProjectLocation.setTvThree(Double.valueOf(totalScore), 16, R.color.app_background);
        pbProjectLocation.setPbProgressMaxVisible();
    }

    String picPath;
    int REQUEST_CODE_CAMERA = 199;

    @OnClick({R.id.localphoto, R.id.takephoto, R.id.addlabel, R.id.swithKeyboard})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.localphoto:
                intent = new Intent(this, SelectedPicActivity.class);
                intent.putExtra("max_pic", 99);
                intent.putExtra("current_pic", 0);
                startActivity(intent);
                break;
            case R.id.takephoto:
//                if (releasePicAdapter.getItemCount() >= 9) {
//                    ToastUtils.getInstance().show("最多选择九张图片");
//                    return;
//                }
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                picPath = getExternalFilesDir(null).getAbsolutePath() + "/" + System.currentTimeMillis() + ".png";
                Uri uri = Uri.fromFile(new File(picPath));
                //为拍摄的图片指定一个存储的路径
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                break;
            case R.id.addlabel:
                intent = new Intent(this, AddLabelActivity.class);
                startActivity(intent);
                break;
            case R.id.swithKeyboard:
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            File file = new File(picPath);
            if (file.exists()) {
                etNewContent.insertImage(null, picPath);
            }
        }
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

        if (SelectedPicActivity.picArray != null) {
            LongSparseArray<PicBean> picArray = SelectedPicActivity.picArray;
            for (int i = 0; i < picArray.size(); i++) {
                etNewContent.insertImage(null, picArray.get(picArray.keyAt(i)).getPath());
            }
            SelectedPicActivity.picArray = null;
        }
        if (AddLabelActivity.array != null) {
            JSONArray array = new JSONArray();
            for (int i = 0; i < AddLabelActivity.array.size(); i++) {
                JSONObject object = new JSONObject();
                DiscussLabelListbean.TagList bean = AddLabelActivity.array.get(AddLabelActivity.array.keyAt(i));
                arrayTags.put(AddLabelActivity.array.keyAt(i),bean);
                //tagId,tagName
                try {
                    object.put("tagId", bean.getTagId());
                    object.put("tagName", bean.getTagName());
                    array.put(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            evaluationTags = array.toString();
            AddLabelActivity.array = null;
        }
        releaseArticleLabelAdapter.setData(arrayTags);
    }

    List<RichTextEditor.EditData> list;

    public void setPostSmallImages() {
        postSmallImages = new JSONArray();
        loadingDialog.show();
        uploadImgFile(0, list.size());
    }

    private void uploadImgFile(final int index, final int size) {
        if (index >= size) {
            saveEvaluation();
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
                .method(RxHttpParams.HttpMethod.POST)
                .addPart("token", token)
                .addPart("uploadfile", StringUtil.getMimeType(file.getName()), file)
                .addPart(Constants.UPLOADIMG_TYPE.IMG_TYPE_KEY, Constants.UPLOADIMG_TYPE.POST_ICON)
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

    private void saveEvaluation() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            //针对某个项目发表文章
            node.put("projectId", projectId);
            //1-简单评测；2-全面系统专业评测;3-部分系统专业评测；4-专业评测-自定义类型
            node.put("modelType", modelType);
            //	 modelType=1对应为值为“简单评测", 2 为 "ALL-专业评测" 3 为 "PART—项目立项、核心团队" 4 为 "ALL-专业评测"
            node.put("postTitle", postTitle);
            //精确到小数点1位。简单评测 和部分评测 需要给出此值；ALL-专业评测 可以不用给。
//            if(modelType!=Constants.ModelType.MODEL_TYPE_ALL_NEW
//                    || modelType!=Constants.ModelType.MODEL_TYPE_ALL){
            node.put("totalScore", Float.valueOf(totalScore));
//            }
            //包含 fileName,fileUrl,size,extension 信息的json数组,最多3个
            node.put("postSmallImages", postSmallImages.toString());
            //内容
            node.put("evauationContent", etNewContent.getEditData(list));
            //	包含 modelId , modelName, score 三项的json数组
            node.put("professionalEvaDetail", professionalEvaDetail);
            //包含 tagId,tagName 的json数组，
            node.put("evaluationTags", evaluationTags);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadingDialog.show();
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SAVE_EVALUATION)
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
                            postTitle, getResources().getString(R.string.evaluation_succeed),getResources().getString(R.string.not_go_look), Constants.PublishSucceed.EVALUATION);
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

    @Override
    public void onItemClick(View view, int postion) {
//        if(postion==0){
////            String tagList = arrayTags.get(arrayTags.keyAt(postion)).getTagId();
//            Intent intent = new Intent(this, SelectProjectActivity.class);
//            intent.putExtra("publication_type",1);
//            intent.putExtra("projectId",projectId);
//            startActivity(intent);
//        }

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
