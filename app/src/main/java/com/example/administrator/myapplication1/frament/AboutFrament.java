package com.example.administrator.myapplication1.frament;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.utils.DialogUtils;
import java.util.Calendar;
import android.app.DatePickerDialog;

/**
 * Created by Liu on 2017/6/28 0028.
 * 5.0dialog
 * http://blog.csdn.net/liujingxing93/article/details/51933679
 *
 * https://github.com/sd6352051/NiftyDialogEffects
 */

public class AboutFrament extends Fragment{
    private View mView;
    private LayoutInflater mInflater;

    private RelativeLayout me;
    private RelativeLayout age;
    private RelativeLayout dollar;
    private RelativeLayout job;
    private RelativeLayout education;
    private RelativeLayout love_state;
    private RelativeLayout love_sex;

    private RelativeLayout height;
    private RelativeLayout weight;

    private RelativeLayout city;


    private Calendar calendar = null;


    public static final String DATEPICKER_TAG = "datepicker";
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null)
        {
            mInflater = LayoutInflater.from(getActivity());
            mView = inflater.inflate(R.layout.fragment_about, container, false);
//            initData();
           initView();

        }
        return mView ;
    }

    private void initView() {

        //关于自己
        me= mView.findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View meDeView= mInflater.inflate(R.layout.dialog_me,null);
                ImageView imageView= mView.findViewById(R.id.imageView);
                TextView textView= mView.findViewById(R.id.introduce);
                DialogUtils.showEditDialog(getContext(),meDeView,mView,"介绍","请简要介绍自己(100字内)",
                imageView,textView);
            }
        });
        //年龄
        age= mView.findViewById(R.id.age2);
        age.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                calendar= Calendar.getInstance();
               // 取得系统年份:
                final int year = calendar.get(Calendar.YEAR);

                //几种主题：http://www.cnblogs.com/huanyou/p/5087044.html
                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        DatePickerDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        TextView showAge= mView.findViewById(R.id.show_age);
                        showAge.setText(year-i +"");

                        Log.e("日期：",i+"年"+i1+"月"+"日"+i2);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();

            }
        });


        //身高
        height= mView.findViewById(R.id.height);

        height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1= mInflater.inflate(R.layout.money_list,null);
                final ListView listView= view1.findViewById(R.id.lv_info);

                final String[] arrayList = new String[] {
                        "低于 150 cm", "151- 156 cm", "161- 170 cm", "171- 180 cm", "181- 190 cm", "高于190 cm"};
                ImageView imageView= mView.findViewById(R.id.imageView_height);
                imageView.setVisibility(View.GONE);
                TextView textView= mView.findViewById(R.id.textView_height);
                textView.setVisibility(View.VISIBLE);
                DialogUtils.showListDialog(getContext(),arrayList,listView,view1,mView,"身高","请选择您的身高",textView);
            }
        });


        //体重
        weight= mView.findViewById(R.id.weight);

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1= mInflater.inflate(R.layout.money_list,null);
                final ListView listView= view1.findViewById(R.id.lv_info);

                final String[] arrayList = new String[] {
                        "低于40 kg(less than 88 pounds)", "41-50 kg(90-110 pounds)", "51-60 kg(111-132 pounds)",
                        "61-70 kg(133-155 pounds)", "71-80 kg(156-176 pounds)", "81-90 kg(177-199 pounds)",
                        "51-60 kg(91-100 pounds)", "高于100 kg(more than 221 pounds)"};
                ImageView imageView= mView.findViewById(R.id.imageView_weight);
                imageView.setVisibility(View.GONE);
                TextView textView= mView.findViewById(R.id.textView_weight);
                textView.setVisibility(View.VISIBLE);
                DialogUtils.showListDialog(getContext(),arrayList,listView,view1,mView,"体重","请选择您的体重",textView);
            }
        });

        //性取向
        love_sex= mView.findViewById(R.id.love_sex);

        love_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1= mInflater.inflate(R.layout.money_list,null);
                final ListView listView= view1.findViewById(R.id.lv_info);
                ImageView imageView= mView.findViewById(R.id.imageView_sex);
                imageView.setVisibility(View.GONE);
                TextView textView= mView.findViewById(R.id.textView_sex);
                textView.setVisibility(View.VISIBLE);
                final String[] arrayList = new String[] {
                        "异性", "开放", "双性", "同性"};
                DialogUtils.showListDialog(getContext(),arrayList,listView,view1,mView,"性取向","请选择您的性取向",textView);
            }
        });


        //感情状态
        love_state= mView.findViewById(R.id.love_state);

        love_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1= mInflater.inflate(R.layout.money_list,null);
                final ListView listView= view1.findViewById(R.id.lv_info);

                final String[] arrayList = new String[] { "单身", "恋爱中", "订婚","已婚", "不好说", "开放式的交往关系",
                        "丧偶", "分局", "离婚", "同性伴侣"};
                TextView textView= mView.findViewById(R.id.love);
                DialogUtils.showListDialog(getContext(),arrayList,listView,view1,mView,"感情状态","请选择您目前的感情状态",textView);
            }
        });

        //城市
        city= mView.findViewById(R.id.city);
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final View meDeView= mInflater.inflate(R.layout.dialog_me,null);

                ImageView imageView= mView.findViewById(R.id.imageView_city);
                imageView.setVisibility(View.GONE);
                TextView textView= mView.findViewById(R.id.textView_city);
                textView.setVisibility(View.VISIBLE);
                DialogUtils.showEditDialog(getContext(),meDeView,mView,"城市","请输入您所在的城市",
                        imageView,textView);
            }
        });



        //教育
        education= mView.findViewById(R.id.education);
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1= mInflater.inflate(R.layout.money_list,null);
                final ListView listView= view1.findViewById(R.id.lv_info);

                final String[] arrayList = new String[] { "中小学", "专科/技校", "学院/大学",
                        "更高学位"};
                TextView textView= mView.findViewById(R.id.edu_level);
                DialogUtils.showListDialog(getContext(),arrayList,listView,view1,mView,"教育","请选择您受教育的水平",textView);
            }
        });

        //工作
        job= mView.findViewById(R.id.job);
        job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView imageView= mView.findViewById(R.id.imageView_job);
                TextView textView= mView.findViewById(R.id.work1);
                final View meDeView= mInflater.inflate(R.layout.dialog_me,null);
                DialogUtils.showEditDialog(getContext(),meDeView,mView,"工作","请简要介绍自己的工作(100字内)",
                        imageView,textView);
            }
        });

        //收入
        dollar= mView.findViewById(R.id.dollar);
        dollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1= mInflater.inflate(R.layout.money_list,null);
                final ListView listView= view1.findViewById(R.id.lv_info);
                final String[] arrayList = new String[] { "低于$30,000", "$30,000-$45,000", "$45,000-$60,000",
                        "$60,000-$75,000","$75,000-$150,000", "高于$150,000"};
                TextView textView= mView.findViewById(R.id.dollar_me);
                DialogUtils.showListDialog(getContext(),arrayList,listView,view1,mView,"收入","请选择您的收入",textView);
            }
        });
    }


}
