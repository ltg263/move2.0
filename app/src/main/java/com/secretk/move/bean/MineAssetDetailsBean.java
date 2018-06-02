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
     * data : {"myTokenBill":{"rowCount":9,"rows":[{"tokenAwardId":29,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527758715000,"createTimeStr":"2018-05-31 17:25:15","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":28,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527757704000,"createTimeStr":"2018-05-31 17:08:24","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":27,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527757109000,"createTimeStr":"2018-05-31 16:58:29","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":26,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527755746000,"createTimeStr":"2018-05-31 16:35:46","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":25,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527754938000,"createTimeStr":"2018-05-31 16:22:18","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":24,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":18,"inviteRewards":500,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":0,"containerAward":0,"createTime":1527752450000,"createTimeStr":"2018-05-31 15:40:50","giveNext":null,"updateTime":1527752452000,"updateTimeStr":"2018-05-31 15:40:52","rewardToken":0,"inviteNumber":0,"distributionType":null,"counter":0,"grantType":0,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":23,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":null,"tokenAwardFunctionType":17,"inviteRewards":0,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":0,"containerAward":0,"createTime":null,"createTimeStr":null,"giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":0,"distributionType":null,"counter":0,"grantType":0,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":22,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":17,"inviteRewards":500,"giveTime":null,"giveTimeStr":null,"priaiseAward":null,"awardBalance":null,"containerAward":null,"createTime":1527739335000,"createTimeStr":"2018-05-31 12:02:15","giveNext":null,"updateTime":1527739337000,"updateTimeStr":"2018-05-31 12:02:17","rewardToken":null,"inviteNumber":null,"distributionType":null,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":21,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":18,"inviteRewards":500,"giveTime":null,"giveTimeStr":null,"priaiseAward":null,"awardBalance":null,"containerAward":null,"createTime":1527739273000,"createTimeStr":"2018-05-31 12:01:13","giveNext":null,"updateTime":1527739276000,"updateTimeStr":"2018-05-31 12:01:16","rewardToken":null,"inviteNumber":null,"distributionType":null,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"userId=3"}}
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
         * myTokenBill : {"rowCount":9,"rows":[{"tokenAwardId":29,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527758715000,"createTimeStr":"2018-05-31 17:25:15","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":28,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527757704000,"createTimeStr":"2018-05-31 17:08:24","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":27,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527757109000,"createTimeStr":"2018-05-31 16:58:29","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":26,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527755746000,"createTimeStr":"2018-05-31 16:35:46","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":25,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527754938000,"createTimeStr":"2018-05-31 16:22:18","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":24,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":18,"inviteRewards":500,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":0,"containerAward":0,"createTime":1527752450000,"createTimeStr":"2018-05-31 15:40:50","giveNext":null,"updateTime":1527752452000,"updateTimeStr":"2018-05-31 15:40:52","rewardToken":0,"inviteNumber":0,"distributionType":null,"counter":0,"grantType":0,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":23,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":null,"tokenAwardFunctionType":17,"inviteRewards":0,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":0,"containerAward":0,"createTime":null,"createTimeStr":null,"giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":0,"distributionType":null,"counter":0,"grantType":0,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":22,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":17,"inviteRewards":500,"giveTime":null,"giveTimeStr":null,"priaiseAward":null,"awardBalance":null,"containerAward":null,"createTime":1527739335000,"createTimeStr":"2018-05-31 12:02:15","giveNext":null,"updateTime":1527739337000,"updateTimeStr":"2018-05-31 12:02:17","rewardToken":null,"inviteNumber":null,"distributionType":null,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":21,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":18,"inviteRewards":500,"giveTime":null,"giveTimeStr":null,"priaiseAward":null,"awardBalance":null,"containerAward":null,"createTime":1527739273000,"createTimeStr":"2018-05-31 12:01:13","giveNext":null,"updateTime":1527739276000,"updateTimeStr":"2018-05-31 12:01:16","rewardToken":null,"inviteNumber":null,"distributionType":null,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"userId=3"}
         */

        private MyTokenBillBean myTokenBill;

        public MyTokenBillBean getMyTokenBill() {
            return myTokenBill;
        }

        public void setMyTokenBill(MyTokenBillBean myTokenBill) {
            this.myTokenBill = myTokenBill;
        }

        public static class MyTokenBillBean {
            /**
             * rowCount : 9
             * rows : [{"tokenAwardId":29,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527758715000,"createTimeStr":"2018-05-31 17:25:15","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":28,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527757704000,"createTimeStr":"2018-05-31 17:08:24","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":27,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527757109000,"createTimeStr":"2018-05-31 16:58:29","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":26,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527755746000,"createTimeStr":"2018-05-31 16:35:46","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":25,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"点赞奖励","tokenAwardFunctionType":17,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":null,"containerAward":null,"createTime":1527754938000,"createTimeStr":"2018-05-31 16:22:18","giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":null,"distributionType":2,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":24,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":18,"inviteRewards":500,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":0,"containerAward":0,"createTime":1527752450000,"createTimeStr":"2018-05-31 15:40:50","giveNext":null,"updateTime":1527752452000,"updateTimeStr":"2018-05-31 15:40:52","rewardToken":0,"inviteNumber":0,"distributionType":null,"counter":0,"grantType":0,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":23,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":null,"tokenAwardFunctionType":17,"inviteRewards":0,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":0,"containerAward":0,"createTime":null,"createTimeStr":null,"giveNext":null,"updateTime":null,"updateTimeStr":null,"rewardToken":0,"inviteNumber":0,"distributionType":null,"counter":0,"grantType":0,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":22,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":17,"inviteRewards":500,"giveTime":null,"giveTimeStr":null,"priaiseAward":null,"awardBalance":null,"containerAward":null,"createTime":1527739335000,"createTimeStr":"2018-05-31 12:02:15","giveNext":null,"updateTime":1527739337000,"updateTimeStr":"2018-05-31 12:02:17","rewardToken":null,"inviteNumber":null,"distributionType":null,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":21,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":18,"inviteRewards":500,"giveTime":null,"giveTimeStr":null,"priaiseAward":null,"awardBalance":null,"containerAward":null,"createTime":1527739273000,"createTimeStr":"2018-05-31 12:01:13","giveNext":null,"updateTime":1527739276000,"updateTimeStr":"2018-05-31 12:01:16","rewardToken":null,"inviteNumber":null,"distributionType":null,"counter":0,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null}]
             * pageSize : 1
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
                 * tokenAwardId : 29
                 * userId : 3
                 * tokenRecordsId : null
                 * praiseId : null
                 * tokenAwardFunctionDesc : 点赞奖励
                 * tokenAwardFunctionType : 17
                 * inviteRewards : null
                 * giveTime : null
                 * giveTimeStr : null
                 * priaiseAward : 0
                 * awardBalance : null
                 * containerAward : null
                 * createTime : 1527758715000
                 * createTimeStr : 2018-05-31 17:25:15
                 * giveNext : null
                 * updateTime : null
                 * updateTimeStr : null
                 * rewardToken : 0
                 * inviteNumber : null
                 * distributionType : 2
                 * counter : 0
                 * grantType : null
                 * userName : null
                 * mobile : null
                 * issuer : null
                 * remark : null
                 */

                private int tokenAwardId;
                private int userId;
                private Object tokenRecordsId;
                private Object praiseId;
                private String tokenAwardFunctionDesc;
                private int tokenAwardFunctionType;
                private double inviteRewards;
                private Object giveTime;
                private Object giveTimeStr;
                private int priaiseAward;
                private Object awardBalance;
                private Object containerAward;
                private long createTime;
                private String createTimeStr;
                private Object giveNext;
                private Object updateTime;
                private Object updateTimeStr;
                private int rewardToken;
                private Object inviteNumber;
                private int distributionType;
                private int counter;
                private Object grantType;
                private Object userName;
                private Object mobile;
                private Object issuer;
                private Object remark;

                public int getTokenAwardId() {
                    return tokenAwardId;
                }

                public void setTokenAwardId(int tokenAwardId) {
                    this.tokenAwardId = tokenAwardId;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public Object getTokenRecordsId() {
                    return tokenRecordsId;
                }

                public void setTokenRecordsId(Object tokenRecordsId) {
                    this.tokenRecordsId = tokenRecordsId;
                }

                public Object getPraiseId() {
                    return praiseId;
                }

                public void setPraiseId(Object praiseId) {
                    this.praiseId = praiseId;
                }

                public String getTokenAwardFunctionDesc() {
                    return tokenAwardFunctionDesc;
                }

                public void setTokenAwardFunctionDesc(String tokenAwardFunctionDesc) {
                    this.tokenAwardFunctionDesc = tokenAwardFunctionDesc;
                }

                public int getTokenAwardFunctionType() {
                    return tokenAwardFunctionType;
                }

                public void setTokenAwardFunctionType(int tokenAwardFunctionType) {
                    this.tokenAwardFunctionType = tokenAwardFunctionType;
                }

                public double getInviteRewards() {
                    return inviteRewards;
                }

                public void setInviteRewards(double inviteRewards) {
                    this.inviteRewards = inviteRewards;
                }

                public Object getGiveTime() {
                    return giveTime;
                }

                public void setGiveTime(Object giveTime) {
                    this.giveTime = giveTime;
                }

                public Object getGiveTimeStr() {
                    return giveTimeStr;
                }

                public void setGiveTimeStr(Object giveTimeStr) {
                    this.giveTimeStr = giveTimeStr;
                }

                public int getPriaiseAward() {
                    return priaiseAward;
                }

                public void setPriaiseAward(int priaiseAward) {
                    this.priaiseAward = priaiseAward;
                }

                public Object getAwardBalance() {
                    return awardBalance;
                }

                public void setAwardBalance(Object awardBalance) {
                    this.awardBalance = awardBalance;
                }

                public Object getContainerAward() {
                    return containerAward;
                }

                public void setContainerAward(Object containerAward) {
                    this.containerAward = containerAward;
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

                public Object getGiveNext() {
                    return giveNext;
                }

                public void setGiveNext(Object giveNext) {
                    this.giveNext = giveNext;
                }

                public Object getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(Object updateTime) {
                    this.updateTime = updateTime;
                }

                public Object getUpdateTimeStr() {
                    return updateTimeStr;
                }

                public void setUpdateTimeStr(Object updateTimeStr) {
                    this.updateTimeStr = updateTimeStr;
                }

                public int getRewardToken() {
                    return rewardToken;
                }

                public void setRewardToken(int rewardToken) {
                    this.rewardToken = rewardToken;
                }

                public Object getInviteNumber() {
                    return inviteNumber;
                }

                public void setInviteNumber(Object inviteNumber) {
                    this.inviteNumber = inviteNumber;
                }

                public int getDistributionType() {
                    return distributionType;
                }

                public void setDistributionType(int distributionType) {
                    this.distributionType = distributionType;
                }

                public int getCounter() {
                    return counter;
                }

                public void setCounter(int counter) {
                    this.counter = counter;
                }

                public Object getGrantType() {
                    return grantType;
                }

                public void setGrantType(Object grantType) {
                    this.grantType = grantType;
                }

                public Object getUserName() {
                    return userName;
                }

                public void setUserName(Object userName) {
                    this.userName = userName;
                }

                public Object getMobile() {
                    return mobile;
                }

                public void setMobile(Object mobile) {
                    this.mobile = mobile;
                }

                public Object getIssuer() {
                    return issuer;
                }

                public void setIssuer(Object issuer) {
                    this.issuer = issuer;
                }

                public Object getRemark() {
                    return remark;
                }

                public void setRemark(Object remark) {
                    this.remark = remark;
                }
            }
        }
    }
}
