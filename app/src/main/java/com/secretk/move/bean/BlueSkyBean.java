package com.secretk.move.bean;

import android.util.Log;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * Created by zc on 2018/4/27.
 */

public class BlueSkyBean extends BaseRes {
    private String status;

    private String reason;

    private String fromuri;

    private String token;

    private Data data;


    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

    public void setFromuri(String fromuri) {
        this.fromuri = fromuri;
    }

    public String getFromuri() {
        return this.fromuri;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public class Data {
        private List<RankList> rankList;

        public void setRankList(List<RankList> rankList) {
            this.rankList = rankList;
        }

        public List<RankList> getRankList() {
            return this.rankList;
        }

    }

    public static class RankList {
        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        int projectId;
        String projectIcon;
        String projectCode;
        String projectEnglishName;
        String projectChineseName;
        String projectSignature;

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
            Log.e("jyh_position","position="+position);
        }

        String position;

        public int getFollowStatus() {
            return followStatus;
        }

        public void setFollowStatus(int followStatus) {
            this.followStatus = followStatus;
        }

        String totalScore;
        String followerNum;
        int followStatus;



        public String getProjectIcon() {
            return projectIcon;
        }

        public void setProjectIcon(String projectIcon) {
            this.projectIcon = projectIcon;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getProjectEnglishName() {
            return projectEnglishName;
        }

        public void setProjectEnglishName(String projectEnglishName) {
            this.projectEnglishName = projectEnglishName;
        }

        public String getProjectChineseName() {
            return projectChineseName;
        }

        public void setProjectChineseName(String projectChineseName) {
            this.projectChineseName = projectChineseName;
        }

        public String getProjectSignature() {
            return projectSignature;
        }

        public void setProjectSignature(String projectSignature) {
            this.projectSignature = projectSignature;
        }

        public String getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(String totalScore) {
            this.totalScore = totalScore;
        }

        public String getFollowerNum() {
            return followerNum;
        }

        public void setFollowerNum(String followerNum) {
            this.followerNum = followerNum;
        }


    }
}
