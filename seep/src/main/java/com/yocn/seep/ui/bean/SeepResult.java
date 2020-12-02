package com.yocn.seep.ui.bean;

import android.graphics.RectF;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.StringDef;

public class SeepResult {
    @StringDef({SeepType.TYPE_ACTIVITY,
            SeepType.TYPE_FRAGMENT,
            SeepType.TYPE_RECYCLERVIEW,
            SeepType.TYPE_VIEW_HOLDER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SeepType {
        String TYPE_ACTIVITY = "activity";
        String TYPE_FRAGMENT = "fragment";
        String TYPE_RECYCLERVIEW = "recyclerView";
        String TYPE_VIEW_HOLDER = "viewHolder";
    }

    private String name = "";
    private String type = "";
    private RectF rectF;
    private Object data;
    private List<SeepResult> child = new ArrayList<>();

    public SeepResult() {
    }

    public List<SeepResult> getChild() {
        return child;
    }

    public void setChild(List<SeepResult> child) {
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RectF getRectF() {
        return rectF;
    }

    public void setRectF(RectF rectF) {
        this.rectF = rectF;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SeepResult{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", rectF=" + rectF +
                ", data=" + data +
                ", child=" + child +
                '}' + "\n";
    }
}
