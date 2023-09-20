package com.example.boot.util;


public enum EnumCodeMessageUtil {
    SAVE_SUCCESS("4001", "保存成功"),
    SAVE_FAIL("4002`", "保存失败"),
    SELECT_SUCCESS("4005", "查询成功"),

    ;

    ;

    private String code;

    private String message;

    EnumCodeMessageUtil(String code, String message){
        this.code = code;

        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
