package com.ytying.sysenum;

/**
 * Created by kefan.wkf on 17/1/19.
 */
public enum TipType {

    NULL(0),
    TOAST(1),
    ALERT(2);

    public int type;

    TipType(int type) {
        this.type = type;
    }

    public static boolean valid(int type){
        TipType[] pts = TipType.values();
        for(TipType pt : pts){
            if(type == pt.type){
                return true;
            }
        }
        return false;
    }
}
