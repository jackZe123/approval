package com.zhengze.roleauthoritymanagement.facade.response;

import lombok.Data;

import java.util.Date;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 19:23
 */
@Data
public class RoleMessageResponse {
    private String roleId;
    private String roleName;
    private String describle;
    private Date createTime;
    private Date updateTime;
    private String functionId;
    private String functionName;
}
