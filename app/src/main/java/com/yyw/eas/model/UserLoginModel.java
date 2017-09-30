package com.yyw.eas.model;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.yyw.eas.R;
import com.yyw.eas.bean.Cookies;
import com.yyw.eas.bean.LoginResponse;
import com.yyw.eas.bean.User;
import com.yyw.eas.callback.OnLoadCallback;
import com.yyw.eas.utils.Constant;
import com.yyw.eas.utils.FileUtils;
import com.yyw.eas.utils.HttpUtils;
import com.yyw.eas.utils.SPUtils;

import org.jsoup.Connection.Response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


public class UserLoginModel implements IUserLoginModel {

    private Context context;
    private OnLoadCallback callback;

    @Override
    public void login(final Context context, final User user, final OnLoadCallback callback) {
        this.context = context;
        this.callback = callback;
        LoginAsyncTask loginAsyncTask = new LoginAsyncTask(user);
        loginAsyncTask.execute();
    }


    private class LoginAsyncTask extends AsyncTask<Void, Void, Response> {

        private User user;

        LoginAsyncTask(User user) {
            this.user = user;
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            // 开始解析响应
            if (response != null) {
                String body = response.body();
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(body, LoginResponse.class);
                if (loginResponse.getIsSuccess().equals(Constant.Response.SUCCESS)) {
                    // 验证成功,保存数据
                    Map<String, String> cookiesMap = response.cookies();
                    Iterator<Map.Entry<String, String>> it = cookiesMap.entrySet().iterator();
                    Cookies cookies = new Cookies();
                    ArrayList<Cookies.Cookie> cookieList = new ArrayList<>();
                    while (it.hasNext()) {
                        Map.Entry<String, String> en = it.next();
                        String key = en.getKey();
                        String value = en.getValue();
                        cookieList.add(new Cookies.Cookie(key, value));
                    }
                    cookies.setCookies(cookieList);
                    cookies.setData(String.valueOf(System.currentTimeMillis()));
                    String cookiesJson = gson.toJson(cookies);
                    // 保存到本地
                    FileUtils.putStringToFile(context, Constant.Response.COOKIES_FILE_NAME, cookiesJson);
                    SPUtils.setPrefParams(context, Constant.User.USERNAME, user.getUsername());
                    SPUtils.setPrefParams(context, Constant.User.PASSWORD, user.getPassword());

                    StudentNameAsyncTask studentNameAsyncTask = new StudentNameAsyncTask();
                    studentNameAsyncTask.execute(cookiesMap);
                } else {
                    if (loginResponse.getMessage().equals(context.getString(R.string.invalid_username))) {
                        callback.onFailed(Constant.Login.INVALID_USERNAME);
                    } else if (loginResponse.getMessage().equals(context.getString(R.string.invalid_password))) {
                        callback.onFailed(Constant.Login.INVALID_PASSWORD);
                    }
                }
            } else {// 网络问题
                callback.onFailed(Constant.Network.Network_ERROR);
            }
            callback.onLoadComplete();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            callback.beforeLoad();
        }

        @Override
        protected Response doInBackground(Void... voids) {
            return HttpUtils.getLoginResponse(user);
        }
    }


    private class StudentNameAsyncTask extends AsyncTask<Map<String, String>, Void, String> {

        @Override
        protected void onPostExecute(String studentName) {
            super.onPostExecute(studentName);
            if (studentName == null || studentName.equals("")) {
                studentName = context.getResources().getString(R.string.hello) + context.getResources().getString(R.string.student);
            } else {
                studentName = context.getResources().getString(R.string.hello) + studentName;
            }
            SPUtils.setPrefParams(context, Constant.StudentName.STUDENT_NAME, studentName);
            callback.onSuccess(null);
        }

        @Override
        protected String doInBackground(Map<String, String>... params) {
            Map<String, String> cookiesMap = params[0];
            return HttpUtils.getStudentName(cookiesMap);
        }
    }
}
