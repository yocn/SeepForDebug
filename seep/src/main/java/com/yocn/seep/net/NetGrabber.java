package com.yocn.seep.net;

import com.yocn.seep.net.bean.NetResult;

import java.util.ArrayList;

public class NetGrabber {
    private static NetGrabber instance;
    private static final int MAX_RANGE = 100;
    private ArrayList<NetResult> data = new ArrayList<>();

    private NetGrabber() {
    }

    public static NetGrabber getInstance() {
        if (instance == null) {
            instance = new NetGrabber();
        }
        return instance;
    }

    public synchronized void grabNet(NetResult netResult) {
        data.add(0, netResult);
        checkRange();
    }

    private synchronized void checkRange() {
        while (data.size() > MAX_RANGE) {
            data.remove(data.size() - 1);
        }
    }

    public ArrayList<NetResult> getData() {
        return data;
    }

    public void clear(){
        data.clear();
    }

    public void release() {
        data.clear();
    }
}
