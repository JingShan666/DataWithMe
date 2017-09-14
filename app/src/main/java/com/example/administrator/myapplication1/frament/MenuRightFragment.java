package com.example.administrator.myapplication1.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication1.R;


/**
 * Created by Liu on 2017/6/20 0020.
 * 右边侧滑菜单fragment
 */

public class MenuRightFragment extends Fragment {
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.right_menu, container, false);
        }
        return mView;

    }
}
