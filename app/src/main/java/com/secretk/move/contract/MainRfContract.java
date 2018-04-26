package com.secretk.move.contract;

import com.secretk.move.base.BasePresenter;
import com.secretk.move.base.BaseView;
import com.secretk.move.bean.MainRfBean;

import java.util.List;


/**
 * Created by zc on 2018/4/26.
 * 推荐和关注
 */

public interface MainRfContract {
    interface Presenter extends BasePresenter {
        void loadingHead();

        void loadingFoot();
    }

    interface View extends BaseView {
        /**
         * 加载头部成功
         */
        void onloadHeadSuccess(List<MainRfBean.Rows> list);

        /**
         * 加载更多成功
         */
        void onloadMoreSuccess(List<MainRfBean.Rows> list);
    }

    /**
     * Created by zc on 2018/4/6.
     */

    interface Interactor {
        void loadReCommendPageIndex(int index);
        void loadfollowPageIndex(int index);
        void destroy();
    }
    /**
     * Created by zc on 2018/4/6.
     */

    interface CallBack {
        void loadPageIndexSuccess(int index,List<MainRfBean.Rows> list);
        void loadFail(String str);
    }
}
