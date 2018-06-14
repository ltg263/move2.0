package com.secretk.move.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.BaseManager;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MineAttentionBean;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class MineAttentionHolder extends RecyclerViewBaseHolder {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvLastContent)
    TextView tvLastContent;
    @BindView(R.id.tv_save_follow)
    TextView tvSaveFollow;
    @BindView(R.id.ll_user)
    LinearLayout llUser;
    @BindView(R.id.img_p)
    ImageView imgP;
    @BindView(R.id.tvName_p)
    TextView tvNameP;
    @BindView(R.id.tvName_pf)
    TextView tvNamePf;
    @BindView(R.id.tvName_pfs)
    TextView tvNamePfs;
    @BindView(R.id.tvLastContent_p)
    TextView tvLastContentP;
    @BindView(R.id.ll_project)
    LinearLayout llProject;

    public MineAttentionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void refresh(final MineAttentionBean.DataBean.MyFollowsBean.RowsBean rowsBean, Context context) {
        tvSaveFollow.setSelected(true);
        ////关注类型，数字，关注类型：1-关注项目;2-关注帖子；3-关注用户
        if(rowsBean.getFollowType()==1){
            showProject(rowsBean,context);
        }else if(rowsBean.getFollowType()==3){
            showUser(rowsBean,context);
        }else{

        }
        llUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1-关注项目;2-关注帖子；3-关注用户
                IntentUtil.startHomeActivity(rowsBean.getFollowedUserId());
            }
        });
        llProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1-关注项目;2-关注帖子；3-关注用户
                IntentUtil.startProjectActivity(rowsBean.getFollowedProjectId());
            }
        });
    }

    private void showProject(MineAttentionBean.DataBean.MyFollowsBean.RowsBean rowsBean, Context context) {
        llProject.setVisibility(View.VISIBLE);
        llUser.setVisibility(View.GONE);
        GlideUtils.loadCircleProjectUrl(context,imgP,Constants.UPLOAD_IMG_FILE+StringUtil.getBeanString(rowsBean.getProjectIcon()));
        tvNameP.setText(StringUtil.getBeanString(rowsBean.getProjectCode()));
        tvNamePf.setText("/"+StringUtil.getBeanString(rowsBean.getProjectChineseName()));
        tvNamePfs.setText(String.valueOf(rowsBean.getTotalScore())+"分");
        tvLastContentP.setText(StringUtil.getBeanString(rowsBean.getProjectSignature()));
    }
    private void showUser(MineAttentionBean.DataBean.MyFollowsBean.RowsBean rowsBean, Context context) {
        llProject.setVisibility(View.GONE);
        llUser.setVisibility(View.VISIBLE);
        tvSaveFollow.setText(BaseManager.app.getResources().getString(R.string.follow_status_1));
        GlideUtils.loadCircleUserUrl(context,img,Constants.UPLOAD_IMG_FILE+StringUtil.getBeanString(rowsBean.getFollowedUserIcon()));
        tvName.setText(StringUtil.getBeanString(rowsBean.getFollowedUserName()));
        tvLastContent.setText(StringUtil.getBeanString(rowsBean.getFollowedUserSignature()));
    }
}
