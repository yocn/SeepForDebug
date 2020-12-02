package com.yocn.seep.ui.digger;

import com.yocn.seep.ui.bean.SeepResult;

public class DefaultStrategy<T> implements ComponentStrategy<T> {

    @Override
    public SeepResult getComponent(T viewHolder) {
        SeepResult seepResult = new SeepResult();
        seepResult.setName("No Found!");
        return seepResult;
    }
}
