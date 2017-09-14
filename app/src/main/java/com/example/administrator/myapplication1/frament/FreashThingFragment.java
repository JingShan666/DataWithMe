package com.example.administrator.myapplication1.frament;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.bean.AllNewThingBean;
import com.example.administrator.myapplication1.bean.VerifyBean;
import com.example.administrator.myapplication1.constant.NetConstant;
import com.example.administrator.myapplication1.utils.ToRoundImage;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Liu on 2017/6/28 0028.
 *
 */

public class FreashThingFragment extends Fragment{
    private View mView;
    private LayoutInflater mInflater;
    private ListView listView;

    private MyAdapter myAdapter;
    /**
     * 上拉刷新的控件
     */
    private PullToRefreshListView mPullRefreshListView;
    private Bitmap roundImage;

    private List<AllNewThingBean.DataBean.ResponseBean.AllxinxianshiBean> allNewThingList;
    private List <String>words;

    private List <String>photoUrls;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null)
        {
            mInflater = LayoutInflater.from(getActivity());
            mView = inflater.inflate(R.layout.fragment_refreash, container, false);
            initData();
            initView();

        }
        return mView ;
    }

    private void initData() {

        String url = NetConstant.baseUrl + NetConstant.newThing;
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

                        Log.e("获取所有新鲜事999：",response);

                        //更新新鲜事UI
                        updataUI(response);

                    }
                });


    }

    private void updataUI(String response) {
        allNewThingList= new ArrayList<>();
        //签名
        words= new ArrayList();
        //头像
        photoUrls= new ArrayList<>();


        Gson gson= new Gson();
        AllNewThingBean allNewThingBean = gson.fromJson(response, AllNewThingBean.class);
        //获取所有新鲜事集合
        allNewThingList= allNewThingBean.getData().getResponse().getAllxinxianshi();


        for (int i=0;i<allNewThingList.size();i++){

            String word= allNewThingList.get(i).getText();
            String photoUrl=allNewThingList.get(i).getPhotoUrl();
            words.add(word);


            photoUrls.add(photoUrl);
        }
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

         myAdapter= new MyAdapter();
        mPullRefreshListView.setAdapter(myAdapter);

        //添加刷新，加载监听
        reFreshList(mPullRefreshListView);


    }


    private void reFreshList(PullToRefreshListView mPullRefreshListView) {


        mPullRefreshListView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
                {
                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<ListView> refreshView)
                    {
                        Log.e("TAG", "onPullDownToRefresh");
                        //这里写下拉刷新的任务

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                            }
                        }).start();

                        new GetDataTask().execute();
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView)
                    {
                        Log.e("TAG", "onPullUpToRefresh");
                        //这里写上拉加载更多的任务
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                            }
                        }).start();
                        new GetDataTask().execute();
                    }
                });
    }

    public class GetDataTask extends AsyncTask<Void, Void, String> {





        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            return photoUrls.size() + "";
        }

        @Override
        protected void onPostExecute(String result) {
            //mListItems.add(result);


                myAdapter.notifyDataSetChanged();
                // Call onRefreshComplete when the list has been refreshed.
                mPullRefreshListView.onRefreshComplete();



        }
    }

    private void initView() {
        mPullRefreshListView= mView.findViewById(R.id.lv_refreash);
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return words.size();
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
            ViewHolder holder;
            //观察convertView随ListView滚动情况
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.fragment_friend_item, null);
                holder = new ViewHolder();
                /*得到各个控件的对象*/
                holder.name =  convertView.findViewById(R.id.tv_friend_name);
                holder.age =  convertView.findViewById(R.id.tv_friend_age);
                holder.word =  convertView.findViewById(R.id.friend_words);
                holder.country= convertView.findViewById(R.id.tv_friend_country);
                holder.img= convertView.findViewById(R.id.iv_user_picture);

                holder.big_image= convertView.findViewById(R.id.picture);

                holder.Dtai= convertView.findViewById(R.id.friend_dongtai);
                holder.date= convertView.findViewById(R.id.friend_date);
                holder.zan= convertView.findViewById(R.id.friend_zan);
                holder.chat= convertView.findViewById(R.id.friend_chat);

                convertView.setTag(holder);
                //绑定ViewHolder对象
            }else{
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
            }
                    /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
            //holder.name.setText(userNames.get(position));

            //设置动态语言
            holder.word.setText(words.get(position));

            //设置大图
            if (photoUrls.get(position)!=null){
                holder.big_image.setVisibility(View.VISIBLE);
                Picasso.with(getContext()).load(photoUrls.get(position)).into(holder.big_image);
            }else {
                holder.big_image.setVisibility(View.GONE);
            }

            //设置头像
            //转换为圆形
            Bitmap bitmap = ((BitmapDrawable) holder.img.getDrawable()).getBitmap();
            roundImage = ToRoundImage.toRoundBitmap(bitmap);

            holder.img.setImageBitmap(roundImage);




            return convertView;
        }

    }


    /*存放控件*/
    public class ViewHolder {
        public TextView zan;
        public TextView chat;
        public TextView name;
        public TextView date;
        public TextView Dtai;
        public TextView age;
        public TextView country;
        public TextView word;

        public ImageView img;
        public ImageView big_image;

    }
}
