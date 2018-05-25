package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;


/**
 * Created by zc on 2018/5/25.
 */

public class UpImgBean extends BaseRes {
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private Data data;

    public class Data {
        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        String imgUrl;
    }
}
