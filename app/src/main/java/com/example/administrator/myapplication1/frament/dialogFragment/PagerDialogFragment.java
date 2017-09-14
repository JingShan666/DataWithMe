package com.example.administrator.myapplication1.frament.dialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.activity.UserInfoActivity;
import com.example.administrator.myapplication1.utils.pager.AdUrlViewpagerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liu on 2017/6/29 0029.
 */

public class PagerDialogFragment extends DialogFragment {
    private View mView;

    //顶部viewPager
    private ViewPager viewpager;
    private LinearLayout lydots;
    private TextView tvtxt;
    private AdUrlViewpagerUtil adUrlViewpagerUtil;
    private ImageView iv_a;
    private ImageView iv_b;


    private List<String> urls;
    private int position;

    private ImageView iv_cancel;


    public interface UrlsChangerListener
    {
        void OnUrlsChangerListener(ArrayList newUrls,int a);
    }




    private boolean isShow= false;

    public PagerDialogFragment(List<String> urls, int position) {

        this.urls= urls;
        this.position= position;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        mView = inflater.inflate(R.layout.layout_gallery, container);


        iv_cancel= mView.findViewById(R.id.iv_cancel);



        dealWithPager();
        return mView;
    }

    private void dealWithPager() {


        //urls = new ArrayList<>();

        if (urls.size()<=0){

            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            startActivity(intent);


        }




        viewpager =  mView.findViewById(R.id.vp_viewpager);
        lydots =  mView.findViewById(R.id.ly_dots);

        iv_a= mView.findViewById(R.id.iv_cancel);

        //删除图片
        iv_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelImage();

            }
        });


        tvtxt =  mView.findViewById(R.id.indicator_tv);

        tvtxt.setText(position+1+"/"+urls.size());

        //带导航圆点
        adUrlViewpagerUtil = new AdUrlViewpagerUtil(getContext(), viewpager, lydots, 8, 4, urls,position);
        adUrlViewpagerUtil.initVps(111);

        //viewpager.setCurrentItem(position);
        Log.e("........",position+"");


        //让导航圆点消失
        lydots.setVisibility(View.GONE);
        //ViewPager页面点击事件
        adUrlViewpagerUtil.setOnAdItemClickListener(new AdUrlViewpagerUtil.OnAdItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getContext(), "position:" + position, Toast.LENGTH_SHORT).show();

                if (!isShow){
                    iv_cancel.setVisibility(View.GONE);
                }else {
                    iv_cancel.setVisibility(View.VISIBLE);
                }
                isShow= !isShow;

            }
        });

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
                if(arg0 == urls.size() + 1)
                {
                    arg0 = urls.size();
                }
                tvtxt.setText(arg0+"/"+urls.size());
            }
        });
    }



    private void cancelImage() {

        dialog();
    }

    private void dialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("删除这张照片?"); //设置内容
        //builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                //Toast.makeText(getContext(), "确认" + which, Toast.LENGTH_SHORT).show();

                //更新viewPager数据

                upDataViewPager();


            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //Toast.makeText(getContext(), "取消" + which, Toast.LENGTH_SHORT).show();
            }
        });


        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }

    private void upDataViewPager() {


        int position= viewpager.getCurrentItem();

        if (urls.size()<=0){
            return;
        }

        urls.remove(position-1);
        Log.e("删除的position：",position+"");

        Log.e("urls的长度：",urls.size()+"");

        for (int a=0;a<urls.size();a++){

            Log.e("urls的图片：",urls.get(a)+"");

        }

        dealWithPager();

        UrlsChangerListener listener = (UrlsChangerListener) getActivity();

        listener.OnUrlsChangerListener((ArrayList) urls,100);
    }

}
