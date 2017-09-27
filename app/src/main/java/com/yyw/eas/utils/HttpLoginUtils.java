package com.yyw.eas.utils;

import com.yyw.eas.bean.User;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpLoginUtils {

    /**
     * 登录接口获取响应
     *
     * @param user
     * @return
     */
    public static Response getLoginResponse(User user) {

        Map<String, String> map = new HashMap<>();
        map.put(Constant.Login.LOGIN_PARAM_USERNAME, user.getUsername());
        map.put(Constant.Login.LOGIN_PARAM_PASSWORD, user.getPassword());
        try {

            return Jsoup.connect(Constant.Login.LOGIN_URL)
                    .data(map)
                    .ignoreContentType(true)
                    .method(Connection.Method.POST)
                    .timeout(10000)
                    .execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户姓名
     *
     * @param cookiesMap
     * @return
     */
    public static String getStudentName(Map<String, String> cookiesMap) {

        try {
            Connection con = Jsoup.connect(Constant.StudentName.URL);
            con.ignoreContentType(true);
            Iterator<Map.Entry<String, String>> it = cookiesMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> en = it.next();
                con = con.cookie(en.getKey(), en.getValue());
            }
            Response response = con.method(Connection.Method.GET).timeout(10000).execute();
            Document document = Jsoup.parse(response.body());
            Element body = document.body();
            Elements elementsByClass = body.getElementsByClass("username");
            return elementsByClass.get(0).text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
