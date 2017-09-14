package com.example.administrator.myapplication1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication1.R;

/**
 * Created by Liu on 2017/6/22 0022.
 * 注册登录界面
 *
 */

public class LoginAndRegisterActivity extends Activity {

    private Button bt_register;
    private Button bt_login;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_loginandregister);
        initView();

    }

    private void initView() {

        bt_register= findViewById(R.id.register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAndRegisterActivity.this,RegisterEmailActiity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
            }
        });
        bt_login= findViewById(R.id.login);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAndRegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
            }
        });

    }


}