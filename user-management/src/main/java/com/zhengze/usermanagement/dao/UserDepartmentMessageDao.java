package com.zhengze.usermanagement.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/9 22:30
 */
@Data
public class UserDepartmentMessageDao {
    private Integer id;
    private String departmentId;
    private String userId;
    private Date createTime;
    private Date updateTime;
}
