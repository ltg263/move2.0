package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.DistributedList;
import com.secretk.move.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：发放中 各种明细
 */

public class MineAssetDistributeAdapter extends RecyclerView.Adapter<MineAssetDistributeAdapter.DetailsHolder> {

    private List<DistributedList.DataBean.InDistributionBean> lists = new ArrayList<>();
    Context context;

    public MineAssetDistributeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public DetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asset_distrbuted, parent, false);
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

    public void setData(List<DistributedList.DataBean.InDistributionBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public List<DistributedList.DataBean.InDistributionBean> getData() {
        return lists;
    }

    class DetailsHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.pb_max)
        ProgressBar pbMax;
        @BindView(R.id.tv_max)
        TextView tvMax;

        public DetailsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void refresh(int position, List<DistributedList.DataBean.InDistributionBean> lists) {
            DistributedList.DataBean.InDistributionBean str = lists.get(position);
            pbMax.setProgress(str.getCounter());
            tvTime.setText(StringUtil.getTimeToM(str.getCreateTime()));
            tvName.setText(StringUtil.getBeanString(str.getTokenAwardFunctionDesc()));
            tvNum.setText(StringUtil.getBeanString(String.valueOf(str.getInviteRewards())));
            tvMax.setText(StringUtil.getBeanString(String.valueOf(str.getCounter()))+"%");
        }
    }
}
