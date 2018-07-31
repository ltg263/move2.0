package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.FindWksyList;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：挖矿收益列表 item
 */

public class FindWksyAdapter extends RecyclerView.Adapter<FindWksyAdapter.FindWksyHolder> {

    private List<FindWksyList.DataBeanX.DataBean.RowsBean> lists = new ArrayList<>();
    private Context mContext;

    public FindWksyAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public FindWksyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_wksy, parent, false);
        FindWksyHolder holder = new FindWksyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FindWksyHolder holder, int position) {
        holder.refresh(position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<FindWksyList.DataBeanX.DataBean.RowsBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public void setAddData(List<FindWksyList.DataBeanX.DataBean.RowsBean> list) {
        lists.addAll(list);
        notifyDataSetChanged();
    }

    public List<FindWksyList.DataBeanX.DataBean.RowsBean> getData() {
        return lists;
    }

    class FindWksyHolder extends RecyclerViewBaseHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_code)
        TextView tvCode;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.ll_project_info)
        LinearLayout llProjectInfo;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        public FindWksyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void refresh(int position) {
            FindWksyList.DataBeanX.DataBean.RowsBean bean = lists.get(position);
            tvTitle.setText(StringUtil.getBeanString(bean.getTitle()));
            tvTime.setText(StringUtil.getTimeToM(bean.getCreatedAt()));
            GlideUtils.loadCircleProjectUrl(mContext,ivIcon, Constants.BASE_IMG_URL+bean.getProjectIcon());
            tvCode.setText(StringUtil.getBeanString(bean.getProjectCode()));
            tvName.setText("/"+StringUtil.getBeanString(bean.getProjectChineseName()));
            tvNum.setText(String.valueOf(bean.getReward()));
            //"status": 0,//状态：0-待发放，1-已发放，2-发放失败
            if(bean.getStatus()==0){
                tvStatus.setText("待发放");
            }
            if(bean.getStatus()==1){
                tvStatus.setText("已发放");
            }
            if(bean.getStatus()==2){
                tvStatus.setText("发放失败");
            }
        }
    }
}
