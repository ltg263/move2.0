package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/5/17 13:57
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class MyCollectList extends BaseRes{

    /**
     * data : {"myTokenRecords":{"rowCount":2,"rows":[{"collectId":2,"collectUserId":3,"projectId":1,"postId":3,"postType":1,"status":1,"createTime":1526284562000,"createTimeStr":"2018-05-14 15:56:02","updateTime":1526284562000,"updateTimeStr":"2018-05-14 15:56:02","postTitle":"比特币的评测","postShortDesc":"比特币的评测描述","postSmallImages":[{"fileUrl":"/upload/posts/201805/1.jpg","fileName":"进度讨论","extension":"jpg"},{"fileUrl":"/upload/posts/201805/2.jpg","fileName":"进度讨论","extension":"jpg"},{"fileUrl":"/upload/posts/201805/3.jpg","fileName":"进度讨论","extension":"jpg"}],"commentsNum":0,"praiseNum":1,"pageviewNum":0,"donateNum":20,"collectNum":1,"createUserIcon":"/upload/avatars/avatar.png","createUserSignature":"凄凄切切","createUserName":"试试","totalScore":0,"projectIcon":"/upload/projects/1.jpg","projectCode":"BTC","projectEnglishName":"Bit Coin X","projectChineseName":"比特币中文","projectSignature":"比特币的简短描述中文中文中文"}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"collectUserId=3"}}
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
         * myTokenRecords : {"rowCount":2,"rows":[{"collectId":2,"collectUserId":3,"projectId":1,"postId":3,"postType":1,"status":1,"createTime":1526284562000,"createTimeStr":"2018-05-14 15:56:02","updateTime":1526284562000,"updateTimeStr":"2018-05-14 15:56:02","postTitle":"比特币的评测","postShortDesc":"比特币的评测描述","postSmallImages":[{"fileUrl":"/upload/posts/201805/1.jpg","fileName":"进度讨论","extension":"jpg"},{"fileUrl":"/upload/posts/201805/2.jpg","fileName":"进度讨论","extension":"jpg"},{"fileUrl":"/upload/posts/201805/3.jpg","fileName":"进度讨论","extension":"jpg"}],"commentsNum":0,"praiseNum":1,"pageviewNum":0,"donateNum":20,"collectNum":1,"createUserIcon":"/upload/avatars/avatar.png","createUserSignature":"凄凄切切","createUserName":"试试","totalScore":0,"projectIcon":"/upload/projects/1.jpg","projectCode":"BTC","projectEnglishName":"Bit Coin X","projectChineseName":"比特币中文","projectSignature":"比特币的简短描述中文中文中文"}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"collectUserId=3"}
         */

        private MyTokenRecordsBean myTokenRecords;

        public MyTokenRecordsBean getMyTokenRecords() {
            return myTokenRecords;
        }

        public void setMyTokenRecords(MyTokenRecordsBean myTokenRecords) {
            this.myTokenRecords = myTokenRecords;
        }

        public static class MyTokenRecordsBean {
            /**
             * rowCount : 2
             * rows : [{"collectId":2,"collectUserId":3,"projectId":1,"postId":3,"postType":1,"status":1,"createTime":1526284562000,"createTimeStr":"2018-05-14 15:56:02","updateTime":1526284562000,"updateTimeStr":"2018-05-14 15:56:02","postTitle":"比特币的评测","postShortDesc":"比特币的评测描述","postSmallImages":[{"fileUrl":"/upload/posts/201805/1.jpg","fileName":"进度讨论","extension":"jpg"},{"fileUrl":"/upload/posts/201805/2.jpg","fileName":"进度讨论","extension":"jpg"},{"fileUrl":"/upload/posts/201805/3.jpg","fileName":"进度讨论","extension":"jpg"}],"commentsNum":0,"praiseNum":1,"pageviewNum":0,"donateNum":20,"collectNum":1,"createUserIcon":"/upload/avatars/avatar.png","createUserSignature":"凄凄切切","createUserName":"试试","totalScore":0,"projectIcon":"/upload/projects/1.jpg","projectCode":"BTC","projectEnglishName":"Bit Coin X","projectChineseName":"比特币中文","projectSignature":"比特币的简短描述中文中文中文"}]
             * pageSize : 1
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : collectUserId=3
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
        }
    }
}
