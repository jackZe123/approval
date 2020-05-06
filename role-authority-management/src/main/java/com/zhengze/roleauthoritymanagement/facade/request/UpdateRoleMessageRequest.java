package com.zhengze.roleauthoritymanagement.facade.request;

import lombok.Data;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/17 14:39
 */
@Data
public class UpdateRoleMessageRequest {
    private String roleId;
    private String roleName;
    private String describle;
    private List<String> functionIds;
}
