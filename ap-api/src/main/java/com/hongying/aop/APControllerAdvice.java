package com.hongying.aop;


import com.hongying.enums.ErrorCodeEnum;
import com.hongying.exceptions.ApException;
import com.hongying.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


/**
 * 统一异常拦截
 */
@ControllerAdvice
public class APControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(APControllerAdvice.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse handleAny(Throwable th) {
        logger.error(th.getMessage(), th);
        return BaseResponse.buildFailedResponse(ErrorCodeEnum.SYSTEM_EXCEPTION);
    }

    @ExceptionHandler(ApException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public BaseResponse handleBusinessException(ApException ex) {
        logger.warn(ex.getMessage());
        return BaseResponse.buildFailedResponse(ex.getCode(), ex.getMsg());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handleRuntimeException(RuntimeException ex) {
        logger.error(ex.getMessage(), ex);
        return BaseResponse.buildFailedResponse("System Error");
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public BaseResponse handleHttpMessageNotReadableException(
            MethodArgumentTypeMismatchException ex2,
            HttpRequestMethodNotSupportedException ex3) {
        return BaseResponse.buildFailedResponse(ErrorCodeEnum.HTTP_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public BaseResponse handleBindException(MethodArgumentNotValidException ex) {
        StringBuilder errorMsg = new StringBuilder();
        if (CollectionUtils.isEmpty(ex.getBindingResult().getAllErrors())) {
            logger.warn(ex.getMessage(), ex);
            return BaseResponse.buildFailedResponse("System Error");
        }
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errorMsg.append(error.getDefaultMessage());
        }
        return BaseResponse.buildFailedResponse(errorMsg.toString());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public BaseResponse handleNotReadableException(HttpMessageNotReadableException ex) {
        logger.warn(ex.getMessage(), ex);
        return BaseResponse.buildFailedResponse("非法的json格式");

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public BaseResponse handleNotReadableException(MissingServletRequestParameterException ex) {
        logger.warn(ex.getMessage(), ex);
        return BaseResponse.buildFailedResponse("缺少必填参数");
    }

}
