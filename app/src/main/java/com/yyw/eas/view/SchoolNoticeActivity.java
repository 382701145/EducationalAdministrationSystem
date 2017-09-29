package com.yyw.eas.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.yyw.eas.R;
import com.yyw.eas.bean.Article;
import com.yyw.eas.presenter.ISchoolNoticePresenter;
import com.yyw.eas.presenter.SchoolNoticePresenter;

public class SchoolNoticeActivity extends Activity implements ISchoolNoticeView {

    private ISchoolNoticePresenter schoolNoticePresenter;
    private int defaultIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_notice);
        schoolNoticePresenter = new SchoolNoticePresenter(this);
        schoolNoticePresenter.getSchoolNotice(defaultIndex);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onLoginSuccess(Article article) {



    }

    @Override
    public void onLoginFailed(int failedMessage) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
