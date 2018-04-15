package com.secretk.move.interactor;

/**
 * Created by zc on 2018/4/6.
 */

public interface MainInteractor {
    void downLoadApk(String url);

    void NetWorkVersion();

    int localVerison();

    void install(String apkPath);
}
