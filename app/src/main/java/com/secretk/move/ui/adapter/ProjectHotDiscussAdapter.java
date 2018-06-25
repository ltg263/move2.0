package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.RowsBean;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：图片九宫格的ITEM
 */

public class ProjectHotDiscussAdapter extends RecyclerView.Adapter<ProjectHotDiscussAdapter.ImagesHolder> {


    private List<RowsBean> lists = new ArrayList<>();
    Context context;

    public ProjectHotDiscussAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ImagesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_discuss, parent, false);
        ImagesHolder holder = new ImagesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImagesHolder holder, final int position) {
        holder.refresh(position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<RowsBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public List<RowsBean> getData() {
        return lists;
    }

    class ImagesHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.iv_commented_user_icon)
        ImageView ivCommentedUserIcon;
        @BindView(R.id.tv_commented_user_name)
        TextView tvCommentedUserName;
        @BindView(R.id.tv_create_time)
        TextView tvCreateTime;
        @BindView(R.id.tv_praise_num)
        TextView tvPraiseNum;
        @BindView(R.id.tv_comment_content)
        TextView tvCommentContent;

        public ImagesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        RowsBean hotDiscussBean;
        public void refresh(int position) {
             hotDiscussBean = lists.get(position);
            GlideUtils.loadCircleUserUrl(context,ivCommentedUserIcon, Constants.BASE_IMG_URL+ StringUtil.getBeanString(hotDiscussBean.getCreateUserIcon()));
            tvCommentedUserName.setText(StringUtil.getBeanString(hotDiscussBean.getCreateUserName()));
            tvCreateTime.setText(StringUtil.getBeanString(String.valueOf(hotDiscussBean.getTotalScore())));
            tvPraiseNum.setText(String.valueOf(hotDiscussBean.getPraiseNum()));
            //"praiseStatus":0,//点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
            if(hotDiscussBean.getPraiseStatus()==1){
                tvPraiseNum.setSelected(false);
            }else if(hotDiscussBean.getPraiseStatus()==0){
                tvPraiseNum.setSelected(true);
            }else if(hotDiscussBean.getPraiseStatus()==2){
                tvPraiseNum.setSelected(false);
            }
            tvPraiseNum.setText(String.valueOf(hotDiscussBean.getPraiseNum()));
            tvCommentContent.setText(StringUtil.getBeanString(hotDiscussBean.getEvauationContent()));
            tvPraiseNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!tvPraiseNum.isSelected()){
                        return;
                    }
                    if (!NetUtil.isPraise(hotDiscussBean.getCreateUserId(), SharedUtils.getUserId())){
                        return;
                    }
                    tvPraiseNum.setEnabled(false);
                    setPraise(tvPraiseNum,hotDiscussBean.getPostId());
                }
            });
        }
        private void setPraise(TextView finalIsLove, int commentsId) {
            final int praiseNumA = hotDiscussBean.getPraiseNum();
            final String strNum;
            if(tvPraiseNum.isSelected()){
                strNum = String.valueOf(praiseNumA +1);
            }else{
                strNum = String.valueOf(praiseNumA -1);
            }
            tvPraiseNum.setText(strNum);
            tvPraiseNum.setSelected(!tvPraiseNum.isSelected());
            NetUtil.addCommentsPraise(!finalIsLove.isSelected(), commentsId, new NetUtil.SaveFollowImpl() {
                @Override
                public void finishFollow(String praiseNum,boolean status) {
                    tvPraiseNum.setEnabled(true);
                    if(!praiseNum.equals(Constants.PRAISE_ERROR)){
                        hotDiscussBean.setPraiseNum(Integer.valueOf(praiseNum));
                        tvPraiseNum.setText(praiseNum);
//                    praiseNumA = commentsBean.getPraiseNum();
                        tvPraiseNum.setSelected(status);
                        ////点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
                        hotDiscussBean.setPraiseStatus(status?0:1);
                    }else{
//                    tvPraiseNum.setSelected(false);
//                    tvPraiseNum.setText(String.valueOf(Integer.valueOf(strNum)-1));
                    }
                }
            });
        }
    }

}
