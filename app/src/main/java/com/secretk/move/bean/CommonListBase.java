package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/4/27 16:39
 * 邮箱；ltg263@126.com
 * 描述：我的评测数据
 */

public class CommonListBase extends BaseRes{

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"articles":{"rowCount":0,"rows":[{"followStatus":2,"postId":2,"projectId":1,"projectIcon":"/upload/projectIcon/123.jpg","projectCode":"BTC","projectEnglishName":"Bit Coin","projectChineseName":"比特币","projectSignature":"ACA","totalScore":8.5,"postTitle":"比特币的文章","postType":3,"postShortDesc":"比特币的文章描述","postSmallImages":[{"fileUrl":"upload/imgs/12.jpg","fileName":"图片1","extension":"jpg"}],"commentsNum":0,"praiseNum":0,"pageviewNum":0,"donateNum":0,"collectNum":0,"createUserId":14,"createUserIcon":"/upload/avatars/avatar.png","createUserSignature":"凄凄切切","createUserName":"试试","createTime":1525619305000,"createTimeStr":"2018-05-06 23:08:25","updateTime":1525619300000,"updateTimeStr":"2018-05-06 23:08:20","status":1}],"pageSize":0,"rowsPerPage":10,"curPageNum":1,"queryParameters":null}}
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
         * articles : {"rowCount":0,"rows":[{"followStatus":2,"postId":2,"projectId":1,"projectIcon":"/upload/projectIcon/123.jpg","projectCode":"BTC","projectEnglishName":"Bit Coin","projectChineseName":"比特币","projectSignature":"ACA","totalScore":8.5,"postTitle":"比特币的文章","postType":3,"postShortDesc":"比特币的文章描述","postSmallImages":[{"fileUrl":"upload/imgs/12.jpg","fileName":"图片1","extension":"jpg"}],"commentsNum":0,"praiseNum":0,"pageviewNum":0,"donateNum":0,"collectNum":0,"createUserId":14,"createUserIcon":"/upload/avatars/avatar.png","createUserSignature":"凄凄切切","createUserName":"试试","createTime":1525619305000,"createTimeStr":"2018-05-06 23:08:25","updateTime":1525619300000,"updateTimeStr":"2018-05-06 23:08:20","status":1}],"pageSize":0,"rowsPerPage":10,"curPageNum":1,"queryParameters":null}
         */

        private DetailsBean evaluations;
        private DetailsBean articles;
        private DetailsBean discusses;

        private DetailsBean recommends;
        private DetailsBean myTokenRecords;
        private DetailsBean follows;

        public DetailsBean getFollows() {
            return follows;
        }

        public void setFollows(DetailsBean follows) {
            this.follows = follows;
        }

        public DetailsBean getMyTokenRecords() {
            return myTokenRecords;
        }

        public void setMyTokenRecords(DetailsBean myTokenRecords) {
            this.myTokenRecords = myTokenRecords;
        }

        public void setEvaluations(DetailsBean evaluations) {
            this.evaluations = evaluations;
        }

        public DetailsBean getEvaluations() {
            return evaluations;
        }

        public DetailsBean getArticles() {
            return articles;
        }

        public void setArticles(DetailsBean articles) {
            this.articles = articles;
        }

        public void setDiscusses(DetailsBean discusses) {
            this.discusses = discusses;
        }

        public DetailsBean getRecommends() {
            return recommends;
        }

        public void setRecommends(DetailsBean recommends) {
            this.recommends = recommends;
        }

        public DetailsBean getDiscusses() {
            return discusses;
        }


        public static class DetailsBean {
            /**
             * rowCount : 0
             * rows : [{"followStatus":2,"postId":2,"projectId":1,"projectIcon":"/upload/projectIcon/123.jpg","projectCode":"BTC","projectEnglishName":"Bit Coin","projectChineseName":"比特币","projectSignature":"ACA","totalScore":8.5,"postTitle":"比特币的文章","postType":3,"postShortDesc":"比特币的文章描述","postSmallImages":[{"fileUrl":"upload/imgs/12.jpg","fileName":"图片1","extension":"jpg"}],"commentsNum":0,"praiseNum":0,"pageviewNum":0,"donateNum":0,"collectNum":0,"createUserId":14,"createUserIcon":"/upload/avatars/avatar.png","createUserSignature":"凄凄切切","createUserName":"试试","createTime":1525619305000,"createTimeStr":"2018-05-06 23:08:25","updateTime":1525619300000,"updateTimeStr":"2018-05-06 23:08:20","status":1}]
             * pageSize : 0
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : null
             */

            private int rowCount;
            private int pageSize;
            private int rowsPerPage;
            private int curPageNum;
            private int pageCount;
            private Object queryParameters;
            private List<RowsBean> rows;

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
            }

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

            public List<RowsBean> getRows() {
                return rows;
            }

            public void setRows(List<RowsBean> rows) {
                this.rows = rows;
            }
        }
    }
}
