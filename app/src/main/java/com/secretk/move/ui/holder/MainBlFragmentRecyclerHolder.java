package com.secretk.move.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MainGzBean;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.ui.activity.ImageViewVpAcivity;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.adapter.ImagesAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.TimeToolUtils;
import com.secretk.move.view.Clickable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class MainBlFragmentRecyclerHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img_organization)
    ImageView imgOrganization;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvIsFollw)
    TextView tvIsFollw;
    @BindView(R.id.rl_project)
    RelativeLayout rlProject;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvDesc)
    TextView tvDesc;
    @BindView(R.id.iv_file_name)
    ImageView ivFileName;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.rl_context)
    RelativeLayout rlContext;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.tv_crack_down)
    TextView tvCrackDown;
    @BindView(R.id.ll_below)
    RelativeLayout llBelow;
    @BindView(R.id.tvPraise)
    TextView tvPraise;
    @BindView(R.id.img_comment)
    ImageView imgComment;
    @BindView(R.id.tvComments)
    TextView tvComments;
    private ImagesAdapter imagesadapter;
    private Context mContext;
    public MainBlFragmentRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvImg.setLayoutManager(layoutManager);
    }
    public void setData(final MainGzBean.DataBean.FollowsBean.RowsBean bean, final Context context){
        this.mContext=context;
        //actionType  //1 关注的用户 点赞帖子  2关注的用户 发表帖子  3关注的用户 关注项目 4关注的项目下发表的帖子
        GlideUtils.loadCircleUserUrl(context,imgOrganization, Constants.BASE_IMG_URL + StringUtil.getBeanString(bean.getCreateUserIcon()));
        tvName.setText(bean.getCreateUserName());
        tvTime.setText(TimeToolUtils.convertTimeToFormat(bean.getCreateTime()));
        tvProjectCode.setText(bean.getProjectCode());
        showPostDesc(bean);

        tvIsFollw.setVisibility(View.VISIBLE);
        if (0 == bean.getFollowStatus()) {
            tvIsFollw.setText(context.getString(R.string.follow_status_0));
            tvIsFollw.setSelected(false);
            tvIsFollw.setPressed(false);
            tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
        } else if (1 == bean.getFollowStatus()) {
            tvIsFollw.setText(context.getString(R.string.follow_status_1));
            tvIsFollw.setSelected(true);
            tvIsFollw.setPressed(true);
            tvIsFollw.setTextColor(Color.parseColor("#3b88f6"));
        } else {
            tvIsFollw.setText(context.getString(R.string.follow_status_0));
            tvIsFollw.setSelected(false);
            tvIsFollw.setPressed(false);
            tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
        }
        if (SharedUtils.getUserId()==bean.getCreateUserId()){
            tvIsFollw.setVisibility(View.GONE);
        }
        //关注
        tvIsFollw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                if (getString().equals("已关注")) {
                    http(context,Constants.CANCEL_FOLLOW,bean.getCreateUserId());
                } else {
                    http(context,Constants.SAVE_FOLLOW,bean.getCreateUserId());
                }
            }
        });
        //用户
        rlProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                IntentUtil.startHomeActivity(bean.getCreateUserId());
            }
        });
        rlContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedUtils.getLoginZt()) {
                    int postId = bean.getPostId();
                    int postType = bean.getPostType();
                    IntentUtil.go2DetailsByType(postType, String.valueOf(postId));
                } else {
                    IntentUtil.startActivity(LoginHomeActivity.class);
                }
            }
        });
        ivFileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,ImageViewVpAcivity.class);
                intent.putParcelableArrayListExtra("lists", imageLists);
                intent.putExtra("position",0);
                context.startActivity(intent);
            }
        });

    }
    public void http(final Context context, String url, int id) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", SharedUtils.getToken());
            node.put("followType", 3);
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
                    if (getString().equals(context.getString(R.string.follow_status_1))) {
                        tvIsFollw.setText(context.getString(R.string.follow_status_0));
                        tvIsFollw.setSelected(false);
                        tvIsFollw.setPressed(false);
                        tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
                    } else {
                        tvIsFollw.setText(context.getString(R.string.follow_status_1));
                        tvIsFollw.setSelected(true);
                        tvIsFollw.setPressed(true);
                        tvIsFollw.setTextColor(Color.parseColor("#3b88f6"));
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

    ArrayList<PostDataInfo> imageLists;
    public void showPostDesc(MainGzBean.DataBean.FollowsBean.RowsBean bean) {
        tvTitle.setText(StringUtil.getBeanString(bean.getPostTitle()));
        tvDesc.setText(StringUtil.getBeanString(bean.getPostShortDesc()));
        tvPraise.setText(bean.getPraiseNum() + "");
        tvComments.setText(bean.getCommentsNum() + "");

        imagesadapter = new ImagesAdapter(mContext);
        rvImg.setAdapter(imagesadapter);


        if(StringUtil.isNotBlank(bean.getPostSmallImages())){
            try {
                //{"fileUrl":"/upload/posts/201805/1.jpg","fileName":"进度讨论","extension":"jpg"},
                JSONArray images = new JSONArray(bean.getPostSmallImages());
                imageLists = new ArrayList<>();
                for (int i = 0; i < images.length(); i++) {
                    JSONObject strObj = images.getJSONObject(i);
                    PostDataInfo info = new PostDataInfo();
                    info.setUrl(StringUtil.getBeanString(strObj.getString("fileUrl")));
                    info.setName(StringUtil.getBeanString(strObj.getString("fileName")));
                    info.setTitle(StringUtil.getBeanString(strObj.getString("extension")));
                    imageLists.add(info);
                }
                if (imageLists.size() != 0) {
                    if (imageLists.size() == 1) {
                        ivFileName.setVisibility(View.VISIBLE);
                        rvImg.setVisibility(View.GONE);
                        GlideUtils.loadSideMaxImage(mContext, ivFileName, Constants.BASE_IMG_URL + imageLists.get(0).getUrl());
                    } else {
                        ivFileName.setVisibility(View.GONE);
                        rvImg.setVisibility(View.VISIBLE);
                        imagesadapter.setData(imageLists);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (StringUtil.isNotBlank(bean.getTagInfos())&& bean.getTagInfos().contains("tagName")) {
            try {
                JSONArray object = new JSONArray(bean.getTagInfos());
                //[{"tagId":1,"tagName":"进度讨论"},{"tagId":3,"tagName":"项目前景讨论"},{"tagId":4,"tagName":"打假"}]
                String tagAll = "";
                String tagOnly[] = new String[object.length()];
                for (int i = 0; i < object.length(); i++) {
                    JSONObject strObj = object.getJSONObject(i);
                    tagOnly[i] = "#" + strObj.getString("tagName") + "#";
                    tagAll += "#" + strObj.getString("tagName") + "#   ";
                }
                Clickable.getSpannableString(tagAll, tagOnly, tvCrackDown, new Clickable.ClickListener() {
                    @Override
                    public void setOnClick(String name) {
                        //ToastUtils.getInstance().show(name);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
