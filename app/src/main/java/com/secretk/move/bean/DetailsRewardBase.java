package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

/**
 * 作者： litongge
 * 时间：  2018/9/7 14:36
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class DetailsRewardBase extends BaseRes{

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"followStatus":0,"praiseStatus":0,"actionDesc":null,"actionType":null,"postId":2115,"projectId":2,"projectIcon":"http://pic.qufen.top/projects20180620203721298.png","projectCode":"TRX","projectEnglishName":"Tron","projectChineseName":"波场","projectSignature":null,"totalScore":0,"postTitle":"国家主席习近平夫人彭丽媛4日下午与卢旺达总统夫人珍妮特共同参观国家大剧院","postType":4,"postShortDesc":"国家主席习近平夫人彭丽媛4日下午与卢旺达总统夫人珍妮特共同参观国家大剧院","postSmallImagesList":null,"postSmallImages":"[]","commentsNum":136,"praiseNum":189,"pageviewNum":1,"donateNum":26,"collectNum":0,"createUserId":55789,"createUserIcon":"https://pic.qufen.top/Avatar2.png","createUserSignature":"","createUserName":"测试zdd三","userType":1,"createTime":1536112457000,"createTimeStr":"2018-09-05 09:54:17","updateTime":1536112457000,"updateTimeStr":"2018-09-05 09:54:17","status":1,"discussId":null,"disscussContents":null,"tagInfos":"[{\"tagId\":\"2\",\"tagName\":\"市值管理\"},{\"tagId\":\"3\",\"tagName\":\"项目进度\"},{\"tagId\":\"7\",\"tagName\":\"行情分析\"}]","evaTotalScore":0,"professionalEvaDetail":null,"evauationContent":null,"evaluationTags":null,"praiseIncome":null,"donateIncome":700,"postTotalIncome":700,"postUuid":null,"isNiceChoice":0,"niceChoiceAt":1536215070000,"type":2,"disStickTop":null,"disStickUpdateTime":null,"rewardMoney":5000,"answerCount":0,"endTime":1536374509000,"rewardContents":"sdakdskadsaskdsdqw"}
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
         * followStatus : 0
         * praiseStatus : 0
         * actionDesc : null
         * actionType : null
         * postId : 2115
         * projectId : 2
         * projectIcon : http://pic.qufen.top/projects20180620203721298.png
         * projectCode : TRX
         * projectEnglishName : Tron
         * projectChineseName : 波场
         * projectSignature : null
         * totalScore : 0
         * postTitle : 国家主席习近平夫人彭丽媛4日下午与卢旺达总统夫人珍妮特共同参观国家大剧院
         * postType : 4
         * postShortDesc : 国家主席习近平夫人彭丽媛4日下午与卢旺达总统夫人珍妮特共同参观国家大剧院
         * postSmallImagesList : null
         * postSmallImages : []
         * commentsNum : 136
         * praiseNum : 189
         * pageviewNum : 1
         * donateNum : 26
         * collectNum : 0
         * createUserId : 55789
         * createUserIcon : https://pic.qufen.top/Avatar2.png
         * createUserSignature :
         * createUserName : 测试zdd三
         * userType : 1
         * createTime : 1536112457000
         * createTimeStr : 2018-09-05 09:54:17
         * updateTime : 1536112457000
         * updateTimeStr : 2018-09-05 09:54:17
         * status : 1
         * discussId : null
         * disscussContents : null
         * tagInfos : [{"tagId":"2","tagName":"市值管理"},{"tagId":"3","tagName":"项目进度"},{"tagId":"7","tagName":"行情分析"}]
         * evaTotalScore : 0
         * professionalEvaDetail : null
         * evauationContent : null
         * evaluationTags : null
         * praiseIncome : null
         * donateIncome : 700
         * postTotalIncome : 700
         * postUuid : null
         * isNiceChoice : 0
         * niceChoiceAt : 1536215070000
         * type : 2
         * disStickTop : null
         * disStickUpdateTime : null
         * rewardMoney : 5000
         * answerCount : 0
         * endTime : 1536374509000
         * rewardContents : sdakdskadsaskdsdqw
         */

        private int followStatus;
        private int praiseStatus;
        private Object actionDesc;
        private Object actionType;
        private int postId;
        private int projectId;
        private String projectIcon;
        private String projectCode;
        private String projectEnglishName;
        private String projectChineseName;
        private Object projectSignature;
        private int totalScore;
        private String postTitle;
        private int postType;
        private String postShortDesc;
        private Object postSmallImagesList;
        private String postSmallImages;
        private int commentsNum;
        private int praiseNum;
        private int pageviewNum;
        private int donateNum;
        private int collectNum;
        private int createUserId;
        private String createUserIcon;
        private String createUserSignature;
        private String createUserName;
        private int userType;
        private long createTime;
        private String createTimeStr;
        private long updateTime;
        private String updateTimeStr;
        private int status;
        private int state;
        private Object discussId;
        private Object disscussContents;
        private String tagInfos;
        private int evaTotalScore;
        private Object professionalEvaDetail;
        private Object evauationContent;
        private Object evaluationTags;
        private Object praiseIncome;
        private int donateIncome;
        private double postTotalIncome;
        private Object postUuid;
        private int isNiceChoice;
        private long niceChoiceAt;
        private int type;
        private Object disStickTop;
        private Object disStickUpdateTime;
        private int rewardMoney;
        private int answerCount;
        private long endTime;
        private String rewardContents;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
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

        public Object getActionDesc() {
            return actionDesc;
        }

        public void setActionDesc(Object actionDesc) {
            this.actionDesc = actionDesc;
        }

        public Object getActionType() {
            return actionType;
        }

        public void setActionType(Object actionType) {
            this.actionType = actionType;
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

        public Object getProjectSignature() {
            return projectSignature;
        }

        public void setProjectSignature(Object projectSignature) {
            this.projectSignature = projectSignature;
        }

        public int getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(int totalScore) {
            this.totalScore = totalScore;
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

        public Object getPostSmallImagesList() {
            return postSmallImagesList;
        }

        public void setPostSmallImagesList(Object postSmallImagesList) {
            this.postSmallImagesList = postSmallImagesList;
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

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
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

        public Object getDiscussId() {
            return discussId;
        }

        public void setDiscussId(Object discussId) {
            this.discussId = discussId;
        }

        public Object getDisscussContents() {
            return disscussContents;
        }

        public void setDisscussContents(Object disscussContents) {
            this.disscussContents = disscussContents;
        }

        public String getTagInfos() {
            return tagInfos;
        }

        public void setTagInfos(String tagInfos) {
            this.tagInfos = tagInfos;
        }

        public int getEvaTotalScore() {
            return evaTotalScore;
        }

        public void setEvaTotalScore(int evaTotalScore) {
            this.evaTotalScore = evaTotalScore;
        }

        public Object getProfessionalEvaDetail() {
            return professionalEvaDetail;
        }

        public void setProfessionalEvaDetail(Object professionalEvaDetail) {
            this.professionalEvaDetail = professionalEvaDetail;
        }

        public Object getEvauationContent() {
            return evauationContent;
        }

        public void setEvauationContent(Object evauationContent) {
            this.evauationContent = evauationContent;
        }

        public Object getEvaluationTags() {
            return evaluationTags;
        }

        public void setEvaluationTags(Object evaluationTags) {
            this.evaluationTags = evaluationTags;
        }

        public Object getPraiseIncome() {
            return praiseIncome;
        }

        public void setPraiseIncome(Object praiseIncome) {
            this.praiseIncome = praiseIncome;
        }

        public int getDonateIncome() {
            return donateIncome;
        }

        public void setDonateIncome(int donateIncome) {
            this.donateIncome = donateIncome;
        }

        public double getPostTotalIncome() {
            return postTotalIncome;
        }

        public void setPostTotalIncome(double postTotalIncome) {
            this.postTotalIncome = postTotalIncome;
        }

        public Object getPostUuid() {
            return postUuid;
        }

        public void setPostUuid(Object postUuid) {
            this.postUuid = postUuid;
        }

        public int getIsNiceChoice() {
            return isNiceChoice;
        }

        public void setIsNiceChoice(int isNiceChoice) {
            this.isNiceChoice = isNiceChoice;
        }

        public long getNiceChoiceAt() {
            return niceChoiceAt;
        }

        public void setNiceChoiceAt(long niceChoiceAt) {
            this.niceChoiceAt = niceChoiceAt;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getDisStickTop() {
            return disStickTop;
        }

        public void setDisStickTop(Object disStickTop) {
            this.disStickTop = disStickTop;
        }

        public Object getDisStickUpdateTime() {
            return disStickUpdateTime;
        }

        public void setDisStickUpdateTime(Object disStickUpdateTime) {
            this.disStickUpdateTime = disStickUpdateTime;
        }

        public int getRewardMoney() {
            return rewardMoney;
        }

        public void setRewardMoney(int rewardMoney) {
            this.rewardMoney = rewardMoney;
        }

        public int getAnswerCount() {
            return answerCount;
        }

        public void setAnswerCount(int answerCount) {
            this.answerCount = answerCount;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getRewardContents() {
            return rewardContents;
        }

        public void setRewardContents(String rewardContents) {
            this.rewardContents = rewardContents;
        }
    }
}
