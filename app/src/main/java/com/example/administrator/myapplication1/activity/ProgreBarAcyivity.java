package com.example.administrator.myapplication1.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;

/**
 * Created by Liu on 2017/7/4 0004.
 * 加载框
 */

public class ProgreBarAcyivity extends AppCompatActivity{


    private Dialog progressDialog;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *
     * @Description: TODO 自定义加载提示内容
     * @param @param id
     * @return void 用法buildProgressDialog(R.string.loding)
     * @throws
     * @author Sunday
     * @date 2015年12月25日
     */
    public void buildProgressDialog(int id) {
        if (progressDialog == null) {
            progressDialog = new Dialog(this, R.style.progress_dialog);
        }
        progressDialog.setContentView(R.layout.dialog_my);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        TextView msg = progressDialog
                .findViewById(R.id.id_tv_loadingmsg);
        msg.setText(getString(id));
        progressDialog.show();
    }

    /**
     * @Description: TODO 固定加载提示内容
     * @author Sunday
     */
    public void buildProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(this, R.style.progress_dialog);
        }
        progressDialog.setContentView(R.layout.dialog_my);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        TextView msg = (TextView) progressDialog
                .findViewById(R.id.id_tv_loadingmsg);
        msg.setText("卖力加载中");
        progressDialog.show();
    }

    /**
     * @Description: TODO 取消加载框
     * @author Sunday
     * @date 2015年12月25日
     */
    public void cancelProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
