package com.yyw.eas.application;

import android.app.Application;

import com.yyw.eas.exception.CrashHandler;

public class CrashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler handler = CrashHandler.getInstance();
        handler.init(getApplicationContext());
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }
}
