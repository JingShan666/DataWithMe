package com.example.administrator.myapplication1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.myapplication1.MainActivity;
import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.bean.LoginBean;
import com.example.administrator.myapplication1.constant.NetConstant;
import com.example.administrator.myapplication1.utils.LoadingUtils;
import com.example.administrator.myapplication1.utils.MyLocationListener;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


/**
 * Created by Liu on 2017/6/22 0022.
 *
 * 登录接口：http://192.168.1.102/dwmlogin
 *
 * 返回数据： {
 * "code":200,"data":
 * {"response":
 * {"account":
 * {"birthday":"哦哦哦",
 * "email":"55669885","gender":0,"id":1,"nickname":"哦哦哦","password":"856",
 * "phonenum":12655,"type":"1",
 * "userid":"2e112702e20f4941b82d608e4ca3af5e"}},"time":1498465690884},"messages":[]}
 *
 * 55669885
 * 856
 */

public class LoginActivity extends AppCompatActivity{
    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_mid;
    private TextView tv_newuser;
    private TextView tv_fgpswd;

    private Button bt_login;

    private EditText et_userName;
    private EditText et_userPwd;
    //底部分享按钮
    private ImageView iv_qq;
    private ImageView iv_wechat;


    //地图定位相关//////////////////////Map///////////////////
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ShareSDK.initSDK(getActivity());
        setContentView(R.layout.activity_login);
        EMClient.getInstance().logout(true);
        initView();

    }

    private void initView() {
        iv_left= (ImageView) findViewById(R.id.imageView_left);
        iv_right= (ImageView) findViewById(R.id.imageView_right);


        iv_qq= (ImageView) findViewById(R.id.qq_img);
        iv_wechat= (ImageView) findViewById(R.id.wechat_img);

        et_userName= (EditText) findViewById(R.id.username);
        et_userPwd= (EditText) findViewById(R.id.userpwd);


        iv_right.setVisibility(View.INVISIBLE);
        tv_mid= (TextView) findViewById(R.id.tv_middle);
        tv_mid.setText("登录");
        bt_login= (Button) findViewById(R.id.login);

        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_newuser= (TextView) findViewById(R.id.new_user);
        tv_newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterEmailActiity.class);
                startActivity(intent);
                finish();
            }
        });

        tv_fgpswd= (TextView) findViewById(R.id.forget_pwd);
        tv_fgpswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ForgetPwdActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //////////////////////Map///////////////////
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );
        //注册监听函数

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示加载kuang

                LoadingUtils.showDialog(LoginActivity.this,"正在登陆...");

                //开始定位
                mLocationClient.start();

                //获取用户名和密码
                final String name= et_userName.getText().toString().trim();
                final String pwd= et_userPwd.getText().toString().trim();


                //非空判断
                if (name.isEmpty()){
                    Toast.makeText(LoginActivity.this,"请输入您的账号",Toast.LENGTH_SHORT).show();
                    return;

                }else if (pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"请输入您的密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                //请求服务器
                //联网，提交服务器

                String url = NetConstant.baseUrl + NetConstant.login;
                OkHttpUtils
                        .get()
                        .url(url)
                        .addParams("account", name)
                        .addParams("password", pwd)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                e.printStackTrace();
                                //返回链接异常信息
                                String errorMsg=  e.getMessage().toString();
                                Log.e("错误信息666：",errorMsg);
                               // Log.e("错误信息：",e.printStackTrace());
                            }
                            @Override
                            public void onResponse(String response) {
                                Log.e("登录返回用户信息。。。：",response);
                                try {

                                    Gson gson = new Gson();

                                    LoginBean loginBean = gson.fromJson(response, LoginBean.class);

                                    final String userid= loginBean.getData().getLoginInfo().getUserId()+"";
                                    final String id=loginBean.getData().getResponse().getHx().getId()+"";

                                    final String iconImg =loginBean.getData().getResponse().getHx().getIconimage()+"";

                                    final String password= loginBean.getData().getResponse().getPassword();

                                    Log.e("返回码....userid:",userid);
                                    Log.e("返回码....password:",password);
                                    Log.e("返回码....iconImg:",iconImg);
                                    Log.e("登录id。。。：",id);

                                    //EMClient.getInstance().logout(true);
                                    EMClient.getInstance().login(id,password,new EMCallBack() {//回调

                                        @Override
                                        public void onSuccess() {
                                            //EMClient.getInstance().groupManager().loadAllGroups();
                                            EMClient.getInstance().chatManager().loadAllConversations();
                                            Log.e("LoginActivity", "登录聊天服务器成功！");

                                            //指定操作的文件名称
                                            SharedPreferences share = getSharedPreferences("UID", MODE_PRIVATE);
                                            SharedPreferences.Editor edit = share.edit();//编辑文件
                                            edit.putString("uId", userid);//根据键值对添加数据
                                            edit.putString("iconImg", iconImg);
                                            edit.putString("id", id);
                                            edit.commit();  //保存数据信息


                                            //实现淡入浅出的效果
                                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                            overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
//                                            // 由左向右滑入的效果
//                                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                                            overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
////                                            // 实现zoommin 和 zoomout (自定义的动画)
////                                            startActivity(new Intent(OverridePendingTransitionActivity.this,SecondActivity.class));
////                                            overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
//
//                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                            startActivity(intent);

                                            //取消加载框

                                            LoadingUtils.cencelDialog();
                                        }

                                        @Override
                                        public void onProgress(int progress, String status) {

                                        }

                                        @Override
                                        public void onError(int code, String message) {
                                            Log.e("LoginActivity", "登录聊天服务器失败！");

                                            Log.e("LoginActivitycode", "登录聊天服务器失败code！"+code);

                                            Log.e("LoginActivitymessage", message);

                                            Log.e("失败....id:",id);
                                            Log.e("失败....password:",password);

                                        }
                                    });


//                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                    startActivity(intent);

                                    //获取经度
                                    Double longitude= MyLocationListener.longitude;
                                    //获取纬度
                                    Double latitude= MyLocationListener.latitude;


                                    //登录成功后，联网，传坐标
                                   // doNetWork(userid,longitude,latitude);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });



    }



    private void doNetWork(String userid, Double longitude, Double latitude) {

        String url = NetConstant.baseUrl + NetConstant.fuser;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("userid", userid)
                .addParams("user_x", longitude+"")
                .addParams("user_y", latitude+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                        Log.e("登录坐标返回值：",response);

                    }
                });

    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        //MobSDK.stopSDK(getActivity());


    }
}
