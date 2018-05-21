package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.PicBean;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Files.FileColumns;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class SelectedPicActivity extends AppCompatActivity {
    @BindView(R.id.tv_release)
    TextView tv_release;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    SelectedPicAdaper selectedPicAdaper;

    private Uri uri;
    private String selection = null;
    private String[] selectionArgs = null;
    private String data = null;
    private ArrayList<PicBean> list = new ArrayList<PicBean>();
    public static LongSparseArray<PicBean> picArray = new LongSparseArray<>();
private int maxPicNum;
private int lastActivityPicNum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_pic);
        ButterKnife.bind(this);
        init();
        initPic();
    }

    private void init() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.main_background), 0);
        recycler.setLayoutManager(new GridLayoutManager(this, 4));
        selectedPicAdaper = new SelectedPicAdaper();
        recycler.setAdapter(selectedPicAdaper);
        maxPicNum=   getIntent().getIntExtra("max_pic",3);
        lastActivityPicNum= getIntent().getIntExtra("current_pic",0);
        picArray.clear();
    }

    private void initPic() {
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        data = MediaStore.Images.Media.DATA;
        selection = null;
        selectionArgs = null;

        list = new ArrayList<PicBean>();
        PicBean bean;
        ContentResolver mContentResolver = getContentResolver();
        Cursor cursor = mContentResolver.query(uri, null, selection,
                selectionArgs, MediaStore.MediaColumns.DATE_MODIFIED);
        if (cursor.moveToFirst()) {
            do {
                String url = cursor.getString(cursor
                        .getColumnIndexOrThrow(data));


                Long id = cursor
                        .getLong(cursor
                                .getColumnIndexOrThrow(FileColumns._ID));
                File file = new File(url);
                Long size = file.length();
                bean = new PicBean();
                String[] ss = url.split("/");
                bean.setName(ss[ss.length - 1]);
                bean.setPath(url);
                bean.setTime_compare(file.lastModified());
                bean.setSize(size);
                bean.setId(id);
                list.add(bean);
            } while (cursor.moveToNext());
        }
        Collections.sort(list);
        selectedPicAdaper.setData();
    }

    @OnClick(R.id.img_return)
    public void img_return() {
        finish();
    }

    @OnClick(R.id.tv_release)
    public void tv_release() {

    }

    public class SelectedPicAdaper extends RecyclerView.Adapter<ViewHolder> {
        private Context mContext;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            mContext = parent.getContext();
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_selected_pic_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final   PicBean bean = list.get(position);
            GlideUtils.loadImage(mContext, holder.img, bean.getPath());
            if (picArray.get(bean.getId()) != null) {
                Glide.with(mContext).
                        load(R.drawable.check_checked).into(holder.check);
            } else {
                Glide.with(mContext).
                        load(R.drawable.check_normal).into(holder.check);
            }
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (picArray.size()+lastActivityPicNum>=maxPicNum){
                        ToastUtils.getInstance().show("最多选择"+maxPicNum+"张照片");
                        return;
                    }
                    if (picArray.get(bean.getId()) == null) {
                        picArray.put(bean.getId(), bean);
                    } else {
                        picArray.delete(bean.getId());
                    }
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void setData() {
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.img)
        public ImageView img;
        @BindView(R.id.check)
        public ImageView check;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
