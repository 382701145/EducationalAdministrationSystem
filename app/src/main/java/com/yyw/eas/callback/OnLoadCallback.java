package com.yyw.eas.callback;

public interface OnLoadCallback {

    void beforeLoad();
    void onSuccess();
    void onFailed(int failedMessage);
    void onLoadComplete();
}
