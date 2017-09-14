package com.example.administrator.myapplication1.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.myapplication1.MainActivity;
import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.constant.NetConstant;
import com.example.administrator.myapplication1.utils.CodeUtils;
import com.example.administrator.myapplication1.utils.TimeCountUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.Calendar;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


/**
 * Created by Liu on 2017/6/22 0022.
 * 个人信息注册界面
 * 用户注册接口:https://www.showdoc.cc/page/15046126
 */

public class RegisterActivity extends AppCompatActivity {
    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;

    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_mid;
    private EditText et_date;
    private Button bt_register;
    private Button bt_getcode;
    private EditText et_pwd1;
    private EditText et_pwd2;
    private ImageView iv_pwd1;
    private ImageView iv_pwd2;
    private EditText et_phone;
    private EditText et_code;
    private EditText et_nickName;

    //输入验证码对话框
    private Dialog mDialog;

    private boolean isHidden = false;
    EventHandler eh;

    private String emailNum;

    private RadioGroup rg_sexes;
    private RadioButton rg_man;
    private RadioButton rg_womam;

    private int checkSex;

    private Button get_ImgCode;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailNum = getIntent().getStringExtra("email");
        initView();
    }

    private void initView() {
        // 创建EventHandler对象
        eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        //Toast.makeText(RegisterActivity.this,"获取验证码成功",Toast.LENGTH_LONG).show();
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调


        iv_left = (ImageView) findViewById(R.id.imageView_left);
        iv_right = (ImageView) findViewById(R.id.imageView_right);

        iv_right.setVisibility(View.INVISIBLE);
        et_date = (EditText) findViewById(R.id.date);
        bt_register = (Button) findViewById(R.id.register);
        tv_mid = (TextView) findViewById(R.id.tv_middle);
        tv_mid.setText("注册");
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_code = (EditText) findViewById(R.id.et_code);
        et_nickName = (EditText) findViewById(R.id.et_nike_name);

        iv_left = (ImageView) findViewById(R.id.imageView_left);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rg_sexes = (RadioGroup) findViewById(R.id.rg_sexes);
        rg_man = (RadioButton) findViewById(R.id.man);
        rg_womam = (RadioButton) findViewById(R.id.woman);

        //密码框相关处理
        et_pwd1 = (EditText) findViewById(R.id.pwd1);
        et_pwd2 = (EditText) findViewById(R.id.pwd2);
        iv_pwd1 = (ImageView) findViewById(R.id.pwd_look);
        iv_pwd2 = (ImageView) findViewById(R.id.pwd_look2);

        get_ImgCode= (Button) findViewById(R.id.get_imgcode);

        get_ImgCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showVerifyCodeDialog();
            }
        });

        iv_pwd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHidden) {
                    //设置EditText文本为可见的
                    et_pwd1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置EditText文本为隐藏的
                    et_pwd1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isHidden = !isHidden;
                iv_pwd1.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = et_pwd1.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });
        iv_pwd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHidden) {
                    //设置EditText文本为可见的
                    et_pwd2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置EditText文本为隐藏的
                    et_pwd2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isHidden = !isHidden;
                iv_pwd2.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = et_pwd2.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });
        bt_getcode = (Button) findViewById(R.id.get_code);
        bt_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断手机号是否为空
                if (et_phone.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                TimeCountUtil timeCountUtil = new TimeCountUtil(RegisterActivity.this, 61000, 1000, bt_getcode);
                timeCountUtil.start();
                smsCheck();
            }
        });

        //点击提交按钮，向服务器提交数据
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });


        /////////显示日期控件
        //iv_date= (ImageView) findViewById(R.id.choose_date);
        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG);
            }
        });

        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

    }

    private void showVerifyCodeDialog() {

        //获取token
        getToken();


        if (mDialog == null || !mDialog.isShowing()) {
            mDialog = new Dialog(this, R.style.alert_dialog);
            mDialog.setContentView(R.layout.verify_code_dialog);
        }
        ImageView verifyCodeImg =  mDialog.findViewById(R.id.verify_code_img);
        verifyCodeImg.setImageBitmap(CodeUtils.getInstance().createBitmap());

        if (!mDialog.isShowing()) {
            mDialog.show();
        }

        verifyCodeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView verifyCodeImg =  mDialog.findViewById(R.id.verify_code_img);
                verifyCodeImg.setImageBitmap(CodeUtils.getInstance().createBitmap());
            }
        });
    }

    private void getToken() {
        String url = NetConstant.baseUrl + NetConstant.getVerifyCode;
        OkHttpUtils
                .get()
                .url(url)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                        Log.e("获取token：",response);

                    }
                });



    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    //点击获取验证码
    private void smsCheck() {

        String number = et_phone.getText().toString().trim();

        SMSSDK.getVerificationCode("86", number);

    }
    private void submitData() {


        //获取注册信息
        //邮箱号 emailNum
        //手机号
        String phoneNumber = et_phone.getText().toString().trim();
        //验证码
        String code = et_code.getText().toString().trim();
        //密码
        String password = et_pwd1.getText().toString().trim();
        //确认密码
        String confirmPassword = et_pwd2.getText().toString().trim();
        //昵称
        String nickName = et_nickName.getText().toString().trim();
        //生日
        String birthirday = et_date.getText().toString().trim();

        //性别 checkSex
        rg_sexes.setOnCheckedChangeListener(mChangeRadio);


        //进行非空判断
        if (phoneNumber.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (code.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;

        } else if (password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;

        } else if (confirmPassword.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;

        } else if (nickName.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
            return;

        } else if (birthirday.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "生日不能为空", Toast.LENGTH_SHORT).show();
            return;

        } else if (checkSex != 1 && checkSex != 2) {
            Toast.makeText(RegisterActivity.this, "请选择您的性别", Toast.LENGTH_SHORT).show();
            return;

        }

//        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//        startActivity(intent);

        //联网，提交服务器

        String url = NetConstant.baseUrl + NetConstant.registerUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("email", emailNum)
                .addParams("birthday", birthirday)
                .addParams("nickname", nickName)
                .addParams("gender", checkSex + "")
                .addParams("phonenum", phoneNumber)
                .addParams("password", password)
                .addParams("password1", confirmPassword)
                .addParams("mobileCode", code)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                        Log.e("注册返回信息：",response);
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);

                    }
                });
    }

    private RadioGroup.OnCheckedChangeListener mChangeRadio = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // TODO Auto-generated method stub
            if (checkedId == rg_man.getId()) {
                // 把mRadio1的内容传到mTextView1
                checkSex = 1;
            } else if (checkedId == rg_womam.getId()) {
                // 把mRadio2的内容传到mTextView1
                checkSex = 2;
            }
        }
    };

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        et_date.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }


}
