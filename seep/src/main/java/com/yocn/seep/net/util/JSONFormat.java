package com.yocn.seep.net.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONFormat {

    private String src;
    private int TABLength = 0;

    private final String BRACKET_LEFT = "[";
    private final String BRACKET_RIGHT = "]";
    private final String BRACE_LEFT = "{";
    private final String BRACE_RIGHT = "}";
    private final String COMMA = ",";
    private final String LINE_BREAK = "\n";
    private final String TAB = "\t";


    public JSONFormat(String src) {
        this.src = src;
    }

    public String format() {
        String result = src;
        try {
            JSONObject jsonObject = new JSONObject(src);
            return format(src);
        } catch (JSONException e) {
            e.printStackTrace();
            return result;
        }
    }

    private String format(String src) {
        StringBuffer result = new StringBuffer();
        char[] srcArray = src.toCharArray();
        for (int index = 0; index < src.length(); index++) {
            result.append(srcArray[index]);

            //{
            if (BRACE_LEFT.equals(String.valueOf(srcArray[index]))) {
                result.append(appendLINE_BREAKAndTAB(++TABLength));
            }
            //}
            if (BRACE_RIGHT.equals(String.valueOf(srcArray[index]))) {
                result.insert(result.length() - 1, appendLINE_BREAKAndTAB(--TABLength));
            }
            //[
            if (BRACKET_LEFT.equals(String.valueOf(srcArray[index]))) {
                result.append(appendLINE_BREAKAndTAB(++TABLength));
            }
            //]
            if (BRACKET_RIGHT.equals(String.valueOf(srcArray[index]))) {
                result.insert(result.length() - 1, appendLINE_BREAKAndTAB(--TABLength));
            }
            //,
            if (COMMA.equals(String.valueOf(srcArray[index]))) {
                result.append(appendLINE_BREAKAndTAB(TABLength));
            }
        }
        return result.toString();
    }

    //追加换行符和   确定长度的制表符
    private String appendLINE_BREAKAndTAB(int TABTimes) {
        StringBuffer temp = new StringBuffer();
        temp.append(appendLINE_BREAK());
        temp.append(appendTAB(TABTimes));
        return temp.toString();
    }

    private String appendLINE_BREAK() {
        return LINE_BREAK;
    }

    private String appendTAB(int TABTimes) {
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < TABTimes; i++) {
            temp.append(TAB);
        }
        return temp.toString();
    }

}
