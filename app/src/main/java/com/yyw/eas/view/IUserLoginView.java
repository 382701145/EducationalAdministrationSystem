package com.yyw.eas.view;

import android.content.Context;

public interface IUserLoginView {

    String getUserName();

    String getPassword();

    void onLoginSuccess();

    void onLoginFailed(int failedMessage);

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    Context getContext();

}
