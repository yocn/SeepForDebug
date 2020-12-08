package com.yocn.dumpanalysis.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author yocn
 */
public class FlowPagerAdapter extends FragmentPagerAdapter {
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    private TabFragment.PagerFragment myFragment1 = null;
    private TabFragment.PagerFragment myFragment2 = null;
    private TabFragment.PagerFragment myFragment3 = null;

    public FlowPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        myFragment1 = new TabFragment.PagerFragment(PAGE_ONE);
        myFragment2 = new TabFragment.PagerFragment(PAGE_TWO);
        myFragment3 = new TabFragment.PagerFragment(PAGE_THREE);
    }

    public FlowPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case PAGE_ONE:
                fragment = myFragment1;
                break;
            case PAGE_TWO:
                fragment = myFragment2;
                break;
            case PAGE_THREE:
                fragment = myFragment3;
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
