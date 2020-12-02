package com.yocn.seep.ui.digger;

import android.graphics.RectF;
import android.view.View;

import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewStrategy<T extends RecyclerView> implements ComponentStrategy<T> {
    @Override
    public SeepResult getComponent(T recyclerview) {
        SeepResult seepResult = new SeepResult();
        seepResult.setType(SeepResult.SeepType.TYPE_RECYCLERVIEW);
        seepResult.setName(recyclerview.getClass().getSimpleName());
        RectF rectf = ViewUtil.getViewBounds(recyclerview);
        seepResult.setRectF(rectf);
        RecyclerView.Adapter adapter = recyclerview.getAdapter();
        List dataList = new ArrayList();
        if (adapter != null) {
            int adapterCount = adapter.getItemCount();
//            if (adapter instanceof MultiTypeAdapter) {
//                dataList = ((MultiTypeAdapter) adapter).getItems();
//            }
        }
        RecyclerView.LayoutManager layoutManager = recyclerview.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            if (lastVisibleItemPosition > -1) {
                List<SeepResult> seepResultList = new ArrayList<>();
                for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
                    View view = layoutManager.findViewByPosition(i);
                    if (view != null) {
                        RecyclerView.ViewHolder viewHolder = recyclerview.getChildViewHolder(view);
                        SeepResult viewHolderSeepResult = SeepComponentDigger.getComponents(viewHolder);
                        if (dataList != null && i < dataList.size()) {
                            Object data = dataList.get(i);
                            viewHolderSeepResult.setData(data);
                        }
                        seepResultList.add(viewHolderSeepResult);
                    }
                }
                seepResult.setChild(seepResultList);
            }
        }
        return seepResult;
    }
}
