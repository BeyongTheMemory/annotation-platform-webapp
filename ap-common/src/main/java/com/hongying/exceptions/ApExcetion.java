package com.hongying.exceptions;

public class ApExcetion extends RuntimeException {
    public ApExcetion(String message) {
        super(message);
    }

    public ApExcetion(String message, Throwable cause) {
        super(message, cause);
    }

    public ApExcetion(Throwable cause) {
        super(cause);
    }

    public ApExcetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
