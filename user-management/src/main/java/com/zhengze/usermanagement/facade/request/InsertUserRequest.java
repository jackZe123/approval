package com.zhengze.usermanagement.facade.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 11:29
 */
@Data
public class InsertUserRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private String password;
    private String telephone;
    private String roleName;
}
