package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/7/31 10:32
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class HotProjectAndHotUserBean extends BaseRes{

    /**
     * data : {"projects":[{"projectChineseName":"1","projectIcon":"http://pic.qufen.top/projects20180620140738671624"},{"projectChineseName":"黑币","projectIcon":"https://pic.qufen.top/projects20180628194833517.png"},{"projectChineseName":"1","projectIcon":"1"},{"projectChineseName":"hhhhhhj","projectIcon":"https://pic.qufen.top/projects20180628201243555.png"}],"users":[{"icon":"https://pic.qufen.top/Avatar3.png","userName":"23445"},{"icon":"https://pic.qufen.top/idcard201807101926187942500.png ","userName":"区分者_007"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ProjectsBean> projects;
        private List<UsersBean> users;

        public List<ProjectsBean> getProjects() {
            return projects;
        }

        public void setProjects(List<ProjectsBean> projects) {
            this.projects = projects;
        }

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class ProjectsBean {
            /**
             * projectChineseName : 1
             * projectIcon : http://pic.qufen.top/projects20180620140738671624
             */

            private String projectChineseName;
            private String projectIcon;
            private int projectId;

            public int getProjectId() {
                return projectId;
            }

            public void setProjectId(int projectId) {
                this.projectId = projectId;
            }

            public String getProjectChineseName() {
                return projectChineseName;
            }

            public void setProjectChineseName(String projectChineseName) {
                this.projectChineseName = projectChineseName;
            }

            public String getProjectIcon() {
                return projectIcon;
            }

            public void setProjectIcon(String projectIcon) {
                this.projectIcon = projectIcon;
            }
        }

        public static class UsersBean {
            /**
             * icon : https://pic.qufen.top/Avatar3.png
             * userName : 23445
             */

            private String icon;
            private String userName;
            private int userId;

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getUserId() {
                return userId;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
