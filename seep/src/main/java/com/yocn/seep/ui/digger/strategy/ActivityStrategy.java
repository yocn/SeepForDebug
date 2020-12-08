package com.yocn.seep.ui.digger.strategy;

import android.graphics.RectF;
import android.view.ViewGroup;

import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.util.TypeUtil;
import com.yocn.seep.ui.util.ViewUtil;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ActivityStrategy<T extends AppCompatActivity> implements ComponentStrategy<T> {

    @Override
    public SeepResult getComponent(T activity) {
        SeepResult seepResult = new SeepResult();
        seepResult.setType(SeepResult.SeepType.TYPE_ACTIVITY);
        seepResult.setName(activity.getClass().getSimpleName());
        ViewGroup content = activity.findViewById(android.R.id.content);
        ViewGroup activityRootView = (ViewGroup) content.getChildAt(0);
        RectF activityRectF = ViewUtil.getViewBounds(activityRootView);
        seepResult.setRectF(activityRectF);
        List<Fragment> fragmentList = activity.getSupportFragmentManager().getFragments();

        seepResult.setChild(TypeUtil.getChildSeepList(activityRootView, fragmentList));
        return seepResult;
    }
}
