package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

/**
 * 作者： litongge
 * 时间：  2018/4/24 19:58
 * 邮箱；ltg263@126.com
 * 描述：用户的登录信息
 */

public class UserLoginInfo extends BaseRes{


    /**
     * data : {"token":"MzoxNTI0NjIyODM1MTM5OmM5YmY4YjgyZDhkNDQyMDQ5YWI3NWE4OTI0YmQxNzlj","user":{"followStatus":0,"userId":3,"userName":"UVHaV6BJo","sex":1,"icon":"/upload/userIcons/default.jpg","mobile":"13212345678","email":null,"wechat":null,"password":"true","payPassword":"true","userType":1,"userDegree":1,"userSignature":null,"fansNum":0,"praiseNum":0,"evaluationNum":0,"discussNum":0,"articleNum":0,"kffCoinNum":0,"areaName":null,"createTime":1524622819000,"createTimeStr":"2018-04-25 10:20:19","updateTime":1524622819000,"updateTimeStr":"2018-04-25 10:20:19","status":1,"memo":null,"provinceCode":null,"cityCode":null,"areaCode":null,"referUserId":null,"referLevel":0}}
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
         * token : MzoxNTI0NjIyODM1MTM5OmM5YmY4YjgyZDhkNDQyMDQ5YWI3NWE4OTI0YmQxNzlj
         * user : {"followStatus":0,"userId":3,"userName":"UVHaV6BJo","sex":1,"icon":"/upload/userIcons/default.jpg","mobile":"13212345678","email":null,"wechat":null,"password":"true","payPassword":"true","userType":1,"userDegree":1,"userSignature":null,"fansNum":0,"praiseNum":0,"evaluationNum":0,"discussNum":0,"articleNum":0,"kffCoinNum":0,"areaName":null,"createTime":1524622819000,"createTimeStr":"2018-04-25 10:20:19","updateTime":1524622819000,"updateTimeStr":"2018-04-25 10:20:19","status":1,"memo":null,"provinceCode":null,"cityCode":null,"areaCode":null,"referUserId":null,"referLevel":0}
         */

        private String token;
        private UserBean user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * followStatus : 0
             * userId : 3
             * userName : UVHaV6BJo
             * sex : 1
             * icon : /upload/userIcons/default.jpg
             * mobile : 13212345678
             * email : null
             * wechat : null
             * password : true
             * payPassword : true
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
             * createTime : 1524622819000
             * createTimeStr : 2018-04-25 10:20:19
             * updateTime : 1524622819000
             * updateTimeStr : 2018-04-25 10:20:19
             * status : 1
             * memo : null
             * provinceCode : null
             * cityCode : null
             * areaCode : null
             * referUserId : null
             * referLevel : 0
             */

            private int followStatus;
            private int userId;
            private String userName;
            private int sex;
            private String icon;
            private String mobile;
            private Object email;
            private Object wechat;
            private String password;
            private String payPassword;
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
            private long createTime;
            private String createTimeStr;
            private long updateTime;
            private String updateTimeStr;
            private int status;
            private Object memo;
            private Object provinceCode;
            private Object cityCode;
            private Object areaCode;
            private Object referUserId;
            private int referLevel;

            public int getFollowStatus() {
                return followStatus;
            }

            public void setFollowStatus(int followStatus) {
                this.followStatus = followStatus;
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

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public Object getWechat() {
                return wechat;
            }

            public void setWechat(Object wechat) {
                this.wechat = wechat;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getPayPassword() {
                return payPassword;
            }

            public void setPayPassword(String payPassword) {
                this.payPassword = payPassword;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getMemo() {
                return memo;
            }

            public void setMemo(Object memo) {
                this.memo = memo;
            }

            public Object getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(Object provinceCode) {
                this.provinceCode = provinceCode;
            }

            public Object getCityCode() {
                return cityCode;
            }

            public void setCityCode(Object cityCode) {
                this.cityCode = cityCode;
            }

            public Object getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(Object areaCode) {
                this.areaCode = areaCode;
            }

            public Object getReferUserId() {
                return referUserId;
            }

            public void setReferUserId(Object referUserId) {
                this.referUserId = referUserId;
            }

            public int getReferLevel() {
                return referLevel;
            }

            public void setReferLevel(int referLevel) {
                this.referLevel = referLevel;
            }

            @Override
            public String toString() {
                return "{" +
                        "followStatus=" + followStatus +
                        ", userId=" + userId +
                        ", userName='" + userName + '\'' +
                        ", sex=" + sex +
                        ", icon='" + icon + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", email=" + email +
                        ", wechat=" + wechat +
                        ", password='" + password + '\'' +
                        ", payPassword='" + payPassword + '\'' +
                        ", userType=" + userType +
                        ", userDegree=" + userDegree +
                        ", userSignature=" + userSignature +
                        ", fansNum=" + fansNum +
                        ", praiseNum=" + praiseNum +
                        ", evaluationNum=" + evaluationNum +
                        ", discussNum=" + discussNum +
                        ", articleNum=" + articleNum +
                        ", kffCoinNum=" + kffCoinNum +
                        ", areaName=" + areaName +
                        ", createTime=" + createTime +
                        ", createTimeStr='" + createTimeStr + '\'' +
                        ", updateTime=" + updateTime +
                        ", updateTimeStr='" + updateTimeStr + '\'' +
                        ", status=" + status +
                        ", memo=" + memo +
                        ", provinceCode=" + provinceCode +
                        ", cityCode=" + cityCode +
                        ", areaCode=" + areaCode +
                        ", referUserId=" + referUserId +
                        ", referLevel=" + referLevel +
                        '}';
            }
        }
    }
}
