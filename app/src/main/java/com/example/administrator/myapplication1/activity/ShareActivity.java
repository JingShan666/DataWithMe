package com.example.administrator.myapplication1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.bean.ShareModel;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;



/**
 * Created by Liu on 2017/6/22 0022.
 * 分享Activity
 */

public class ShareActivity extends AppCompatActivity {

    /** 短信分享对象 */
    private Platform platform_shortMessage;
    private PlatformActionListener platformActionListener;
    /** 分享的网址部分 */
    private Platform.ShareParams shareParams;
    private String text = "一个好玩的社交平台分享给你";
    private String imageurl = "http://h.hiphotos.baidu.com/image/pic/item/ac4bd11373f082028dc9b2a749fbfbedaa641bca.jpg";
    private String title = "DataWithMe";
    private String url = "www.baidu.com";

    private LinearLayout shareToPhone;
    private LinearLayout shareToQQ;
    private LinearLayout shareToWeiChat;

    private ImageView back;
    private TextView tv_share;
    private TextView tv_right;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_share);

        initView();
    }

    private void initView() {
        shareToPhone= (LinearLayout) findViewById(R.id.share_phone);
        shareToQQ= (LinearLayout) findViewById(R.id.share_qq);
        shareToWeiChat= (LinearLayout) findViewById(R.id.share_wechat);

        back= (ImageView) findViewById(R.id.imageView_left);

        tv_share= (TextView) findViewById(R.id.tv_middle);

        tv_right= (TextView) findViewById(R.id.tv_right);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_share.setText("分享");
        tv_right.setVisibility(View.GONE);

        clickEvent();

    }
    /**
     * 初始化分享参数
     *
     * @param shareModel
     */
    public void initShareParams(ShareModel shareModel) {
        if (shareModel != null) {
            Platform.ShareParams sp = new Platform.ShareParams();
            sp.setShareType(Platform.SHARE_TEXT);
            sp.setShareType(Platform.SHARE_WEBPAGE);

            sp.setTitle(shareModel.getText());
            sp.setText(shareModel.getText());
            sp.setUrl(shareModel.getUrl());
            sp.setImageUrl(shareModel.getImageUrl());
            shareParams = sp;
        }
    }

    private void clickEvent() {
        ShareModel model = new ShareModel();



        model.setImageUrl(imageurl);
        model.setText(text);
        model.setTitle(title);
        model.setUrl(url);
        initShareParams(model);

        shareToPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Platform.ShareParams sp = new Platform.ShareParams();
                sp.setAddress("");
                sp.setText(shareParams.getText()+"这是网址《"+shareParams.getUrl()+"》很给力哦！");

                Platform circle = ShareSDK.getPlatform("ShortMessage");
                circle.setPlatformActionListener(platformActionListener); // 设置分享事件回调
                // 执行图文分享
                circle.share(sp);
            }
        });
        shareToQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Platform.ShareParams sp = new Platform.ShareParams();
                sp.setTitle(shareParams.getTitle());
                sp.setTitleUrl(shareParams.getUrl()); // 标题的超链接
                sp.setText(shareParams.getText());
                sp.setImageUrl(shareParams.getImageUrl());
                sp.setComment("我对此分享内容的评论");
                sp.setSite(shareParams.getTitle());
                sp.setSiteUrl(shareParams.getUrl());
                Platform qq = ShareSDK.getPlatform("QQ");
                qq.setPlatformActionListener(platformActionListener);
                qq.share(sp);
            }
        });


        shareToWeiChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Platform plat = null;
                plat = ShareSDK.getPlatform("Wechat");
                if (platformActionListener != null) {
                    plat.setPlatformActionListener(platformActionListener);
                }

                plat.share(shareParams);
            }
        });
    }



}
