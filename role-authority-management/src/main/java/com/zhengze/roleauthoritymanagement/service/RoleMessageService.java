package com.zhengze.roleauthoritymanagement.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zhengze.roleauthoritymanagement.dao.RoleMessageDao;
import com.zhengze.roleauthoritymanagement.facade.request.AddRoleMessageRequest;
import com.zhengze.roleauthoritymanagement.facade.request.UpdateRoleMessageRequest;
import com.zhengze.roleauthoritymanagement.facade.response.RoleLabelResponse;
import com.zhengze.roleauthoritymanagement.facade.response.RoleMessageResponse;

import java.util.List;
import java.util.Map;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 17:40
 */
public interface RoleMessageService {
    public List<RoleMessageResponse> getRoleMessages();
    public Map<String,RoleMessageDao> getAllRoles();
    public List<RoleLabelResponse> getAllRoleLabels();
    public String getRoleIdByRoleName(String roleName);
    public Boolean deleteRoleMessage(String roleId);
    public Boolean addRoleMessage(AddRoleMessageRequest request);
    public Boolean updateRoleMessage(UpdateRoleMessageRequest request);
}
