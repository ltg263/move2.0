package com.secretk.move.ui.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PatternUtils;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class FindFragmentRecyclerHolder extends RecyclerViewBaseHolder {
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
    String token = SharedUtils.singleton().get("token", "");

    public FindFragmentRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    //1-按关注数量倒序；2-按名称排序
    public void setData(List<SearchedBean.DataBean.ProjectsBean.RowsBean> list, int position, int type, final Context context) {
        final SearchedBean.DataBean.ProjectsBean.RowsBean currenBean = list.get(position);
        GlideUtils.loadCircleProjectUrl(context, img, Constants.BASE_IMG_URL + StringUtil.getBeanString(currenBean.getProjectIcon()));
        tvCode.setText(StringUtil.getBeanString(currenBean.getProjectCode()) + "/");
        tvName.setText(StringUtil.getBeanString(currenBean.getProjectChineseName()));
        if (currenBean.getTotalScore() != 0) {
            tvTotalScore.setVisibility(View.VISIBLE);
            tvTotalScore.setText(String.valueOf(currenBean.getTotalScore()));
        } else {
            tvTotalScore.setVisibility(View.GONE);
        }
        switch (type) {
            case Constants.TOPIC_SORT_BY_NUM:
                sortByNum();
                break;
            case Constants.TOPIC_SORT_BY_NAME:
                sortByName(currenBean, list, position);
                break;
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
        tvIsFollw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedUtils.singleton().put("isFollowerSky",true);
                SharedUtils.singleton().put("isFollowerFx",true);
                boolean isLogin = SharedUtils.singleton().get(Constants.IS_LOGIN_KEY, false);
                if (isLogin) {
                    if (getString().equals(context.getString(R.string.follow_status_1))) {
                        http(Constants.CANCEL_FOLLOW, currenBean.getProjectId(),context,currenBean);
                    } else {
                        http(Constants.SAVE_FOLLOW, currenBean.getProjectId(),context,currenBean);
                    }
                } else {
                    IntentUtil.startActivity(LoginHomeActivity.class);
                }
            }
        });
    }

    public void sortByName(SearchedBean.DataBean.ProjectsBean.RowsBean currenBean, List<SearchedBean.DataBean.ProjectsBean.RowsBean> list, int position) {
        if (position == 0) {
            if (PatternUtils.isLetter(currenBean.getProjectCode()
                    .subSequence(0, 1).toString())) {
                tvSpell.setText(currenBean.getProjectCode()
                        .subSequence(0, 1).toString().toUpperCase());
            } else {
                tvSpell.setText("#");
            }
            tvSpell.setVisibility(View.VISIBLE);
        } else {
            SearchedBean.DataBean.ProjectsBean.RowsBean lastBean = list.get(position - 1);
            boolean b = lastBean.getProjectCode().subSequence(0, 1)
                    .equals(currenBean.getProjectCode().subSequence(0, 1));
            if (b) {
                tvSpell.setVisibility(View.GONE);
            } else if (PatternUtils.isLetter(currenBean.getProjectCode()
                    .subSequence(0, 1).toString())
                    && b == false) {
                tvSpell.setVisibility(View.VISIBLE);
                tvSpell.setText(currenBean.getProjectCode()
                        .subSequence(0, 1).toString().toUpperCase());
            } else if (PatternUtils.isLetter(currenBean.getProjectCode()
                    .subSequence(0, 1).toString()) == false
                    && b == false) {
                tvSpell.setVisibility(View.VISIBLE);
            }
        }
    }

    public void sortByNum() {
        tvSpell.setVisibility(View.GONE);
    }

    public void http(String url, int id, final Context context, final SearchedBean.DataBean.ProjectsBean.RowsBean currenBean) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("followType", 1);
            node.put("followedId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(url)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, BaseRes.class, new HttpCallBackImpl<BaseRes>() {
            @Override
            public void onCompleted(BaseRes bean) {
                if (bean.getCode() == 0) {
                    if (getString().equals(context.getString(R.string.follow_status_1))) {
                        tvIsFollw.setText(context.getString(R.string.follow_status_0));
                        tvIsFollw.setSelected(false);
                        tvIsFollw.setPressed(false);
                        tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
                        currenBean.setFollowStatus(0);
                    } else {
                        tvIsFollw.setText(context.getString(R.string.follow_status_1));
                        tvIsFollw.setSelected(true);
                        tvIsFollw.setPressed(true);
                        tvIsFollw.setTextColor(Color.parseColor("#3b88f6"));
                        currenBean.setFollowStatus(1);
                    }
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    public String getString() {
        return tvIsFollw.getText().toString();
    }
}
