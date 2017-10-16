package com.yyw.eas.model;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yyw.eas.bean.Cookies;
import com.yyw.eas.bean.Course;
import com.yyw.eas.callback.OnLoadCallback;
import com.yyw.eas.utils.Constant;
import com.yyw.eas.utils.FileUtils;
import com.yyw.eas.utils.HttpUtils;
import com.yyw.eas.utils.LogUtils;

import org.jsoup.Connection;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CoursePraiseModel implements ICoursePraiseModel {

    private List<Course.CourseInfo> courseInfoList;
    private List<Course> courseList;

    @Override
    public void getCourseId(Context context, OnLoadCallback onLoadCallback) {

        courseList = new ArrayList<>();

        CoursePraiseIdAsyncTask coursePraiseIdAsyncTask = new CoursePraiseIdAsyncTask(context, onLoadCallback);
        coursePraiseIdAsyncTask.execute();


    }

    @Override
    public void getCourseInfo(Context context, int index, OnLoadCallback onLoadCallback) {

    }

    private class CoursePraiseIdAsyncTask extends AsyncTask<Void, Void, Connection.Response> {

        private Context context;
        private OnLoadCallback onLoadCallback;
        private Gson gson;

        CoursePraiseIdAsyncTask(Context context, OnLoadCallback onLoadCallback) {
            this.context = context;
            this.onLoadCallback = onLoadCallback;
            this.gson = new Gson();
        }

        @Override
        protected void onPostExecute(Connection.Response response) {
            super.onPostExecute(response);
            if (response != null) {
                String body = response.body();
                if (body != null) {
                    // TODO 获取成功,保存到本地
                    LogUtils.d(body);
                    Type listType = new TypeToken<List<Course.CourseInfo>>() {
                    }.getType();
                    courseInfoList = gson.fromJson(body, listType);
                    // TODO 根据CourseId查询课程信息
                    if (!courseInfoList.isEmpty()) {
                        for (int i = 0; i < courseInfoList.size(); i++) {
                            new CoursePraiseInfoAsyncTask(context, onLoadCallback).execute(courseInfoList.get(i));
                        }
                    } else {
                        onLoadCallback.onFailed(Constant.Connect.ERROR);
                    }
                } else {
                    onLoadCallback.onFailed(Constant.Connect.ERROR);
                }
            } else {
                onLoadCallback.onFailed(Constant.Network.Network_ERROR);
            }
        }

        @Override
        protected Connection.Response doInBackground(Void... voids) {

            String json = FileUtils.getStringFromFile(context, Constant.Response.COOKIES_FILE_NAME);
            Cookies cookies = gson.fromJson(json, Cookies.class);
            Map<String, String> cookiesMap = new HashMap<>();
            ArrayList<Cookies.Cookie> cookieArrayList = cookies.getCookies();
            for (Cookies.Cookie cookie : cookieArrayList) {
                cookiesMap.put(cookie.getCookieKey(), cookie.getCookieValue());
            }
            return HttpUtils.getCourseId(cookiesMap);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onLoadCallback.beforeLoad();
        }
    }

    private class CoursePraiseInfoAsyncTask extends AsyncTask<Course.CourseInfo, Void, Connection.Response> {

        private Context context;
        private OnLoadCallback onLoadCallback;
        private Gson gson;
        private String courseName;
        private final Object locker;

        CoursePraiseInfoAsyncTask(Context context, OnLoadCallback onLoadCallback) {
            this.context = context;
            this.onLoadCallback = onLoadCallback;
            this.gson = new Gson();
            this.locker = new Object();
        }


        @Override
        protected void onPostExecute(Connection.Response response) {
            super.onPostExecute(response);
            if (response != null) {
                String body = response.body();
                if (body != null) {
                    Type listType = new TypeToken<List<Course.CourseInfo>>() {
                    }.getType();
                    List<Course.CourseInfo> courseInfos = gson.fromJson(body, listType);
                    Course course = new Course();
                    course.setSchoolYear(courseName);
                    course.setCourseInfoList(courseInfos);
                    synchronized(locker){
                        courseList.add(course);
                        if(courseList.size() == courseInfoList.size()){
                            onLoadCallback.onSuccess(courseList);
                            onLoadCallback.onLoadComplete();
                        }
                    }
                }else{
                    onLoadCallback.onFailed(Constant.Connect.ERROR);
                    onLoadCallback.onLoadComplete();
                }
            }
        }

        @Override
        protected Connection.Response doInBackground(Course.CourseInfo... params) {

            String json = FileUtils.getStringFromFile(context, Constant.Response.COOKIES_FILE_NAME);
            Cookies cookies = gson.fromJson(json, Cookies.class);
            Map<String, String> cookiesMap = new HashMap<>();
            ArrayList<Cookies.Cookie> cookieArrayList = cookies.getCookies();
            for (Cookies.Cookie cookie : cookieArrayList) {
                cookiesMap.put(cookie.getCookieKey(), cookie.getCookieValue());
            }
            this.courseName = params[0].getName();
            return HttpUtils.getCourseInfo(cookiesMap, params[0].getId(), params[0].getName());
        }
    }
}
