package com.yocn.seep.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yocn.seep.R;
import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.util.ScreenUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BottomListDialog extends Dialog {
    private DisplayMetrics displayMetrics;
    private View view;
    private SeepResult current;
    private BottomMenuAdapter bottomMenuAdapter;
    private RecyclerView recyclerView;
    private TextView titleTV;
    private ImageView closeTV;
    private OnDialogInterface onDialogInterface;
    private ImageView mBackIV;
    private RelativeLayout mBackRL;

    public BottomListDialog(Context context, OnDialogInterface onDialogInterface) {
        super(context, R.style.SeepDialog);

        mContext = context;
        this.onDialogInterface = onDialogInterface;
        displayMetrics = context.getResources().getDisplayMetrics();
    }

    private Context mContext;

    public BottomListDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BottomListDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public interface OnDialogInterface {
        public void clickSeepResult(SeepResult seepResult);

        public void close();

        public void closeAll();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(mContext, R.layout.dialog_seep_bottom, null);
        titleTV = view.findViewById(R.id.tv_title);
        closeTV = view.findViewById(R.id.iv_close);
        mBackIV = view.findViewById(R.id.iv_back);
        mBackRL = view.findViewById(R.id.rl_back);
        recyclerView = view.findViewById(R.id.rv);
        bottomMenuAdapter = new BottomMenuAdapter(mContext, onDialogInterface);
        recyclerView.setAdapter(bottomMenuAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        bottomMenuAdapter.setData(current.getChild());
        titleTV.setText(current.getName());
        mBackRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogInterface.close();
            }
        });
        closeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogInterface.closeAll();
            }
        });
    }

    public void setData(SeepResult current) {
        this.current = current;
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = displayMetrics.widthPixels;
        params.height = ScreenUtil.dp2px(getContext(), 50 * (1 + current.getChild().size()));
        params.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(params);
        getWindow().setContentView(view);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void onBackPressed() {
        onDialogInterface.close();
    }
}