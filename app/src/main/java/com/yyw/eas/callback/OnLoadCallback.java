package com.yyw.eas.callback;

public interface OnLoadCallback {

    void beforeLoad();
    <T> void onSuccess(T t);
    void onFailed(int failedMessage);
    void onLoadComplete();
}
