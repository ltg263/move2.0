package com.secretk.move.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class SelectProjectHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tvCode)
    TextView tvCode;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvFollws)
    TextView tvFollws;
    @BindView(R.id.iv_selected)
    ImageView ivSlected;
    public SelectProjectHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void refresh(int position, List<SearchedBean.DataBean.ProjectsBean.RowsBean> listNum , int projectId, Context context) {
        final SearchedBean.DataBean.ProjectsBean.RowsBean currenBean= listNum.get(position);
        GlideUtils.loadCircleProjectUrl(context,img, Constants.BASE_IMG_URL + currenBean.getProjectIcon());
        tvCode.setText(currenBean.getProjectCode()+"/");
        tvName.setText(currenBean.getProjectChineseName());
        tvFollws.setText(currenBean.getFollowerNum()+"关注");
        if(projectId==currenBean.getProjectId()){
            ivSlected.setSelected(true);
        }
    }
}
