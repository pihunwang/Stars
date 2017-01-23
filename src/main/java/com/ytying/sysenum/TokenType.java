package com.ytying.sysenum;

/**
 * Created by kefan.wkf on 17/1/22.
 */
public enum TokenType {

    NULL((byte) 0, 0L),
    LONGTIME((byte) 1, 7 * 24 * 3600 * 1000L),  //一星期
    SHORTTIME((byte) 2, 1 * 3600 * 1000L);  //一小时

    public byte type;
    public long survivetime;

    TokenType(byte type, long survivetime) {
        this.type = type;
        this.survivetime = survivetime;
    }

    public static boolean valid(byte type) {
        TokenType[] pts = TokenType.values();
        for (TokenType pt : pts) {
            if (type == pt.type) {
                return true;
            }
        }
        return false;
    }
}
