package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/5/9 16:49
 * 邮箱；ltg263@126.com
 * 描述：讨论最新评论信息
 */
public class DiscussNewInfoBean extends BaseRes{

    /**
     * data : {"comments":{"rowCount":3,"rows":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":3,"commentUserId":24,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"用户24","commentContent":"怎么能随便评论呢？","projectId":1,"postId":4,"postType":2,"praiseNum":29,"parentCommentsId":1,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835712000,"createTimeStr":"2018-05-09 11:15:12","updateTime":1525835714000,"updateTimeStr":"2018-05-09 11:15:14","status":1},{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":2,"commentUserId":25,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"@杭州","commentContent":"我也来评论一下啊","projectId":1,"postId":4,"postType":2,"praiseNum":300,"parentCommentsId":null,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835610000,"createTimeStr":"2018-05-09 11:13:30","updateTime":1525835613000,"updateTimeStr":"2018-05-09 11:13:33","status":1},{"praiseStatus":0,"childCommentsNum":1,"childCommentsList":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":3,"commentUserId":24,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"用户24","commentContent":"怎么能随便评论呢？","projectId":1,"postId":4,"postType":2,"praiseNum":29,"parentCommentsId":1,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835712000,"createTimeStr":"2018-05-09 11:15:12","updateTime":1525835714000,"updateTimeStr":"2018-05-09 11:15:14","status":1}],"commentsId":1,"commentUserId":14,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"试试","commentContent":"随便评论一下","projectId":1,"postId":4,"postType":2,"praiseNum":1000,"parentCommentsId":null,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835525000,"createTimeStr":"2018-05-09 11:12:05","updateTime":1525835528000,"updateTimeStr":"2018-05-09 11:12:08","status":1}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"postType=2&status=1&postId=4"}}
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
         * comments : {"rowCount":3,"rows":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":3,"commentUserId":24,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"用户24","commentContent":"怎么能随便评论呢？","projectId":1,"postId":4,"postType":2,"praiseNum":29,"parentCommentsId":1,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835712000,"createTimeStr":"2018-05-09 11:15:12","updateTime":1525835714000,"updateTimeStr":"2018-05-09 11:15:14","status":1},{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":2,"commentUserId":25,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"@杭州","commentContent":"我也来评论一下啊","projectId":1,"postId":4,"postType":2,"praiseNum":300,"parentCommentsId":null,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835610000,"createTimeStr":"2018-05-09 11:13:30","updateTime":1525835613000,"updateTimeStr":"2018-05-09 11:13:33","status":1},{"praiseStatus":0,"childCommentsNum":1,"childCommentsList":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":3,"commentUserId":24,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"用户24","commentContent":"怎么能随便评论呢？","projectId":1,"postId":4,"postType":2,"praiseNum":29,"parentCommentsId":1,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835712000,"createTimeStr":"2018-05-09 11:15:12","updateTime":1525835714000,"updateTimeStr":"2018-05-09 11:15:14","status":1}],"commentsId":1,"commentUserId":14,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"试试","commentContent":"随便评论一下","projectId":1,"postId":4,"postType":2,"praiseNum":1000,"parentCommentsId":null,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835525000,"createTimeStr":"2018-05-09 11:12:05","updateTime":1525835528000,"updateTimeStr":"2018-05-09 11:12:08","status":1}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"postType=2&status=1&postId=4"}
         */

        private CommentsBean comments;
        private CommentsBean newestComments;

        public CommentsBean getComments() {
            return comments;
        }

        public CommentsBean getNewestComments() {
            return newestComments;
        }

        public void setNewestComments(CommentsBean newestComments) {
            this.newestComments = newestComments;
        }

        public void setComments(CommentsBean comments) {
            this.comments = comments;
        }

        public static class CommentsBean {
            /**
             * rowCount : 3
             * rows : [{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":3,"commentUserId":24,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"用户24","commentContent":"怎么能随便评论呢？","projectId":1,"postId":4,"postType":2,"praiseNum":29,"parentCommentsId":1,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835712000,"createTimeStr":"2018-05-09 11:15:12","updateTime":1525835714000,"updateTimeStr":"2018-05-09 11:15:14","status":1},{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":2,"commentUserId":25,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"@杭州","commentContent":"我也来评论一下啊","projectId":1,"postId":4,"postType":2,"praiseNum":300,"parentCommentsId":null,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835610000,"createTimeStr":"2018-05-09 11:13:30","updateTime":1525835613000,"updateTimeStr":"2018-05-09 11:13:33","status":1},{"praiseStatus":0,"childCommentsNum":1,"childCommentsList":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"commentsId":3,"commentUserId":24,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"用户24","commentContent":"怎么能随便评论呢？","projectId":1,"postId":4,"postType":2,"praiseNum":29,"parentCommentsId":1,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835712000,"createTimeStr":"2018-05-09 11:15:12","updateTime":1525835714000,"updateTimeStr":"2018-05-09 11:15:14","status":1}],"commentsId":1,"commentUserId":14,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"试试","commentContent":"随便评论一下","projectId":1,"postId":4,"postType":2,"praiseNum":1000,"parentCommentsId":null,"becommentedUserId":3,"becommentedUserName":"UVHaV6BJo","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525835525000,"createTimeStr":"2018-05-09 11:12:05","updateTime":1525835528000,"updateTimeStr":"2018-05-09 11:12:08","status":1}]
             * pageSize : 1
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : postType=2&status=1&postId=4
             */

            private int rowCount;
            private int pageSize;
            private int rowsPerPage;
            private int curPageNum;
            private String queryParameters;
            private List<CommonCommentsBean> rows;

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

            public void setRows(List<CommonCommentsBean> rows) {
                this.rows = rows;
            }

            public List<CommonCommentsBean> getRows() {
                return rows;
            }
        }
    }
}
