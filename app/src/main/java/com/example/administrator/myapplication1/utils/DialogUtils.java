package com.example.administrator.myapplication1.utils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication1.R;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;


/**
 * Created by Liu on 2017/7/4 0004.
 * DIalog工具类
 * https://github.com/sd6352051/NiftyDialogEffects
 */

public class DialogUtils {

    public static void showEditDialog(final Context context, final View meDeView, final View mView,
                                      String title, String message, final ImageView imageView, final TextView textView){


        final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(context);
        dialogBuilder
                .withTitle(title)
                .withTitleColor(context.getResources().getColor(R.color.window_background))
                .withDividerColor(context.getResources().getColor(R.color.light_grey))
                .withMessage(message)
                .withMessageColor(context.getResources().getColor(R.color.window_background))
                .withDialogColor(context.getResources().getColor(R.color.sky_blue))
                //.withIcon(getResources().getDrawable(R.drawable.icon))
                .withDuration(500)
                .withEffect(Effectstype.Fall)
                .withButton1Text("确定")
                .withButton2Text("取消")
                .isCancelableOnTouchOutside(true)
                .setCustomView(meDeView,context)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final EditText editText= meDeView.findViewById(R.id.about_me);
                        String me= editText.getText().toString().trim();
                        if (me.length()==0){
                            Toast.makeText(context,"请输入内容",Toast.LENGTH_SHORT).show();
                        }else {
                            imageView.setVisibility(View.GONE);
                            textView.setText(me);
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


    public static void showListDialog(Context context, final String[] arrayList, ListView listView,
                                      View view1, final View mView, String title, String message, final TextView textView){

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(
                context, android.R.layout.simple_list_item_1,
                arrayList);
        listView.setAdapter(arrayAdapter);
        final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(context);
        dialogBuilder
                .withTitle(title)
                .withTitleColor(context.getResources().getColor(R.color.window_background))
                .withDividerColor(context.getResources().getColor(R.color.light_grey))
                .withMessage(message)
                .withMessageColor(context.getResources().getColor(R.color.window_background))
                .withDialogColor(context.getResources().getColor(R.color.sky_blue))
                //.withIcon(getResources().getDrawable(R.drawable.icon))
                .withDuration(500)
                .withEffect(Effectstype.Fall)
//                        .withButton1Text("确定")
//                        .withButton2Text("取消")
                .isCancelableOnTouchOutside(true)
                .setCustomView(view1,context)
                .show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                textView.setText(arrayList[position]);
                dialogBuilder.dismiss();
            }
        });
    }
}
