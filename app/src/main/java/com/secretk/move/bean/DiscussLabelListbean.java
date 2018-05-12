package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

public class DiscussLabelListbean extends BaseRes {
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private Data data;

    public class Data {
        public List<TagList> getTagList() {
            return tagList;
        }

        public void setTagList(List<TagList> tagList) {
            this.tagList = tagList;
        }

        private List<TagList> tagList;


    }


    public static class TagList {
        public TagList() {
            this.selected = false;
        }

        public String getTagId() {
            return tagId;
        }

        public void setTagId(String tagId) {
            this.tagId = tagId;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        String tagId;
        String tagName;
        String memo;

        public Boolean getSelected() {
            return selected;
        }

        public void setSelected(Boolean selected) {
            this.selected = selected;
        }

        Boolean selected;
    }
}
