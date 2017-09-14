package com.example.administrator.myapplication1.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication1.R;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.hyphenate.chat.EMClient;

import ch.ielse.view.SwitchView;

/**
 * Created by Liu on 2017/7/3 0003.
 * 设置界面
 */

public class SetingActivity extends AppCompatActivity{


    private ch.ielse.view.SwitchView switchView1;
    private ch.ielse.view.SwitchView switchView2;
    private ch.ielse.view.SwitchView switchView3;


    private Vibrator vibrator;
    private MediaPlayer mp;


    private TextView tv_mid;
    private ImageView iv_right;
    private ImageView iv_left;

    private RelativeLayout exit;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seting);

        initView();


    }

    private void initView() {
        iv_left= (ImageView) findViewById(R.id.imageView_left);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_mid= (TextView) findViewById(R.id.tv_middle);
        tv_mid.setText("设置");
        iv_right= (ImageView) findViewById(R.id.imageView_right);
        iv_right.setVisibility(View.GONE);


        ////////////////////震动
        switchView1= (SwitchView) findViewById(R.id.switch_button1);

        switchView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpened = switchView1.isOpened();
            }
        });


        switchView1.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView view) {
                view.toggleSwitch(true); // or false


                 /*
                 * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
                * */
                vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                long [] pattern = {100,400};   // 停止 开启 停止 开启
                vibrator.vibrate(pattern,-1);           //重复两次上面的pattern 如果只想震动一次，index设为-1
            }

            @Override
            public void toggleToOff(SwitchView view) {
                view.toggleSwitch(false); // or true
                vibrator.cancel();
            }
        });



        //////////////////响铃
        switchView2= (SwitchView) findViewById(R.id.switch_button2);

        switchView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpened = switchView2.isOpened();

            }
        });


        switchView2.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView view) {
                view.toggleSwitch(true); // or false
                mp = new MediaPlayer();
                try {
                    mp.setDataSource(SetingActivity.this, RingtoneManager
                            .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
                    mp.prepare();
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void toggleToOff(SwitchView view) {
                view.toggleSwitch(false); // or true
                mp.stop();
            }
        });


        //隐身
        switchView3= (SwitchView) findViewById(R.id.switch_button3);

        switchView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpened = switchView3.isOpened();
            }
        });


        switchView3.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView view) {
                view.toggleSwitch(true); // or false
            }

            @Override
            public void toggleToOff(SwitchView view) {
                view.toggleSwitch(false); // or true
            }
        });


        exit= (RelativeLayout) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(SetingActivity.this);
                dialogBuilder
                        .withTitle("提示")                                  //.withTitle(null)  no title
                        .withTitleColor(SetingActivity.this.getResources().getColor(R.color.window_background))                                  //def
                        .withDividerColor(SetingActivity.this.getResources().getColor(R.color.window_background))                              //def
                        .withMessage("确定要注销账号吗？")                     //.withMessage(null)  no Msg
                        .withMessageColor(SetingActivity.this.getResources().getColor(R.color.window_background))                              //def  | withMessageColor(int resid)
                        .withDialogColor(SetingActivity.this.getResources().getColor(R.color.sky_blue))                               //def  | withDialogColor(int resid)
                        //.withIcon(getResources().getDrawable(R.drawable.icon))
                        .withDuration(700)                                          //def
                        .withEffect(Effectstype.Fall)                               //def Effectstype.Slidetop
                        .withButton1Text("确定")                                     //def gone
                        .withButton2Text("取消")                                     //def gone
                        .isCancelableOnTouchOutside(true)                           //def |isCancelable(true)
                        //.setCustomView(R.layout.custom_view,v.getContext())       //.setCustomView(View or ResId,context)
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EMClient.getInstance().logout(true);
                                dialogBuilder.dismiss();

                                Intent intent= new Intent(SetingActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setButton2Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogBuilder.dismiss();
                            }
                        })
                        .show();

            }
        });



    }





}
