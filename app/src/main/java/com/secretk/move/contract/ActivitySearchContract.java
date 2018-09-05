package com.secretk.move.contract;

import com.secretk.move.base.BasePresenter;
import com.secretk.move.base.BaseView;
import com.secretk.move.bean.SearchBean;
import com.secretk.move.bean.SearchedBean;

import java.util.List;

/**
 * Created by zc on 2018/4/30.
 */

public interface ActivitySearchContract {
    interface Presenter extends BasePresenter {
        //加载历史信息
        void initHistoryInfo();

        void search();

        void SearchBean(SearchBean bean);

        void cleanHistoryInfo();
    }

    interface View extends BaseView {
        void loadHistorySuccess(List<SearchBean> list);

        void loadSearchSuccess(List<SearchedBean.DataBean.ProjectsBean.RowsBean> list);

        String getSearchTxt();
    }

    interface CallBack {
        void searchSuccess(List<SearchedBean.DataBean.ProjectsBean.RowsBean> list, String searchTxt,int type);

        void onError(String str);
    }


    interface Interactor {
        void search(String str);

        void destroy();
    }
}
