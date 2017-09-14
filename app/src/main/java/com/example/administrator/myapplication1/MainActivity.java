package com.example.administrator.myapplication1;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.myapplication1.frament.MenuLeftFragment;
import com.example.administrator.myapplication1.frament.MenuRightFragment;
import com.example.administrator.myapplication1.frament.NearFrament;
import com.example.administrator.myapplication1.frament.OnLineFrament;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    private LayoutInflater mInflater;
    private TextView tv_left;
    private TextView tv_right;
    private Fragment mContent;
    public static String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);           //设置标题栏样式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏

        setContentView(R.layout.activity_main);
        id= getIntent().getStringExtra("id");

        initView();

        //初始化Frament
        initFrament();


    }

    private void initView() {
        mInflater = LayoutInflater.from(this);
        tv_left= (TextView) findViewById(R.id.tv_left);
        tv_right= (TextView) findViewById(R.id.tv_right);


        //首页点击事件方法
        clickEvent();
    }

    private void initFrament() {
        //默认点击第一条
        OnLineFrament onLineFrament= new OnLineFrament();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_activity_main, onLineFrament).commit();


        findViewById(R.id.line_left).setVisibility(View.VISIBLE);

        Fragment leftMenuFragment = new MenuLeftFragment();
        setBehindContentView(R.layout.left_menu_frame);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//		menu.setBehindWidth()
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        // menu.setBehindScrollScale(1.0f);
        menu.setSecondaryShadowDrawable(R.drawable.shadow);
        //设置右边（二级）侧滑菜单
        menu.setSecondaryMenu(R.layout.right_menu_frame);
        Fragment rightMenuFragment = new MenuRightFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_right_menu_frame, rightMenuFragment).commit();

    }

    private void clickEvent() {
        findViewById(R.id.tv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OnLineFrament onLineFrament=null;
                if (onLineFrament== null){
                    onLineFrament= new OnLineFrament();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.id_activity_main, onLineFrament).commit();
                    findViewById(R.id.line_right).setVisibility(View.GONE);
                    findViewById(R.id.line_left).setVisibility(View.VISIBLE);
                }
            }
        });
        findViewById(R.id.tv_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NearFrament nearFrament=null;
                if (nearFrament== null){
                    nearFrament= new NearFrament();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.id_activity_main, nearFrament).commit();
                    findViewById(R.id.line_right).setVisibility(View.VISIBLE);
                    findViewById(R.id.line_left).setVisibility(View.GONE);
                }
            }
        });

        findViewById(R.id.imageView_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSlidingMenu().showMenu();
            }
        });
        findViewById(R.id.imageView_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSlidingMenu().showSecondaryMenu();
            }
        });

    }
    /**
     * 切换Fragment
     *
     * @param fragment
     */
    public void switchConent(Fragment fragment, String title) {
        mContent = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_activity_main, fragment).commit();
        getSlidingMenu().showContent();
        tv_left.setText(title);
        tv_right.setVisibility(View.GONE);
    }




}




