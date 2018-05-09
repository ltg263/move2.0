package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.HomeReviewBase;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.DetailsDiscussAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 作者： litongge
 * 时间： 2018/5/3 16:53
 * 邮箱；ltg263@126.com
 * 描述：文章---评论详情列表
 */
public class DetailsArticleCommentActivity extends BaseActivity{

    @BindView(R.id.rv_hot_review)
    RecyclerView rvkHotReview;
    @BindView(R.id.rv_new_review)
    RecyclerView rvNewReview;
    private DetailsDiscussAdapter adapter;
    private DetailsDiscussAdapter adapterNew;
    private String postId;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_details_article_comment;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle("EOS");
        mHeadView.setTitleVice("/張三");
        mHeadView.setTitleColor(R.color.title_gray);
        mMenuInfos.add(0,new MenuInfo(R.string.share, "分享", R.drawable.ic_share));
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        postId = getIntent().getStringExtra("postId");
        setVerticalManager(rvkHotReview);
        adapter = new DetailsDiscussAdapter();
        rvkHotReview.setAdapter(adapter);

        setVerticalManager(rvNewReview);
        adapterNew = new DetailsDiscussAdapter();
        rvNewReview.setAdapter(adapterNew);
    }

    protected void initData() {
        List<HomeReviewBase> list = new ArrayList<>();
        HomeReviewBase base = new HomeReviewBase();
        base.setDiyi("张三");
        base.setEr("李四");
        base.setSan("周五");
        base.setIndex(1);
        list.add(base);
        list.add(base);
//        adapter.setData(list);
//        adapterNew.setData(list);

        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", postId);//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.ARTICLE_COMMENT_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String bean) {
                List<HomeReviewBase> list = new ArrayList<>();
                HomeReviewBase base = new HomeReviewBase();
                base.setDiyi("张三");
                base.setEr("李四");
                base.setSan("周五");
                base.setIndex(1);
                list.add(base);
                list.add(base);
//                adapter.setData(list);
            }
        });
    }
}
