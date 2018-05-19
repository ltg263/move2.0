package com.secretk.move.ui.holder;

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
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PatternUtils;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class TopicFragmentRecyclerHolder extends RecyclerViewBaseHolder {
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
    String token = SharedUtils.singleton().get("token", "");
    public TopicFragmentRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    //1-按关注数量倒序；2-按名称排序
    public void setData(List<SearchedBean.Projects> list, int position,int type ){
        final SearchedBean.Projects  currenBean= list.get(position);
        GlideUtils.loadCircleUrl(img, Constants.BASE_IMG_URL + currenBean.getProjectIcon());
        tvCode.setText(currenBean.getProjectCode()+"/");
        tvName.setText(currenBean.getProjectChineseName());
        switch (type){
            case Constants.TOPIC_SORT_BY_NUM:
                sortByNum();
                break;
            case Constants.TOPIC_SORT_BY_NAME:
                sortByName(currenBean,list,position);
                break;
        }
        tvFollws.setText(currenBean.getFollowerNum()+"关注");
        if (0 == currenBean.getFollowStatus()) {
            tvIsFollw.setText("+ 关注");
            tvIsFollw.setSelected(false);
            tvIsFollw.setPressed(false);
        } else if (1 == currenBean.getFollowStatus()) {
            tvIsFollw.setText("已关注");
            tvIsFollw.setSelected(true);
            tvIsFollw.setPressed(true);
        } else {
            tvIsFollw.setVisibility(View.GONE);
        }
        tvIsFollw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getString().equals("已关注")) {
                    http(Constants.CANCEL_FOLLOW,currenBean.getProjectId());
                } else {
                    http(Constants.SAVE_FOLLOW,currenBean.getProjectId());
                }
            }
        });
    }
    public void sortByName(SearchedBean.Projects  currenBean,List<SearchedBean.Projects> list, int position){
        if (position == 0) {
            if (PatternUtils.isLetter(currenBean.getProjectCode()
                    .subSequence(0, 1).toString())) {
                tvSpell.setText( currenBean.getProjectCode()
                        .subSequence(0, 1).toString().toUpperCase());
            } else {
                tvSpell.setText( "#");
            }
            tvSpell.setVisibility(View.VISIBLE);
        } else {
            SearchedBean.Projects  lastBean=list.get(position-1);
            boolean b =lastBean.getProjectCode().subSequence(0, 1)
                    .equals(currenBean.getProjectCode().subSequence(0, 1));
            if (b) {
                tvSpell.setVisibility(View.GONE);
            } else if (PatternUtils.isLetter(currenBean.getProjectCode()
                    .subSequence(0, 1).toString())
                    && b == false) {
                tvSpell.setVisibility(View.VISIBLE);
                tvSpell.setText( currenBean.getProjectCode()
                        .subSequence(0, 1).toString().toUpperCase());
            } else if (PatternUtils.isLetter(currenBean.getProjectCode()
                    .subSequence(0, 1).toString()) == false
                    && b == false) {
                tvSpell.setVisibility(View.VISIBLE);
            }
        }
    }
    public void sortByNum(){
        tvSpell.setVisibility(View.GONE);
    }

    public void http(String url,int id) {
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
                if (bean.getCode()==0){
                    if (getString().equals("已关注")) {
                        tvIsFollw.setText("+ 关注");
                        tvIsFollw.setSelected(false);
                        tvIsFollw.setPressed(false);
                    } else {
                        tvIsFollw.setText("已关注");
                        tvIsFollw.setSelected(true);
                        tvIsFollw.setPressed(true);
                    }
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }
    public String getString(){
        return tvIsFollw.getText().toString();
    }
}
