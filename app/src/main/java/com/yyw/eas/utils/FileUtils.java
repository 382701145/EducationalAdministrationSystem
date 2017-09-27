package com.yyw.eas.utils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    /**
     * 保存字符串到file目录中
     *
     * @param context
     * @param name
     * @param data
     * @return
     */
    public static boolean putStringTFile(Context context, String name, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 从file目录中获取字符串
     *
     * @param context
     * @param name
     * @return
     */
    public static String getStringFromFile(Context context, String name) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(name);
            byte[] buff = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder("");
            while ((hasRead = fileInputStream.read(buff)) > 0) {
                sb.append(new String(buff, 0, hasRead));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
