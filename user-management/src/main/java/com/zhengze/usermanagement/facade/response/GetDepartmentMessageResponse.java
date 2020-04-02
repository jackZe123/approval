package com.zhengze.usermanagement.facade.response;

import com.zhengze.usermanagement.facade.response.dto.UserDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/11 9:38
 */
@Data
public class GetDepartmentMessageResponse {
    private String departmentId;
    private String departmentName;
    private String describle;
    private String managerId;
    private String managerName;
    private String groupLeaderId;
    private String groupLeaderName;
    private String createTime;
    private String updateTime;
    private List<UserDto> users;
}
