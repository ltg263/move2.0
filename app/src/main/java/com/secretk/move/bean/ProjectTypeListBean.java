package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/6/2 10:59
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class ProjectTypeListBean extends BaseRes{

    /**
     * data : {"projectTypes":[{"projectTypeId":9,"projectTypeName":"~！@#￥%\u2026\u2026&*（）","createUserId":0,"createTime":1527217654000,"createTimeStr":"2018-05-25 11:07:34","updateTime":1527217654000,"updateTimeStr":"2018-05-25 11:07:34","status":1},{"projectTypeId":8,"projectTypeName":"lalala","createUserId":0,"createTime":1527213574000,"createTimeStr":"2018-05-25 09:59:34","updateTime":1527213574000,"updateTimeStr":"2018-05-25 09:59:34","status":1},{"projectTypeId":7,"projectTypeName":"物流","createUserId":0,"createTime":1525859486000,"createTimeStr":"2018-05-09 17:51:26","updateTime":1525859486000,"updateTimeStr":"2018-05-09 17:51:26","status":1},{"projectTypeId":6,"projectTypeName":"1111","createUserId":0,"createTime":1525312502000,"createTimeStr":"2018-05-03 09:55:02","updateTime":1527213568000,"updateTimeStr":"2018-05-25 09:59:28","status":1},{"projectTypeId":4,"projectTypeName":"供应链","createUserId":1,"createTime":1524301901000,"createTimeStr":"2018-04-21 17:11:41","updateTime":1525618522000,"updateTimeStr":"2018-05-06 22:55:22","status":1},{"projectTypeId":3,"projectTypeName":"学术","createUserId":1,"createTime":1524301896000,"createTimeStr":"2018-04-21 17:11:36","updateTime":1524301896000,"updateTimeStr":"2018-04-21 17:11:36","status":1},{"projectTypeId":2,"projectTypeName":"游戏","createUserId":1,"createTime":1524301896000,"createTimeStr":"2018-04-21 17:11:36","updateTime":1524301896000,"updateTimeStr":"2018-04-21 17:11:36","status":1},{"projectTypeId":1,"projectTypeName":"金融","createUserId":1,"createTime":1524301895000,"createTimeStr":"2018-04-21 17:11:35","updateTime":1524301895000,"updateTimeStr":"2018-04-21 17:11:35","status":1}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ProjectTypesBean> projectTypes;

        public List<ProjectTypesBean> getProjectTypes() {
            return projectTypes;
        }

        public void setProjectTypes(List<ProjectTypesBean> projectTypes) {
            this.projectTypes = projectTypes;
        }

        public static class ProjectTypesBean {
            /**
             * projectTypeId : 9
             * projectTypeName : ~！@#￥%……&*（）
             * createUserId : 0
             * createTime : 1527217654000
             * createTimeStr : 2018-05-25 11:07:34
             * updateTime : 1527217654000
             * updateTimeStr : 2018-05-25 11:07:34
             * status : 1
             */

            private int projectTypeId;
            private String projectTypeName;
            private int createUserId;
            private long createTime;
            private String createTimeStr;
            private long updateTime;
            private String updateTimeStr;
            private int status;

            public int getProjectTypeId() {
                return projectTypeId;
            }

            public void setProjectTypeId(int projectTypeId) {
                this.projectTypeId = projectTypeId;
            }

            public String getProjectTypeName() {
                return projectTypeName;
            }

            public void setProjectTypeName(String projectTypeName) {
                this.projectTypeName = projectTypeName;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
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
        }
    }
}
