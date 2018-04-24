package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

/**
 * 作者： litongge
 * 时间：  2018/4/23 19:42
 * 邮箱；ltg263@126.com
 * 描述：
 */

public class DynamicValidateCodeSend extends BaseRes{


    /**
     * data : {"dynamicCode":"143206"}
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
         * dynamicCode : 143206
         */

        private String dynamicCode;

        public String getDynamicCode() {
            return dynamicCode;
        }

        public void setDynamicCode(String dynamicCode) {
            this.dynamicCode = dynamicCode;
        }
    }
}
