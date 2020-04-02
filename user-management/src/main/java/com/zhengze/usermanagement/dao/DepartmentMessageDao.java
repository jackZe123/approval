package com.zhengze.usermanagement.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/9 22:26
 */
@Data
public class DepartmentMessageDao {
    private Integer id;
    private String departmentId;
    private String departmentName;
    private String describle;
    private String managerId;
    private String managerName;
    private String groupLeaderId;
    private String groupLeaderName;
    private Date createTime;
    private Date updateTime;
}
