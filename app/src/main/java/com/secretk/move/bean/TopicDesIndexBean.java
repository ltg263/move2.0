package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

/**
 * 作者： litongge
 * 时间：  2018/9/6 11:50
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class TopicDesIndexBean extends BaseRes{

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"tagId":13,"tagName":"测试","createUserId":1,"createTime":1535972016000,"createTimeStr":"2018-09-03 18:53:36","updateTime":1535972016000,"updateTimeStr":"2018-09-03 18:53:36","status":true,"memo":"差 阿斯打撒","typeId":3,"imgPath":"https://pic.qufen.top/projects1535972016222015467","stickTop":1,"stickUpdateTime":1536024230000}
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
         * tagId : 13
         * tagName : 测试
         * createUserId : 1
         * createTime : 1535972016000
         * createTimeStr : 2018-09-03 18:53:36
         * updateTime : 1535972016000
         * updateTimeStr : 2018-09-03 18:53:36
         * status : true
         * memo : 差 阿斯打撒
         * typeId : 3
         * imgPath : https://pic.qufen.top/projects1535972016222015467
         * stickTop : 1
         * stickUpdateTime : 1536024230000
         */

        private int tagId;
        private String tagName;
        private int createUserId;
        private long createTime;
        private String createTimeStr;
        private long updateTime;
        private String updateTimeStr;
        private boolean status;
        private String memo;
        private int typeId;
        private String imgPath;
        private int stickTop;
        private long stickUpdateTime;

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

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public int getStickTop() {
            return stickTop;
        }

        public void setStickTop(int stickTop) {
            this.stickTop = stickTop;
        }

        public long getStickUpdateTime() {
            return stickUpdateTime;
        }

        public void setStickUpdateTime(long stickUpdateTime) {
            this.stickUpdateTime = stickUpdateTime;
        }
    }
}
