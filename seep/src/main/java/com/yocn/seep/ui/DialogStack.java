package com.yocn.seep.ui;

import com.yocn.seep.ui.view.BottomListDialog;

import java.util.ArrayDeque;

public class DialogStack {
    private ArrayDeque<BottomListDialog> dialogArrayDeque = new ArrayDeque<>();

    public DialogStack() {
    }

    public void addDialog(BottomListDialog bottomListDialog) {
        BottomListDialog last = dialogArrayDeque.peekLast();
        if (last != null) {
            last.dismiss();
        }
        dialogArrayDeque.addLast(bottomListDialog);
    }

    public void popDialog() {
        BottomListDialog last = dialogArrayDeque.pollLast();
        if (last != null) {
            last.dismiss();
        }
        BottomListDialog preLast = dialogArrayDeque.peekLast();
        if (preLast != null) {
            preLast.show();
        }
    }

    public void clear() {
        dialogArrayDeque.clear();
    }

    public int getDialogSize(){
        return dialogArrayDeque.size();
    }
}
