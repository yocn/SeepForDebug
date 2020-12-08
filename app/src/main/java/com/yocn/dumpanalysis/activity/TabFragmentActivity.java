package com.yocn.dumpanalysis.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.yocn.dumpanalysis.R;
import com.yocn.dumpanalysis.view.BaseFragment;
import com.yocn.dumpanalysis.view.TabFragment;


public class TabFragmentActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab);
        RelativeLayout tab1 = findViewById(R.id.tab1);
        RelativeLayout tab2 = findViewById(R.id.tab2);
        RelativeLayout tab3 = findViewById(R.id.tab3);
        RelativeLayout tab4 = findViewById(R.id.tab4);
        RelativeLayout tab5 = findViewById(R.id.tab5);
        tab1.setOnClickListener(onClickListener);
        tab2.setOnClickListener(onClickListener);
        tab3.setOnClickListener(onClickListener);
        tab4.setOnClickListener(onClickListener);
        tab5.setOnClickListener(onClickListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, BaseFragment.getInstance(TabFragment.FlowFragment.class)).commitAllowingStateLoss();
    }

    View.OnClickListener onClickListener = v -> {
        switch (v.getId()) {
            case R.id.tab1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, BaseFragment.getInstance(TabFragment.FlowFragment.class)).commitAllowingStateLoss();
                break;
            case R.id.tab2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, BaseFragment.getInstance(TabFragment.DiscoverFragment.class)).commitAllowingStateLoss();
                break;
            case R.id.tab3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, BaseFragment.getInstance(TabFragment.HomeFragment.class)).commitAllowingStateLoss();
                break;
            case R.id.tab4:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, BaseFragment.getInstance(TabFragment.ChatFragment.class)).commitAllowingStateLoss();
                break;
            case R.id.tab5:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, BaseFragment.getInstance(TabFragment.MeFragment.class)).commitAllowingStateLoss();
                break;
            default:
                break;
        }
    };
}