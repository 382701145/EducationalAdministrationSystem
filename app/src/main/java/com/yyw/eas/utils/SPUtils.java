package com.yyw.eas.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

public class SPUtils {

    public static void setPrefParams(Context context, String key, Object value) {

        final SharedPreferences settings = context.getSharedPreferences(Constant.FileName.SP_FILE_NAME, Context.MODE_PRIVATE);

        if (value instanceof String) {
            settings.edit().putString(key, (String) value).apply();
        } else if (value instanceof Boolean) {
            settings.edit().putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof Integer) {
            settings.edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Float) {
            settings.edit().putFloat(key, (Float) value).apply();
        } else if (value instanceof Long) {
            settings.edit().putLong(key, (Long) value).apply();
        }
    }

    public static Object getPrefParams(Context context, String key, Object defaultValue) {

        String type = defaultValue.getClass().getSimpleName();
        final SharedPreferences settings = context.getSharedPreferences(Constant.FileName.SP_FILE_NAME, Context.MODE_PRIVATE);
        switch (type) {
            case "String":
                return settings.getString(key, (String) defaultValue);
            case "Boolean":
                return settings.getBoolean(key, (Boolean) defaultValue);
            case "Integer":
                return settings.getInt(key, (Integer) defaultValue);
            case "Float":
                return settings.getFloat(key, (Float) defaultValue);
            case "Long":
                return settings.getLong(key, (Long) defaultValue);
            default:
                return null;
        }
    }

    public static void setPrefStringSet(Context context, final String key, final Set<String> value) {
        final SharedPreferences settings = context.getSharedPreferences(Constant.FileName.SP_FILE_NAME, Context.MODE_PRIVATE);
        settings.edit().putStringSet(key, value).apply();
    }

    public static Set<String> getPrefStringSet(Context context, final String key, final Set<String> defaultValue) {
        final SharedPreferences settings = context.getSharedPreferences(Constant.FileName.SP_FILE_NAME, Context.MODE_PRIVATE);
        return settings.getStringSet(key, defaultValue);
    }

    public static boolean hasKey(Context context, final String key) {
        return context.getSharedPreferences(Constant.FileName.SP_FILE_NAME, Context.MODE_PRIVATE).contains(key);
    }
}

