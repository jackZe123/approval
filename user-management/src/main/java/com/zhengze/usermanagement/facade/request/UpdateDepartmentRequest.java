package com.zhengze.usermanagement.facade.request;

import com.zhengze.usermanagement.facade.response.dto.UserDto;
import lombok.Data;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/17 9:12
 */
@Data
public class UpdateDepartmentRequest {
    private String departmentId;
    private String departmentName;
    private String describe;
    private String managerId;
    private String managerName;
    private String groupLeaderId;
    private String groupLeaderName;
    private List<String> users;
}
