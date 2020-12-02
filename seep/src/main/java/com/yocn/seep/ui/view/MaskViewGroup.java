package com.yocn.seep.ui.view;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yocn.seep.R;
import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.util.DigResourceUtil;

import androidx.annotation.Nullable;

public class MaskViewGroup extends RelativeLayout implements View.OnClickListener {
    SeepResult seepResult;
    TextView maskView;
    TextView mTopView;

    public MaskViewGroup(Context context) {
        super(context);
        initView(context);
    }

    public MaskViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MaskViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_seep_mask, this);
        mTopView = findViewById(R.id.tv_top);
        maskView = findViewById(R.id.view_mask);
        mTopView.setOnClickListener(this);
        maskView.setOnClickListener(this);
    }

    public void setSeepResult(SeepResult seepResult) {
        this.seepResult = seepResult;
        RectF rectF = seepResult.getRectF();
        LayoutParams layoutParams = (LayoutParams) maskView.getLayoutParams();
        layoutParams.topMargin = (int) rectF.top;
        layoutParams.leftMargin = (int) rectF.left;
        layoutParams.width = (int) (rectF.right - rectF.left);
        layoutParams.height = (int) (rectF.bottom - rectF.top);
        maskView.setLayoutParams(layoutParams);
        maskView.setText(seepResult.getName());

        mTopView.setVisibility(GONE);
        if (seepResult.getData() != null) {
            mTopView.setText(getShowMsg());
            mTopView.setVisibility(VISIBLE);
        }
    }

    private String getShowMsg() {
        if (seepResult == null) {
            return "";
        }
        return DigResourceUtil.getSimpleDataShowing(seepResult.getData());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_top) {
            mTopView.setVisibility(GONE);
        } else if (id == R.id.view_mask) {
            if (seepResult != null && seepResult.getData() != null) {
                mTopView.setText(getShowMsg());
                mTopView.setVisibility(VISIBLE);
            }
        }
    }
}
