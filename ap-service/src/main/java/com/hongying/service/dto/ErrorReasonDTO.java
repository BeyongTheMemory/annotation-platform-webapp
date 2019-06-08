package com.hongying.service.dto;


import com.hongying.enums.ErrorReasonEnum;

public class ErrorReasonDTO {
    /**
     * @see ErrorReasonEnum
     */
    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
