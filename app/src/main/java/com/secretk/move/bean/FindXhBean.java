package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/9/12 14:50
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class FindXhBean extends BaseRes{


    /**
     * data : {"curPageNum":1,"hasNext":false,"nextPage":1,"pageCount":1,"pageSize":1,"queryParameters":"state=0&isActivity=0&sort=rac.updated_at&endTime=Wed Sep 12 15:00:52 CST 2018","rowCount":1,"rows":[{"beginTime":1536115305000,"endTime":1537411309000,"postId":2116,"postTitle":"这些年我们在北京故宫举办过","projectCode":"AIF","projectIcon":"https://pic.qufen.top/projects20180628121526025.png","projectId":195,"rewardMoney":100500,"state":0}],"rowsPerPage":10}
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
         * curPageNum : 1
         * hasNext : false
         * nextPage : 1
         * pageCount : 1
         * pageSize : 1
         * queryParameters : state=0&isActivity=0&sort=rac.updated_at&endTime=Wed Sep 12 15:00:52 CST 2018
         * rowCount : 1
         * rows : [{"beginTime":1536115305000,"endTime":1537411309000,"postId":2116,"postTitle":"这些年我们在北京故宫举办过","projectCode":"AIF","projectIcon":"https://pic.qufen.top/projects20180628121526025.png","projectId":195,"rewardMoney":100500,"state":0}]
         * rowsPerPage : 10
         */

        private int curPageNum;
        private boolean hasNext;
        private int nextPage;
        private int pageCount;
        private int pageSize;
        private String queryParameters;
        private int rowCount;
        private int rowsPerPage;
        private List<RowsBean> rows;

        public int getCurPageNum() {
            return curPageNum;
        }

        public void setCurPageNum(int curPageNum) {
            this.curPageNum = curPageNum;
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public String getQueryParameters() {
            return queryParameters;
        }

        public void setQueryParameters(String queryParameters) {
            this.queryParameters = queryParameters;
        }

        public int getRowCount() {
            return rowCount;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public int getRowsPerPage() {
            return rowsPerPage;
        }

        public void setRowsPerPage(int rowsPerPage) {
            this.rowsPerPage = rowsPerPage;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * beginTime : 1536115305000
             * endTime : 1537411309000
             * postId : 2116
             * postTitle : 这些年我们在北京故宫举办过
             * projectCode : AIF
             * projectIcon : https://pic.qufen.top/projects20180628121526025.png
             * projectId : 195
             * rewardMoney : 100500
             * state : 0
             */

            private long beginTime;
            private long endTime;
            private int postId;
            private String postTitle;
            private String projectCode;
            private String projectIcon;
            private int projectId;
            private int rewardMoney;
            private int state;

            public long getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(long beginTime) {
                this.beginTime = beginTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public String getPostTitle() {
                return postTitle;
            }

            public void setPostTitle(String postTitle) {
                this.postTitle = postTitle;
            }

            public String getProjectCode() {
                return projectCode;
            }

            public void setProjectCode(String projectCode) {
                this.projectCode = projectCode;
            }

            public String getProjectIcon() {
                return projectIcon;
            }

            public void setProjectIcon(String projectIcon) {
                this.projectIcon = projectIcon;
            }

            public int getProjectId() {
                return projectId;
            }

            public void setProjectId(int projectId) {
                this.projectId = projectId;
            }

            public int getRewardMoney() {
                return rewardMoney;
            }

            public void setRewardMoney(int rewardMoney) {
                this.rewardMoney = rewardMoney;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }
    }
}
