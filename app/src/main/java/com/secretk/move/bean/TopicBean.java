package com.secretk.move.bean;

import android.support.annotation.NonNull;

public class TopicBean implements Comparable<TopicBean> {
    private String name;
    private String spell;
    private Boolean isFollow;
    private String iCon;
    private int followNum;

    @Override
    public int compareTo(@NonNull TopicBean another) {
        if(this.spell==null||another.getSpell()==null)
        {
            return 1;
        }
        return this.spell.compareTo(another.getSpell());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public Boolean getFollow() {
        return isFollow;
    }

    public void setFollow(Boolean follow) {
        isFollow = follow;
    }

    public String getiCon() {
        return iCon;
    }

    public void setiCon(String iCon) {
        this.iCon = iCon;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }
}
