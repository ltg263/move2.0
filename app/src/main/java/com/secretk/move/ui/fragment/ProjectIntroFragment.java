package com.secretk.move.ui.fragment;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.ProjectHomeBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.ProjectIntroAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:04
 * 邮箱；ltg263@126.com
 * 描述：项目主页--简介
 */


public class ProjectIntroFragment extends LazyFragment implements ItemClickListener {

    @BindView(R.id.tv_project_desc)
    TextView tvProjectDesc;
    @BindView(R.id.tv_project_english_name)
    TextView tvProjectEnglishName;
    @BindView(R.id.tv_project_chinese_name)
    TextView tvProjectChineseName;
    @BindView(R.id.tv_issue_date)
    TextView tvIssueDate;
    @BindView(R.id.tv_issue_ltzl)
    TextView tvIssueLtzl;
    @BindView(R.id.tv_issue_fxzl)
    TextView tvIssueFxzl;
    @BindView(R.id.tv_all_contact)
    TextView tvAllContact;
    @BindView(R.id.tv_website_url)
    TextView tvWebsiteUrl;
    @BindView(R.id.tv_project_type_name)
    TextView tvProjectTypeName;
    @BindView(R.id.tv_whitepaper_url)
    TextView tvWhitepaperUrl;
    @BindView(R.id.tv_ww_gw)
    TextView tvWwGw;
    @BindView(R.id.tv_ww_bps)
    TextView tvWwBps;
    @BindView(R.id.tv_ww_git)
    TextView tvWwGit;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.iv_model_type)
    ImageView ivModelType;
    @BindView(R.id.rl_station_agent)
    RelativeLayout rlStationAgent;
    @BindView(R.id.tv_follow_status)
    TextView tvFollowStatus;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_signature)
    TextView tvUserSignature;
    @BindView(R.id.rv_active_users)
    RecyclerView rvActiveUsers;
    @BindView(R.id.ll_active_user)
    LinearLayout llActiveUser;
    @BindView(R.id.iv_icon_gf)
    ImageView ivIconGf;
    @BindView(R.id.iv_model_type_gf)
    ImageView ivModelTypeGf;
    @BindView(R.id.rl_f_gf)
    RelativeLayout rlFGf;
    @BindView(R.id.view_center_gf)
    View viewCenterGf;
    @BindView(R.id.tv_follow_status_gf)
    TextView tvFollowStatusGf;
    @BindView(R.id.tv_user_name_gf)
    TextView tvUserNameGf;
    @BindView(R.id.tv_user_signature_gf)
    TextView tvUserSignatureGf;
    @BindView(R.id.ll_gfzh)
    LinearLayout llGfzh;
    @BindView(R.id.rl_gfzh)
    RelativeLayout rlGfzh;
    private ProjectIntroAdapter adapter;
    private ProjectHomeBean.DataBean.ProjectBean projectIntro;
    private int submitUserId;
    private List<ProjectHomeBean.DataBean.ProjectBean.ActiveUsersBean> activeUsers;
    private int ellipsisCount;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_project_intro;
    }

    @Override
    public void initViews() {
        setVerticalManager(rvActiveUsers);
        adapter = new ProjectIntroAdapter(getActivity());
        rvActiveUsers.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onFirstUserVisible() {

    }

    public void initUiData(ProjectHomeBean.DataBean.ProjectBean projectIntro) {
        if (projectIntro != null) {
            this.projectIntro = projectIntro;
            submitUserId = projectIntro.getSubmitUserId();
            tvProjectDesc.setText(projectIntro.getProjectDesc());
            tvProjectEnglishName.setText(projectIntro.getProjectEnglishName());
            tvProjectChineseName.setText(projectIntro.getProjectChineseName());
            tvIssueDate.setText(StringUtil.getTimeToM(projectIntro.getIssueDate()));
            tvWebsiteUrl.setText(projectIntro.getWebsiteUrl());
            tvProjectTypeName.setText(projectIntro.getProjectTypeName());
            tvWhitepaperUrl.setText(projectIntro.getWhitepaperUrl());
            tvIssueLtzl.setText(String.valueOf(projectIntro.getBsjCirculateData()));
            tvIssueFxzl.setText(String.valueOf(projectIntro.getIssueNum()));
            activeUsers = projectIntro.getActiveUsers();
            if(projectIntro.getProjectUserId()!=0){
                llGfzh.setVisibility(View.VISIBLE);
                GlideUtils.loadCircleProjectUrl(getActivity(),ivIconGf,Constants.BASE_IMG_URL+projectIntro.getIcon());
                StringUtil.getUserType(projectIntro.getProjectUserType(),ivModelTypeGf);
                tvUserNameGf.setText(StringUtil.getBeanString(projectIntro.getUserName()));
                tvUserSignatureGf.setText(StringUtil.getBeanString(projectIntro.getUserSignature()));

                if (SharedUtils.getUserId() != projectIntro.getProjectUserId()) {
                    if (projectIntro.getProjectFollowStatus() == 1) {
                        tvFollowStatusGf.setSelected(true);
                        tvFollowStatusGf.setText(getResources().getString(R.string.follow_status_1));
                    }else{
                        tvFollowStatusGf.setSelected(false);
                        tvFollowStatusGf.setText(getResources().getString(R.string.follow_status_0));
                    }
                } else {
                    tvFollowStatusGf.setVisibility(View.GONE);
                }
            }

            if (StringUtil.isNotBlank(projectIntro.getWebsiteUrl())) {
                tvWwGw.setTextColor(getResources().getColor(R.color.app_background));
                tvWwGw.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            }
            if (StringUtil.isNotBlank(projectIntro.getGithub())) {
                tvWwGit.setTextColor(getResources().getColor(R.color.app_background));
                tvWwGit.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            }
            if (StringUtil.isNotBlank(projectIntro.getWhitepaperUrl())) {
                tvWwBps.setTextColor(getResources().getColor(R.color.app_background));
                tvWwBps.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            }
            ellipsisCount = tvProjectDesc.getLayout().getEllipsisCount(tvProjectDesc.getLineCount() - 1);
            //ellipsisCount>0说明没有显示全部，存在省略部分。
            if (ellipsisCount > 0) {
                tvAllContact.setVisibility(View.VISIBLE);
            }
            if (activeUsers != null && activeUsers.size() > 0) {
                llActiveUser.setVisibility(View.VISIBLE);
                adapter.setData(activeUsers);
            }
            ProjectHomeBean.DataBean.ProjectBean.OwnerBean owner = projectIntro.getOwner();
            if (owner != null) {
                GlideUtils.loadCircleUserUrl(getActivity(), ivIcon, Constants.BASE_IMG_URL + owner.getIcon());
                StringUtil.getUserType(owner.getUserType(), ivModelType);
                tvUserName.setText(owner.getUserName());
                tvUserSignature.setText(owner.getUserSignature());
                //0 显示 关注按钮； 1--显示取消关注 按钮 ；2 不显示按钮
                if (SharedUtils.getUserId() != owner.getUserId()) {
                    if (owner.getFollowStatus() == 0) {
                        tvFollowStatus.setSelected(false);
                        tvFollowStatus.setText(getResources().getString(R.string.follow_status_0));
                    } else if (owner.getFollowStatus() == 1) {
                        tvFollowStatus.setSelected(true);
                        tvFollowStatus.setText(getResources().getString(R.string.follow_status_1));
                    } else {
                        tvFollowStatus.setVisibility(View.GONE);
                    }
                } else {
                    tvFollowStatus.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onItemClick(View view, int postion) {
        IntentUtil.startHomeActivity(activeUsers.get(postion).getUserId());
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

    @OnClick({R.id.tv_follow_status, R.id.rl_station_agent, R.id.tv_website_url, R.id.tv_all_contact,
            R.id.tv_ww_gw, R.id.tv_ww_git, R.id.tv_ww_bps,R.id.rl_gfzh,R.id.tv_follow_status_gf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_follow_status:
                tvFollowStatus.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatus,
                        Constants.SaveFollow.USER, submitUserId, new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvFollowStatus.setEnabled(true);
                                if (!str.equals(Constants.FOLLOW_ERROR)) {
                                    tvFollowStatus.setText(str);
                                }
                            }
                        });
                break;
            case R.id.rl_station_agent:
                IntentUtil.startHomeActivity(submitUserId);
                break;
            case R.id.tv_all_contact:
                //ellipsisCount>0说明没有显示全部，存在省略部分。
                if (ellipsisCount > 0) {
                    //展示全部，按钮设置为点击收起。
                    tvAllContact.setVisibility(View.GONE);
                    tvProjectDesc.setMaxHeight(getResources().getDisplayMetrics().heightPixels);
                }
                break;
            case R.id.tv_website_url:
                if (StringUtil.isNotBlank(tvWebsiteUrl.getText().toString().trim())) {
                    IntentUtil.startWebViewActivity(tvWebsiteUrl.getText().toString().trim(), "官网");
                }
                break;
            case R.id.tv_ww_gw:
                if (StringUtil.isNotBlank(projectIntro.getWhitepaperUrl())) {
                    IntentUtil.startWebViewActivity(tvWebsiteUrl.getText().toString().trim(), "官网");
                }
                break;
            case R.id.tv_ww_bps:
                if (StringUtil.isNotBlank(projectIntro.getWebsiteUrl())) {
                    IntentUtil.startWebViewActivity(tvWebsiteUrl.getText().toString().trim(), "白皮书");
                }
                break;
            case R.id.tv_ww_git:
                if (StringUtil.isNotBlank(projectIntro.getGithub())) {
                    IntentUtil.startWebViewActivity(tvWebsiteUrl.getText().toString().trim(), "Github");
                }
                break;
            case R.id.rl_gfzh:
                IntentUtil.startHomeActivity(projectIntro.getProjectUserId());
                break;
            case R.id.tv_follow_status_gf:
                tvFollowStatusGf.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatusGf,
                        Constants.SaveFollow.USER, projectIntro.getProjectUserId(), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvFollowStatusGf.setEnabled(true);
                                if (!str.equals(Constants.FOLLOW_ERROR)) {
                                    tvFollowStatusGf.setText(str);
                                }
                            }
                        });
                break;
        }
    }
}
