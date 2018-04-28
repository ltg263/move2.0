package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

/**
 * 作者： litongge
 * 时间：  2018/4/27 16:39
 * 邮箱；ltg263@126.com
 * 描述：我的评测数据
 */

public class HomeReviewBase extends BaseRes{

    /**
     * diyi : 13812345678
     * er : 12345678a
     * san : 572898
     */

    private String diyi;
    private String er;
    private String san;
    private int index;//1--2--3
    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getDiyi() {
        return diyi;
    }

    public void setDiyi(String diyi) {
        this.diyi = diyi;
    }

    public String getEr() {
        return er;
    }

    public void setEr(String er) {
        this.er = er;
    }

    public String getSan() {
        return san;
    }

    public void setSan(String san) {
        this.san = san;
    }
    //-------------------------------------------------------------------------

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"evaluations":{"rowCount":0,"rows":null,"pageSize":0,"rowsPerPage":10,"curPageNum":1,"queryParameters":null}}
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
         * evaluations : {"rowCount":0,"rows":null,"pageSize":0,"rowsPerPage":10,"curPageNum":1,"queryParameters":null}
         */

        private EvaluationsBean evaluations;

        public EvaluationsBean getEvaluations() {
            return evaluations;
        }

        public void setEvaluations(EvaluationsBean evaluations) {
            this.evaluations = evaluations;
        }

        public static class EvaluationsBean {
            /**
             * rowCount : 0
             * rows : null
             * pageSize : 0
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : null
             */

            private int rowCount;
            private Object rows;
            private int pageSize;
            private int rowsPerPage;
            private int curPageNum;
            private Object queryParameters;

            public int getRowCount() {
                return rowCount;
            }

            public void setRowCount(int rowCount) {
                this.rowCount = rowCount;
            }

            public Object getRows() {
                return rows;
            }

            public void setRows(Object rows) {
                this.rows = rows;
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

            public Object getQueryParameters() {
                return queryParameters;
            }

            public void setQueryParameters(Object queryParameters) {
                this.queryParameters = queryParameters;
            }
        }
    }
}
