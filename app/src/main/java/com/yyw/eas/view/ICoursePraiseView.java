package com.yyw.eas.view;


import android.content.Context;

import com.yyw.eas.bean.Course;

import java.util.List;


public interface ICoursePraiseView {

    void onSuccess(List<Course> courseList);

    void onLoginFailed(int failedMessage);

    void showLoading();

    void hideLoading();

    void flushView();

    Context getContext();
}
