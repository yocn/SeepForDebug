package com.yocn.dumpanalysis.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yocn.dumpanalysis.R;
import com.yocn.dumpanalysis.util.ColorUtil;

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
        initView();
        return rootView;
    }

    protected @LayoutRes
    int getLayoutRes() {
        return R.layout.fragment_main;
    }

    protected void initView() {
        TextView contentView = rootView.findViewById(R.id.tv_content);
        contentView.setText(getClass().getSimpleName());
        rootView.setBackgroundResource(ColorUtil.colors[this.hashCode() % ColorUtil.colors.length]);
        contentView.setTextColor(getActivity().getResources().getColor(ColorUtil.textColor[this.hashCode() % ColorUtil.textColor.length]));
    }
}
