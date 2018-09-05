package com.secretk.move.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.SearchUserBean;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class SearchUserHolder extends RecyclerViewBaseHolder {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.iv_model_icon)
    ImageView ivModelIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_signature)
    TextView tvSignature;
    @BindView(R.id.tv_fans_num)
    TextView tvFansNum;
    @BindView(R.id.tv_save_follow)
    TextView tvSaveFollow;
    @BindView(R.id.ll_user)
    LinearLayout llUser;

    public SearchUserHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void refresh(final SearchUserBean.DataBean.RowsBean rowsBean, Context context) {
        tvSaveFollow.setSelected(true);
        llUser.setVisibility(View.VISIBLE);
        if(rowsBean.getFollowStatus() == 1){
            tvSaveFollow.setSelected(true);
            tvSaveFollow.setText(context.getResources().getString(R.string.follow_status_1));
        }else{
            tvSaveFollow.setSelected(false);
            tvSaveFollow.setText(context.getResources().getString(R.string.follow_status_0));
        }
        tvFansNum.setText(rowsBean.getPraiseNum()+"赞    "+rowsBean.getFansNum()+"关注");
        StringUtil.getUserType(rowsBean.getUserType(),ivModelIcon);
        GlideUtils.loadCircleUserUrl(context,img,Constants.BASE_IMG_URL+StringUtil.getBeanString(rowsBean.getIcon()));
        tvName.setText(StringUtil.getBeanString(rowsBean.getUserName()));
        if(StringUtil.isNotBlank(rowsBean.getUserSignature())){
            tvSignature.setText(StringUtil.getBeanString(rowsBean.getUserSignature()));
        }else{
            tvSignature.setText("无");
        }
        //关注
        tvSaveFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                tvSaveFollow.setEnabled(false);
                NetUtil.addSaveFollow(tvSaveFollow,
                        Constants.SaveFollow.USER, rowsBean.getUserId(), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvSaveFollow.setEnabled(true);
                                if(!str.equals(Constants.FOLLOW_ERROR)){
                                    tvSaveFollow.setText(str);
                                }
                            }
                        });
            }
        });
        llUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1-关注项目;2-关注帖子；3-关注用户
                IntentUtil.startHomeActivity(rowsBean.getUserId());
            }
        });
    }
}
