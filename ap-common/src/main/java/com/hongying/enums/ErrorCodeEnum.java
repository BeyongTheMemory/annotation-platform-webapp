package com.hongying.enums;

public enum ErrorCodeEnum {
    PARAM_ERROR(1, "入参错误"),
    LOGIN_ERROR(2,"用户未登录"),
    ;
    private int code;

    private String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
