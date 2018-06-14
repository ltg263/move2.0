package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.MineAssetDetailsBean;
import com.secretk.move.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：收支明细
 */

public class MineAssetDetailsAdapter extends RecyclerView.Adapter<MineAssetDetailsAdapter.DetailsHolder> {


    private List<MineAssetDetailsBean.DataBean.MyTokenBillBean.RowsBean> lists = new ArrayList<>();
    Context context;

    public MineAssetDetailsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public DetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asset_details, parent, false);
        DetailsHolder holder = new DetailsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DetailsHolder holder, final int position) {
        holder.refresh(position, lists);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<MineAssetDetailsBean.DataBean.MyTokenBillBean.RowsBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public void setAddData(List<MineAssetDetailsBean.DataBean.MyTokenBillBean.RowsBean> list) {
        lists.addAll(list);
        notifyDataSetChanged();
    }
    public List<MineAssetDetailsBean.DataBean.MyTokenBillBean.RowsBean> getData() {
        return lists;
    }

    class DetailsHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.tv_asset_name)
        TextView tvAssetName;
        @BindView(R.id.tv_asset_time)
        TextView tvAssetTime;
        @BindView(R.id.tv_asset_num)
        TextView tvAssetNum;

        public DetailsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void refresh(int position, List<MineAssetDetailsBean.DataBean.MyTokenBillBean.RowsBean> lists) {
            MineAssetDetailsBean.DataBean.MyTokenBillBean.RowsBean str = lists.get(position);
            double asset = str.getTradeType();
            //交易类型:1-收入；2-支出
            if (asset==2) {
                tvAssetNum.setTextColor(context.getResources().getColor(R.color.zdmx));
                tvAssetNum.setText("-"+StringUtil.getBeanString(String.valueOf(str.getAmount())));
            } else {
                tvAssetNum.setTextColor(context.getResources().getColor(R.color.app_background));
                tvAssetNum.setText(StringUtil.getBeanString(String.valueOf(str.getAmount())));
            }
            tvAssetName.setText(StringUtil.getBeanString(str.getFunctionDesc()));
            tvAssetTime.setText(StringUtil.getBeanString(str.getCreateTimeStr()));
//            tvAssetTime.setText(StringUtil.getTimeToM(str.getCreateTime()));
        }
    }
}
