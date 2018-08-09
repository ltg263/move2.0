package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.FindRankingUserBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class FindRankingUserAdapter extends RecyclerView.Adapter<FindRankingUserAdapter.FindRankingUserHolder> {

    private List<FindRankingUserBean.DataBean.KFFUserPageBean.RowsBean> list = new ArrayList<>();
    private ItemClickListener mListener;
    Context context;
    int index;

    public FindRankingUserAdapter(Context context) {
        this.context = context;
    }

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public FindRankingUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_ranking, parent, false);
        FindRankingUserHolder holder = new FindRankingUserHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FindRankingUserHolder holder, int position) {
        holder.setItemListener(mListener);
        FindRankingUserBean.DataBean.KFFUserPageBean.RowsBean bean = list.get(position);
        holder.setData(bean, position, context);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setAddData(List<FindRankingUserBean.DataBean.KFFUserPageBean.RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setData(List<FindRankingUserBean.DataBean.KFFUserPageBean.RowsBean> list, int index) {
        this.list = list;
        this.index = index;
        notifyDataSetChanged();
    }

    public FindRankingUserBean.DataBean.KFFUserPageBean.RowsBean getDataIndex(int position) {
        return list.get(position);
    }

    class FindRankingUserHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.rl_ge_ren)
        RelativeLayout rlGeRen;
        @BindView(R.id.iv_move)
        ImageView ivMove;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_move)
        TextView tvMove;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_name_f)
        TextView tvNameF;
        @BindView(R.id.tv_score)
        TextView tvScore;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_project_folly)
        TextView tvProjectFolly;

        public FindRankingUserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(final FindRankingUserBean.DataBean.KFFUserPageBean.RowsBean bean, int position, final Context context) {
            if (position == 0) {
                ivMove.setImageDrawable(context.getResources().getDrawable(R.drawable.topic_one));
                ivMove.setVisibility(View.VISIBLE);
                tvMove.setVisibility(View.GONE);
            } else if (position == 1) {
                ivMove.setImageDrawable(context.getResources().getDrawable(R.drawable.topic_two));
                ivMove.setVisibility(View.VISIBLE);
                tvMove.setVisibility(View.GONE);
            } else if (position == 2) {
                ivMove.setImageDrawable(context.getResources().getDrawable(R.drawable.topic_three));
                ivMove.setVisibility(View.VISIBLE);
                tvMove.setVisibility(View.GONE);
            } else {
                tvMove.setText(String.valueOf(position + 1));
                ivMove.setVisibility(View.INVISIBLE);
                tvMove.setVisibility(View.VISIBLE);
            }
            if (index != 1) {
                GlideUtils.loadCircleUserUrl(context, ivIcon, Constants.BASE_IMG_URL + StringUtil.getBeanString(bean.getProjectIcon()));
                tvName.setText(StringUtil.getBeanString(bean.getProjectCode()));
                tvNameF.setText("/" + StringUtil.getBeanString(bean.getProjectChineseName()));
                tvNum.setText(String.valueOf(bean.getFollowerNum()));
                if (bean.getFollowStatus() == 1) { //关注状态  "//0 未关注；1-已关注；2-不显示关注按钮"
                    tvProjectFolly.setSelected(true);
                    tvProjectFolly.setText(context.getString(R.string.follow_status_1));
                } else if (bean.getFollowStatus() == 0) {
                    tvProjectFolly.setSelected(false);
                    tvProjectFolly.setText(context.getString(R.string.follow_status_0));
                }
                tvScore.setText(String.valueOf(bean.getTotalScore()));
                tvScore.setVisibility(View.VISIBLE);
//                tvNameF.setVisibility(View.VISIBLE);
            } else {
                GlideUtils.loadCircleUserUrl(context, ivIcon, Constants.BASE_IMG_URL + StringUtil.getBeanString(bean.getIcon()));
                tvName.setText(StringUtil.getBeanString(bean.getUserName()));
                tvNum.setText(String.valueOf(bean.getFansNum()));
                if (bean.getFollowStatus() == 1) { //关注状态  "//0 未关注；1-已关注；2-不显示关注按钮"
                    tvProjectFolly.setSelected(true);
                    tvProjectFolly.setText(context.getString(R.string.follow_status_1));
                } else if (bean.getFollowStatus() == 0) {
                    tvProjectFolly.setSelected(false);
                    tvProjectFolly.setText(context.getString(R.string.follow_status_0));
                }
                tvScore.setVisibility(View.INVISIBLE);
                tvNameF.setVisibility(View.INVISIBLE);
            }
            tvProjectFolly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvProjectFolly.setEnabled(false);
                    if(index==1){
                        NetUtil.addSaveFollow(tvProjectFolly,
                                Constants.SaveFollow.USER,bean.getUserId(), new NetUtil.SaveFollowImp() {
                                    @Override
                                    public void finishFollow(String str) {
                                        // 0 显示 关注按钮； 1--显示取消关注 按钮 ；2 不显示按钮
                                        tvProjectFolly.setEnabled(true);
                                        if(!str.equals(Constants.FOLLOW_ERROR)){
                                            tvProjectFolly.setText(str);
                                        }
                                    }
                                });
                        return;
                    }
                    NetUtil.addSaveFollow(tvProjectFolly,
                            Constants.SaveFollow.PROJECT,bean.getProjectId(), new NetUtil.SaveFollowImp() {
                                @Override
                                public void finishFollow(String str) {
                                    // 0 显示 关注按钮； 1--显示取消关注 按钮 ；2 不显示按钮
                                    tvProjectFolly.setEnabled(true);
                                    if(!str.equals(Constants.FOLLOW_ERROR)){
                                        tvProjectFolly.setText(str);
                                    }
                                }
                            });
                }
            });
            rlGeRen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(index==1){
                        IntentUtil.startHomeActivity(bean.getUserId());
                    }else{
                        IntentUtil.startProjectActivity(bean.getProjectId());
                    }
                }
            });
        }
    }
}
