package com.example.administrator.myapplication1.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class ImgLoadBean {
    /**
     * code : 200
     * data : {"response":{"key":"1695/3168/0710736092.jpg","token":"iIH5fmDcegprf9895ohWs_UQTtQ=:eyJzY29wZSI6ImxvYWQiLCJkZWFkbGluZSI6MTQ5OTA2OTQ3OH0=","url":"http://oscshgxeg.bkt.clouddn.com/1695/3168/0710736092.jpg"},"time":1499065878736}
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
         * response : {"key":"1695/3168/0710736092.jpg","token":"iIH5fmDcegprf9895ohWs_UQTtQ=:eyJzY29wZSI6ImxvYWQiLCJkZWFkbGluZSI6MTQ5OTA2OTQ3OH0=","url":"http://oscshgxeg.bkt.clouddn.com/1695/3168/0710736092.jpg"}
         * time : 1499065878736
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
             * key : 1695/3168/0710736092.jpg
             * token : iIH5fmDcegprf9895ohWs_UQTtQ=:eyJzY29wZSI6ImxvYWQiLCJkZWFkbGluZSI6MTQ5OTA2OTQ3OH0=
             * url : http://oscshgxeg.bkt.clouddn.com/1695/3168/0710736092.jpg
             */

            private String key;
            private String token;
            private String url;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
