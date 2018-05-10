package com.secretk.move.ui.holder;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.BaseManager;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.CommonCommentsBean;
import com.secretk.move.ui.activity.MoreCommentsActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.StringUtil;

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
    @BindView(R.id.tv_child_content1)
    TextView tvChildContent1;
    @BindView(R.id.tv_child_content2)
    TextView tvChildContent2;
    @BindView(R.id.view_child_content1)
    View viewChildContent1;
    @BindView(R.id.view_child_content2)
    View viewChildContent2;
    @BindView(R.id.tv_child_comments_num)
    TextView tvChildCommentsNum;
    public DetailsDiscussHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void refresh(final int position, List<CommonCommentsBean> lists, Context context) {
        CommonCommentsBean commentsBean = lists.get(position);
        GlideUtils.loadCircleUrl(ivCommentedUserIcon, Constants.BASE_IMG_URL+commentsBean.getCommentUserIcon());
        tvCommentedUserName.setText(commentsBean.getCommentUserName());
        tvPraiseNum.setText(String.valueOf(commentsBean.getPraiseNum()));
        tvCreateTime.setText(commentsBean.getFloor() +"楼    "+StringUtil.getTimeToM(commentsBean.getCreateTime()));
        tvCommentContent.setText(commentsBean.getCommentContent());
        //"praiseStatus":0,//点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
        if(commentsBean.getPraiseStatus()==1){
            tvPraiseNum.setSelected(false);
        }else if(commentsBean.getPraiseStatus()==0){
            tvPraiseNum.setSelected(true);
        }else if(commentsBean.getPraiseStatus()==3){
            tvPraiseNum.setText("****");
        }
        if(commentsBean.getChildCommentsNum()!=0){
            tvChildCommentsNum.setText("更多"+commentsBean.getChildCommentsNum()+"条评论");
        }
        tvChildCommentsNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startActivity(MoreCommentsActivity.class);
            }
        });
        final List<CommonCommentsBean.ChildCommentsListBean> childLists = commentsBean.getChildCommentsList();
        if(childLists!=null && childLists.size()>0){
            for(int i=0;i<childLists.size();i++){
                String userName = childLists.get(i).getCommentUserName()+": ";
                String userNameB = "@"+childLists.get(i).getBecommentedUserName();
                String content = childLists.get(i).getCommentContent();
                String all = userName+userNameB+content;
                SpannableString InfoOne = new SpannableString(all);
                final int finalI = i;
                //评论人
                int var = all.indexOf(userName);
                InfoOne.setSpan(new Clickable(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(BaseManager.app, childLists.get(finalI).getCommentUserName(),Toast.LENGTH_SHORT).show();
                    }
                }),var,var+userNameB.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                //被评论人
                int varB = all.indexOf(userNameB);
                InfoOne.setSpan(new Clickable(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(BaseManager.app, childLists.get(finalI).getBecommentedUserName(),Toast.LENGTH_SHORT).show();
                    }
                }),varB,varB+userNameB.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                if(i==0){
                    tvChildContent1.setText(InfoOne);
                    tvChildContent1.setVisibility(View.VISIBLE);
                    viewChildContent1.setVisibility(View.VISIBLE);
                    tvChildContent1.setMovementMethod(LinkMovementMethod.getInstance());
                }
                if(i==1){
                    tvChildContent2.setText(InfoOne);
                    tvChildContent2.setVisibility(View.VISIBLE);
                    viewChildContent2.setVisibility(View.VISIBLE);
                    tvChildContent2.setMovementMethod(LinkMovementMethod.getInstance());
                }

            }

        }
    }
    class Clickable extends ClickableSpan {
        private final View.OnClickListener mListener;
        public Clickable(View.OnClickListener l) {
            mListener = l;
        }
        /**
         * 重写父类点击事件
         */
        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }
        /**
         * 重写父类updateDrawState方法  我们可以给TextView设置字体颜色,背景颜色等等...
         */
        @Override
        public void updateDrawState(TextPaint ds) {
           ds.setColor(BaseManager.app.getResources().getColor(R.color.app_background));
        }
    }
}
