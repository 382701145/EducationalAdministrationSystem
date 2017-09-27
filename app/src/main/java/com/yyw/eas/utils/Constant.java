package com.yyw.eas.utils;


public interface Constant {

    class FileName {
        public static String SP_FILE_NAME = "eas";
    }

    class StudentName {
        static String URL = "http://glxt.juti.cn/new/Basic/Default";
        public static String STUDENT_NAME = "student_name";
    }


    class User {
        public static String USERNAME = "username";
        public static String PASSWORD = "password";
    }

    class Login {
        static String LOGIN_URL = "http://glxt.juti.cn/new/Basic/Login";
        static String LOGIN_PARAM_USERNAME = "U_Account";
        static String LOGIN_PARAM_PASSWORD = "U_Password";
        public static int SUCCESS = 0;
        public static int INVALID_USERNAME = 1;
        public static int INVALID_PASSWORD = 2;
        public static int ERROR = 3;
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
