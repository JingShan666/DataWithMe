package com.example.administrator.myapplication1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.constant.NetConstant;
import com.example.administrator.myapplication1.utils.GetFileByUri;
import com.example.administrator.myapplication1.utils.ImageLoder;
import com.example.administrator.myapplication1.utils.ToRoundImage;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;


/**
 * Created by Administrator on 2017/7/5 0005.
 * 发布新鲜事界面
 */

public class SubNewThing extends AppCompatActivity {

    private ImageView iv_smile;
    private ImageView iv_photo;
    private ImageView iv_earth;
    private ImageView iv_take_photo;

    private TextView tv_cancel;
    private TextView tv_ok;

    private EditText et_content;

    private Bitmap bitmap1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        initView();

    }

    private void initView() {
        iv_smile= (ImageView) findViewById(R.id.smile);
        iv_photo= (ImageView) findViewById(R.id.photo);
        iv_earth= (ImageView) findViewById(R.id.earth);
        iv_take_photo= (ImageView) findViewById(R.id.take_photo);

        tv_cancel= (TextView) findViewById(R.id.tv_cancel);
        tv_ok= (TextView) findViewById(R.id.tv_ok);

        et_content= (EditText) findViewById(R.id.input_word);

        clickEvent();

    }

    private void clickEvent() {

        //表情
        iv_smile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //相机/册
        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity(null)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .start(SubNewThing.this);
            }
        });

        //翻译
        iv_earth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //取消
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        //完成
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //提交自己发布的新鲜事
                subMitNewThing();

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                //回调之后显示到相应的imageView上
                if (result.getUri()!=null){
                    iv_take_photo.setVisibility(View.VISIBLE);
                    iv_take_photo.setImageURI(result.getUri());
                }



                //获取图片真实路径
                String path= GetFileByUri.getFileByUri(result.getUri(),getApplicationContext()).getPath();
                Log.e("图片SubNewThingpath:::::",path);
                Log.e("SubNewThing图片:::",result.getUri()+"");

                //上传图片//////////////////////////////////////////////////////////////////

                SharedPreferences share = getSharedPreferences("UID", MODE_PRIVATE);
                String userId = share.getString("uId", "");
                Log.e("上传图片返回信息userId6666666：", userId);
                ImageLoder.startLoad(userId,path,"xinxianshi");

                Toast.makeText(this, "设置成功: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "设置失败: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void subMitNewThing() {

        SharedPreferences share = getApplication().getSharedPreferences("UID", MODE_PRIVATE);
        String id = share.getString("id", "");

        String content= et_content.getText().toString().trim();


        Log.e("上传个人新鲜事id：",id);
        Log.e("上传个人新鲜事content：",content);
        String url = NetConstant.baseUrl + NetConstant.subNewThing;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("userid", id)
                .addParams("text", content)
                .addParams("photourl", ImageLoder.getUrl())
                .addParams("type", 1+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                        Log.e("获取所有新鲜事：",response);

                    }
                });


    }
}

