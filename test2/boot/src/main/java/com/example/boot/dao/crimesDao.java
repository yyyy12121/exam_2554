package com.example.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.entity.crimes;
import com.example.boot.vo.CrimeVo;
import com.example.boot.vo.city;
import com.example.boot.vo.riskTopVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface crimesDao extends BaseMapper<crimes> {
    List<riskTopVo> selectBy(@Param("year") Integer year,@Param("days")Integer days);

    List<city> selectYear();
}
