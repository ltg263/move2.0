package com.secretk.move.interactor;



import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.contract.ActivitySearchContract;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;

import org.json.JSONException;
import org.json.JSONObject;

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
    public void search(final String str) {
//        List<SearchBean> list = new ArrayList<SearchBean>();
//        SearchBean bean1 = new SearchBean();
//        bean1.setType(1);
//        bean1.setSearchTxt("小米");
//        SearchBean bean2 = new SearchBean();
//        bean2.setType(1);
//        bean2.setSearchTxt("华为");
//        list.add(bean1);
//        list.add(bean2);
//        callBack.searchSuccess(list,str);
        String token = SharedUtils.singleton().get("token", "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectCode", str);
            node.put("pageIndex", 1);
            node.put("pageSize", 500);
            node.put("sortType",Constants.TOPIC_SORT_BY_NUM);
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

                if (bean.getCode() == 0) {
                    List<SearchedBean.DataBean.ProjectsBean.RowsBean> list = bean.getData().getProjects().getRows();
                    callBack.searchSuccess(list,str,0);
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void destroy() {

    }
}
