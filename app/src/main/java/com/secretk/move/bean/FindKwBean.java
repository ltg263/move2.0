package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/7/28 16:03
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class FindKwBean extends BaseRes{

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"data":{"rowCount":2,"rows":[{"id":6,"createdAt":1532674814000,"createDt":null,"updatedAt":1532674814000,"title":"ETH莱特币","projectId":3,"projectCode":"ETH","tokenName":"ETH","tokenCount":3000,"tokenEveryCount":100,"tokenCash":5000,"tokenNum":30,"tokenSurplusNum":30,"activityRemark":"纤维胶带确定强无敌看情况的卡萨丁卡打第三款拉到阿森大厦去多块钱的玩强无敌挖墙脚纤维胶带我确定钱到位我确定大枪懂我的请问","beginDt":"2018-07-28 14:59:42","endDt":"2018-08-01 14:59:45","activityStep":"0,2,","type":2,"articleId":49,"status":0,"statusMsg":null,"tokenUnclaimed":3000},{"id":2,"createdAt":1532673183000,"createDt":null,"updatedAt":1532680386000,"title":"测试件大事","projectId":1,"projectCode":"BTC","tokenName":"FIND","tokenCount":3000,"tokenEveryCount":200,"tokenCash":1500,"tokenNum":15,"tokenSurplusNum":15,"activityRemark":"NJKksakda d年斯柯达大撒旦，你很好，你真是个大傻吊！","beginDt":"2018-07-27 17:32:17","endDt":"2018-07-30 14:32:36","activityStep":"0,1,","type":null,"articleId":null,"status":0,"statusMsg":null,"tokenUnclaimed":3000}],"pageSize":1,"rowsPerPage":15,"curPageNum":1,"queryParameters":"status1=0&status2=1&status3=4","hasNext":false,"pageCount":1,"nextPage":1}}
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
         * data : {"rowCount":2,"rows":[{"id":6,"createdAt":1532674814000,"createDt":null,"updatedAt":1532674814000,"title":"ETH莱特币","projectId":3,"projectCode":"ETH","tokenName":"ETH","tokenCount":3000,"tokenEveryCount":100,"tokenCash":5000,"tokenNum":30,"tokenSurplusNum":30,"activityRemark":"纤维胶带确定强无敌看情况的卡萨丁卡打第三款拉到阿森大厦去多块钱的玩强无敌挖墙脚纤维胶带我确定钱到位我确定大枪懂我的请问","beginDt":"2018-07-28 14:59:42","endDt":"2018-08-01 14:59:45","activityStep":"0,2,","type":2,"articleId":49,"status":0,"statusMsg":null,"tokenUnclaimed":3000},{"id":2,"createdAt":1532673183000,"createDt":null,"updatedAt":1532680386000,"title":"测试件大事","projectId":1,"projectCode":"BTC","tokenName":"FIND","tokenCount":3000,"tokenEveryCount":200,"tokenCash":1500,"tokenNum":15,"tokenSurplusNum":15,"activityRemark":"NJKksakda d年斯柯达大撒旦，你很好，你真是个大傻吊！","beginDt":"2018-07-27 17:32:17","endDt":"2018-07-30 14:32:36","activityStep":"0,1,","type":null,"articleId":null,"status":0,"statusMsg":null,"tokenUnclaimed":3000}],"pageSize":1,"rowsPerPage":15,"curPageNum":1,"queryParameters":"status1=0&status2=1&status3=4","hasNext":false,"pageCount":1,"nextPage":1}
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
             * rows : [{"id":6,"createdAt":1532674814000,"createDt":null,"updatedAt":1532674814000,"title":"ETH莱特币","projectId":3,"projectCode":"ETH","tokenName":"ETH","tokenCount":3000,"tokenEveryCount":100,"tokenCash":5000,"tokenNum":30,"tokenSurplusNum":30,"activityRemark":"纤维胶带确定强无敌看情况的卡萨丁卡打第三款拉到阿森大厦去多块钱的玩强无敌挖墙脚纤维胶带我确定钱到位我确定大枪懂我的请问","beginDt":"2018-07-28 14:59:42","endDt":"2018-08-01 14:59:45","activityStep":"0,2,","type":2,"articleId":49,"status":0,"statusMsg":null,"tokenUnclaimed":3000},{"id":2,"createdAt":1532673183000,"createDt":null,"updatedAt":1532680386000,"title":"测试件大事","projectId":1,"projectCode":"BTC","tokenName":"FIND","tokenCount":3000,"tokenEveryCount":200,"tokenCash":1500,"tokenNum":15,"tokenSurplusNum":15,"activityRemark":"NJKksakda d年斯柯达大撒旦，你很好，你真是个大傻吊！","beginDt":"2018-07-27 17:32:17","endDt":"2018-07-30 14:32:36","activityStep":"0,1,","type":null,"articleId":null,"status":0,"statusMsg":null,"tokenUnclaimed":3000}]
             * pageSize : 1
             * rowsPerPage : 15
             * curPageNum : 1
             * queryParameters : status1=0&status2=1&status3=4
             * hasNext : false
             * pageCount : 1
             * nextPage : 1
             */

            private int rowCount;
            private int pageSize;
            private int rowsPerPage;
            private int curPageNum;
            private String queryParameters;
            private boolean hasNext;
            private int pageCount;
            private int nextPage;
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

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public List<RowsBean> getRows() {
                return rows;
            }

            public void setRows(List<RowsBean> rows) {
                this.rows = rows;
            }

            public static class RowsBean {
                /**
                 * id : 6
                 * createdAt : 1532674814000
                 * createDt : null
                 * updatedAt : 1532674814000
                 * title : ETH莱特币
                 * projectId : 3
                 * projectCode : ETH
                 * tokenName : ETH
                 * tokenCount : 3000
                 * tokenEveryCount : 100
                 * tokenCash : 5000
                 * tokenNum : 30
                 * tokenSurplusNum : 30
                 * activityRemark : 纤维胶带确定强无敌看情况的卡萨丁卡打第三款拉到阿森大厦去多块钱的玩强无敌挖墙脚纤维胶带我确定钱到位我确定大枪懂我的请问
                 * beginDt : 2018-07-28 14:59:42
                 * endDt : 2018-08-01 14:59:45
                 * activityStep : 0,2,
                 * type : 2
                 * articleId : 49
                 * status : 0
                 * statusMsg : null
                 * tokenUnclaimed : 3000
                 */

                private int id;
                private long createdAt;
                private Object createDt;
                private long updatedAt;
                private String title;
                private String projectIcon;
                private String projectChineseName;
                private int projectId;
                private String projectCode;
                private String tokenName;
                private int tokenCount;
                private double tokenEveryCount;
                private int tokenCash;
                private int tokenNum;
                private int tokenSurplusNum;
                private String activityRemark;
                private String beginDt;
                private String endDt;
                private String activityStep;
                private int type;
                private int articleId;
                private int status;
                private Object statusMsg;
                private int tokenUnclaimed;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public void setProjectChineseName(String projectChineseName) {
                    this.projectChineseName = projectChineseName;
                }

                public String getProjectChineseName() {
                    return projectChineseName;
                }

                public void setProjectIcon(String projectIcon) {
                    this.projectIcon = projectIcon;
                }

                public String getProjectIcon() {
                    return projectIcon;
                }

                public long getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(long createdAt) {
                    this.createdAt = createdAt;
                }

                public Object getCreateDt() {
                    return createDt;
                }

                public void setCreateDt(Object createDt) {
                    this.createDt = createDt;
                }

                public long getUpdatedAt() {
                    return updatedAt;
                }

                public void setUpdatedAt(long updatedAt) {
                    this.updatedAt = updatedAt;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
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

                public String getTokenName() {
                    return tokenName;
                }

                public void setTokenName(String tokenName) {
                    this.tokenName = tokenName;
                }

                public int getTokenCount() {
                    return tokenCount;
                }

                public void setTokenCount(int tokenCount) {
                    this.tokenCount = tokenCount;
                }

                public double getTokenEveryCount() {
                    return tokenEveryCount;
                }

                public void setTokenEveryCount(double tokenEveryCount) {
                    this.tokenEveryCount = tokenEveryCount;
                }

                public int getTokenCash() {
                    return tokenCash;
                }

                public void setTokenCash(int tokenCash) {
                    this.tokenCash = tokenCash;
                }

                public int getTokenNum() {
                    return tokenNum;
                }

                public void setTokenNum(int tokenNum) {
                    this.tokenNum = tokenNum;
                }

                public int getTokenSurplusNum() {
                    return tokenSurplusNum;
                }

                public void setTokenSurplusNum(int tokenSurplusNum) {
                    this.tokenSurplusNum = tokenSurplusNum;
                }

                public String getActivityRemark() {
                    return activityRemark;
                }

                public void setActivityRemark(String activityRemark) {
                    this.activityRemark = activityRemark;
                }

                public String getBeginDt() {
                    return beginDt;
                }

                public void setBeginDt(String beginDt) {
                    this.beginDt = beginDt;
                }

                public String getEndDt() {
                    return endDt;
                }

                public void setEndDt(String endDt) {
                    this.endDt = endDt;
                }

                public String getActivityStep() {
                    return activityStep;
                }

                public void setActivityStep(String activityStep) {
                    this.activityStep = activityStep;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getArticleId() {
                    return articleId;
                }

                public void setArticleId(int articleId) {
                    this.articleId = articleId;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public Object getStatusMsg() {
                    return statusMsg;
                }

                public void setStatusMsg(Object statusMsg) {
                    this.statusMsg = statusMsg;
                }

                public int getTokenUnclaimed() {
                    return tokenUnclaimed;
                }

                public void setTokenUnclaimed(int tokenUnclaimed) {
                    this.tokenUnclaimed = tokenUnclaimed;
                }
            }
        }
    }
}
