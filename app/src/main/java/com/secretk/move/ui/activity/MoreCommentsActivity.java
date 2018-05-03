package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.HomeReviewBase;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.MoreCommentsAdapter;
import com.secretk.move.utils.IntentUtil;
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
 * 时间： 2018/5/2 18:53
 * 邮箱；ltg263@126.com
 * 描述：评论详情------更多评论
 */
public class MoreCommentsActivity extends BaseActivity  implements ItemClickListener {

    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.rl_ge_ren)
    RelativeLayout rlGeRen;
    @BindView(R.id.tv_assist)
    TextView tvAssist;
    private MoreCommentsAdapter adapter;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_comments_more ;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle("评论详情");
        mMenuInfos.add(0,new MenuInfo(R.string.share, "分享", R.drawable.ic_share));
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        setVerticalManager(rvReview);
        adapter = new MoreCommentsAdapter();
        rvReview.setAdapter(adapter);
        adapter.setItemListener(this);
        tvAssist.setSelected(true);
        rlGeRen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startActivity(ProjectActivity.class);
            }
        });
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
        adapter.setData(list);

        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY, "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", 1);//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.HOME_DISCUSS_COMMENT_LIST)
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
                adapter.setData(list);
            }
        });
    }

    @Override
    public void onItemClick(View view, int postion) {

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
