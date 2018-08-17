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
import com.secretk.move.bean.MoreCommentsBean;
import com.secretk.move.ui.activity.MoreCommentsActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.TimeToolUtils;
import com.secretk.move.view.Clickable;
import com.secretk.move.view.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/5/2 16:48
 * 邮箱；ltg263@126.com
 * 描述：讨论详情页 单个评论详情页Item
 */

public class MoreCommentsAdapter extends RecyclerView.Adapter<MoreCommentsAdapter.MoreCommentsHolder> {

    private List<MoreCommentsBean.DataBean.CommentsBean.RowsBean> lists = new ArrayList<>();
    Context context;
    int parentUserId;

    public MoreCommentsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MoreCommentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discuss_single, parent, false);
        MoreCommentsHolder holder = new MoreCommentsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MoreCommentsHolder holder, int position) {
        holder.refresh(position, lists);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<MoreCommentsBean.DataBean.CommentsBean.RowsBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public List<MoreCommentsBean.DataBean.CommentsBean.RowsBean> getData() {
        return lists;
    }

    public void setParentUserId(int userId) {
        this.parentUserId = userId;
    }

    class MoreCommentsHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.view)
        View view;
        @BindView(R.id.iv_commented_user_icon)
        ImageView ivCommentedUserIcon;
        @BindView(R.id.iv_model_icon)
        ImageView ivModelIcon;
        @BindView(R.id.tv_commented_user_name)
        TextView tvCommentedUserName;
        @BindView(R.id.tv_eave_content)
        TextView tvEaveContent;
        @BindView(R.id.tv_create_time)
        TextView tvCreateTime;
        @BindView(R.id.tv_praise_num)
        TextView tvPraiseNum;
        @BindView(R.id.tvComments)
        TextView tvComments;
        public MoreCommentsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        //"commentUserName": @ "becommentedUserName":  "commentContent":
        public void refresh(int position, List<MoreCommentsBean.DataBean.CommentsBean.RowsBean> lists) {
            final MoreCommentsBean.DataBean.CommentsBean.RowsBean bean = lists.get(position);
            GlideUtils.loadCircleUserUrl(context,ivCommentedUserIcon, Constants.BASE_IMG_URL+bean.getCommentUserIcon());
            StringUtil.getUserType(bean.getUserType(),ivModelIcon);
            tvCommentedUserName.setText(StringUtil.getBeanString(bean.getCommentUserName()));
            tvCreateTime.setText(TimeToolUtils.convertTimeToFormat(bean.getCreateTime()));
            tvPraiseNum.setText(String.valueOf(bean.getPraiseNum()));

            //"praiseStatus":0,//点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
            if (bean.getPraiseStatus() == 1) {
                tvPraiseNum.setSelected(false);
            } else if (bean.getPraiseStatus() == 0) {
                tvPraiseNum.setSelected(true);
            } else if (bean.getPraiseStatus() == 2) {
                tvPraiseNum.setVisibility(View.GONE);
            }
            final String userName = StringUtil.getBeanString(bean.getCommentUserName());
            String userNameB = ": ";
            if (bean.getCommentUserId() != bean.getBecommentedUserId()) {
//                    && bean.getBecommentedUserId()!=parentUserId){
                userNameB = ": @" + StringUtil.getBeanString(bean.getBecommentedUserName()) + "  ";
            }
            final String content = StringUtil.getBeanString(bean.getCommentContent());
            String all = userName + userNameB + content;
            String name[] = {userName, userNameB};
            Clickable.getSpannableString(all, name, tvEaveContent, new Clickable.ClickListener() {
                @Override
                public void setOnClick(String name) {
                    if (name.equals(userName)) {
                        IntentUtil.startHomeActivity(bean.getCommentUserId());
                    } else {
                        IntentUtil.startHomeActivity(bean.getBecommentedUserId());
                    }
                }
            });
            tvPraiseNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goPraise(bean);
                }
            });
            tvComments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MoreCommentsActivity) context).setSendEd(bean.getCommentUserName(), bean.getCommentsId());
//                    ((MoreCommentsActivity)context).setSendEd(bean.getCommentUserName(),0);
                }
            });
            tvEaveContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MoreCommentsActivity) context).setSendEd(bean.getCommentUserName(), bean.getCommentsId());
//                    ((MoreCommentsActivity)context).setSendEd(bean.getCommentUserName(),0);
                }
            });
        }
        private void goPraise(MoreCommentsBean.DataBean.CommentsBean.RowsBean bean) {
            if(!tvPraiseNum.isSelected()){
                return;
            }
            if(!NetUtil.isPraise(bean.getCommentUserId(), SharedUtils.getUserId())){
                return;
            }
            tvPraiseNum.setEnabled(false);
            String strNum;
            if(tvPraiseNum.isSelected()){
                strNum =  String.valueOf(bean.getPraiseNum()+1);
            }else{
                strNum = String.valueOf(bean.getPraiseNum()-1);
            }
            tvPraiseNum.setText(strNum);
            tvPraiseNum.setSelected(!tvPraiseNum.isSelected());
            NetUtil.setAnimation(tvPraiseNum);
            NetUtil.addCommentsPraise(!tvPraiseNum.isSelected(), bean.getCommentsId(), new NetUtil.SaveFollowImpl() {
                @Override
                public void finishFollow(String praiseNum,boolean status,double find) {
                    tvPraiseNum.setEnabled(true);
                    if(!praiseNum.equals(Constants.PRAISE_ERROR)){
                        DialogUtils.showDialogPraise(context,1,true,find);
                        tvPraiseNum.setText(praiseNum);
                        tvPraiseNum.setSelected(status);
                    }
                }
            });
        }
    }


}
