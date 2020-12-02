package com.yocn.seep.ui.digger;

import android.graphics.RectF;

import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderStrategy<T extends RecyclerView.ViewHolder> implements ComponentStrategy<T> {

    @Override
    public SeepResult getComponent(T viewHolder) {
        SeepResult seepResult = new SeepResult();
        seepResult.setType(SeepResult.SeepType.TYPE_VIEW_HOLDER);
        seepResult.setName(viewHolder.getClass().getSimpleName());
        RectF rectf = ViewUtil.getViewBounds(viewHolder.itemView);
        seepResult.setRectF(rectf);
        List<RecyclerView> list = ViewUtil.getAllChildRecyclerViews(viewHolder.itemView);
        if (list.size() > 0) {
            List<SeepResult> childSeepResultList = new ArrayList<>();
            for (RecyclerView recyclerView : list) {
                SeepResult childSeepResult = SeepComponentDigger.getComponents(recyclerView);
                childSeepResultList.add(childSeepResult);
            }
            seepResult.setChild(childSeepResultList);
        }
        return seepResult;
    }
}
