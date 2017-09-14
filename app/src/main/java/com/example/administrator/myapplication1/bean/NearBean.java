package com.example.administrator.myapplication1.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class NearBean {

    /**
     * code : 200
     * data : {"response":{"kfs":[{"birthday":"2017-7-21","country":"1","distance":0,"email":"11","endTime":1499422587000,"gender":1,"id":60,"member":0,"nickname":"11","phonenum":11,"sessionKey":"2IHW4GZBCYRSDGQKYXIHIHGV1TKCJ6NV_60","status":1,"type":"2","userX":"113.67981","userY":"34.783919","userid":"351436ef4b279e1811a6c68a2dd58b1b"},{"birthday":"2017-7-6","country":"1","distance":0,"email":"422","endTime":1499422589000,"gender":1,"id":61,"member":0,"nickname":"666","phonenum":666,"sessionKey":"P64AWYDRRJU5LPGUBKKMBAZK2VFMCRET_61","status":1,"type":"2","userX":"113.67982","userY":"34.783849","userid":"52d2202ffe31056a236ba05c1bf3792d"},{"birthday":"请选择生日","country":"1","distance":12162.79,"email":"999@qq.com","endTime":1499419401000,"gender":1,"id":62,"member":0,"nickname":"999","phonenum":999,"sessionKey":"U6KVUZRIHHJ5C91QKCTJKFYXQJMZH2SX_62","status":1,"type":"2","userX":"0.0","userY":"0.0","userid":"0e40068938f53df53a605c74f7d4543d"},{"birthday":"请选择生日","country":"1","distance":0,"email":"1111@qq.com","endTime":1499422592000,"gender":1,"id":63,"member":0,"nickname":"1111","phonenum":1111,"sessionKey":"BGRCOP4DF54HFVHEGZYD6IUWH0YCWG9L_63","status":1,"type":"2","userX":"113.67983","userY":"34.783849","userid":"917fe772b0a28cc27f37e8834e821b1c"},{"birthday":"2017-7-21","country":"1","distance":0.01,"email":"1236","endTime":1499422595000,"gender":1,"iconimage":"http://osnejttlj.bkt.clouddn.com/4773/9609/6276543008.jpg","id":64,"member":0,"nickname":"888","phonenum":888,"sessionKey":"T1RTISTBOZRPZDWKHK6TLYBAHMCGYXWD_64","status":1,"type":"2","userX":"113.679934","userY":"34.783927","userid":"c98a6534be7d16a78fbb1fabc722d13d"},{"birthday":"2017-7-6","country":"1","distance":0,"email":"5222","endTime":1499419152000,"gender":1,"id":65,"member":0,"nickname":"000","phonenum":0,"sessionKey":"N9YS46GDBB50FHCAYU80MJ32B6QE72D4_65","status":1,"type":"2","userX":"113.67985","userY":"34.78386","userid":"ca51194570926df046bf7d1827edb2d6"},{"birthday":"请选择生日","country":"1","distance":0,"email":"2222@qq.com","endTime":1499422600000,"gender":1,"id":66,"member":0,"nickname":"2222","phonenum":2222,"sessionKey":"JPFZSVWYXH2FOO5EEPE8XIMUZ8YNMN6R_66","status":1,"type":"2","userX":"113.67986","userY":"34.783849","userid":"4a65bc3e077d6e408d5e789ae56ca63e"},{"birthday":"请选择生日","country":"1","distance":0.01,"email":"33333@qq.com","endTime":1499422602000,"gender":1,"id":67,"member":0,"nickname":"33333","phonenum":33333,"sessionKey":"IKWOTNJKLHR9FWBTCIKXHCYLDANYZMMN_67","status":1,"type":"2","userX":"113.67987","userY":"34.783849","userid":"cea3108f8676260c29a49b658dbee7d5"}]},"time":1499423647594}
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
         * response : {"kfs":[{"birthday":"2017-7-21","country":"1","distance":0,"email":"11","endTime":1499422587000,"gender":1,"id":60,"member":0,"nickname":"11","phonenum":11,"sessionKey":"2IHW4GZBCYRSDGQKYXIHIHGV1TKCJ6NV_60","status":1,"type":"2","userX":"113.67981","userY":"34.783919","userid":"351436ef4b279e1811a6c68a2dd58b1b","iconimage":"http://osnejttlj.bkt.clouddn.com/4773/9609/6276543008.jpg"},{"birthday":"2017-7-6","country":"1","distance":0,"email":"422","endTime":1499422589000,"gender":1,"id":61,"member":0,"nickname":"666","phonenum":666,"sessionKey":"P64AWYDRRJU5LPGUBKKMBAZK2VFMCRET_61","status":1,"type":"2","userX":"113.67982","userY":"34.783849","userid":"52d2202ffe31056a236ba05c1bf3792d"},{"birthday":"请选择生日","country":"1","distance":12162.79,"email":"999@qq.com","endTime":1499419401000,"gender":1,"id":62,"member":0,"nickname":"999","phonenum":999,"sessionKey":"U6KVUZRIHHJ5C91QKCTJKFYXQJMZH2SX_62","status":1,"type":"2","userX":"0.0","userY":"0.0","userid":"0e40068938f53df53a605c74f7d4543d"},{"birthday":"请选择生日","country":"1","distance":0,"email":"1111@qq.com","endTime":1499422592000,"gender":1,"id":63,"member":0,"nickname":"1111","phonenum":1111,"sessionKey":"BGRCOP4DF54HFVHEGZYD6IUWH0YCWG9L_63","status":1,"type":"2","userX":"113.67983","userY":"34.783849","userid":"917fe772b0a28cc27f37e8834e821b1c"},{"birthday":"2017-7-21","country":"1","distance":0.01,"email":"1236","endTime":1499422595000,"gender":1,"iconimage":"http://osnejttlj.bkt.clouddn.com/4773/9609/6276543008.jpg","id":64,"member":0,"nickname":"888","phonenum":888,"sessionKey":"T1RTISTBOZRPZDWKHK6TLYBAHMCGYXWD_64","status":1,"type":"2","userX":"113.679934","userY":"34.783927","userid":"c98a6534be7d16a78fbb1fabc722d13d"},{"birthday":"2017-7-6","country":"1","distance":0,"email":"5222","endTime":1499419152000,"gender":1,"id":65,"member":0,"nickname":"000","phonenum":0,"sessionKey":"N9YS46GDBB50FHCAYU80MJ32B6QE72D4_65","status":1,"type":"2","userX":"113.67985","userY":"34.78386","userid":"ca51194570926df046bf7d1827edb2d6"},{"birthday":"请选择生日","country":"1","distance":0,"email":"2222@qq.com","endTime":1499422600000,"gender":1,"id":66,"member":0,"nickname":"2222","phonenum":2222,"sessionKey":"JPFZSVWYXH2FOO5EEPE8XIMUZ8YNMN6R_66","status":1,"type":"2","userX":"113.67986","userY":"34.783849","userid":"4a65bc3e077d6e408d5e789ae56ca63e"},{"birthday":"请选择生日","country":"1","distance":0.01,"email":"33333@qq.com","endTime":1499422602000,"gender":1,"id":67,"member":0,"nickname":"33333","phonenum":33333,"sessionKey":"IKWOTNJKLHR9FWBTCIKXHCYLDANYZMMN_67","status":1,"type":"2","userX":"113.67987","userY":"34.783849","userid":"cea3108f8676260c29a49b658dbee7d5"}]}
         * time : 1499423647594
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
            private List<KfsBean> kfs;

            public List<KfsBean> getKfs() {
                return kfs;
            }

            public void setKfs(List<KfsBean> kfs) {
                this.kfs = kfs;
            }

            public static class KfsBean {
                /**
                 * birthday : 2017-7-21
                 * country : 1
                 * distance : 0
                 * email : 11
                 * endTime : 1499422587000
                 * gender : 1
                 * id : 60
                 * member : 0
                 * nickname : 11
                 * phonenum : 11
                 * sessionKey : 2IHW4GZBCYRSDGQKYXIHIHGV1TKCJ6NV_60
                 * status : 1
                 * type : 2
                 * userX : 113.67981
                 * userY : 34.783919
                 * userid : 351436ef4b279e1811a6c68a2dd58b1b
                 * iconimage : http://osnejttlj.bkt.clouddn.com/4773/9609/6276543008.jpg
                 */

                private String birthday;
                private String country;
                private double distance;
                private String email;
                private long endTime;
                private int gender;
                private int id;
                private int member;
                private String nickname;
                private int phonenum;
                private String sessionKey;
                private int status;
                private String type;
                private String userX;
                private String userY;
                private String userid;
                private String iconimage;

                public String getBirthday() {
                    return birthday;
                }

                public void setBirthday(String birthday) {
                    this.birthday = birthday;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public Double getDistance() {
                    return distance;
                }

                public void setDistance(int distance) {
                    this.distance = distance;
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

                public int getMember() {
                    return member;
                }

                public void setMember(int member) {
                    this.member = member;
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

                public String getIconimage() {
                    return iconimage;
                }

                public void setIconimage(String iconimage) {
                    this.iconimage = iconimage;
                }
            }
        }
    }
}
