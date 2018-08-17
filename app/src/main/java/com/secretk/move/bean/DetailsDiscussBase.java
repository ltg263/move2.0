package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/5/9 10:10
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class DetailsDiscussBase extends BaseRes{

    /**
     * data : {"discussDetail":{"discussId":2,"postId":4,"disscussContents":"发表的打假讨论评测描述测描述测描述测描述测描述测描述","postUuid":1,"tagInfos":"[{\"tagId\":1,\"tagName\":\"进度讨论\"},{\"tagId\":3,\"tagName\":\"项目前景讨论\"},{\"tagId\":4,\"tagName\":\"打假\"}]","followStatus":0,"projectId":1,"projectIcon":"","projectCode":"BTC","projectEnglishName":"Bit Coin","projectChineseName":"\u201c\u201d","postTitle":"发表的打假讨论","postType":2,"postShortDesc":"发表的打假讨论评测描述测描述测描述测描述测描述测描述","postSmallImages":"[{\"fileUrl\":\"/upload/posts/201805/1.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"},{\"fileUrl\":\"/upload/posts/201805/2.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"},{\"fileUrl\":\"/upload/posts/201805/3.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"}]","commentsNum":0,"praiseStatus":0,"praiseNum":0,"pageviewNum":0,"donateNum":0,"collectStatus":null,"collectNum":0,"createUserId":3,"createUserIcon":"/upload/avatars/avatar.png","createUserSignature":"凄凄切切","createUserName":"试试","createTime":1525619305000,"createTimeStr":"2018-05-06 23:08:25","updateTime":1525619300000,"updateTimeStr":"2018-05-06 23:08:20","hotComments":[{"praiseStatus":0,"childCommentsNum":1,"childCommentsList":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":3,"commentUserId":24,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"用户24","commentContent":"怎么能随便评论呢？","projectId":1,"postId":4,"postType":2,"praiseNum":29,"parentCommentsId":1,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835712000,"createTimeStr":"2018-05-09 11:15:12","updateTime":1525835714000,"updateTimeStr":"2018-05-09 11:15:14","status":1}],"commentsId":1,"commentUserId":14,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"试试","commentContent":"随便评论一下","projectId":1,"postId":4,"postType":2,"praiseNum":1000,"parentCommentsId":null,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835525000,"createTimeStr":"2018-05-09 11:12:05","updateTime":1525835528000,"updateTimeStr":"2018-05-09 11:12:08","status":1},{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":2,"commentUserId":25,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"@杭州","commentContent":"我也来评论一下啊","projectId":1,"postId":4,"postType":2,"praiseNum":300,"parentCommentsId":null,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835610000,"createTimeStr":"2018-05-09 11:13:30","updateTime":1525835613000,"updateTimeStr":"2018-05-09 11:13:33","status":1}]}}
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
         * discussDetail : {"discussId":2,"postId":4,"disscussContents":"发表的打假讨论评测描述测描述测描述测描述测描述测描述","postUuid":1,"tagInfos":"[{\"tagId\":1,\"tagName\":\"进度讨论\"},{\"tagId\":3,\"tagName\":\"项目前景讨论\"},{\"tagId\":4,\"tagName\":\"打假\"}]","followStatus":0,"projectId":1,"projectIcon":"","projectCode":"BTC","projectEnglishName":"Bit Coin","projectChineseName":"\u201c\u201d","postTitle":"发表的打假讨论","postType":2,"postShortDesc":"发表的打假讨论评测描述测描述测描述测描述测描述测描述","postSmallImages":"[{\"fileUrl\":\"/upload/posts/201805/1.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"},{\"fileUrl\":\"/upload/posts/201805/2.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"},{\"fileUrl\":\"/upload/posts/201805/3.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"}]","commentsNum":0,"praiseStatus":0,"praiseNum":0,"pageviewNum":0,"donateNum":0,"collectStatus":null,"collectNum":0,"createUserId":3,"createUserIcon":"/upload/avatars/avatar.png","createUserSignature":"凄凄切切","createUserName":"试试","createTime":1525619305000,"createTimeStr":"2018-05-06 23:08:25","updateTime":1525619300000,"updateTimeStr":"2018-05-06 23:08:20","hotComments":[{"praiseStatus":0,"childCommentsNum":1,"childCommentsList":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":3,"commentUserId":24,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"用户24","commentContent":"怎么能随便评论呢？","projectId":1,"postId":4,"postType":2,"praiseNum":29,"parentCommentsId":1,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835712000,"createTimeStr":"2018-05-09 11:15:12","updateTime":1525835714000,"updateTimeStr":"2018-05-09 11:15:14","status":1}],"commentsId":1,"commentUserId":14,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"试试","commentContent":"随便评论一下","projectId":1,"postId":4,"postType":2,"praiseNum":1000,"parentCommentsId":null,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835525000,"createTimeStr":"2018-05-09 11:12:05","updateTime":1525835528000,"updateTimeStr":"2018-05-09 11:12:08","status":1},{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":2,"commentUserId":25,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"@杭州","commentContent":"我也来评论一下啊","projectId":1,"postId":4,"postType":2,"praiseNum":300,"parentCommentsId":null,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835610000,"createTimeStr":"2018-05-09 11:13:30","updateTime":1525835613000,"updateTimeStr":"2018-05-09 11:13:33","status":1}]}
         */

        private DiscussDetailBean discussDetail;

        public DiscussDetailBean getDiscussDetail() {
            return discussDetail;
        }

        public void setDiscussDetail(DiscussDetailBean discussDetail) {
            this.discussDetail = discussDetail;
        }

        public static class DiscussDetailBean {
            /**
             * discussId : 2
             * postId : 4
             * disscussContents : 发表的打假讨论评测描述测描述测描述测描述测描述测描述
             * postUuid : 1
             * tagInfos : [{"tagId":1,"tagName":"进度讨论"},{"tagId":3,"tagName":"项目前景讨论"},{"tagId":4,"tagName":"打假"}]
             * followStatus : 0
             * projectId : 1
             * projectIcon :
             * projectCode : BTC
             * projectEnglishName : Bit Coin
             * projectChineseName : “”
             * postTitle : 发表的打假讨论
             * postType : 2
             * postShortDesc : 发表的打假讨论评测描述测描述测描述测描述测描述测描述
             * postSmallImages : [{"fileUrl":"/upload/posts/201805/1.jpg","fileName":"进度讨论","extension":"jpg"},{"fileUrl":"/upload/posts/201805/2.jpg","fileName":"进度讨论","extension":"jpg"},{"fileUrl":"/upload/posts/201805/3.jpg","fileName":"进度讨论","extension":"jpg"}]
             * commentsNum : 0
             * praiseStatus : 0
             * praiseNum : 0
             * pageviewNum : 0
             * donateNum : 0
             * collectStatus : null
             * collectNum : 0
             * createUserId : 3
             * createUserIcon : /upload/avatars/avatar.png
             * createUserSignature : 凄凄切切
             * createUserName : 试试
             * createTime : 1525619305000
             * createTimeStr : 2018-05-06 23:08:25
             * updateTime : 1525619300000
             * updateTimeStr : 2018-05-06 23:08:20
             * hotComments : [{"praiseStatus":0,"childCommentsNum":1,"childCommentsList":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":3,"commentUserId":24,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"用户24","commentContent":"怎么能随便评论呢？","projectId":1,"postId":4,"postType":2,"praiseNum":29,"parentCommentsId":1,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835712000,"createTimeStr":"2018-05-09 11:15:12","updateTime":1525835714000,"updateTimeStr":"2018-05-09 11:15:14","status":1}],"commentsId":1,"commentUserId":14,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"试试","commentContent":"随便评论一下","projectId":1,"postId":4,"postType":2,"praiseNum":1000,"parentCommentsId":null,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835525000,"createTimeStr":"2018-05-09 11:12:05","updateTime":1525835528000,"updateTimeStr":"2018-05-09 11:12:08","status":1},{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":2,"commentUserId":25,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"@杭州","commentContent":"我也来评论一下啊","projectId":1,"postId":4,"postType":2,"praiseNum":300,"parentCommentsId":null,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835610000,"createTimeStr":"2018-05-09 11:13:30","updateTime":1525835613000,"updateTimeStr":"2018-05-09 11:13:33","status":1}]
             */
            private int userType;
            private int discussId;
            private int postId;
            private String disscussContents;
            private int postUuid;
            private String tagInfos;
            private int followStatus;
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
            private int createUserId;
            private String createUserIcon;
            private String createUserSignature;

            private double commendationNum;

            private String createUserName;
            private long createTime;
            private String createTimeStr;
            private long updateTime;
            private String updateTimeStr;
            private List<CommonCommentsBean> hotComments;
            private List<CommendationListBean> commendationList;

            public List<CommendationListBean> getCommendationList() {
                return commendationList;
            }

            public void setCommendationList(List<CommendationListBean> commendationList) {
                this.commendationList = commendationList;
            }

            public double getCommendationNum() {
                return commendationNum;
            }

            public void setCommendationNum(double commendationNum) {
                this.commendationNum = commendationNum;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public int getDiscussId() {
                return discussId;
            }

            public void setDiscussId(int discussId) {
                this.discussId = discussId;
            }

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public String getDisscussContents() {
                return disscussContents;
            }

            public void setDisscussContents(String disscussContents) {
                this.disscussContents = disscussContents;
            }

            public int getPostUuid() {
                return postUuid;
            }

            public void setPostUuid(int postUuid) {
                this.postUuid = postUuid;
            }

            public String getTagInfos() {
                return tagInfos;
            }

            public void setTagInfos(String tagInfos) {
                this.tagInfos = tagInfos;
            }

            public int getFollowStatus() {
                return followStatus;
            }

            public void setFollowStatus(int followStatus) {
                this.followStatus = followStatus;
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

            public List<CommonCommentsBean> getHotComments() {
                return hotComments;
            }

            public void setHotComments(List<CommonCommentsBean> hotComments) {
                this.hotComments = hotComments;
            }
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
