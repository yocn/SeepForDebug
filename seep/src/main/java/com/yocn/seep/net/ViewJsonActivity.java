package com.yocn.seep.net;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.yocn.seep.R;
import com.yocn.seep.net.bean.NetResult;
import com.yocn.seep.net.jsonviewer.JsonRecyclerView;
import com.yocn.seep.net.jsonviewer.utils.Utils;

import androidx.annotation.Nullable;

/**
 * @author yocn
 */
public class ViewJsonActivity extends BaseSeepActivity {
    public TextView contentTV;
    public JsonRecyclerView jsonRecyclerView;
    public static NetResult netResult;
    public static String showString;

    public static void start(Context context, NetResult netResult) {
        try {
            ViewJsonActivity.netResult = netResult;
        } catch (Exception e) {
            e.printStackTrace();
            ViewJsonActivity.showString = (netResult == null || netResult.getData() == null) ? "Data Is Null, Please Check." : netResult.getData().toString();
        }
        Intent intent = new Intent(context, ViewJsonActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jsonRecyclerView = findViewById(R.id.rv_json);
        contentTV = findViewById(R.id.tv_content);
        initData();
        setMyTitle("JsonView");
    }

    @Override
    public int setContentView() {
        return R.layout.activity_json_view;
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String json = getShowString();
            try {
                jsonRecyclerView.bindJson(json);
            } catch (Exception e) {
                e.printStackTrace();
                contentTV.setText(json);
            }
        }
    }

    private String getShowString() {
        String json = "";
        if (netResult == null) {
            json = showString;
        } else {
            json = netResult.getData().toString();
        }
        return json;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            Utils.shareString(ViewJsonActivity.this, getShowString());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_json_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        netResult = null;
    }
}
