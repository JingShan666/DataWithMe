package com.example.administrator.myapplication1.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.frament.AboutFrament;
import com.example.administrator.myapplication1.frament.FreashThingFragment;
import com.example.administrator.myapplication1.frament.MenuLeftFragment;
import com.example.administrator.myapplication1.frament.MenuRightFragment;
import com.example.administrator.myapplication1.frament.dialogFragment.PagerDialogFragment;
import com.example.administrator.myapplication1.utils.GetFileByUri;
import com.example.administrator.myapplication1.utils.ImageLoder;
import com.example.administrator.myapplication1.utils.ToRoundImage;
import com.example.administrator.myapplication1.utils.pager.AdUrlViewpagerUtil;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on Liu/6/20 0020.
 * 个人信息activity
 *
 * 参考：http://blog.csdn.net/lf0814/article/details/52473678
 * http://blog.csdn.net/lf0814/article/details/52473678
 * http://blog.csdn.net/q9104422999/article/details/52896063
 *
 *
 * 图片剪裁
 * http://p.codekk.com/detail/Android/ArthurHub/Android-Image-Cropper
 *  *
 https://github.com/TangXiaoLv/TelegramGallery
 *
 */

public class UserInfoActivity extends SlidingFragmentActivity implements PagerDialogFragment.UrlsChangerListener {
    private CoordinatorLayout coordinatorlayout;
    private Toolbar mToolbar;
    private AppBarLayout appbarlayout;
    private CollapsingToolbarLayout tablayout;

    private LinearLayout ll_start_chat;
    private LinearLayout ll_online;
    private LinearLayout ll_fresh_thing;

    private FrameLayout frameLayout;

    private AboutFrament aboutFrament;
    private Fragment mContent;


    private ImageView imageView1;
    private ImageView imageView2;

    private Bitmap bitmap1;



    private View line_right;
    private View line_left;

    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;

    //顶部viewPager
    private ViewPager viewpager;
    private LinearLayout lydots;
    private TextView tvtxt;
    private List<String> urls;

    private AdUrlViewpagerUtil adUrlViewpagerUtil;



    private ImageView customImageView1;

