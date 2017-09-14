package com.example.administrator.myapplication1.frament;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication1.MainActivity;
import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.activity.NewThingActivity;
import com.example.administrator.myapplication1.activity.SetingActivity;
import com.example.administrator.myapplication1.activity.ShareActivity;
import com.example.administrator.myapplication1.activity.UserInfoActivity;
import com.example.administrator.myapplication1.activity.VipActivity;
import com.example.administrator.myapplication1.activity.XieHouActivity;
import com.example.administrator.myapplication1.utils.CircleTransform;
import com.example.administrator.myapplication1.utils.CommonAdapter;
import com.example.administrator.myapplication1.utils.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;


import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Liu on 2017/6/20 0020.
 * 左边侧滑菜单Fragment
 */

public class MenuLeftFragment extends Fragment {
    private ImageView img_user;
    private ImageView img_set;
    private View mView;
    private ListView listView;

    private String iconImg;

    private List<String> mDatas = Arrays
            .asList("在线用户", "新鲜事", "邂逅", "聊天", "来访", "会员中心", "推荐给好友");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if (mView == null) {
            initView(inflater, container);
        }


        return mView;
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {

        SharedPreferences share = getActivity().getSharedPreferences("UID", MODE_PRIVATE);
        iconImg = share.getString("iconImg", "");

        mView = inflater.inflate(R.layout.left_menu, container, false);
        img_user =  mView.findViewById(R.id.img_user);
        img_set= mView.findViewById(R.id.img_set);

        img_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), SetingActivity.class);
                startActivity(intent);


            }
        });


        //加载头像
        addUserImg(iconImg);




        listView =  mView
                .findViewById(R.id.left_menu_list);

        listView.setAdapter(new CommonAdapter<String>(getActivity(),
                mDatas, R.layout.menu_list_item) {
            @Override
            public void convert(ViewHolder helper, String item) {
                //helper.setText()
                helper.setText(R.id.name, item);
//                helper.setText(R.id.tv_describe, item.getDesc());
//                helper.setText(R.id.tv_phone, item.getPhone());
//                helper.setText(R.id.tv_time, item.getTime());

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Fragment newContent = null;
                        String title = null;
                        switch (position) {
                            case 0:
                                Intent intent0= new Intent(getContext(),MainActivity.class);
                                startActivity(intent0);

                                break;
                            case 1:
                                if (newContent == null) {

                                    Intent intent1= new Intent(getContext(), NewThingActivity.class);
                                    startActivity(intent1);

                                }

                                break;
                            case 2:
                                Intent intent2= new Intent(getContext(),XieHouActivity.class);
                                startActivity(intent2);
                                break;
                            case 4:

                                if (newContent == null) {
                                    newContent = new VisitFragment();
                                    title = mDatas.get(position);
                                }
                                break;
                            case 5:

                                //会员中心
                                if (newContent == null) {
                                    Intent intent= new Intent(getContext(), VipActivity.class);
                                    startActivity(intent);
                                }
                                break;
                            case 6:

                                Intent intent6 = new Intent(getContext(), ShareActivity.class);
                                startActivity(intent6);
                                break;

                            default:
                                break;
                        }

                        if (newContent != null) {
                            switchFragment(newContent, title,position);

                        }
                    }
                });

            }
        });

        clickEvent();
    }

    private void addUserImg(String icon) {


        Log.e("用户头像：：：",icon);


//        //圆形裁剪
//        Glide.with(this)
//                .load(icon)
//                //.placeholder(R.drawable.i1)
//                .bitmapTransform(new CropCircleTransformation(getContext()))
//                .override(62, 62)
//                .into(img_user);
        Picasso.with( getContext() )
                .load(iconImg)
                .resize(70,70)
                .transform(new CircleTransform())
                .centerCrop()
                .into(img_user);


    }

    private void clickEvent() {
        img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserInfoActivity.class);
                startActivity(intent);
                //Log.e("nnn", "qqqqqqqqqq");
//                if (getActivity() instanceof MainActivity) {
//                    MainActivity fca = (MainActivity) getActivity();
//                    fca.jumpToActivity();
//                }
            }
        });

    }

    /**
     * 切换fragment
     *
     * @param fragment
     * @param position
     */
    private void switchFragment(Fragment fragment, String title, int position) {
        if (getActivity() == null) {
            return;
        }
        if (getActivity() instanceof MainActivity) {
            MainActivity fca = (MainActivity) getActivity();

            fca.switchConent(fragment, title);
        }

        if (getActivity() instanceof UserInfoActivity) {
            UserInfoActivity fca = (UserInfoActivity) getActivity();
            if (position==0){
                Intent intent= new Intent(getContext(),MainActivity.class);
                startActivity(intent);

            }else if (position==2){
                Intent intent= new Intent(getContext(),XieHouActivity.class);
                startActivity(intent);
            }


           // fca.switchConent(fragment, title);
        }
    }

}
