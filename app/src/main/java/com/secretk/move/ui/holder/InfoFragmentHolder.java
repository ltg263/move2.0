package com.secretk.move.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.MessageBean;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.ShareView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class InfoFragmentHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.tv_top_time)
    TextView tvTopTime;
    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.iv_dot)
    ImageView ivDot;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.tv_detail_desc)
    TextView tvDetailDesc;
    @BindView(R.id.tv_info_z)
    TextView tvInfoZ;
    @BindView(R.id.tv_info_j)
    TextView tvInfoJ;
    @BindView(R.id.tv_info_share)
    TextView tvInfoShare;
    int position;
    public InfoFragmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getInstance().show("跳转详情"+position);
            }
        });
        tvInfoZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getInstance().show("看涨"+position);
            }
        });
        tvInfoJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getInstance().show("看跌"+position);
            }
        });
        tvInfoShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareView.showShare("skipRrl", "title","content","imgUrl");
            }
        });
    }

    public void refresh(Context context, int position, List<MessageBean.DataBean.MessagesBean.RowsBean> list) {
        this.position = position;
        tvTopTime.setText("2018.06.04   星期一");
        tvTime.setText("14:48");
        tvHeadTitle.setText("真的跑路了，完全联系不上啊！！！");
        tvDetailDesc.setText("区块链是分布式数据存储、点对点传输、共识机制加密算法等计算机技术的新型应用模式。所谓共识机制是区块链系统中实现不同节点");
        if(position%2==1){
            tvInfoZ.setSelected(true);
            tvInfoJ.setSelected(false);
        }else{
            tvInfoZ.setSelected(false);
            tvInfoJ.setSelected(true);
        }
        tvInfoZ.setText("看涨345");
        tvInfoJ.setText("看跌643");
        if(tvTopTime.getVisibility()==View.VISIBLE){
            viewTop.setVisibility(View.GONE);
        }else{
            viewTop.setVisibility(View.VISIBLE);
        }
    }

}
