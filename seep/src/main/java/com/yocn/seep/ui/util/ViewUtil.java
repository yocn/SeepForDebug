package com.yocn.seep.ui.util;

import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;

import com.yocn.seep.ui.bean.SeepResult;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ViewUtil {

    public static List<SeepResult> getChildSeepList(ViewGroup viewGroup, List<Fragment> fragmentList) {
        List<SeepResult> seepResultList = new ArrayList<>();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            RectF childRectF = ViewUtil.getViewBounds(view);
            SeepResult childSeepResult = new SeepResult();
            Fragment fragment = null;
            if (TypeUtil.isRecyclerView(view)) {
                childSeepResult.setType(SeepResult.SeepType.TYPE_RECYCLERVIEW);
                childSeepResult.setName(view.getClass().getSimpleName());
                childSeepResult.setRectF(childRectF);
                childSeepResult.setWeakReference(new WeakReference<>(view));
            } else if ((fragment = TypeUtil.isFragment(view, fragmentList)) != null) {
                childSeepResult.setType(SeepResult.SeepType.TYPE_FRAGMENT);
                childSeepResult.setName(fragment.getClass().getSimpleName());
                childSeepResult.setRectF(childRectF);
                childSeepResult.setWeakReference(new WeakReference<>(fragment));
            } else {
                childSeepResult.setType(SeepResult.SeepType.TYPE_VIEW);
                childSeepResult.setName(view.getClass().getSimpleName());
                childSeepResult.setRectF(childRectF);
                childSeepResult.setWeakReference(new WeakReference<>(view));
            }
            SeepLogger.d("fragment::" + fragment);
            seepResultList.add(childSeepResult);
        }
        return seepResultList;
    }

    public static int getViewHierarchyMaxDeep(ViewGroup rootView) {
        return getDeep(rootView, 1);
    }

    private static int getDeep(View view, int curr) {
        if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            int childCount = parent.getChildCount();
            if (childCount > 0) {
                int max = curr + 1;
                for (int i = 0; i < childCount; i++) {
                    View child = parent.getChildAt(i);
                    int height = getDeep(child, curr + 1);
                    if (max < height) {
                        max = height;
                    }
                }
                return max;
            } else {
                return curr;
            }
        } else {
            return curr;
        }
    }

    private List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<View>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }

    public static List<RecyclerView> getAllChildRecyclerViews(View view) {
        List<RecyclerView> allchildren = new ArrayList<>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                if (viewchild instanceof RecyclerView) {
                    allchildren.add((RecyclerView) viewchild);
                }
                allchildren.addAll(getAllChildRecyclerViews(viewchild));
            }
        }
        return allchildren;
    }

    public static RectF getViewBounds(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];
        return new RectF(viewX, viewY, viewX + view.getWidth(), viewY
                + view.getHeight());
    }
}
