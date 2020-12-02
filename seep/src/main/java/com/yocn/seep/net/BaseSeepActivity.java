package com.yocn.seep.net;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yocn.seep.R;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public abstract class BaseSeepActivity extends AppCompatActivity {

    protected @Nullable
    ActionBar actionBar;
    protected @Nullable
    Toolbar toolbar;
    private ViewGroup appBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = getContentView();
        if (contentView != null) {
            setContentView(contentView);
        } else {
            setContentView(setContentView());
        }
        initToolBar();
    }

    protected void initToolBar() {
        appBar = (ViewGroup) findViewById(R.id.app_bar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        // 减小Title与导航键的间距
        toolbar.setContentInsetStartWithNavigation(0);
    }

    protected View getContentView() {
        return null;
    }

    public void setMyTitle(String text) {
        if (toolbar != null) {
            toolbar.setTitle(text);
        }
    }

    @LayoutRes
    public abstract int setContentView();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            clickHome();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void clickHome() {
        onBackPressed();
    }

}
