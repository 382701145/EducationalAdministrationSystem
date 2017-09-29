package com.yyw.eas.view;


import android.content.Context;

public interface ISchoolNoticeView {

    void showLoading();

    void hideLoading();

    void onLoginSuccess();

    void onLoginFailed(int failedMessage);

    Context getContext();
}
