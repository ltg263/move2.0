package com.secretk.move.interactor;



import com.secretk.move.bean.SearchBean;
import com.secretk.move.contract.ActivitySearchContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/30.
 */

public class ActivitySearchInteractorImpl  implements ActivitySearchContract.Interactor {
    private ActivitySearchContract.CallBack callBack;

    public ActivitySearchInteractorImpl(ActivitySearchContract.CallBack callBack) {
        this.callBack = callBack;
    }


    @Override
    public void search(String str) {
        List<SearchBean> list = new ArrayList<SearchBean>();
        SearchBean bean1 = new SearchBean();
        bean1.setType(1);
        bean1.setSearchTxt("小米");
        SearchBean bean2 = new SearchBean();
        bean2.setType(1);
        bean2.setSearchTxt("华为");
        list.add(bean1);
        list.add(bean2);
        callBack.searchSuccess(list,str);
    }

    @Override
    public void destroy() {

    }
}
