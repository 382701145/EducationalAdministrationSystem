package com.yyw.eas.view;


import android.content.Context;

import com.yyw.eas.bean.Course;

import java.util.List;
import java.util.Map;


public interface ICoursePraiseView {

    void onSuccess(Map<String, List<Course>> courseMap);

    void onLoginFailed(int failedMessage);

    void showLoading();

    void hideLoading();

    void flushView();

    Context getContext();
}
