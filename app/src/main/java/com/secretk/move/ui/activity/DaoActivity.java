package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.bean.SearchBean;
import com.secretk.move.bean.SearchBeanDao;
import com.secretk.move.utils.GreenDaoMananger;

import java.util.List;

/**
 * Created by zc on 2018/5/1.
 */

public class DaoActivity extends AppCompatActivity {
    SearchBeanDao dao;
    Long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao);
        dao = (SearchBeanDao) GreenDaoMananger.getInstance(this).getDao(SearchBean.class);
    }

    public void add(View view) {
        SearchBean bean = new SearchBean();
        bean.setSearchTxt("一号");
        bean.setType(0);
        bean.setTime(System.currentTimeMillis());
        id = dao.insert(bean);
    }

    public void delete(View view) {

    }

    public void query(View view) {
        //查询单个
//        List<SearchBean> list = dao.queryBuilder().where(SearchBeanDao.Properties.Id.eq(id)).build().list();
        List<SearchBean> list=  dao.queryBuilder().orderAsc(SearchBeanDao.Properties.SearchTxt).build().list();
        int i = list.size();
    }

    public void updata(View view) {

    }

    public void clean(View view) {

    }
}
