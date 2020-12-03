package com.yocn.dumpanalysis.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yocn.dumpanalysis.R;
import com.yocn.dumpanalysis.bean.MainBean;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<MainBean> list;
    private Context context;
    private int[] colors = {R.color.color1, R.color.color2, R.color.color3
            , R.color.color4, R.color.color5, R.color.color6
            , R.color.color7, R.color.color8, R.color.color9};

    private int[] textColor = {R.color.write, R.color.black, R.color.write
            , R.color.black, R.color.black, R.color.black
            , R.color.write, R.color.black, R.color.write};

    static class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        TextView name;

        public ViewHolder(View view) {
            super(view);
            root = view;
            name = (TextView) view.findViewById(R.id.name);
        }
    }

    public MainAdapter(Context context, List<MainBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainBean bean = list.get(position);
        holder.name.setText(bean.getName());
        holder.name.setTextColor(context.getResources().getColor(textColor[position % textColor.length]));
        holder.root.setBackgroundResource(colors[position% colors.length]);
        holder.name.setOnClickListener(v -> context.startActivity(new Intent(context, bean.getTarget())));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}