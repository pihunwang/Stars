package com.ytying.packaged;

import com.ytying.sysenum.ReturnCode;

/**
 * Created by kefan.wkf on 17/1/19.
 */
public class ResultDo {

    private int sn;
    private int code;
    private String tip;
    private int tipType;
    private Object data;

    public ResultDo(ReturnCode info,Object data){
        this.code = info.code;
        this.data = data;
        this.sn = info.sn;
        this.tip = info.tip;
        this.tipType = info.tipType;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getTipType() {
        return tipType;
    }

    public void setTipType(int tipType) {
        this.tipType = tipType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
