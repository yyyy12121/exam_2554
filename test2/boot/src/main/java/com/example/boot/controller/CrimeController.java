package com.example.boot.controller;

import com.example.boot.service.CrimeService;
import com.example.boot.util.R;
import com.example.boot.vo.CrimeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yhj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/19 19:46
 */
@RestController
@RequestMapping("/api")
public class CrimeController  extends BaseController{

    @Autowired
    private CrimeService crimeService;

    @GetMapping("/crime/stat")
    public R stat() {
        return selectResponse(crimeService.pageList());
    }

    @PostMapping("/crime")
    public R crime(@RequestBody Map params) throws Exception{
        return selectResponse(crimeService.add(params));
    }
}
