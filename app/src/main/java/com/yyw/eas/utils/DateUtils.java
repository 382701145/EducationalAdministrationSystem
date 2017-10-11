package com.yyw.eas.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/10/11.
 */

public class DateUtils {


    public static String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    public static String getTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        java.util.Date dt = new Date(Long.valueOf(time));
        String sDateTime = sdf.format(dt);
        return sDateTime;
    }
}
