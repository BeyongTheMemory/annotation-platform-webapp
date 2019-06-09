package com.hongying.enums;

public enum ErrorReasonEnum {
    ENTITY_ERR(1,"Entity is wrong"),
    ENTITY_CATEGORY_ERR(2,"Category is wrong"),
    ;

    private int code;

    private String msg;

    ErrorReasonEnum(int code, String msg) {
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
