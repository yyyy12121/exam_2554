package com.example.boot.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.interfaces.Func;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.dao.crimesDao;
import com.example.boot.entity.crimes;
import com.example.boot.exception.DataVerifyException;
import com.example.boot.service.CrimeService;
import com.example.boot.vo.CrimeVo;
import com.example.boot.vo.IncidentIdVo;
import com.example.boot.vo.city;
import com.example.boot.vo.riskTopVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author yhj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/19 19:49
 */
@Service("CrimeService")
public class CrimerServiceImpl  extends ServiceImpl<crimesDao, crimes> implements CrimeService {

    @Override
    public List<city> pageList() {
        List<city> crimeVos1 = baseMapper.selectYear();

        for(city city:crimeVos1){
            Integer days = dayNum(city.getYear());
            List<riskTopVo> cities = baseMapper.selectBy(city.getYear(),days);

            city.setRiskTop3(cities);
        }
        return crimeVos1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IncidentIdVo add(Map params) throws Exception{
        crimes crimes = new crimes();
        Object data = params.get("data");
        Map map = JSONObject.parseObject(JSONObject.toJSONString(data), Map.class);
        crimes.setCity(map.get("city").toString());
        crimes.setVictims(Integer.valueOf(map.get("Victims").toString()));
        crimes.setOffenceCode(Integer.valueOf(map.get("offenceCode").toString()));
        crimes.setStartDateTime(map.get("startDateTime").toString());
        crimes.setDispatchTime(map.get("dispatchTime").toString());
        baseMapper.insert(crimes);
        IncidentIdVo incidentIdVo = new IncidentIdVo();
        incidentIdVo.setIncidentID(crimes.getIncidentId());
        return incidentIdVo;
    }


    /**
     * @description:  根据传入的年份确定那天的天数，0为本年
     * @param: [year]
     * @return:
     * @author yaohuaijian
     * @date: 2023/9/20 11:40
     */
    public int dayNum(int year){
        if(year==0){
            return LocalDate.now().lengthOfYear();
        }else{
            return LocalDate.of(year,1,1).lengthOfYear();
        }
    }

}
