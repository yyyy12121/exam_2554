package com.example.boot.entity;

/**
 * @author yhj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/20 12:50
 */

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("offences")
@Data
public class offences implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer offenceCode;

    private String crimeName1;

    private String crimeName2;

    private String crimeName3;
}
