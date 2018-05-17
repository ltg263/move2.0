package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

public class SearchedBean  extends BaseRes {
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private Data data;
    public class Data {
        public List<Projects> getProjects() {
            return projects;
        }

        public void setProjects(List<Projects> projects) {
            this.projects = projects;
        }

        private List<Projects> projects;




    }
    public class Projects{
        public int getFollowStatus() {
            return followStatus;
        }

        public void setFollowStatus(int followStatus) {
            this.followStatus = followStatus;
        }

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

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

        public int getFollowerNum() {
            return followerNum;
        }

        public void setFollowerNum(int followerNum) {
            this.followerNum = followerNum;
        }

        int followStatus;
  int projectId;
  String projectIcon;
  String projectCode;
  String projectEnglishName;
  String projectChineseName;
  String projectSignature;
  int followerNum;
    }
}
