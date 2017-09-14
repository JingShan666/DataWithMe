package com.example.administrator.myapplication1.frament;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication1.activity.ChatActivity;
import com.example.administrator.myapplication1.activity.SearchActivity;
import com.example.administrator.myapplication1.bean.AllUsersBean;
import com.example.administrator.myapplication1.constant.NetConstant;
import com.example.administrator.myapplication1.utils.RecycleViewUtils.GalleryAdapter;
import com.example.administrator.myapplication1.utils.ToRoundImage;
import com.example.administrator.myapplication1.utils.pager.AdUrlViewpagerUtil;
import com.example.administrator.myapplication1.utils.pager.GridAdapter;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.administrator.myapplication1.bean.AllUsersBean.DataBean.ResponseBean.AlluserBean;


import com.example.administrator.myapplication1.R;


import static android.content.Context.MODE_PRIVATE;

/**
 * Created by liu on 2017/6/20 0020.
 * 在线好友fragment
 *
 * 分页加载
 * http://p.codekk.com/detail/Android/nicolasjafelle/PagingListView
 */

public class OnLineFrament extends Fragment {
    //此集合存放所有用户名字
    private static List<String> userNikeNames = new ArrayList<>();
    //此集合存放所有用户年龄
    private List<String> userAges = new ArrayList<>();
    //此集合存放所有用户签名
    private List<String> usertexts = new ArrayList<>();
    //此集合存放所有用户国家
    private List<String> userCountrys = new ArrayList<>();
    //此集合存放所有用户头像
    private List<String> userImgs = new ArrayList<>();
    //此集合存放所有用户id
    private List<String> userIds = new ArrayList<>();


    private LayoutInflater mInflater;
    private ListView listView;

    private GridView gridView;
    private RelativeLayout et_search;

    private static MyAdapter listAdapter;
    private GridAdapter gridAdapter;


    private View mView;

    private ViewPager advPager;

    private String userId2;

    private LinearLayout lydots;

    private List<String> urls;

    private AdUrlViewpagerUtil adUrlViewpagerUtil;

    private int CONSTANT = 200;

    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;

    private Bitmap roundImage;

    private ImageView iv_change;

    private List<String> mDrawableList;


    private boolean isShowGridList = false;

    private String id;

    private String iconImg;


    /**
     * 上拉刷新的控件
     */
    private  PullToRefreshListView mPullRefreshListView;
    private  PullToRefreshGridView mPullRefreshGridView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mInflater = LayoutInflater.from(getActivity());
            mView = inflater.inflate(R.layout.online_frament, container, false);

