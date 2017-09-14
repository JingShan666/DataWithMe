package com.example.administrator.myapplication1.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.utils.CommonAdapter;
import com.example.administrator.myapplication1.utils.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/6/21 0021.
 */

public class VisitFragment extends Fragment {
    private View mView;
    private ListView listView;
    private List<String> mDatas = new ArrayList<String>(Arrays.asList("1",
            "2", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3"));
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_visit, container, false);
            initView();
        }
        return mView;
    }

    private void initView() {
        listView= mView.findViewById(R.id.lv_visit);
        listView.setAdapter(new CommonAdapter<String>(getActivity(),
                mDatas, R.layout.visit_item) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.name, item);
            }
        });
    }
}
