package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

public class MessageBean extends BaseRes{

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"messages":{"rowCount":47,"rows":[{"messageId":177,"type":2,"status":1,"state":1,"createTime":1527247072000,"createTimeStr":"2018-05-25 19:17:52","updateTime":1527247072000,"updateTimeStr":"2018-05-25 19:17:52","userId":3,"title":"点赞","content":"哈喽1点赞了您!","jumpInfo":""},{"messageId":176,"type":1,"status":1,"state":1,"createTime":1527240535000,"createTimeStr":"2018-05-25 17:28:55","updateTime":1527240535000,"updateTimeStr":"2018-05-25 17:28:55","userId":3,"title":"关注","content":"U1g84Lq67关注了您!","jumpInfo":""},{"messageId":172,"type":2,"status":1,"state":1,"createTime":1527079259000,"createTimeStr":"2018-05-23 20:40:59","updateTime":1527079259000,"updateTimeStr":"2018-05-23 20:40:59","userId":3,"title":"点赞","content":"哈喽1点赞了您!","jumpInfo":""},{"messageId":168,"type":2,"status":1,"state":1,"createTime":1527072386000,"createTimeStr":"2018-05-23 18:46:26","updateTime":1527072386000,"updateTimeStr":"2018-05-23 18:46:26","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""},{"messageId":158,"type":2,"status":1,"state":1,"createTime":1527066666000,"createTimeStr":"2018-05-23 17:11:06","updateTime":1527066666000,"updateTimeStr":"2018-05-23 17:11:06","userId":3,"title":"点赞","content":"哈喽1点赞了您!","jumpInfo":""},{"messageId":156,"type":2,"status":1,"state":1,"createTime":1526978152000,"createTimeStr":"2018-05-22 16:35:52","updateTime":1526978152000,"updateTimeStr":"2018-05-22 16:35:52","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""},{"messageId":153,"type":1,"status":1,"state":1,"createTime":1526972103000,"createTimeStr":"2018-05-22 14:55:03","updateTime":1526972103000,"updateTimeStr":"2018-05-22 14:55:03","userId":3,"title":"关注","content":"哈喽1关注了您!","jumpInfo":""},{"messageId":150,"type":2,"status":1,"state":1,"createTime":1526965820000,"createTimeStr":"2018-05-22 13:10:20","updateTime":1526965820000,"updateTimeStr":"2018-05-22 13:10:20","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""},{"messageId":145,"type":2,"status":1,"state":1,"createTime":1526961664000,"createTimeStr":"2018-05-22 12:01:04","updateTime":1526961664000,"updateTimeStr":"2018-05-22 12:01:04","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""},{"messageId":144,"type":2,"status":1,"state":1,"createTime":1526961654000,"createTimeStr":"2018-05-22 12:00:54","updateTime":1526961654000,"updateTimeStr":"2018-05-22 12:00:54","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""}],"pageSize":5,"rowsPerPage":10,"curPageNum":1,"queryParameters":"status=1&userId=3"}}
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
         * messages : {"rowCount":47,"rows":[{"messageId":177,"type":2,"status":1,"state":1,"createTime":1527247072000,"createTimeStr":"2018-05-25 19:17:52","updateTime":1527247072000,"updateTimeStr":"2018-05-25 19:17:52","userId":3,"title":"点赞","content":"哈喽1点赞了您!","jumpInfo":""},{"messageId":176,"type":1,"status":1,"state":1,"createTime":1527240535000,"createTimeStr":"2018-05-25 17:28:55","updateTime":1527240535000,"updateTimeStr":"2018-05-25 17:28:55","userId":3,"title":"关注","content":"U1g84Lq67关注了您!","jumpInfo":""},{"messageId":172,"type":2,"status":1,"state":1,"createTime":1527079259000,"createTimeStr":"2018-05-23 20:40:59","updateTime":1527079259000,"updateTimeStr":"2018-05-23 20:40:59","userId":3,"title":"点赞","content":"哈喽1点赞了您!","jumpInfo":""},{"messageId":168,"type":2,"status":1,"state":1,"createTime":1527072386000,"createTimeStr":"2018-05-23 18:46:26","updateTime":1527072386000,"updateTimeStr":"2018-05-23 18:46:26","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""},{"messageId":158,"type":2,"status":1,"state":1,"createTime":1527066666000,"createTimeStr":"2018-05-23 17:11:06","updateTime":1527066666000,"updateTimeStr":"2018-05-23 17:11:06","userId":3,"title":"点赞","content":"哈喽1点赞了您!","jumpInfo":""},{"messageId":156,"type":2,"status":1,"state":1,"createTime":1526978152000,"createTimeStr":"2018-05-22 16:35:52","updateTime":1526978152000,"updateTimeStr":"2018-05-22 16:35:52","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""},{"messageId":153,"type":1,"status":1,"state":1,"createTime":1526972103000,"createTimeStr":"2018-05-22 14:55:03","updateTime":1526972103000,"updateTimeStr":"2018-05-22 14:55:03","userId":3,"title":"关注","content":"哈喽1关注了您!","jumpInfo":""},{"messageId":150,"type":2,"status":1,"state":1,"createTime":1526965820000,"createTimeStr":"2018-05-22 13:10:20","updateTime":1526965820000,"updateTimeStr":"2018-05-22 13:10:20","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""},{"messageId":145,"type":2,"status":1,"state":1,"createTime":1526961664000,"createTimeStr":"2018-05-22 12:01:04","updateTime":1526961664000,"updateTimeStr":"2018-05-22 12:01:04","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""},{"messageId":144,"type":2,"status":1,"state":1,"createTime":1526961654000,"createTimeStr":"2018-05-22 12:00:54","updateTime":1526961654000,"updateTimeStr":"2018-05-22 12:00:54","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""}],"pageSize":5,"rowsPerPage":10,"curPageNum":1,"queryParameters":"status=1&userId=3"}
         */

        private MessagesBean messages;

        public MessagesBean getMessages() {
            return messages;
        }

        public void setMessages(MessagesBean messages) {
            this.messages = messages;
        }

        public static class MessagesBean {
            /**
             * rowCount : 47
             * rows : [{"messageId":177,"type":2,"status":1,"state":1,"createTime":1527247072000,"createTimeStr":"2018-05-25 19:17:52","updateTime":1527247072000,"updateTimeStr":"2018-05-25 19:17:52","userId":3,"title":"点赞","content":"哈喽1点赞了您!","jumpInfo":""},{"messageId":176,"type":1,"status":1,"state":1,"createTime":1527240535000,"createTimeStr":"2018-05-25 17:28:55","updateTime":1527240535000,"updateTimeStr":"2018-05-25 17:28:55","userId":3,"title":"关注","content":"U1g84Lq67关注了您!","jumpInfo":""},{"messageId":172,"type":2,"status":1,"state":1,"createTime":1527079259000,"createTimeStr":"2018-05-23 20:40:59","updateTime":1527079259000,"updateTimeStr":"2018-05-23 20:40:59","userId":3,"title":"点赞","content":"哈喽1点赞了您!","jumpInfo":""},{"messageId":168,"type":2,"status":1,"state":1,"createTime":1527072386000,"createTimeStr":"2018-05-23 18:46:26","updateTime":1527072386000,"updateTimeStr":"2018-05-23 18:46:26","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""},{"messageId":158,"type":2,"status":1,"state":1,"createTime":1527066666000,"createTimeStr":"2018-05-23 17:11:06","updateTime":1527066666000,"updateTimeStr":"2018-05-23 17:11:06","userId":3,"title":"点赞","content":"哈喽1点赞了您!","jumpInfo":""},{"messageId":156,"type":2,"status":1,"state":1,"createTime":1526978152000,"createTimeStr":"2018-05-22 16:35:52","updateTime":1526978152000,"updateTimeStr":"2018-05-22 16:35:52","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""},{"messageId":153,"type":1,"status":1,"state":1,"createTime":1526972103000,"createTimeStr":"2018-05-22 14:55:03","updateTime":1526972103000,"updateTimeStr":"2018-05-22 14:55:03","userId":3,"title":"关注","content":"哈喽1关注了您!","jumpInfo":""},{"messageId":150,"type":2,"status":1,"state":1,"createTime":1526965820000,"createTimeStr":"2018-05-22 13:10:20","updateTime":1526965820000,"updateTimeStr":"2018-05-22 13:10:20","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""},{"messageId":145,"type":2,"status":1,"state":1,"createTime":1526961664000,"createTimeStr":"2018-05-22 12:01:04","updateTime":1526961664000,"updateTimeStr":"2018-05-22 12:01:04","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""},{"messageId":144,"type":2,"status":1,"state":1,"createTime":1526961654000,"createTimeStr":"2018-05-22 12:00:54","updateTime":1526961654000,"updateTimeStr":"2018-05-22 12:00:54","userId":3,"title":"评论点赞","content":"哈喽1点赞了您的评论!","jumpInfo":""}]
             * pageSize : 5
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : status=1&userId=3
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
                 * messageId : 177
                 * type : 2
                 * status : 1
                 * state : 1
                 * createTime : 1527247072000
                 * createTimeStr : 2018-05-25 19:17:52
                 * updateTime : 1527247072000
                 * updateTimeStr : 2018-05-25 19:17:52
                 * userId : 3
                 * title : 点赞
                 * content : 哈喽1点赞了您!
                 * jumpInfo :
                 */

                private int messageId;
                private int type;
                private int status;
                private int state;
                private long createTime;
                private String createTimeStr;
                private long updateTime;
                private String updateTimeStr;
                private int userId;
                private int postId;
                private int postType;
                private int senderUserId;
                private String title;
                private String content;
                private String jumpInfo;
                private String senderUserIcon;

                public int getPostType() {
                    return postType;
                }

                public void setPostType(int postType) {
                    this.postType = postType;
                }

                public int getPostId() {
                    return postId;
                }

                public void setPostId(int postId) {
                    this.postId = postId;
                }

                public void setSenderUserId(int senderUserId) {
                    this.senderUserId = senderUserId;
                }

                public int getSenderUserId() {
                    return senderUserId;
                }

                public String getSenderUserIcon() {
                    return senderUserIcon;
                }

                public void setSenderUserIcon(String senderUserIcon) {
                    this.senderUserIcon = senderUserIcon;
                }

                public int getMessageId() {
                    return messageId;
                }

                public void setMessageId(int messageId) {
                    this.messageId = messageId;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
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

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getJumpInfo() {
                    return jumpInfo;
                }

                public void setJumpInfo(String jumpInfo) {
                    this.jumpInfo = jumpInfo;
                }
            }
        }
    }
}
