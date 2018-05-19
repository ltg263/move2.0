package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.view.EvaluationSliderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：专业评测 列表 item
 */

public class EvaluationCompileAdapter extends RecyclerView.Adapter<EvaluationCompileAdapter.EvaluationHolder> {


    private List<BaseRes> lists = new ArrayList<>();
    private Context context;

    public EvaluationCompileAdapter(Context context) {
        this.context = context;
    }

    @Override
    public EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evaluation_compile, parent, false);
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
        @BindView(R.id.esv)
        EvaluationSliderView esv;

        public EvaluationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void refresh(int position, List<BaseRes> lists) {
            BaseRes res = lists.get(position);
            float f = res.getCode() / 10f;
            esv.setScore(f);
            esv.setTvDimensionalityName(res.getMsg());
            switch (position) {
                case 0:
                    esv.setEsvBackground(R.color.app_background);
                    break;
                case 1:
                    esv.setEsvBackground(R.color.xmdw);
                    break;
                case 2:
                    esv.setEsvBackground(R.color.jskj);
                    break;
                case 3:
                    esv.setEsvBackground(R.color.tdsl);
                    break;
                case 4:
                    esv.setEsvBackground(R.color.xmjd);
                    break;
                case 5:
                    esv.setEsvBackground(R.color.tzfx);
                    break;
            }
        }
    }
}
