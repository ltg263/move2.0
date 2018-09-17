package com.secretk.move.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.SearchProjectBean;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class SearchProjectHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img)
    public ImageView img;
    @BindView(R.id.tvSpell)
    public TextView tvSpell;
    @BindView(R.id.tvCode)
    public TextView tvCode;
    @BindView(R.id.tvName)
    public TextView tvName;
    @BindView(R.id.tvFollws)
    public TextView tvFollws;
    @BindView(R.id.tvIsFollw)
    public TextView tvIsFollw;
    @BindView(R.id.tv_total_score)
    public TextView tvTotalScore;
    @BindView(R.id.rl_project)
    public RelativeLayout rlProject;
    public SearchProjectHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void setData(List<SearchProjectBean.DataBean.RowsBean> list, int position, final Context context) {
        tvSpell.setVisibility(View.GONE);
        final SearchProjectBean.DataBean.RowsBean currenBean = list.get(position);
        GlideUtils.loadCircleProjectUrl(context, img, Constants.BASE_IMG_URL + StringUtil.getBeanString(currenBean.getProjectIcon()));
        tvCode.setText(StringUtil.getBeanString(currenBean.getProjectCode()) + "/");
        tvName.setText(StringUtil.getBeanString(currenBean.getProjectChineseName()));
        if (currenBean.getTotalScore() != 0) {
            tvTotalScore.setVisibility(View.VISIBLE);
            tvTotalScore.setText(String.valueOf(currenBean.getTotalScore()));
        } else {
            tvTotalScore.setVisibility(View.GONE);
        }
        tvFollws.setText(currenBean.getFollowerNum() + "关注");
        //0 显示 关注按钮； 1--显示取消关注 按钮 ；2 不显示按钮
        if(currenBean.getFollowStatus() == 1){
            tvIsFollw.setSelected(true);
            tvIsFollw.setText(context.getResources().getString(R.string.follow_status_1));
        }else{
            tvIsFollw.setSelected(false);
            tvIsFollw.setText(context.getResources().getString(R.string.follow_status_0));
        }
        rlProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                IntentUtil.startProjectActivity(currenBean.getProjectId());
            }
        });
        tvIsFollw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedUtils.getLoginZt()) {
                    tvIsFollw.setEnabled(false);
                    NetUtil.addSaveFollow(tvIsFollw,
                            Constants.SaveFollow.PROJECT, Integer.valueOf(currenBean.getProjectId()), new NetUtil.SaveFollowImp() {
                                @Override
                                public void finishFollow(String str) {
                                    tvIsFollw.setEnabled(true);
                                    if (!str.equals(Constants.FOLLOW_ERROR)) {
                                        tvIsFollw.setText(str);
                                    }
                                }
                            });
                } else {
                    IntentUtil.startActivity(LoginHomeActivity.class);
                }
            }
        });
    }
}
