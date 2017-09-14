package com.example.administrator.myapplication1.frament;

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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.bean.NearBean;
import com.example.administrator.myapplication1.constant.NetConstant;
import com.example.administrator.myapplication1.utils.CircleTransform;
import com.example.administrator.myapplication1.utils.MyLocationListener;
import com.example.administrator.myapplication1.utils.ToRoundImage;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



import static android.content.Context.MODE_PRIVATE;

/**
 * Created by liu on 2017/6/20 0020.
 * 附近fragment
 */

public class NearFrament extends Fragment {
    private View mView;
    private LayoutInflater mInflater;
    private int[] mImgIds;

    private Bitmap roundImage;
    /**
     * 上拉刷新的控件
     */
    private PullToRefreshListView mPullRefreshListView;
    private static NearFrament.MyAdapter listAdapter;

    //此集合存放所有用户名字
    private static List<String> userNikeNames = new ArrayList<>();
    //此集合存放所有用户年龄
    private List<String> userAges = new ArrayList<>();
    //此集合存放所有用户签名
    private List<String> usertexts = new ArrayList<>();
    //此集合存放所有用户国家
    private List<String> userDistances = new ArrayList<>();
    //此集合存放所有用户头像
    private List<String> userImgs = new ArrayList<>();
    //此集合存放所有用户id
    private List<String> userIds = new ArrayList<>();

    private List<String> mDatas = new ArrayList<String>(Arrays.asList("1",
            "2", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3"));
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null)
        {
            mInflater = LayoutInflater.from(getActivity());
            mView = inflater.inflate(R.layout.near_layout, container, false);
            initData();
            initView();

        }
        return mView ;
    }

    private void initView() {
        mPullRefreshListView = mView.findViewById(R.id.lv_friends_info);
//        listView.setAdapter(new CommonAdapter<String>(getActivity(),
//                mDatas, R.layout.friends_list_item) {
//            @Override
//            public void convert(ViewHolder helper, String item) {
//                //helper.setText()
//            }
//        });

        //listView.setAdapter();
    }

    private void initData() {
        mImgIds = new int[] { R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher };


        SharedPreferences share = getActivity().getSharedPreferences("UID", MODE_PRIVATE);
        String userId = share.getString("uId", "");
        String url = NetConstant.baseUrl + NetConstant.nearby;

        //获取经度
        Double longitude= MyLocationListener.longitude;
        //获取纬度
        Double latitude= MyLocationListener.latitude;


        OkHttpUtils
                .get()
                .url(url)
                .addParams("userid", userId)
                .addParams("user_x", String.valueOf(longitude))
                .addParams("user_y", String.valueOf(latitude))
                .addParams("country", "")
                //.addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(com.squareup.okhttp.Request request, Exception e) {

                        Log.e("附近得人返回信息:::","验证失败");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("附近得人返回信息：",response);

                        Gson gson = new Gson();
                        NearBean nearBean = gson.fromJson(response, NearBean.class);


                        ArrayList<Double> disTance=new ArrayList();


                        ArrayList<NearBean.DataBean.ResponseBean.KfsBean> list= (ArrayList<NearBean.DataBean.ResponseBean.KfsBean>) nearBean.getData().getResponse().getKfs();


                        Comparator<NearBean.DataBean.ResponseBean.KfsBean> comparator=new Comparator<NearBean.DataBean.ResponseBean.KfsBean>(){


                            @Override
                            public int compare(NearBean.DataBean.ResponseBean.KfsBean kfsBean, NearBean.DataBean.ResponseBean.KfsBean kfsBean2) {
                                if(kfsBean.getDistance()>kfsBean2.getDistance()){
                                    return 1;
                                }else if(kfsBean.getDistance()==kfsBean2.getDistance()){
                                    return 0;
                                }else {
                                    return -1;
                                }
                            }
                        };

                        Collections.sort(list,comparator);

                        for(NearBean.DataBean.ResponseBean.KfsBean kfsBean0 : list){
                            Log.e("排序后：：：：", kfsBean0.getDistance()+"" );

                            //距离
                            userDistances.add(kfsBean0.getDistance()+"");
                            //昵称
                            userNikeNames.add(kfsBean0.getNickname());
                            //头像
                            userImgs.add(kfsBean0.getIconimage());

                            //年龄
                            userAges.add(kfsBean0.getBirthday());



                            listAdapter = new MyAdapter();

                            mPullRefreshListView.setAdapter(listAdapter);

                        }

                    }
                });
    }


    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return userNikeNames.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            NearFrament.ViewHolder holder;
            //观察convertView随ListView滚动情况
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.friends_list_item, null);
                holder = new NearFrament.ViewHolder();
                        /*得到各个控件的对象*/
                holder.name = convertView.findViewById(R.id.user_name);
                holder.age = convertView.findViewById(R.id.age);
                holder.word = convertView.findViewById(R.id.word);
                holder.distance = convertView.findViewById(R.id.diatance);
                holder.img = convertView.findViewById(R.id.image_user);



                convertView.setTag(holder);
                //绑定ViewHolder对象
            } else {
                holder = (NearFrament.ViewHolder) convertView.getTag();//取出ViewHolder对象
            }
                    /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
            if (userNikeNames.size() > 0) {
                holder.name.setText(userNikeNames.get(position));

                holder.age.setText(userAges.get(position));

                //holder.img.setText(userAges.get(position));

                Picasso.with( getContext() )
                        .load(userImgs.get(position))
                        .placeholder(R.drawable.i1)
                        .error(R.drawable.i3)
                        .resize(70,70)
                        .transform(new CircleTransform())
                        .centerCrop()
                        .into( holder.img);

                //转换为圆形
                Bitmap bitmap = ((BitmapDrawable) holder.img.getDrawable()).getBitmap();
                roundImage = ToRoundImage.toRoundBitmap(bitmap);

                holder.img.setImageBitmap(roundImage);

                holder.distance.setText(userDistances.get(position));


            }

            return convertView;
        }

    }

    /*存放控件*/
    public class ViewHolder {
        public TextView name;
        public TextView age;
        public TextView distance;
        public TextView word;

        public ImageView img;

    }


}