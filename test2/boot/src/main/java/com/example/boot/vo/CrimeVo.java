package com.example.boot.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author yhj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/19 20:36
 */
@Data
public class CrimeVo {

    private Integer incidentId;

    private Integer offenceCode;

    private Date dispatchTime;

    private Integer victims;

    private String city;

    private Date startDateTime;

}
