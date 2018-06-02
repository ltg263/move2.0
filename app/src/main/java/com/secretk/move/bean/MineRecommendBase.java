package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/6/1 16:35
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class MineRecommendBase extends BaseRes{

    /**
     * data : {"myTokenRecords":[{"coinPropertyId":1,"userId":1,"coinLock":0,"coinUnlock":100,"coinUnlockTime":null,"coinUnlockTimeStr":"","coinUnlockType":1,"coinDistributed":20000,"coinUsable":100,"coinUnlockUptime":1,"coinUnlockUptimeStr":"","totalAssets":20200}],"wallet":[{"followStatus":0,"userWalletId":3,"userId":3,"userName":"哈喽","mobile":"13212345678","createTimeStr":null,"updateTimeStr":null,"wallet":"","walletType":0,"createtime":1527838628000,"updatetime":1527838631000}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MyTokenRecordsBean> myTokenRecords;
        private List<WalletBean> wallet;

        public List<MyTokenRecordsBean> getMyTokenRecords() {
            return myTokenRecords;
        }

        public void setMyTokenRecords(List<MyTokenRecordsBean> myTokenRecords) {
            this.myTokenRecords = myTokenRecords;
        }

        public List<WalletBean> getWallet() {
            return wallet;
        }

        public void setWallet(List<WalletBean> wallet) {
            this.wallet = wallet;
        }

        public static class MyTokenRecordsBean {
            /**
             * coinPropertyId : 1
             * userId : 1
             * coinLock : 0
             * coinUnlock : 100
             * coinUnlockTime : null
             * coinUnlockTimeStr :
             * coinUnlockType : 1
             * coinDistributed : 20000
             * coinUsable : 100
             * coinUnlockUptime : 1
             * coinUnlockUptimeStr :
             * totalAssets : 20200
             */

            private int coinPropertyId;
            private int userId;
            private int coinLock;
            private int coinUnlock;
            private Object coinUnlockTime;
            private String coinUnlockTimeStr;
            private int coinUnlockType;
            private int coinDistributed;
            private int coinUsable;
            private int coinUnlockUptime;
            private String coinUnlockUptimeStr;
            private int totalAssets;

            public int getCoinPropertyId() {
                return coinPropertyId;
            }

            public void setCoinPropertyId(int coinPropertyId) {
                this.coinPropertyId = coinPropertyId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getCoinLock() {
                return coinLock;
            }

            public void setCoinLock(int coinLock) {
                this.coinLock = coinLock;
            }

            public int getCoinUnlock() {
                return coinUnlock;
            }

            public void setCoinUnlock(int coinUnlock) {
                this.coinUnlock = coinUnlock;
            }

            public Object getCoinUnlockTime() {
                return coinUnlockTime;
            }

            public void setCoinUnlockTime(Object coinUnlockTime) {
                this.coinUnlockTime = coinUnlockTime;
            }

            public String getCoinUnlockTimeStr() {
                return coinUnlockTimeStr;
            }

            public void setCoinUnlockTimeStr(String coinUnlockTimeStr) {
                this.coinUnlockTimeStr = coinUnlockTimeStr;
            }

            public int getCoinUnlockType() {
                return coinUnlockType;
            }

            public void setCoinUnlockType(int coinUnlockType) {
                this.coinUnlockType = coinUnlockType;
            }

            public int getCoinDistributed() {
                return coinDistributed;
            }

            public void setCoinDistributed(int coinDistributed) {
                this.coinDistributed = coinDistributed;
            }

            public int getCoinUsable() {
                return coinUsable;
            }

            public void setCoinUsable(int coinUsable) {
                this.coinUsable = coinUsable;
            }

            public int getCoinUnlockUptime() {
                return coinUnlockUptime;
            }

            public void setCoinUnlockUptime(int coinUnlockUptime) {
                this.coinUnlockUptime = coinUnlockUptime;
            }

            public String getCoinUnlockUptimeStr() {
                return coinUnlockUptimeStr;
            }

            public void setCoinUnlockUptimeStr(String coinUnlockUptimeStr) {
                this.coinUnlockUptimeStr = coinUnlockUptimeStr;
            }

            public int getTotalAssets() {
                return totalAssets;
            }

            public void setTotalAssets(int totalAssets) {
                this.totalAssets = totalAssets;
            }
        }

        public static class WalletBean {
            /**
             * followStatus : 0
             * userWalletId : 3
             * userId : 3
             * userName : 哈喽
             * mobile : 13212345678
             * createTimeStr : null
             * updateTimeStr : null
             * wallet :
             * walletType : 0
             * createtime : 1527838628000
             * updatetime : 1527838631000
             */

            private int followStatus;
            private int userWalletId;
            private int userId;
            private String userName;
            private String mobile;
            private Object createTimeStr;
            private Object updateTimeStr;
            private String wallet;
            private int walletType;
            private long createtime;
            private long updatetime;

            public int getFollowStatus() {
                return followStatus;
            }

            public void setFollowStatus(int followStatus) {
                this.followStatus = followStatus;
            }

            public int getUserWalletId() {
                return userWalletId;
            }

            public void setUserWalletId(int userWalletId) {
                this.userWalletId = userWalletId;
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

            public Object getCreateTimeStr() {
                return createTimeStr;
            }

            public void setCreateTimeStr(Object createTimeStr) {
                this.createTimeStr = createTimeStr;
            }

            public Object getUpdateTimeStr() {
                return updateTimeStr;
            }

            public void setUpdateTimeStr(Object updateTimeStr) {
                this.updateTimeStr = updateTimeStr;
            }

            public String getWallet() {
                return wallet;
            }

            public void setWallet(String wallet) {
                this.wallet = wallet;
            }

            public int getWalletType() {
                return walletType;
            }

            public void setWalletType(int walletType) {
                this.walletType = walletType;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }

            public long getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(long updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
