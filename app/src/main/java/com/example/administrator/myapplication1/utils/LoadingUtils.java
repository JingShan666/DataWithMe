package com.example.administrator.myapplication1.utils;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;

/**
 * Created by Liu on 2017/7/6 0006.
 * 加载框
 */

public class LoadingUtils {

    private static Dialog progressDialog;

    public static void showDialog(Context context,String loadText){
        progressDialog = new Dialog(context, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg =  progressDialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText(loadText);
        progressDialog.show();
    }


    public static void cencelDialog(){
        progressDialog.dismiss();
    }
}
