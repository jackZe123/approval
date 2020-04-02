package com.zhengze.roleauthoritymanagement.service.impl;

import com.zhengze.roleauthoritymanagement.dao.FunctionMessageDao;
import com.zhengze.roleauthoritymanagement.dao.RoleMessageDao;
import com.zhengze.roleauthoritymanagement.facade.response.RoleMessageResponse;
import com.zhengze.roleauthoritymanagement.mapper.RoleMessageMapper;
import com.zhengze.roleauthoritymanagement.service.FunctionMessageService;
import com.zhengze.roleauthoritymanagement.service.RoleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 17:43
 */
@Service
public class RoleMessageServiceImpl implements RoleMessageService {

    @Autowired
    private RoleMessageMapper roleMessageMapper;

    @Override
    public List<RoleMessageResponse> getRoleMessages() {
        List<RoleMessageResponse> roleMessageDaos = roleMessageMapper.getRoleMessages();
        return roleMessageDaos;
    }

    @Override
    public Map<String, RoleMessageDao> getAllRoles() {
        List<RoleMessageDao> roleMessageDaos = roleMessageMapper.getAllRoles();
        Map<String, RoleMessageDao> roleMap = roleMessageDaos.stream().collect(
                Collectors.toMap(r -> r.getRoleId(), r -> r, (key1, key2) -> key2));
        return roleMap;
    }
}
