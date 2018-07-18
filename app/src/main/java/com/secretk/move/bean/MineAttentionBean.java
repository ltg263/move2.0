package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * Created by zc on 2018/4/26.
 */

public class MineAttentionBean extends BaseRes {

    /**
     * data : {"myFollows":{"rowCount":3,"rows":[{"followId":4,"followUserId":3,"followType":3,"followerUserName":"哈喽你知道","followedUserId":1,"followedUserSignature":"啊啊","followedUserIcon":"/upload/avatars/avatar.png","followedUserName":"UL7b6mA0W","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1526451845000,"createTimeStr":"2018-05-16 14:24:05","updateTime":1527219146000,"updateTimeStr":"2018-05-25 11:32:26","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null},{"followId":3,"followUserId":3,"followType":3,"followerUserName":"哈喽你知道","followedUserId":14,"followedUserSignature":"凄凄切切","followedUserIcon":"/upload/avatars/avatar.png","followedUserName":"试试","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1526121726000,"createTimeStr":"2018-05-12 18:42:06","updateTime":1527077205000,"updateTimeStr":"2018-05-23 20:06:45","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null},{"followId":2,"followUserId":3,"followType":3,"followerUserName":"哈喽你知道","followedUserId":3,"followedUserSignature":"我这期是嘻嘻嘻WWI测试。很长。","followedUserIcon":"/upload/avatars/avatar.png","followedUserName":"哈喽你知道","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1525832269000,"createTimeStr":"2018-05-09 10:17:49","updateTime":1526972103000,"updateTimeStr":"2018-05-22 14:55:03","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"followType=3&followUserId=3"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * myFollows : {"rowCount":3,"rows":[{"followId":4,"followUserId":3,"followType":3,"followerUserName":"哈喽你知道","followedUserId":1,"followedUserSignature":"啊啊","followedUserIcon":"/upload/avatars/avatar.png","followedUserName":"UL7b6mA0W","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1526451845000,"createTimeStr":"2018-05-16 14:24:05","updateTime":1527219146000,"updateTimeStr":"2018-05-25 11:32:26","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null},{"followId":3,"followUserId":3,"followType":3,"followerUserName":"哈喽你知道","followedUserId":14,"followedUserSignature":"凄凄切切","followedUserIcon":"/upload/avatars/avatar.png","followedUserName":"试试","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1526121726000,"createTimeStr":"2018-05-12 18:42:06","updateTime":1527077205000,"updateTimeStr":"2018-05-23 20:06:45","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null},{"followId":2,"followUserId":3,"followType":3,"followerUserName":"哈喽你知道","followedUserId":3,"followedUserSignature":"我这期是嘻嘻嘻WWI测试。很长。","followedUserIcon":"/upload/avatars/avatar.png","followedUserName":"哈喽你知道","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1525832269000,"createTimeStr":"2018-05-09 10:17:49","updateTime":1526972103000,"updateTimeStr":"2018-05-22 14:55:03","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"followType=3&followUserId=3"}
         */

        private MyFollowsBean myFollows;

        public MyFollowsBean getMyFollows() {
            return myFollows;
        }

        public void setMyFollows(MyFollowsBean myFollows) {
            this.myFollows = myFollows;
        }

        public static class MyFollowsBean {
            /**
             * rowCount : 3
             * rows : [{"followId":4,"followUserId":3,"followType":3,"followerUserName":"哈喽你知道","followedUserId":1,"followedUserSignature":"啊啊","followedUserIcon":"/upload/avatars/avatar.png","followedUserName":"UL7b6mA0W","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1526451845000,"createTimeStr":"2018-05-16 14:24:05","updateTime":1527219146000,"updateTimeStr":"2018-05-25 11:32:26","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null},{"followId":3,"followUserId":3,"followType":3,"followerUserName":"哈喽你知道","followedUserId":14,"followedUserSignature":"凄凄切切","followedUserIcon":"/upload/avatars/avatar.png","followedUserName":"试试","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1526121726000,"createTimeStr":"2018-05-12 18:42:06","updateTime":1527077205000,"updateTimeStr":"2018-05-23 20:06:45","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null},{"followId":2,"followUserId":3,"followType":3,"followerUserName":"哈喽你知道","followedUserId":3,"followedUserSignature":"我这期是嘻嘻嘻WWI测试。很长。","followedUserIcon":"/upload/avatars/avatar.png","followedUserName":"哈喽你知道","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1525832269000,"createTimeStr":"2018-05-09 10:17:49","updateTime":1526972103000,"updateTimeStr":"2018-05-22 14:55:03","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null}]
             * pageSize : 1
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : followType=3&followUserId=3
             */