    private LinearLayout ll_photo;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.titlebar_layout);

        setContentView(R.layout.activity_user_info);
        initView();


    }
    private void initView() {

        imageView1= (ImageView) findViewById(R.id.imageView1);
        imageView2= (ImageView) findViewById(R.id.imageView2);

        changeFragment1(imageView1,imageView2);


        //顶部viewPager相关
        initPager();
        line_right= findViewById(R.id.line_right);
        line_left= findViewById(R.id.line_left);
        urls = new ArrayList<>();


        frameLayout= (FrameLayout) findViewById(R.id.fl_about_me);
        coordinatorlayout= (CoordinatorLayout) findViewById(R.id.main_content);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        appbarlayout= (AppBarLayout) findViewById(R.id.appbar);

        tablayout= (CollapsingToolbarLayout) findViewById(R.id.tabs);

        ll_online= (LinearLayout) findViewById(R.id.ll_online);
        ll_fresh_thing= (LinearLayout) findViewById(R.id.ll_fresh_thing);
        //viewpager= (ViewPager) findViewById(R.id.viewpager);

//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mToolbar.setTitle("5.0");

        ll_start_chat= (LinearLayout) findViewById(R.id.start_chat);
        //发起聊天
        ll_start_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入聊天界面
                startTalk();
            }
        });


        //点击切换fragment
        changeFragment();


        //4个按钮点击事件

        clickEvent();

    }

    private void clickEvent() {
        //相册
        ll_photo= (LinearLayout) findViewById(R.id.gallery);
        ll_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(UserInfoActivity.this,PhotoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void changeFragment1(ImageView imageView1, ImageView imageView2) {

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


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSlidingMenu().showMenu();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSlidingMenu().showSecondaryMenu();
            }
        });

    }


    private void initPager() {


        customImageView1= (ImageView) findViewById(R.id.img_me);

        customImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity(null)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(UserInfoActivity.this);
            }
        });



        viewpager = (ViewPager) findViewById(R.id.vp_viewpager);

        lydots = (LinearLayout) findViewById(R.id.ly_dots);
        tvtxt = (TextView) findViewById(R.id.indicator_tv);
        tvtxt.setText(1+"/"+1);
        //让导航圆点消失
        lydots.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                //回调之后显示到相应的imageView上
                customImageView1.setImageURI(result.getUri());

                String path= GetFileByUri.getFileByUri(result.getUri(),getApplicationContext()).getPath();
                Log.e("图片path:::;;;;:::：",path);
                Log.e("图片000:::;;;;:::：",result.getUri()+"");
                String url1= result.getUri()+"";

                urls.add(url1);

                //上传图片//////////////////////////////////////////////////////////////////

                SharedPreferences share = getSharedPreferences("UID", MODE_PRIVATE);
                String userId = share.getString("uId", "");
                Log.e("上传图片返回信息userId6666666：", userId);
                ImageLoder.startLoad(userId,path,"load");

                dealWithPager(urls);


                Bitmap bitmap = ((BitmapDrawable)customImageView1.getDrawable()).getBitmap();
                //转换为圆形
                bitmap1=  ToRoundImage.toRoundBitmap(bitmap);

                customImageView1.setImageBitmap(bitmap1);


                Toast.makeText(this, "设置成功: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "设置失败: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }




    private void dealWithPager(List<String> urls){

        tvtxt.setText(1+"/"+urls.size());

        //带导航圆点
        adUrlViewpagerUtil = new AdUrlViewpagerUtil(this, viewpager, lydots, 8, 4, urls,100);
        adUrlViewpagerUtil.initVps(11);
        //ViewPager页面点击事件

        final List<String> finalUrls1 = urls;
        adUrlViewpagerUtil.setOnAdItemClickListener(new AdUrlViewpagerUtil.OnAdItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(UserInfoActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();

                //Intent intent= new Intent(UserInfoActivity.this,BigImgActivity.class);

                Log.e("position5555",position+"");

                PagerDialogFragment pagerDialogFragment= new PagerDialogFragment(finalUrls1,position);
                pagerDialogFragment.show(getSupportFragmentManager(),"dialog");

            }
        });

        final List<String> finalUrls = urls;
        adUrlViewpagerUtil.setOnAdPageChangeListener(new AdUrlViewpagerUtil.OnAdPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                if(arg0 == 0)
                    arg0 = 1;
                if(arg0 == finalUrls.size() + 1)
                {
                    arg0 = finalUrls.size();
                }
                tvtxt.setText(arg0+"/"+ finalUrls.size());

            }
        });

    }


    private void changeFragment() {

        //默认点击第一条
        line_right.setVisibility(View.GONE);
        aboutFrament= new AboutFrament();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_about_me, aboutFrament).commit();


        ll_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                line_left.setVisibility(View.VISIBLE);
                line_right.setVisibility(View.GONE);
                aboutFrament= new AboutFrament();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_about_me, aboutFrament).commit();


            }
        });

        ll_fresh_thing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                line_right.setVisibility(View.VISIBLE);
                line_left.setVisibility(View.GONE);
                FreashThingFragment freashThingFragment= new FreashThingFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_about_me, freashThingFragment).commit();

            }
        });

    }

    private void startTalk() {
        // 判断sdk是否登录成功过，并没有退出和被踢，否则跳转到登陆界面
//        if (!EMClient.getInstance().isLoggedInBefore()) {
//
//
//            Intent intent = new Intent(UserInfoActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }

        if (!TextUtils.isEmpty("555")){
            Intent chat = new Intent(this,ChatActivity.class);
            chat.putExtra(EaseConstant.EXTRA_USER_ID,"555");  //对方账号
            chat.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
            startActivity(chat);
        }else{
            Toast.makeText(UserInfoActivity.this, "请输入要聊天的对方的账号", Toast.LENGTH_SHORT).show();
        }


    }






    @Override
    public void OnUrlsChangerListener(final ArrayList newUrls,int a ) {

        Log.e("新的url",newUrls.size()+"");

        dealWithPager(newUrls);
    }
}

