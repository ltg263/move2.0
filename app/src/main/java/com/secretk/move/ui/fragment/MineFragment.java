package com.secretk.move.ui.fragment;

import android.content.Intent;
import android.widget.ImageView;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.bean.PersonInfors;
import com.secretk.move.presenter.MineFragmentPresenter;
import com.secretk.move.presenter.impl.MineFragmentPresenterImpl;
import com.secretk.move.ui.activity.LoginActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.FragmentMineView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by zc on 2018/4/5.
 */

public class MineFragment extends LazyFragment implements FragmentMineView {
    @BindView(R.id.fragment_mine_account_portrait)
    ImageView fragment_mine_account_portrait;
    MineFragmentPresenter presenter;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViews() {
        GlideUtils.loadCircle(fragment_mine_account_portrait, 0);
    }

    @Override
    public void onFirstUserVisible() {
        GlideUtils.loadCircle(fragment_mine_account_portrait, R.drawable.account_portrait);
        presenter = new MineFragmentPresenterImpl(this);
    }

    @OnClick(R.id.fragment_mine_account_portrait)
    public void fragment_mine_account_portrait() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fragment_mine_img_set)
    public void fragment_mine_img_set() {
        ToastUtils.getInstance().show("img_set");
    }

    @Override
    public void loadInfoSuccess(PersonInfors infos) {
        GlideUtils.loadCircle(fragment_mine_account_portrait, R.drawable.account_portrait);
    }
}
