package com.zhengze.usermanagement.dao;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/9 21:46
 */
@Data
public class UserRoleMessageDao {
    private Integer id;
    private String userId;
    private String roleId;
    private Date createTime;
    private Date updateTime;
}
