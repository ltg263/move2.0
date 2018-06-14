package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/5/10 15:57
 * 邮箱；ltg263@126.com
 * 描述：文章相信 的实例
 */

public class DetailsArticleBean extends BaseRes{


    /**
     * data : {"articleDetail":{"articleId":0,"commendationList":[{"commendationId":2,"status":1,"createTime":1525700995000,"createTimeStr":"2018-05-07 21:49:55","updateTime":1525700998000,"updateTimeStr":"2018-05-07 21:49:58","sendUserId":3,"sendUserIcon":"/upload/avatars/avatar.png","receiveUserId":14,"postId":2,"projectId":1,"postType":1,"amount":20}],"postId":2,"followStatus":0,"articleContents":"文章内容文章内容","projectId":1,"projectIcon":"","projectCode":"BTC","projectEnglishName":"Bit Coin","projectChineseName":"","postTitle":"比特币的文章","postType":3,"postShortDesc":"比特币的文章描述","postSmallImages":"[{\"fileUrl\":\"/upload/posts/201805/1.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"},{\"fileUrl\":\"/upload/posts/201805/2.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"},{\"fileUrl\":\"/upload/posts/201805/3.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"}]","commentsNum":0,"praiseStatus":0,"praiseNum":0,"pageviewNum":0,"donateNum":0,"collectStatus":0,"collectNum":0,"createUserId":14,"createUserIcon":"/upload/avatars/avatar.png","createUserSignature":"凄凄切切","createUserName":"试试","createTime":1525619305000,"createTimeStr":"2018-05-06 23:08:25","updateTime":1525619300000,"updateTimeStr":"2018-05-06 23:08:20"}}
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
         * articleDetail : {"articleId":0,"commendationList":[{"commendationId":2,"status":1,"createTime":1525700995000,"createTimeStr":"2018-05-07 21:49:55","updateTime":1525700998000,"updateTimeStr":"2018-05-07 21:49:58","sendUserId":3,"sendUserIcon":"/upload/avatars/avatar.png","receiveUserId":14,"postId":2,"projectId":1,"postType":1,"amount":20}],"postId":2,"followStatus":0,"articleContents":"文章内容文章内容","projectId":1,"projectIcon":"","projectCode":"BTC","projectEnglishName":"Bit Coin","projectChineseName":"","postTitle":"比特币的文章","postType":3,"postShortDesc":"比特币的文章描述","postSmallImages":"[{\"fileUrl\":\"/upload/posts/201805/1.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"},{\"fileUrl\":\"/upload/posts/201805/2.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"},{\"fileUrl\":\"/upload/posts/201805/3.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"}]","commentsNum":0,"praiseStatus":0,"praiseNum":0,"pageviewNum":0,"donateNum":0,"collectStatus":0,"collectNum":0,"createUserId":14,"createUserIcon":"/upload/avatars/avatar.png","createUserSignature":"凄凄切切","createUserName":"试试","createTime":1525619305000,"createTimeStr":"2018-05-06 23:08:25","updateTime":1525619300000,"updateTimeStr":"2018-05-06 23:08:20"}
         */

        private ArticleDetailBean articleDetail;
        private List<CommonCommentsBean>   hotComments;

        public List<CommonCommentsBean> getHotComments() {
            return hotComments;
        }

        public void setHotComments(List<CommonCommentsBean> hotComments) {
            this.hotComments = hotComments;
        }

        public ArticleDetailBean getArticleDetail() {
            return articleDetail;
        }

        public void setArticleDetail(ArticleDetailBean articleDetail) {
            this.articleDetail = articleDetail;
        }

        public static class ArticleDetailBean {
            /**
             * articleId : 0
             * commendationList : [{"commendationId":2,"status":1,"createTime":1525700995000,"createTimeStr":"2018-05-07 21:49:55","updateTime":1525700998000,"updateTimeStr":"2018-05-07 21:49:58","sendUserId":3,"sendUserIcon":"/upload/avatars/avatar.png","receiveUserId":14,"postId":2,"projectId":1,"postType":1,"amount":20}]
             * postId : 2
             * followStatus : 0
             * articleContents : 文章内容文章内容
             * projectId : 1
             * projectIcon :
             * projectCode : BTC
             * projectEnglishName : Bit Coin
             * projectChineseName :
             * postTitle : 比特币的文章
             * postType : 3
             * postShortDesc : 比特币的文章描述
             * postSmallImages : [{"fileUrl":"/upload/posts/201805/1.jpg","fileName":"进度讨论","extension":"jpg"},{"fileUrl":"/upload/posts/201805/2.jpg","fileName":"进度讨论","extension":"jpg"},{"fileUrl":"/upload/posts/201805/3.jpg","fileName":"进度讨论","extension":"jpg"}]
             * commentsNum : 0
             * praiseStatus : 0
             * praiseNum : 0
             * pageviewNum : 0
             * donateNum : 0
             * commendationNum:20
             * collectStatus : 0
             * collectNum : 0
             * createUserId : 14
             * createUserIcon : /upload/avatars/avatar.png
             * createUserSignature : 凄凄切切
             * createUserName : 试试
             * createTime : 1525619305000
             * createTimeStr : 2018-05-06 23:08:25
             * updateTime : 1525619300000
             * updateTimeStr : 2018-05-06 23:08:20
             */

