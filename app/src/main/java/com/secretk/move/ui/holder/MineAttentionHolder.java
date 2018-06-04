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
    @BindView(R.id.ll)
    LinearLayout ll;

    public MineAttentionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    private int type;
    public void setAdapterType(int type) {
        this.type=type;
    }
    public void refresh(int position, final MineAttentionBean.DataBean.MyFollowsBean.RowsBean rowsBean, Context context) {
        tvSaveFollow.setSelected(true);
        ////关注类型，数字，关注类型：1-关注项目;2-关注帖子；3-关注用户
        if(rowsBean.getFollowType()==3){
//            IntentUtil.startHomeActivity(rowsBean.getFollowedUserId());
        }else if(rowsBean.getFollowType()==2){
//            IntentUtil.startProjectActivity(rowsBean.getFollowedProjectId());
        }
        tvSaveFollow.setText(BaseManager.app.getResources().getString(R.string.follow_status_1));
        GlideUtils.loadCircleUserUrl(context,img,Constants.UPLOAD_IMG_FILE+StringUtil.getBeanString(rowsBean.getFollowedUserIcon()));
        tvName.setText(StringUtil.getBeanString(rowsBean.getFollowerUserName()));
        tvLastContent.setText(StringUtil.getBeanString(rowsBean.getFollowedUserSignature()));
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1-关注项目;2-关注帖子；3-关注用户
                if(rowsBean.getFollowType()==3){
                    IntentUtil.startHomeActivity(rowsBean.getFollowedUserId());
                }else if(rowsBean.getFollowType()==2){
                    IntentUtil.startProjectActivity(rowsBean.getFollowedProjectId());
                }

            }
        });
    }
}
