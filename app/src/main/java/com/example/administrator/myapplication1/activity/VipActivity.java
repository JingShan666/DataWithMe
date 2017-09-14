package com.example.administrator.myapplication1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.utils.ToRoundImage;

import butterknife.OnClick;

/**
 * Created by Liu on 2017/7/5 0005.
 * 会员中心
 */

public class VipActivity extends AppCompatActivity {

    private TextView tv_right;
    private TextView tv_mid;
    private ImageView iv_left;

    private Bitmap roundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        initView();

    }

    private void initView() {
        tv_right= (TextView) findViewById(R.id.tv_right);
        tv_right.setVisibility(View.GONE);
        tv_mid= (TextView) findViewById(R.id.tv_middle);
        tv_mid.setText("会员中心");
        iv_left= (ImageView) findViewById(R.id.imageView_left);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //转换为圆形
        ImageView imageView= (ImageView) findViewById(R.id.iv_upvip);
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        roundImage= ToRoundImage.toRoundBitmap(bitmap);

        imageView.setImageBitmap(roundImage);
        //转换为圆形
        ImageView imageView1= (ImageView) findViewById(R.id.iv_gold);
        Bitmap bitmap1 = ((BitmapDrawable)imageView1.getDrawable()).getBitmap();
        roundImage= ToRoundImage.toRoundBitmap(bitmap1);

        imageView.setImageBitmap(roundImage);
        //转换为圆形
        ImageView imageView2= (ImageView) findViewById(R.id.iv_face);
        Bitmap bitmap2 = ((BitmapDrawable)imageView2.getDrawable()).getBitmap();
        roundImage= ToRoundImage.toRoundBitmap(bitmap2);

        imageView.setImageBitmap(roundImage);
        //转换为圆形
        ImageView imageView3= (ImageView) findViewById(R.id.iv_face);
        Bitmap bitmap3 = ((BitmapDrawable)imageView3.getDrawable()).getBitmap();
        roundImage= ToRoundImage.toRoundBitmap(bitmap3);

        imageView.setImageBitmap(roundImage);



        clickEvent();

    }

    private void clickEvent() {

        findViewById(R.id.up_vip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(VipActivity.this,UpVipActivity.class);
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.buy_gold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(VipActivity.this,GetGoldActivity.class);
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.transalete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(VipActivity.this,FanYiActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @OnClick({R.id.up_vip, R.id.buy_gold, R.id.transalete, R.id.face})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.up_vip:

                break;
            case R.id.buy_gold:
                break;
            case R.id.transalete:

                break;
            case R.id.face:

                break;
        }
    }
}