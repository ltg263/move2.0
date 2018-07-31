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
import com.secretk.move.bean.HotProjectAndHotUserBean;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：發現首頁 item
 */

public class FindNewAdapter extends RecyclerView.Adapter<FindNewAdapter.ImagesHolder> {

    private HotProjectAndHotUserBean.DataBean lists;
    Context context;
    private int type;
    public FindNewAdapter(Context context,int type) {
        this.context=context;
        this.type=type;
    }

    @Override
    public ImagesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_new, parent, false);
        ImagesHolder holder = new ImagesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImagesHolder holder, final int position) {
        if(type==1){
            GlideUtils.loadCircleProjectUrl(context,holder.iv_icon_p,
                    Constants.BASE_IMG_URL+lists.getProjects().get(position).getProjectIcon());
            holder.tv_name.setText(lists.getProjects().get(position).getProjectChineseName());
            holder.iv_icon_p.setVisibility(View.VISIBLE);
            holder.iv_icon_u.setVisibility(View.GONE);
        }else{
            GlideUtils.loadCircleUserUrl(context,holder.iv_icon_u,
                    Constants.BASE_IMG_URL+lists.getUsers().get(position).getIcon());
            holder.tv_name.setText(lists.getUsers().get(position).getUserName());
            holder.iv_icon_p.setVisibility(View.GONE);
            holder.iv_icon_u.setVisibility(View.VISIBLE);
        }
        holder.home_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt() || StringUtil.isBlank(SharedUtils.getToken())){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                if(type==1){
                    IntentUtil.startProjectActivity(lists.getProjects().get(position).getProjectId());
                }else{
                    IntentUtil.startHomeActivity(lists.getUsers().get(position).getUserId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        LogUtil.w("lists:"+lists);
        if(lists ==null){
            return 0;
        }
        if(type==1){
            return lists.getProjects().size();
        }
        return lists.getUsers().size();
    }

    //index 0 用户 1项目
    public void setData(HotProjectAndHotUserBean.DataBean list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public HotProjectAndHotUserBean.DataBean getData() {
        return lists;
    }




    class ImagesHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.iv_icon_p)
        ImageView iv_icon_p;
        @BindView(R.id.iv_icon_u)
        ImageView iv_icon_u;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.home_find)
        LinearLayout home_find;
        public ImagesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
