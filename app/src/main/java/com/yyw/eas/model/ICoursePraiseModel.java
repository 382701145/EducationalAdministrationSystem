package com.yyw.eas.model;

import android.content.Context;

import com.yyw.eas.callback.OnLoadCallback;

public interface ICoursePraiseModel {

    void getCourseId(Context context, OnLoadCallback onLoadCallback);

    void getCourseInfo(Context context, int index, OnLoadCallback onLoadCallback);

}
