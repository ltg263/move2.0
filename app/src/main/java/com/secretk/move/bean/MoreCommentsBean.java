package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/6/10 16:17
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class MoreCommentsBean extends BaseRes{

    /**
     * data : {"comments":{"rowCount":3,"rows":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":26,"commentUserId":3,"commentUserIcon":"/upload/avatars/201805/3.jpg","commentUserName":"m78","commentContent":"nsnsn","projectId":1,"postId":2,"postType":3,"praiseNum":0,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"m78","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1526978157000,"createTimeStr":"2018-05-22 16:35:57","updateTime":1526978157000,"updateTimeStr":"2018-05-22 16:35:57","status":1},{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":15,"commentUserId":3,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"m78","commentContent":"12366555","projectId":1,"postId":2,"postType":3,"praiseNum":0,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"m78","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1526001560000,"createTimeStr":"2018-05-11 09:19:20","updateTime":1526001560000,"updateTimeStr":"2018-05-11 09:19:20","status":1},{"praiseStatus":1,"childCommentsNum":1,"childCommentsList":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":19,"commentUserId":3,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"m78","commentContent":"159666","projectId":1,"postId":2,"postType":3,"praiseNum":0,"parentCommentsId":12,"becommentedUserId":12,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1526008219000,"createTimeStr":"2018-05-11 11:10:19","updateTime":1526008219000,"updateTimeStr":"2018-05-11 11:10:19","status":1}],"floor":0,"commentsId":12,"commentUserId":12,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"UVHaV6BJo","commentContent":"吃顿饭","projectId":1,"postId":2,"postType":3,"praiseNum":101,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"m78","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525862057000,"createTimeStr":"2018-05-09 18:34:17","updateTime":1525935319000,"updateTimeStr":"2018-05-10 14:55:19","status":1}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"parentCommentsId=11&status=1&postId=2"}}
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
         * comments : {"rowCount":3,"rows":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":26,"commentUserId":3,"commentUserIcon":"/upload/avatars/201805/3.jpg","commentUserName":"m78","commentContent":"nsnsn","projectId":1,"postId":2,"postType":3,"praiseNum":0,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"m78","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1526978157000,"createTimeStr":"2018-05-22 16:35:57","updateTime":1526978157000,"updateTimeStr":"2018-05-22 16:35:57","status":1},{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":15,"commentUserId":3,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"m78","commentContent":"12366555","projectId":1,"postId":2,"postType":3,"praiseNum":0,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"m78","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1526001560000,"createTimeStr":"2018-05-11 09:19:20","updateTime":1526001560000,"updateTimeStr":"2018-05-11 09:19:20","status":1},{"praiseStatus":1,"childCommentsNum":1,"childCommentsList":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":19,"commentUserId":3,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"m78","commentContent":"159666","projectId":1,"postId":2,"postType":3,"praiseNum":0,"parentCommentsId":12,"becommentedUserId":12,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1526008219000,"createTimeStr":"2018-05-11 11:10:19","updateTime":1526008219000,"updateTimeStr":"2018-05-11 11:10:19","status":1}],"floor":0,"commentsId":12,"commentUserId":12,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"UVHaV6BJo","commentContent":"吃顿饭","projectId":1,"postId":2,"postType":3,"praiseNum":101,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"m78","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525862057000,"createTimeStr":"2018-05-09 18:34:17","updateTime":1525935319000,"updateTimeStr":"2018-05-10 14:55:19","status":1}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"parentCommentsId=11&status=1&postId=2"}
         */

        private CommentsBean comments;

        public CommentsBean getComments() {
            return comments;
        }

        public void setComments(CommentsBean comments) {
            this.comments = comments;
        }

        public static class CommentsBean {
            /**
             * rowCount : 3
             * rows : [{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":26,"commentUserId":3,"commentUserIcon":"/upload/avatars/201805/3.jpg","commentUserName":"m78","commentContent":"nsnsn","projectId":1,"postId":2,"postType":3,"praiseNum":0,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"m78","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1526978157000,"createTimeStr":"2018-05-22 16:35:57","updateTime":1526978157000,"updateTimeStr":"2018-05-22 16:35:57","status":1},{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":15,"commentUserId":3,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"m78","commentContent":"12366555","projectId":1,"postId":2,"postType":3,"praiseNum":0,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"m78","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1526001560000,"createTimeStr":"2018-05-11 09:19:20","updateTime":1526001560000,"updateTimeStr":"2018-05-11 09:19:20","status":1},{"praiseStatus":1,"childCommentsNum":1,"childCommentsList":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":19,"commentUserId":3,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"m78","commentContent":"159666","projectId":1,"postId":2,"postType":3,"praiseNum":0,"parentCommentsId":12,"becommentedUserId":12,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1526008219000,"createTimeStr":"2018-05-11 11:10:19","updateTime":1526008219000,"updateTimeStr":"2018-05-11 11:10:19","status":1}],"floor":0,"commentsId":12,"commentUserId":12,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"UVHaV6BJo","commentContent":"吃顿饭","projectId":1,"postId":2,"postType":3,"praiseNum":101,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"m78","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525862057000,"createTimeStr":"2018-05-09 18:34:17","updateTime":1525935319000,"updateTimeStr":"2018-05-10 14:55:19","status":1}]
             * pageSize : 1
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : parentCommentsId=11&status=1&postId=2
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
                 * praiseStatus : 0
                 * childCommentsNum : 0
                 * childCommentsList : null
                 * floor : 0
                 * commentsId : 26
                 * commentUserId : 3
                 * commentUserIcon : /upload/avatars/201805/3.jpg
                 * commentUserName : m78
                 * commentContent : nsnsn
                 * projectId : 1
                 * postId : 2
                 * postType : 3
                 * praiseNum : 0
                 * parentCommentsId : 11
                 * becommentedUserId : 3
                 * becommentedUserName : m78
                 * becommentedUserIcon : /upload/avatars/avatar.png
                 * createTime : 1526978157000
                 * createTimeStr : 2018-05-22 16:35:57
                 * updateTime : 1526978157000
                 * updateTimeStr : 2018-05-22 16:35:57
                 * status : 1
                 */

                private int praiseStatus;
                private int childCommentsNum;
                private Object childCommentsList;
                private int floor;
                private int commentsId;
                private int commentUserId;
                private String commentUserIcon;
                private String commentUserName;
                private String commentContent;
                private int projectId;
                private int postId;
                private int postType;
                private int praiseNum;
                private int parentCommentsId;
                private int userType;
                private int becommentedUserId;
                private String becommentedUserName;
                private String becommentedUserIcon;
                private long createTime;
                private String createTimeStr;
                private long updateTime;
                private String updateTimeStr;
                private int status;

                public int getUserType() {
                    return userType;
                }

                public void setUserType(int userType) {
                    this.userType = userType;
                }

                public int getPraiseStatus() {
                    return praiseStatus;
                }

                public void setPraiseStatus(int praiseStatus) {
                    this.praiseStatus = praiseStatus;
                }

                public int getChildCommentsNum() {
                    return childCommentsNum;
                }

                public void setChildCommentsNum(int childCommentsNum) {
                    this.childCommentsNum = childCommentsNum;
                }

                public Object getChildCommentsList() {
                    return childCommentsList;
                }

                public void setChildCommentsList(Object childCommentsList) {
                    this.childCommentsList = childCommentsList;
                }

                public int getFloor() {
                    return floor;
                }

                public void setFloor(int floor) {
                    this.floor = floor;
                }

                public int getCommentsId() {
                    return commentsId;
                }

                public void setCommentsId(int commentsId) {
                    this.commentsId = commentsId;
                }

                public int getCommentUserId() {
                    return commentUserId;
                }

                public void setCommentUserId(int commentUserId) {
                    this.commentUserId = commentUserId;
                }

                public String getCommentUserIcon() {
                    return commentUserIcon;
                }

                public void setCommentUserIcon(String commentUserIcon) {
                    this.commentUserIcon = commentUserIcon;
                }

                public String getCommentUserName() {
                    return commentUserName;
                }

                public void setCommentUserName(String commentUserName) {
                    this.commentUserName = commentUserName;
                }

                public String getCommentContent() {
                    return commentContent;
                }

                public void setCommentContent(String commentContent) {
                    this.commentContent = commentContent;
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

                public void setPostId(int postId) {
                    this.postId = postId;
                }

                public int getPostType() {
                    return postType;
                }

                public void setPostType(int postType) {
                    this.postType = postType;
                }

                public int getPraiseNum() {
                    return praiseNum;
                }

                public void setPraiseNum(int praiseNum) {
                    this.praiseNum = praiseNum;
                }

                public int getParentCommentsId() {
                    return parentCommentsId;
                }

                public void setParentCommentsId(int parentCommentsId) {
                    this.parentCommentsId = parentCommentsId;
                }

                public int getBecommentedUserId() {
                    return becommentedUserId;
                }

                public void setBecommentedUserId(int becommentedUserId) {
                    this.becommentedUserId = becommentedUserId;
                }

                public String getBecommentedUserName() {
                    return becommentedUserName;
                }

                public void setBecommentedUserName(String becommentedUserName) {
                    this.becommentedUserName = becommentedUserName;
                }

                public String getBecommentedUserIcon() {
                    return becommentedUserIcon;
                }

                public void setBecommentedUserIcon(String becommentedUserIcon) {
                    this.becommentedUserIcon = becommentedUserIcon;
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
            }
        }
    }
}
