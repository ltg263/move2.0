package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.secretk.move.R;
import com.secretk.move.listener.ItemClickListener;
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

/*发表文章*/
public class ReleaseArticleActivity extends AppCompatActivity implements ItemClickListener {
    InputMethodManager imm;
    private List<String> picList;
    @BindView(R.id.recycler_pic)
    RecyclerView recycler_pic;
    ReleasePicAdapter releasePicAdapter;
    @BindView(R.id.recycler_horizontal)
    RecyclerView recycler_horizontal;

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

        recycler_pic.setLayoutManager(new GridLayoutManager(this, 3));
        releasePicAdapter = new ReleasePicAdapter();
        recycler_pic.setAdapter(releasePicAdapter);
        releasePicAdapter.setItemListener(this);
    }

    @OnClick(R.id.img_return)
    public void img_return() {
        finish();
    }

    @OnClick(R.id.tv_release)
    public void tv_release() {

    }

    public void localphoto(View view) {

    }

    int REQUEST_CODE_CAMERA = 199;
    String picPath;
    public void takephoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        picPath= getExternalFilesDir(null).getAbsolutePath()+ "/crashinfo/"+System.currentTimeMillis()+".png";
        Uri uri = Uri.fromFile(new File(picPath));
        //为拍摄的图片指定一个存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }
    int REQUEST_CODE_ADD_LABEL = 198;
    public void addlabel(View view) {
        Intent intent = new Intent(this, AddLabelActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_LABEL);
    }

    public void swithKeyboard(View view) {
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    // takephoto addlabel
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            File file=  new File(picPath);
            if (file.exists()){
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
}
