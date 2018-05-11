package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectedPicActivity extends AppCompatActivity {
    @BindView(R.id.tv_release)
    TextView tv_release;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    SelectedPicAdaper selectedPicAdaper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_pic);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        recycler.setLayoutManager(new GridLayoutManager(this,4));
        selectedPicAdaper=new SelectedPicAdaper();
        recycler.setAdapter(selectedPicAdaper);
    }

    @OnClick(R.id.img_return)
    public void img_return() {
        finish();
    }

    @OnClick(R.id.tv_release)
    public void tv_release() {

    }
    public class SelectedPicAdaper extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
    public class ViewHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.img)
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
