package com.example.administrator.myapplication1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;

/**
 * Created by Liu on 2017/6/22 0022.
 */

public class ForgetPwdActivity extends AppCompatActivity {
    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_mid;
    private Button bt_send;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_forget_pwd);
        initView();

    }

    private void initView() {
        iv_left= (ImageView) findViewById(R.id.imageView_left);
        iv_right= (ImageView) findViewById(R.id.imageView_right);

        iv_right.setVisibility(View.INVISIBLE);
        tv_mid= (TextView) findViewById(R.id.tv_middle);
        tv_mid.setText("忘记密码");

        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bt_send= (Button) findViewById(R.id.send);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
