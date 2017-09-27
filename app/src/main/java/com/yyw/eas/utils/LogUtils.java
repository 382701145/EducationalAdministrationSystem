package com.yyw.eas.utils;

import android.util.Log;

public class LogUtils {

	private static boolean SHOW_TRACE = false;
	private static boolean SHOW_ERROR = true;
	private static boolean SHOW_DEBUG = true;
	private static final String TAG = "YYWLog";

	public LogUtils() {
	}

	public static void enableError(boolean enable) {
		SHOW_ERROR = enable;
	}

	public static void enableDebug(boolean enable) {
		SHOW_DEBUG = enable;
	}

	public static void enableTrace(boolean enable) {
		SHOW_TRACE = enable;
	}

	public static void d(String tag, String msg) {
		if (SHOW_DEBUG) {
			Log.d(tag, msg);
		}

	}

	public static void d(String msg) {
		if (SHOW_DEBUG) {
			StackTraceElement element = Thread.currentThread().getStackTrace()[3];
			Log.d(TAG, "Class: " + element.getClassName() + " Method: " + element.getMethodName() +"--"+ msg);
		}
	}

	public static void trace() {
		if (SHOW_TRACE) {
			StackTraceElement element = Thread.currentThread().getStackTrace()[3];
			Log.d(TAG, "Class: " + element.getClassName() + " Method: " + element.getMethodName());
		}

	}

	public static void e(Exception e) {
		StackTraceElement element = e.getStackTrace()[0];
		Log.e(TAG, "Caught Exception in Class: " + element.getClassName() + "\r\nMethod: " + element.getMethodName() + "()\r\nMessage: " + e.getMessage());
	}

	public static void e(String msg) {
		if (SHOW_ERROR) {
			StackTraceElement element = Thread.currentThread().getStackTrace()[3];
			Log.e(TAG, "Class: " + element.getClassName() + " Method: " + element.getMethodName() +"--"+ msg);
		}
	}
}
