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
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DiscussLabelListbean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.ReleaseArticleLabelAdapter;
import com.secretk.move.ui.adapter.ReleasePicAdapter;
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
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*发表悬赏*/
public class ReleaseRewardOkActivity extends AppCompatActivity implements ItemClickListener {
    InputMethodManager imm;
    private List<String> picList;
    @BindView(R.id.recycler_pic)
    RecyclerView recycler_pic;
    ReleasePicAdapter releasePicAdapter;
    @BindView(R.id.recycler_horizontal)
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

    @BindView(R.id.ed_content)
    EditText ed_content;
    List<String> serverImgList = new ArrayList<>();
    LoadingDialog loadingDialog;
    int projectId;
    String token = SharedUtils.singleton().get("token", "");
    private JSONArray sonArray;
    private String reward_day;
    private String reward_find;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_reward_ok);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        MoveApplication.getContext().addActivity(this);
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.main_background), 0);
        Bundle reward = getIntent().getBundleExtra("reward");
//        reward_find
//        reward_day
//        reward_time
        reward_find = reward.getString("reward_find");
        reward_day = reward.getString("reward_day");
        String reward_time = reward.getString("reward_time");
        LogUtil.w("reward_find:"+reward_find);
        LogUtil.w("reward_day:"+reward_day);
        LogUtil.w("reward_time:"+reward_time);
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
        StringUtil.etSearchChangedListener(ed_content, null, new StringUtil.EtChange() {
            @Override
            public void etYes() {
                if(getEdContent().length()>9){
                    tv1.setVisibility(View.INVISIBLE);
                }else{
                    tv1.setVisibility(View.VISIBLE);
                    tv1.setText("加油，还差"+(10-getEdContent().length())+"个字");
                }
                tv2.setText(getEdContent().length()+"/300");
            }

            @Override
            public void etNo() {
                tv1.setText("加油，还差10个字");
                tv2.setText("0/300");
            }
        });
        imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        picList = new ArrayList<>();

        releasePicAdapter = new ReleasePicAdapter();
        recycler_pic.setLayoutManager(new GridLayoutManager(this, 3));
        recycler_pic.setAdapter(releasePicAdapter);
        releasePicAdapter.setItemListener(this);
        releasePicAdapter.addData("move");

        releaseArticleLabelAdapter = new ReleaseArticleLabelAdapter();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_horizontal.setLayoutManager(layoutManager);
        recycler_horizontal.setAdapter(releaseArticleLabelAdapter);
        releaseArticleLabelAdapter.setBundle(reward);
//       releaseArticleLabelAdapter.setItemListener(this);

//        ed_title.setHint(Html.fromHtml("请输入标题 <small>(6-60字之间)</small>"));
        loadingDialog = new LoadingDialog(this);
        projectId = getIntent().getIntExtra("projectId", 0);

        beans = new DiscussLabelListbean.TagList();
        beans.setTagId(String.valueOf(projectId));
        beans.setTagName(getIntent().getStringExtra("projectPay"));
        arrayTags.put(-1,beans);
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
        if (adapterImgList!=null && adapterImgList.size()!=0 && adapterImgList.size()>1){
            qiToken = "";
            upImgHttp(adapterImgList.get(0),0);
        }else{
            httpRelease();
        }

    }

    int REQUEST_CODE_CAMERA = 199;
    String picPath;

    @OnClick(R.id.addlabel)
    public void addlabel(View view) {
        Intent intent = new Intent(this, AddLabelActivity.class);
        startActivity(intent);
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
        if(releasePicAdapter.getData().get(postion).equals("move")){
            openChangeHeadDialog();
        }
        if(view.getId()==R.id.img_delect){
            releasePicAdapter.removeIndex(postion);
        }
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
    private void takephoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        picPath = getExternalFilesDir(null).getAbsolutePath() + "/" + System.currentTimeMillis() + ".png";
        Uri uri = Uri.fromFile(new File(picPath));
        //为拍摄的图片指定一个存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }
    @Override
    public void onItemLongClick(View view, int postion) {

    }

    SparseArray<DiscussLabelListbean.TagList> arrayTags = new SparseArray<>();//默认标签
    DiscussLabelListbean.TagList beans;//
    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.w("当前的Class名称:"+ StringUtil.getCurrentClassName(this));
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
            releasePicAdapter.addSparseData(SelectedPicActivity.picArray);
            SelectedPicActivity.picArray=null;
        }

        if(SelectProjectActivity.staticProjectId!=0){
            projectId = SelectProjectActivity.staticProjectId;
            releaseArticleLabelAdapter.amendCode(projectId,SelectProjectActivity.staticProjectCode);
            SelectProjectActivity.staticProjectId=0;
        }
    }

    String discussImages = "";

    public void httpRelease() {

        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", projectId);
            node.put("postTitle", getEdTitle());
            node.put("rewardContents", getEdContent());
            node.put("tagInfos", sonArray.toString());
            node.put("rewardDate", Integer.valueOf(reward_day));
            node.put("rewardMoney", reward_find);
            if (StringUtil.isNotBlank(discussImages)) {
                node.put("discussImages", discussImages);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SAVE_REWARD_ACTIVITY)
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
                            "发表悬赏", getResources().getString(R.string.discuss_reward),getResources().getString(R.string.not_go_look), Constants.PublishSucceed.REWARD);
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
    String qiToken = "";
    String userId = "";
    public void upImgHttp(String path, final int position) {
        if(path.equals("move")){
//            try {
//                generatePostSmallImages(serverImgList);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            return;
        }
        final File file = new File(path);
        if (!file.exists()) {
            ToastUtils.getInstance().show("图片不存在,请重新上传");
            return;
        }
        if (TextUtils.isEmpty(token)) {
            ToastUtils.getInstance().show("请先登录账号");
            return;
        }
        LogUtil.w("当前文件大小："+ PicUtil.getPrintSize(file.length()));
        if(StringUtil.isNotBlank(qiToken) && StringUtil.isNotBlank(userId)){
            NetUtil.sendQiniuImgUrl(file, qiToken, NetUtil.getQiniuImgName("posts",userId,position), new NetUtil.QiniuImgUpload() {
                @Override
                public void uploadStatus(String str, boolean status) {
                    if(status){
                        serverImgList.add(str);
                        if (serverImgList.size() == releasePicAdapter.getItemCount()-1) {
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
                                if (serverImgList.size() == releasePicAdapter.getItemCount()-1) {
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


    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    public String getEdTitle() {
        return ed_title.getText().toString().trim();
    }

    public String getEdContent() {
        return ed_content.getText().toString().trim();
    }
}
