package com.yocn.seep.ui.digger.strategy;

import android.graphics.RectF;
import android.view.ViewGroup;

import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.digger.SeepComponentDigger;
import com.yocn.seep.ui.util.ViewUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ViewGroupStrategy<T extends ViewGroup> implements ComponentStrategy<T> {

    @Override
    public SeepResult getComponent(T viewGroup) {
        SeepResult seepResult = new SeepResult();
        seepResult.setType(SeepResult.SeepType.TYPE_VIEW_GROUP);
        seepResult.setName(viewGroup.getClass().getSimpleName());
        seepResult.setWeakReference(new WeakReference<>(viewGroup));
        RectF rectf = ViewUtil.getViewBounds(viewGroup);
        seepResult.setRectF(rectf);
        List<SeepResult> childSeepResultList = new ArrayList<>();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            SeepResult childSeepResult = SeepComponentDigger.getComponents(viewGroup.getChildAt(i));
            childSeepResultList.add(childSeepResult);
        }
        seepResult.setChild(childSeepResultList);
        return seepResult;
    }
}
