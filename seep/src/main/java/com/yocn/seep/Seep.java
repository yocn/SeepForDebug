package com.yocn.seep;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.yocn.seep.net.NetSeepManager;
import com.yocn.seep.ui.UISeepManager;
import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.digger.SeepComponentDigger;
import com.yocn.seep.view.SeepRouterView;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AppCompatActivity;

public class Seep {
    public static String TAG = "Seep";
    private SeepRouterView seepRouterView;
    private WeakReference<AppCompatActivity> weakReference;
    private UISeepManager uiSeepManager;
    private NetSeepManager netSeepManager;
    private static Handler handler;
    private static final int WHAT = 100;
    private static final int DELAY = 500;

    private Seep(final AppCompatActivity appCompatActivity) {
        weakReference = new WeakReference<>(appCompatActivity);
        uiSeepManager = new UISeepManager(weakReference);
        netSeepManager = new NetSeepManager(weakReference);
        handler = new Handler(appCompatActivity.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (seepRouterView == null) {
                    seepRouterView = new SeepRouterView(appCompatActivity);
                    ViewGroup decorView = (ViewGroup) appCompatActivity.getWindow().getDecorView();
                    decorView.addView(seepRouterView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    seepRouterView.setOnMaskClickListener(onMaskClickListener);
                }
            }
        };
    }

    private static Seep instance;

    private static Seep getInstance(AppCompatActivity appCompatActivity) {
        if (instance == null) {
            instance = new Seep(appCompatActivity);
        }
        return instance;
    }

    private static boolean isSeepOpen() {
        return !BuildConfig.DEBUG;
    }

    private void sendDelaySeepMessage() {
        if (!handler.hasMessages(WHAT)) {
            Message message = new Message();
            message.what = WHAT;
            handler.sendMessageDelayed(message, DELAY);
        }
    }

    private void removeSeepMessage() {
        if (handler.hasMessages(WHAT)) {
            handler.removeMessages(WHAT);
            release();
        }
    }

    private boolean containsMsg() {
        return handler.hasMessages(WHAT);
    }

    private static boolean isEventDownloading = false;

    public static boolean handleTouchEvent(AppCompatActivity appCompatActivity, MotionEvent ev) {
        if (isSeepOpen()) {
            return false;
        }
        if (ev.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
            if (ev.getPointerCount() >= 3) {
                isEventDownloading = true;
                instance = getInstance(appCompatActivity);
                instance.sendDelaySeepMessage();
                return true;
            }
        }
        if ((ev.getActionMasked() == MotionEvent.ACTION_POINTER_UP || ev.getAction() == MotionEvent.ACTION_UP) && instance != null) {
            instance.removeSeepMessage();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isEventDownloading = false;
                }
            }, 1000);
        }
        return isEventDownloading;
    }

    public static boolean handleBackPressed() {
        if (isSeepOpen()) {
            return false;
        }
        if (instance == null) {
            return false;
        }
        return instance.onBackPressed();
    }

    private boolean onBackPressed() {
        if (uiSeepManager.onBackPressed()) {
            return true;
        }
        if (netSeepManager.onBackPressed()) {
            return true;
        }
        release();
        return false;
    }

    private SeepRouterView.OnMaskClickListener onMaskClickListener = new SeepRouterView.OnMaskClickListener() {
        @Override
        public void onClick(int viewId) {
            AppCompatActivity appCompatActivity = weakReference.get();
            if (viewId == R.id.tv_ui) {
                SeepResult seepResult = SeepComponentDigger.getComponents(appCompatActivity);
                uiSeepManager.showDialog(appCompatActivity, seepResult);
            } else if (viewId == R.id.tv_net_grabber) {
                netSeepManager.checkNet();
            } else if (viewId == R.id.iv_close) {
                seepRouterView.setVisibility(View.GONE);
                seepRouterView = null;
            }
        }
    };

    private void release() {
        if (seepRouterView != null && seepRouterView.getParent() != null) {
            ((ViewGroup) seepRouterView.getParent()).removeView(seepRouterView);
        }
        instance = null;
    }
}
