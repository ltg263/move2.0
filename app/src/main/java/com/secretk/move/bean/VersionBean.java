package com.secretk.move.bean;

import com.secretk.move.bean.base.BaseRes;

public class VersionBean extends BaseRes{
    /**
     * data : {"ver":"4.6.0","upgrade":1,"force":1,"upgradeUrl":"http://m.xueleyun.com/download/android","guideUrl":"http://m.xueleyun.com/app/xueleyun_3100_f26a2a71e45074a289a19cbe0482294b.apk","upExplain":" 1.空间优化\r\n\r\n（1）学校自创空间新增自定义空间，教师、学生可通过申请加入\r\n\r\n（2）自创空间的管理新增成员管理、申请管理、操作记录\r\n\r\n 2.修复已知问题，优化用户体验\r\n"}
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
         * ver : 4.6.0
         * upgrade : 1
         * force : 1
         * upgradeUrl : http://m.xueleyun.com/download/android
         * guideUrl : http://m.xueleyun.com/app/xueleyun_3100_f26a2a71e45074a289a19cbe0482294b.apk
         * upExplain :  1.空间优化

         （1）学校自创空间新增自定义空间，教师、学生可通过申请加入

         （2）自创空间的管理新增成员管理、申请管理、操作记录

         2.修复已知问题，优化用户体验

         */

        private String ver;
        private int upgrade;
        private int force;
        private String upgradeUrl;
        private String guideUrl;
        private String upExplain;

        public String getVer() {
            return ver;
        }

        public void setVer(String ver) {
            this.ver = ver;
        }

        public int getUpgrade() {
            return upgrade;
        }

        public void setUpgrade(int upgrade) {
            this.upgrade = upgrade;
        }

        public int getForce() {
            return force;
        }

        public void setForce(int force) {
            this.force = force;
        }

        public String getUpgradeUrl() {
            return upgradeUrl;
        }

        public void setUpgradeUrl(String upgradeUrl) {
            this.upgradeUrl = upgradeUrl;
        }

        public String getGuideUrl() {
            return guideUrl;
        }

        public void setGuideUrl(String guideUrl) {
            this.guideUrl = guideUrl;
        }

        public String getUpExplain() {
            return upExplain;
        }

        public void setUpExplain(String upExplain) {
            this.upExplain = upExplain;
        }
    }

    /*public String name;
    public String version;
    public String versionShort;
    public String changelog;
    public int build;
    public String installUrl;
    public String install_url;
    public String direct_install_url;

    @Override
    public String toString() {
        return "name=" + name + " versionShort =" + versionShort + " build=" + build + " installUrl=" + installUrl+" changelog="+changelog;
    }*/

}
