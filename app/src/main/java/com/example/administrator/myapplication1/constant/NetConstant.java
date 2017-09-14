package com.example.administrator.myapplication1.constant;

/**
 * Created by Liu on 2017/6/22 0022.
 */

public class NetConstant {
    //http://localhost:9000/findmail?email=2131231

    public static String baseUrl="http://192.168.1.232:9000/";
    //注册
    public static String registerUrl="regist";
    //邮箱
    public static String findEmail="findmail";
    //登录
    public static String login="dwmlogin";
    //坐标系
    public static String fuser="fuser";
    //获取好友列表
    public static String alluser="alluser";
    //上传图片
    public static String loadImg="qiniu";
    //更新图片
    public static String updataImg="updataphoto";

    //获取图形验证码token verifyCode

    public static String getVerifyCode="isVerifyCode";

    //获取图形验证码图片
    public static String verifyCode="validateCode";

    //新鲜事
    public static String newThing="allxinxianshi";

    //新增新鲜事
    public static String subNewThing="savexinxianshi";


    //查找附近的人nearby
    public static String nearby="nearby";

    //保存创建的相册信息savephotoinfo
    public static String savephotoinfo="savephotoinfo";

    //查询创建的相册selectphotoinfo

    public static String selectphotoinfo="selectphotoinfo";

}
