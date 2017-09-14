package com.example.administrator.myapplication1.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication1.R;

/**
 * Created by Liu on 2017/7/5 0005.
 */

public class AboutMeFragment extends Fragment{
    private View mView;
    private LayoutInflater mInflater;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null)
        {
            mInflater = LayoutInflater.from(getActivity());
            mView = inflater.inflate(R.layout.fragment_me, container, false);
//            initData();
            //initView();

        }
        return mView ;
    }
}
