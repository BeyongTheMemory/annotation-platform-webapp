package com.hongying.response;

import com.hongying.enums.ErrorCodeEnum;

public class BaseResponse<T> {

    private Integer code;

    private String msg;

    private T data;

    public static  <T> BaseResponse<T> buildSuccessResponse(T result){
        return BaseResponseBuilder.builder().data(result).build();
    }


    public static <T> BaseResponse<T> buildFailedResponse(ErrorCodeEnum errorCodeEnum){
        return BaseResponseBuilder.builder().code(errorCodeEnum.getCode()).msg(errorCodeEnum.getMsg()).build();
    }

    public static <T> BaseResponse<T> buildFailedResponse(String msg){
        return BaseResponseBuilder.builder().msg(msg).build();
    }

    public static <T> BaseResponse<T> buildFailedResponse(Integer code,String msg){
        return BaseResponseBuilder.builder().code(code).msg(msg).build();
    }

    private static final class BaseResponseBuilder<T> {
        private Integer code = 200;
        private String msg = "";
        private T data;

        private BaseResponseBuilder() {
        }

        public static BaseResponseBuilder builder() {
            return new BaseResponseBuilder();
        }

        public BaseResponseBuilder code(Integer code) {
            this.code = code;
            return this;
        }

        public BaseResponseBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public BaseResponseBuilder data(T data) {
            this.data = data;
            return this;
        }

        public BaseResponse build() {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.code = this.code;
            baseResponse.msg = this.msg;
            baseResponse.data = this.data;
            return baseResponse;
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
