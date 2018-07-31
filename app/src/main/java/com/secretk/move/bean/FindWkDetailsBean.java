package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

/**
 * 作者： litongge
 * 时间：  2018/7/30 15:54
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class FindWkDetailsBean extends BaseRes{

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"data":{"id":6,"createdAt":1532674814000,"createDt":null,"updatedAt":1532674814000,"title":"ETH莱特币","projectId":3,"projectCode":"ETH","tokenName":"ETH","tokenCount":3000,"tokenEveryCount":100,"tokenCash":5000,"tokenNum":30,"tokenSurplusNum":30,"activityRemark":"纤维胶带确定强无敌看情况的卡萨丁卡打第三款拉到阿森大厦去多块钱的玩强无敌挖墙脚纤维胶带我确定钱到位我确定大枪懂我的请问","beginDt":"2018-07-28 14:59:42","endDt":"2018-08-01 14:59:45","activityStep":"0,2,","type":2,"articleId":49,"status":0,"statusMsg":null,"tokenUnclaimed":3000,"followType":0,"commentType":null,"shareType":0,"receiveType":0}}
     */

    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * data : {"id":6,"createdAt":1532674814000,"createDt":null,"updatedAt":1532674814000,"title":"ETH莱特币","projectId":3,"projectCode":"ETH","tokenName":"ETH","tokenCount":3000,"tokenEveryCount":100,"tokenCash":5000,"tokenNum":30,"tokenSurplusNum":30,"activityRemark":"纤维胶带确定强无敌看情况的卡萨丁卡打第三款拉到阿森大厦去多块钱的玩强无敌挖墙脚纤维胶带我确定钱到位我确定大枪懂我的请问","beginDt":"2018-07-28 14:59:42","endDt":"2018-08-01 14:59:45","activityStep":"0,2,","type":2,"articleId":49,"status":0,"statusMsg":null,"tokenUnclaimed":3000,"followType":0,"commentType":null,"shareType":0,"receiveType":0}
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
             * id : 6
             * createdAt : 1532674814000
             * createDt : null
             * updatedAt : 1532674814000
             * title : ETH莱特币
             * projectId : 3
             * projectCode : ETH
             * tokenName : ETH
             * tokenCount : 3000
             * tokenEveryCount : 100
             * tokenCash : 5000
             * tokenNum : 30
             * tokenSurplusNum : 30
             * activityRemark : 纤维胶带确定强无敌看情况的卡萨丁卡打第三款拉到阿森大厦去多块钱的玩强无敌挖墙脚纤维胶带我确定钱到位我确定大枪懂我的请问
             * beginDt : 2018-07-28 14:59:42
             * endDt : 2018-08-01 14:59:45
             * activityStep : 0,2,
             * type : 2
             * articleId : 49
             * status : 0
             * statusMsg : null
             * tokenUnclaimed : 3000
             * followType : 0
             * commentType : null
             * shareType : 0
             * receiveType : 0
             */

            private int id;
            private long createdAt;
            private Object createDt;
            private long updatedAt;
            private String title;
            private String projectIcon;
            private int projectId;
            private String projectCode;
            private String tokenName;
            private int tokenCount;
            private int tokenEveryCount;
            private int tokenCash;
            private int tokenNum;
            private int tokenSurplusNum;
            private String activityRemark;
            private String beginDt;
            private String endDt;
            private String activityStep;
            private int type;
            private int articleId;
            private int status;
            private Object statusMsg;
            private int tokenUnclaimed;
            private int followType;
            private int commentType;
            private int shareType;
            private int receiveType;

            public String getProjectIcon() {
                return projectIcon;
            }

            public void setProjectIcon(String projectIcon) {
                this.projectIcon = projectIcon;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(long createdAt) {
                this.createdAt = createdAt;
            }

            public Object getCreateDt() {
                return createDt;
            }

            public void setCreateDt(Object createDt) {
                this.createDt = createDt;
            }

            public long getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(long updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getProjectId() {
                return projectId;
            }

            public void setProjectId(int projectId) {
                this.projectId = projectId;
            }

            public String getProjectCode() {
                return projectCode;
            }

            public void setProjectCode(String projectCode) {
                this.projectCode = projectCode;
            }

            public String getTokenName() {
                return tokenName;
            }

            public void setTokenName(String tokenName) {
                this.tokenName = tokenName;
            }

            public int getTokenCount() {
                return tokenCount;
            }

            public void setTokenCount(int tokenCount) {
                this.tokenCount = tokenCount;
            }

            public int getTokenEveryCount() {
                return tokenEveryCount;
            }

            public void setTokenEveryCount(int tokenEveryCount) {
                this.tokenEveryCount = tokenEveryCount;
            }

            public int getTokenCash() {
                return tokenCash;
            }

            public void setTokenCash(int tokenCash) {
                this.tokenCash = tokenCash;
            }

            public int getTokenNum() {
                return tokenNum;
            }

            public void setTokenNum(int tokenNum) {
                this.tokenNum = tokenNum;
            }

            public int getTokenSurplusNum() {
                return tokenSurplusNum;
            }

            public void setTokenSurplusNum(int tokenSurplusNum) {
                this.tokenSurplusNum = tokenSurplusNum;
            }

            public String getActivityRemark() {
                return activityRemark;
            }

            public void setActivityRemark(String activityRemark) {
                this.activityRemark = activityRemark;
            }

            public String getBeginDt() {
                return beginDt;
            }

            public void setBeginDt(String beginDt) {
                this.beginDt = beginDt;
            }

            public String getEndDt() {
                return endDt;
            }

            public void setEndDt(String endDt) {
                this.endDt = endDt;
            }

            public String getActivityStep() {
                return activityStep;
            }

            public void setActivityStep(String activityStep) {
                this.activityStep = activityStep;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getArticleId() {
                return articleId;
            }

            public void setArticleId(int articleId) {
                this.articleId = articleId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getStatusMsg() {
                return statusMsg;
            }

            public void setStatusMsg(Object statusMsg) {
                this.statusMsg = statusMsg;
            }

            public int getTokenUnclaimed() {
                return tokenUnclaimed;
            }

            public void setTokenUnclaimed(int tokenUnclaimed) {
                this.tokenUnclaimed = tokenUnclaimed;
            }

            public int getFollowType() {
                return followType;
            }

            public void setFollowType(int followType) {
                this.followType = followType;
            }

            public int getCommentType() {
                return commentType;
            }

            public void setCommentType(int commentType) {
                this.commentType = commentType;
            }

            public int getShareType() {
                return shareType;
            }

            public void setShareType(int shareType) {
                this.shareType = shareType;
            }

            public int getReceiveType() {
                return receiveType;
            }

            public void setReceiveType(int receiveType) {
                this.receiveType = receiveType;
            }
        }
    }
}
