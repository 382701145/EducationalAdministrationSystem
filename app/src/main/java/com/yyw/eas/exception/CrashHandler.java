package com.yyw.eas.exception;


import android.content.Context;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler INSTANCE ;
    private Context context;

    private CrashHandler(){

    }

    public static synchronized CrashHandler getInstance(){
        if (INSTANCE == null)
            INSTANCE = new CrashHandler();
        return INSTANCE;
    }

    public void init(Context context){
        this.context = context;
    }


    public void uncaughtException(Thread arg0, Throwable arg1) {
        System.out.println("EAS KO...");
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
