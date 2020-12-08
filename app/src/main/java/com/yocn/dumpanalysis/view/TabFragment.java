package com.yocn.dumpanalysis.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.yocn.dumpanalysis.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @author yocn
 */
public class TabFragment {

    public static class PagerFragment extends BaseFragment {
        private int index;

        public PagerFragment(int index) {
            this.index = index;
        }

        @Override
        protected void initView() {
            super.initView();
            TextView contentView = rootView.findViewById(R.id.tv_content);
            contentView.setText(getClass().getSimpleName() + "[ " + index + " ]");
        }
    }

    public static class FlowFragment extends BaseFragment {

        private Handler handler;
        private ViewPager viewpager;

        @Override
        protected int getLayoutRes() {
            return R.layout.fragment_flow;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            viewpager = rootView.findViewById(R.id.viewpager);
            FlowPagerAdapter flowPagerAdapter = new FlowPagerAdapter(getChildFragmentManager());
            viewpager.setAdapter(flowPagerAdapter);
            viewpager.setCurrentItem(0);
            handler = new Handler();
            handler.postDelayed(scrollRunnable, 3000);
        }

        Runnable scrollRunnable = new Runnable() {
            @Override
            public void run() {
                int currentPosition = viewpager.getCurrentItem();
                int nextPosition = currentPosition == 2 ? 0 : currentPosition + 1;
                viewpager.setCurrentItem(nextPosition, true);
                handler.postDelayed(scrollRunnable, 3000);
            }
        };

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            handler.removeCallbacks(scrollRunnable);
        }
    }

    public static class DiscoverFragment extends BaseFragment {

    }

    public static class HomeFragment extends BaseFragment {

    }

    public static class ChatFragment extends BaseFragment {

    }

    public static class MeFragment extends BaseFragment {

    }
}
