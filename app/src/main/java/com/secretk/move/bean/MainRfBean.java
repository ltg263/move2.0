package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * Created by zc on 2018/4/26.
 */

public class MainRfBean extends BaseRes {
    private String status;
    private String reason;
    private String fromuri;
    private String token;
    private Data data;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }


    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

    public void setFromuri(String fromuri) {
        this.fromuri = fromuri;
    }

    public String getFromuri() {
        return this.fromuri;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public class Data {
        private Recommends recommends;

        public void setRecommends(Recommends recommends) {
            this.recommends = recommends;
        }

        public Recommends getRecommends() {
            return this.recommends;
        }

        private Follows follows;

        public Follows getFollows() {
            return follows;
        }

        public void setFollows(Follows follows) {
            this.follows = follows;
        }
    }

    public static class Recommends {
        private int rowCount;

        private List<Rows> rows;

        private int pageSize;

        private int rowsPerPage;

        private int curPageNum;

        private String queryParameters;

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public int getRowCount() {
            return this.rowCount;
        }

        public void setRows(List<Rows> rows) {
            this.rows = rows;
        }

        public List<Rows> getRows() {
            return this.rows;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public void setRowsPerPage(int rowsPerPage) {
            this.rowsPerPage = rowsPerPage;
        }

        public int getRowsPerPage() {
            return this.rowsPerPage;
        }

        public void setCurPageNum(int curPageNum) {
            this.curPageNum = curPageNum;
        }

        public int getCurPageNum() {
            return this.curPageNum;
        }

        public void setQueryParameters(String queryParameters) {
            this.queryParameters = queryParameters;
        }

        public String getQueryParameters() {
            return this.queryParameters;
        }

    }

    public static class Follows {
        private int rowCount;

        private List<Rows> rows;

        private int pageSize;

        private int rowsPerPage;

        private int curPageNum;

        private String queryParameters;

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public int getRowCount() {
            return this.rowCount;
        }

        public void setRows(List<Rows> rows) {
            this.rows = rows;
        }

        public List<Rows> getRows() {
            return this.rows;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public void setRowsPerPage(int rowsPerPage) {
            this.rowsPerPage = rowsPerPage;
        }

        public int getRowsPerPage() {
            return this.rowsPerPage;
        }

        public void setCurPageNum(int curPageNum) {
            this.curPageNum = curPageNum;
        }

        public int getCurPageNum() {
            return this.curPageNum;
        }

        public void setQueryParameters(String queryParameters) {
            this.queryParameters = queryParameters;
        }

        public String getQueryParameters() {
            return this.queryParameters;
        }

    }

    public static class Rows {
        int followStatus;

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        int projectId;
        String postId;

        public int getPostType() {
            return postType;
        }

        public void setPostType(int postType) {
            this.postType = postType;
        }

        int postType;
        String projectIcon;
        String projectCode;
        String projectEnglishName;
        String projectChineseName;
        String projectSignature;
        String postTitle;
        String postShortDesc;
        int praiseNum;
        int commentsNum;
        String createUserName;

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        int createUserId;
        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        long createTime;
        String totalScore;
        List<PostSmallImagesList> postSmallImagesList;
        public List<PostSmallImagesList> getPostSmallImagesList() {
            return postSmallImagesList;
        }

        public void setPostSmallImagesList(List<PostSmallImagesList> postSmallImagesList) {
            this.postSmallImagesList = postSmallImagesList;
        }


        public String getCreateUserIcon() {
            return createUserIcon;
        }

        public void setCreateUserIcon(String createUserIcon) {
            this.createUserIcon = createUserIcon;
        }

        String createUserIcon;


        public int getFollowStatus() {
            return followStatus;
        }

        public void setFollowStatus(int followStatus) {
            this.followStatus = followStatus;
        }



        public String getPostId() {
            return postId;
        }

        public void setPostId(String postId) {
            this.postId = postId;
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

        public String getPostTitle() {
            return postTitle;
        }

        public void setPostTitle(String postTitle) {
            this.postTitle = postTitle;
        }

        public String getPostShortDesc() {
            return postShortDesc;
        }

        public void setPostShortDesc(String postShortDesc) {
            this.postShortDesc = postShortDesc;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getCommentsNum() {
            return commentsNum;
        }

        public void setCommentsNum(int commentsNum) {
            this.commentsNum = commentsNum;
        }

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }



        public String getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(String totalScore) {
            this.totalScore = totalScore;
        }


    }

    public static class PostSmallImagesList {
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

        String fileUrl;
        String fileName;
        String extension;
    }
}
