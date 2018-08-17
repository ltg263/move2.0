package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.CommonCommentsBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.MoreCommentsBean;
import com.secretk.move.ui.adapter.MoreCommentsAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.KeybordS;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.TimeToolUtils;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;
import com.secretk.move.view.InputMethodLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 作者： litongge
 * 时间： 2018/5/2 18:53
 * 邮箱；ltg263@126.com
 * 描述：所有的 评论详情------更多评论
 */
public class MoreCommentsActivity extends BaseActivity{

    @BindView(R.id.input_method_layout)
    InputMethodLayout inputMethodLayout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_commented_user_icon)
    ImageView ivCommentedUserIcon;
    @BindView(R.id.iv_model_type)
    ImageView iv_model_type;
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
    @BindView(R.id.et_content)
    EditText etMessage;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.rl_select_yse)
    RelativeLayout rlSelectYse;
    @BindView(R.id.rl_select_no)
    RelativeLayout rlSelectNo;
    private MoreCommentsAdapter adapter;
    private int commentsId;
    private int userId;
    private int postId;
    private int parentCommentsId;
    private int becommentedId;
    boolean isBianHua = false;
    private int praiseNum;
    private int pageIndex=1;
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
//        mMenuInfos.add(0, new MenuInfo(R.string.share, "分享", R.drawable.ic_share));
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        setVerticalManager(rvReview);
        adapter = new MoreCommentsAdapter(this);
        rvReview.setAdapter(adapter);
        initRefresh();
        inputMethod();
        StringUtil.etSearchChangedListener(etMessage, null, new StringUtil.EtChange() {
            @Override
            public void etYes() {
                if(!etMessage.getText().toString().contains(strLs) && !strLs.equals("find_apk")){
                    parentCommentsId = 0;
                    strLs="find_apk";
                    etMessage.setText("");
                }
            }
        });
        etMessage.setHint("请下你的留言...");
    }
    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                getWlData();
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                getWlData();
            }
        });
    }

    private void inputMethod() {
        inputMethodLayout.setOnkeyboarddStateListener(new InputMethodLayout.onKeyboardsChangeListener() {// 监听软键盘状态
            @Override
            public void onKeyBoardStateChange(int state) {
                // TODO Auto-generated method stub
                switch (state) {
                    case InputMethodLayout.KEYBOARD_STATE_SHOW:
                        etMessage.setFocusable(true);
                        etMessage.setFocusableInTouchMode(true);
                        etMessage.requestFocus();
                        rlSelectYse.setVisibility(View.VISIBLE);
                        rlSelectNo.setVisibility(View.GONE);
                        break;
                    case InputMethodLayout.KEYBOARD_STATE_HIDE:
                        rlSelectYse.setVisibility(View.GONE);
                        rlSelectNo.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }
    CommonCommentsBean commentsBean;
    protected void initData() {
        commentsBean = getIntent().getParcelableExtra("commentsBean");
        GlideUtils.loadCircleUserUrl(this,ivCommentedUserIcon, Constants.BASE_IMG_URL + commentsBean.getCommentUserIcon());
        StringUtil.getUserType(commentsBean.getUserType(),iv_model_type);
        postId = commentsBean.getPostId();
        parentCommentsId = commentsBean.getCommentsId();
        commentsId = commentsBean.getCommentsId();
        userId= commentsBean.getCommentUserId();
        adapter.setParentUserId(userId);
        tvCommentedUserName.setText(commentsBean.getCommentUserName());
        tvCreateTime.setText(commentsBean.getFloor() + "楼    " + TimeToolUtils.convertTimeToFormat(commentsBean.getCreateTime()));
        tvCommentContent.setText(commentsBean.getCommentContent());
        praiseNum = commentsBean.getPraiseNum();
        tvPraiseNum.setText(String.valueOf(praiseNum));
        //"praiseStatus":0,//点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
        if (commentsBean.getPraiseStatus() == 1) {
            tvPraiseNum.setSelected(false);
        } else if (commentsBean.getPraiseStatus() == 0) {
            tvPraiseNum.setSelected(true);
        } else if (commentsBean.getPraiseStatus() == 2) {
            tvPraiseNum.setVisibility(View.GONE);
        }
        loadingDialog.show();
        getWlData();
    }

    @OnClick({R.id.tv_praise_num, R.id.rl_ge_ren, R.id.tv_send,R.id.tv_commented_user_name,R.id.rl_select_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_praise_num:
                if(!tvPraiseNum.isSelected()){
                    return;
                }
                if(!NetUtil.isPraise(userId,baseUserId)){
                    return;
                }
                tvPraiseNum.setEnabled(false);
                String strNum;
                if(tvPraiseNum.isSelected()){
                    strNum =  String.valueOf(praiseNum+1);
                }else{
                    strNum = String.valueOf(praiseNum-1);
                }
                tvPraiseNum.setText(strNum);
                tvPraiseNum.setSelected(!tvPraiseNum.isSelected());
                NetUtil.setAnimation(tvPraiseNum);
                NetUtil.addCommentsPraise(!tvPraiseNum.isSelected(), commentsId, new NetUtil.SaveFollowImpl() {
                    @Override
                    public void finishFollow(String praiseNum,boolean status,double find) {
                        tvPraiseNum.setEnabled(true);
                        if(!praiseNum.equals(Constants.PRAISE_ERROR)){
                            DialogUtils.showDialogPraise(MoreCommentsActivity.this,1,true,find);
                            MoreCommentsActivity.this.praiseNum=Integer.valueOf(praiseNum);
                            tvPraiseNum.setText(praiseNum);
                            tvPraiseNum.setSelected(status);
                            isBianHua=true;
                        }
                    }
                });
                break;
            case R.id.rl_ge_ren:
                IntentUtil.startHomeActivity(userId);
                break;
            case R.id.tv_commented_user_name:
                IntentUtil.startHomeActivity(userId);
                break;
            case R.id.rl_select_no:
                KeybordS.openKeybord(etMessage,this);
                break;
            case R.id.tv_send:
                String str = etMessage.getText().toString().trim();
                if (StringUtil.isNotBlank(str)) {
                    if(str.contains(strLs)){
                        str = str.replaceAll( strLs,"");
                    }
                    KeybordS.closeKeybord(etMessage,this);
                    saveComment(str);
                } else {
                    ToastUtils.getInstance().show("内容不能为空");
                }
                break;
        }
    }
    String strLs = "find_apk";
    public void setSendEd(String user,int id){
        strLs="@"+user+":";
        etMessage.setText(strLs);
        if(id!=0){
//            this.parentCommentsId=id;
            this.becommentedId=id;
        }
        StringUtil.showSoftInputFromWindow(this,etMessage);
    }
    private void saveComment(String content) {
        if(!NetUtil.isNetworkAvailable()){
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("commentContent", content);//帖子ID
            node.put("postId", Integer.valueOf(postId));
            if(parentCommentsId==0 || becommentedId==0){
                parentCommentsId=commentsBean.getCommentsId();
                becommentedId=commentsBean.getCommentsId();
            }
            node.put("becommentedId", becommentedId);//parentCommentsId 未null
            node.put("parentCommentsId", parentCommentsId);//parentCommentsId 未null
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SAVE_COMMENT)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                isBianHua=true;
                etMessage.setText("");
                pageIndex=1;
                getWlData();
            }

            @Override
            public void onError(String message) {
                loadingDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(Constants.REQUEST_CODE,isBianHua);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void getWlData() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("commentsId", commentsId);
            node.put("postId", postId);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", 30);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.COMMENT_COMMENTS_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, MoreCommentsBean.class, new HttpCallBackImpl<MoreCommentsBean>() {
            @Override
            public void onCompleted(MoreCommentsBean bean) {
                MoreCommentsBean.DataBean.CommentsBean commentsBean = bean.getData().getComments();
                if(commentsBean!=null){
                    List<MoreCommentsBean.DataBean.CommentsBean.RowsBean> commentsList = commentsBean.getRows();
                    if (commentsBean.getCurPageNum() == commentsBean.getPageSize()) {
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }
                    if (commentsList != null && commentsList.size() > 0) {
                        adapter.setData(commentsList);
                    }
                }
            }

            @Override
            public void onFinish() {
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore(true);
                }
                loadingDialog.dismiss();
            }
        });
    }
}
