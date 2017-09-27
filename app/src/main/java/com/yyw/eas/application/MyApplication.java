package com.yyw.eas.application;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    //返回
    public static Context getContextObject(){
        return context;
    }
}
