package com.example.administrator.myapplication1.bean;

import java.util.List;

/**
 * Created by Liu on 2017/6/28 0028.
 * 所有用户信息
 */

public class AllUsersBean {

    /**
     * code : 200
     * data : {"response":{"alluser":[{"birthday":"2017-6-15","createTime":1498615034000,
     * "email":"422","endTime":1498615034000,"gender":1,"id":35,"nickname":"AA",
     * "password":"11","phonenum":11,"sessionKey":"1IUQQKQEKJZL0WEF9OLSBZJBI2IJJOFI_35",
     * "status":1,"userid":"a8ff0b7d6b81e45ceaecfdcb30966fa7"},{"birthday":"2017-6-28",
     * "createTime":1498615398000,"email":"22","endTime":1498615398000,"gender":1,"id":36,
     * "nickname":"22","password":"22","phonenum":22,"sessionKey":"GXGNZX0KCSLL1UMLKXWA12CGR725G2ZP_36",
     * "status":1,"userid":"77ad180bba3c85d54d0ae764adf04fbb"},{"birthday":"2017-6-28","createTime":1498615398000,
     * "email":"22","gender":1,"id":37,"nickname":"22","password":"22","phonenum":22,"status":1,
     * "userid":"77ad180bba3c85d54d0ae764adf04fbb"}]},"time":1498617106700}
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
         * response : {"alluser":[{"birthday":"2017-6-15","createTime":1498615034000,"email":"422","endTime":1498615034000,"gender":1,"id":35,"nickname":"AA","password":"11","phonenum":11,"sessionKey":"1IUQQKQEKJZL0WEF9OLSBZJBI2IJJOFI_35","status":1,"userid":"a8ff0b7d6b81e45ceaecfdcb30966fa7"},{"birthday":"2017-6-28","createTime":1498615398000,"email":"22","endTime":1498615398000,"gender":1,"id":36,"nickname":"22","password":"22","phonenum":22,"sessionKey":"GXGNZX0KCSLL1UMLKXWA12CGR725G2ZP_36","status":1,"userid":"77ad180bba3c85d54d0ae764adf04fbb"},{"birthday":"2017-6-28","createTime":1498615398000,"email":"22","gender":1,"id":37,"nickname":"22","password":"22","phonenum":22,"status":1,"userid":"77ad180bba3c85d54d0ae764adf04fbb"}]}
         * time : 1498617106700
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
            private List<AlluserBean> alluser;

            public List<AlluserBean> getAlluser() {
                return alluser;
            }

            public void setAlluser(List<AlluserBean> alluser) {
                this.alluser = alluser;
            }

            public static class AlluserBean {
                /**
                 * birthday : 2017-6-15
                 * createTime : 1498615034000
                 * email : 422
                 * endTime : 1498615034000
                 * gender : 1
                 * id : 35
                 * nickname : AA
                 * password : 11
                 * phonenum : 11
                 * sessionKey : 1IUQQKQEKJZL0WEF9OLSBZJBI2IJJOFI_35
                 * status : 1
                 * userid : a8ff0b7d6b81e45ceaecfdcb30966fa7
                 */

                private String birthday;
                private long createTime;
                private String email;
                private long endTime;
                private int gender;
                private int id;
                private String nickname;
                private String password;
                private int phonenum;
                private String sessionKey;
                private int status;
                private String userid;

                public String getBirthday() {
                    return birthday;
                }

                public void setBirthday(String birthday) {
                    this.birthday = birthday;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public long getEndTime() {
                    return endTime;
                }

                public void setEndTime(long endTime) {
                    this.endTime = endTime;
                }

                public int getGender() {
                    return gender;
                }

                public void setGender(int gender) {
                    this.gender = gender;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public int getPhonenum() {
                    return phonenum;
                }

                public void setPhonenum(int phonenum) {
                    this.phonenum = phonenum;
                }

                public String getSessionKey() {
                    return sessionKey;
                }

                public void setSessionKey(String sessionKey) {
                    this.sessionKey = sessionKey;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }
            }
        }
    }
}
