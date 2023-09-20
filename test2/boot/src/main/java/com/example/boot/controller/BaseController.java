package com.example.boot.controller;

import com.example.boot.util.EnumCodeMessageUtil;
import com.example.boot.util.R;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yhj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/19 19:55
 */
public class BaseController {
    @Autowired
    private HttpServletRequest request;


    public BaseController() {
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public R selectResponse(Object data) {
        return R.ok(EnumCodeMessageUtil.SELECT_SUCCESS.getCode(),
                EnumCodeMessageUtil.SELECT_SUCCESS.getMessage(),
                data
        );
    }

    public R insertResponse(boolean result) {
        if(result){
            return R.ok(EnumCodeMessageUtil.SAVE_SUCCESS.getCode(),
                    EnumCodeMessageUtil.SAVE_SUCCESS.getMessage(),
                    true);
        }else {
            return R.error(EnumCodeMessageUtil.SAVE_FAIL.getCode(),
                    EnumCodeMessageUtil.SAVE_FAIL.getMessage());
        }
    }

    /**
     * 获取查询参数
     */
    protected Map<String, Object> getGetParams(HttpServletRequest request) throws Exception {
        Map<String, Object> paramMap = new HashMap<String,Object>();
        Enumeration<String> names = request.getParameterNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            paramMap.put(name, request.getParameter(name));
        }
        return paramMap;
    }
}
