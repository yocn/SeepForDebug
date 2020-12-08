package com.yocn.dumpanalysis;

import android.os.Bundle;
import android.view.ViewGroup;

import com.yocn.dumpanalysis.view.BaseFragment;
import com.yocn.dumpanalysis.view.BottomFragment;
import com.yocn.dumpanalysis.view.TopFragment;
import com.yocn.seep.ui.util.SeepLogger;

public class SimpleFragmentActivity extends BaseActivity {

    private ViewGroup viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_fragment);
        BaseFragment topFragment = TopFragment.getInstance(TopFragment.class);
        BaseFragment bottomFragment = BottomFragment.getInstance(TopFragment.class);
        viewById = findViewById(R.id.fl_container_top);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container_top, topFragment)
                .commitAllowingStateLoss();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container_bottom, bottomFragment)
                .commitAllowingStateLoss();
    }

}