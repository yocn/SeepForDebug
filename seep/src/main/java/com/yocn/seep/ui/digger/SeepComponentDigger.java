package com.yocn.seep.ui.digger;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.digger.strategy.ActivityStrategy;
import com.yocn.seep.ui.digger.strategy.ComponentStrategy;
import com.yocn.seep.ui.digger.strategy.DefaultStrategy;
import com.yocn.seep.ui.digger.strategy.FragmentStrategy;
import com.yocn.seep.ui.digger.strategy.RecyclerViewStrategy;
import com.yocn.seep.ui.digger.strategy.ViewGroupStrategy;
import com.yocn.seep.ui.digger.strategy.ViewHolderStrategy;
import com.yocn.seep.ui.digger.strategy.ViewStrategy;

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
        } else if (target instanceof ViewGroup) {
            componentStrategy = new ViewGroupStrategy<>();
        } else if (target instanceof View) {
            componentStrategy = new ViewStrategy<>();
        } else {
            componentStrategy = new DefaultStrategy<>();
        }
        return componentStrategy.getComponent(target);
    }


}
