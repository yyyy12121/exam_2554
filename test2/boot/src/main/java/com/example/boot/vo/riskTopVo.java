package com.example.boot.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yhj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/19 23:15
 */
@Data
public class riskTopVo {



    private String city;

    //风险指数
    private BigDecimal riskIndex;

    //排名
    private Integer rank;

}
