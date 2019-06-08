package com.hongying.exceptions;

import com.hongying.enums.ErrorCodeEnum;

public class ApException extends RuntimeException {
    private Integer code;

    private String msg;

    public ApException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }

    public ApException(String message) {
        super(message);
        this.code = -1;
        this.msg = message;
    }

    public ApException(Integer code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
