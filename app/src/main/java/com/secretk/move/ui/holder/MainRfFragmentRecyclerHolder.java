package com.secretk.move.ui.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MainRfBean;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.customview.NoDispatchTouchRecyclerView;
import com.secretk.move.ui.adapter.ImgExtraHorizontalAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class MainRfFragmentRecyclerHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img_organization)
    public ImageView img_organization;
    @BindView(R.id.tvName)
    public TextView tvName;
    @BindView(R.id.tv_english_name)
    public TextView tv_english_name;

    @BindView(R.id.tvIsFollw)
    public TextView tvIsFollw;
    @BindView(R.id.tvTime)
    public TextView tvTime;
    @BindView(R.id.tvTitle)
    public TextView tvTitle;
    @BindView(R.id.tvScore)
    public TextView tvScore;
    @BindView(R.id.tvDesc)
    public TextView tvDesc;
    @BindView(R.id.img_user_head)
    public ImageView img_user_head;
    @BindView(R.id.tvUser)
    public TextView tvUser;
    @BindView(R.id.tvPraise)
    public TextView tvPraise;
    @BindView(R.id.tvComments)
    public TextView tvComments;
    @BindView(R.id.ry_horizontal)
    public NoDispatchTouchRecyclerView ry_horizontal;

    @BindView(R.id.rl_project)
    public RelativeLayout rl_project;
    @BindView(R.id.ll_user)
    LinearLayout ll_user;
    @BindView(R.id.tvUserDynamic)
    TextView tvUserDynamic;

    /**
     * 横向多张图的adapter
     */
    public ImgExtraHorizontalAdapter imgExtraHorizontalAdapter;


    /**
     * 横向多张图layoutManager参数设置
     */
    private LinearLayoutManager layoutManagerImgExtra;
    String token = SharedUtils.singleton().get("token", "");
    public MainRfFragmentRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        img_organization.setOnClickListener(this);
        tvName.setOnClickListener(this);
        tv_english_name.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        ll_user.setOnClickListener(this);
    }
    public  void  setData(final MainRfBean.Rows bean){
        GlideUtils.loadCircleUrl(img_organization, Constants.BASE_IMG_URL + bean.getProjectIcon());
        tvName.setText(bean.getProjectChineseName());
        tv_english_name.setText("/"+bean.getProjectEnglishName());
        tvTime.setText(StringUtil.getTimeToM(bean.getCreateTime()));
        tvIsFollw.setVisibility(View.VISIBLE);
        if (0 == bean.getFollowStatus()) {
            tvIsFollw.setText("+ 关注");
        } else if (1 == bean.getFollowStatus()) {
            tvIsFollw.setText("已关注");
        } else {
            tvIsFollw.setVisibility(View.GONE);
        }
        tvTitle.setText(bean.getPostTitle());
        tvScore.setText(bean.getTotalScore()+"分");
        tvDesc.setText(bean.getPostShortDesc());

        GlideUtils.loadCircleUrl(img_user_head, Constants.BASE_IMG_URL + bean.getCreateUserIcon());
        tvUser.setText(bean.getCreateUserName());
        tvPraise.setText(bean.getPraiseNum() + "");
        tvComments.setText(bean.getCommentsNum() + "");

        if (ry_horizontal.getAdapter()==null){
            imgExtraHorizontalAdapter = new ImgExtraHorizontalAdapter();
            //设置成横向
            layoutManagerImgExtra = new LinearLayoutManager(UiUtils.getContext());
            layoutManagerImgExtra.setOrientation(LinearLayoutManager.HORIZONTAL);
            ry_horizontal.setAdapter(imgExtraHorizontalAdapter);
            ry_horizontal.setLayoutManager(layoutManagerImgExtra);
        }
        imgExtraHorizontalAdapter.setData(bean);

        tvIsFollw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getString().equals("已关注")) {
                  http(Constants.CANCEL_FOLLOW,bean.getProjectId());
                } else {
                    http(Constants.SAVE_FOLLOW,bean.getProjectId());
                }
            }
        });
       switch (bean.getPostType()){

           case 1:
               tvUserDynamic.setText("发表了评测");
               break;
           case 2:
               tvUserDynamic.setText("发表了讨论");
               break;
           case 3:
               tvUserDynamic.setText("发表了文章");
               break;
       }
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
                    } else {
                        tvIsFollw.setText("已关注");
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
    private int type;
    public void setAdapterType(int type) {
        this.type=type;
    }
}
