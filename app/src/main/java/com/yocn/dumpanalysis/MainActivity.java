package com.yocn.dumpanalysis;

import android.os.Bundle;

import com.yocn.dumpanalysis.bean.MainBean;
import com.yocn.dumpanalysis.view.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv);
        List<MainBean> list = new ArrayList<>();
        list.add(new MainBean("SimpleFragmentActivity", SimpleFragmentActivity.class));
        MainAdapter mainAdapter = new MainAdapter(this, list);
        recyclerView.setAdapter(mainAdapter);
        int spanCount = 3;
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                                @Override
                                                public int getSpanSize(int position) {
                                                    return position == 0 ? gridLayoutManager.getSpanCount() : 1;
                                                }
                                            }
        );
        recyclerView.setLayoutManager(gridLayoutManager);
    }

}