            private int rowCount;
            private int pageSize;
            private int rowsPerPage;
            private int curPageNum;
            private String queryParameters;
            private List<RowsBean> rows;

            public int getRowCount() {
                return rowCount;
            }

            public void setRowCount(int rowCount) {
                this.rowCount = rowCount;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getRowsPerPage() {
                return rowsPerPage;
            }

            public void setRowsPerPage(int rowsPerPage) {
                this.rowsPerPage = rowsPerPage;
            }

            public int getCurPageNum() {
                return curPageNum;
            }

            public void setCurPageNum(int curPageNum) {
                this.curPageNum = curPageNum;
            }

            public String getQueryParameters() {
                return queryParameters;
            }

            public void setQueryParameters(String queryParameters) {
                this.queryParameters = queryParameters;
            }

            public List<RowsBean> getRows() {
                return rows;
            }

            public void setRows(List<RowsBean> rows) {
                this.rows = rows;
            }

            public static class RowsBean {
                /**
                 * followId : 4
                 * followUserId : 3
                 * followType : 3
                 * followerUserName : 哈喽你知道
                 * followedUserId : 1
                 * followedUserSignature : 啊啊
                 * followedUserIcon : /upload/avatars/avatar.png
                 * followedUserName : UL7b6mA0W
                 * followedProjectId : null
                 * followedPostId : null
                 * status : 1
                 * createTime : 1526451845000
                 * createTimeStr : 2018-05-16 14:24:05
                 * updateTime : 1527219146000
                 * updateTimeStr : 2018-05-25 11:32:26
                 * postTitle : null
                 * postShortDesc : null
                 * postSmallImages : null
                 * commentsNum : null
                 * praiseNum : null
                 * pageviewNum : null
                 * donateNum : null
                 * collectNum : null
                 * createUserIcon : null
                 * createUserSignature : null
                 * createUserName : null
                 * totalScore : 0
                 * projectIcon : null
                 * projectCode : null
                 * projectEnglishName : null
                 * projectChineseName : null
                 * projectSignature : null
                 */

                private int followId;
                private int followUserId;
                private int followType;
                private String followerUserName;
                private int followedUserId;
                private String followedUserSignature;
                private String followedUserIcon;
                private String followedUserName;
                private int followedProjectId;
                private Object followedPostId;
                private int status;
                private int userType;
                private long createTime;
                private String createTimeStr;
                private long updateTime;
                private String updateTimeStr;
                private Object postTitle;
                private Object postShortDesc;
                private Object postSmallImages;
                private Object commentsNum;
                private Object praiseNum;
                private Object pageviewNum;
                private Object donateNum;
                private Object collectNum;
                private Object createUserIcon;
                private Object createUserSignature;
                private String createUserName;
                private double totalScore;
                private String projectIcon;
                private String projectCode;
                private String projectEnglishName;
                private String projectChineseName;
                private String projectSignature;

                public int getUserType() {
                    return userType;
                }

                public void setUserType(int userType) {
                    this.userType = userType;
                }

                public int getFollowId() {
                    return followId;
                }

                public void setFollowId(int followId) {
                    this.followId = followId;
                }

                public int getFollowUserId() {
                    return followUserId;
                }

                public void setFollowUserId(int followUserId) {
                    this.followUserId = followUserId;
                }

                public int getFollowType() {
                    return followType;
                }

                public void setFollowType(int followType) {
                    this.followType = followType;
                }

                public String getFollowerUserName() {
                    return followerUserName;
                }

                public void setFollowerUserName(String followerUserName) {
                    this.followerUserName = followerUserName;
                }

                public int getFollowedUserId() {
                    return followedUserId;
                }

                public void setFollowedUserId(int followedUserId) {
                    this.followedUserId = followedUserId;
                }

                public String getFollowedUserSignature() {
                    return followedUserSignature;
                }

                public void setFollowedUserSignature(String followedUserSignature) {
                    this.followedUserSignature = followedUserSignature;
                }

                public String getFollowedUserIcon() {
                    return followedUserIcon;
                }

                public void setFollowedUserIcon(String followedUserIcon) {
                    this.followedUserIcon = followedUserIcon;
                }

                public String getFollowedUserName() {
                    return followedUserName;
                }

                public void setFollowedUserName(String followedUserName) {
                    this.followedUserName = followedUserName;
                }

                public int getFollowedProjectId() {
                    return followedProjectId;
                }

                public void setFollowedProjectId(int followedProjectId) {
                    this.followedProjectId = followedProjectId;
                }

                public Object getFollowedPostId() {
                    return followedPostId;
                }

                public void setFollowedPostId(Object followedPostId) {
                    this.followedPostId = followedPostId;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
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

                public Object getPostTitle() {
                    return postTitle;
                }

                public void setPostTitle(Object postTitle) {
                    this.postTitle = postTitle;
                }

                public Object getPostShortDesc() {
                    return postShortDesc;
                }

                public void setPostShortDesc(Object postShortDesc) {
                    this.postShortDesc = postShortDesc;
                }

                public Object getPostSmallImages() {
                    return postSmallImages;
                }

                public void setPostSmallImages(Object postSmallImages) {
                    this.postSmallImages = postSmallImages;
                }

                public Object getCommentsNum() {
                    return commentsNum;
                }

                public void setCommentsNum(Object commentsNum) {
                    this.commentsNum = commentsNum;
                }

                public Object getPraiseNum() {
                    return praiseNum;
                }

                public void setPraiseNum(Object praiseNum) {
                    this.praiseNum = praiseNum;
                }

                public Object getPageviewNum() {
                    return pageviewNum;
                }

                public void setPageviewNum(Object pageviewNum) {
                    this.pageviewNum = pageviewNum;
                }

                public Object getDonateNum() {
                    return donateNum;
                }

                public void setDonateNum(Object donateNum) {
                    this.donateNum = donateNum;
                }

                public Object getCollectNum() {
                    return collectNum;
                }

                public void setCollectNum(Object collectNum) {
                    this.collectNum = collectNum;
                }

                public Object getCreateUserIcon() {
                    return createUserIcon;
                }

                public void setCreateUserIcon(Object createUserIcon) {
                    this.createUserIcon = createUserIcon;
                }

                public Object getCreateUserSignature() {
                    return createUserSignature;
                }

                public void setCreateUserSignature(Object createUserSignature) {
                    this.createUserSignature = createUserSignature;
                }

                public String getCreateUserName() {
                    return createUserName;
                }

                public void setCreateUserName(String createUserName) {
                    this.createUserName = createUserName;
                }

                public double getTotalScore() {
                    return totalScore;
                }

                public void setTotalScore(double totalScore) {
                    this.totalScore = totalScore;
                }

                public String getProjectIcon() {
                    return projectIcon;
                }

                public void setProjectIcon(String projectIcon) {
                    this.projectIcon = projectIcon;
                }

                public String getProjectCode() {
                    return projectCode;
                }

                public void setProjectCode(String projectCode) {
                    this.projectCode = projectCode;
                }

                public String getProjectEnglishName() {
                    return projectEnglishName;
                }

                public void setProjectEnglishName(String projectEnglishName) {
                    this.projectEnglishName = projectEnglishName;
                }

                public String getProjectChineseName() {
                    return projectChineseName;
                }

                public void setProjectChineseName(String projectChineseName) {
                    this.projectChineseName = projectChineseName;
                }

                public String getProjectSignature() {
                    return projectSignature;
                }

                public void setProjectSignature(String projectSignature) {
                    this.projectSignature = projectSignature;
                }
            }
        }
    }
}
