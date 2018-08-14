package com.secretk.move.view;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.ui.adapter.DiaLogListReportAdapter;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.NetUtil;

import java.util.List;

/**
 *
 * @time 2014/11/5
 */
public class ReportPopupWindow extends PopupWindow {

    private final RecyclerView rvPopWindow;
    private final TextView tvPopWindow;
    private final DiaLogListReportAdapter adapter;
    private View mMenuView;
    private List<Integer> listIndex;
    private int postId;
    private Context mContext;

    public ReportPopupWindow(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_window_report, null);
        rvPopWindow =  mMenuView.findViewById(R.id.rv_context);
        tvPopWindow =mMenuView.findViewById(R.id.tv_confirm);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPopWindow.setLayoutManager(manager);

       adapter = new DiaLogListReportAdapter(context);
        rvPopWindow.setAdapter(adapter);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点�?
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        setEvent();

    }

    public void setData(List<String> list, List<Integer> listIndex, int postId){
        this.listIndex = listIndex;
        adapter.setData(list);
        this.postId=postId;
    }

    private void setEvent() {
        tvPopWindow.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
//                List<String> list = adapter.getData();
//                LogUtil.w("list:"+list.toString());
                LogUtil.w("list:"+adapter.getPosition());
                if(listIndex==null){
                    return;
                }
                NetUtil.saveReport(1, postId, listIndex.get(adapter.getPosition()), new NetUtil.SaveCollectImp() {
                    @Override
                    public void finishCollect(String code, boolean status) {
                        if(!code.equals(Constants.COLLECT_ERROR)){
                            DialogUtils.showDialogPraise(mContext,4,status,0);
                        }
                    }
                });
                dismiss();
            }
        });
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
    public interface DialogConfirm {
        void confirm(String type);
    }
}
