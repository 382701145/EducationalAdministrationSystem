package com.yyw.eas.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/10/11.
 */

public class StringUtils {


    public static String getNumberFromString(String str) {
        String regEx = "[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("").trim();
    }

}
