package com.secretk.move.ui.holder;

import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.BaseManager;
import com.secretk.move.bean.HomeReviewBase;
import com.secretk.move.ui.activity.MoreCommentsActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/5/2 16:48
 * 邮箱；ltg263@126.com
 * 描述：评测详情——评价Item
 */
public class DetailsDiscussHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_assist)
    TextView tvAssist;
    @BindView(R.id.tv_eave_content)
    TextView tvEaveContent;
    @BindView(R.id.tv_eave_ont)
    TextView tvEaveOnt;
    @BindView(R.id.tv_eave_two)
    TextView tvEaveTwo;
    @BindView(R.id.tv_eave_num)
    TextView tvEaveNum;
    public DetailsDiscussHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void refresh(final int position, List<HomeReviewBase> lists) {
        GlideUtils.loadCircle(imgHead, R.drawable.account_portrait);
        HomeReviewBase base = lists.get(position);
        tvEaveNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startActivity(MoreCommentsActivity.class);
            }
        });
        tvName.setText(base.getDiyi());
        tvEaveContent.setText("体自在EOS引力区的知识星球里有一个人，他在知识星球分享了一篇文章《数字会说明，老猫在想什么，写给eos的投资者们》，精明地推测出老猫分批地积累了上百万个EOS，这更能说明老猫看好EOS。道理很简单：因为看好，所以大量持有。");
        tvEaveTwo.setText(Html.fromHtml("<font color='#3b88f6'>张赫：@小柚子</font> 目前所有的交易所都没有公布是否会映射，还要进一步等消息。"));

        String str = "老柚子：@乌拉 圭你 @乌啊 说的很好可是能不能买呢？";
        String s= "老柚子：@乌拉";
        int var = str.indexOf(s);
        SpannableString InfoOne = new SpannableString(str);
        InfoOne.setSpan(new Clickable(clickListener),var,var+s.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvEaveOnt.setText(InfoOne);
        tvEaveOnt.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString InfoTwo = new SpannableString(str);
        String strt = "张赫：@小柚子 目前所有的交易所都没有公布是否会映射，还要进一步等消息。";
        String st= "张赫：@小柚子";
        int vart = strt.indexOf(st);
        InfoTwo.setSpan(new Clickable(clickListener),vart,var+st.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LogUtil.w("v:"+v.getId());
            Toast.makeText(BaseManager.app, "成功....",Toast.LENGTH_SHORT).show();
        }
    };
    class Clickable extends ClickableSpan {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener l) {
            mListener = l;
        }
        /**
         * 重写父类点击事件
         */
        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }
        /**
         * 重写父类updateDrawState方法  我们可以给TextView设置字体颜色,背景颜色等等...
         */
        @Override
        public void updateDrawState(TextPaint ds) {
           ds.setColor(BaseManager.app.getResources().getColor(R.color.app_background));
        }
    }
}
