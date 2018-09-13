package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

/**
 * 作者： litongge
 * 时间：  2018/9/13 10:08
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class MoneyGuide extends BaseRes{

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"result":{"praiseDegr":10,"praiseAward":10,"praiseSumDegr":null,"praiseReceStatus":1,"praiseAwardSum":100,"sharePostDegr":0,"sharePostAward":2,"sharePostSumDegr":null,"sharePostReceStatus":0,"sharePostAwardSum":0,"commentDegr":0,"commentAward":2,"commentSumDegr":null,"commentReceStatus":0,"commentAwardSum":0,"evaDegr":null,"evaAward":100,"evaAwardSumDegr":null,"evaAwardSum":0,"evaReceStatus":0,"readingDegr":null,"readingAward":20,"readingSumDegr":null,"readingReceStatus":0,"readingAwardSum":0,"statusHierarchyType":100,"loginAward":0,"invaAward":0,"invaEachAward":1500,"todayAward":0}}
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
         * result : {"praiseDegr":10,"praiseAward":10,"praiseSumDegr":null,"praiseReceStatus":1,"praiseAwardSum":100,"sharePostDegr":0,"sharePostAward":2,"sharePostSumDegr":null,"sharePostReceStatus":0,"sharePostAwardSum":0,"commentDegr":0,"commentAward":2,"commentSumDegr":null,"commentReceStatus":0,"commentAwardSum":0,"evaDegr":null,"evaAward":100,"evaAwardSumDegr":null,"evaAwardSum":0,"evaReceStatus":0,"readingDegr":null,"readingAward":20,"readingSumDegr":null,"readingReceStatus":0,"readingAwardSum":0,"statusHierarchyType":100,"loginAward":0,"invaAward":0,"invaEachAward":1500,"todayAward":0}
         */

        private ResultBean result;

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * praiseDegr : 10
             * praiseAward : 10
             * praiseSumDegr : null
             * praiseReceStatus : 1
             * praiseAwardSum : 100
             * sharePostDegr : 0
             * sharePostAward : 2
             * sharePostSumDegr : null
             * sharePostReceStatus : 0
             * sharePostAwardSum : 0
             * commentDegr : 0
             * commentAward : 2
             * commentSumDegr : null
             * commentReceStatus : 0
             * commentAwardSum : 0
             * evaDegr : null
             * evaAward : 100
             * evaAwardSumDegr : null
             * evaAwardSum : 0
             * evaReceStatus : 0
             * readingDegr : null
             * readingAward : 20
             * readingSumDegr : null
             * readingReceStatus : 0
             * readingAwardSum : 0
             * statusHierarchyType : 100
             * loginAward : 0
             * invaAward : 0
             * invaEachAward : 1500
             * todayAward : 0
             */

            private int praiseDegr;
            private double praiseAward;
            private int praiseSumDegr;
            private int praiseReceStatus;
            private double praiseAwardSum;
            private int sharePostDegr;
            private double sharePostAward;
            private int sharePostSumDegr;
            private int sharePostReceStatus;
            private double sharePostAwardSum;
            private int commentDegr;
            private double commentAward;
            private double commentFirstAward;
            private int commentSumDegr;
            private int commentReceStatus;
            private double commentAwardSum;
            private int evaDegr;
            private double evaAward;
            private int evaAwardSumDegr;
            private double evaAwardSum;
            private int evaReceStatus;
            private int readingDegr;
            private double readingAward;
            private int readingSumDegr;
            private int readingReceStatus;
            private double readingAwardSum;
            private int statusHierarchyType;
            private double loginAward;
            private double invaAward;
            private double invaEachAward;
            private double todayAward;

            public int getPraiseDegr() {
                return praiseDegr;
            }

            public double getCommentFirstAward() {
                return commentFirstAward;
            }

            public void setCommentFirstAward(double commentFirstAward) {
                this.commentFirstAward = commentFirstAward;
            }

            public void setPraiseDegr(int praiseDegr) {
                this.praiseDegr = praiseDegr;
            }

            public double getPraiseAward() {
                return praiseAward;
            }

            public void setPraiseAward(double praiseAward) {
                this.praiseAward = praiseAward;
            }

            public int getPraiseSumDegr() {
                return praiseSumDegr;
            }

            public void setPraiseSumDegr(int praiseSumDegr) {
                this.praiseSumDegr = praiseSumDegr;
            }

            public int getPraiseReceStatus() {
                return praiseReceStatus;
            }

            public void setPraiseReceStatus(int praiseReceStatus) {
                this.praiseReceStatus = praiseReceStatus;
            }

            public double getPraiseAwardSum() {
                return praiseAwardSum;
            }

            public void setPraiseAwardSum(double praiseAwardSum) {
                this.praiseAwardSum = praiseAwardSum;
            }

            public int getSharePostDegr() {
                return sharePostDegr;
            }

            public void setSharePostDegr(int sharePostDegr) {
                this.sharePostDegr = sharePostDegr;
            }

            public double getSharePostAward() {
                return sharePostAward;
            }

            public void setSharePostAward(double sharePostAward) {
                this.sharePostAward = sharePostAward;
            }

            public int getSharePostSumDegr() {
                return sharePostSumDegr;
            }

            public void setSharePostSumDegr(int sharePostSumDegr) {
                this.sharePostSumDegr = sharePostSumDegr;
            }

            public int getSharePostReceStatus() {
                return sharePostReceStatus;
            }

            public void setSharePostReceStatus(int sharePostReceStatus) {
                this.sharePostReceStatus = sharePostReceStatus;
            }

            public double getSharePostAwardSum() {
                return sharePostAwardSum;
            }

            public void setSharePostAwardSum(double sharePostAwardSum) {
                this.sharePostAwardSum = sharePostAwardSum;
            }

            public int getCommentDegr() {
                return commentDegr;
            }

            public void setCommentDegr(int commentDegr) {
                this.commentDegr = commentDegr;
            }

            public double getCommentAward() {
                return commentAward;
            }

            public void setCommentAward(double commentAward) {
                this.commentAward = commentAward;
            }

            public int getCommentSumDegr() {
                return commentSumDegr;
            }

            public void setCommentSumDegr(int commentSumDegr) {
                this.commentSumDegr = commentSumDegr;
            }

            public int getCommentReceStatus() {
                return commentReceStatus;
            }

            public void setCommentReceStatus(int commentReceStatus) {
                this.commentReceStatus = commentReceStatus;
            }

            public double getCommentAwardSum() {
                return commentAwardSum;
            }

            public void setCommentAwardSum(double commentAwardSum) {
                this.commentAwardSum = commentAwardSum;
            }

            public int getEvaDegr() {
                return evaDegr;
            }

            public void setEvaDegr(int evaDegr) {
                this.evaDegr = evaDegr;
            }

            public double getEvaAward() {
                return evaAward;
            }

            public void setEvaAward(double evaAward) {
                this.evaAward = evaAward;
            }

            public int getEvaAwardSumDegr() {
                return evaAwardSumDegr;
            }

            public void setEvaAwardSumDegr(int evaAwardSumDegr) {
                this.evaAwardSumDegr = evaAwardSumDegr;
            }

            public double getEvaAwardSum() {
                return evaAwardSum;
            }

            public void setEvaAwardSum(double evaAwardSum) {
                this.evaAwardSum = evaAwardSum;
            }

            public int getEvaReceStatus() {
                return evaReceStatus;
            }

            public void setEvaReceStatus(int evaReceStatus) {
                this.evaReceStatus = evaReceStatus;
            }

            public int getReadingDegr() {
                return readingDegr;
            }

            public void setReadingDegr(int readingDegr) {
                this.readingDegr = readingDegr;
            }

            public double getReadingAward() {
                return readingAward;
            }

            public void setReadingAward(double readingAward) {
                this.readingAward = readingAward;
            }

            public int getReadingSumDegr() {
                return readingSumDegr;
            }

            public void setReadingSumDegr(int readingSumDegr) {
                this.readingSumDegr = readingSumDegr;
            }

            public int getReadingReceStatus() {
                return readingReceStatus;
            }

            public void setReadingReceStatus(int readingReceStatus) {
                this.readingReceStatus = readingReceStatus;
            }

            public double getReadingAwardSum() {
                return readingAwardSum;
            }

            public void setReadingAwardSum(double readingAwardSum) {
                this.readingAwardSum = readingAwardSum;
            }

            public int getStatusHierarchyType() {
                return statusHierarchyType;
            }

            public void setStatusHierarchyType(int statusHierarchyType) {
                this.statusHierarchyType = statusHierarchyType;
            }

            public double getLoginAward() {
                return loginAward;
            }

            public void setLoginAward(double loginAward) {
                this.loginAward = loginAward;
            }

            public double getInvaAward() {
                return invaAward;
            }

            public void setInvaAward(double invaAward) {
                this.invaAward = invaAward;
            }

            public double getInvaEachAward() {
                return invaEachAward;
            }

            public void setInvaEachAward(double invaEachAward) {
                this.invaEachAward = invaEachAward;
            }

            public double getTodayAward() {
                return todayAward;
            }

            public void setTodayAward(double todayAward) {
                this.todayAward = todayAward;
            }
        }
    }
}
