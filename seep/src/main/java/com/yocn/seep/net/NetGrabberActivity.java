package com.yocn.seep.net;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.yocn.seep.R;
import com.yocn.seep.net.adapter.NetGrabberAdapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author yocn
 */
public class NetGrabberActivity extends BaseSeepActivity  {

    private RecyclerView recyclerView;
    private TextView contentTv;
    private NetGrabberAdapter netGrabberAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.rv_net);
        contentTv = findViewById(R.id.tv_content);
        initData();
        setMyTitle("Network Flows");
    }

    @Override
    public int setContentView() {
        return R.layout.activity_net_grabber;
    }

    private void initData() {
        netGrabberAdapter = new NetGrabberAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(netGrabberAdapter);
        invalidateList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            //clear network flows history
            NetGrabber.getInstance().clear();
            invalidateList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_net_grabber, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void invalidateList(){
        netGrabberAdapter.notifyDataSetChanged();
        if (NetGrabber.getInstance().getData().size() == 0) {
            contentTv.setText("No Net Activities, Please Check Network.");
        }
    }

}
