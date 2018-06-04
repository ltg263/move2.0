package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/6/1 17:02
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class MineAssetDetailsBean extends BaseRes{

    /**
     * data : {"myTokenBill":{"rowCount":20,"rows":[{"userId":3,"tradeType":1,"functionDesc":1,"functionType":"","amount":11111,"createTime":1528036223000,"createTimeStr":"2018-06-03 22:30:23","status":1,"rewardGrantType":1,"tokenRecordsId":null,"tradeCode":null,"tradeDate":null,"tradeDateStr":null,"balance":null,"updateTime":null,"updateTimeStr":null,"memo":null,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":"点赞奖励","functionType":null,"amount":0.45,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527758715000,"createTimeStr":"2018-05-31 17:25:15","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":"点赞奖励","functionType":null,"amount":0.45,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527757671000,"createTimeStr":"2018-05-31 17:07:51","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":"邀请奖励","functionType":null,"amount":55555,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527752450000,"createTimeStr":"2018-05-31 15:40:50","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":"用户赞赏","functionType":null,"amount":0.5,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527738347000,"createTimeStr":"2018-05-31 11:45:47","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":null,"functionType":null,"amount":11111,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527570281000,"createTimeStr":"2018-05-29 13:04:41","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":2,"functionDesc":"赞赏他人","functionType":null,"amount":1,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1526353508000,"createTimeStr":"2018-05-15 11:05:08","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":2,"functionDesc":"赞赏他人","functionType":null,"amount":5.5,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1526300239000,"createTimeStr":"2018-05-14 20:17:19","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":2,"functionDesc":"赞赏他人","functionType":null,"amount":0.6,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1526299485000,"createTimeStr":"2018-05-14 20:04:45","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":2,"functionDesc":"赞赏他人","functionType":null,"amount":0.5,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1526297869000,"createTimeStr":"2018-05-14 19:37:49","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null}],"pageSize":2,"rowsPerPage":10,"curPageNum":1,"queryParameters":"userId=3"},"sum":77793.3}
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
         * myTokenBill : {"rowCount":20,"rows":[{"userId":3,"tradeType":1,"functionDesc":1,"functionType":"","amount":11111,"createTime":1528036223000,"createTimeStr":"2018-06-03 22:30:23","status":1,"rewardGrantType":1,"tokenRecordsId":null,"tradeCode":null,"tradeDate":null,"tradeDateStr":null,"balance":null,"updateTime":null,"updateTimeStr":null,"memo":null,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":"点赞奖励","functionType":null,"amount":0.45,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527758715000,"createTimeStr":"2018-05-31 17:25:15","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":"点赞奖励","functionType":null,"amount":0.45,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527757671000,"createTimeStr":"2018-05-31 17:07:51","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":"邀请奖励","functionType":null,"amount":55555,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527752450000,"createTimeStr":"2018-05-31 15:40:50","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":"用户赞赏","functionType":null,"amount":0.5,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527738347000,"createTimeStr":"2018-05-31 11:45:47","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":null,"functionType":null,"amount":11111,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527570281000,"createTimeStr":"2018-05-29 13:04:41","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":2,"functionDesc":"赞赏他人","functionType":null,"amount":1,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1526353508000,"createTimeStr":"2018-05-15 11:05:08","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":2,"functionDesc":"赞赏他人","functionType":null,"amount":5.5,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1526300239000,"createTimeStr":"2018-05-14 20:17:19","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":2,"functionDesc":"赞赏他人","functionType":null,"amount":0.6,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1526299485000,"createTimeStr":"2018-05-14 20:04:45","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":2,"functionDesc":"赞赏他人","functionType":null,"amount":0.5,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1526297869000,"createTimeStr":"2018-05-14 19:37:49","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null}],"pageSize":2,"rowsPerPage":10,"curPageNum":1,"queryParameters":"userId=3"}
         * sum : 77793.3
         */

        private MyTokenBillBean myTokenBill;
        private double sum;

        public MyTokenBillBean getMyTokenBill() {
            return myTokenBill;
        }

        public void setMyTokenBill(MyTokenBillBean myTokenBill) {
            this.myTokenBill = myTokenBill;
        }

        public double getSum() {
            return sum;
        }

        public void setSum(double sum) {
            this.sum = sum;
        }

        public static class MyTokenBillBean {
            /**
             * rowCount : 20
             * rows : [{"userId":3,"tradeType":1,"functionDesc":1,"functionType":"","amount":11111,"createTime":1528036223000,"createTimeStr":"2018-06-03 22:30:23","status":1,"rewardGrantType":1},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":"点赞奖励","functionType":null,"amount":0.45,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527758715000,"createTimeStr":"2018-05-31 17:25:15","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":"点赞奖励","functionType":null,"amount":0.45,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527757671000,"createTimeStr":"2018-05-31 17:07:51","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":"邀请奖励","functionType":null,"amount":55555,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527752450000,"createTimeStr":"2018-05-31 15:40:50","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":"用户赞赏","functionType":null,"amount":0.5,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527738347000,"createTimeStr":"2018-05-31 11:45:47","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":1,"functionDesc":null,"functionType":null,"amount":11111,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1527570281000,"createTimeStr":"2018-05-29 13:04:41","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":2,"functionDesc":"赞赏他人","functionType":null,"amount":1,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1526353508000,"createTimeStr":"2018-05-15 11:05:08","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":2,"functionDesc":"赞赏他人","functionType":null,"amount":5.5,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1526300239000,"createTimeStr":"2018-05-14 20:17:19","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":2,"functionDesc":"赞赏他人","functionType":null,"amount":0.6,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1526299485000,"createTimeStr":"2018-05-14 20:04:45","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null},{"tokenRecordsId":null,"userId":3,"tradeCode":null,"tradeType":2,"functionDesc":"赞赏他人","functionType":null,"amount":0.5,"tradeDate":null,"tradeDateStr":null,"balance":null,"createTime":1526297869000,"createTimeStr":"2018-05-14 19:37:49","updateTime":null,"updateTimeStr":null,"status":1,"memo":null,"rewardGrantType":1,"user_id":null,"function_type":null,"function_desc":null,"sum":null,"rewardToken":null}]
             * pageSize : 2
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : userId=3
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
                 * userId : 3
                 * tradeType : 1
                 * functionDesc : 1
                 * functionType :
                 * amount : 11111
                 * createTime : 1528036223000
                 * createTimeStr : 2018-06-03 22:30:23
                 * status : 1
                 * rewardGrantType : 1
                 * tokenRecordsId : null
                 * tradeCode : null
                 * tradeDate : null
                 * tradeDateStr : null
                 * balance : null
                 * updateTime : null
                 * updateTimeStr : null
                 * memo : null
                 * user_id : null
                 * function_type : null
                 * function_desc : null
                 * sum : null
                 * rewardToken : null
                 */

                private int userId;
                private int tradeType;
                private String functionDesc;
                private int functionType;
                private double amount;
                private long createTime;
                private String createTimeStr;
                private int status;
                private int rewardGrantType;

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public int getTradeType() {
                    return tradeType;
                }

                public void setTradeType(int tradeType) {
                    this.tradeType = tradeType;
                }

                public String getFunctionDesc() {
                    return functionDesc;
                }

                public void setFunctionDesc(String functionDesc) {
                    this.functionDesc = functionDesc;
                }

                public int getFunctionType() {
                    return functionType;
                }

                public void setFunctionType(int functionType) {
                    this.functionType = functionType;
                }

                public double getAmount() {
                    return amount;
                }

                public void setAmount(double amount) {
                    this.amount = amount;
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

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getRewardGrantType() {
                    return rewardGrantType;
                }

                public void setRewardGrantType(int rewardGrantType) {
                    this.rewardGrantType = rewardGrantType;
                }
            }
        }
    }
}
