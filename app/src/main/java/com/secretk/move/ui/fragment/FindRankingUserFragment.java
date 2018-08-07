package com.secretk.move.ui.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/6/8 13:46
 * 邮箱；ltg263@126.com
 * 描述：发现--旁行榜 用户
 */
public class FindRankingUserFragment extends LazyFragment {


    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;
    private int index;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_find_ranking;
    }

    @Override
    public void initViews() {
//        list.add("项目评分榜");
//        list.add("KLO榜单");
//        list.add("最佳项目榜");
        if (index == 0) {
            tv.setText("最热项目榜");
            iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_king_zxxmb));
        }
        if (index == 1) {
            tv.setText("KOL榜单");
            iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_king_klobd));
        }
        if (index == 2) {
            tv.setText("项目评分榜");
            iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_king_xmqfb));
        }
    }
    @Override
    public void onFirstUserVisible() {

    }

    public void setTabName(int index) {
        this.index = index;
    }
}
