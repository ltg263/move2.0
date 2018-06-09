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
import com.secretk.move.bean.DetailsUserGradeBean;
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
 * 描述：图片九宫格的ITEM
 */

public class ProjectHotDiscussAdapter extends RecyclerView.Adapter<ProjectHotDiscussAdapter.ImagesHolder> {


    private List<DetailsUserGradeBean.DataBean.HotDiscussBean> lists = new ArrayList<>();
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

    public void setData(List<DetailsUserGradeBean.DataBean.HotDiscussBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public List<DetailsUserGradeBean.DataBean.HotDiscussBean> getData() {
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

        public void refresh(int position) {
            DetailsUserGradeBean.DataBean.HotDiscussBean hotDiscussBean = lists.get(position);
            GlideUtils.loadCircleUserUrl(context,ivCommentedUserIcon, Constants.BASE_IMG_URL+ StringUtil.getBeanString(hotDiscussBean.getCreateUserIcon()));
            tvCommentedUserName.setText(StringUtil.getBeanString(hotDiscussBean.getCreateUserName()));
            tvCreateTime.setText(StringUtil.getBeanString(String.valueOf(hotDiscussBean.getTotalScore())));
            tvPraiseNum.setText(String.valueOf(hotDiscussBean.getPraiseNum()));
            //"praiseStatus":0,//点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
            if(hotDiscussBean.getFollowStatus()==1){
                tvPraiseNum.setSelected(false);
            }else if(hotDiscussBean.getFollowStatus()==0){
                tvPraiseNum.setSelected(true);
            }else if(hotDiscussBean.getFollowStatus()==3){
                tvPraiseNum.setVisibility(View.GONE);
            }
            tvCommentContent.setText(StringUtil.getBeanString(hotDiscussBean.getDisscussContents()));
        }
    }
}
