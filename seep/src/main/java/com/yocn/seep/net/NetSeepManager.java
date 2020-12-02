package com.yocn.seep.net;

import android.content.Intent;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AppCompatActivity;

public class NetSeepManager {
    private WeakReference<AppCompatActivity> weakReference;

    public NetSeepManager(WeakReference<AppCompatActivity> weakReference) {
        this.weakReference = weakReference;
    }

    public void checkNet() {
        weakReference.get().startActivity(new Intent(weakReference.get(), NetGrabberActivity.class));
    }

    public boolean onBackPressed() {
        return false;
    }

}
