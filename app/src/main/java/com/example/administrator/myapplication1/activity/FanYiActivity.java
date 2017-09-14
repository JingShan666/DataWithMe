package com.example.administrator.myapplication1.activity;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;


/**
 * Created by Liu on 2017/7/5 0005.
 * 翻译界面
 *
 * //下拉刷新
 * https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh/blob/master/README-cn.md
 * http://blog.csdn.net/lmj623565791/article/details/38238749
 */

public class FanYiActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;

    private TextView tv_right;
    private TextView tv_mid;
    private ImageView iv_left;

    private ImageView iv_round_earth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fanyi);

        initView();

    }

    private void initView() {
        textView1= (TextView) findViewById(R.id.textView1);
        textView2= (TextView) findViewById(R.id.textView2);
        textView3= (TextView) findViewById(R.id.textView3);
        textView4= (TextView) findViewById(R.id.textView4);
        textView5= (TextView) findViewById(R.id.textView5);
        textView1.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
        textView2.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
        textView3.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
        textView4.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
        textView5.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线

        tv_right= (TextView) findViewById(R.id.tv_right);
        tv_right.setVisibility(View.GONE);
        tv_mid= (TextView) findViewById(R.id.tv_middle);
        tv_mid.setText("精译包");
        iv_left= (ImageView) findViewById(R.id.imageView_left);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        iv_round_earth= (ImageView) findViewById(R.id.round_earth);
        //设置为圆形
//        Bitmap bitmap = ((BitmapDrawable)iv_round_earth.getDrawable()).getBitmap();
//        roundImage= ToRoundImage.toRoundBitmap(bitmap);
//
//        iv_round_earth.setImageBitmap(roundImage);
        //Picasso.with(this).load(R.drawable.earth).transform(new CircleTransform()).into(iv_round_earth);

    }
}
