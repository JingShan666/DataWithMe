package com.example.administrator.myapplication1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.constant.NetConstant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
/**
 * Created by Liu on 2017/6/22 0022.
 * 邮箱验证界面
 */

public class RegisterEmailActiity extends AppCompatActivity {
    private EditText et_email;
    private Button bt_next;
    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_left;
    private TextView tv_right;
    private TextView tv_policy;
    private TextView tv_mid;
    private SpannableString msp = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_email_register);
        initView();


    }

    private void initView() {
        et_email = (EditText) findViewById(R.id.input_email);
        bt_next = (Button) findViewById(R.id.register_next);
        iv_left = (ImageView) findViewById(R.id.imageView_left);
        iv_right = (ImageView) findViewById(R.id.imageView_right);
        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_policy = (TextView) findViewById(R.id.use_policy);

        iv_right.setVisibility(View.INVISIBLE);

        tv_mid = (TextView) findViewById(R.id.tv_middle);
        tv_mid.setText("注册");

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //非空校验
                String emailNum1 = et_email.getText().toString().trim();
                if (emailNum1.isEmpty()) {
                    Toast.makeText(RegisterEmailActiity.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //联网，验证邮箱
                Intent intent = new Intent(RegisterEmailActiity.this, RegisterActivity.class);
                //intent.putExtra("email", emailNum);
                startActivity(intent);
                //netWork();
            }


        });

        iv_left = (ImageView) findViewById(R.id.imageView_left);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //设置部分字体颜色和样式
        setTextStyle();
    }



    private void setTextStyle() {
        String text = tv_policy.getText().toString();

        msp = new SpannableString(text);

        msp.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }

            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(RegisterEmailActiity.this, UserPolicyActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoomin,R.anim.zoomout);


            }
        }, 10, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_policy.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明，否则会一直出现高亮
        tv_policy.setText(msp);
        tv_policy.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件

        msp.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }

            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(RegisterEmailActiity.this, YinSiActivity.class);
                startActivity(intent);

            }
        }, 15, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_policy.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明，否则会一直出现高亮
        tv_policy.setText(msp);
        tv_policy.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件


    }

    private void netWork() {

        final String emailNum = et_email.getText().toString().trim();

        String url = NetConstant.baseUrl + NetConstant.findEmail;
        //http://192.168.1.102:9000/findmail?email=1234

        OkHttpUtils
                .get()
                .url(url)
                .addParams("email", emailNum)
                //.addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(com.squareup.okhttp.Request request, Exception e) {

                        Log.e("RegisterEmailActiity验证","验证失败");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("邮箱返回信息：",response);
                        Intent intent = new Intent(RegisterEmailActiity.this, RegisterActivity.class);
                        intent.putExtra("email", emailNum);
                        startActivity(intent);
                        overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
                    }

                });

    }


}


