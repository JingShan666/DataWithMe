package com.example.administrator.myapplication1.bean;

import java.util.List;

/**
 * Created by Liu on 2017/7/6 0006.
 */

public class AllNewThingBean {
    /**
     * code : 200
     * data : {"response":{"allxinxianshi":[{"createTime":1499311260000,"id":1,"text":"试试","type":2,"userid":59},{"createTime":1499311261000,"id":2,"text":"试试","type":2,"userid":59},{"createTime":1499311261000,"id":3,"text":"试试","type":2,"userid":59},{"createTime":1499311263000,"id":4,"photoUrl":"http://osnejttlj.bkt.clouddn.com/2838/1766/618335440.jpg","text":"4556","type":2,"userid":59}],"user":{"user":{"birthday":"2017-7-19","email":"555","endTime":1499311144000,"gender":1,"iconimage":"http://osnejttlj.bkt.clouddn.com/2838/1766/618335440.jpg","id":59,"member":0,"nickname":"555","phonenum":555,"sessionKey":"VICAR1HWZMSQVOQ8VUZGZQOHFYMYVCNC_59","status":1,"type":"1","userid":"2598ca7448c97258c36de90479fcc9fa"}}},"time":1499311703124}
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
         * response : {"allxinxianshi":[{"createTime":1499311260000,"id":1,"text":"试试","type":2,"userid":59,"photoUrl":"http://osnejttlj.bkt.clouddn.com/2838/1766/618335440.jpg"},{"createTime":1499311261000,"id":2,"text":"试试","type":2,"userid":59},{"createTime":1499311261000,"id":3,"text":"试试","type":2,"userid":59},{"createTime":1499311263000,"id":4,"photoUrl":"http://osnejttlj.bkt.clouddn.com/2838/1766/618335440.jpg","text":"4556","type":2,"userid":59}],"user":{"user":{"birthday":"2017-7-19","email":"555","endTime":1499311144000,"gender":1,"iconimage":"http://osnejttlj.bkt.clouddn.com/2838/1766/618335440.jpg","id":59,"member":0,"nickname":"555","phonenum":555,"sessionKey":"VICAR1HWZMSQVOQ8VUZGZQOHFYMYVCNC_59","status":1,"type":"1","userid":"2598ca7448c97258c36de90479fcc9fa"}}}
         * time : 1499311703124
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
             * allxinxianshi : [{"createTime":1499311260000,"id":1,"text":"试试","type":2,"userid":59},{"createTime":1499311261000,"id":2,"text":"试试","type":2,"userid":59},{"createTime":1499311261000,"id":3,"text":"试试","type":2,"userid":59},{"createTime":1499311263000,"id":4,"photoUrl":"http://osnejttlj.bkt.clouddn.com/2838/1766/618335440.jpg","text":"4556","type":2,"userid":59}]
             * user : {"user":{"birthday":"2017-7-19","email":"555","endTime":1499311144000,"gender":1,"iconimage":"http://osnejttlj.bkt.clouddn.com/2838/1766/618335440.jpg","id":59,"member":0,"nickname":"555","phonenum":555,"sessionKey":"VICAR1HWZMSQVOQ8VUZGZQOHFYMYVCNC_59","status":1,"type":"1","userid":"2598ca7448c97258c36de90479fcc9fa"}}
             */

            private UserBeanX user;
            private List<AllxinxianshiBean> allxinxianshi;

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
            }

            public List<AllxinxianshiBean> getAllxinxianshi() {
                return allxinxianshi;
            }

            public void setAllxinxianshi(List<AllxinxianshiBean> allxinxianshi) {
                this.allxinxianshi = allxinxianshi;
            }

            public static class UserBeanX {
                /**
                 * user : {"birthday":"2017-7-19","email":"555","endTime":1499311144000,"gender":1,"iconimage":"http://osnejttlj.bkt.clouddn.com/2838/1766/618335440.jpg","id":59,"member":0,"nickname":"555","phonenum":555,"sessionKey":"VICAR1HWZMSQVOQ8VUZGZQOHFYMYVCNC_59","status":1,"type":"1","userid":"2598ca7448c97258c36de90479fcc9fa"}
                 */

                private UserBean user;

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public static class UserBean {
                    /**
                     * birthday : 2017-7-19
                     * email : 555
                     * endTime : 1499311144000
                     * gender : 1
                     * iconimage : http://osnejttlj.bkt.clouddn.com/2838/1766/618335440.jpg
                     * id : 59
                     * member : 0
                     * nickname : 555
                     * phonenum : 555
                     * sessionKey : VICAR1HWZMSQVOQ8VUZGZQOHFYMYVCNC_59
                     * status : 1
                     * type : 1
                     * userid : 2598ca7448c97258c36de90479fcc9fa
                     */

                    private String birthday;
                    private String email;
                    private long endTime;
                    private int gender;
                    private String iconimage;
                    private int id;
                    private int member;
                    private String nickname;
                    private int phonenum;
                    private String sessionKey;
                    private int status;
                    private String type;
                    private String userid;

                    public String getBirthday() {
                        return birthday;
                    }

                    public void setBirthday(String birthday) {
                        this.birthday = birthday;
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

                    public String getUserid() {
                        return userid;
                    }

                    public void setUserid(String userid) {
                        this.userid = userid;
                    }
                }
            }

            public static class AllxinxianshiBean {
                /**
                 * createTime : 1499311260000
                 * id : 1
                 * text : 试试
                 * type : 2
                 * userid : 59
                 * photoUrl : http://osnejttlj.bkt.clouddn.com/2838/1766/618335440.jpg
                 */

                private long createTime;
                private int id;
                private String text;
                private int type;
                private int userid;
                private String photoUrl;

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getUserid() {
                    return userid;
                }

                public void setUserid(int userid) {
                    this.userid = userid;
                }

                public String getPhotoUrl() {
                    return photoUrl;
                }

                public void setPhotoUrl(String photoUrl) {
                    this.photoUrl = photoUrl;
                }
            }
        }
    }
}
