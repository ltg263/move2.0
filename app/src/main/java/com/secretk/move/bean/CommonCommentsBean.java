package com.secretk.move.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/5/9 16:42
 * 邮箱；ltg263@126.com
 * 描述：通用的讨论详情中的评论
 */
public class CommonCommentsBean implements Parcelable {
    /**
     * floor:0
     * praiseStatus : 0
     * childCommentsNum : 1
     * childCommentsList : [{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":3,"commentUserId":24,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"用户24","commentContent":"怎么能随便评论呢？","projectId":1,"postId":4,"postType":2,"praiseNum":29,"parentCommentsId":1,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835712000,"createTimeStr":"2018-05-09 11:15:12","updateTime":1525835714000,"updateTimeStr":"2018-05-09 11:15:14","status":1}]
     * commentsId : 1
     * commentUserId : 14
     * commentUserIcon : /upload/avatars/avatar.png
     * commentUserName : 试试
     * commentContent : 随便评论一下
     * projectId : 1
     * postId : 4
     * postType : 2
     * praiseNum : 1000
     * parentCommentsId : null
     * becommentedUserId : 3
     * becommentedUserName : UVHaV6BJo
     * becommentedUserIcon : /upload/avatars/avatar.png
     * createTime : 1525835525000
     * createTimeStr : 2018-05-09 11:12:05
     * updateTime : 1525835528000
     * updateTimeStr : 2018-05-09 11:12:08
     * status : 1
     */

    private int floor;
    private int praiseStatus;
    private int childCommentsNum;
    private int commentsId;
    private int commentUserId;
    private String commentUserIcon;
    private String commentUserName;
    private String commentContent;
    private int projectId;
    private int postId;
    private int postType;
    private int praiseNum;
    private int parentCommentsId;
    private int becommentedUserId;
    private String becommentedUserName;
    private String becommentedUserIcon;
    private long createTime;
    private String createTimeStr;
    private long updateTime;
    private String updateTimeStr;
    private int status;
    private List<ChildCommentsListBean> childCommentsList;

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public int getPraiseStatus() {
        return praiseStatus;
    }

    public void setPraiseStatus(int praiseStatus) {
        this.praiseStatus = praiseStatus;
    }

    public int getChildCommentsNum() {
        return childCommentsNum;
    }

    public void setChildCommentsNum(int childCommentsNum) {
        this.childCommentsNum = childCommentsNum;
    }

    public int getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(int commentsId) {
        this.commentsId = commentsId;
    }

