package com.example.administrator.myapplication1.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.frament.MenuLeftFragment;
import com.example.administrator.myapplication1.frament.MenuRightFragment;
import com.example.administrator.myapplication1.frament.XieHouFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by Liu on 2017/7/3 0003.
 */

public class XieHouActivity extends SlidingFragmentActivity {


    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_mid;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiehou);

        initView();

        XieHouFragment xieHouFragment= new XieHouFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_activity_xiehou, xieHouFragment).commit();
    }

    private void initView() {
        iv_left= (ImageView) findViewById(R.id.imageView1);
        iv_right= (ImageView) findViewById(R.id.imageView2);
        tv_mid= (TextView) findViewById(R.id.tv_middle);
        tv_mid.setText("邂逅");

        changeFragment(iv_left,iv_right);


    }

    private void changeFragment(ImageView iv_left, ImageView iv_right) {


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


        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSlidingMenu().showMenu();
            }
        });

        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSlidingMenu().showSecondaryMenu();
            }
        });
    }
}
