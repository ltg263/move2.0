package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/6/21 18:04
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class MyFansList extends BaseRes{

    /**
     * data : {"myFans":{"rowCount":2,"rows":[{"followId":9,"followUserId":2,"followType":3,"followerUserName":"区分者0001","followedUserId":3,"followedUserSignature":null,"followedUserIcon":"http://pic.qufen.top/avatars20180621143248088.png","followedUserName":"区分者_0002","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1529575336000,"createTimeStr":"2018-06-21 18:02:16","updateTime":1529575336000,"updateTimeStr":"2018-06-21 18:02:16","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null},{"followId":1,"followUserId":2,"followType":3,"followerUserName":"区分者0001","followedUserId":1,"followedUserSignature":null,"followedUserIcon":"http://pic.qufen.top/Avatar3.png","followedUserName":"开发1","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1529560482000,"createTimeStr":"2018-06-21 13:54:42","updateTime":1529575338000,"updateTimeStr":"2018-06-21 18:02:18","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"followType=3&status=1&followUserId=2"}}
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
         * myFans : {"rowCount":2,"rows":[{"followId":9,"followUserId":2,"followType":3,"followerUserName":"区分者0001","followedUserId":3,"followedUserSignature":null,"followedUserIcon":"http://pic.qufen.top/avatars20180621143248088.png","followedUserName":"区分者_0002","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1529575336000,"createTimeStr":"2018-06-21 18:02:16","updateTime":1529575336000,"updateTimeStr":"2018-06-21 18:02:16","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null},{"followId":1,"followUserId":2,"followType":3,"followerUserName":"区分者0001","followedUserId":1,"followedUserSignature":null,"followedUserIcon":"http://pic.qufen.top/Avatar3.png","followedUserName":"开发1","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1529560482000,"createTimeStr":"2018-06-21 13:54:42","updateTime":1529575338000,"updateTimeStr":"2018-06-21 18:02:18","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"followType=3&status=1&followUserId=2"}
         */

        private MyFansBean myFans;

        public MyFansBean getMyFans() {
            return myFans;
        }

        public void setMyFans(MyFansBean myFans) {
            this.myFans = myFans;
        }

        public static class MyFansBean {
            /**
             * rowCount : 2
             * rows : [{"followId":9,"followUserId":2,"followType":3,"followerUserName":"区分者0001","followedUserId":3,"followedUserSignature":null,"followedUserIcon":"http://pic.qufen.top/avatars20180621143248088.png","followedUserName":"区分者_0002","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1529575336000,"createTimeStr":"2018-06-21 18:02:16","updateTime":1529575336000,"updateTimeStr":"2018-06-21 18:02:16","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null},{"followId":1,"followUserId":2,"followType":3,"followerUserName":"区分者0001","followedUserId":1,"followedUserSignature":null,"followedUserIcon":"http://pic.qufen.top/Avatar3.png","followedUserName":"开发1","followedProjectId":null,"followedPostId":null,"status":1,"createTime":1529560482000,"createTimeStr":"2018-06-21 13:54:42","updateTime":1529575338000,"updateTimeStr":"2018-06-21 18:02:18","postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null}]
             * pageSize : 1
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : followType=3&status=1&followUserId=2
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
                 * followId : 9
                 * followUserId : 2
                 * followType : 3
                 * followerUserName : 区分者0001
                 * followedUserId : 3
                 * followedUserSignature : null
                 * followedUserIcon : http://pic.qufen.top/avatars20180621143248088.png
                 * followedUserName : 区分者_0002
                 * followedProjectId : null
                 * followedPostId : null
                 * status : 1
                 * createTime : 1529575336000
                 * createTimeStr : 2018-06-21 18:02:16
                 * updateTime : 1529575336000
                 * updateTimeStr : 2018-06-21 18:02:16
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
                private Object followedProjectId;
                private Object followedPostId;
                private int status;
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
                private Object createUserName;
                private int totalScore;
                private Object projectIcon;
                private Object projectCode;
                private Object projectEnglishName;
                private Object projectChineseName;
                private Object projectSignature;

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

                public Object getFollowedProjectId() {
                    return followedProjectId;
                }

                public void setFollowedProjectId(Object followedProjectId) {
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

                public Object getCreateUserName() {
                    return createUserName;
                }

                public void setCreateUserName(Object createUserName) {
                    this.createUserName = createUserName;
                }

                public int getTotalScore() {
                    return totalScore;
                }

                public void setTotalScore(int totalScore) {
                    this.totalScore = totalScore;
                }

                public Object getProjectIcon() {
                    return projectIcon;
                }

                public void setProjectIcon(Object projectIcon) {
                    this.projectIcon = projectIcon;
                }

                public Object getProjectCode() {
                    return projectCode;
                }

                public void setProjectCode(Object projectCode) {
                    this.projectCode = projectCode;
                }

                public Object getProjectEnglishName() {
                    return projectEnglishName;
                }

                public void setProjectEnglishName(Object projectEnglishName) {
                    this.projectEnglishName = projectEnglishName;
                }

                public Object getProjectChineseName() {
                    return projectChineseName;
                }

                public void setProjectChineseName(Object projectChineseName) {
                    this.projectChineseName = projectChineseName;
                }

                public Object getProjectSignature() {
                    return projectSignature;
                }

                public void setProjectSignature(Object projectSignature) {
                    this.projectSignature = projectSignature;
                }
            }
        }
    }
}
