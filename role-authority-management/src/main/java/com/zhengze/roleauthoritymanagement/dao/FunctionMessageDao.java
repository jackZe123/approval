package com.zhengze.roleauthoritymanagement.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 15:43
 */
@Data
public class FunctionMessageDao implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String functionId;
    private String functionName;
    private String describle;
    private Date createTime;
    private Date updateTime;
}
