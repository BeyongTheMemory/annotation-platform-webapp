package com.hongying.enums;

public enum ErrorReasonEnum {
    ENTITY_ERR(0,"Entity is not a entity"),
    ENTITY_RANGE_ERR(1,"Entity range error"),
    ENTITY_CATEGORY_ERR(2,"Entity is not belong to this category"),
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
