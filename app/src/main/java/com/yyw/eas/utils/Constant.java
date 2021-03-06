package com.yyw.eas.utils;


public interface Constant {

    class FileName {
        public static String SP_FILE_NAME = "eas";
    }

    class CoursePraise{
        static String URL = "http://glxt.juti.cn/new/Eval/Thumbsup/GetUnitEval";
        static String COURSE_URL = "http://glxt.juti.cn/new/Eval/Thumbsup/zTreeData";
        static String COURSE_URL_ID = "id";
        static String COURSE_URL_NAME = "name";
        static String COURSE_URL_PID = "pId";
        static String COURSE_URL_LEVEL = "level";
        static String COURSE_URL_OTHER_PARAM = "otherParam";
        static String COURSE_URL_ = "_";
    }

    class SchoolNotice{
        static String URL = "http://glxt.juti.cn/new/Basic/Default/GetArticleList";
        static String PARAM_PAGE_INDEX = "_PageIndex";
        static String PARAM_PAGE_SIZE = "_PageSize";
        static String PARAM_ORDER_BY_TYPE = "_OrderByType";
        static String PARAM_A_TITLE = "A_Title";
    }

    class StudentName {
        static String URL = "http://glxt.juti.cn/new/Basic/Default";
        public static String STUDENT_NAME = "student_name";
    }


    class User {
        public static String USERNAME = "username";
        public static String PASSWORD = "password";
    }

    class Connect{
        public static int SUCCESS = 0;
        public static int ERROR = 3;
    }

    class Login {
        static String LOGIN_URL = "http://glxt.juti.cn/new/Basic/Login";
        static String PARAM_USERNAME = "U_Account";
        static String PARAM_PASSWORD = "U_Password";
        public static int INVALID_USERNAME = 1;
        public static int INVALID_PASSWORD = 2;
        public static int EMPTY_USERNAME_OR_EMPTY_PASSWORD = 4;
    }

    class Response {
        public static String COOKIES_FILE_NAME = "cookiesJson";
        public static String SUCCESS = "true";
    }

    class Network {
        public static int Network_SUCCESS = 0;
        public static int Network_ERROR = 1;
    }
}
