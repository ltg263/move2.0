package com.secretk.move.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.BaseManager;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.ProjectHomeBean;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:21
 * 邮箱；ltg263@126.com
 * 描述：项目主页---简介ListViewItem
 */
public class ProjectIntroHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_follow_status)
    TextView tvFollowStatus;
    @BindView(R.id.tv_user_uame)
    TextView tvUserUame;
    @BindView(R.id.tv_user_signature)
    TextView tvUserSignature;
    public ProjectIntroHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void refresh(final int position, List<ProjectHomeBean.DataBean.ProjectBean.ActiveUsersBean> lists) {
        final ProjectHomeBean.DataBean.ProjectBean.ActiveUsersBean usersBean = lists.get(position);
        GlideUtils.loadCircleUrl(ivIcon, Constants.BASE_IMG_URL+usersBean.getIcon());
        tvUserUame.setText(usersBean.getUserName());
        tvUserSignature.setText(usersBean.getUserSignature());
        //0 显示 关注按钮； 1--显示取消关注 按钮 ；2 不显示按钮
        if(usersBean.getFollowStatus()==1){
            tvFollowStatus.setSelected(false);
            tvFollowStatus.setText(BaseManager.app.getResources().getString(R.string.follow_status_0));
        }else if(usersBean.getFollowStatus() == 0){
            tvFollowStatus.setSelected(true);
            tvFollowStatus.setText(BaseManager.app.getResources().getString(R.string.follow_status_1));
        }else{
            tvFollowStatus.setVisibility(View.GONE);
        }
        tvFollowStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFollowStatus.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatus,
                        Constants.SaveFollow.USER, usersBean.getUserId(), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvFollowStatus.setEnabled(true);
                                if(!str.equals(Constants.FOLLOW_ERROR)){
                                    tvFollowStatus.setText(str);
                                }
                            }
                        });
            }
        });
    }
}
