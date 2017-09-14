package com.example.administrator.myapplication1.utils.pager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.utils.ToRoundImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liu on 2017/7/4 0004.
 */

public class GridAdapter extends BaseAdapter {
    private List<String> mNameList = new ArrayList();
    private List<String> mDrawableList = new ArrayList();
    private LayoutInflater mInflater;
    private Context mContext;
    RelativeLayout.LayoutParams params;

    private Bitmap roundImage;


    private int a=100;

    public GridAdapter(Context context, List<String> nameList, List<String> drawableList, int a) {

        this.a= a;
        mNameList = nameList;
        mDrawableList = drawableList;
        mContext = context;
        mInflater = LayoutInflater.from(context);

        Log.e("mDrawableList的长度9999：",mDrawableList.size()+"");
        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //params.gravity = Gravity.CENTER;
    }

    public int getCount() {
        return mNameList.size();
    }

    public Object getItem(int position) {
        return mDrawableList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewTag viewTag;

        if (convertView == null)
        {

            if (a==0){
                convertView = mInflater.inflate(R.layout.grid_list_user, null);
                // construct an item tag
                viewTag = new ItemViewTag((ImageView) convertView.findViewById(R.id.image_user),
                        (TextView) convertView.findViewById(R.id.user_de_name));
                convertView.setTag(viewTag);
            }else {
                convertView = mInflater.inflate(R.layout.photo_item, null);
                viewTag = new ItemViewTag((ImageView) convertView.findViewById(R.id.iv_pic),
                        (TextView) convertView.findViewById(R.id.tv_how));
                convertView.setTag(viewTag);
            }




        } else
        {
            viewTag = (ItemViewTag) convertView.getTag();
        }

        // set name
        //viewTag.mName.setText(mNameList.get(position));

        // set icon
        //viewTag.mIcon.setBackgroundDrawable(Drawable.createFromPath(mDrawableList.get(position)));



        Bitmap bitmap = ((BitmapDrawable)viewTag.mIcon.getDrawable()).getBitmap();
        if (a==0){
            //转换为圆形

            roundImage= ToRoundImage.toRoundBitmap(bitmap);

            viewTag.mIcon.setImageBitmap(roundImage);
            Log.e("qwqwqwqwqwq：",mDrawableList.get(position)+"");

        }else {
            //viewTag.mIcon.setImageBitmap(mImage);
//            Picasso.with(mContext.getApplicationContext())
//                    .load(mDrawableList.get(position))
//                    .placeholder(R.drawable.i3)
//                    .resize(150,150)
//                    //.error(R.drawable.i1)
//                    .into(viewTag.mIcon);


            //Log.e("mDrawableList111：",mDrawableList.get(position)+"");
        }

        viewTag.mIcon.setLayoutParams(params);

        viewTag.mName.setText(mNameList.get(position));
        return convertView;
    }

    class ItemViewTag
    {
        protected ImageView mIcon;
        protected TextView mName;

        /**
         * The constructor to construct a navigation view tag
         *
         * @param name
         *            the name view of the item
         *            the size view of the item
         * @param icon
         *            the icon view of the item
         */
        public ItemViewTag(ImageView icon, TextView name)
        {
            this.mName = name;
            this.mIcon = icon;
        }
    }

}