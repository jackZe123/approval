package com.zhengze.usermanagement.facade.request;

import lombok.Data;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/10 16:23
 */
@Data
public class BDuserForDepartmentRequest {
    private String departmentId;
    private List<String> userIds;
}
