package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.SelectProjectAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/6/5 14:48
 * 邮箱；ltg263@126.com
 * 描述：发表 种类 选择项目
 * publication_type 发表的类型
 * 1 评测
 * 2 文章
 * 3 讨论
 */
public class SelectProjectActivity extends BaseActivity implements ItemClickListener {

    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private SelectProjectAdapter adapter;

    private List<SearchedBean.Projects> list;
    private int publicationType;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        return null;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_select_project;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        publicationType = getIntent().getIntExtra("publication_type",0);
        setVerticalManager(recycler);
        adapter = new SelectProjectAdapter();
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    protected void initData() {
        loadingDialog.show();
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectCode", "");
            node.put("sortType", 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SEARCH_PROJECTS)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, SearchedBean.class, new HttpCallBackImpl<SearchedBean>() {
            @Override
            public void onCompleted(SearchedBean bean) {
                list = bean.getData().getProjects();
                tvCount.setText("共" + list.size() + "个币种");
                adapter.setData(list);
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.tv_search)
    public void onViewClicked() {
    }

    /**
     * 1 评测
     * 2 文章
     * 3 讨论
     */
    @Override
    public void onItemClick(View view, int postion) {
        SearchedBean.Projects bean = list.get(postion);
        if(publicationType==1){
            IntentUtil.startProjectSimplenessActivity(bean.getProjectId(),bean.getProjectIcon(),
                    bean.getProjectChineseName(),bean.getProjectCode());
        }else if(publicationType==2){
            Intent intent = new Intent(this,ReleaseArticleActivity.class);
            intent.putExtra("projectId",list.get(postion).getProjectId());
            startActivity(intent);
        }else{
            Intent intent = new Intent(this,ReleaseDiscussActivity.class);
            intent.putExtra("projectId",list.get(postion).getProjectId());
            startActivity(intent);
        }

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
