package com.secretk.move.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.CommonListBase;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.ProjectActivity;
import com.secretk.move.ui.adapter.ProjectRecommendAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:04
 * 邮箱；ltg263@126.com
 * 描述：项目主页--文章
 */


public class ProjectArticleFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.tv_row_count)
    TextView tvRowCount;
    @BindView(R.id.tv_sort)
    TextView tvSort;
    @BindView(R.id.ll_have_data)
    LinearLayout llHaveData;
    @BindView(R.id.iv_not_content)
    ImageView ivNotContent;

    private ProjectRecommendAdapter adapter;
    public boolean isHaveData = true;
    private int pageIndex = 1;
    private String projectId;
    private LoadingDialog loadingDialog;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_project_article;
    }

    @Override
    public void initViews() {
        setVerticalManager(rvReview);
        adapter = new ProjectRecommendAdapter(getActivity());
        rvReview.setAdapter(adapter);
        tvSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sort = tvSort.getText().toString();
                if (sort.equals(getString(R.string.sort_love))) {
                    tvSort.setText(getString(R.string.sort_time));
                } else {
                    tvSort.setText(getString(R.string.sort_love));
                }
                pageIndex = 1;
                loadingDialog.show();
                getLoadData(null, getString(R.string.sort_time));
            }
        });
    }

    @Override
    public void onFirstUserVisible() {
        loadingDialog.show();
        getLoadData(null, "");
    }


    @Override
    public void onItemClick(View view, int postion) {
        Toast.makeText(getActivity(), "文章界面：我是第：" + postion, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        projectId = ((ProjectActivity) context).getProjectId();
        loadingDialog = ((ProjectActivity) context).getloadingDialog();
    }

    /**
     * @param refreshlayout
     * @param sort:排毒方式     空时间   否则赞
     */
    public void getLoadData(final RefreshLayout refreshlayout, String sort) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", Integer.valueOf(projectId));
            node.put("pageIndex", pageIndex++);
            if (sort.equals(getString(R.string.sort_love))) {
                node.put("praiseNum", pageIndex++);//按点赞数排序，需要传值 "praiseNum"
            }
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.PROJECT_ARTICLE_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, CommonListBase.class, new HttpCallBackImpl<CommonListBase>() {
            @Override
            public void onCompleted(CommonListBase bean) {
                CommonListBase.DataBean.DetailsBean detailsBean = bean.getData().getArticles();
                if (detailsBean.getPageSize() == detailsBean.getCurPageNum()) {
                    isHaveData = false;
                }
                if (detailsBean.getRows() == null || detailsBean.getRows().size() == 0) {
                    return;
                }
                llHaveData.setVisibility(View.VISIBLE);
                ivNotContent.setVisibility(View.GONE);
                tvRowCount.setText("共" + detailsBean.getRowCount() + "篇文章");
                if (pageIndex > 2) {
                    adapter.setAddData(detailsBean.getRows());
                } else {
                    adapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onFinish() {
                if (refreshlayout != null) {
                    refreshlayout.finishLoadmore();
                }
                loadingDialog.dismiss();
            }
        });
    }
}
