package com.secretk.move.ui.fragment;

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
    @BindView(R.id.tv_website_url)
    TextView tvWebsiteUrl;
    @BindView(R.id.tv_project_type_name)
    TextView tvProjectTypeName;
    @BindView(R.id.tv_whitepaper_url)
    TextView tvWhitepaperUrl;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
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
    private ProjectIntroAdapter adapter;
    private int submitUserId;
    private List<ProjectHomeBean.DataBean.ProjectBean.ActiveUsersBean> activeUsers;

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
            submitUserId = projectIntro.getSubmitUserId();
            tvProjectDesc.setText(projectIntro.getProjectDesc());
            tvProjectEnglishName.setText(projectIntro.getProjectEnglishName());
            tvProjectChineseName.setText(projectIntro.getProjectChineseName());
            tvIssueDate.setText(StringUtil.getTimeToM(projectIntro.getIssueDate()));
            tvWebsiteUrl.setText(projectIntro.getWebsiteUrl());
            tvProjectTypeName.setText(projectIntro.getProjectTypeName());
            tvWhitepaperUrl.setText(projectIntro.getWhitepaperUrl());
            activeUsers = projectIntro.getActiveUsers();
            if(activeUsers!=null && activeUsers.size()>0){
                llActiveUser.setVisibility(View.VISIBLE);
                adapter.setData(activeUsers);
            }
            ProjectHomeBean.DataBean.ProjectBean.OwnerBean owner = projectIntro.getOwner();
            if (owner != null) {
                GlideUtils.loadCircleUserUrl(getActivity(),ivIcon, Constants.BASE_IMG_URL + owner.getIcon());
                tvUserName.setText(owner.getUserName());
                tvUserSignature.setText(owner.getUserSignature());
                //0 显示 关注按钮； 1--显示取消关注 按钮 ；2 不显示按钮
                if(owner.getFollowStatus()==0){
                    tvFollowStatus.setSelected(false);
                    tvFollowStatus.setText(getResources().getString(R.string.follow_status_0));
                }else if(owner.getFollowStatus() == 1){
                    tvFollowStatus.setSelected(true);
                    tvFollowStatus.setText(getResources().getString(R.string.follow_status_1));
                }else{
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

    @OnClick({R.id.tv_follow_status, R.id.rl_station_agent,R.id.tv_website_url})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_follow_status:
                tvFollowStatus.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatus,
                        Constants.SaveFollow.USER, submitUserId, new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvFollowStatus.setEnabled(true);
                                if(!str.equals(Constants.FOLLOW_ERROR)){
                                    tvFollowStatus.setText(str);
                                }
                            }
                        });
                break;
            case R.id.rl_station_agent:
                IntentUtil.startHomeActivity(submitUserId);
                break;
            case R.id.tv_website_url:
                if(StringUtil.isNotBlank(tvWebsiteUrl.getText().toString().trim())){
                    IntentUtil.startWebViewActivity(tvWebsiteUrl.getText().toString().trim(),"官网");
                }
                break;
        }
    }
}
