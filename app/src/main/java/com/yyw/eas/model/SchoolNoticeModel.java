package com.yyw.eas.model;


import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.yyw.eas.R;
import com.yyw.eas.bean.Article;
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

        SchoolNoticeAsyncTask schoolNoticeAsyncTask = new SchoolNoticeAsyncTask(context, onLoadCallback);
        schoolNoticeAsyncTask.execute(index);
    }

    private class SchoolNoticeAsyncTask extends AsyncTask<Integer, Void, Response> {

        private Context context;
        private OnLoadCallback onLoadCallback;
        private Gson gson;

        SchoolNoticeAsyncTask(Context context, OnLoadCallback onLoadCallback) {
            this.context = context;
            this.onLoadCallback = onLoadCallback;
            this.gson = new Gson();
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            if (response != null) {
                String body = response.body();
//                String body = "{\"total\":127,\"rows\":[{\"A_ID\":223,\"A_Cat\":37,\"A_Title\":\"关于使用学生管理系统的通知1\",\"A_TitleSub\":\"编码规则\",\"A_Photo\":\"\",\"A_Content\":\"学生管理系统中，部分基础数据需要按一定的编码规则统一编码，现规定编码规则下\",\"A_DateTime\":\"11111111\",\"A_Auther\":\"mrliu\",\"A_Editor\":\"mrliu\",\"A_KeyCode\":\"11111\",\"A_Hits\":157,\"A_BeginTime\":\"12345\",\"A_EndTime\":\"321\",\"A_OnTop\":false,\"A_Deleted\":false,\"A_IssueTo\":\"022222\",\"A_HomePage\":true,\"A_Group\":\"802\",\"A_Reader\":\",glytz,\",\"A_Passed\":true,\"A_Elite\":false},{\"A_ID\":223,\"A_Cat\":37,\"A_Title\":\"关于使用学生管理系统的通知1\",\"A_TitleSub\":\"编码规则\",\"A_Photo\":\"\",\"A_Content\":\"学生管理系统中，部分基础数据需要按一定的编码规则统一编码，现规定编码规则下\",\"A_DateTime\":\"11111111\",\"A_Auther\":\"mrliu\",\"A_Editor\":\"mrliu\",\"A_KeyCode\":\"11111\",\"A_Hits\":157,\"A_BeginTime\":\"12345\",\"A_EndTime\":\"321\",\"A_OnTop\":false,\"A_Deleted\":false,\"A_IssueTo\":\"022222\",\"A_HomePage\":true,\"A_Group\":\"802\",\"A_Reader\":\",glytz,\",\"A_Passed\":true,\"A_Elite\":false}]}";
                if (!body.contains(context.getResources().getString(R.string.login_educational_administration_system))) {
                    // TODO 获取成功,保存到本地
                    LogUtils.d(body);
                    Article article = gson.fromJson(body, Article.class);
                    if (article != null) {
                        onLoadCallback.onSuccess(article);
                    }else{
                        onLoadCallback.onFailed(Constant.Connect.ERROR);
                    }
                } else {
                    onLoadCallback.onFailed(Constant.Connect.ERROR);
                }
            } else {
                onLoadCallback.onFailed(Constant.Network.Network_ERROR);
            }
            onLoadCallback.onLoadComplete();
        }

        @Override
        protected Response doInBackground(Integer... integers) {

            String json = FileUtils.getStringFromFile(context, Constant.Response.COOKIES_FILE_NAME);
            Cookies cookies = gson.fromJson(json, Cookies.class);
            Map<String, String> cookiesMap = new HashMap<>();
            ArrayList<Cookies.Cookie> cookieArrayList = cookies.getCookies();
            for (Cookies.Cookie cookie : cookieArrayList) {
                cookiesMap.put(cookie.getCookieKey(), cookie.getCookieValue());
            }
            return HttpUtils.getSchoolNotice(cookiesMap, integers[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onLoadCallback.beforeLoad();
        }
    }
}
