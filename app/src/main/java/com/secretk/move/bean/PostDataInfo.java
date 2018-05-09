package com.secretk.move.bean;

/**
 * 作者： litongge
 * 时间：  2018/5/9 19:33
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class PostDataInfo {


    private int id;
    private String name;
    private String title;
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
