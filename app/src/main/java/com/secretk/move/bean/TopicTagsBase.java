package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/9/6 14:20
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class TopicTagsBase extends BaseRes{

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : [{"tagId":9,"tagName":"uu","createUserId":1,"createTime":1534228486000,"createTimeStr":"2018-08-14 14:34:46","updateTime":1535968763000,"updateTimeStr":"2018-09-03 17:59:23","status":true,"memo":"也一样","typeId":1,"imgPath":"https://pic.qufen.top/projects1535972016222015467","stickTop":1,"stickUpdateTime":1536024233000},{"tagId":10,"tagName":"打假项目进展","createUserId":1,"createTime":1535016412000,"createTimeStr":"2018-08-23 17:26:52","updateTime":1535517978000,"updateTimeStr":"2018-08-29 12:46:18","status":true,"memo":"h1","typeId":2,"imgPath":"https://pic.qufen.top/projects1535972016222015467","stickTop":1,"stickUpdateTime":1536024232000},{"tagId":11,"tagName":"打假项目","createUserId":1,"createTime":1535016494000,"createTimeStr":"2018-08-23 17:28:14","updateTime":1535968343000,"updateTimeStr":"2018-09-03 17:52:23","status":true,"memo":"h1","typeId":1,"imgPath":"https://pic.qufen.top/projects1535972016222015467","stickTop":1,"stickUpdateTime":1536024230000},{"tagId":13,"tagName":"测试","createUserId":1,"createTime":1535972016000,"createTimeStr":"2018-09-03 18:53:36","updateTime":1535972016000,"updateTimeStr":"2018-09-03 18:53:36","status":true,"memo":"差 阿斯打撒","typeId":3,"imgPath":"https://pic.qufen.top/projects1535972016222015467","stickTop":1,"stickUpdateTime":1536024230000},{"tagId":12,"tagName":"嗯嗯","createUserId":1,"createTime":1535016531000,"createTimeStr":"2018-08-23 17:28:51","updateTime":1536129542000,"updateTimeStr":"2018-09-05 14:39:02","status":true,"memo":"h2","typeId":1,"imgPath":"https://pic.qufen.top/projects1535972016222015467","stickTop":1,"stickUpdateTime":1536024228000}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tagId : 9
         * tagName : uu
         * createUserId : 1
         * createTime : 1534228486000
         * createTimeStr : 2018-08-14 14:34:46
         * updateTime : 1535968763000
         * updateTimeStr : 2018-09-03 17:59:23
         * status : true
         * memo : 也一样
         * typeId : 1
         * imgPath : https://pic.qufen.top/projects1535972016222015467
         * stickTop : 1
         * stickUpdateTime : 1536024233000
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
