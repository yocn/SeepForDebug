package com.yocn.seep.ui.util;


public class DigResourceUtil {
    public static String getSimpleDataShowing(Object data) {
        if (data == null) {
            return "";
        }
        String showMsg = data.toString();
//        if (data instanceof ResourceFlow) {
//            ResourceFlow resourceFlow = (ResourceFlow) data;
//            return "ResourceFlow\n" + "id:" + resourceFlow.getId() + "\n"
//                    + "name:" + resourceFlow.getName() + "\n"
//                    + "type:" + resourceFlow.getType() + "  style: " + resourceFlow.getStyle();
//        }
//        if (data instanceof OnlineResource) {
//            OnlineResource onlineResource = (OnlineResource) data;
//            return "OnlineResource\n" + "id:" + onlineResource.getId() + "\n"
//                    + "name:" + onlineResource.getName() + "\n"
//                    + "type:" + onlineResource.getType();
//        }
        return showMsg;
    }

    public static String getSimpleDataName(Object data) {
        if (data == null) {
            return "";
        }
        String name = "";
//        if (data instanceof OnlineResource) {
//            OnlineResource onlineResource = (OnlineResource) data;
//            return onlineResource.getName();
//        }
        return name;
    }
}
