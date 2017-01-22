package com.ytying.sysenum;

/**
 * Created by kefan.wkf on 17/1/19.
 */
public enum ReturnCode {

    RETURN_SUCCESS(1000, 0, "操作成功", TipType.TOAST),
    RETURN_ERROR(1001, 1, "操作失败", TipType.TOAST),
    RETURN_UserLogin_FAIL_NOUSER(1002, 1, "登录失败,未注册用户", TipType.TOAST),
    RETURN_PARAMERROR(1003, 1, "请求参数错误", TipType.TOAST),
    RETURN_UserLogin_FAIL_SERVICERROR(1005, 1, "登录服务异常", TipType.TOAST),
    RETURN_UserLogin_SUCCESS(1006, 0, "登录成功", TipType.TOAST),
    RETURN_USERNAMEORPASSERROR(1008, 1, "用户名或密码错误", TipType.TOAST);

    public int sn;
    public int code;
    public String tip;
    public int tipType;

    ReturnCode(int sn, int code, String tip, TipType tipType) {
        this.sn = sn;
        this.code = code;
        this.tip = tip;
        this.tipType = tipType.type;
    }
    }
