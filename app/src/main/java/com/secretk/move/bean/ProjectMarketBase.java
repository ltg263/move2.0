package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/8/1 11:36
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class ProjectMarketBase extends BaseRes{

    /**
     * status : null
     * reason : null
     * fromuri : null
     * token : null
     * data : {"TransactionPairResponse":{"rowCount":419,"rows":[{"exchangeDisplayName":"U-COIN","transactionPairId":18169,"exchangeId":295,"mainCode":"BTC","coinpair":"UT","exchangeName":"uncoinex"},{"exchangeDisplayName":"BuyBitcoin","transactionPairId":18087,"exchangeId":293,"mainCode":"BTC","coinpair":"INR","exchangeName":"buybitcoin"},{"exchangeDisplayName":"AAcoin","transactionPairId":18105,"exchangeId":292,"mainCode":"BTC","coinpair":"USDT","exchangeName":"aacoin"},{"exchangeDisplayName":"ABCC","transactionPairId":18304,"exchangeId":291,"mainCode":"BTC","coinpair":"USDT","exchangeName":"abcc"},{"exchangeDisplayName":"ezBtc","transactionPairId":18051,"exchangeId":289,"mainCode":"BTC","coinpair":"CAD","exchangeName":"ezbtc"},{"exchangeDisplayName":"BigONE","transactionPairId":17864,"exchangeId":287,"mainCode":"BTC","coinpair":"BNC","exchangeName":"bigone"},{"exchangeDisplayName":"BigONE","transactionPairId":17836,"exchangeId":287,"mainCode":"BTC","coinpair":"USDT","exchangeName":"bigone"},{"exchangeDisplayName":"Coineal","transactionPairId":17997,"exchangeId":285,"mainCode":"BTC","coinpair":"USDT","exchangeName":"coineal"},{"exchangeDisplayName":"Coinmake","transactionPairId":17803,"exchangeId":284,"mainCode":"BTC","coinpair":"USDT","exchangeName":"coinmake"},{"exchangeDisplayName":"BitKonan","transactionPairId":17994,"exchangeId":282,"mainCode":"BTC","coinpair":"USD","exchangeName":"bitkonan"}],"pageSize":42,"rowsPerPage":10,"curPageNum":1,"queryParameters":"mainCode=BTC","pageCount":42,"hasNext":true,"nextPage":2}}
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
         * TransactionPairResponse : {"rowCount":419,"rows":[{"exchangeDisplayName":"U-COIN","transactionPairId":18169,"exchangeId":295,"mainCode":"BTC","coinpair":"UT","exchangeName":"uncoinex"},{"exchangeDisplayName":"BuyBitcoin","transactionPairId":18087,"exchangeId":293,"mainCode":"BTC","coinpair":"INR","exchangeName":"buybitcoin"},{"exchangeDisplayName":"AAcoin","transactionPairId":18105,"exchangeId":292,"mainCode":"BTC","coinpair":"USDT","exchangeName":"aacoin"},{"exchangeDisplayName":"ABCC","transactionPairId":18304,"exchangeId":291,"mainCode":"BTC","coinpair":"USDT","exchangeName":"abcc"},{"exchangeDisplayName":"ezBtc","transactionPairId":18051,"exchangeId":289,"mainCode":"BTC","coinpair":"CAD","exchangeName":"ezbtc"},{"exchangeDisplayName":"BigONE","transactionPairId":17864,"exchangeId":287,"mainCode":"BTC","coinpair":"BNC","exchangeName":"bigone"},{"exchangeDisplayName":"BigONE","transactionPairId":17836,"exchangeId":287,"mainCode":"BTC","coinpair":"USDT","exchangeName":"bigone"},{"exchangeDisplayName":"Coineal","transactionPairId":17997,"exchangeId":285,"mainCode":"BTC","coinpair":"USDT","exchangeName":"coineal"},{"exchangeDisplayName":"Coinmake","transactionPairId":17803,"exchangeId":284,"mainCode":"BTC","coinpair":"USDT","exchangeName":"coinmake"},{"exchangeDisplayName":"BitKonan","transactionPairId":17994,"exchangeId":282,"mainCode":"BTC","coinpair":"USD","exchangeName":"bitkonan"}],"pageSize":42,"rowsPerPage":10,"curPageNum":1,"queryParameters":"mainCode=BTC","pageCount":42,"hasNext":true,"nextPage":2}
         */

        private TransactionPairResponseBean TransactionPairResponse;

        public TransactionPairResponseBean getTransactionPairResponse() {
            return TransactionPairResponse;
        }

        public void setTransactionPairResponse(TransactionPairResponseBean TransactionPairResponse) {
            this.TransactionPairResponse = TransactionPairResponse;
        }

        public static class TransactionPairResponseBean {
            /**
             * rowCount : 419
             * rows : [{"exchangeDisplayName":"U-COIN","transactionPairId":18169,"exchangeId":295,"mainCode":"BTC","coinpair":"UT","exchangeName":"uncoinex"},{"exchangeDisplayName":"BuyBitcoin","transactionPairId":18087,"exchangeId":293,"mainCode":"BTC","coinpair":"INR","exchangeName":"buybitcoin"},{"exchangeDisplayName":"AAcoin","transactionPairId":18105,"exchangeId":292,"mainCode":"BTC","coinpair":"USDT","exchangeName":"aacoin"},{"exchangeDisplayName":"ABCC","transactionPairId":18304,"exchangeId":291,"mainCode":"BTC","coinpair":"USDT","exchangeName":"abcc"},{"exchangeDisplayName":"ezBtc","transactionPairId":18051,"exchangeId":289,"mainCode":"BTC","coinpair":"CAD","exchangeName":"ezbtc"},{"exchangeDisplayName":"BigONE","transactionPairId":17864,"exchangeId":287,"mainCode":"BTC","coinpair":"BNC","exchangeName":"bigone"},{"exchangeDisplayName":"BigONE","transactionPairId":17836,"exchangeId":287,"mainCode":"BTC","coinpair":"USDT","exchangeName":"bigone"},{"exchangeDisplayName":"Coineal","transactionPairId":17997,"exchangeId":285,"mainCode":"BTC","coinpair":"USDT","exchangeName":"coineal"},{"exchangeDisplayName":"Coinmake","transactionPairId":17803,"exchangeId":284,"mainCode":"BTC","coinpair":"USDT","exchangeName":"coinmake"},{"exchangeDisplayName":"BitKonan","transactionPairId":17994,"exchangeId":282,"mainCode":"BTC","coinpair":"USD","exchangeName":"bitkonan"}]
             * pageSize : 42
             * rowsPerPage : 10
             * curPageNum : 1
             * queryParameters : mainCode=BTC
             * pageCount : 42
             * hasNext : true
             * nextPage : 2
             */

            private int rowCount;
            private int pageSize;
            private int rowsPerPage;
            private int curPageNum;
            private String queryParameters;
            private int pageCount;
            private boolean hasNext;
            private int nextPage;
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

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
            }

            public boolean isHasNext() {
                return hasNext;
            }

            public void setHasNext(boolean hasNext) {
                this.hasNext = hasNext;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public List<RowsBean> getRows() {
                return rows;
            }

            public void setRows(List<RowsBean> rows) {
                this.rows = rows;
            }

            public static class RowsBean {
                /**
                 * exchangeDisplayName : U-COIN
                 * transactionPairId : 18169
                 * exchangeId : 295
                 * mainCode : BTC
                 * coinpair : UT
                 * exchangeName : uncoinex
                 */

                private String exchangeDisplayName;
                private int transactionPairId;
                private int exchangeId;
                private String mainCode;
                private String coinpair;
                private String exchangeName;

                private boolean isOk=false;
                private String strError;
                /**
                 * timestamps : 1533106760540
                 * last : 7583.1
                 * high : 8145.1
                 * low : 7469.5
                 * bid : 7583.1
                 * ask : 7583.5
                 * vol : 53584.05226534
                 * base_volume : 4.0633322673329973E8
                 * change_daily : -0.069
                 * market : bitfinex
                 * symbol_name : bitcoin
                 * symbol_pair : BTC_USD
                 * rating : 3
                 * has_kline : true
                 * usd_rate : 1
                 */

                private long timestamps;
                private double last;
                private double high;
                private double low;
                private double bid;
                private double ask;
                private double vol;
                private double base_volume;
                private double change_daily;
                private double baseVolume;
                private double changeDaily;
                private double usdRate;
                private String market;
                private String symbol_name;
                private String symbol_pair;
                private int rating;
                private boolean has_kline;
                private double usd_rate;


                public void setBaseVolume(double baseVolume) {
                    this.baseVolume = baseVolume;
                }

                public void setChangeDaily(double changeDaily) {
                    this.changeDaily = changeDaily;
                }

                public void setUsdRate(double usdRate) {
                    this.usdRate = usdRate;
                }

                public double getBaseVolume() {
                    return baseVolume;
                }

                public double getChangeDaily() {
                    return changeDaily;
                }

                public double getUsdRate() {
                    return usdRate;
                }

                public String getStrError() {
                    return strError;
                }

                public void setStrError(String strError) {
                    this.strError = strError;
                }

                public boolean isOk() {
                    return isOk;
                }

                public void setOk(boolean ok) {
                    isOk = ok;
                }

                public String getExchangeDisplayName() {
                    return exchangeDisplayName;
                }

                public void setExchangeDisplayName(String exchangeDisplayName) {
                    this.exchangeDisplayName = exchangeDisplayName;
                }

                public int getTransactionPairId() {
                    return transactionPairId;
                }

                public void setTransactionPairId(int transactionPairId) {
                    this.transactionPairId = transactionPairId;
                }

                public int getExchangeId() {
                    return exchangeId;
                }

                public void setExchangeId(int exchangeId) {
                    this.exchangeId = exchangeId;
                }

                public String getMainCode() {
                    return mainCode;
                }

                public void setMainCode(String mainCode) {
                    this.mainCode = mainCode;
                }

                public String getCoinpair() {
                    return coinpair;
                }

                public void setCoinpair(String coinpair) {
                    this.coinpair = coinpair;
                }

                public String getExchangeName() {
                    return exchangeName;
                }

                public void setExchangeName(String exchangeName) {
                    this.exchangeName = exchangeName;
                }

                public long getTimestamps() {
                    return timestamps;
                }

                public void setTimestamps(long timestamps) {
                    this.timestamps = timestamps;
                }

                public double getLast() {
                    return last;
                }

                public void setLast(double last) {
                    this.last = last;
                }

                public double getHigh() {
                    return high;
                }

                public void setHigh(double high) {
                    this.high = high;
                }

                public double getLow() {
                    return low;
                }

                public void setLow(double low) {
                    this.low = low;
                }

                public double getBid() {
                    return bid;
                }

                public void setBid(double bid) {
                    this.bid = bid;
                }

                public double getAsk() {
                    return ask;
                }

                public void setAsk(double ask) {
                    this.ask = ask;
                }

                public double getVol() {
                    return vol;
                }

                public void setVol(double vol) {
                    this.vol = vol;
                }

                public double getBase_volume() {
                    return base_volume;
                }

                public void setBase_volume(double base_volume) {
                    this.base_volume = base_volume;
                }

                public double getChange_daily() {
                    return change_daily;
                }

                public void setChange_daily(double change_daily) {
                    this.change_daily = change_daily;
                }

                public String getMarket() {
                    return market;
                }

                public void setMarket(String market) {
                    this.market = market;
                }

                public String getSymbol_name() {
                    return symbol_name;
                }

                public void setSymbol_name(String symbol_name) {
                    this.symbol_name = symbol_name;
                }

                public String getSymbol_pair() {
                    return symbol_pair;
                }

                public void setSymbol_pair(String symbol_pair) {
                    this.symbol_pair = symbol_pair;
                }

                public int getRating() {
                    return rating;
                }

                public void setRating(int rating) {
                    this.rating = rating;
                }

                public boolean isHas_kline() {
                    return has_kline;
                }

                public void setHas_kline(boolean has_kline) {
                    this.has_kline = has_kline;
                }

                public double getUsd_rate() {
                    return usd_rate;
                }

                public void setUsd_rate(double usd_rate) {
                    this.usd_rate = usd_rate;
                }
            }
        }
    }
}
