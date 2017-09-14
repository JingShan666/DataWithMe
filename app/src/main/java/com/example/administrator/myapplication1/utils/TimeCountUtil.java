package com.example.administrator.myapplication1.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

import com.example.administrator.myapplication1.R;

/**
 * Created by Liu on 2017/6/23 0023.
 */

public class TimeCountUtil extends CountDownTimer {
    private Activity mActivity;
    private Button btn;//按钮

    // 在这个构造方法里需要传入三个参数，一个是Activity，一个是总的时间millisInFuture，一个是countDownInterval，然后就是你在哪个按钮上做这个是，就把这个按钮传过来就可以了
    public TimeCountUtil( Activity mActivity,long millisInFuture, long countDownInterval,Button btn) {
        super(millisInFuture, countDownInterval);
        this.mActivity = mActivity;
        this.btn =btn;
    }


    @SuppressLint("NewApi")
    @Override
    public void onTick(long millisUntilFinished) {
        btn.setClickable(false);//设置不能点击
        btn.setText(millisUntilFinished / 1000 + "秒");//设置倒计时时间

        //设置按钮为灰色，这时是不能点击的
        btn.setBackground(mActivity.getResources().getDrawable(R.color.secondary_text));

        SpannableString  span = new SpannableString(btn.getText().toString());//获取按钮的文字
        span.setSpan(new ForegroundColorSpan(Color.RED), 0,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//讲倒计时时间显示为红色
        btn.setText(span);

    }


    @SuppressLint("NewApi")
    @Override
    public void onFinish() {
        btn.setText("重新获取验证码");
        btn.setClickable(true);//重新获得点击
        btn.setBackground(mActivity.getResources().getDrawable(R.color.sky_blue));//还原背景色

    }


}
