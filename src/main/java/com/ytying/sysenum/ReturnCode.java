package com.ytying.sysenum;

/**
 * Created by kefan.wkf on 17/1/19.
 */
public enum ReturnCode {

    RETURN_SUCCESS(1000, 0, "操作成功", TipType.TOAST),
    RETURN_ERROR(1001, 1, "操作失败", TipType.TOAST),
    RETURN_PARAM_ERROR(1002, 1, "请求参数错误", TipType.TOAST),

    RETURN_UserLogin_FAIL_NOUSER(2001, 1, "登录失败,未注册用户", TipType.TOAST),
    RETURN_UserLogin_FAIL_SERVICERROR(2002, 1, "登录服务异常", TipType.TOAST),
    RETURN_UserLogin_SUCCESS(2003, 0, "登录成功", TipType.TOAST),
    RETURN_USER_NAME_OR_PASS_ERROR(2004, 1, "用户名或密码错误", TipType.TOAST),
    RETURN_USER_ALREADY_EXISTS(2005, 1, "该用户已存在", TipType.TOAST),

    RETURN_CodeMake_COMPILE_ERROR(3001,1,"编译失败",TipType.TOAST),
    RETURN_CodeMake_TIMEOUT_ERROR(3002,1,"程序运行超时",TipType.TOAST),
    RETURN_CodeMake_UNKNOW_ERROR(3003,1,"未知错误",TipType.TOAST),

    RETURN_NOTOKEN_ERROR(9998, 98, "无权限访问，请登录!", TipType.TOAST),
    RETURN_TOKEN_OUTDATE(9999, 99, "您的Token已过期，请重新登录!", TipType.TOAST);

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
