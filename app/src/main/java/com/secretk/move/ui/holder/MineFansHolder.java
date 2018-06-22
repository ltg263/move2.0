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
import com.secretk.move.bean.MyFansList;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 作者： litongge
 * 时间： 2018/4/27 15:21
 * 邮箱；ltg263@126.com
 * 描述：我的收藏 评分、讨论、文章列表
 */
public class MineFansHolder extends RecyclerViewBaseHolder {
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
    @BindView(R.id.ll_project)
    LinearLayout llProject;

    public MineFansHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void refresh(final MyFansList.DataBean.MyFansBean.RowsBean rowsBean, Context context) {
        tvSaveFollow.setSelected(true);
        ////关注类型，数字，关注类型：1-关注项目;2-关注帖子；3-关注用户
        showUser(rowsBean,context);
        llUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1-关注项目;2-关注帖子；3-关注用户
                IntentUtil.startHomeActivity(rowsBean.getFollowedUserId());
            }
        });
    }
    private void showUser(MyFansList.DataBean.MyFansBean.RowsBean rowsBean, Context context) {
        llProject.setVisibility(View.GONE);
        llUser.setVisibility(View.VISIBLE);
        tvSaveFollow.setText(BaseManager.app.getResources().getString(R.string.follow_status_1));
        GlideUtils.loadCircleUserUrl(context,img,Constants.BASE_IMG_URL+StringUtil.getBeanString(rowsBean.getFollowedUserIcon()));
        tvName.setText(StringUtil.getBeanString(rowsBean.getFollowedUserName()));
        tvLastContent.setText(StringUtil.getBeanString(rowsBean.getFollowedUserSignature()));
    }


}
