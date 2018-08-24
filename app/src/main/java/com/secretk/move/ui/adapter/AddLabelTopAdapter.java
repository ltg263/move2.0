package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.base.TagsAndTagtbean;
import com.secretk.move.bean.DiscussLabelListbean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.AddLabelActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class AddLabelTopAdapter extends RecyclerView.Adapter<AddLabelTopAdapter.ViewHolder> {
    private List<TagsAndTagtbean.DataBean.ResultBean> list = new ArrayList<>();
    private ItemClickListener mListener;
    Context mContext;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_label_top, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        TagsAndTagtbean.DataBean.ResultBean resultBean = list.get(position);
        holder.tvName.setText(resultBean.getTypeName());
        if(position % 2 ==0){
            GlideUtils.loadUrlDd(mContext,holder.ivImg,R.drawable.ic_label_red);
        }else{
            GlideUtils.loadUrlDd(mContext,holder.ivImg,R.drawable.ic_label_lv);
        }
        List<TagsAndTagtbean.DataBean.ResultBean.DtagsListBean> listBeans = resultBean.getDtagsList();
        final List<DiscussLabelListbean.TagList> tagList = new ArrayList<>();
        for(int i=0;i<listBeans.size();i++){
            TagsAndTagtbean.DataBean.ResultBean.DtagsListBean b = listBeans.get(i);
            DiscussLabelListbean.TagList tag = new DiscussLabelListbean.TagList();
            tag.setTagName(b.getTagName());
            tag.setTagId(String.valueOf(b.getTagId()));
            tagList.add(tag);
        }
        holder.adapter.setData(tagList);

        holder.adapter.setItemListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int mPosition) {
                selectAddLabel(tagList.get(mPosition));
                holder.adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<TagsAndTagtbean.DataBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void addData(TagsAndTagtbean.DataBean.ResultBean str) {
       list.add(1,str);
        notifyDataSetChanged();
    }
    public TagsAndTagtbean.DataBean.ResultBean getDataIndex(int index){
        return list.get(index);
    }
    public class ViewHolder extends RecyclerViewBaseHolder {

        @BindView(R.id.recycler)
        RecyclerView recycler;
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        AddLabelActivityRecyclerAdapter adapter;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            AddLabelActivity.array = new SparseArray<>();
            recycler.setLayoutManager(new GridLayoutManager(mContext, 3));
            adapter = new AddLabelActivityRecyclerAdapter();
            recycler.setAdapter(adapter);
        }
    }

    private void selectAddLabel(DiscussLabelListbean.TagList bean) {
        if (bean.getSelected()) {
            bean.setSelected(false);
            AddLabelActivity.array.remove(Integer.parseInt(bean.getTagId()));
        } else {
            if (AddLabelActivity.array.size()>=3){
                return;
            }
            bean.setSelected(true);
            AddLabelActivity.array.put(Integer.parseInt(bean.getTagId()),bean);
        }
        if (AddLabelActivity.array.size()>3){
            ToastUtils.getInstance().show("最多选三个");
        }else {
//          tv_selected.setText("已选"+array.size()+"个");
        }
//      }
    }
}
