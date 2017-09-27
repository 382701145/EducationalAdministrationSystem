package com.yyw.eas.model;

import android.content.Context;

import com.yyw.eas.bean.User;
import com.yyw.eas.callback.OnLoadCallback;

public interface IUserLoginModel {

    void login(Context context, User user, OnLoadCallback callback);

}
