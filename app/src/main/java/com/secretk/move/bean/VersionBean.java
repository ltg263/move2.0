package com.secretk.move.bean;

public class VersionBean {
    public String name;
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
    }
}
