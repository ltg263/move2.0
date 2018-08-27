package com.secretk.move.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.ui.activity.MineApproveSubmitiCertificateActivity;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： litongge
 * 时间：  2018/8/13 13:58
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class ReportPopupWindowPull extends BasePopupWindowWithMask{
    private View contentView;
    private ReportPopupWindowPull.OnItemClickListener listener;
    private TextView tv;
    private FixGridLayout ll_add_view;
    private int reportId=-1;

    public interface OnItemClickListener {
        void OnItemClick(View v);
    }
    public void setOnItemClickListener(ReportPopupWindowPull.OnItemClickListener listener) {
        this.listener = listener;
    }
    public ReportPopupWindowPull(Context context) {
        super(context);
        initListener();
    }
    @Override
    protected View initContentView() {
        contentView = LayoutInflater.from(context).inflate(R.layout.popwindow_site, null, false);
        tv = contentView.findViewById(R.id.tv_1);
        ll_add_view = contentView.findViewById(R.id.ll_add_view);
        return contentView;
    }

    private void initListener() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemClick(tv);
                }
                dismiss();
            }
        });
    }
    @Override
    protected int initHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }
    @Override
    protected int initWidth() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    public void showAtLocation(View anchor) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(15,10,15,10);
        String getReportModelList = SharedUtils.singleton().get("getReportModelList", "");
        if(StringUtil.isNotBlank(getReportModelList)){
            try {
                final JSONArray array = new JSONArray(getReportModelList);
                for(int i=0;i<array.length();i++){
                    final JSONObject object = array.getJSONObject(i);
                    if(StringUtil.isNotBlank(object.getString("reportName"))){
                        final TextView crack_down = new TextView(context);
                        crack_down.setPadding(20,10,20,10);
                        crack_down.setBackground(context.getResources().getDrawable(R.drawable.shape_add_label_selected));
                        crack_down.setTextColor(context.getResources().getColor(R.color.app_background));
                        crack_down.setTextSize(14);
                        crack_down.setText(object.getString("reportName"));
                        crack_down.setLayoutParams(params);
                        crack_down.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    reportId = object.getInt("reportId");
                                    if(listener!=null){
                                        listener.OnItemClick(crack_down);
                                    }
                                    dismiss();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        ll_add_view.addView(crack_down);
                        LogUtil.w("ll_add_view.getChildCount():"+ll_add_view.getChildCount());
                        if(ll_add_view.getChildCount()==1){
                            super.showAtLocation(anchor,contentView);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void setListenerDate(View v, int postId){
        if(v.getId()==R.id.tv_1){
            NetUtil.saveCollect(false,
                    Integer.valueOf(postId), new NetUtil.SaveCollectImp() {
                        @Override
                        public void finishCollect(String str,boolean status) {
                            if(!str.equals(Constants.COLLECT_ERROR)){
                                DialogUtils.showDialogPraise(context,2,status,0);
                            }
                        }
                    });
        }else{
            if(reportId==-1){
                return;
            }
            int userCardStatus = SharedUtils.singleton().get("userCardStatus", 0);
            if(userCardStatus!=2){
                DialogUtils.showDialogHint(context, "请先实名认证",false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        IntentUtil.startActivity(MineApproveSubmitiCertificateActivity.class);
                    }
                });
                return;
            }
            NetUtil.saveReport(1, postId, reportId, new NetUtil.SaveCollectImp() {
                @Override
                public void finishCollect(String code, boolean status) {
                    if(!code.equals(Constants.COLLECT_ERROR)){
                        DialogUtils.showDialogPraise(context,4,status,0);
                    }
                }
            });
        }
    }
}
