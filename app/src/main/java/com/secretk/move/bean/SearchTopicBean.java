package com.secretk.move.bean;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/9/6 10:37
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class SearchTopicBean {

    /**
     * status : null
     * msg : Success
     * code : 0
     * reason : null
     * fromuri : null
     * token : null
     * data : {"rowCount":1,"rows":[{"tagId":1,"tagName":"团队","createUserId":1,"createTime":1529496960000,"createTimeStr":"2018-06-20 20:16:00","updateTime":1529496960000,"updateTimeStr":"2018-06-20 20:16:00","status":true,"memo":"团队","typeId":1,"imgPath":null,"stickTop":0,"stickUpdateTime":null}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"sortSequence=desc&sortField=createTime&title=团队&status=1","nextPage":1,"hasNext":false,"pageCount":1}
     * serverDatetime : 1536200969858
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
         * rowCount : 1
         * rows : [{"tagId":1,"tagName":"团队","createUserId":1,"createTime":1529496960000,"createTimeStr":"2018-06-20 20:16:00","updateTime":1529496960000,"updateTimeStr":"2018-06-20 20:16:00","status":true,"memo":"团队","typeId":1,"imgPath":null,"stickTop":0,"stickUpdateTime":null}]
         * pageSize : 1
         * rowsPerPage : 10
         * curPageNum : 1
         * queryParameters : sortSequence=desc&sortField=createTime&title=团队&status=1
         * nextPage : 1
         * hasNext : false
         * pageCount : 1
         */

        private int rowCount;
        private int pageSize;
        private int rowsPerPage;
        private int curPageNum;
        private String queryParameters;
        private int nextPage;
        private boolean hasNext;
        private int pageCount;
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

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * tagId : 1
             * tagName : 团队
             * createUserId : 1
             * createTime : 1529496960000
             * createTimeStr : 2018-06-20 20:16:00
             * updateTime : 1529496960000
             * updateTimeStr : 2018-06-20 20:16:00
             * status : true
             * memo : 团队
             * typeId : 1
             * imgPath : null
             * stickTop : 0
             * stickUpdateTime : null
             */

            private int tagId;
            private String tagName;
            private int createUserId;
            private long createTime;
            private String createTimeStr;
            private long updateTime;
            private String updateTimeStr;
            private boolean status;
            private String memo;
            private int typeId;
            private String imgPath;
            private int stickTop;
            private Object stickUpdateTime;

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
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

            public boolean isStatus() {
                return status;
            }

            public void setStatus(boolean status) {
                this.status = status;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public int getStickTop() {
                return stickTop;
            }

            public void setStickTop(int stickTop) {
                this.stickTop = stickTop;
            }

            public Object getStickUpdateTime() {
                return stickUpdateTime;
            }

            public void setStickUpdateTime(Object stickUpdateTime) {
                this.stickUpdateTime = stickUpdateTime;
            }
        }
    }
}
