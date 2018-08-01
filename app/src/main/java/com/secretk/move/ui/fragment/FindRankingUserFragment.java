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


    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.iv_3)
    ImageView iv3;
    @BindView(R.id.tv_3)
    TextView tv3;
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
        if(index==0){

            tv1.setText("项目评分榜");
            iv1.setImageDrawable(getResources().getDrawable(R.drawable.ic_find_1));

            tv2.setText("最佳项目榜");
            iv2.setImageDrawable(getResources().getDrawable(R.drawable.ic_find_3));

            tv3.setText("KLO榜单");
            iv3.setImageDrawable(getResources().getDrawable(R.drawable.ic_find_2));

        }
        if(index==1){
            tv1.setText("最佳项目榜");
            iv1.setImageDrawable(getResources().getDrawable(R.drawable.ic_find_3));

            tv2.setText("KLO榜单");
            iv2.setImageDrawable(getResources().getDrawable(R.drawable.ic_find_2));

            tv3.setText("项目评分榜");
            iv3.setImageDrawable(getResources().getDrawable(R.drawable.ic_find_1));


        }
        if(index==2){

            tv1.setText("KLO榜单");
            iv1.setImageDrawable(getResources().getDrawable(R.drawable.ic_find_2));

            tv2.setText("项目评分榜");
            iv2.setImageDrawable(getResources().getDrawable(R.drawable.ic_find_1));

            tv3.setText("最佳项目榜");
            iv3.setImageDrawable(getResources().getDrawable(R.drawable.ic_find_3));
        }

    }


    @Override
    public void onFirstUserVisible() {

    }

    public void setTabName(int index) {
        this.index = index;
    }
}
