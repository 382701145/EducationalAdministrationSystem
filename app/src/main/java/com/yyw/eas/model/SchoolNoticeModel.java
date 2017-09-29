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

import org.jsoup.Connection;
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
                if (!body.contains(context.getResources().getString(R.string.login_educational_administration_system))) {
                    // TODO 获取成功,保存到本地
                    Article article = gson.fromJson(body, Article.class);
                    onLoadCallback.onSuccess(article);
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
