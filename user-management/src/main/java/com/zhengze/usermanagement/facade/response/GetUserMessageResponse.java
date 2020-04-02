package com.zhengze.usermanagement.facade.response;

import lombok.Data;

import java.util.Date;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/8 20:19
 */
@Data
public class GetUserMessageResponse {
    private String userId;
    private String userName;
    private String password;
    private String telephone;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private String roleId;
    private String roleName;
}
