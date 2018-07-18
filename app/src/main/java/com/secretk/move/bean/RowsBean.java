package com.secretk.move.bean;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/5/7 14:14
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class RowsBean extends CommonListBase {

    /**
     * followStatus : 2
     * postId : 2
     * projectId : 1
     * projectIcon : /upload/projectIcon/123.jpg
     * projectCode : BTC
     * projectEnglishName : Bit Coin
     * projectChineseName : 比特币
     * projectSignature : ACA
     * totalScore : 8.5
     * postTitle : 比特币的文章
     * postType : 3
     * postShortDesc : 比特币的文章描述
     * postSmallImages : [{"fileUrl":"upload/imgs/12.jpg","fileName":"图片1","extension":"jpg"}]
     * commentsNum : 0
     * praiseNum : 0
     * pageviewNum : 0
     * donateNum : 0
     * collectNum : 0
     * createUserId : 14
     * createUserIcon : /upload/avatars/avatar.png
     * createUserSignature : 凄凄切切
     * createUserName : 试试
     * createTime : 1525619305000
     * createTimeStr : 2018-05-06 23:08:25
     * updateTime : 1525619300000
     * updateTimeStr : 2018-05-06 23:08:20
     * status : 1
     */
    private int followStatus;
    private int postId;
    private int projectId;
    private String projectIcon;
    private String projectCode;
    private String projectEnglishName;
    private String projectChineseName;
    private String projectSignature;
    private double totalScore;
    private double evaTotalScore;
    private String postTitle;
    private int postType;
    private int modelType;
    private String postShortDesc;
    private int commentsNum;
    private int praiseStatus;
    private int praiseNum;
    private int pageviewNum;
    private int donateNum;
    private int collectNum;
    private int createUserId;
    private int userType;
    private String createUserIcon;
    private String createUserSignature;
    private String createUserName;
    private long createTime;
    private String createTimeStr;
    private long updateTime;
    private String updateTimeStr;
    private int status;
    private List<PostSmallImagesListBean> postSmallImagesList;
    private String postSmallImages;
    private String professionalEvaDetail;

    /**
     * discussId
     * disscussContents
     * <p>
     * evaluationId
     * evaluationTags
     * evauationContent
     * tagInfos [{"tagId":1,"tagName":"进度讨论"},{"tagId":3,"tagName":"项目前景讨论"},{"tagId":4,"tagName":"打假"}]
     */
    private int discussId;
    private String disscussContents;

    private int valuationId;
    private String evaluationTags;
    private String evauationContent;
    private String tagInfos;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public void setEvaTotalScore(double evaTotalScore) {
        this.evaTotalScore = evaTotalScore;
    }

    public double getEvaTotalScore() {
        return evaTotalScore;
    }

    public int getPraiseStatus() {
        return praiseStatus;
    }

    public void setPraiseStatus(int praiseStatus) {
        this.praiseStatus = praiseStatus;
    }

    public void setModelType(int modelType) {
        this.modelType = modelType;
    }

    public int getModelType() {
        return modelType;
    }

    public String getProfessionalEvaDetail() {
        return professionalEvaDetail;
    }

    public void setProfessionalEvaDetail(String professionalEvaDetail) {
        this.professionalEvaDetail = professionalEvaDetail;
    }

    public void setTagInfos(String tagInfos) {
        this.tagInfos = tagInfos;
    }

    public String getTagInfos() {
        return tagInfos;
    }

    public int getDiscussId() {
        return discussId;
    }

    public void setDiscussId(int discussId) {
        this.discussId = discussId;
    }

    public String getDisscussContents() {
        return disscussContents;
    }

    public void setDisscussContents(String disscussContents) {
        this.disscussContents = disscussContents;
    }

    public int getValuationId() {
        return valuationId;
    }

    public void setValuationId(int valuationId) {
        this.valuationId = valuationId;
    }

    public String getEvaluationTags() {
        return evaluationTags;
    }

    public void setEvaluationTags(String evaluationTags) {
        this.evaluationTags = evaluationTags;
    }

    public String getEvauationContent() {
        return evauationContent;
    }

    public void setEvauationContent(String evauationContent) {
        this.evauationContent = evauationContent;
    }

    public int getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(int followStatus) {
        this.followStatus = followStatus;
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

    public String getProjectSignature() {
        return projectSignature;
    }

    public void setProjectSignature(String projectSignature) {
        this.projectSignature = projectSignature;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
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

    public String getPostSmallImages() {
        return postSmallImages;
    }

    public void setPostSmallImages(String postSmallImages) {
        this.postSmallImages = postSmallImages;
    }

    public void setPostSmallImagesList(List<PostSmallImagesListBean> postSmallImagesList) {
        this.postSmallImagesList = postSmallImagesList;
    }

    public List<PostSmallImagesListBean> getPostSmallImagesList() {
        return postSmallImagesList;
    }

    public static class PostSmallImagesListBean {
        /**
         * fileUrl : upload/imgs/12.jpg
         * fileName : 图片1
         * extension : jpg
         */

        private String fileUrl;
        private String fileName;
        private String extension;

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }
    }
}
