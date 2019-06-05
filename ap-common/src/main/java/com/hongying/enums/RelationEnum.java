package com.hongying.enums;

public enum RelationEnum {
    INSTANCE(0,"is instance of"),
    ;

    private int code;

    private String msg;

    RelationEnum(int code, String msg) {
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
