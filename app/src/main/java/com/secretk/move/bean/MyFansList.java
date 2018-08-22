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
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"myFans":{"rowCount":4,"rows":[{"followId":82467,"followUserId":null,"followType":null,"followerUserName":"数链评级","followedUserId":39,"followedUserSignature":"区分测试","followedUserIcon":"https://pic.qufen.top/avatars2018081410183219939.png","followedUserName":"区分者_F","followedProjectId":null,"followedPostId":null,"status":null,"createTime":null,"createTimeStr":null,"updateTime":null,"updateTimeStr":null,"userType":3,"postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null,"followerNum":null,"userName":"数链评级","userId":1,"userSignature":"做个俗人","userIcon":"http://pic.qufen.top/avatars20180622001908102.png"},{"followId":82463,"followUserId":null,"followType":null,"followerUserName":"区分者_007","followedUserId":39,"followedUserSignature":"区分测试","followedUserIcon":"https://pic.qufen.top/avatars2018081410183219939.png","followedUserName":"区分者_F","followedProjectId":null,"followedPostId":null,"status":null,"createTime":null,"createTimeStr":null,"updateTime":null,"updateTimeStr":null,"userType":3,"postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null,"followerNum":null,"userName":"区分者_007","userId":7,"userSignature":"啊哈哈吧","userIcon":"https://pic.qufen.top/avatars2018080719070579770.png"},{"followId":82460,"followUserId":null,"followType":null,"followerUserName":"测试账户认证","followedUserId":39,"followedUserSignature":"区分测试","followedUserIcon":"https://pic.qufen.top/avatars2018081410183219939.png","followedUserName":"区分者_F","followedProjectId":null,"followedPostId":null,"status":null,"createTime":null,"createTimeStr":null,"updateTime":null,"updateTimeStr":null,"userType":4,"postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null,"followerNum":null,"userName":"测试账户认证","userId":25019,"userSignature":"bbgggfgggddcvgh","userIcon":"https://pic.qufen.top/avatars20180717235122799.jpg"},{"followId":82457,"followUserId":null,"followType":null,"followerUserName":"腾讯","followedUserId":39,"followedUserSignature":"区分测试","followedUserIcon":"https://pic.qufen.top/avatars2018081410183219939.png","followedUserName":"区分者_F","followedProjectId":null,"followedPostId":null,"status":null,"createTime":null,"createTimeStr":null,"updateTime":null,"updateTimeStr":null,"userType":1,"postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null,"followerNum":null,"userName":"腾讯","userId":101,"userSignature":null,"userIcon":"https://pic.qufen.top/Avatar1.png"}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"followType=3&status=1&followedUserId=39","pageCount":1,"hasNext":false,"nextPage":1}}
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
         * myFans : {"rowCount":4,"rows":[{"followId":82467,"followUserId":null,"followType":null,"followerUserName":"数链评级","followedUserId":39,"followedUserSignature":"区分测试","followedUserIcon":"https://pic.qufen.top/avatars2018081410183219939.png","followedUserName":"区分者_F","followedProjectId":null,"followedPostId":null,"status":null,"createTime":null,"createTimeStr":null,"updateTime":null,"updateTimeStr":null,"userType":3,"postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null,"followerNum":null,"userName":"数链评级","userId":1,"userSignature":"做个俗人","userIcon":"http://pic.qufen.top/avatars20180622001908102.png"},{"followId":82463,"followUserId":null,"followType":null,"followerUserName":"区分者_007","followedUserId":39,"followedUserSignature":"区分测试","followedUserIcon":"https://pic.qufen.top/avatars2018081410183219939.png","followedUserName":"区分者_F","followedProjectId":null,"followedPostId":null,"status":null,"createTime":null,"createTimeStr":null,"updateTime":null,"updateTimeStr":null,"userType":3,"postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null,"followerNum":null,"userName":"区分者_007","userId":7,"userSignature":"啊哈哈吧","userIcon":"https://pic.qufen.top/avatars2018080719070579770.png"},{"followId":82460,"followUserId":null,"followType":null,"followerUserName":"测试账户认证","followedUserId":39,"followedUserSignature":"区分测试","followedUserIcon":"https://pic.qufen.top/avatars2018081410183219939.png","followedUserName":"区分者_F","followedProjectId":null,"followedPostId":null,"status":null,"createTime":null,"createTimeStr":null,"updateTime":null,"updateTimeStr":null,"userType":4,"postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null,"followerNum":null,"userName":"测试账户认证","userId":25019,"userSignature":"bbgggfgggddcvgh","userIcon":"https://pic.qufen.top/avatars20180717235122799.jpg"},{"followId":82457,"followUserId":null,"followType":null,"followerUserName":"腾讯","followedUserId":39,"followedUserSignature":"区分测试","followedUserIcon":"https://pic.qufen.top/avatars2018081410183219939.png","followedUserName":"区分者_F","followedProjectId":null,"followedPostId":null,"status":null,"createTime":null,"createTimeStr":null,"updateTime":null,"updateTimeStr":null,"userType":1,"postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null,"followerNum":null,"userName":"腾讯","userId":101,"userSignature":null,"userIcon":"https://pic.qufen.top/Avatar1.png"}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"followType=3&status=1&followedUserId=39","pageCount":1,"hasNext":false,"nextPage":1}
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
             * rowCount : 4
             * rows : [{"followId":82467,"followUserId":null,"followType":null,"followerUserName":"数链评级","followedUserId":39,"followedUserSignature":"区分测试","followedUserIcon":"https://pic.qufen.top/avatars2018081410183219939.png","followedUserName":"区分者_F","followedProjectId":null,"followedPostId":null,"status":null,"createTime":null,"createTimeStr":null,"updateTime":null,"updateTimeStr":null,"userType":3,"postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null,"followerNum":null,"userName":"数链评级","userId":1,"userSignature":"做个俗人","userIcon":"http://pic.qufen.top/avatars20180622001908102.png"},{"followId":82463,"followUserId":null,"followType":null,"followerUserName":"区分者_007","followedUserId":39,"followedUserSignature":"区分测试","followedUserIcon":"https://pic.qufen.top/avatars2018081410183219939.png","followedUserName":"区分者_F","followedProjectId":null,"followedPostId":null,"status":null,"createTime":null,"createTimeStr":null,"updateTime":null,"updateTimeStr":null,"userType":3,"postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null,"followerNum":null,"userName":"区分者_007","userId":7,"userSignature":"啊哈哈吧","userIcon":"https://pic.qufen.top/avatars2018080719070579770.png"},{"followId":82460,"followUserId":null,"followType":null,"followerUserName":"测试账户认证","followedUserId":39,"followedUserSignature":"区分测试","followedUserIcon":"https://pic.qufen.top/avatars2018081410183219939.png","followedUserName":"区分者_F","followedProjectId":null,"followedPostId":null,"status":null,"createTime":null,"createTimeStr":null,"updateTime":null,"updateTimeStr":null,"userType":4,"postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null,"followerNum":null,"userName":"测试账户认证","userId":25019,"userSignature":"bbgggfgggddcvgh","userIcon":"https://pic.qufen.top/avatars20180717235122799.jpg"},{"followId":82457,"followUserId":null,"followType":null,"followerUserName":"腾讯","followedUserId":39,"followedUserSignature":"区分测试","followedUserIcon":"https://pic.qufen.top/avatars2018081410183219939.png","followedUserName":"区分者_F","followedProjectId":null,"followedPostId":null,"status":null,"createTime":null,"createTimeStr":null,"updateTime":null,"updateTimeStr":null,"userType":1,"postTitle":null,"postShortDesc":null,"postSmallImages":null,"commentsNum":null,"praiseNum":null,"pageviewNum":null,"donateNum":null,"collectNum":null,"createUserIcon":null,"createUserSignature":null,"createUserName":null,"totalScore":0,"projectIcon":null,"projectCode":null,"projectEnglishName":null,"projectChineseName":null,"projectSignature":null,"followerNum":null,"userName":"腾讯","userId":101,"userSignature":null,"userIcon":"https://pic.qufen.top/Avatar1.png"}]
             * pageSize : 1
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : followType=3&status=1&followedUserId=39
             * pageCount : 1
             * hasNext : false
             * nextPage : 1
             */

            private int rowCount;
            private int pageSize;
            private int rowsPerPage;
            private int curPageNum;
            private String queryParameters;
            private int pageCount;
            private boolean hasNext;
            private int nextPage;
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

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
            }

            public boolean isHasNext() {
                return hasNext;
            }

            public void setHasNext(boolean hasNext) {
                this.hasNext = hasNext;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public List<RowsBean> getRows() {
                return rows;
            }

            public void setRows(List<RowsBean> rows) {
                this.rows = rows;
            }

            public static class RowsBean {
                /**
                 * followId : 82467
                 * followUserId : null
                 * followType : null
                 * followerUserName : 数链评级
                 * followedUserId : 39
                 * followedUserSignature : 区分测试
                 * followedUserIcon : https://pic.qufen.top/avatars2018081410183219939.png
                 * followedUserName : 区分者_F
                 * followedProjectId : null
                 * followedPostId : null
                 * status : null
                 * createTime : null
                 * createTimeStr : null
                 * updateTime : null
                 * updateTimeStr : null
                 * userType : 3
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
                 * followerNum : null
                 * userName : 数链评级
                 * userId : 1
                 * userSignature : 做个俗人
                 * userIcon : http://pic.qufen.top/avatars20180622001908102.png
                 */

                private int followId;
                private Object followUserId;
                private int followType;
                private int followStatus;
                private String followerUserName;
                private int followedUserId;
                private String followedUserSignature;
                private String followedUserIcon;
                private String followedUserName;
                private Object followedProjectId;
                private Object followedPostId;
                private Object status;
                private Object createTime;
                private Object createTimeStr;
                private Object updateTime;
                private Object updateTimeStr;
                private int userType;
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
                private double totalScore;
                private Object projectIcon;
                private Object projectCode;
                private Object projectEnglishName;
                private Object projectChineseName;
                private Object projectSignature;
                private Object followerNum;
                private String userName;
                private int userId;
                private String userSignature;
                private String userIcon;

                public int getFollowStatus() {
                    return followStatus;
                }

                public void setFollowStatus(int followStatus) {
                    this.followStatus = followStatus;
                }

                public int getFollowId() {
                    return followId;
                }

                public void setFollowId(int followId) {
                    this.followId = followId;
                }

                public Object getFollowUserId() {
                    return followUserId;
                }

                public void setFollowUserId(Object followUserId) {
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

                public Object getStatus() {
                    return status;
                }

                public void setStatus(Object status) {
                    this.status = status;
                }

                public Object getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(Object createTime) {
                    this.createTime = createTime;
                }

                public Object getCreateTimeStr() {
                    return createTimeStr;
                }

                public void setCreateTimeStr(Object createTimeStr) {
                    this.createTimeStr = createTimeStr;
                }

                public Object getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(Object updateTime) {
                    this.updateTime = updateTime;
                }

                public Object getUpdateTimeStr() {
                    return updateTimeStr;
                }

                public void setUpdateTimeStr(Object updateTimeStr) {
                    this.updateTimeStr = updateTimeStr;
                }

                public int getUserType() {
                    return userType;
                }

                public void setUserType(int userType) {
                    this.userType = userType;
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

                public double getTotalScore() {
                    return totalScore;
                }

                public void setTotalScore(double totalScore) {
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

                public Object getFollowerNum() {
                    return followerNum;
                }

                public void setFollowerNum(Object followerNum) {
                    this.followerNum = followerNum;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public String getUserSignature() {
                    return userSignature;
                }

                public void setUserSignature(String userSignature) {
                    this.userSignature = userSignature;
                }

                public String getUserIcon() {
                    return userIcon;
                }

                public void setUserIcon(String userIcon) {
                    this.userIcon = userIcon;
                }
            }
        }
    }
}