    public int getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(int commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentUserIcon() {
        return commentUserIcon;
    }

    public void setCommentUserIcon(String commentUserIcon) {
        this.commentUserIcon = commentUserIcon;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public int getParentCommentsId() {
        return parentCommentsId;
    }

    public void setParentCommentsId(int parentCommentsId) {
        this.parentCommentsId = parentCommentsId;
    }

    public int getBecommentedUserId() {
        return becommentedUserId;
    }

    public void setBecommentedUserId(int becommentedUserId) {
        this.becommentedUserId = becommentedUserId;
    }

    public String getBecommentedUserName() {
        return becommentedUserName;
    }

    public void setBecommentedUserName(String becommentedUserName) {
        this.becommentedUserName = becommentedUserName;
    }

    public String getBecommentedUserIcon() {
        return becommentedUserIcon;
    }

    public void setBecommentedUserIcon(String becommentedUserIcon) {
        this.becommentedUserIcon = becommentedUserIcon;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ChildCommentsListBean> getChildCommentsList() {
        return childCommentsList;
    }

    public void setChildCommentsList(List<ChildCommentsListBean> childCommentsList) {
        this.childCommentsList = childCommentsList;
    }

    public static class ChildCommentsListBean implements Parcelable {
        /**
         * praiseStatus : 0
         * childCommentsNum : 0
         * childCommentsList : null
         * commentsId : 3
         * commentUserId : 24
         * commentUserIcon : /upload/avatars/avatar.png
         * commentUserName : 用户24
         * commentContent : 怎么能随便评论呢？
         * projectId : 1
         * postId : 4
         * postType : 2
         * praiseNum : 29
         * parentCommentsId : 1
         * becommentedUserId : 3
         * becommentedUserName : UVHaV6BJo
         * becommentedUserIcon : /upload/avatars/avatar.png
         * createTime : 1525835712000
         * createTimeStr : 2018-05-09 11:15:12
         * updateTime : 1525835714000
         * updateTimeStr : 2018-05-09 11:15:14
         * status : 1
         */

        private int praiseStatus;
        private int childCommentsNum;
//        private Object childCommentsList;
        private int commentsId;
        private int commentUserId;
        private String commentUserIcon;
        private String commentUserName;
        private String commentContent;
        private int projectId;
        private int postId;
        private int postType;
        private int praiseNum;
        private int parentCommentsId;
        private int becommentedUserId;
        private String becommentedUserName;
        private String becommentedUserIcon;
        private long createTime;
        private String createTimeStr;
        private long updateTime;
        private String updateTimeStr;
        private int status;

        public int getPraiseStatus() {
            return praiseStatus;
        }

        public void setPraiseStatus(int praiseStatus) {
            this.praiseStatus = praiseStatus;
        }

        public int getChildCommentsNum() {
            return childCommentsNum;
        }

        public void setChildCommentsNum(int childCommentsNum) {
            this.childCommentsNum = childCommentsNum;
        }

//        public Object getChildCommentsList() {
//            return childCommentsList;
//        }
//
//        public void setChildCommentsList(Object childCommentsList) {
//            this.childCommentsList = childCommentsList;
//        }

        public int getCommentsId() {
            return commentsId;
        }

        public void setCommentsId(int commentsId) {
            this.commentsId = commentsId;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserIcon() {
            return commentUserIcon;
        }

        public void setCommentUserIcon(String commentUserIcon) {
            this.commentUserIcon = commentUserIcon;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public int getPostId() {
            return postId;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }

        public int getPostType() {
            return postType;
        }

        public void setPostType(int postType) {
            this.postType = postType;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getParentCommentsId() {
            return parentCommentsId;
        }

        public void setParentCommentsId(int parentCommentsId) {
            this.parentCommentsId = parentCommentsId;
        }

        public int getBecommentedUserId() {
            return becommentedUserId;
        }

        public void setBecommentedUserId(int becommentedUserId) {
            this.becommentedUserId = becommentedUserId;
        }

        public String getBecommentedUserName() {
            return becommentedUserName;
        }

        public void setBecommentedUserName(String becommentedUserName) {
            this.becommentedUserName = becommentedUserName;
        }

        public String getBecommentedUserIcon() {
            return becommentedUserIcon;
        }

        public void setBecommentedUserIcon(String becommentedUserIcon) {
            this.becommentedUserIcon = becommentedUserIcon;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCreateTimeStr() {
            return createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateTimeStr() {
            return updateTimeStr;
        }

        public void setUpdateTimeStr(String updateTimeStr) {
            this.updateTimeStr = updateTimeStr;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.praiseStatus);
            dest.writeInt(this.childCommentsNum);
            dest.writeInt(this.commentsId);
            dest.writeInt(this.commentUserId);
            dest.writeString(this.commentUserIcon);
            dest.writeString(this.commentUserName);
            dest.writeString(this.commentContent);
            dest.writeInt(this.projectId);
            dest.writeInt(this.postId);
            dest.writeInt(this.postType);
            dest.writeInt(this.praiseNum);
            dest.writeInt(this.parentCommentsId);
            dest.writeInt(this.becommentedUserId);
            dest.writeString(this.becommentedUserName);
            dest.writeString(this.becommentedUserIcon);
            dest.writeLong(this.createTime);
            dest.writeString(this.createTimeStr);
            dest.writeLong(this.updateTime);
            dest.writeString(this.updateTimeStr);
            dest.writeInt(this.status);
        }

        public ChildCommentsListBean() {
        }

        protected ChildCommentsListBean(Parcel in) {
            this.praiseStatus = in.readInt();
            this.childCommentsNum = in.readInt();
            this.commentsId = in.readInt();
            this.commentUserId = in.readInt();
            this.commentUserIcon = in.readString();
            this.commentUserName = in.readString();
            this.commentContent = in.readString();
            this.projectId = in.readInt();
            this.postId = in.readInt();
            this.postType = in.readInt();
            this.praiseNum = in.readInt();
            this.parentCommentsId = in.readInt();
            this.becommentedUserId = in.readInt();
            this.becommentedUserName = in.readString();
            this.becommentedUserIcon = in.readString();
            this.createTime = in.readLong();
            this.createTimeStr = in.readString();
            this.updateTime = in.readLong();
            this.updateTimeStr = in.readString();
            this.status = in.readInt();
        }

        public static final Creator<ChildCommentsListBean> CREATOR = new Creator<ChildCommentsListBean>() {
            @Override
            public ChildCommentsListBean createFromParcel(Parcel source) {
                return new ChildCommentsListBean(source);
            }

            @Override
            public ChildCommentsListBean[] newArray(int size) {
                return new ChildCommentsListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.floor);
        dest.writeInt(this.praiseStatus);
        dest.writeInt(this.childCommentsNum);
        dest.writeInt(this.commentsId);
        dest.writeInt(this.commentUserId);
        dest.writeString(this.commentUserIcon);
        dest.writeString(this.commentUserName);
        dest.writeString(this.commentContent);
        dest.writeInt(this.projectId);
        dest.writeInt(this.postId);
        dest.writeInt(this.postType);
        dest.writeInt(this.praiseNum);
        dest.writeInt(this.parentCommentsId);
        dest.writeInt(this.becommentedUserId);
        dest.writeString(this.becommentedUserName);
        dest.writeString(this.becommentedUserIcon);
        dest.writeLong(this.createTime);
        dest.writeString(this.createTimeStr);
        dest.writeLong(this.updateTime);
        dest.writeString(this.updateTimeStr);
        dest.writeInt(this.status);
        dest.writeList(this.childCommentsList);
    }

    public CommonCommentsBean() {
    }

    protected CommonCommentsBean(Parcel in) {
        this.floor = in.readInt();
        this.praiseStatus = in.readInt();
        this.childCommentsNum = in.readInt();
        this.commentsId = in.readInt();
        this.commentUserId = in.readInt();
        this.commentUserIcon = in.readString();
        this.commentUserName = in.readString();
        this.commentContent = in.readString();
        this.projectId = in.readInt();
        this.postId = in.readInt();
        this.postType = in.readInt();
        this.praiseNum = in.readInt();
        this.parentCommentsId = in.readInt();
        this.becommentedUserId = in.readInt();
        this.becommentedUserName = in.readString();
        this.becommentedUserIcon = in.readString();
        this.createTime = in.readLong();
        this.createTimeStr = in.readString();
        this.updateTime = in.readLong();
        this.updateTimeStr = in.readString();
        this.status = in.readInt();
        this.childCommentsList = new ArrayList<ChildCommentsListBean>();
        in.readList(this.childCommentsList, ChildCommentsListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CommonCommentsBean> CREATOR = new Parcelable.Creator<CommonCommentsBean>() {
        @Override
        public CommonCommentsBean createFromParcel(Parcel source) {
            return new CommonCommentsBean(source);
        }

        @Override
        public CommonCommentsBean[] newArray(int size) {
            return new CommonCommentsBean[size];
        }
    };
}
