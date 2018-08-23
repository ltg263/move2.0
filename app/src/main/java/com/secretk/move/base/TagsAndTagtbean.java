package com.secretk.move.base;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/8/23 16:26
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class TagsAndTagtbean extends BaseRes{

    /**
     * data : {"result":[{"dtagsList":[{"createTime":1529496960000,"createTimeStr":"2018-06-20 20:16:00","createUserId":1,"status":true,"tagId":1,"tagName":"团队","typeId":1,"updateTime":1529496960000,"updateTimeStr":"2018-06-20 20:16:00"},{"createTime":1529496971000,"createTimeStr":"2018-06-20 20:16:11","createUserId":1,"status":true,"tagId":2,"tagName":"市值管理","typeId":1,"updateTime":1529496971000,"updateTimeStr":"2018-06-20 20:16:11"},{"createTime":1529496990000,"createTimeStr":"2018-06-20 20:16:30","createUserId":1,"status":true,"tagId":3,"tagName":"项目进度","typeId":1,"updateTime":1529496990000,"updateTimeStr":"2018-06-20 20:16:30"},{"createTime":1529496999000,"createTimeStr":"2018-06-20 20:16:39","createUserId":1,"status":true,"tagId":4,"tagName":"社区","typeId":1,"updateTime":1529496999000,"updateTimeStr":"2018-06-20 20:16:39"},{"createTime":1530703357000,"createTimeStr":"2018-07-04 19:22:37","createUserId":1,"status":true,"tagId":8,"tagName":"打假","typeId":1,"updateTime":1530703357000,"updateTimeStr":"2018-07-04 19:22:37"},{"createTime":1534228486000,"createTimeStr":"2018-08-14 14:34:46","createUserId":1,"status":true,"tagId":9,"tagName":"uu","typeId":1,"updateTime":1534228486000,"updateTimeStr":"2018-08-14 14:34:46"}],"isShow":1,"status":1,"tagTypeOrderNumder":1,"typeId":1,"typeName":"评测类型","updateTime":1534939246000},{"dtagsList":[{"createTime":1529497025000,"createTimeStr":"2018-06-20 20:17:05","createUserId":1,"status":true,"tagId":5,"tagName":"技术","typeId":2,"updateTime":1529497025000,"updateTimeStr":"2018-06-20 20:17:05"},{"createTime":1529497153000,"createTimeStr":"2018-06-20 20:19:13","createUserId":1,"status":true,"tagId":6,"tagName":"市场","typeId":2,"updateTime":1529497153000,"updateTimeStr":"2018-06-20 20:19:13"},{"createTime":1530703337000,"createTimeStr":"2018-07-04 19:22:17","createUserId":1,"status":true,"tagId":7,"tagName":"行情分析","typeId":2,"updateTime":1530703337000,"updateTimeStr":"2018-07-04 19:22:17"}],"isShow":1,"status":1,"tagTypeOrderNumder":2,"typeId":2,"typeName":"评测类型1","updateTime":1534939750000}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ResultBean> result;

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * dtagsList : [{"createTime":1529496960000,"createTimeStr":"2018-06-20 20:16:00","createUserId":1,"status":true,"tagId":1,"tagName":"团队","typeId":1,"updateTime":1529496960000,"updateTimeStr":"2018-06-20 20:16:00"},{"createTime":1529496971000,"createTimeStr":"2018-06-20 20:16:11","createUserId":1,"status":true,"tagId":2,"tagName":"市值管理","typeId":1,"updateTime":1529496971000,"updateTimeStr":"2018-06-20 20:16:11"},{"createTime":1529496990000,"createTimeStr":"2018-06-20 20:16:30","createUserId":1,"status":true,"tagId":3,"tagName":"项目进度","typeId":1,"updateTime":1529496990000,"updateTimeStr":"2018-06-20 20:16:30"},{"createTime":1529496999000,"createTimeStr":"2018-06-20 20:16:39","createUserId":1,"status":true,"tagId":4,"tagName":"社区","typeId":1,"updateTime":1529496999000,"updateTimeStr":"2018-06-20 20:16:39"},{"createTime":1530703357000,"createTimeStr":"2018-07-04 19:22:37","createUserId":1,"status":true,"tagId":8,"tagName":"打假","typeId":1,"updateTime":1530703357000,"updateTimeStr":"2018-07-04 19:22:37"},{"createTime":1534228486000,"createTimeStr":"2018-08-14 14:34:46","createUserId":1,"status":true,"tagId":9,"tagName":"uu","typeId":1,"updateTime":1534228486000,"updateTimeStr":"2018-08-14 14:34:46"}]
             * isShow : 1
             * status : 1
             * tagTypeOrderNumder : 1
             * typeId : 1
             * typeName : 评测类型
             * updateTime : 1534939246000
             */

            private int isShow;
            private int status;
            private int tagTypeOrderNumder;
            private int typeId;
            private String typeName;
            private long updateTime;
            private List<DtagsListBean> dtagsList;

            public int getIsShow() {
                return isShow;
            }

            public void setIsShow(int isShow) {
                this.isShow = isShow;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTagTypeOrderNumder() {
                return tagTypeOrderNumder;
            }

            public void setTagTypeOrderNumder(int tagTypeOrderNumder) {
                this.tagTypeOrderNumder = tagTypeOrderNumder;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public List<DtagsListBean> getDtagsList() {
                return dtagsList;
            }

            public void setDtagsList(List<DtagsListBean> dtagsList) {
                this.dtagsList = dtagsList;
            }

            public static class DtagsListBean {
                /**
                 * createTime : 1529496960000
                 * createTimeStr : 2018-06-20 20:16:00
                 * createUserId : 1
                 * status : true
                 * tagId : 1
                 * tagName : 团队
                 * typeId : 1
                 * updateTime : 1529496960000
                 * updateTimeStr : 2018-06-20 20:16:00
                 */

                private long createTime;
                private String createTimeStr;
                private int createUserId;
                private boolean status;
                private int tagId;
                private String tagName;
                private int typeId;
                private long updateTime;
                private String updateTimeStr;

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

                public int getCreateUserId() {
                    return createUserId;
                }

                public void setCreateUserId(int createUserId) {
                    this.createUserId = createUserId;
                }

                public boolean isStatus() {
                    return status;
                }

                public void setStatus(boolean status) {
                    this.status = status;
                }

                public int getTagId() {
                    return tagId;
                }

                public void setTagId(int tagId) {
                    this.tagId = tagId;
                }

                public String getTagName() {
                    return tagName;
                }

                public void setTagName(String tagName) {
                    this.tagName = tagName;
                }

                public int getTypeId() {
                    return typeId;
                }

                public void setTypeId(int typeId) {
                    this.typeId = typeId;
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
            }
        }
    }
}
