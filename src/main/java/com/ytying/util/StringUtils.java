package com.ytying.util;

/**
 * Created by kefan.wkf on 17/1/19.
 */
public class StringUtils {

    public static boolean isBlank(String str){
        if(str == null || str.equals("")){
            return true;
        }
        return false;
    }

}
