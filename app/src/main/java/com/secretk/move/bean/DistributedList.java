package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/6/2 11:43
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class DistributedList extends BaseRes{

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"inDistribution":[{"tokenAwardId":21,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":18,"inviteRewards":33333,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":500,"containerAward":0,"createTime":1527739273000,"createTimeStr":"2018-05-31 12:01:13","giveNext":500,"updateTime":1527739276000,"updateTimeStr":"2018-05-31 12:01:16","rewardToken":0,"inviteNumber":2,"distributionType":0,"counter":0,"grantType":1,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":24,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":18,"inviteRewards":55555,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":200,"containerAward":0,"createTime":1527752450000,"createTimeStr":"2018-05-31 15:40:50","giveNext":700,"updateTime":1527752452000,"updateTimeStr":"2018-05-31 15:40:52","rewardToken":0,"inviteNumber":0,"distributionType":11,"counter":0,"grantType":1,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":null,"userId":null,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":null,"tokenAwardFunctionType":null,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":null,"awardBalance":null,"containerAward":null,"createTime":null,"createTimeStr":null,"giveNext":700,"updateTime":null,"updateTimeStr":null,"rewardToken":88188,"inviteNumber":null,"distributionType":null,"counter":null,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null}],"sum":{"tokenAwardId":null,"userId":null,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":null,"tokenAwardFunctionType":null,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":null,"awardBalance":null,"containerAward":null,"createTime":null,"createTimeStr":null,"giveNext":700,"updateTime":null,"updateTimeStr":null,"rewardToken":88188,"inviteNumber":null,"distributionType":null,"counter":null,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null}}
     */

    private Object fromuri;
    private DataBean data;

    public Object getFromuri() {
        return fromuri;
    }

    public void setFromuri(Object fromuri) {
        this.fromuri = fromuri;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * inDistribution : [{"tokenAwardId":21,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":18,"inviteRewards":33333,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":500,"containerAward":0,"createTime":1527739273000,"createTimeStr":"2018-05-31 12:01:13","giveNext":500,"updateTime":1527739276000,"updateTimeStr":"2018-05-31 12:01:16","rewardToken":0,"inviteNumber":2,"distributionType":0,"counter":0,"grantType":1,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":24,"userId":3,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":"邀请奖励","tokenAwardFunctionType":18,"inviteRewards":55555,"giveTime":null,"giveTimeStr":null,"priaiseAward":0,"awardBalance":200,"containerAward":0,"createTime":1527752450000,"createTimeStr":"2018-05-31 15:40:50","giveNext":700,"updateTime":1527752452000,"updateTimeStr":"2018-05-31 15:40:52","rewardToken":0,"inviteNumber":0,"distributionType":11,"counter":0,"grantType":1,"userName":null,"mobile":null,"issuer":null,"remark":null},{"tokenAwardId":null,"userId":null,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":null,"tokenAwardFunctionType":null,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":null,"awardBalance":null,"containerAward":null,"createTime":null,"createTimeStr":null,"giveNext":700,"updateTime":null,"updateTimeStr":null,"rewardToken":88188,"inviteNumber":null,"distributionType":null,"counter":null,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null}]
         * sum : {"tokenAwardId":null,"userId":null,"tokenRecordsId":null,"praiseId":null,"tokenAwardFunctionDesc":null,"tokenAwardFunctionType":null,"inviteRewards":null,"giveTime":null,"giveTimeStr":null,"priaiseAward":null,"awardBalance":null,"containerAward":null,"createTime":null,"createTimeStr":null,"giveNext":700,"updateTime":null,"updateTimeStr":null,"rewardToken":88188,"inviteNumber":null,"distributionType":null,"counter":null,"grantType":null,"userName":null,"mobile":null,"issuer":null,"remark":null}
         */

        private SumBean sum;
        private List<InDistributionBean> inDistribution;

        public SumBean getSum() {
            return sum;
        }

        public void setSum(SumBean sum) {
            this.sum = sum;
        }

        public List<InDistributionBean> getInDistribution() {
            return inDistribution;
        }

        public void setInDistribution(List<InDistributionBean> inDistribution) {
            this.inDistribution = inDistribution;
        }

        public static class SumBean {
            /**
             * tokenAwardId : null
             * userId : null
             * tokenRecordsId : null
             * praiseId : null
             * tokenAwardFunctionDesc : null
             * tokenAwardFunctionType : null
             * inviteRewards : null
             * giveTime : null
             * giveTimeStr : null
             * priaiseAward : null
             * awardBalance : null
             * containerAward : null
             * createTime : null
             * createTimeStr : null
             * giveNext : 700
             * updateTime : null
             * updateTimeStr : null
             * rewardToken : 88188
             * inviteNumber : null
             * distributionType : null
             * counter : null
             * grantType : null
             * userName : null
             * mobile : null
             * issuer : null
             * remark : null
             */

            private int giveNext;
            private int rewardToken;

            public int getGiveNext() {
                return giveNext;
            }

            public void setGiveNext(int giveNext) {
                this.giveNext = giveNext;
            }

            public int getRewardToken() {
                return rewardToken;
            }

            public void setRewardToken(int rewardToken) {
                this.rewardToken = rewardToken;
            }
        }

        public static class InDistributionBean {
            /**
             * tokenAwardId : 21
             * userId : 3
             * tokenRecordsId : null
             * praiseId : null
             * tokenAwardFunctionDesc : 邀请奖励
             * tokenAwardFunctionType : 18
             * inviteRewards : 33333
             * giveTime : null
             * giveTimeStr : null
             * priaiseAward : 0
             * awardBalance : 500
             * containerAward : 0
             * createTime : 1527739273000
             * createTimeStr : 2018-05-31 12:01:13
             * giveNext : 500
             * updateTime : 1527739276000
             * updateTimeStr : 2018-05-31 12:01:16
             * rewardToken : 0
             * inviteNumber : 2
             * distributionType : 0
             * counter : 0
             * grantType : 1
             * userName : null
             * mobile : null
             * issuer : null
             * remark : null
             */

            private int tokenAwardId;
            private int userId;
            private String tokenAwardFunctionDesc;
            private int tokenAwardFunctionType;
            private int inviteRewards;
            private int priaiseAward;
            private int awardBalance;
            private long createTime;
            private String createTimeStr;
            private int giveNext;
            private long updateTime;
            private String updateTimeStr;
            private int rewardToken;
            private int inviteNumber;
            private int distributionType;
            private int counter;
            private int grantType;

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

            public int getInviteRewards() {
                return inviteRewards;
            }

            public void setInviteRewards(int inviteRewards) {
                this.inviteRewards = inviteRewards;
            }

            public int getPriaiseAward() {
                return priaiseAward;
            }

            public void setPriaiseAward(int priaiseAward) {
                this.priaiseAward = priaiseAward;
            }

            public int getAwardBalance() {
                return awardBalance;
            }

            public void setAwardBalance(int awardBalance) {
                this.awardBalance = awardBalance;
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

            public int getGiveNext() {
                return giveNext;
            }

            public void setGiveNext(int giveNext) {
                this.giveNext = giveNext;
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

            public int getRewardToken() {
                return rewardToken;
            }

            public void setRewardToken(int rewardToken) {
                this.rewardToken = rewardToken;
            }

            public int getInviteNumber() {
                return inviteNumber;
            }

            public void setInviteNumber(int inviteNumber) {
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

            public int getGrantType() {
                return grantType;
            }

            public void setGrantType(int grantType) {
                this.grantType = grantType;
            }
        }
    }
}
