package com.yocn.dumpanalysis.bean;

import com.yocn.dumpanalysis.activity.BaseActivity;

public class MainBean {
    private String name;
    private Class<? extends BaseActivity> target;

    public MainBean(String name, Class<? extends BaseActivity> target) {
        this.name = name;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public Class<? extends BaseActivity> getTarget() {
        return target;
    }
}
