package com.secretk.move.contract;

import com.secretk.move.base.BasePresenter;
import com.secretk.move.base.BaseView;
import com.secretk.move.bean.VersionBean;

/**
 * Created by zc on 2018/4/25.
 */

public interface ActivityMainContract {
    /**
     * Created by zc on 2018/4/6.
     */

    interface MainPresenter extends BasePresenter{
        void initialized();
        void downLoadApk();
    }

    /**
     * Created by zc on 2018/4/5.
     */

    interface ActivityMainView extends BaseView{
        void showDialog(String str);
    }

    /**
     * Created by zc on 2018/4/6.
     */

    interface CallBack {
        void requestFailed(String str);
        void requestSuccess(VersionBean data);
        void isDownLoadSuccess(Boolean isSuccess);
    }

    /**
     * Created by zc on 2018/4/6.
     */

    interface MainInteractor {
        void downLoadApk(String url);

        void NetWorkVersion();

        int localVerison();

        void install(String apkPath);

        void destroy();
    }
}