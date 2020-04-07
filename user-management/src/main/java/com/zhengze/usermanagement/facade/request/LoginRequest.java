package com.zhengze.usermanagement.facade.request;

import lombok.Data;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/2 15:18
 */

@Data
public class LoginRequest {
    private String telephone;
    private String password;
    private String role;
}
