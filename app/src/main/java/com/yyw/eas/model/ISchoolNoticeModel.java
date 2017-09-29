package com.yyw.eas.model;


import android.content.Context;

import com.yyw.eas.callback.OnLoadCallback;

public interface ISchoolNoticeModel {

    void getSchoolNotice(Context context, int index, OnLoadCallback onLoadCallback);
}
