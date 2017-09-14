package com.example.administrator.myapplication1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.constant.NetConstant;
import com.example.administrator.myapplication1.frament.AboutFrament;
import com.example.administrator.myapplication1.frament.AboutMeFragment;
import com.example.administrator.myapplication1.frament.FreashThingFragment;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import static com.example.administrator.myapplication1.R.id.line_left;
import static com.example.administrator.myapplication1.R.id.line_right;
import static com.example.administrator.myapplication1.R.id.ll_online;

/**
 * Created by Liu on 2017/7/5 0005.
 */

public class NewThingActivity extends AppCompatActivity {

    private LinearLayout ll_friend;
    private LinearLayout ll_me;
    private View line_right;
    private View line_left;

    private FreashThingFragment freashThingFragment;
    private AboutMeFragment aboutMeFragment;

    private ImageView iv_right;
    private TextView tv_mid;
    private ImageView iv_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newthing);


        //initData();
        initView();


    }

    private void initData() {
        String url = NetConstant.baseUrl + NetConstant.newThing;
        OkHttpUtils
                .get()
                .url(url)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                        Log.e("获取所有新鲜事666：",response);

                    }
                });


    }

    private void initView() {

        iv_right= (ImageView) findViewById(R.id.imageView2);
        iv_left= (ImageView) findViewById(R.id.imageView1);

        tv_mid= (TextView) findViewById(R.id.tv_middle);
        tv_mid.setText("新鲜事");


        line_right= findViewById(R.id.line_right);
        line_left= findViewById(R.id.line_left);
        ll_friend= (LinearLayout) findViewById(R.id.ll_newthing);
        ll_me= (LinearLayout) findViewById(R.id.my_about);

        //点击事件
        clickEvent();

        //点击切换fragment
        changeFragment(ll_friend,ll_me);

    }

    private void clickEvent() {
        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(NewThingActivity.this,SubNewThing.class);
                startActivity(intent);
                finish();


            }
        });

    }

    private void changeFragment(LinearLayout ll_friend, LinearLayout ll_me) {

        //默认点击第一条
        line_right.setVisibility(View.GONE);
        freashThingFragment= new FreashThingFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_newthing, freashThingFragment).commit();


        ll_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                line_left.setVisibility(View.VISIBLE);
                line_right.setVisibility(View.GONE);
                FreashThingFragment freashThingFragment= new FreashThingFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_newthing, freashThingFragment).commit();




            }
        });

        ll_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                line_right.setVisibility(View.VISIBLE);
                line_left.setVisibility(View.GONE);
                aboutMeFragment= new AboutMeFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_newthing, aboutMeFragment).commit();
            }
        });

    }
}

