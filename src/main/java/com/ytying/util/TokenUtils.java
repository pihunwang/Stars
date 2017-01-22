package com.ytying.util;

import java.util.Random;

/**
 * Created by kefan.wkf on 17/1/20.
 */
public class TokenUtils {

    private static final char[] CHAR_POOL = new char[] { '1', '2', '3', '4',
            '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z' };

    private static final int CHAR_POOL_LENGTH = CHAR_POOL.length;

    public static String getTokenByUid(long uid) {
        return D2CP(uid) + getRandomStr(6);
    }

    public static String getOpenIdByUid(long uid) {
        return D2CP(uid) + getRandomStr(13);
    }

    public static String getKeyByUid(long uid) {
        return getRandomStr(16);
    }

    private static String getRandomStr(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHAR_POOL[random.nextInt(CHAR_POOL_LENGTH)]);
        }
        return sb.toString();

    }

    /**
     * 10进制转CHAR_POOL长度进制
     *
     * @param d
     * @return
     */
    public static String D2CP(long d) {
        StringBuilder sb = new StringBuilder();
        int k = 1;
        while (Math.pow(CHAR_POOL_LENGTH, k) <= d) {
            k++;
        }
        for (; k > 0; k--) {
            int v = (int) (d / Math.pow(CHAR_POOL_LENGTH, k - 1));
            sb.append(CHAR_POOL[v]);
            d -= v * Math.pow(CHAR_POOL_LENGTH, k - 1);
        }
        return sb.toString();
    }
}

