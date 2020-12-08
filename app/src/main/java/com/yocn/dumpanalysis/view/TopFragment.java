package com.yocn.dumpanalysis.view;

import android.os.Bundle;
import android.view.View;

import com.yocn.dumpanalysis.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author yocn
 */
public class TopFragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_top;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getChildFragmentManager().beginTransaction().add(R.id.fl_1, getInstance(TopInnerFragment.class)).commitAllowingStateLoss();
        getChildFragmentManager().beginTransaction().add(R.id.fl_2, getInstance(TopInnerFragment.class)).commitAllowingStateLoss();
    }

    public static class TopInnerFragment extends BaseFragment {

    }

}
