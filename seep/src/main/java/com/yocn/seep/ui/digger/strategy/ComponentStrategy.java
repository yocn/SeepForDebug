package com.yocn.seep.ui.digger.strategy;

import com.yocn.seep.ui.bean.SeepResult;

public interface ComponentStrategy<O> {
    public SeepResult getComponent(O target);
}
