package com.yocn.dumpanalysis.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yocn.dumpanalysis.R;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public static BaseFragment getInstance(Class<? extends BaseFragment> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BaseFragment() {
    }

    View rootView;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutRes(), container, false);
        return rootView;
    }

    protected @LayoutRes
    int getLayoutRes() {
        return R.layout.fragment_main;
    }
}
