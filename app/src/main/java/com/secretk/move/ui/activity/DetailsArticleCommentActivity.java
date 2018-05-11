package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

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
import com.secretk.move.bean.CommonCommentsBean;
import com.secretk.move.bean.DetailsArticleCommentBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.DetailsDiscussAdapter;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 作者： litongge
 * 时间： 2018/5/3 16:53
 * 邮箱；ltg263@126.com
 * 描述：文章---评论详情列表
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
    @BindView(R.id.but_send)
    Button butSend;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private DetailsDiscussAdapter adapter;
    private DetailsDiscussAdapter adapterNew;
    private String postId;
    private int pageIndex = 1;

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
        mMenuInfos.add(0, new MenuInfo(R.string.share, "分享", R.drawable.ic_share));
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        postId = getIntent().getStringExtra("postId");
        List<CommonCommentsBean> hotComments = getIntent().getParcelableArrayListExtra("hotComments");
        LogUtil.w("hotComments" + hotComments);
        setVerticalManager(rvkHotReview);
        adapter = new DetailsDiscussAdapter(this);
        rvkHotReview.setAdapter(adapter);
        if (hotComments != null) {
            adapter.setData(hotComments);
        }

        setVerticalManager(rvNewReview);
        adapterNew = new DetailsDiscussAdapter(this);
        rvNewReview.setAdapter(adapterNew);
        initRefresh();
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
                .url(Constants.ARTICLE_COMMENT_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, DetailsArticleCommentBean.class, new HttpCallBackImpl<DetailsArticleCommentBean>() {
            @Override
            public void onCompleted(DetailsArticleCommentBean bean) {
                if (bean.getData().getNewestComments().getCurPageNum() == bean.getData().getNewestComments().getPageSize()) {
                    refreshLayout.setLoadmoreFinished(true);
                }
                adapterNew.setData(bean.getData().getNewestComments().getRows());
            }

            @Override
            public void onFinish() {
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isLoading()) {
                    refreshLayout.finishLoadmore(true);
                }
            }
        });
    }
    @OnClick(R.id.but_send)
    public void onViewClicked() {
        String str = etContent.getText().toString().trim();
        if (StringUtil.isNotBlank(str)) {
            saveComment(str);
        } else {
            ToastUtils.getInstance().show("内容不能为空");
        }
    }

    /**
     * @param content
     */
    private void saveComment(String content) {
        pageIndex = 1;
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("commentContent", content);//帖子ID
            node.put("postId", Integer.valueOf(postId));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SAVE_COMMENT)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                etContent.setText("");
                initData();
            }
        });
    }
}
