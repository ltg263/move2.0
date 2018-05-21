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
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.secretk.move.R;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.ReleaseArticleLabelAdapter;
import com.secretk.move.ui.adapter.ReleasePicAdapter;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*发表讨论*/
public class ReleaseDiscussActivity extends AppCompatActivity  implements ItemClickListener {
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_discuss);
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
    }
    @OnClick(R.id.img_return)
    public void img_return() {
        finish();
    }

    @OnClick(R.id.tv_release)
    public void tv_release() {

    }
    @OnClick(R.id.localphoto)
    public void localphoto(View view) {
        Intent intent = new Intent(this, SelectedPicActivity.class);
        intent.putExtra("max_pic",9);
        intent.putExtra("current_pic",releasePicAdapter.getItemCount());
        startActivity(intent);
    }

    int REQUEST_CODE_CAMERA = 199;
    String picPath;
    @OnClick(R.id.takephoto)
    public void takephoto(View view) {
        if (releasePicAdapter.getItemCount()>=9){
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

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AddLabelActivity.array != null) {
            releaseArticleLabelAdapter.setData(AddLabelActivity.array);
            AddLabelActivity.array=null;
        }
        if (true){

        }
    }
}
