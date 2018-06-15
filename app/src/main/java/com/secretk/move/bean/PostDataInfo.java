package com.secretk.move.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者： litongge
 * 时间：  2018/5/9 19:33
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class PostDataInfo implements Parcelable {


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.title);
        dest.writeString(this.url);
    }

    public PostDataInfo() {
    }

    protected PostDataInfo(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.title = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<PostDataInfo> CREATOR = new Parcelable.Creator<PostDataInfo>() {
        @Override
        public PostDataInfo createFromParcel(Parcel source) {
            return new PostDataInfo(source);
        }

        @Override
        public PostDataInfo[] newArray(int size) {
            return new PostDataInfo[size];
        }
    };
}
