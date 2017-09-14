package com.example.administrator.myapplication1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.bean.ImgLoadBean;
import com.example.administrator.myapplication1.constant.NetConstant;
import com.example.administrator.myapplication1.db.DBServer;
import com.example.administrator.myapplication1.db.Photo;
import com.example.administrator.myapplication1.utils.FinishProjectPopupWindows;
import com.example.administrator.myapplication1.utils.pager.GridAdapter;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/7/8 0008.
 * 相册界面
 */


public class PhotoActivity extends AppCompatActivity{

    private GridView gridView;

    private View mView;

    private ImageView iv_back;
    private ImageView iv_add;
    private TextView tv_title;

    private GridAdapter gridAdapter;

    private List<String> mDrawableList;


    private static final String TAG = "FinishProjectPopupWindows";

    private static List<String> userPhotoNums = new ArrayList<>();




    private LayoutInflater mInflater;

    private String userId;

    private List<Photo> photoData =new ArrayList<Photo>();
    private DBServer db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);


        initView();

        //查询数据库
        queryDb();

    }

    private void queryDb() {


        photoData= db.findAllPhotoFiles();

        if (photoData.size()>0){
            for(int i=0;i<photoData.size();i++)
            {
                Log.e("所有相册信息：：：",'\n'+photoData.get(i).toString());
            }
        }
        String url = NetConstant.baseUrl + NetConstant.selectphotoinfo;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("userid",userId)
                .build()
                .execute(new StringCallback() {


                    @Override
                    public void onError(Request request, Exception e) {

                    }
                    @Override
                    public void onResponse(String response) {

                        Log.e("查询相册返回信息9999：", response);

                    }
                });

    }




    private void initView() {
//        dbHelper = new MyDatabaseHelper(this,"Photo.db",null,1);
//        dbHelper.getWritableDatabase();

        //创建相册提交服务器
        SharedPreferences share = getSharedPreferences("UID", MODE_PRIVATE);
         userId = share.getString("uId", "");

        db=new DBServer(this);

        mInflater = LayoutInflater.from(this);


        gridView= (GridView) findViewById(R.id.gv_photo);

        iv_back= (ImageView) findViewById(R.id.imageView_left);
        iv_add= (ImageView) findViewById(R.id.imageView_right);
        tv_title= (TextView) findViewById(R.id.tv_middle);


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_title.setText("相册");


        dealWithGridView();

        //取得GridView对象
        mDrawableList = new ArrayList<>();


        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final View meDeView= mInflater.inflate(R.layout.dialog_one,null);
                EditText editText= meDeView.findViewById(R.id.about_me);
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)}); //即限定最大输入字符数为5
                final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(PhotoActivity.this);
                dialogBuilder
                        .withTitle("创建相册")
                        .withTitleColor(PhotoActivity.this.getResources().getColor(R.color.window_background))
                        .withDividerColor(PhotoActivity.this.getResources().getColor(R.color.light_grey))
                        .withMessage("请输入相册名字(5个字内)")
                        .withMessageColor(PhotoActivity.this.getResources().getColor(R.color.window_background))
                        .withDialogColor(PhotoActivity.this.getResources().getColor(R.color.sky_blue))
                        //.withIcon(getResources().getDrawable(R.drawable.icon))
                        .withDuration(500)
                        .withEffect(Effectstype.Fall)
                        .withButton1Text("确定")
                        .withButton2Text("取消")
                        .isCancelableOnTouchOutside(true)
                        .setCustomView(meDeView,PhotoActivity.this)
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final EditText editText= meDeView.findViewById(R.id.about_me);
                                String photoName= editText.getText().toString().trim();
                                if (photoName.length()==0){
                                    Toast.makeText(PhotoActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
                                }else {
//                                    imageView.setVisibility(View.GONE);
//                                    textView.setText(me);
                                    mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");


                                    userPhotoNums.add(photoName+"(0)");


                                    for(int i=0;i<userPhotoNums.size();i++)
                                    {

                                        Log.e("相册名字：：：",userPhotoNums.get(i));

                                        //photoName = userPhotoNums.get(i);
                                    }


                                    addPhotoToNet(userId,photoName);

                                    Log.e("mDrawableList的长度：",mDrawableList.size()+"");

                                    gridAdapter.notifyDataSetChanged();

                                    //添加到数据库

                                    addToDb(mDrawableList,userPhotoNums);

                                    dialogBuilder.dismiss();
                                }
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

    private void addPhotoToNet(String userId, String photoName) {

        //联网，提交服务器

        String url = NetConstant.baseUrl + NetConstant.savephotoinfo;



        OkHttpUtils
                .get()
                .url(url)
                .addParams("userid",userId)
                .addParams("path", photoName)
                .build()
                .execute(new StringCallback() {


                    @Override
                    public void onError(Request request, Exception e) {

                    }
                    @Override
                    public void onResponse(String response) {

                        Log.e("新建相册返回信息9999：", response);

                    }
                });



    }

    private void addToDb(List<String> mDrawableList, List<String> photoNums) {


        Photo photo=new Photo();
        for(int i=0;i<mDrawableList.size();i++)
        {

            photo.setPhotoUrl(mDrawableList.get(i));
            photo.setPhotoName(photoNums.get(i));
            //photo.setPhotoId("00"+i);

            db.addPhotoFile(photo);

        }




    }


    private void dealWithGridView() {
        //取得GridView对象
        mDrawableList = new ArrayList<>();

        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");
        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");

        Log.e("mDrawableList的长度6666：",mDrawableList.size()+"");
        userPhotoNums.add("默认(4)");
        userPhotoNums.add("默认(4)");



        gridAdapter= new GridAdapter(PhotoActivity.this, userPhotoNums, mDrawableList,100);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Toast.makeText(PhotoActivity.this, position + "", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(PhotoActivity.this,PictureActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void clearList(List<String> list) {
        int size = list.size();
        if (size > 0) {
            list.removeAll(list);
            gridAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearList(userPhotoNums);

        //删除数据库

        db.deletePhotoFile(null);

    }
}
