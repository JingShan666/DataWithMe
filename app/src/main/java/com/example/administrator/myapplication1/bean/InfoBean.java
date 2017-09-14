package com.example.administrator.myapplication1.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class InfoBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username : 12222222222
         * userpwd : 222
         */

        private String username;
        private String userpwd;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserpwd() {
            return userpwd;
        }

        public void setUserpwd(String userpwd) {
            this.userpwd = userpwd;
        }
    }
}
