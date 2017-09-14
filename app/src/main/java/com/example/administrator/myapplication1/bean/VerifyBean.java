package com.example.administrator.myapplication1.bean;

import java.util.List;

/**
 * Created by Liu on 2017/7/5 0005.
 */

public class VerifyBean {


    /**
     * code : 200
     * data : {"response":{"isVerifyCode":true,"token":"yZgSDPIuJJzU4GN0axUvM8MRSbXjgSJW"},"time":1499221597962}
     * messages : []
     */

    private int code;
    private DataBean data;
    private List<?> messages;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<?> getMessages() {
        return messages;
    }

    public void setMessages(List<?> messages) {
        this.messages = messages;
    }

    public static class DataBean {
        /**
         * response : {"isVerifyCode":true,"token":"yZgSDPIuJJzU4GN0axUvM8MRSbXjgSJW"}
         * time : 1499221597962
         */

        private ResponseBean response;
        private long time;

        public ResponseBean getResponse() {
            return response;
        }

        public void setResponse(ResponseBean response) {
            this.response = response;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public static class ResponseBean {
            /**
             * isVerifyCode : true
             * token : yZgSDPIuJJzU4GN0axUvM8MRSbXjgSJW
             */

            private boolean isVerifyCode;
            private String token;

            public boolean isIsVerifyCode() {
                return isVerifyCode;
            }

            public void setIsVerifyCode(boolean isVerifyCode) {
                this.isVerifyCode = isVerifyCode;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }
    }
}
