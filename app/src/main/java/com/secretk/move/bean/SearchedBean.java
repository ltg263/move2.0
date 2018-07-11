package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

public class SearchedBean  extends BaseRes {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private ProjectsBean projects;

        public ProjectsBean getProjects() {
            return projects;
        }

        public void setProjects(ProjectsBean projects) {
            this.projects = projects;
        }

        public static class ProjectsBean {

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
                 * activeUsers : null
                 * owner : null
                 * followStatus : 0
                 * projectId : 5
                 * projectIcon : /upload/projects/1.jpg
                 * state : 2
                 * projectCode : 11132
                 * projectEnglishName : 321
                 * projectChineseName : 123
                 * projectSignature : 214
                 * websiteUrl : 321
                 * listed : 0
                 * issueDate : 1524931200000
                 * issueDateStr : 2018-04-29
                 * issueNum : 10000
                 * whitepaperUrl : 23
                 * projectTypeName : 供应链
                 * projectTypeId : 4
                 * projectDesc : 2314
                 * submitUserId : 1
                 * submitUserContactInfo : 系统后台提交
                 * submitUserType : 1
                 * submitReason : 系统后台提交
                 * status : 1
                 * createTime : 1526875382000
                 * createTimeStr : 2018-05-21 12:03:02
                 * publishTime : 1526875382000
                 * publishTimeStr : 2018-05-21 12:03:02
                 * updateTime : 1526955105000
                 * updateTimeStr : 2018-05-22 10:11:45
                 * totalScore : 7.2
                 * raterNum : 33
                 * followerNum : 6
                 * commentsNum : 0
                 * collectNum : 0
                 * totalRaterNum : 37
                 */

                private Object activeUsers;
                private Object owner;
                private int followStatus;
                private int projectId;
                private String projectIcon;
                private int state;
                private String projectCode;
                private String projectEnglishName;
                private String projectChineseName;
                private String projectSignature;
                private String websiteUrl;
                private int listed;
                private long issueDate;
                private String issueDateStr;
                private int issueNum;
                private String whitepaperUrl;
                private String projectTypeName;
                private int projectTypeId;
                private String projectDesc;
                private int submitUserId;
                private String submitUserContactInfo;
                private int submitUserType;
                private String submitReason;
                private int status;
                private long createTime;
                private String createTimeStr;
                private long publishTime;
                private String publishTimeStr;
                private long updateTime;
                private String updateTimeStr;
                private double totalScore;
                private int raterNum;
                private int followerNum;
                private int commentsNum;
                private int collectNum;
                private int totalRaterNum;

                public Object getActiveUsers() {
                    return activeUsers;
                }

                public void setActiveUsers(Object activeUsers) {
                    this.activeUsers = activeUsers;
                }

                public Object getOwner() {
                    return owner;
                }

                public void setOwner(Object owner) {
                    this.owner = owner;
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

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
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

                public String getWebsiteUrl() {
                    return websiteUrl;
                }

                public void setWebsiteUrl(String websiteUrl) {
                    this.websiteUrl = websiteUrl;
                }

                public int getListed() {
                    return listed;
                }

                public void setListed(int listed) {
                    this.listed = listed;
                }

                public long getIssueDate() {
                    return issueDate;
                }

                public void setIssueDate(long issueDate) {
                    this.issueDate = issueDate;
                }

                public String getIssueDateStr() {
                    return issueDateStr;
                }

                public void setIssueDateStr(String issueDateStr) {
                    this.issueDateStr = issueDateStr;
                }

                public int getIssueNum() {
                    return issueNum;
                }

                public void setIssueNum(int issueNum) {
                    this.issueNum = issueNum;
                }

                public String getWhitepaperUrl() {
                    return whitepaperUrl;
                }

                public void setWhitepaperUrl(String whitepaperUrl) {
                    this.whitepaperUrl = whitepaperUrl;
                }

                public String getProjectTypeName() {
                    return projectTypeName;
                }

                public void setProjectTypeName(String projectTypeName) {
                    this.projectTypeName = projectTypeName;
                }

                public int getProjectTypeId() {
                    return projectTypeId;
                }

                public void setProjectTypeId(int projectTypeId) {
                    this.projectTypeId = projectTypeId;
                }

                public String getProjectDesc() {
                    return projectDesc;
                }

                public void setProjectDesc(String projectDesc) {
                    this.projectDesc = projectDesc;
                }

                public int getSubmitUserId() {
                    return submitUserId;
                }

                public void setSubmitUserId(int submitUserId) {
                    this.submitUserId = submitUserId;
                }

                public String getSubmitUserContactInfo() {
                    return submitUserContactInfo;
                }

                public void setSubmitUserContactInfo(String submitUserContactInfo) {
                    this.submitUserContactInfo = submitUserContactInfo;
                }

                public int getSubmitUserType() {
                    return submitUserType;
                }

                public void setSubmitUserType(int submitUserType) {
                    this.submitUserType = submitUserType;
                }

                public String getSubmitReason() {
                    return submitReason;
                }

                public void setSubmitReason(String submitReason) {
                    this.submitReason = submitReason;
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

                public long getPublishTime() {
                    return publishTime;
                }

                public void setPublishTime(long publishTime) {
                    this.publishTime = publishTime;
                }

                public String getPublishTimeStr() {
                    return publishTimeStr;
                }

                public void setPublishTimeStr(String publishTimeStr) {
                    this.publishTimeStr = publishTimeStr;
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

                public double getTotalScore() {
                    return totalScore;
                }

                public void setTotalScore(double totalScore) {
                    this.totalScore = totalScore;
                }

                public int getRaterNum() {
                    return raterNum;
                }

                public void setRaterNum(int raterNum) {
                    this.raterNum = raterNum;
                }

                public int getFollowerNum() {
                    return followerNum;
                }

                public void setFollowerNum(int followerNum) {
                    this.followerNum = followerNum;
                }

                public int getCommentsNum() {
                    return commentsNum;
                }

                public void setCommentsNum(int commentsNum) {
                    this.commentsNum = commentsNum;
                }

                public int getCollectNum() {
                    return collectNum;
                }

                public void setCollectNum(int collectNum) {
                    this.collectNum = collectNum;
                }

                public int getTotalRaterNum() {
                    return totalRaterNum;
                }

                public void setTotalRaterNum(int totalRaterNum) {
                    this.totalRaterNum = totalRaterNum;
                }
            }
        }
    }
}
