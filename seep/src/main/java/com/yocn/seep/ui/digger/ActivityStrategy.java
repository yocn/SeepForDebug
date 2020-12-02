package com.yocn.seep.ui.digger;

import android.graphics.RectF;
import android.view.ViewGroup;

import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ActivityStrategy<T extends AppCompatActivity> implements ComponentStrategy<T> {

    private static boolean supportGaanaUIFragment() {
        return false;
    }

    private static boolean filterFragment(String name) {
        if (name.equals("GaanaUIFragment")) {
            return supportGaanaUIFragment();
        } else {
            return true;
        }
    }

    @Override
    public SeepResult getComponent(T activity) {
        SeepResult seepResult = new SeepResult();
        seepResult.setType(SeepResult.SeepType.TYPE_ACTIVITY);
        seepResult.setName(activity.getClass().getSimpleName());
        List<SeepResult> seepResultList = new ArrayList<>();
        ViewGroup content = activity.findViewById(android.R.id.content);
        RectF activityRectF = ViewUtil.getViewBounds(content);
        seepResult.setRectF(activityRectF);
        List<Fragment> fragmentList = activity.getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragmentList) {
            boolean userVisible = fragment.getUserVisibleHint();
            String fragmentName = fragment.getClass().getSimpleName();
            if (userVisible && filterFragment(fragmentName)) {
                SeepResult fragmentSeepResult = SeepComponentDigger.getComponents(fragment);
                seepResultList.add(fragmentSeepResult);
            }
        }

        seepResult.setChild(seepResultList);
        return seepResult;
    }
}