            private int articleId;
            private int userType;
            private int postId;
            private int followStatus;
            private String articleContents;
            private int projectId;
            private String projectIcon;
            private String projectCode;
            private String projectEnglishName;
            private String projectChineseName;
            private String postTitle;
            private int postType;
            private String postShortDesc;
            private String postSmallImages;
            private int commentsNum;
            private int praiseStatus;
            private int praiseNum;
            private int pageviewNum;
            private int donateNum;
            private int collectStatus;
            private int collectNum;
            private double commendationNum;
            private int createUserId;
            private String createUserIcon;
            private String createUserSignature;
            private String createUserName;
            private long createTime;
            private String createTimeStr;
            private long updateTime;
            private String updateTimeStr;
            private List<CommendationListBean> commendationList;

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public void setCommendationNum(double commendationNum) {
                this.commendationNum = commendationNum;
            }

            public double getCommendationNum() {
                return commendationNum;
            }


            public int getArticleId() {
                return articleId;
            }

            public void setArticleId(int articleId) {
                this.articleId = articleId;
            }

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public int getFollowStatus() {
                return followStatus;
            }

            public void setFollowStatus(int followStatus) {
                this.followStatus = followStatus;
            }

            public String getArticleContents() {
                return articleContents;
            }

            public void setArticleContents(String articleContents) {
                this.articleContents = articleContents;
            }

            public int getProjectId() {
                return projectId;
            }

            public void setProjectId(int projectId) {
                this.projectId = projectId;
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

            public String getPostTitle() {
                return postTitle;
            }

            public void setPostTitle(String postTitle) {
                this.postTitle = postTitle;
            }

            public int getPostType() {
                return postType;
            }

            public void setPostType(int postType) {
                this.postType = postType;
            }

            public String getPostShortDesc() {
                return postShortDesc;
            }

            public void setPostShortDesc(String postShortDesc) {
                this.postShortDesc = postShortDesc;
            }

            public String getPostSmallImages() {
                return postSmallImages;
            }

            public void setPostSmallImages(String postSmallImages) {
                this.postSmallImages = postSmallImages;
            }

            public int getCommentsNum() {
                return commentsNum;
            }

            public void setCommentsNum(int commentsNum) {
                this.commentsNum = commentsNum;
            }

            public int getPraiseStatus() {
                return praiseStatus;
            }

            public void setPraiseStatus(int praiseStatus) {
                this.praiseStatus = praiseStatus;
            }

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public int getPageviewNum() {
                return pageviewNum;
            }

            public void setPageviewNum(int pageviewNum) {
                this.pageviewNum = pageviewNum;
            }

            public int getDonateNum() {
                return donateNum;
            }

            public void setDonateNum(int donateNum) {
                this.donateNum = donateNum;
            }

            public int getCollectStatus() {
                return collectStatus;
            }

            public void setCollectStatus(int collectStatus) {
                this.collectStatus = collectStatus;
            }

            public int getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(int collectNum) {
                this.collectNum = collectNum;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public String getCreateUserIcon() {
                return createUserIcon;
            }

            public void setCreateUserIcon(String createUserIcon) {
                this.createUserIcon = createUserIcon;
            }

            public String getCreateUserSignature() {
                return createUserSignature;
            }

            public void setCreateUserSignature(String createUserSignature) {
                this.createUserSignature = createUserSignature;
            }

            public String getCreateUserName() {
                return createUserName;
            }

            public void setCreateUserName(String createUserName) {
                this.createUserName = createUserName;
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

            public List<CommendationListBean> getCommendationList() {
                return commendationList;
            }

            public void setCommendationList(List<CommendationListBean> commendationList) {
                this.commendationList = commendationList;
            }

            public static class CommendationListBean {
                /**
                 * commendationId : 2
                 * status : 1
                 * createTime : 1525700995000
                 * createTimeStr : 2018-05-07 21:49:55
                 * updateTime : 1525700998000
                 * updateTimeStr : 2018-05-07 21:49:58
                 * sendUserId : 3
                 * sendUserIcon : /upload/avatars/avatar.png
                 * receiveUserId : 14
                 * postId : 2
                 * projectId : 1
                 * postType : 1
                 * amount : 20
                 */

                private int commendationId;
                private int status;
                private long createTime;
                private String createTimeStr;
                private long updateTime;
                private String updateTimeStr;
                private int sendUserId;
                private String sendUserIcon;
                private int receiveUserId;
                private int postId;
                private int projectId;
                private int postType;
                private double amount;

                public int getCommendationId() {
                    return commendationId;
                }

                public void setCommendationId(int commendationId) {
                    this.commendationId = commendationId;
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

                public int getSendUserId() {
                    return sendUserId;
                }

                public void setSendUserId(int sendUserId) {
                    this.sendUserId = sendUserId;
                }

                public String getSendUserIcon() {
                    return sendUserIcon;
                }

                public void setSendUserIcon(String sendUserIcon) {
                    this.sendUserIcon = sendUserIcon;
                }

                public int getReceiveUserId() {
                    return receiveUserId;
                }

                public void setReceiveUserId(int receiveUserId) {
                    this.receiveUserId = receiveUserId;
                }

                public int getPostId() {
                    return postId;
                }

                public void setPostId(int postId) {
                    this.postId = postId;
                }

                public int getProjectId() {
                    return projectId;
                }

                public void setProjectId(int projectId) {
                    this.projectId = projectId;
                }

                public int getPostType() {
                    return postType;
                }

                public void setPostType(int postType) {
                    this.postType = postType;
                }

                public double getAmount() {
                    return amount;
                }

                public void setAmount(double amount) {
                    this.amount = amount;
                }
            }
        }
    }
}
