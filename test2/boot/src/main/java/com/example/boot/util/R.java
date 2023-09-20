package com.example.boot.util;

import lombok.Data;

/**
 * @author yhj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/19 19:52
 */

@Data
public class R {
    private String code = "0";

    private String status;

    private Object result;

    public R(){

    }

    public R(Object data){
        this.result = data;
    }

    public static R error(String detailCode, String message){
        R r = new R();
        r.setStatus("error");
        return r;
    }

    public static R ok(String detailCode, String message, Object data){
        R r = new R();
        r.setStatus("ok");
        r.setResult(data);
        return r;
    }

    public static R ok(String detailCode, String message){
        R r = new R();
        r.setStatus("ok");
        return r;
    }
}
