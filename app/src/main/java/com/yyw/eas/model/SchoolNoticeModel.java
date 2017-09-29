package com.yyw.eas.model;


import android.content.Context;

import com.google.gson.Gson;
import com.yyw.eas.R;
import com.yyw.eas.bean.Cookies;
import com.yyw.eas.callback.OnLoadCallback;
import com.yyw.eas.utils.Constant;
import com.yyw.eas.utils.FileUtils;
import com.yyw.eas.utils.HttpUtils;
import com.yyw.eas.utils.LogUtils;

import org.jsoup.Connection.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SchoolNoticeModel implements ISchoolNoticeModel {

    @Override
    public void getSchoolNotice(final Context context, final int index, final OnLoadCallback onLoadCallback) {

        onLoadCallback.beforeLoad();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Gson gson = new Gson();
                String json = FileUtils.getStringFromFile(context, Constant.Response.COOKIES_FILE_NAME);
                Cookies cookies = gson.fromJson(json, Cookies.class);
                Map<String, String> cookiesMap = new HashMap<>();
                ArrayList<Cookies.Cookie> cookieArrayList = cookies.getCookies();
                for (Cookies.Cookie c : cookieArrayList) {
                    cookiesMap.put(c.getCookieKey(), c.getCookieValue());
                }
                Response response = HttpUtils.getSchoolNotice(cookiesMap, index);
                if (response != null) {
                    String body = response.body();
                    if(!body.contains(context.getResources().getString(R.string.login_educational_administration_system))){
                        // TODO 获取成功
                        onLoadCallback.onSuccess();
                    }else{
                        onLoadCallback.onFailed(Constant.Connect.ERROR);
                    }
                } else {
                    onLoadCallback.onFailed(Constant.Network.Network_ERROR);
                }
                onLoadCallback.onLoadComplete();
            }
        }).start();

    }
}
