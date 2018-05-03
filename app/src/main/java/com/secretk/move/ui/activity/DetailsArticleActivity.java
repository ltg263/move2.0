package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.PileLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/5/2 14:12
 * 邮箱；ltg263@126.com
 * 描述: 文章详情.
 */
public class DetailsArticleActivity extends BaseActivity  implements ItemClickListener {

    @BindView(R.id.pile_layout)
    PileLayout pileLayout;
    @BindView(R.id.tv_comment)
    TextView tvComment;

    String[] urls = {"http://img2.imgtn.bdimg.com/it/u=1939271907,257307689&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1939271907,257307689&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg"
    };
    @Override
    protected int setOnCreate() {
        return R.layout.activity_details_article;
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

    public void initPraises() {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < urls.length; i++) {

            ImageView imageView = (ImageView) inflater.inflate(R.layout.item_praise, pileLayout, false);
            GlideUtils.loadCircleUrl(imageView, urls[i]);
            pileLayout.addView(imageView);
        }

    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        GlideUtils.loadCircle(mHeadView.getImageView(),R.drawable.ic_collect_have);
        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startActivity(DetailsArticleCommentActivity.class);
            }
        });
        initPraises();
    }

    protected void initData() {

        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY, "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", 1);//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.HOME_DISCUSS_DETAIL)
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