            //进来加载下面listView数据
            initData(9);
            initView();



        }
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initData();


    }

    //初始化view控件
    private void initView() {
        Log.e("aaaaa", "22222");
        listAdapter = new MyAdapter();
        SharedPreferences share = getActivity().getSharedPreferences("UID", MODE_PRIVATE);
        iconImg = share.getString("iconImg", "");

        ImageView imageView_me = mView.findViewById(R.id.imageView2);

        //加载个人头像
        addSelfImg(imageView_me);

        et_search = mView.findViewById(R.id.start_search);
        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        iv_change = mView.findViewById(R.id.image_changelist);
        iv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //切换列表
                changeList();
            }
        });

        //处理横向RecycleView
        dealWithRecycleView();
        advPager = mView.findViewById(R.id.vp_viewpager);

        //处理顶部viewPager
        dealWithPager(advPager);



        //Log.e("用户数据id：", id + "");
        Log.e("用户数据userIds：", userIds + "");
        Log.e("用户数据userNames：", userNikeNames + "");

        mPullRefreshListView = mView.findViewById(R.id.lv_friends_info_main);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListView.setAdapter(listAdapter);




        //添加刷新，加载监听
        reFreshList(mPullRefreshListView);

        //listView条目点击事件
        itemClickEvent(listView);
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
                                initData(1);
                            }
                        }).start();

                        new GetDataTask(1).execute();
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
                                initData(1);
                            }
                        }).start();
                        new GetDataTask(1).execute();
                    }
                });
    }


    //加载个人头像
    private void addSelfImg(ImageView imageView_me) {

        Picasso.with(getContext())
                .load(iconImg)
                .resize(88, 64)
                .centerCrop()
                .into(imageView_me);
    }

    //切换底部列表
    private void changeList() {
        //gridView = mView.findViewById(R.id.gv_friends_info_main);

        // 得到控件
        mPullRefreshGridView =  mView.findViewById(R.id.gv_friends_info_main);

        //切换到gridView列表
        if (!isShowGridList) {
            Log.e("切换到gridView:", "666");
            mPullRefreshListView.setVisibility(View.GONE);
            //gridView.setVisibility(View.VISIBLE);

            mPullRefreshGridView.setVisibility(View.VISIBLE);
            //加载新的数据
            clearList(userNikeNames);

            Log.e("userNikeNames的长度:", userNikeNames.size() + "");
            //reFreshList(0,mPullRefreshListView);

            initData(0);

            //切换到listView列表
        } else {
            Log.e("切换到listView......:", "666");
            mPullRefreshListView.setVisibility(View.VISIBLE);
            mPullRefreshGridView.setVisibility(View.GONE);

            clearList(userNikeNames);

            reFreshList(mPullRefreshListView);
            initData(1);

        }
        isShowGridList = !isShowGridList;

    }
    //处理GridView
    private void dealWithGridView() {

        //获得头像数据
        Log.e("userNikeNames的长度999:", userNikeNames.size() + "");
        //用户昵称userNikeNames

        //取得GridView对象
        mDrawableList = new ArrayList<>();
        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");
        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");
        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");
        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");
        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");
        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");
        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");
        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");
        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");
        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");
        mDrawableList.add("http://www.taopic.com/uploads/allimg/110703/1830-110F313593338.jpg");



        gridAdapter= new GridAdapter(getContext(), userNikeNames, mDrawableList, 0);
        mPullRefreshGridView = mView.findViewById(R.id.gv_friends_info_main);
        mPullRefreshGridView.setAdapter(gridAdapter);


        mPullRefreshGridView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>()
                {

                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<GridView> refreshView)
                    {
                        Log.e("TAG", "onPullDownToRefresh"); // Do work to

                        //这里写下拉刷新的任务

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                initData(0);
                            }
                        }).start();

                        new GetDataTask(0).execute();
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<GridView> refreshView)
                    {
                        Log.e("TAG", "onPullUpToRefresh"); // Do work to refresh
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                initData(0);
                            }
                        }).start();
                        new GetDataTask(0).execute();
                    }
                });

        mPullRefreshGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();

            }
        });
    }

    //处理RecycleView
    private void dealWithRecycleView() {

        initDatas();
        //得到控件
        mRecyclerView = mView.findViewById(R.id.id_recyclerview_horizontal);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new GalleryAdapter(getContext(), mDatas);
        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initDatas() {


        mDatas = new ArrayList(Arrays.asList(R.drawable.i1,
                R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5,
                R.drawable.i6, R.drawable.i1, R.drawable.i2, R.drawable.i3));
    }


    private void dealWithPager(ViewPager advPager) {
        urls = new ArrayList<>();
        urls.add("http://www.wzsky.net/ddimg/uploadimg/20110120/1441380.jpg");
        urls.add("http://www.86ps.com/sc/BJ/153/abstract_color_background_picture_8015.jpg");

        lydots = mView.findViewById(R.id.ly_dots);
        //带导航圆点
        adUrlViewpagerUtil = new AdUrlViewpagerUtil(getContext(), advPager, lydots, 8, 4, urls, 100);
        adUrlViewpagerUtil.initVps(CONSTANT);
        //ViewPager页面点击事件

        adUrlViewpagerUtil.setOnAdItemClickListener(new AdUrlViewpagerUtil.OnAdItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getContext(), "position:" + position, Toast.LENGTH_SHORT).show();

                //Intent intent= new Intent(UserInfoActivity.this,BigImgActivity.class);

                Log.e("position5555", position + "");
            }
        });

        final List<String> finalUrls = urls;
        adUrlViewpagerUtil.setOnAdPageChangeListener(new AdUrlViewpagerUtil.OnAdPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                if (arg0 == 0)
                    arg0 = 1;
                if (arg0 == finalUrls.size() + 1) {
                    arg0 = finalUrls.size();
                }
            }
        });
    }

    public void initData(int whichList) {
        SharedPreferences share = getActivity().getSharedPreferences("UID", MODE_PRIVATE);
        id = share.getString("id", "");

        Log.e("自己的id333", id);
        //从服务器获取用户数据
        getUserInfoFromNet(whichList);
        Log.e("OnLineFrament-userId2", id);

    }

    private void getUserInfoFromNet(final int whichList) {

        String url = NetConstant.baseUrl + NetConstant.alluser;
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
                        Log.e("所有用户数据onLineFragment66：", response);
                        Gson gson = new Gson();
                        AllUsersBean allUserBean = gson.fromJson(response, AllUsersBean.class);
                        ArrayList<AlluserBean> allusers =
                                (ArrayList) allUserBean.getData().getResponse().getAlluser();
                        for (int i = 0; i < allusers.size(); i++) {
                            //id
                            int id = allusers.get(i).getId();
                            userIds.add(id + "");
                            //name
                            String name = allusers.get(i).getNickname();
                            userNikeNames.add(name);
                        }
                        if (whichList == 0) {

                            dealWithGridView();
                        } else if (whichList == 1) {
                            listAdapter.notifyDataSetChanged();

                        } else if (whichList == 9) {

                            mPullRefreshListView.setAdapter(listAdapter);

                        }
                        //判断是不是自己，如果是自己，则剔除
                        if (userIds.contains(id)) {
                            userIds.remove(id);
                        }
                    }
                });

    }

    public class GetDataTask extends AsyncTask<Void, Void, String> {

        public int whichList;

        public GetDataTask(int i) {
            whichList= i;
        }


        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            return userNikeNames.size() + "";
        }

        @Override
        protected void onPostExecute(String result) {
            //mListItems.add(result);

            if (whichList==1){
                listAdapter.notifyDataSetChanged();
                // Call onRefreshComplete when the list has been refreshed.
                mPullRefreshListView.onRefreshComplete();
            }else {
                gridAdapter.notifyDataSetChanged();
                mPullRefreshGridView.onRefreshComplete();
            }


        }
    }

    private void itemClickEvent(ListView listView) {
        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //获取点击条目的name
                String name = userNikeNames.get(position);
                //获取点击条目的id
                String id = userIds.get(position);
                if (!TextUtils.isEmpty(id)) {
                    Intent chat = new Intent(getContext(), ChatActivity.class);
                    chat.putExtra(EaseConstant.EXTRA_USER_ID, id);  //对方账号
                    chat.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
                    startActivity(chat);
                } else {
                    Toast.makeText(getContext(), "请输入要聊天的对方的账号", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public void clearList(List<String> list) {
        int size = list.size();
        if (size > 0) {
            list.removeAll(list);
            listAdapter.notifyDataSetChanged();
        }
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
            ViewHolder holder;
            //观察convertView随ListView滚动情况
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.friends_list_item, null);
                holder = new ViewHolder();
                        /*得到各个控件的对象*/
                holder.name = convertView.findViewById(R.id.user_name);
                holder.age = convertView.findViewById(R.id.age);
                holder.word = convertView.findViewById(R.id.word);
                holder.country = convertView.findViewById(R.id.diatance);
                holder.img = convertView.findViewById(R.id.image_user);
                //转换为圆形
                Bitmap bitmap = ((BitmapDrawable) holder.img.getDrawable()).getBitmap();
                roundImage = ToRoundImage.toRoundBitmap(bitmap);

                holder.img.setImageBitmap(roundImage);


                convertView.setTag(holder);
                //绑定ViewHolder对象
            } else {
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
            }
                    /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
            if (userNikeNames.size() > 0) {
                holder.name.setText(userNikeNames.get(position));

            }

            return convertView;
        }

    }

    /*存放控件*/
    public class ViewHolder {
        public TextView name;
        public TextView age;
        public TextView country;
        public TextView word;

        public ImageView img;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        userNikeNames.clear();
    }
}