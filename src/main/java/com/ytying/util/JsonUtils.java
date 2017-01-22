package com.ytying.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ytying.packaged.ResultDo;
import com.ytying.sysenum.ReturnCode;

/**
 * Created by kefan.wkf on 17/1/19.
 */
public class JsonUtils {

    /**
     * 将对象o转换成json格式数据
     */
    public static String Object2Json(Object o) {
        try {
            return JSON.toJSONString(o);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转换成为jsonArray对象
     */
    public static JSONArray Json2JsonArray(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return JSON.parseArray(str);

    }

    /**
     * 将非集合类java对象转换成为json object
     *
     * @param o
     * @return
     */
    public static JSONObject Object2JsonObject(Object o) {
        try {
            return (JSONObject) JSON.toJSON(o);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject json2JsonObject(String json) {
        try {
            return JSON.parseObject(json);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到格式化json数据  退格用\t 换行用\r
     */
    public static String format(String jsonStr) {
        int level = 0;
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }

        return jsonForMatStr.toString();

    }

    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

    public static void print(Object data) {
        System.out.println(format(JsonUtils.Object2Json(new ResultDo(ReturnCode.RETURN_SUCCESS, data))));
    }

}
