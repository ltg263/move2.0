package com.secretk.move.bean;

import com.google.gson.annotations.SerializedName;
import com.secretk.move.bean.base.BaseRes;

/**
 * 作者： litongge
 * 时间：  2018/4/26 20:45
 * 邮箱；ltg263@126.com
 * 描述：
 */

public class HomeUserIndexBean extends BaseRes{

    /**
     * data : {"user":{"homePageTitle":"我的主页","showFollow":0,"totalPostNum":100,"praiseNum":100,"fansNum":100,"userType":1,"icon":"upload/userIcons/1.jpg","userName":"老猫","userSignature":"xxxx"}}
     * serverDatetime : 1503147401
     */

    private DataBean data;
    @SerializedName("serverDatetime")
    private int serverDatetimeX;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getServerDatetimeX() {
        return serverDatetimeX;
    }

    public void setServerDatetimeX(int serverDatetimeX) {
        this.serverDatetimeX = serverDatetimeX;
    }

    public static class DataBean {
        /**
         * user : {"homePageTitle":"我的主页","showFollow":0,"totalPostNum":100,"praiseNum":100,"fansNum":100,"userType":1,"icon":"upload/userIcons/1.jpg","userName":"老猫","userSignature":"xxxx"}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * homePageTitle : 我的主页
             * showFollow : 0
             * totalPostNum : 100
             * praiseNum : 100
             * fansNum : 100
             * userType : 1
             * icon : upload/userIcons/1.jpg
             * userName : 老猫
             * userSignature : xxxx
             */

            private String homePageTitle;
            private int showFollow;
            private int totalPostNum;
            private int praiseNum;
            private int fansNum;
            private int userType;
            private String icon;
            private String userName;
            private String userSignature;

            public String getHomePageTitle() {
                return homePageTitle;
            }

            public void setHomePageTitle(String homePageTitle) {
                this.homePageTitle = homePageTitle;
            }

            public int getShowFollow() {
                return showFollow;
            }

            public void setShowFollow(int showFollow) {
                this.showFollow = showFollow;
            }

            public int getTotalPostNum() {
                return totalPostNum;
            }

            public void setTotalPostNum(int totalPostNum) {
                this.totalPostNum = totalPostNum;
            }

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public int getFansNum() {
                return fansNum;
            }

            public void setFansNum(int fansNum) {
                this.fansNum = fansNum;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSignature() {
                return userSignature;
            }

            public void setUserSignature(String userSignature) {
                this.userSignature = userSignature;
            }
        }
    }
}
