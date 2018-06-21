package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DetailsArticleCommentBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.DetailsDiscussAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.ShareView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 作者： litongge
 * 时间： 2018/5/3 16:53
 * 邮箱；ltg263@126.com
 * 描述：文章-评测--评论详情列表
 */
public class DetailsArticleCommentActivity extends BaseActivity {

    @BindView(R.id.rv_hot_review)
    RecyclerView rvkHotReview;
    @BindView(R.id.rv_new_review)
    RecyclerView rvNewReview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.tv_icon)
    ImageView tvIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_not_data)
    LinearLayout rlNotData;
    @BindView(R.id.ll_hot)
    LinearLayout llHot;
    @BindView(R.id.ll_new)
    LinearLayout llNew;
    private DetailsDiscussAdapter adapter;
    private DetailsDiscussAdapter adapterNew;
    private String postId;
    private int pageIndex = 1;
    private String url;
    private String imgUrl="";
    @Override
    protected int setOnCreate() {
        return R.layout.activity_details_article_comment;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle("评论详情");
        mHeadView.setTitleColor(R.color.title_gray);
//        mMenuInfos.add(0, new MenuInfo(R.string.share, "分享", R.drawable.ic_share));
        return mHeadView;
    }

    @Override
    protected void OnToolbarRightListener() {
//        {"postId","url","share_url","share_title","share_content"};
        String shareUrl = getIntent().getStringExtra("share_url");
        String shareTitle = getIntent().getStringExtra("share_title");
        String shareContent = getIntent().getStringExtra("share_content");
        ShareView.showShare(shareUrl,shareTitle,shareContent,imgUrl);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        postId = getIntent().getStringExtra("postId");
        url = getIntent().getStringExtra("url");
        setVerticalManager(rvkHotReview);
        adapter = new DetailsDiscussAdapter(this);
        rvkHotReview.setAdapter(adapter);

        setVerticalManager(rvNewReview);
        adapterNew = new DetailsDiscussAdapter(this);
        rvNewReview.setAdapter(adapterNew);
        initRefresh();
        loadingDialog.show();
        StringUtil.etSearchChangedListener(etContent, null, new StringUtil.EtChange() {
            @Override
            public void etYes() {
                if(!etContent.getText().toString().contains(strLs) && !strLs.equals("find_apk")){
                    parentCommentsId=0;
                    strLs="find_apk";
                    etContent.setText("");
                }
            }
        });
    }

    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                initData();
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                initData();
            }
        });
    }

    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", Integer.valueOf(postId));//帖子ID
            node.put("pageIndex", pageIndex++);//帖子ID
            node.put("pageSize", 20);//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(url)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, DetailsArticleCommentBean.class, new HttpCallBackImpl<DetailsArticleCommentBean>() {
            @Override
            public void onCompleted(DetailsArticleCommentBean bean) {
                DetailsArticleCommentBean.DataBean data = bean.getData();
                if (data == null ) {
                    return;
                }
                rlNotData.setVisibility(View.VISIBLE);
                if (data.getNewestComments().getCurPageNum() == 1) {
                    if (data.getHotComments() != null && data.getHotComments().size() > 0) {
                        refreshLayout.setVisibility(View.VISIBLE);
                        llHot.setVisibility(View.VISIBLE);
                        rlNotData.setVisibility(View.GONE);
                        adapter.setData(data.getHotComments());
                    }
                }
                if (data.getNewestComments().getCurPageNum() == data.getNewestComments().getPageSize()) {
                    refreshLayout.setLoadmoreFinished(true);
                }
                if (bean.getData().getNewestComments().getRows() != null
                        && bean.getData().getNewestComments().getRows().size() > 0) {
                    refreshLayout.setVisibility(View.VISIBLE);
                    llNew.setVisibility(View.VISIBLE);
                    rlNotData.setVisibility(View.GONE);
                    adapterNew.setData(bean.getData().getNewestComments().getRows());
                }
            }

            @Override
            public void onFinish() {
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isLoading()) {
                    refreshLayout.finishLoadmore(true);
                }
                loadingDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.tv_send)
    public void onViewClicked() {
        String str = etContent.getText().toString().trim();
        if (StringUtil.isNotBlank(str)) {
            if(str.contains(strLs)){
                str = str.replaceAll( strLs,"");
            }
            saveComment(str);
        } else {
            ToastUtils.getInstance().show("内容不能为空");
        }
    }
    int parentCommentsId = 0;
    /**
     * @param content
     */
    private void saveComment(String content) {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        pageIndex = 1;
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("commentContent", content);//帖子ID
            node.put("postId", Integer.valueOf(postId));
            if(parentCommentsId!=0){
                node.put("parentCommentsId", parentCommentsId);//parentCommentsId 未null
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadingDialog.show();
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SAVE_COMMENT)
                .addPart("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addPart("sign", MD5.Md5(node.toString()))
                .build();
        params.setMethod(RxHttpParams.HttpMethod.POST);
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                etContent.setText("");
                initData();
            }

            @Override
            public void onError(String message) {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    /**
     * 有问题
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            if (data.getExtras().getBoolean(Constants.REQUEST_CODE)) {
                pageIndex = 1;
                initData();
            }
        }
    }
    String strLs = "find_apk";
    public void setIntput(String str,int parentCommentsId){
        strLs="@"+str+":";
        etContent.setText(strLs);
        this.parentCommentsId=parentCommentsId;
        StringUtil.showSoftInputFromWindow(this,etContent);
    }
}
