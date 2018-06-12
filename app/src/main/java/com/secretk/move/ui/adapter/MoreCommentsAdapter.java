package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.MoreCommentsBean;
import com.secretk.move.ui.activity.MoreCommentsActivity;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.view.Clickable;

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
    public MoreCommentsAdapter(Context context) {
        this.context=context;
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

    class MoreCommentsHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.tv_eave_content)
        TextView tvEaveContent;
        public MoreCommentsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
//"commentUserName": @ "becommentedUserName":  "commentContent":
        public void refresh(int position, List<MoreCommentsBean.DataBean.CommentsBean.RowsBean> lists) {
            final MoreCommentsBean.DataBean.CommentsBean.RowsBean bean = lists.get(position);
            final String userName = StringUtil.getBeanString(bean.getCommentUserName());
            String userNameB = ": @"+StringUtil.getBeanString(bean.getBecommentedUserName());
            final String content = StringUtil.getBeanString(bean.getCommentContent());
            String all = userName+userNameB+content;
            String name[] = {userName,userNameB};
            Clickable.getSpannableString(all, name, tvEaveContent,new Clickable.ClickListener() {
                @Override
                public void setOnClick(String name) {
                    if(name.equals(userName)){
                        IntentUtil.startHomeActivity(bean.getCommentUserId());
                    }else{
                        IntentUtil.startHomeActivity(bean.getBecommentedUserId());
                    }
                }
            });
            tvEaveContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MoreCommentsActivity)context).setSendEd(bean.getCommentUserName(),bean.getCommentsId());
//                    ((MoreCommentsActivity)context).setSendEd(bean.getCommentUserName(),0);
                }
            });
        }
    }
}
