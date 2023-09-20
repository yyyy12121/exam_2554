package com.example.boot.exception;


public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    private String code;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

}
