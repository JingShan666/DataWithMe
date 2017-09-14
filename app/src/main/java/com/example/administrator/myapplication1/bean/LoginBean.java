package com.example.administrator.myapplication1.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5 0005.
 */

public class LoginBean {
    /**
     * code : 200
     * data : {"loginInfo":{"extend":{},"newDevice":false,"otherSuccessd":0,"sessionKey":"HDMUNARYT8ZNVKWXALECIA0M4HZFWJZJ_58","userId":58,"version":0},"response":{"hx":{"age":11,"birthday":"请选择生日","city":"11","country":"11","education":1,"email":"222@qq.com","endTime":1499238158000,"feeling":11,"gender":1,"height":"11","iconimage":"11","id":58,"income":"11","interesting":"11","language":111,"member":0,"motto":"111","nickname":"222","phonenum":222,"photos":"11","sessionKey":"BJZC3QHVF3JGHBIK1FKSDMHELA7SO0V6_58","status":1,"type":"1","userX":"11","userY":"11","userid":"1be26dbfefb718e0e80a785be70a7187","weight":"11","work":11},"password":"222"},"time":1499238937726}
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
         * loginInfo : {"extend":{},"newDevice":false,"otherSuccessd":0,"sessionKey":"HDMUNARYT8ZNVKWXALECIA0M4HZFWJZJ_58","userId":58,"version":0}
         * response : {"hx":{"age":11,"birthday":"请选择生日","city":"11","country":"11","education":1,"email":"222@qq.com","endTime":1499238158000,"feeling":11,"gender":1,"height":"11","iconimage":"11","id":58,"income":"11","interesting":"11","language":111,"member":0,"motto":"111","nickname":"222","phonenum":222,"photos":"11","sessionKey":"BJZC3QHVF3JGHBIK1FKSDMHELA7SO0V6_58","status":1,"type":"1","userX":"11","userY":"11","userid":"1be26dbfefb718e0e80a785be70a7187","weight":"11","work":11},"password":"222"}
         * time : 1499238937726
         */

        private LoginInfoBean loginInfo;
        private ResponseBean response;
        private long time;

        public LoginInfoBean getLoginInfo() {
            return loginInfo;
        }

        public void setLoginInfo(LoginInfoBean loginInfo) {
            this.loginInfo = loginInfo;
        }

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

        public static class LoginInfoBean {
            /**
             * extend : {}
             * newDevice : false
             * otherSuccessd : 0
             * sessionKey : HDMUNARYT8ZNVKWXALECIA0M4HZFWJZJ_58
             * userId : 58
             * version : 0
             */

            private ExtendBean extend;
            private boolean newDevice;
            private int otherSuccessd;
            private String sessionKey;
            private int userId;
            private int version;

            public ExtendBean getExtend() {
                return extend;
            }

            public void setExtend(ExtendBean extend) {
                this.extend = extend;
            }

            public boolean isNewDevice() {
                return newDevice;
            }

            public void setNewDevice(boolean newDevice) {
                this.newDevice = newDevice;
            }

            public int getOtherSuccessd() {
                return otherSuccessd;
            }

            public void setOtherSuccessd(int otherSuccessd) {
                this.otherSuccessd = otherSuccessd;
            }

            public String getSessionKey() {
                return sessionKey;
            }

            public void setSessionKey(String sessionKey) {
                this.sessionKey = sessionKey;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public static class ExtendBean {
            }
        }

        public static class ResponseBean {
            /**
             * hx : {"age":11,"birthday":"请选择生日","city":"11","country":"11","education":1,"email":"222@qq.com","endTime":1499238158000,"feeling":11,"gender":1,"height":"11","iconimage":"11","id":58,"income":"11","interesting":"11","language":111,"member":0,"motto":"111","nickname":"222","phonenum":222,"photos":"11","sessionKey":"BJZC3QHVF3JGHBIK1FKSDMHELA7SO0V6_58","status":1,"type":"1","userX":"11","userY":"11","userid":"1be26dbfefb718e0e80a785be70a7187","weight":"11","work":11}
             * password : 222
             */

            private HxBean hx;
            private String password;

            public HxBean getHx() {
                return hx;
            }

            public void setHx(HxBean hx) {
                this.hx = hx;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public static class HxBean {
                /**
                 * age : 11
                 * birthday : 请选择生日
                 * city : 11
                 * country : 11
                 * education : 1
                 * email : 222@qq.com
                 * endTime : 1499238158000
                 * feeling : 11
                 * gender : 1
                 * height : 11
                 * iconimage : 11
                 * id : 58
                 * income : 11
                 * interesting : 11
                 * language : 111
                 * member : 0
                 * motto : 111
                 * nickname : 222
                 * phonenum : 222
                 * photos : 11
                 * sessionKey : BJZC3QHVF3JGHBIK1FKSDMHELA7SO0V6_58
                 * status : 1
                 * type : 1
                 * userX : 11
                 * userY : 11
                 * userid : 1be26dbfefb718e0e80a785be70a7187
                 * weight : 11
                 * work : 11
                 */

                private int age;
                private String birthday;
                private String city;
                private String country;
                private int education;
                private String email;
                private long endTime;
                private int feeling;
                private int gender;
                private String height;
                private String iconimage;
                private int id;
                private String income;
                private String interesting;
                private int language;
                private int member;
                private String motto;
                private String nickname;
                private int phonenum;
                private String photos;
                private String sessionKey;
                private int status;
                private String type;
                private String userX;
                private String userY;
                private String userid;
                private String weight;
                private int work;

                public int getAge() {
                    return age;
                }

                public void setAge(int age) {
                    this.age = age;
                }

                public String getBirthday() {
                    return birthday;
                }

                public void setBirthday(String birthday) {
                    this.birthday = birthday;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public int getEducation() {
                    return education;
                }

                public void setEducation(int education) {
                    this.education = education;
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

                public int getFeeling() {
                    return feeling;
                }

                public void setFeeling(int feeling) {
                    this.feeling = feeling;
                }

                public int getGender() {
                    return gender;
                }

                public void setGender(int gender) {
                    this.gender = gender;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getIconimage() {
                    return iconimage;
                }

                public void setIconimage(String iconimage) {
                    this.iconimage = iconimage;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIncome() {
                    return income;
                }

                public void setIncome(String income) {
                    this.income = income;
                }

                public String getInteresting() {
                    return interesting;
                }

                public void setInteresting(String interesting) {
                    this.interesting = interesting;
                }

                public int getLanguage() {
                    return language;
                }

                public void setLanguage(int language) {
                    this.language = language;
                }

                public int getMember() {
                    return member;
                }

                public void setMember(int member) {
                    this.member = member;
                }

                public String getMotto() {
                    return motto;
                }

                public void setMotto(String motto) {
                    this.motto = motto;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getPhonenum() {
                    return phonenum;
                }

                public void setPhonenum(int phonenum) {
                    this.phonenum = phonenum;
                }

                public String getPhotos() {
                    return photos;
                }

                public void setPhotos(String photos) {
                    this.photos = photos;
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

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserX() {
                    return userX;
                }

                public void setUserX(String userX) {
                    this.userX = userX;
                }

                public String getUserY() {
                    return userY;
                }

                public void setUserY(String userY) {
                    this.userY = userY;
                }

                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }

                public int getWork() {
                    return work;
                }

                public void setWork(int work) {
                    this.work = work;
                }
            }
        }
    }
}
