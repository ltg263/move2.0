package com.secretk.move.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by zc on 2018/4/30.
 */
@Entity
public class SearchBean {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    Long id;
    @NotNull
    String searchTxt;
    /**
     * type 0  历史搜索
     * type 1  历史搜索集合
     * type 2  清空搜索历史
     */
    int type;

    Long time;

    @Generated(hash = 1951846529)
    public SearchBean(Long id, @NotNull String searchTxt, int type, Long time) {
        this.id = id;
        this.searchTxt = searchTxt;
        this.type = type;
        this.time = time;
    }

    @Generated(hash = 562045751)
    public SearchBean() {
    }

    public String getSearchTxt() {
        return searchTxt;
    }

    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
