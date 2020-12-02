package com.yocn.seep.ui.digger;

import android.app.Activity;

import com.yocn.seep.ui.bean.SeepResult;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class SeepComponentDigger {

    public static SeepResult getComponents(Object target) {
        ComponentStrategy componentStrategy;
        if (target instanceof Activity) {
            componentStrategy = new ActivityStrategy<>();
        } else if (target instanceof Fragment) {
            componentStrategy = new FragmentStrategy<>();
        } else if (target instanceof RecyclerView) {
            componentStrategy = new RecyclerViewStrategy<>();
        } else if (target instanceof RecyclerView.ViewHolder) {
            componentStrategy = new ViewHolderStrategy<>();
        } else {
            componentStrategy = new DefaultStrategy<>();
        }
        return componentStrategy.getComponent(target);
    }


}
