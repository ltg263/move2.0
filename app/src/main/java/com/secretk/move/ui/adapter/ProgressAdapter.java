package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.view.ProgressBarStyleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：进度条
 */

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ProgressHolder> {

    private List<String> lists = new ArrayList<>();
    Context context;
    public ProgressAdapter(Context context) {
        this.context=context;
    }

    @Override
    public ProgressHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);
        ProgressHolder holder = new ProgressHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProgressHolder holder, final int position) {
        try {
            holder.refresh(position, lists);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<String> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public List<String> getData() {
        return lists;
    }

    class ProgressHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.pb_project)
        ProgressBarStyleView pbProject;
        public ProgressHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void refresh(int position, List<String> lists) throws JSONException {
            //[{"modelId":3,"modelName":"项目进度","score":"6.7"}
            //{"id":10,"modelId":3,"detailName":"项目定位","detailDesc":"从项目前景、市场空间、竞争对手等各方面来评估。",
            // "detailWeight":30,"totalScore":8.6,"raterNum":212}
            String str = lists.get(position);
            JSONObject object = new JSONObject(str);
            String projectName = object.getString("modelName");
            int detailWeight = object.getInt("modelWeight");
//            int raterNum = object.getInt("raterNum");
            double totalScor = object.getDouble("score");
            totalScor= Double.valueOf(String.format("%.1f", totalScor));

//            String two = "/ " + String.valueOf(detailWeight) + "% (" + raterNum + "人)";
            String two = "/ " + String.valueOf(detailWeight) + "%";
            pbProject.setAllTv(projectName, two, totalScor);
            if (position==0) {
                pbProject.setProgressDrawable(R.drawable.pb_view_xmdw, R.color.xmdw);
            }else if (position==1) {
                pbProject.setProgressDrawable(R.drawable.pb_view_jskj, R.color.jskj);
            }else if (position==2) {
                pbProject.setProgressDrawable(R.drawable.pb_view_tdsl, R.color.tdsl);
            }else if (position==3) {
                pbProject.setProgressDrawable(R.drawable.pb_view_xmjd, R.color.xmjd);
            }else if (position==4) {
                pbProject.setProgressDrawable(R.drawable.pb_view_tzfx, R.color.tzfx);
            }else{
                pbProject.setProgressDrawable(R.drawable.pb_view_tzfx, R.color.tzfx);
            }
        }
    }
}
