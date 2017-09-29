package com.yyw.eas.view;


import android.content.Context;

import com.yyw.eas.bean.Article;

public interface ISchoolNoticeView {

    void showLoading();

    void hideLoading();

    void onLoginSuccess(Article article);

    void onLoginFailed(int failedMessage);

    Context getContext();
}
