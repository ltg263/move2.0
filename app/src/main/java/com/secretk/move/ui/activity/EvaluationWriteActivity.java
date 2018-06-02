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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DiscussLabelListbean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.PicBean;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.ui.adapter.ReleaseArticleLabelAdapter;
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
public class EvaluationWriteActivity extends BaseActivity {
    InputMethodManager imm;
    @BindView(R.id.tv_icon)
    ImageView tvIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.rl_top_theme)
    RelativeLayout rlTopTheme;
    private List<String> picList;
    @BindView(R.id.rv_post_small_images)
    RecyclerView rvPostSmallImages;
    //    ReleasePicAdapter releasePicAdapter;
    @BindView(R.id.rv_evaluation_tags)
    RecyclerView rvEvaluationTags;
    @BindView(R.id.pb_project_location)
    ProgressBarStyleView pbProjectLocation;
    ReleaseArticleLabelAdapter releaseArticleLabelAdapter;
    LinearLayoutManager layoutManager;
    @BindView(R.id.et_evaluation_content)
    EditText etEvaluationContent;
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
    }

    @Override
    protected void OnToolbarRightListener() {
//        if (StringUtil.isBlank(etNewContent)) {
//            ToastUtils.getInstance().show("评测内容不能为空");
//            return;
//        }
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        setPostSmallImages();
    }

    @Override
    protected void initData() {
        projectId = getIntent().getIntExtra("projectId", 0);
        totalScore = getIntent().getStringExtra("totalScore");
        modelType = getIntent().getIntExtra(Constants.ModelType.MODEL_TYPE, 0);
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
            postTitle = "PART-" + modelPbTitle;
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

    @OnClick({R.id.localphoto, R.id.takephoto, R.id.addlabel, R.id.swithKeyboard, R.id.tv_submit})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.localphoto:
                intent = new Intent(this, SelectedPicActivity.class);
                intent.putExtra("max_pic", 9);
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
            case R.id.tv_submit:
                intent = new Intent(this, DetailsReviewAllActivity.class);
//                intent.putExtra("postId",postId);
                startActivity(intent);
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


    @Override
    protected void onResume() {
        super.onResume();
        if (SelectedPicActivity.picArray != null) {
//            releasePicAdapter.addSparseData(SelectedPicActivityedPicActivity.picArray);
            LongSparseArray<PicBean> picArray = SelectedPicActivity.picArray;
            for (int i = 0; i < picArray.size(); i++) {
                etNewContent.insertImage(null, picArray.get(picArray.keyAt(i)).getPath());
            }
            SelectedPicActivity.picArray = null;
        }
        if (AddLabelActivity.array != null) {
            SparseArray<DiscussLabelListbean.TagList> arrayTags = AddLabelActivity.array;
            JSONArray array = new JSONArray();
            for (int i = 0; i < arrayTags.size(); i++) {
                JSONObject object = new JSONObject();
                DiscussLabelListbean.TagList bean = arrayTags.get(arrayTags.keyAt(i));
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
            releaseArticleLabelAdapter.setData(AddLabelActivity.array);
            AddLabelActivity.array = null;
        }
    }

    List<RichTextEditor.EditData> list;

    public void setPostSmallImages() {
        postSmallImages = new JSONArray();

        loadingDialog.show();
        list = etNewContent.buildEditData();
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
                .addPart("token", token)
                .addPart("uploadfile ", StringUtil.getMimeType(file.getName()), file)
                .addPart("imgtype", "2")
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
            node.put("totalScore", totalScore);
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
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, BaseRes.class, new HttpCallBackImpl<BaseRes>() {
            @Override
            public void onCompleted(BaseRes str) {
                findViewById(R.id.submit_ok).setVisibility(View.VISIBLE);
                rlTopTheme.setVisibility(View.VISIBLE);
                findViewById(R.id.rl).setVisibility(View.GONE);
                tvName.setText("测评发布成功！");
                mHeadView.setTitle(postTitle);
                tvSubmit.setText("去看看");
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
    }
}
