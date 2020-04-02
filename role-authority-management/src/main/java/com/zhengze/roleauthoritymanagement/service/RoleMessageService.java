package com.zhengze.roleauthoritymanagement.service;

import com.zhengze.roleauthoritymanagement.dao.RoleMessageDao;
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
}
