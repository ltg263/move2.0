package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

/**
 * 作者： litongge
 * 时间：  2018/4/27 16:39
 * 邮箱；ltg263@126.com
 * 描述：我的评测数据
 */

public class HomeReviewBase extends BaseRes{

    /**
     * diyi : 13812345678
     * er : 12345678a
     * san : 572898
     */

    private String diyi;
    private String er;
    private String san;
    private int index;//1--2--3

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getDiyi() {
        return diyi;
    }

    public void setDiyi(String diyi) {
        this.diyi = diyi;
    }

    public String getEr() {
        return er;
    }

    public void setEr(String er) {
        this.er = er;
    }

    public String getSan() {
        return san;
    }

    public void setSan(String san) {
        this.san = san;
    }
}
