package com.zhengze.roleauthoritymanagement.facade.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private String functionId;
    private String functionName;
    private String functionNames;
}
