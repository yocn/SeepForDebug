package com.yocn.seep.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yocn.seep.R;

import androidx.annotation.Nullable;

public class SeepRouterView extends RelativeLayout implements View.OnClickListener {
    private OnMaskClickListener onMaskClickListener;

    public SeepRouterView(Context context) {
        super(context);
        initView(context);
    }

    public interface OnMaskClickListener {
        public void onClick(int viewId);
    }

    public void setOnMaskClickListener(OnMaskClickListener onMaskClickListener) {
        this.onMaskClickListener = onMaskClickListener;
    }

    public SeepRouterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SeepRouterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_router_view, this);
        TextView mUIView = findViewById(R.id.tv_ui);
        ImageView mCloseIv = findViewById(R.id.iv_close);
        TextView mNetGrabberView = findViewById(R.id.tv_net_grabber);
        mUIView.setOnClickListener(this);
        mCloseIv.setOnClickListener(this);
        mNetGrabberView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (onMaskClickListener != null) {
            onMaskClickListener.onClick(v.getId());
        }
    }

}
