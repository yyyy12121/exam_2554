package com.example.boot.vo;

import java.util.HashMap;

public class DataVo extends HashMap<String, Object> {

    private DataVo(){}

    public static DataVo bd(){
        return new DataVo();
    }

}
