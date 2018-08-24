package com.secretk.move.bean;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/5/14 10:30
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class DetailsReviewBean {

    /**
     * data : {"evaluationDetail":{"followStatus":1,"praiseStatus":0,"collectStatus":0,"commendationList":[{"commendationId":1,"status":1,"createTime":1525700995000,"createTimeStr":"2018-05-07 21:49:55","updateTime":1525700998000,"updateTimeStr":"2018-05-07 21:49:58","sendUserId":14,"sendUserIcon":"/upload/avatars/avatar.png","receiveUserId":3,"postId":3,"projectId":1,"postType":1,"amount":10}],"evaluationId":2,"projectId":1,"postId":3,"postUuid":null,"modelType":2,"totalScore":8.9,"professionalEvaDetail":"[\r\n{\"id\":10,\"modelId\":3,\"detailName\":\"项目定位\",\"detailDesc\":\"从项目前景、市场空间、竞争对手等各方面来评估。\",\"detailWeight\":30,\"totalScore\":8.6,\"raterNum\":212},\r\n{\"id\":11,\"modelId\":3,\"detailName\":\"技术框架\",\"detailDesc\":\"从项目前景、市场空间、竞争对手等各方面来评估。\",\"detailWeight\":30,\"totalScore\":7.6,\"raterNum\":112},\r\n{\"id\":12,\"modelId\":3,\"detailName\":\"团队实力\",\"detailDesc\":\"从项目前景、市场空间、竞争对手等各方面来评估。\",\"detailWeight\":30,\"totalScore\":5.6,\"raterNum\":142},\r\n{\"id\":13,\"modelId\":3,\"detailName\":\"项目进度\",\"detailDesc\":\"从项目前景、市场空间、竞争对手等各方面来评估。\",\"detailWeight\":20,\"totalScore\":4.6,\"raterNum\":122}\r\n]","evauationContent":"简单评测的内容","evaluationTags":null,"status":1,"createTime":1525619305000,"createTimeStr":"2018-05-06 23:08:25","updateTime":1526107083000,"updateTimeStr":"2018-05-12 14:38:03","createUserId":14,"projectIcon":"","projectCode":"BTC","projectEnglishName":"Bit Coin","postTitle":"比特币的评测","postType":1,"postShortDesc":"比特币的评测描述","postSmallImages":"[{\"fileUrl\":\"/upload/posts/201805/1.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"},{\"fileUrl\":\"/upload/posts/201805/2.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"},{\"fileUrl\":\"/upload/posts/201805/3.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"}]","postSmallImagesList":null,"commentsNum":0,"praiseNum":0,"pageviewNum":0,"donateNum":0,"collectNum":0,"createUserIcon":"/upload/avatars/avatar.png","createUserSignature":"凄凄切切","createUserName":"试试","fullProEvaList":null,"hotEvaList":null}}
     * serverDatetime : 1526264737270
     */

    private DataBean data;
    private long serverDatetime;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public long getServerDatetime() {
        return serverDatetime;
    }

    public void setServerDatetime(long serverDatetime) {
        this.serverDatetime = serverDatetime;
    }

    public static class DataBean {
        /**
         * evaluationDetail : {"followStatus":1,"praiseStatus":0,"collectStatus":0,"commendationList":[{"commendationId":1,"status":1,"createTime":1525700995000,"createTimeStr":"2018-05-07 21:49:55","updateTime":1525700998000,"updateTimeStr":"2018-05-07 21:49:58","sendUserId":14,"sendUserIcon":"/upload/avatars/avatar.png","receiveUserId":3,"postId":3,"projectId":1,"postType":1,"amount":10}],"evaluationId":2,"projectId":1,"postId":3,"postUuid":null,"modelType":2,"totalScore":8.9,"professionalEvaDetail":"[\r\n{\"id\":10,\"modelId\":3,\"detailName\":\"项目定位\",\"detailDesc\":\"从项目前景、市场空间、竞争对手等各方面来评估。\",\"detailWeight\":30,\"totalScore\":8.6,\"raterNum\":212},\r\n{\"id\":11,\"modelId\":3,\"detailName\":\"技术框架\",\"detailDesc\":\"从项目前景、市场空间、竞争对手等各方面来评估。\",\"detailWeight\":30,\"totalScore\":7.6,\"raterNum\":112},\r\n{\"id\":12,\"modelId\":3,\"detailName\":\"团队实力\",\"detailDesc\":\"从项目前景、市场空间、竞争对手等各方面来评估。\",\"detailWeight\":30,\"totalScore\":5.6,\"raterNum\":142},\r\n{\"id\":13,\"modelId\":3,\"detailName\":\"项目进度\",\"detailDesc\":\"从项目前景、市场空间、竞争对手等各方面来评估。\",\"detailWeight\":20,\"totalScore\":4.6,\"raterNum\":122}\r\n]","evauationContent":"简单评测的内容","evaluationTags":null,"status":1,"createTime":1525619305000,"createTimeStr":"2018-05-06 23:08:25","updateTime":1526107083000,"updateTimeStr":"2018-05-12 14:38:03","createUserId":14,"projectIcon":"","projectCode":"BTC","projectEnglishName":"Bit Coin","postTitle":"比特币的评测","postType":1,"postShortDesc":"比特币的评测描述","postSmallImages":"[{\"fileUrl\":\"/upload/posts/201805/1.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"},{\"fileUrl\":\"/upload/posts/201805/2.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"},{\"fileUrl\":\"/upload/posts/201805/3.jpg\",\"fileName\":\"进度讨论\",\"extension\":\"jpg\"}]","postSmallImagesList":null,"commentsNum":0,"praiseNum":0,"pageviewNum":0,"donateNum":0,"collectNum":0,"createUserIcon":"/upload/avatars/avatar.png","createUserSignature":"凄凄切切","createUserName":"试试","fullProEvaList":null,"hotEvaList":null}
         */

        private EvaluationDetailBean evaluationDetail;

        public EvaluationDetailBean getEvaluationDetail() {
            return evaluationDetail;
        }

        public void setEvaluationDetail(EvaluationDetailBean evaluationDetail) {
            this.evaluationDetail = evaluationDetail;
        }

        public static class EvaluationDetailBean {
            /**
             * followStatus : 1
             * praiseStatus : 0
             * collectStatus : 0
             * commendationList : [{"commendationId":1,"status":1,"createTime":1525700995000,"createTimeStr":"2018-05-07 21:49:55","updateTime":1525700998000,"updateTimeStr":"2018-05-07 21:49:58","sendUserId":14,"sendUserIcon":"/upload/avatars/avatar.png","receiveUserId":3,"postId":3,"projectId":1,"postType":1,"amount":10}]
             * evaluationId : 2
             * projectId : 1
             * postId : 3
             * postUuid : null
             * modelType : 2
             * totalScore : 8.9
             * professionalEvaDetail : [
             {"id":10,"modelId":3,"detailName":"项目定位","detailDesc":"从项目前景、市场空间、竞争对手等各方面来评估。","detailWeight":30,"totalScore":8.6,"raterNum":212},
             {"id":11,"modelId":3,"detailName":"技术框架","detailDesc":"从项目前景、市场空间、竞争对手等各方面来评估。","detailWeight":30,"totalScore":7.6,"raterNum":112},
             {"id":12,"modelId":3,"detailName":"团队实力","detailDesc":"从项目前景、市场空间、竞争对手等各方面来评估。","detailWeight":30,"totalScore":5.6,"raterNum":142},
             {"id":13,"modelId":3,"detailName":"项目进度","detailDesc":"从项目前景、市场空间、竞争对手等各方面来评估。","detailWeight":20,"totalScore":4.6,"raterNum":122}
             ]
             * evauationContent : 简单评测的内容
             * evaluationTags : null
             * status : 1
             * createTime : 1525619305000
             * createTimeStr : 2018-05-06 23:08:25
             * updateTime : 1526107083000
             * updateTimeStr : 2018-05-12 14:38:03
             * createUserId : 14
             * projectIcon :
             * projectCode : BTC
             * projectEnglishName : Bit Coin
             * postTitle : 比特币的评测
             * postType : 1
             * postShortDesc : 比特币的评测描述
             * postSmallImages : [{"fileUrl":"/upload/posts/201805/1.jpg","fileName":"进度讨论","extension":"jpg"},{"fileUrl":"/upload/posts/201805/2.jpg","fileName":"进度讨论","extension":"jpg"},{"fileUrl":"/upload/posts/201805/3.jpg","fileName":"进度讨论","extension":"jpg"}]
             * postSmallImagesList : null
             * commentsNum : 0
             * praiseNum : 0
             * pageviewNum : 0
             * donateNum : 0
             * collectNum : 0
             * createUserIcon : /upload/avatars/avatar.png
             * createUserSignature : 凄凄切切
             * createUserName : 试试
             * fullProEvaList : null
             * hotEvaList : null
             */

            private int followStatus;
            private int praiseStatus;
            private int collectStatus;
            private int evaluationId;
            private int projectId;
            private int postId;
            private Object postUuid;
            private int modelType;
            private int userType;
            private double totalScore;
            private String professionalEvaDetail;
            private String evauationContent;
            private String evaluationTags;
            private int status;
            private long createTime;
            private String createTimeStr;
            private long updateTime;
            private String updateTimeStr;
            private int createUserId;
            private String projectIcon;
            private String projectCode;
            private String projectEnglishName;
            private String projectChineseName;
            private String postTitle;
            private int postType;
            private String postShortDesc;
            private String postSmallImages;
            private Object postSmallImagesList;
            private int commentsNum;
            private int praiseNum;
            private double postTotalIncome;
            private int pageviewNum;
            private int donateNum;
            private int collectNum;
            private double commendationNum;
            private String createUserIcon;
            private String createUserSignature;
            private String createUserName;
            private Object fullProEvaList;
            private Object hotEvaList;

            public double getPostTotalIncome() {
                return postTotalIncome;
            }

            public void setPostTotalIncome(double postTotalIncome) {
                this.postTotalIncome = postTotalIncome;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            private List<CommendationListBean> commendationList;

            public String getProjectChineseName() {
                return projectChineseName;
            }

            public void setProjectChineseName(String projectChineseName) {
                this.projectChineseName = projectChineseName;
            }

            public int getFollowStatus() {
                return followStatus;
            }

            public void setFollowStatus(int followStatus) {
                this.followStatus = followStatus;
            }

            public int getPraiseStatus() {
                return praiseStatus;
            }

            public void setPraiseStatus(int praiseStatus) {
                this.praiseStatus = praiseStatus;
            }

            public int getCollectStatus() {
                return collectStatus;
            }

            public void setCollectStatus(int collectStatus) {
                this.collectStatus = collectStatus;
            }

            public int getEvaluationId() {
                return evaluationId;
            }

            public void setEvaluationId(int evaluationId) {
                this.evaluationId = evaluationId;
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

            public void setCommendationNum(double commendationNum) {
                this.commendationNum = commendationNum;
            }

            public double getCommendationNum() {
                return commendationNum;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public Object getPostUuid() {
                return postUuid;
            }

            public void setPostUuid(Object postUuid) {
                this.postUuid = postUuid;
            }

            public int getModelType() {
                return modelType;
            }

            public void setModelType(int modelType) {
                this.modelType = modelType;
            }

            public double getTotalScore() {
                return totalScore;
            }

            public void setTotalScore(double totalScore) {
                this.totalScore = totalScore;
            }

            public String getProfessionalEvaDetail() {
                return professionalEvaDetail;
            }

            public void setProfessionalEvaDetail(String professionalEvaDetail) {
                this.professionalEvaDetail = professionalEvaDetail;
            }

            public String getEvauationContent() {
                return evauationContent;
            }

            public void setEvauationContent(String evauationContent) {
                this.evauationContent = evauationContent;
            }

            public String getEvaluationTags() {
                return evaluationTags;
            }

            public void setEvaluationTags(String evaluationTags) {
                this.evaluationTags = evaluationTags;
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

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
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

            public Object getPostSmallImagesList() {
                return postSmallImagesList;
            }

            public void setPostSmallImagesList(Object postSmallImagesList) {
                this.postSmallImagesList = postSmallImagesList;
            }

            public int getCommentsNum() {
                return commentsNum;
            }

            public void setCommentsNum(int commentsNum) {
                this.commentsNum = commentsNum;
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

            public int getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(int collectNum) {
                this.collectNum = collectNum;
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

            public Object getFullProEvaList() {
                return fullProEvaList;
            }

            public void setFullProEvaList(Object fullProEvaList) {
                this.fullProEvaList = fullProEvaList;
            }

            public Object getHotEvaList() {
                return hotEvaList;
            }

            public void setHotEvaList(Object hotEvaList) {
                this.hotEvaList = hotEvaList;
            }

            public List<CommendationListBean> getCommendationList() {
                return commendationList;
            }

            public void setCommendationList(List<CommendationListBean> commendationList) {
                this.commendationList = commendationList;
            }

            public static class CommendationListBean {
                /**
                 * commendationId : 1
                 * status : 1
                 * createTime : 1525700995000
                 * createTimeStr : 2018-05-07 21:49:55
                 * updateTime : 1525700998000
                 * updateTimeStr : 2018-05-07 21:49:58
                 * sendUserId : 14
                 * sendUserIcon : /upload/avatars/avatar.png
                 * receiveUserId : 3
                 * postId : 3
                 * projectId : 1
                 * postType : 1
                 * amount : 10
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
