package com.yocn.dumpanalysis;

import android.os.Bundle;
import android.view.ViewGroup;

import com.yocn.dumpanalysis.view.BaseFragment;
import com.yocn.dumpanalysis.view.BottomFragment;
import com.yocn.dumpanalysis.view.TopFragment;
import com.yocn.seep.ui.util.SeepLogger;

public class SimpleFragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_fragment);
        BaseFragment topFragment = TopFragment.getInstance(TopFragment.class);
        BaseFragment bottomFragment = BottomFragment.getInstance(BottomFragment.class);
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