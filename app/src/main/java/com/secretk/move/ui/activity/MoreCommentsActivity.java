package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.CommonCommentsBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.MoreCommentsAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 作者： litongge
 * 时间： 2018/5/2 18:53
 * 邮箱；ltg263@126.com
 * 描述：评论详情------更多评论
 */
public class MoreCommentsActivity extends BaseActivity {


    @BindView(R.id.iv_commented_user_icon)
    ImageView ivCommentedUserIcon;
    @BindView(R.id.tv_commented_user_name)
    TextView tvCommentedUserName;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_praise_num)
    TextView tvPraiseNum;
    @BindView(R.id.rl_ge_ren)
    RelativeLayout rlGeRen;
    @BindView(R.id.tv_comment_content)
    TextView tvCommentContent;
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.et_message)
    EditText etMessage;
    @BindView(R.id.but_send)
    Button butSend;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private MoreCommentsAdapter adapter;
    private int commentsId;
    private int userId;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_comments_more;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle("评论详情");
        mMenuInfos.add(0, new MenuInfo(R.string.share, "分享", R.drawable.ic_share));
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        setVerticalManager(rvReview);
        adapter = new MoreCommentsAdapter(this);
        rvReview.setAdapter(adapter);
    }

    protected void initData() {
        CommonCommentsBean commentsBean = getIntent().getParcelableExtra("commentsBean");
        GlideUtils.loadCircleUrl(ivCommentedUserIcon, Constants.BASE_IMG_URL + commentsBean.getCommentUserIcon());
        commentsId = commentsBean.getCommentsId();
        userId= commentsBean.getCommentUserId();
        tvCommentedUserName.setText(commentsBean.getCommentUserName());
        tvCreateTime.setText(commentsBean.getFloor() + "楼    " + StringUtil.getTimeToM(commentsBean.getCreateTime()));
        tvCommentContent.setText(commentsBean.getCommentContent());
        tvPraiseNum.setText(String.valueOf(commentsBean.getPraiseNum()));
        //"praiseStatus":0,//点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
        LogUtil.w("commentsBean.getPraiseStatus():"+commentsBean.getPraiseStatus());
        if (commentsBean.getPraiseStatus() == 1) {
            tvPraiseNum.setSelected(false);
        } else if (commentsBean.getPraiseStatus() == 0) {
            tvPraiseNum.setSelected(true);
        } else if (commentsBean.getPraiseStatus() == 3) {
            tvPraiseNum.setText("****");
        }
        List<CommonCommentsBean.ChildCommentsListBean> commentsList = commentsBean.getChildCommentsList();
        if (commentsList != null && commentsList.size() > 0) {
            adapter.setData(commentsList);
        }
    }

    @OnClick({R.id.tv_praise_num, R.id.rl_ge_ren, R.id.but_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_praise_num:
                NetUtil.addCommentsPraise(tvPraiseNum.isSelected(), commentsId, new NetUtil.SaveFollowImpl() {
                    @Override
                    public void finishFollow(String str,boolean status) {
                            tvPraiseNum.setSelected(status);
                    }
                });
                break;
            case R.id.rl_ge_ren:
                IntentUtil.startHomeActivity(userId);
                break;
            case R.id.but_send:
                break;
        }
    }
}
