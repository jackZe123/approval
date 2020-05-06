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
    private String departmentName;//部门名称
    private String describe;//部门描述
    private String managerId;
    private String managerName;//经理名称
    private String groupLeaderId;
    private String groupLeaderName;//组长名称
    private String createTime;//创建时间
    private String updateTime;//更新时间
    private String users;//组员
}
