package com.yocn.dumpanalysis;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.yocn.seep.DumpUtil;
import com.yocn.seep.ui.util.SeepLogger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
        findViewById(R.id.btn_dump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DumpUtil.createDumpFile(MainActivity.this);
                onBackPressed();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        SeepLogger.d("onBackPressed");
        Log.d("yocn","onBackPressed");
//        super.onBackPressed();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        SeepLogger.d("dispatchKeyEvent");
        return super.dispatchTouchEvent(ev);
    }
}