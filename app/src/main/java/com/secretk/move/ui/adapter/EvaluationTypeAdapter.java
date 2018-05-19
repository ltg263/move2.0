package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.RowsBean;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.ui.activity.EvaluationProfessionalItemActivity;
import com.secretk.move.ui.holder.HomeListHolder;
import com.secretk.move.utils.IntentUtil;

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


    private List<BaseRes> lists = new ArrayList<>();
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

    public void setData(List<BaseRes> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public List<BaseRes> getData() {
        return lists;
    }
    public class EvaluationHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.tv_type)
        TextView tv_type;

        public EvaluationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void refresh(int position, List<BaseRes> lists) {
            tv_type.setText(lists.get(position).getMsg());
            tv_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IntentUtil.startActivity(EvaluationProfessionalItemActivity.class);
                }
            });
        }
    }
}
