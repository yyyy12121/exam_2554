package com.example.boot.exception;

/**
 * 数据验证异常
 */
public class DataVerifyException extends BaseException{
    private static final long serialVersionUID = 1L;

    public DataVerifyException(String code, String defaultMessage) {
        super(code, defaultMessage);
    }
}
