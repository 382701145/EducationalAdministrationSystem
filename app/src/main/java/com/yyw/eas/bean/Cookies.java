package com.yyw.eas.bean;

import java.util.ArrayList;

public class Cookies {

    private String data;
    private ArrayList<Cookie> cookies;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(ArrayList<Cookie> cookies) {
        this.cookies = cookies;
    }

    public static class Cookie {
        private String cookieKey;
        private String cookieValue;

        public Cookie(String cookieKey, String cookieValue) {
            this.cookieKey = cookieKey;
            this.cookieValue = cookieValue;
        }

        public String getCookieKey() {
            return cookieKey;
        }

        public void setCookieKey(String cookieKey) {
            this.cookieKey = cookieKey;
        }

        public String getCookieValue() {
            return cookieValue;
        }

        public void setCookieValue(String cookieValue) {
            this.cookieValue = cookieValue;
        }
    }
}
