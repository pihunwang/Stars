package com.ytying.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kefan.wkf on 17/1/19.
 */
public class StringUtils {

    public static boolean isBlank(String str) {
        if (null == str || str.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 从源码中匹配出类名
     *
     * @param sourceCode
     * @return
     */
    public static String getClassNameFromSourceCode(String sourceCode) {
        Pattern classNamePattern = Pattern.compile("(class\\s{0,})(.*?)(\\s{0,}\\{)");
        Matcher classNameMatcher = classNamePattern.matcher(sourceCode);
        if (classNameMatcher.find()) {
            return classNameMatcher.group(2);
        }
        return null;
    }

}
