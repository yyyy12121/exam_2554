package com.example.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.entity.crimes;
import com.example.boot.vo.CrimeVo;
import com.example.boot.vo.DataVo;
import com.example.boot.vo.IncidentIdVo;
import com.example.boot.vo.city;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CrimeService extends IService<crimes> {
    List<city> pageList();

    IncidentIdVo add(Map params) throws Exception;
}
