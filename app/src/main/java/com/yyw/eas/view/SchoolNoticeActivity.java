package com.yyw.eas.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.yyw.eas.presenter.ISchoolNoticePresenter;
import com.yyw.eas.presenter.SchoolNoticePresenter;

public class SchoolNoticeActivity extends Activity implements ISchoolNoticeView{

    private ISchoolNoticePresenter schoolNoticePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        schoolNoticePresenter = new SchoolNoticePresenter(this);
        schoolNoticePresenter.getSchoolNotice(1);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailed(int failedMessage) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
