package com.yocn.seep.net.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yocn.seep.R;
import com.yocn.seep.net.NetGrabber;
import com.yocn.seep.net.ViewJsonActivity;
import com.yocn.seep.net.bean.NetResult;
import com.yocn.seep.net.jsonviewer.utils.Utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NetGrabberAdapter extends RecyclerView.Adapter<NetGrabberAdapter.NetHolder> {
    private Context context;

    public NetGrabberAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_net_grabber, parent, false);
        return new NetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NetHolder holder, int position) {
        NetResult netResult = NetGrabber.getInstance().getData().get(position);
        holder.mUrlTv.setText(netResult.getUrl());
        holder.mTimeTv.setText(netResult.getDateTime().toString("yyyy-MM-dd HH:mm:ss.SSS"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewJsonActivity.start(context, netResult);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Utils.copyText(context, netResult.getUrl(), "this url has been copied to clipboard");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return NetGrabber.getInstance().getData().size();
    }

    static class NetHolder extends RecyclerView.ViewHolder {
        TextView mUrlTv;
        TextView mTimeTv;

        NetHolder(@NonNull View itemView) {
            super(itemView);
            mUrlTv = itemView.findViewById(R.id.tv_url);
            mTimeTv = itemView.findViewById(R.id.tv_time);
        }
    }
}
