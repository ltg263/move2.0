package com.secretk.move.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MainRfBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.MainRfFragmentRecyclerHolder;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class MainRfFragmentRecyclerAdapter extends RecyclerView.Adapter<MainRfFragmentRecyclerHolder> {
    private List<MainRfBean.Rows> list = new ArrayList<MainRfBean.Rows>();
    private ItemClickListener mListener;
    /**
     * 横向多张图的adapter
     */
    public ImgExtraHorizontalAdapter imgExtraHorizontalAdapter;


    /**
     * 横向多张图layoutManager参数设置
     */
    private LinearLayoutManager layoutManagerImgExtra;
    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public MainRfFragmentRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_rf_recycler_item, parent, false);
        MainRfFragmentRecyclerHolder holder = new MainRfFragmentRecyclerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainRfFragmentRecyclerHolder holder, int position) {
        holder.setItemListener(mListener);
        MainRfBean.Rows bean = list.get(position);

        GlideUtils.loadCircleUrl(holder.img_organization, Constants.BASE_IMG_URL + bean.getProjectIcon());
        holder.tvName.setText(bean.getProjectChineseName());
        holder.tvTime.setText(StringUtil.getTimeToM(bean.getCreateTime()));
        holder.tvIsFollw.setVisibility(View.VISIBLE);
        if (0 == bean.getFollowStatus()) {
            holder.tvIsFollw.setText("+ 关注");
        } else if (1 == bean.getFollowStatus()) {
            holder.tvIsFollw.setText("已关注");
        } else {
            holder.tvIsFollw.setVisibility(View.GONE);
        }
        holder.tvTitle.setText(bean.getPostTitle());
        holder.tvScore.setText(bean.getTotalScore());
        holder.tvDesc.setText(bean.getPostShortDesc());

        GlideUtils.loadCircleUrl(holder.img_user_head, Constants.BASE_IMG_URL + bean.getCreateUserIcon());
        holder.tvUser.setText(bean.getCreateUserName());
        holder.tvPraise.setText(bean.getPraiseNum() + "");
        holder.tvComments.setText(bean.getCommentsNum() + "");

        if (holder.ry_horizontal.getAdapter()==null){
            imgExtraHorizontalAdapter = new ImgExtraHorizontalAdapter();
            //设置成横向
            layoutManagerImgExtra = new LinearLayoutManager(UiUtils.getContext());
            layoutManagerImgExtra.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.ry_horizontal.setAdapter(imgExtraHorizontalAdapter);
            holder.ry_horizontal.setLayoutManager(layoutManagerImgExtra);
        }
        imgExtraHorizontalAdapter.setData(bean);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }


    public void loadHead(List<MainRfBean.Rows> data) {
        if (data == null) return;
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void loadMore(List<MainRfBean.Rows> data) {
        if (list == null) return;
        list.addAll(data);
        notifyDataSetChanged();
    }
    public MainRfBean.Rows getDataIndex(int position){
        return list.get(position);
    }

}
