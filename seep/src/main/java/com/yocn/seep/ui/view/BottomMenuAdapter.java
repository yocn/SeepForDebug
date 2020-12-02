package com.yocn.seep.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yocn.seep.R;
import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.util.DigResourceUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BottomMenuAdapter extends RecyclerView.Adapter<BottomMenuAdapter.RecyclerHolder> {
    private List<SeepResult> seepResult = new ArrayList<>();
    private Context context;
    private BottomListDialog.OnDialogInterface onDialogInterface;

    public BottomMenuAdapter(Context context, BottomListDialog.OnDialogInterface onDialogInterface) {
        this.context = context;
        this.onDialogInterface = onDialogInterface;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_seep_dialog_bottom, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, final int position) {
        SeepResult childSeepResult = seepResult.get(holder.getAdapterPosition());
        String show = "";
        String dataMsg = DigResourceUtil.getSimpleDataName(childSeepResult.getData());
        if (TextUtils.isEmpty(dataMsg)) {
            show = childSeepResult.getName();
        } else {
            show = childSeepResult.getName() + "<" + dataMsg + ">";
        }
        holder.textView.setText(show);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogInterface.clickSeepResult(seepResult.get(position));
            }
        });
    }

    public void setData(List<SeepResult> seepResult) {
        this.seepResult = seepResult;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return seepResult.size();
    }

    static class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_menu);
        }
    }
}