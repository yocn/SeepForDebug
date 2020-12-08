package com.yocn.seep.config;

import android.view.View;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import androidx.fragment.app.Fragment;

public class SeepUIConfig {
    private Set<Class<? extends androidx.fragment.app.Fragment>> filterFragment = new HashSet<>();
    private Set<Class<? extends View>> filterView = new HashSet<>();

    public SeepUIConfig() {
    }

    public SeepUIConfig filterFragment(Class<? extends androidx.fragment.app.Fragment>... filterClasses) {
        filterFragment.addAll(Arrays.asList(filterClasses));
        return this;
    }

    public SeepUIConfig filterView(Class<? extends View>... filters) {
        filterView.addAll(Arrays.asList(filters));
        return this;
    }

    public boolean needOptFragment(Fragment fragment) {
        return filterFragment.contains(fragment.getClass());
    }
}
