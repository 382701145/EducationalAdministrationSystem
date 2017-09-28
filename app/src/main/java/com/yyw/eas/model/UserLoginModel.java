package com.yyw.eas.model;

import android.content.Context;

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

    @Override
    public void login(final Context context, final User user, final OnLoadCallback callback) {

        callback.beforeLoad();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Response response = HttpUtils.getLoginResponse(user);
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
                        FileUtils.putStringTFile(context, Constant.Response.COOKIES_FILE_NAME, cookiesJson);
                        SPUtils.setPrefParams(context, Constant.User.USERNAME, user.getUsername());
                        SPUtils.setPrefParams(context, Constant.User.PASSWORD, user.getPassword());
                        String studentName = HttpUtils.getStudentName(cookiesMap);
                        if (studentName == null || studentName.equals("")) {
                            studentName = context.getResources().getString(R.string.student) + ",你好!";
                        } else {
                            studentName = studentName + ",你好!";
                        }
                        SPUtils.setPrefParams(context, Constant.StudentName.STUDENT_NAME, studentName);
                        callback.onSuccess();
                    } else {
                        callback.onLoadComplete();
                        if (loginResponse.getMessage().equals(context.getString(R.string.invalid_username))) {
                            callback.onFailed(Constant.Login.INVALID_USERNAME);
                        } else if (loginResponse.getMessage().equals(context.getString(R.string.invalid_password))) {
                            callback.onFailed(Constant.Login.INVALID_PASSWORD);
                        }
                    }
                } else {// 网络问题
                    callback.onLoadComplete();
                    callback.onFailed(Constant.Network.Network_ERROR);
                }
            }
        }).start();
    }
}
