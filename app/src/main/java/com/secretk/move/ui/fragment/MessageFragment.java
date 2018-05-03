package com.secretk.move.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.bean.MessageBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.MessageFragmentRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zc on 2018/4/5.
 */

public class MessageFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private MessageFragmentRecyclerAdapter adapter;
    @Override
    public int setFragmentView() {
        return R.layout.fragment_message;
    }

    @Override
    public void initViews() {
        setVerticalManager(recycler);
        adapter=new MessageFragmentRecyclerAdapter();
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onFirstUserVisible() {
        List<MessageBean> list=new ArrayList<MessageBean>();
        MessageBean bean=new MessageBean();
        bean.setName("大神");
        bean.setLastContent("回复了你的文章《EOS用户句》");
        MessageBean bean1=new MessageBean();
        bean1.setName("老厉害");
        bean1.setLastContent("关注了你");
        MessageBean bean2=new MessageBean();
        bean2.setName("牛牛");
        bean2.setLastContent("回复了你的文章《EOS用户句》");
        list.add(bean);
        list.add(bean1);
        list.add(bean2);
        adapter.setData(list);
    }

    @Override
    public void onItemClick(View view, int postion) {

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
