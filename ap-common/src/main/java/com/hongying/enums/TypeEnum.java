package com.hongying.enums;

public enum TypeEnum {

    SINGLE_RELATION(0, "单个关系"),
    ;

    private int code;

    private String msg;

    TypeEnum(int code, String msg) {
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
