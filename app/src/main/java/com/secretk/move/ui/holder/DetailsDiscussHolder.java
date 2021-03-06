package com.secretk.move.ui.holder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.CommonCommentsBean;
import com.secretk.move.ui.activity.DetailsArticleActivity;
import com.secretk.move.ui.activity.DetailsArticleCommentActivity;
import com.secretk.move.ui.activity.DetailsDiscussActivity;
import com.secretk.move.ui.activity.DetailsReviewAllActivity;
import com.secretk.move.ui.activity.MoreCommentsActivity;
import com.secretk.move.ui.adapter.DetailsDiscussAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.TimeToolUtils;
import com.secretk.move.view.Clickable;
import com.secretk.move.view.DialogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/5/2 16:48
 * 邮箱；ltg263@126.com
 * 描述：评测详情——评价Item
 */
public class DetailsDiscussHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.view)
    View view;
    @BindView(R.id.iv_commented_user_icon)
    ImageView ivCommentedUserIcon;
    @BindView(R.id.iv_model_icon)
    ImageView ivModelIcon;
    @BindView(R.id.tv_commented_user_name)
    TextView tvCommentedUserName;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_praise_num)
    TextView tvPraiseNum;
    @BindView(R.id.tv_comment_content)
    TextView tvCommentContent;
    @BindView(R.id.tv_child_content1)
    TextView tvChildContent1;
    @BindView(R.id.tv_child_content2)
    TextView tvChildContent2;
    @BindView(R.id.tv_child_comments_num)
    TextView tvChildCommentsNum;
    @BindView(R.id.tvComments)
    TextView tvComments;
    private CommonCommentsBean commentsBean;
    int loginUserId;
    public DetailsDiscussHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        loginUserId = SharedUtils.singleton().get(Constants.USER_ID,0);
    }

    public void refresh(int position, List<CommonCommentsBean> lists, final Context context, final DetailsDiscussAdapter adapter) {
        if(position==0){
            view.setVisibility(View.GONE);
        }
        commentsBean = lists.get(position);
        GlideUtils.loadCircleUserUrl(context,ivCommentedUserIcon, Constants.BASE_IMG_URL+commentsBean.getCommentUserIcon());
        StringUtil.getUserType(commentsBean.getUserType(),ivModelIcon);
        tvCommentedUserName.setText(commentsBean.getCommentUserName());
        tvPraiseNum.setText(String.valueOf(commentsBean.getPraiseNum()));
        tvCreateTime.setText(TimeToolUtils.convertTimeToFormat(commentsBean.getCreateTime()));
//        tvCreateTime.setText(commentsBean.getFloor() +"楼    "+ TimeToolUtils.convertTimeToFormat(commentsBean.getCreateTime()));
        tvCommentContent.setText(commentsBean.getCommentContent());
        //"praiseStatus":0,//点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
        if(commentsBean.getPraiseStatus()==1){
            tvPraiseNum.setSelected(false);
        }else if(commentsBean.getPraiseStatus()==0){
            tvPraiseNum.setSelected(true);
        }else if(commentsBean.getPraiseStatus()==2){
            tvPraiseNum.setVisibility(View.GONE);
        }
        //点赞
        tvPraiseNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvPraiseNum.isSelected()){
                    return;
                }
                if (!NetUtil.isPraise(commentsBean.getCommentUserId(), loginUserId)){
                    return;
                }
                tvPraiseNum.setEnabled(false);
                setPraise(context,tvPraiseNum,commentsBean.getCommentsId(),adapter);
            }
        });
        //跳转到用户
        ivCommentedUserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startHomeActivity(commentsBean.getCommentUserId());
            }
        });
        //跳转到用户
        tvCommentedUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startHomeActivity(commentsBean.getCommentUserId());
            }
        });
        tvChildCommentsNum.setVisibility(View.GONE);
        if(commentsBean.getChildCommentsNum()>2){
            tvChildCommentsNum.setVisibility(View.VISIBLE);
            tvChildCommentsNum.setText("共"+commentsBean.getChildCommentsNum()+"条回复");
        }
        final List<CommonCommentsBean.ChildCommentsListBean> childLists = commentsBean.getChildCommentsList();
        //更多评论
        tvChildCommentsNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MoreCommentsActivity.class);
                intent.putExtra("commentsBean", commentsBean);
                ((Activity)context).startActivityForResult(intent,0);
            }
        });
        setChildLists(childLists,context);

        tvChildContent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MoreCommentsActivity.class);
                intent.putExtra("commentsBean", commentsBean);
                ((Activity)context).startActivityForResult(intent,0);
            }
        });
        tvChildContent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MoreCommentsActivity.class);
                intent.putExtra("commentsBean", commentsBean);
                ((Activity)context).startActivityForResult(intent,0);
            }
        });
        tvCommentContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context instanceof DetailsDiscussActivity){
                    ((DetailsDiscussActivity)context).setIntput(commentsBean.getCommentUserName(),commentsBean.getCommentsId());
                }else if(context instanceof DetailsArticleCommentActivity){
                    ((DetailsArticleCommentActivity)context).setIntput(commentsBean.getCommentUserName(),commentsBean.getCommentsId());
                }else if(context instanceof DetailsArticleActivity){
                    ((DetailsArticleActivity)context).setIntput(commentsBean.getCommentUserName(),commentsBean.getCommentsId());
                }else if(context instanceof DetailsReviewAllActivity){
                    ((DetailsReviewAllActivity)context).setIntput(commentsBean.getCommentUserName(),commentsBean.getCommentsId());
                }
            }
        });
        tvComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context instanceof DetailsDiscussActivity){
                    ((DetailsDiscussActivity)context).setIntput(commentsBean.getCommentUserName(),commentsBean.getCommentsId());
                }else if(context instanceof DetailsArticleCommentActivity){
                    ((DetailsArticleCommentActivity)context).setIntput(commentsBean.getCommentUserName(),commentsBean.getCommentsId());
                }else if(context instanceof DetailsArticleActivity){
                    ((DetailsArticleActivity)context).setIntput(commentsBean.getCommentUserName(),commentsBean.getCommentsId());
                }else if(context instanceof DetailsReviewAllActivity){
                    ((DetailsReviewAllActivity)context).setIntput(commentsBean.getCommentUserName(),commentsBean.getCommentsId());
                }
            }
        });
    }

    private void setPraise(final Context context, TextView finalIsLove, final int commentsId, final DetailsDiscussAdapter adapter) {
        final int praiseNumA = commentsBean.getPraiseNum();
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
            public void finishFollow(String praiseNum,boolean status,double find,double postTotalIncome) {
                tvPraiseNum.setEnabled(true);
                if(!praiseNum.equals(Constants.PRAISE_ERROR)){
                    DialogUtils.showDialogPraise(context,1,true,0);
                    commentsBean.setPraiseNum(Integer.valueOf(praiseNum));
                    tvPraiseNum.setText(praiseNum);
//                    praiseNumA = commentsBean.getPraiseNum();
                    tvPraiseNum.setSelected(status);
                    ////点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
                    commentsBean.setPraiseStatus(status?0:1);

                    if(context instanceof DetailsDiscussActivity){
                        ((DetailsDiscussActivity)context).setCommentsIdPraise(adapter,commentsId,Integer.valueOf(praiseNum),status?0:1);
                    }else if(context instanceof DetailsArticleCommentActivity){
                        ((DetailsArticleCommentActivity)context).setCommentsIdPraise(adapter,commentsId,Integer.valueOf(praiseNum),status?0:1);
                    }else if(context instanceof DetailsArticleActivity){
                        ((DetailsArticleActivity)context).setCommentsIdPraise(adapter,commentsId,Integer.valueOf(praiseNum),status?0:1);
                    }else if(context instanceof DetailsReviewAllActivity){
                        ((DetailsReviewAllActivity)context).setCommentsIdPraise(adapter,commentsId,Integer.valueOf(praiseNum),status?0:1);
                    }
                }else{
//                    tvPraiseNum.setSelected(false);
//                    tvPraiseNum.setText(String.valueOf(Integer.valueOf(strNum)-1));
                }
            }
        });
    }

    /**
     * 设置更多子的留言信息
     * @param childLists
     * @param context
     */
    private void setChildLists(final List<CommonCommentsBean.ChildCommentsListBean> childLists, final Context context) {
        tvChildContent1.setVisibility(View.GONE);
        tvChildContent2.setVisibility(View.GONE);
        if(childLists!=null && childLists.size()>0){
            for(int i=0;i<childLists.size();i++){
                final String userName = childLists.get(i).getCommentUserName();
                String userNameB = ": ";
                if(childLists.get(i).getCommentUserId()!=childLists.get(i).getBecommentedUserId()){
//                        && commentsBean.getCommentUserId() != childLists.get(i).getBecommentedUserId() ){
                    userNameB = ": @"+childLists.get(i).getBecommentedUserName()+"  ";
                }
                String content = childLists.get(i).getCommentContent();
                String all = userName+userNameB+content;
                String name[] = {userName,userNameB};
                if(i==0){
                    tvChildContent1.setVisibility(View.VISIBLE);
                    Clickable.getSpannableString(all, name, tvChildContent1,new Clickable.ClickListener() {
                        @Override
                        public void setOnClick(String name) {
//                            if(name.equals(userName)){
//                                IntentUtil.startHomeActivity(childLists.get(0).getCommentUserId());
//                            }else{
//                                IntentUtil.startHomeActivity(childLists.get(0).getBecommentedUserId());
//                            }
                        }
                    });
                }
                if(i==1){
                    tvChildContent2.setVisibility(View.VISIBLE);
                    Clickable.getSpannableString(all, name, tvChildContent2,new Clickable.ClickListener() {
                        @Override
                        public void setOnClick(String name) {
//                            if(name.equals(userName)){
//                                IntentUtil.startHomeActivity(childLists.get(1).getCommentUserId());
//                            }else{
//                                IntentUtil.startHomeActivity(childLists.get(1).getBecommentedUserId());
//                            }
                        }
                    });
                }

            }

        }
    }
}
