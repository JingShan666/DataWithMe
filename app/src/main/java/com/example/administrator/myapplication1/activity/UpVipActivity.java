package com.example.administrator.myapplication1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;


/**
 * Created by Liu on 2017/7/5 0005.
 * 升级会员
 */

public class UpVipActivity extends AppCompatActivity {


    private TextView tv_right;
    private TextView tv_mid;
    private ImageView iv_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upvip);

        initView();

    }

    private void initView() {

        tv_right= (TextView) findViewById(R.id.tv_right);
        tv_right.setVisibility(View.GONE);
        tv_mid= (TextView) findViewById(R.id.tv_middle);
        tv_mid.setText("升级");
        iv_left= (ImageView) findViewById(R.id.imageView_left);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
