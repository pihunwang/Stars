package com.ytying.util;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

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
        final String regex = "(class\\s{0,})(\\S{0,})(\\s{0,}\\{)";
        Pattern classNamePattern = Pattern.compile(regex);
        Matcher classNameMatcher = classNamePattern.matcher(sourceCode);
        if (classNameMatcher.find()) {
            return classNameMatcher.group(2);
        }
        return null;
    }

    /**
     * 将源代码中类名替换成随机类名,为了解决ClassLoader缓存问题
     * 如果每次调用接口的时候都new一个新的ClassLoader就不用改类名了
     *
     * @param sourceCode
     * @param replaceClassName
     * @return
     */
    public static String replaceClassNameFromSourceCode(String sourceCode, String replaceClassName) {
        String className = getClassNameFromSourceCode(sourceCode);
        if (null == className) {
            return sourceCode;
        }
        sourceCode = sourceCode.replaceFirst(className, replaceClassName);
        return sourceCode;
    }

    /**
     * 从源码中提取代码主体
     *
     * @param sourceCode
     * @return
     */
    public static String getMainBodyFromSourceCode(String sourceCode) {
        final String regex = "(class\\s{0,})(\\S{0,})(\\s{0,}\\{\\s{0,})([\\s\\S]*)";
        Pattern mainBodyPattern = Pattern.compile(regex);
        Matcher mainBodyMatcher = mainBodyPattern.matcher(sourceCode);
        if (mainBodyMatcher.find()) {
            String mainBody = mainBodyMatcher.group(4);
            int end = mainBody.lastIndexOf("}");
            mainBody = mainBody.substring(0, end);
            return mainBody;
        }
        return null;
    }

}
