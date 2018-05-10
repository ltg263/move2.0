package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/5/10 18:45
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class DetailsArticleCommentBean extends BaseRes{

    /**
     * data : {"newestComments":{"rowCount":2,"rows":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":12,"commentUserId":12,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"UVHaV6BJo","commentContent":"吃顿饭","projectId":1,"postId":2,"postType":3,"praiseNum":100,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"试试","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525862057000,"createTimeStr":"2018-05-09 18:34:17","updateTime":1525935319000,"updateTimeStr":"2018-05-10 14:55:19","status":1},{"praiseStatus":0,"childCommentsNum":1,"childCommentsList":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":12,"commentUserId":12,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"UVHaV6BJo","commentContent":"吃顿饭","projectId":1,"postId":2,"postType":3,"praiseNum":100,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"试试","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525862057000,"createTimeStr":"2018-05-09 18:34:17","updateTime":1525935319000,"updateTimeStr":"2018-05-10 14:55:19","status":1}],"floor":0,"commentsId":11,"commentUserId":3,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"UVHaV6BJo","commentContent":"ABC","projectId":1,"postId":2,"postType":3,"praiseNum":100,"parentCommentsId":null,"becommentedUserId":14,"becommentedUserName":"试试","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525862057000,"createTimeStr":"2018-05-09 18:34:17","updateTime":1525935319000,"updateTimeStr":"2018-05-10 14:55:19","status":1}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":null}}
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
         * newestComments : {"rowCount":2,"rows":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":12,"commentUserId":12,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"UVHaV6BJo","commentContent":"吃顿饭","projectId":1,"postId":2,"postType":3,"praiseNum":100,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"试试","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525862057000,"createTimeStr":"2018-05-09 18:34:17","updateTime":1525935319000,"updateTimeStr":"2018-05-10 14:55:19","status":1},{"praiseStatus":0,"childCommentsNum":1,"childCommentsList":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":12,"commentUserId":12,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"UVHaV6BJo","commentContent":"吃顿饭","projectId":1,"postId":2,"postType":3,"praiseNum":100,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"试试","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525862057000,"createTimeStr":"2018-05-09 18:34:17","updateTime":1525935319000,"updateTimeStr":"2018-05-10 14:55:19","status":1}],"floor":0,"commentsId":11,"commentUserId":3,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"UVHaV6BJo","commentContent":"ABC","projectId":1,"postId":2,"postType":3,"praiseNum":100,"parentCommentsId":null,"becommentedUserId":14,"becommentedUserName":"试试","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525862057000,"createTimeStr":"2018-05-09 18:34:17","updateTime":1525935319000,"updateTimeStr":"2018-05-10 14:55:19","status":1}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":null}
         */

        private NewestCommentsBean newestComments;

        public NewestCommentsBean getNewestComments() {
            return newestComments;
        }

        public void setNewestComments(NewestCommentsBean newestComments) {
            this.newestComments = newestComments;
        }

        public static class NewestCommentsBean {
            /**
             * rowCount : 2
             * rows : [{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":12,"commentUserId":12,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"UVHaV6BJo","commentContent":"吃顿饭","projectId":1,"postId":2,"postType":3,"praiseNum":100,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"试试","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525862057000,"createTimeStr":"2018-05-09 18:34:17","updateTime":1525935319000,"updateTimeStr":"2018-05-10 14:55:19","status":1},{"praiseStatus":0,"childCommentsNum":1,"childCommentsList":[{"praiseStatus":0,"childCommentsNum":0,"childCommentsList":null,"floor":0,"commentsId":12,"commentUserId":12,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"UVHaV6BJo","commentContent":"吃顿饭","projectId":1,"postId":2,"postType":3,"praiseNum":100,"parentCommentsId":11,"becommentedUserId":3,"becommentedUserName":"试试","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525862057000,"createTimeStr":"2018-05-09 18:34:17","updateTime":1525935319000,"updateTimeStr":"2018-05-10 14:55:19","status":1}],"floor":0,"commentsId":11,"commentUserId":3,"commentUserIcon":"/upload/avatars/avatar.png","commentUserName":"UVHaV6BJo","commentContent":"ABC","projectId":1,"postId":2,"postType":3,"praiseNum":100,"parentCommentsId":null,"becommentedUserId":14,"becommentedUserName":"试试","becommentedUserIcon":"/upload/avatars/avatar.png","createTime":1525862057000,"createTimeStr":"2018-05-09 18:34:17","updateTime":1525935319000,"updateTimeStr":"2018-05-10 14:55:19","status":1}]
             * pageSize : 1
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : null
             */

            private int rowCount;
            private int pageSize;
            private int rowsPerPage;
            private int curPageNum;
            private Object queryParameters;
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

            public Object getQueryParameters() {
                return queryParameters;
            }

            public void setQueryParameters(Object queryParameters) {
                this.queryParameters = queryParameters;
            }

            public List<CommonCommentsBean> getRows() {
                return rows;
            }

            public void setRows(List<CommonCommentsBean> rows) {
                this.rows = rows;
            }

        }
    }
}
