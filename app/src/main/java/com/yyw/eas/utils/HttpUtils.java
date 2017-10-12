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

public class HttpUtils {

    /**
     * 登录接口获取响应
     *
     * @param user
     * @return
     */
    public static Response getLoginResponse(User user) {

        Map<String, String> map = new HashMap<>();
        map.put(Constant.Login.PARAM_USERNAME, user.getUsername());
        map.put(Constant.Login.PARAM_PASSWORD, user.getPassword());
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
            if (elementsByClass.size() > 0) {
                return elementsByClass.get(0).text();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取学校通知接口
     *
     * @param cookiesMap
     * @param index
     * @return
     */
    public static Response getSchoolNotice(Map<String, String> cookiesMap, int index) {

        try {
            Connection con = Jsoup.connect(Constant.SchoolNotice.URL);
            con.ignoreContentType(true);
            Iterator<Map.Entry<String, String>> it = cookiesMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> en = it.next();
                con = con.cookie(en.getKey(), en.getValue());
            }

            return con.method(Connection.Method.POST)
                    .data(Constant.SchoolNotice.PARAM_PAGE_SIZE, "10")
                    .data(Constant.SchoolNotice.PARAM_PAGE_INDEX, String.valueOf(index))
                    .data(Constant.SchoolNotice.PARAM_A_TITLE, "")
                    .data(Constant.SchoolNotice.PARAM_ORDER_BY_TYPE, "asc")
                    .timeout(10000)
                    .execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response getCourseID(Map<String, String> cookiesMap) {

        try {
            Connection con = Jsoup.connect(Constant.CoursePraise.COURSE_URL);
            con.ignoreContentType(true);
            Iterator<Map.Entry<String, String>> it = cookiesMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> en = it.next();
                con = con.cookie(en.getKey(), en.getValue());
            }

            return con.method(Connection.Method.GET)
                    .data(Constant.CoursePraise.COURSE_URL_ID, "2016-2017^2")
                    .data(Constant.CoursePraise.COURSE_URL_NAME, "2016-2017-2")
                    .data(Constant.CoursePraise.COURSE_URL_LEVEL, "0")
                    .data(Constant.CoursePraise.COURSE_URL_OTHER_PARAM, "zTreeAsyncTest")
                    .data(Constant.CoursePraise.COURSE_URL_, "1507812989512")
                    .timeout(10000)
                    .execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
