package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

/**
 * 作者： litongge
 * 时间：  2018/4/26 20:45
 * 邮箱；ltg263@126.com
 * 描述：
 */

public class HomeUserIndexBean extends BaseRes{

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"user":{"homePageTitle":"我的主页","showFollow":0,"totalPostNum":0,"userId":3,"userName":"UVHaV6BJo","sex":1,"icon":"/upload/avatars/avatar.png","userType":1,"userDegree":1,"userSignature":null,"fansNum":0,"praiseNum":0,"evaluationNum":0,"discussNum":0,"articleNum":0,"kffCoinNum":0,"areaName":null,"status":1}}
     */

    private Object status;
    private Object reason;
    private Object fromuri;
    private Object token;
    private DataBean data;

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

    public Object getFromuri() {
        return fromuri;
    }

    public void setFromuri(Object fromuri) {
        this.fromuri = fromuri;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user : {"homePageTitle":"我的主页","showFollow":0,"totalPostNum":0,"userId":3,"userName":"UVHaV6BJo","sex":1,"icon":"/upload/avatars/avatar.png","userType":1,"userDegree":1,"userSignature":null,"fansNum":0,"praiseNum":0,"evaluationNum":0,"discussNum":0,"articleNum":0,"kffCoinNum":0,"areaName":null,"status":1}
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
             * totalPostNum : 0
             * userId : 3
             * userName : UVHaV6BJo
             * sex : 1
             * icon : /upload/avatars/avatar.png
             * userType : 1
             * userDegree : 1
             * userSignature : null
             * fansNum : 0
             * praiseNum : 0
             * evaluationNum : 0
             * discussNum : 0
             * articleNum : 0
             * kffCoinNum : 0
             * areaName : null
             * status : 1
             */

            private String homePageTitle;
            private int showFollow;
            private int totalPostNum;
            private int userId;
            private String userName;
            private int sex;
            private String icon;
            private int userType;
            private int userDegree;
            private String userSignature;
            private int fansNum;
            private int praiseNum;
            private int evaluationNum;
            private int discussNum;
            private int articleNum;
            private int kffCoinNum;
            private Object areaName;
            private int status;

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

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public int getUserDegree() {
                return userDegree;
            }

            public void setUserDegree(int userDegree) {
                this.userDegree = userDegree;
            }

            public String getUserSignature() {
                return userSignature;
            }

            public void setUserSignature(String userSignature) {
                this.userSignature = userSignature;
            }

            public int getFansNum() {
                return fansNum;
            }

            public void setFansNum(int fansNum) {
                this.fansNum = fansNum;
            }

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public int getEvaluationNum() {
                return evaluationNum;
            }

            public void setEvaluationNum(int evaluationNum) {
                this.evaluationNum = evaluationNum;
            }

            public int getDiscussNum() {
                return discussNum;
            }

            public void setDiscussNum(int discussNum) {
                this.discussNum = discussNum;
            }

            public int getArticleNum() {
                return articleNum;
            }

            public void setArticleNum(int articleNum) {
                this.articleNum = articleNum;
            }

            public int getKffCoinNum() {
                return kffCoinNum;
            }

            public void setKffCoinNum(int kffCoinNum) {
                this.kffCoinNum = kffCoinNum;
            }

            public Object getAreaName() {
                return areaName;
            }

            public void setAreaName(Object areaName) {
                this.areaName = areaName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
