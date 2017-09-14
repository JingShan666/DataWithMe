package com.example.administrator.myapplication1.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.administrator.myapplication1.bean.ImgLoadBean;
import com.example.administrator.myapplication1.constant.NetConstant;
import com.google.gson.Gson;
import com.qiniu.android.http.ResponseInfo;

import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;



/**
 * Created by Liu on 2017/7/1 0001.
 * 上传图片工具类
 * http://www.cnblogs.com/china-soft/p/4925683.html
 *
 * http://blog.csdn.net/u010694658/article/details/51177948
 */

public class ImageLoder {


    private static String load= "http://load.qiniu.com";
    private static String token;


    private static String newUrl= null;



    public static void startLoad(final String userId, final String path,final String name) {


        //联网，提交服务器

        String url = NetConstant.baseUrl + NetConstant.loadImg;

       // String url= "162.168.1.103:8080/ssm_project/qiniu";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("bucketName",name)
                .addParams("expires", 360000 + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }
                    @Override
                    public void onResponse(String response) {

                        Log.e("上传图片返回信息9999：", response);

                        Gson gson = new Gson();
                        ImgLoadBean imgBean = gson.fromJson(response, ImgLoadBean.class);

                        String url = imgBean.getData().getResponse().getUrl();
                        String key= imgBean.getData().getResponse().getKey();
                        token = imgBean.getData().getResponse().getToken();
                        Log.e("上传图片返回信息url：", url);
                        Log.e("上传图片返回信息key：", key);
                        Log.e("上传图片返回信息token：", token);
                        Log.e("上传图片返回信息path：", path);
                        Log.e("上传图片返回信息userId：", userId);
                        //开始上传

                        newUrl= url;

                        startLoadImg(token,path, key,userId,url);
                    }
                });
    }

    //上传头像
    private static void startLoadImg(final String token, final String path, final String key,
                                     final String userid, final String url) {


        new Thread(new Runnable(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {

                if(token!=null){

                    //图片名称为当前日期+随机数生成
//                    Date curDate   =   new Date(System.currentTimeMillis());//获取当前时间
//                    String   str   = curDate.toString();

                    UploadManager uploadManager = new UploadManager();

                    uploadManager.put(path, key,token,
                            new UpCompletionHandler() {
                                @Override
                                public void complete(String arg0, ResponseInfo info, JSONObject response) {
                                    if (info != null && info.statusCode == 200) {// 上传成功
                                        Log.e("QiNiuUpLoad", "上传成功！地址为：" + load + "/" + key);
                                        upDataImg(userid,url);
                                    } else {
                                        Log.e("QiNiuUpLoad", "错误" + info.statusCode + ":" + info.error);
                                    }
                                }
                            }, null);
                }
                else{
                    Log.e("fail", "上传失败");
                }
            }
        }).start();


    }

    //更新头像

    public static void  upDataImg(String userid, String url1){
        String url = NetConstant.baseUrl + NetConstant.updataImg;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("userid",userid)
                .addParams("url", url1)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }
                    @Override
                    public void onResponse(String response) {

                        Log.e("更新图片返回信息8888：", response);
                    }
                });
    }

    public static String getUrl(){
        return newUrl;
    }
}