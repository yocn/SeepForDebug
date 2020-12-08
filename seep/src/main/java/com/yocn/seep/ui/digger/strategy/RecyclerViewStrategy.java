package com.yocn.seep.ui.digger.strategy;

import android.graphics.RectF;
import android.view.View;

import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.digger.SeepComponentDigger;
import com.yocn.seep.ui.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * TODO: 怎样能得到viewHolder对应的数据？
 * 设置自动获取或者给一个策略，如果设置了策略就按照策越获取，否则自动获取，让用户选择
 * 1、反射获取adapter里面所有的list，让用户选择
 * 2、没有list，用viewModel之类的获取怎么整？？？用户可以自己配置策略？
 * @param <T>
 */
public class RecyclerViewStrategy<T extends RecyclerView> implements ComponentStrategy<T> {

    @Override
    public SeepResult getComponent(T recyclerview) {
        SeepResult seepResult = new SeepResult();
        seepResult.setType(SeepResult.SeepType.TYPE_RECYCLERVIEW);
        seepResult.setName(recyclerview.getClass().getSimpleName());
        RectF rectf = ViewUtil.getViewBounds(recyclerview);
        seepResult.setRectF(rectf);
        RecyclerView.Adapter adapter = recyclerview.getAdapter();
        List dataList = new ArrayList();
        if (adapter != null) {
            int adapterCount = adapter.getItemCount();
//            if (adapter instanceof MultiTypeAdapter) {
//                dataList = ((MultiTypeAdapter) adapter).getItems();
//            }
        }
        RecyclerView.LayoutManager layoutManager = recyclerview.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            if (lastVisibleItemPosition > -1) {
                List<SeepResult> seepResultList = new ArrayList<>();
                for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
                    View view = layoutManager.findViewByPosition(i);
                    if (view != null) {
                        RecyclerView.ViewHolder viewHolder = recyclerview.getChildViewHolder(view);
                        SeepResult viewHolderSeepResult = SeepComponentDigger.getComponents(viewHolder);
                        if (dataList != null && i < dataList.size()) {
                            Object data = dataList.get(i);
                            viewHolderSeepResult.setData(data);
                        }
                        seepResultList.add(viewHolderSeepResult);
                    }
                }
                seepResult.setChild(seepResultList);
            }
        }
        return seepResult;
    }
}
