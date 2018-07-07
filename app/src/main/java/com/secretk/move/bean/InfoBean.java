package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/7/5 14:02
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class InfoBean extends BaseRes{

    /**
     * data : {"data":{"rowCount":2,"rows":[{"id":10,"createdAt":1530676415000,"createDt":null,"updatedAt":1530676415000,"title":"啊大苏打aadasda","content":"啊啊是大三大dada","rise":0,"fall":0,"state":0,"author":"root","isCheckDetails":0,"type":0,"articleId":0,"isProminent":0},{"id":9,"createdAt":1530675115000,"createDt":null,"updatedAt":1530675115000,"title":"的期望的请问w'q","content":"我qq大旗网的期望的亲卫队请问","rise":0,"fall":0,"state":0,"author":"root","isCheckDetails":null,"type":null,"articleId":null,"isProminent":null}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"state=0"}}
     */

    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * data : {"rowCount":2,"rows":[{"id":10,"createdAt":1530676415000,"createDt":null,"updatedAt":1530676415000,"title":"啊大苏打aadasda","content":"啊啊是大三大dada","rise":0,"fall":0,"state":0,"author":"root","isCheckDetails":0,"type":0,"articleId":0,"isProminent":0},{"id":9,"createdAt":1530675115000,"createDt":null,"updatedAt":1530675115000,"title":"的期望的请问w'q","content":"我qq大旗网的期望的亲卫队请问","rise":0,"fall":0,"state":0,"author":"root","isCheckDetails":null,"type":null,"articleId":null,"isProminent":null}],"pageSize":1,"rowsPerPage":10,"curPageNum":1,"queryParameters":"state=0"}
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
             * rowCount : 2
             * rows : [{"id":10,"createdAt":1530676415000,"createDt":null,"updatedAt":1530676415000,"title":"啊大苏打aadasda","content":"啊啊是大三大dada","rise":0,"fall":0,"state":0,"author":"root","isCheckDetails":0,"type":0,"articleId":0,"isProminent":0},{"id":9,"createdAt":1530675115000,"createDt":null,"updatedAt":1530675115000,"title":"的期望的请问w'q","content":"我qq大旗网的期望的亲卫队请问","rise":0,"fall":0,"state":0,"author":"root","isCheckDetails":null,"type":null,"articleId":null,"isProminent":null}]
             * pageSize : 1
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : state=0
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
                 * id : 10
                 * createdAt : 1530676415000
                 * createDt : null
                 * updatedAt : 1530676415000
                 * title : 啊大苏打aadasda
                 * content : 啊啊是大三大dada
                 * rise : 0
                 * fall : 0
                 * state : 0
                 * author : root
                 * isCheckDetails : 0
                 * type : 0
                 * articleId : 0
                 * isProminent : 0
                 */

                private int id;
                private long createdAt;
                private Object createDt;
                private long updatedAt;
                private String title;
                private String content;
                private String imgPath;
                private int rise;
                private int fall;
                private String outUrl;
                private boolean isRise=false;
                private boolean isFall=false;
                private int state;
                private String author;
                private int isCheckDetails;
                //-类型：0-完整版专业评测，1-自定义评测，2-文章，3-打假，4-单项评测 5-外链URL
                private int type;
                private int articleId;
                private int isProminent;

                public void setOutUrl(String outUrl) {
                    this.outUrl = outUrl;
                }

                public String getOutUrl() {
                    return outUrl;
                }


                public String getImgPath() {
                    return imgPath;
                }

                public void setImgPath(String imgPath) {
                    this.imgPath = imgPath;
                }

                public boolean isRise() {
                    return isRise;
                }

                public boolean isFall() {
                    return isFall;
                }

                public void setRise(boolean rise) {
                    isRise = rise;
                }

                public void setFall(boolean fall) {
                    isFall = fall;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public long getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(long createdAt) {
                    this.createdAt = createdAt;
                }

                public Object getCreateDt() {
                    return createDt;
                }

                public void setCreateDt(Object createDt) {
                    this.createDt = createDt;
                }

                public long getUpdatedAt() {
                    return updatedAt;
                }

                public void setUpdatedAt(long updatedAt) {
                    this.updatedAt = updatedAt;
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

                public int getRise() {
                    return rise;
                }

                public void setRise(int rise) {
                    this.rise = rise;
                }

                public int getFall() {
                    return fall;
                }

                public void setFall(int fall) {
                    this.fall = fall;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public int getIsCheckDetails() {
                    return isCheckDetails;
                }

                public void setIsCheckDetails(int isCheckDetails) {
                    this.isCheckDetails = isCheckDetails;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getArticleId() {
                    return articleId;
                }

                public void setArticleId(int articleId) {
                    this.articleId = articleId;
                }

                public int getIsProminent() {
                    return isProminent;
                }

                public void setIsProminent(int isProminent) {
                    this.isProminent = isProminent;
                }
            }
        }
    }
}
