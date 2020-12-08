package com.yocn.seep.ui;

import android.view.ViewGroup;

import com.yocn.seep.ui.bean.SeepResult;
import com.yocn.seep.ui.digger.SeepComponentDigger;
import com.yocn.seep.ui.view.BottomListDialog;
import com.yocn.seep.ui.view.MaskViewGroup;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AppCompatActivity;

public class UISeepManager {
    private MaskViewGroup maskViewGroup;
    private DialogStack dialogStack = new DialogStack();
    private WeakReference<AppCompatActivity> weakReference;

    public UISeepManager(WeakReference<AppCompatActivity> weakReference) {
        this.weakReference = weakReference;
    }

    public void showDialog(AppCompatActivity appCompatActivity, SeepResult seepResult) {
        BottomListDialog bottomListDialog = new BottomListDialog(appCompatActivity, onDialogInterface);
        bottomListDialog.setData(seepResult);
        bottomListDialog.show();
        if (maskViewGroup == null) {
            maskViewGroup = new MaskViewGroup(appCompatActivity);
            ViewGroup decorView = (ViewGroup) appCompatActivity.getWindow().getDecorView();
            decorView.addView(maskViewGroup, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        maskViewGroup.setSeepResult(seepResult);
        dialogStack.addDialog(bottomListDialog);
    }

    private void popDialog() {
        dialogStack.popDialog();
        if (dialogStack.getDialogSize() == 0) {
            release();
        }
    }

    private void popAllDialog() {
        while (dialogStack.getDialogSize() != 0) {
            dialogStack.popDialog();
        }
        release();
    }

    public boolean hasDialog() {
        return dialogStack.getDialogSize() == 0;
    }

    private BottomListDialog.OnDialogInterface onDialogInterface = new BottomListDialog.OnDialogInterface() {
        @Override
        public void clickSeepResult(SeepResult seepResult) {
            SeepResult target = SeepComponentDigger.getComponents(seepResult.getWeakReference().get());
            showDialog(weakReference.get(), target);
        }

        @Override
        public void close() {
            popDialog();
        }

        @Override
        public void closeAll() {
            popAllDialog();
        }
    };

    public boolean onBackPressed() {
        if (dialogStack.getDialogSize() > 0) {
            popDialog();
            return true;
        }
        return false;
    }

    private void release() {
        if (maskViewGroup != null && maskViewGroup.getParent() != null) {
            ((ViewGroup) maskViewGroup.getParent()).removeView(maskViewGroup);
        }
        dialogStack.clear();
    }

}
