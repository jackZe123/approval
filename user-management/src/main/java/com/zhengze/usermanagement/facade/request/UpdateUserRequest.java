package com.zhengze.usermanagement.facade.request;

import lombok.Data;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/16 13:03
 */
@Data
public class UpdateUserRequest {
    private String userName;
    private String telephone;
    private String roleName;
    private String statu;
    private String userId;
    private String password;
}
