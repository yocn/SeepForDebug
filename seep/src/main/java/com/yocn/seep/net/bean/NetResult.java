package com.yocn.seep.net.bean;

import org.joda.time.DateTime;

import java.io.Serializable;

public class NetResult implements Serializable {

    private String url;
    private Object data;
    private DateTime dateTime;

    public NetResult() {
    }

    public NetResult(String url, Object data, DateTime dateTime) {
        this.url = url;
        this.data = data;
        this.dateTime = dateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "NetSeepResult{" +
                "url='" + url + '\'' +
                ", data=" + data +
                ", Time=" + dateTime.toString("yyyy-MM-dd HH:mm:ss") +
                '}';
    }
}
