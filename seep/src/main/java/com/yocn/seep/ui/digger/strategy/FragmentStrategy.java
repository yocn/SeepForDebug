package com.yocn.seep.ui.digger.strategy;

import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;

import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

public class FragmentStrategy<T extends Fragment> implements ComponentStrategy<T> {

    @Override
    public SeepResult getComponent(T fragment) {
        SeepResult seepResult = new SeepResult();
        FragmentStrategy fragmentStrategy = new DefaultFragmentStrategy();
        seepResult = fragmentStrategy.getComponent(fragment);
        return seepResult;
    }

    private static class DefaultFragmentStrategy<T extends Fragment> extends FragmentStrategy<T> {

        @Override
        public SeepResult getComponent(T fragment) {
            SeepResult seepResult = new SeepResult();
            seepResult.setType(SeepResult.SeepType.TYPE_FRAGMENT);
            if (fragment != null && fragment.getView() != null) {
                View fragmentRootView = fragment.getView();
                seepResult.setName(fragment.getClass().getSimpleName());
                RectF rectf = ViewUtil.getViewBounds(fragmentRootView);
                seepResult.setRectF(rectf);
                List<Fragment> fragmentList = fragment.getChildFragmentManager().getFragments();
                if (fragmentRootView instanceof ViewGroup) {
                    seepResult.setChild(ViewUtil.getChildSeepList((ViewGroup) fragmentRootView, fragmentList));
                }
            }
            return seepResult;
        }
    }
}
