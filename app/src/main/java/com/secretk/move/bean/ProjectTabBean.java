package com.secretk.move.bean;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/7/25 17:38
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class ProjectTabBean {

    /**
     * status : null
     * msg : Success
     * code : 0
     * reason : null
     * fromuri : null
     * token : null
     * data : {"tabs":[{"tabId":1,"tabOrderNumber":1,"tabTitle":"全部"},{"tabId":2,"tabOrderNumber":2,"tabTitle":"关注"}]}
     * serverDatetime : 1532678369540
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TabsBean> tabs;

        public List<TabsBean> getTabs() {
            return tabs;
        }

        public void setTabs(List<TabsBean> tabs) {
            this.tabs = tabs;
        }

        public static class TabsBean {
            /**
             * tabId : 1
             * tabOrderNumber : 1
             * tabTitle : 全部
             */

            private int tabId;
            private int tabOrderNumber;
            private int isDefaultOpen;
            private String tabTitle;

            public int getIsDefaultOpen() {
                return isDefaultOpen;
            }

            public void setIsDefaultOpen(int isDefaultOpen) {
                this.isDefaultOpen = isDefaultOpen;
            }

            public int getTabId() {
                return tabId;
            }

            public void setTabId(int tabId) {
                this.tabId = tabId;
            }

            public int getTabOrderNumber() {
                return tabOrderNumber;
            }

            public void setTabOrderNumber(int tabOrderNumber) {
                this.tabOrderNumber = tabOrderNumber;
            }

            public String getTabTitle() {
                return tabTitle;
            }

            public void setTabTitle(String tabTitle) {
                this.tabTitle = tabTitle;
            }
        }
    }
}
