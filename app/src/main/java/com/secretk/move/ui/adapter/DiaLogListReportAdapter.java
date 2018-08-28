package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.MineApproveSubmitiCertificateActivity;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.view.DialogUtils;
import com.secretk.move.view.ReportPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 */

public class DiaLogListReportAdapter extends RecyclerView.Adapter<DiaLogListReportAdapter.ListHolder> {
    private ItemClickListener mListener;
    private List<String> lists = new ArrayList<>();
    private List<String> listSelect = new ArrayList<>();
    private List<Integer> listIndex = new ArrayList<>();
    private int postId;
    private ReportPopupWindow reportPopupWindow;

    int lsPosition = -1;
    Context context;
    public DiaLogListReportAdapter(Context context) {
        this.context=context;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        ListHolder holder = new ListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListHolder holder, final int position) {
        holder.refresh(position, lists);
        holder.setItemListener(mListener);
    }
    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<String> list, List<Integer> listIndex, int postId, ReportPopupWindow reportPopupWindow) {
        this.lists = list;
        this.postId = postId;
        this.listIndex = listIndex;
        this.reportPopupWindow = reportPopupWindow;
        notifyDataSetChanged();
    }

    public List<String> getData() {
        return listSelect;
    }
    public int getPosition() {
        return lsPosition;
    }


    class ListHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;
        public ListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void refresh(final int position, final List<String> lists){
            tvItem.setText(lists.get(position));
            tvItem.setTextColor(context.getResources().getColor(R.color.title_gray));
            if(lsPosition!=-1 && lsPosition==position){
                tvItem.setTextColor(context.getResources().getColor(R.color.app_background));
            }
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    多选
//                    if(listSelect.contains(lists.get(position))){
//                        listSelect.remove(lists.get(position));
//                        tvItem.setTextColor(context.getResources().getColor(R.color.title_gray));
//                    }else{
//                        listSelect.add(lists.get(position));
//                        tvItem.setTextColor(context.getResources().getColor(R.color.app_background));
//                    }
//                    单选
//                    lsPosition = position;
//                    notifyDataSetChanged();
                    setZjjb(position);
                }
            });
        }

        private void setZjjb(int position) {
            int userCardStatus = SharedUtils.singleton().get("userCardStatus", 0);
            if(userCardStatus!=2){
                DialogUtils.showDialogHint(context, "请先实名认证",false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        IntentUtil.startActivity(MineApproveSubmitiCertificateActivity.class);
                    }
                });
                reportPopupWindow.dismiss();
                return;
            }
            NetUtil.saveReport(1, postId, listIndex.get(position), new NetUtil.SaveCollectImp() {
                @Override
                public void finishCollect(String code, boolean status) {
                    if(!code.equals(Constants.COLLECT_ERROR)){
                        DialogUtils.showDialogPraise(context,4,status,0);
                    }
                }
            });
            reportPopupWindow.dismiss();
        }

    }
}
