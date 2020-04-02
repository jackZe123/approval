package com.zhengze.usermanagement.facade.request;

import lombok.Data;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/10 14:40
 */
@Data
public class AddDepartmentRequest {
    private String departmentName;
    private String describle;
    private String managerId;
    private String managerName;
    private String groupLeaderId;
    private String groupLeaderName;
}
