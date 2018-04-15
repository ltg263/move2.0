package com.secretk.move.interactor;

public interface TopicFragmentInteractor {
    void getDataFromLocal();
    void getDataFromNet();
    void cancelFollow();
    void followNow();
}
