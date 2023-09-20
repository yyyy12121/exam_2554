package com.example.boot.vo;

import lombok.Data;
import org.apache.ibatis.annotations.Delete;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yhj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/19 21:29
 */
@Data
public class city {

    private Integer year;

    List <riskTopVo> riskTop3 = new ArrayList();
}
