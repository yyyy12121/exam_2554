package com.example.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yhj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/19 20:06
 */
@TableName("crimes")
@Data
public class crimes implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "incidentId", type = IdType.AUTO)
    private Integer incidentId;

    private Integer offenceCode;

    private String dispatchTime;

    private Integer victims;

    private String city;

    private String startDateTime;
}
