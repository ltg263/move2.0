package com.secretk.move.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.SysEvaluationModelBean;
import com.secretk.move.ui.activity.EvaluationProfessionalActivity;
import com.secretk.move.ui.activity.EvaluationProfessionalItemActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：我的主页 列表 item
 */

public class EvaluationTypeAdapter extends RecyclerView.Adapter<EvaluationTypeAdapter.EvaluationHolder> {


    private List<SysEvaluationModelBean.DataBean.ModeDetailListBean> lists = new ArrayList<>();
    private Context context;
    public EvaluationTypeAdapter(Context context) {
        this.context=context;
    }

    @Override
    public EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evaluation_professional, parent, false);
        EvaluationHolder holder = new EvaluationHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EvaluationHolder holder, int position) {
        holder.refresh(position, lists);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<SysEvaluationModelBean.DataBean.ModeDetailListBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public List<SysEvaluationModelBean.DataBean.ModeDetailListBean> getData() {
        return lists;
    }
    public class EvaluationHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_weight)
        TextView tvWeight;
        @BindView(R.id.tv_dimensionality_compile)
        TextView tvDimensionality_compile;

        public EvaluationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void refresh(final int position, final List<SysEvaluationModelBean.DataBean.ModeDetailListBean> lists) {
            int colorUrl=R.color.app_background;
            switch (position) {
                case 0:
                    colorUrl=R.color.app_background;
                    break;
                case 1:
                    colorUrl=R.color.xmdw;
                    break;
                case 2:
                    colorUrl=R.color.jskj;
                    break;
                case 3:
                    colorUrl=R.color.tdsl;
                    break;
                case 4:
                    colorUrl=R.color.xmjd;
                    break;
                case 5:
                    colorUrl=R.color.tzfx;
                    break;
            }
            tvName.setTextColor(context.getResources().getColor(colorUrl));
            tvName.setText(lists.get(position).getDetailName());
            tvWeight.setText(" / "+lists.get(position).getDetailWeight()+"%");
            tvDimensionality_compile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,EvaluationProfessionalItemActivity.class);
                    intent.putExtra("sys_evaluation_model",lists.get(position));
                    intent.putExtra("projectName",((EvaluationProfessionalActivity)context).getProjectName());
                    intent.putExtra("projectPay",((EvaluationProfessionalActivity)context).getprojectPay());
                    intent.putExtra("projectId",((EvaluationProfessionalActivity)context).getProjectId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
