package com.zhengze.usermanagement.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/4 13:32
 */
@Data
public class UserMessageDao implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String userId;
    private String userName;
    private String password;
    private String telephone;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
