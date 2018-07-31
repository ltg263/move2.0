package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/7/31 13:55
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class FindWksyList extends BaseRes{

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"data":{"rowCount":2,"rows":[{"id":2,"createdAt":1532929524000,"updatedAt":1532929526000,"activityId":6,"userId":1,"userName":"UL7b6mA0W","mobile":"13112311231","title":"ETH莱特币","reward":100,"tokenName":"ETH","status":0,"cash":166.67,"projectId":3,"projectCode":"ETH","projectIcon":"https://pic.qufen.top/projects20180628194833517.png"},{"id":1,"createdAt":1532929388000,"updatedAt":1532929390000,"activityId":1,"userId":1,"userName":"UL7b6mA0W","mobile":"13112311231","title":"测试","reward":1000,"tokenName":"FIND","status":1,"cash":500,"projectId":13,"projectCode":"BCN","projectIcon":"https://pic.qufen.top/projects20180628201243555.png"}],"pageSize":1,"rowsPerPage":15,"curPageNum":1,"queryParameters":"userId=1","nextPage":1,"pageCount":1,"hasNext":false}}
     */

    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * data : {"rowCount":2,"rows":[{"id":2,"createdAt":1532929524000,"updatedAt":1532929526000,"activityId":6,"userId":1,"userName":"UL7b6mA0W","mobile":"13112311231","title":"ETH莱特币","reward":100,"tokenName":"ETH","status":0,"cash":166.67,"projectId":3,"projectCode":"ETH","projectIcon":"https://pic.qufen.top/projects20180628194833517.png"},{"id":1,"createdAt":1532929388000,"updatedAt":1532929390000,"activityId":1,"userId":1,"userName":"UL7b6mA0W","mobile":"13112311231","title":"测试","reward":1000,"tokenName":"FIND","status":1,"cash":500,"projectId":13,"projectCode":"BCN","projectIcon":"https://pic.qufen.top/projects20180628201243555.png"}],"pageSize":1,"rowsPerPage":15,"curPageNum":1,"queryParameters":"userId=1","nextPage":1,"pageCount":1,"hasNext":false}
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
             * rowCount : 2
             * rows : [{"id":2,"createdAt":1532929524000,"updatedAt":1532929526000,"activityId":6,"userId":1,"userName":"UL7b6mA0W","mobile":"13112311231","title":"ETH莱特币","reward":100,"tokenName":"ETH","status":0,"cash":166.67,"projectId":3,"projectCode":"ETH","projectIcon":"https://pic.qufen.top/projects20180628194833517.png"},{"id":1,"createdAt":1532929388000,"updatedAt":1532929390000,"activityId":1,"userId":1,"userName":"UL7b6mA0W","mobile":"13112311231","title":"测试","reward":1000,"tokenName":"FIND","status":1,"cash":500,"projectId":13,"projectCode":"BCN","projectIcon":"https://pic.qufen.top/projects20180628201243555.png"}]
             * pageSize : 1
             * rowsPerPage : 15
             * curPageNum : 1
             * queryParameters : userId=1
             * nextPage : 1
             * pageCount : 1
             * hasNext : false
             */

            private int rowCount;
            private int pageSize;
            private int rowsPerPage;
            private int curPageNum;
            private String queryParameters;
            private int nextPage;
            private int pageCount;
            private boolean hasNext;
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

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
            }

            public boolean isHasNext() {
                return hasNext;
            }

            public void setHasNext(boolean hasNext) {
                this.hasNext = hasNext;
            }

            public List<RowsBean> getRows() {
                return rows;
            }

            public void setRows(List<RowsBean> rows) {
                this.rows = rows;
            }

            public static class RowsBean {
                /**
                 * id : 2
                 * createdAt : 1532929524000
                 * updatedAt : 1532929526000
                 * activityId : 6
                 * userId : 1
                 * userName : UL7b6mA0W
                 * mobile : 13112311231
                 * title : ETH莱特币
                 * reward : 100
                 * tokenName : ETH
                 * status : 0
                 * cash : 166.67
                 * projectId : 3
                 * projectCode : ETH
                 * projectIcon : https://pic.qufen.top/projects20180628194833517.png
                 */

                private int id;
                private long createdAt;
                private long updatedAt;
                private int activityId;
                private int userId;
                private String userName;
                private String mobile;
                private String title;
                private int reward;
                private String tokenName;
                private int status;
                private double cash;
                private int projectId;
                private String projectCode;
                private String projectChineseName;
                private String projectIcon;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public long getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(long createdAt) {
                    this.createdAt = createdAt;
                }

                public long getUpdatedAt() {
                    return updatedAt;
                }

                public void setUpdatedAt(long updatedAt) {
                    this.updatedAt = updatedAt;
                }

                public int getActivityId() {
                    return activityId;
                }

                public void setActivityId(int activityId) {
                    this.activityId = activityId;
                }

                public void setProjectChineseName(String projectChineseName) {
                    this.projectChineseName = projectChineseName;
                }

                public String getProjectChineseName() {
                    return projectChineseName;
                }


                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getReward() {
                    return reward;
                }

                public void setReward(int reward) {
                    this.reward = reward;
                }

                public String getTokenName() {
                    return tokenName;
                }

                public void setTokenName(String tokenName) {
                    this.tokenName = tokenName;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public double getCash() {
                    return cash;
                }

                public void setCash(double cash) {
                    this.cash = cash;
                }

                public int getProjectId() {
                    return projectId;
                }

                public void setProjectId(int projectId) {
                    this.projectId = projectId;
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
            }
        }
    }
}
