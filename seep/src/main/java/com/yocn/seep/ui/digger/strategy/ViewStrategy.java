package com.yocn.seep.ui.digger.strategy;

import android.graphics.RectF;
import android.view.View;

import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.util.ViewUtil;

import java.lang.ref.WeakReference;

public class ViewStrategy<T extends View> implements ComponentStrategy<T> {

    @Override
    public SeepResult getComponent(T view) {
        SeepResult seepResult = new SeepResult();
        seepResult.setType(SeepResult.SeepType.TYPE_VIEW_HOLDER);
        seepResult.setName(view.getClass().getSimpleName());
        seepResult.setWeakReference(new WeakReference<>(view));
        RectF rectf = ViewUtil.getViewBounds(view);
        seepResult.setRectF(rectf);
        return seepResult;
    }
}
