package com.yocn.seep.ui.digger.strategy;

import android.graphics.RectF;

import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.digger.SeepComponentDigger;
import com.yocn.seep.ui.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentStrategy<T extends Fragment> implements ComponentStrategy<T> {

    @Override
    public SeepResult getComponent(T fragment) {
        SeepResult seepResult = new SeepResult();
        FragmentStrategy fragmentStrategy;
//        if (fragment instanceof TabFragment) {
//            fragmentStrategy = new TabFragmentStrategy();
//        } else if (fragment instanceof OnlineFragment) {
//            fragmentStrategy = new OnlineFragmentStrategy();
//        } else {
            fragmentStrategy = new DefaultFragmentStrategy();
//        }
        seepResult = fragmentStrategy.getComponent(fragment);
        return seepResult;
    }


//    private static class OnlineFragmentStrategy<O extends OnlineFragment> extends FragmentStrategy<O> {
//
//        @Override
//        public SeepResult getComponent(O fragment) {
//            SeepResult seepResult = new SeepResult();
//            List<Fragment> fragmentList = fragment.getChildFragmentManager().getFragments();
//            for (Fragment containerFragment : fragmentList) {
//                boolean userVisible = containerFragment.getUserVisibleHint();
//                if (userVisible) {
//                    seepResult = new FragmentStrategy().getComponent(containerFragment);
//                }
//            }
//            return seepResult;
//        }
//    }
//
//    private static class TabFragmentStrategy<T extends TabFragment> extends FragmentStrategy<T> {
//
//        private ResourceFlow resourceFlow;
//        private MXRecyclerView recyclerView;
//
//        @Override
//        public SeepResult getComponent(T fragment) {
//            SeepResult seepResult = new SeepResult();
//            seepResult.setType(SeepResult.SeepType.TYPE_FRAGMENT);
//            if (fragment != null && fragment.getView() != null) {
//                seepResult.setName(fragment.getClass().getSimpleName());
//                RectF rectf = ViewUtil.getViewBounds(fragment.getView());
//                seepResult.setRectF(rectf);
//
//                try {
//                    resourceFlow = getResourceFlowField(fragment);
//                    seepResult.setData(resourceFlow.getName() + " - " + resourceFlow.getType());
//                    recyclerView = getRecyclerViewField(fragment);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                }
//
//                List<SeepResult> seepResultList = new ArrayList<>();
//                SeepResult recyclerViewSeepResult = SeepComponentDigger.getComponents(recyclerView);
//                seepResultList.add(recyclerViewSeepResult);
//                seepResult.setChild(seepResultList);
//            }
//            return seepResult;
//        }
//    }
//
//    private static ResourceFlow getResourceFlowField(TabFragment fragment) throws NoSuchFieldException, IllegalAccessException {
//        Class clazz = fragment.getClass();
//        ResourceFlow resourceFlow = null;
//        while (clazz != AbstractFlowFragment.class) {
//            clazz = clazz.getSuperclass();
//            if (clazz == Fragment.class) {
//                return null;
//            }
//        }
//        if (clazz == AbstractFlowFragment.class) {
//            Field resourceFlowField = clazz.getDeclaredField("resourceFlow");
//            resourceFlowField.setAccessible(true);
//            resourceFlow = (ResourceFlow) resourceFlowField.get(fragment);
//        }
//        return resourceFlow;
//    }
//
//    private static MXRecyclerView getRecyclerViewField(TabFragment fragment) throws NoSuchFieldException, IllegalAccessException {
//        Class clazz = fragment.getClass();
//        MXRecyclerView recyclerView = null;
//        while (clazz != AbstractFlowFragment.class) {
//            clazz = clazz.getSuperclass();
//            if (clazz == Fragment.class) {
//                return null;
//            }
//        }
//        if (clazz == AbstractFlowFragment.class) {
//            Field resourceFlowField = clazz.getDeclaredField("list");
//            resourceFlowField.setAccessible(true);
//            recyclerView = (MXRecyclerView) resourceFlowField.get(fragment);
//        }
//        return recyclerView;
//    }

    private static class DefaultFragmentStrategy<T extends Fragment> extends FragmentStrategy<T> {

        @Override
        public SeepResult getComponent(T fragment) {
            SeepResult seepResult = new SeepResult();
            seepResult.setType(SeepResult.SeepType.TYPE_FRAGMENT);
            if (fragment != null && fragment.getView() != null) {
                seepResult.setName(fragment.getClass().getSimpleName());
                RectF rectf = ViewUtil.getViewBounds(fragment.getView());
                seepResult.setRectF(rectf);
                List<RecyclerView> recyclerViewList = ViewUtil.getAllChildRecyclerViews(fragment.getView());
                List<SeepResult> seepResultList = new ArrayList<>();
                for (RecyclerView recyclerView : recyclerViewList) {
                    int visible = recyclerView.getVisibility();
                    SeepResult fragmentSeepResult = SeepComponentDigger.getComponents(recyclerView);
                    seepResultList.add(fragmentSeepResult);
                }
                seepResult.setChild(seepResultList);
            }
            return seepResult;
        }
    }
}
