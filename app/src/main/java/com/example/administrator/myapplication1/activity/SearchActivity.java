package com.example.administrator.myapplication1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.utils.SeekBarPressure;

/**
 * Created by Liu on 2017/6/27 0027.
 * 搜索Activity
 * https://github.com/YahooArchive/android-range-seek-bar
 */

public class SearchActivity extends AppCompatActivity{

    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_mid;
    private TextView low_age;
    private TextView high_age;

    private SeekBarPressure seekbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();

    }

    private void initView() {

        iv_left= (ImageView) findViewById(R.id.imageView_left);
        iv_right= (ImageView) findViewById(R.id.imageView_right);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        iv_right.setVisibility(View.INVISIBLE);
        tv_mid= (TextView) findViewById(R.id.tv_middle);
        tv_mid.setText("搜索");

        low_age= (TextView) findViewById(R.id.low_age);
        high_age= (TextView) findViewById(R.id.high_age);

        seekbar= (SeekBarPressure) findViewById(R.id.search_seek);
        //设置默认初始值
        seekbar.setProgressHigh(100);
        seekbar.setProgressLow(0);

        seekbar.setOnSeekBarChangeListener(new SeekBarPressure.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBarPressure seekBar, double progressLow, double progressHigh) {
                //tv_age.setText("低：" + progressLow + "高：" + progressHigh);

                int a = (int) progressLow;
                int b = (int) progressHigh;
                low_age.setText(a+"--");
                high_age.setText(b+"");

            }
        });

    }
}
