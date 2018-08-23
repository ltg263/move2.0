package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DiscussLabelListbean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.EvaluationSimplenessLabelAdapter;
import com.secretk.move.ui.adapter.ReleasePicAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PicUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;
import com.secretk.move.view.EvaluationSliderView;

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
 * 时间：  2018/5/17 19:27
 * 邮箱；ltg263@126.com
 * 描述：简单测评
 */
public class EvaluationSimplenessActivity extends BaseActivity{

    @BindView(R.id.es_viewa)
    EvaluationSliderView esViewa;
    @BindView(R.id.tv_evaluation_state)
    TextView tvEvaluationState;
    @BindView(R.id.et_evaluation_content)
    EditText etEvaluationContent;
    String projectId;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    EvaluationSimplenessLabelAdapter addLabelAdapter;
    ReleasePicAdapter releasePicAdapter;
    @BindView(R.id.recycler_horizontal)
    RecyclerView recyclerHorizontal;
    @BindView(R.id.recycler_pic)
    RecyclerView recyclerPic;
    @BindView(R.id.addlabel)
    RelativeLayout addlabel;
    @BindView(R.id.tv_release)
    TextView tvRelease;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mMenuInfos.add(0, new MenuInfo(R.string.evaluation_professional, getString(R.string.evaluation_professional), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_evaluation_simpleness;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

        tvProjectCode.setText(getIntent().getStringExtra("projectPay"));
        projectId = getIntent().getStringExtra("projectId");
        StringUtil.etSearchChangedListener(etEvaluationContent, null, new StringUtil.EtChange() {
            @Override
            public void etYes() {
                if (getEdContent().length() > 9) {
                    tv1.setVisibility(View.INVISIBLE);
                } else {
                    tv1.setVisibility(View.VISIBLE);
                    tv1.setText("加油，还差" + (10 - getEdContent().length()) + "个字");
                }
                tv2.setText(getEdContent().length() + "/300");
            }

            @Override
            public void etNo() {
                tv1.setText("加油，还差10个字");
                tv2.setText("0/300");
            }
        });
        addlabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EvaluationSimplenessActivity.this, AddLabelActivity.class);
                startActivity(intent);
            }
        });
        tvProjectCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EvaluationSimplenessActivity.this, SelectProjectActivity.class);
                intent.putExtra("publication_type",4);
                startActivity(intent);
            }
        });
        recyclerHorizontal.setLayoutManager(new GridLayoutManager(this, 4));
        addLabelAdapter = new EvaluationSimplenessLabelAdapter();
        recyclerHorizontal.setAdapter(addLabelAdapter);
        addLabelAdapter.setItemListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                selectLable(postion);
            }

            @Override
            public void onItemLongClick(View view, int postion) {}
        });

        recyclerPic.setLayoutManager(new GridLayoutManager(this, 3));
        releasePicAdapter = new ReleasePicAdapter();
        recyclerPic.setAdapter(releasePicAdapter);
        releasePicAdapter.setItemListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if(releasePicAdapter.getData().get(postion).equals("move")){
                    openChangeHeadDialog();
                }
                if(view.getId()==R.id.img_delect){
                    releasePicAdapter.removeIndex(postion);
                }
            }

            @Override
            public void onItemLongClick(View view, int postion) {}
        });
        releasePicAdapter.addData("move");

        mHeadView.setTitle(getIntent().getStringExtra("projectPay") + "-" + getString(R.string.evaluation_simpleness));
        esViewa.setScore(8.0f);
        esViewa.setEsvBackground(R.color.app_background);
        tvEvaluationState.setText(StringUtil.getStateValueStr(8.0f));
    }

    public void setTvEvaluationState(String value) {
        tvEvaluationState.setText(value);
    }

    @Override
    protected void initData() {
        MoveApplication.getContext().addActivity(this);
        initTagList();
    }

    @Override
    protected void OnToolbarRightListener() {
//        Intent intent = new Intent(this, EvaluationProfessionalActivity.class);
//        intent.putExtra("projectId", Integer.valueOf(projectId));
//        intent.putExtra("projectIcon", getIntent().getStringExtra("projectIcon"));
//        intent.putExtra("projectName", getIntent().getStringExtra("projectName"));
//        intent.putExtra("projectPay", getIntent().getStringExtra("projectPay"));
//        startActivity(intent);

        IntentUtil.startProjectCompileDxZjyActivity(String.valueOf(Constants.ModelType.MODEL_TYPE_ALL_NEW),
                String.valueOf(projectId), getIntent().getStringExtra("projectPay"),
                "", "8.0", getString(R.string.comprehensive_evaluation));
    }

    SparseArray<DiscussLabelListbean.TagList> arrayTags = new SparseArray<>();//默认标签
    DiscussLabelListbean.TagList beans;//
    @Override
    protected void onResume() {
        super.onResume();
        if (SelectedPicActivity.picArray != null) {
            releasePicAdapter.addSparseData(SelectedPicActivity.picArray);
            SelectedPicActivity.picArray=null;
        }
        if(SelectProjectActivity.staticProjectId!=0){
            projectId = String.valueOf(SelectProjectActivity.staticProjectId);
            tvProjectCode.setText(SelectProjectActivity.staticProjectCode);
            SelectProjectActivity.staticProjectCode="";
            SelectProjectActivity.staticProjectId=0;
        }
    }

    String discussImages = "";
    List<String> adapterImgList;
    List<String> serverImgList = new ArrayList<>();
    JSONArray sonArray;
    @OnClick(R.id.tv_release)
    public void onViewClicked() {

        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        if (StringUtil.isBlank(etEvaluationContent.getText().toString().trim())) {
            ToastUtils.getInstance().show("评测内容不能为空");
            return;
        }
        if (etEvaluationContent.getText().toString().trim().length() < 10) {
            ToastUtils.getInstance().show("评测字数最少10个字");
            return;
        }
        // tagId,tagName
        if(arrayTags!=null){
            sonArray = new JSONArray();
            for(int i=0;i<arrayTags.size();i++){
                JSONObject object = new JSONObject();
                DiscussLabelListbean.TagList aa = arrayTags.get(arrayTags.keyAt(i));
                try {
                    object.put("tagId",aa.getTagId());
                    object.put("tagName",aa.getTagName());
                    sonArray.put(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        loadingDialog.show();
        discussImages="";
        adapterImgList= releasePicAdapter.getData();
        if (adapterImgList!=null&&adapterImgList.size()!=0 && adapterImgList.size()>1){
            qiToken = "";
            upImgHttp(adapterImgList.get(0),0);
        }else{
            httpRelease();
        }
    }

    String qiToken = "";
    String userId = "";
    public void upImgHttp(String path, final int position) {
        if(path.equals("move")){
            try {
                generatePostSmallImages(serverImgList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        }
        final File file = new File(path);
        if (!file.exists()) {
            ToastUtils.getInstance().show("图片不存在");
            loadingDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(token)) {
            ToastUtils.getInstance().show("请先登录账号");
            loadingDialog.dismiss();
            return;
        }
        LogUtil.w("当前文件大小："+ PicUtil.getPrintSize(file.length()));
        if(StringUtil.isNotBlank(qiToken) && StringUtil.isNotBlank(userId)){
            NetUtil.sendQiniuImgUrl(file, qiToken, NetUtil.getQiniuImgName("posts",userId,position), new NetUtil.QiniuImgUpload() {
                @Override
                public void uploadStatus(String str, boolean status) {
                    if(status){
                        serverImgList.add(str);
                        if (serverImgList.size() == releasePicAdapter.getItemCount()) {
                            try {
                                generatePostSmallImages(serverImgList);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            upImgHttp(adapterImgList.get(position+1),position+1);
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
                    NetUtil.sendQiniuImgUrl(file, mQiToken, NetUtil.getQiniuImgName("avatars",mUserId,0), new NetUtil.QiniuImgUpload() {
                        @Override
                        public void uploadStatus(String str, boolean status) {
                            if(status){
                                serverImgList.add(str);
                                if (serverImgList.size() == releasePicAdapter.getItemCount()) {
                                    try {
                                        generatePostSmallImages(serverImgList);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else {
                                    upImgHttp(adapterImgList.get(position+1),position+1);
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
        }
        if(array.length()>0){
            discussImages = array.toString();
        }
        httpRelease();
    }

    private void httpRelease() {

        Float num = Float.valueOf(esViewa.getTvEvaluationMun());
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            //针对某个项目发表文章
            node.put("projectId", Integer.valueOf(projectId));
            //1-简单评测；2-全面系统专业评测;3-部分系统专业评测；4-专业评测-自定义类型
            node.put("modelType", 1);
            //	 modelType=1对应为值为“简单评测", 2 为 "ALL-专业评测" 3 为 "PART—项目立项、核心团队" 4 为 "ALL-专业评测"
            node.put("postTitle", "简单评测");
            //精确到小数点1位。简单评测 和部分评测 需要给出此值；ALL-专业评测 可以不用给。
            node.put("totalScore", num);
            //包含 fileName,fileUrl,size,extension 信息的json数组,最多3个
//            node.put("discussImages", token);
            node.put("evauationContent", etEvaluationContent.getText().toString().trim());
            //包含 modelId , modelName, score 三项的json数组
//            node.put("discussImages", token);
            //包含 tagId,tagName 的json数组，数量最多3个
//            node.put("professionalEvaDetail", token);
            //包含 fileName,fileUrl,size,extension 信息的json数组,最多3个
            if (StringUtil.isNotBlank(discussImages)) {
                node.put("postSmallImages", discussImages);
            }
            //包含 tagId,tagName 的json数组，
            if(sonArray.length()!=0){
                node.put("evaluationTags", sonArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                            getString(R.string.evaluation_simpleness), getResources().getString(R.string.evaluation_succeed),
                            getResources().getString(R.string.not_go_look), Constants.PublishSucceed.EVALUATION);
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


    public String getEdContent() {
        return etEvaluationContent.getText().toString().trim();
    }


    private void selectLable(int postion) {
        DiscussLabelListbean.TagList bean = addLabelAdapter.getDataIndex(postion);
        if (bean.getSelected()) {
            bean.setSelected(false);
            arrayTags.remove(Integer.parseInt(bean.getTagId()));
        } else {
            if(arrayTags.size()==3){
                ToastUtils.getInstance().show("最多选三个");
                return;
            }
            bean.setSelected(true);
            arrayTags.put(Integer.parseInt(bean.getTagId()),bean);
        }
        addLabelAdapter.notifyDataSetChanged();
    }

    private void openChangeHeadDialog() {
        if (releasePicAdapter.getItemCount() > 9) {
            ToastUtils.getInstance().show("最多选择九张图片");
            return;
        }
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
        intent.putExtra("max_pic", 9);
        intent.putExtra("current_pic", releasePicAdapter.getItemCount()-1);
        startActivity(intent);
    }
    int REQUEST_CODE_CAMERA = 199;
    String picPath;
    private void takephoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        picPath = getExternalFilesDir(null).getAbsolutePath() + "/" + System.currentTimeMillis() + ".png";
        Uri uri = Uri.fromFile(new File(picPath));
        //为拍摄的图片指定一个存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
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

    public void initTagList() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.RELEASE_DISCUSS_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, DiscussLabelListbean.class, new HttpCallBackImpl<DiscussLabelListbean>() {
            @Override
            public void onCompleted(DiscussLabelListbean bean) {
                int code = bean.getCode();
                List<DiscussLabelListbean.TagList> lists = bean.getData().getTagList();
                if (code == 0) {
                    addLabelAdapter.setData(lists);
                }
            }
        });
    }
}
