package com.secretk.move.presenter;


import android.text.TextUtils;

import com.secretk.move.MoveApplication;
import com.secretk.move.bean.SearchBean;
import com.secretk.move.bean.SearchBeanDao;
import com.secretk.move.contract.ActivitySearchContract;
import com.secretk.move.interactor.ActivitySearchInteractorImpl;
import com.secretk.move.utils.GreenDaoMananger;

import java.util.List;


/**
 * Created by zc on 2018/4/30.
 */

public class ActivitySearchPresenterImpl implements ActivitySearchContract.Presenter, ActivitySearchContract.CallBack {
    private ActivitySearchContract.View view;
    private ActivitySearchContract.Interactor interactor;
    private SearchBeanDao dao;

    public ActivitySearchPresenterImpl(ActivitySearchContract.View view) {
        this.view = view;
        interactor = new ActivitySearchInteractorImpl(this);
        dao = (SearchBeanDao) GreenDaoMananger.getInstance(MoveApplication.getContext()).getDao(SearchBean.class);
    }


    @Override
    public void initHistoryInfo() {
        List<SearchBean> list = dao.queryBuilder().orderAsc(SearchBeanDao.Properties.SearchTxt).build().list();
        if (list == null || list.size() == 0) {
            view.loadHistorySuccess(list);
        } else {
            SearchBean bean = new SearchBean();
            bean.setType(0);
            bean.setSearchTxt("历史搜索");
            SearchBean bean1 = new SearchBean();
            bean1.setType(2);
            bean.setSearchTxt("清空历史搜索");
            list.add(0, bean);
            list.add(bean1);
        }
    }

    @Override
    public void search() {
        String txt = view.getSearchTxt();
        if (TextUtils.isEmpty(txt)) {
            return;
        }
        view.showLoading();
        interactor.search(txt);
    }


    @Override
    public void searchSuccess(List<SearchBean> list, String searchTxt) {
        view.hideLoading();
        view.loadSearchSuccess(list);

        SearchBean searchBean = new SearchBean();
        searchBean.setType(1);
        searchBean.setTime(System.currentTimeMillis());
        searchBean.setSearchTxt(searchTxt);
        dao.insertOrReplace(searchBean);
    }

    @Override
    public void onError(String str) {
        view.showLoading();
        view.showMsg(str);
    }

    @Override
    public void detachView() {
        view = null;
        interactor.destroy();
    }
}
