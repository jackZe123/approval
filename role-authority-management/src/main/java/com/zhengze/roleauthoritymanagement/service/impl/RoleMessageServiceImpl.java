package com.zhengze.roleauthoritymanagement.service.impl;

import com.google.common.base.Joiner;
import com.zhengze.roleauthoritymanagement.dao.FunctionMessageDao;
import com.zhengze.roleauthoritymanagement.dao.RoleMessageDao;
import com.zhengze.roleauthoritymanagement.facade.request.AddRoleMessageRequest;
import com.zhengze.roleauthoritymanagement.facade.request.UpdateRoleMessageRequest;
import com.zhengze.roleauthoritymanagement.facade.response.FunctionMessage;
import com.zhengze.roleauthoritymanagement.facade.response.RoleLabelResponse;
import com.zhengze.roleauthoritymanagement.facade.response.RoleMessageResponse;
import com.zhengze.roleauthoritymanagement.mapper.AuthorityMessageMapper;
import com.zhengze.roleauthoritymanagement.mapper.FunctionMessageMapper;
import com.zhengze.roleauthoritymanagement.mapper.RoleMessageMapper;
import com.zhengze.roleauthoritymanagement.service.FunctionMessageService;
import com.zhengze.roleauthoritymanagement.service.RoleMessageService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
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

    @Autowired
    private FunctionMessageMapper functionMessageMapper;

    @Autowired
    private AuthorityMessageMapper authorityMessageMapper;

    @Override
    public List<RoleMessageResponse> getRoleMessages() {
        List<RoleMessageResponse> responses = new ArrayList<>();
        List<RoleMessageDao> roleMessageDaos = roleMessageMapper.getAllRoles();
        for (RoleMessageDao dao:roleMessageDaos){
            RoleMessageResponse roleMessageResponse = new RoleMessageResponse();
            BeanUtils.copyProperties(dao,roleMessageResponse);
            List<String> functionMessages = authorityMessageMapper.getAuthoritysByRoleId(dao.getRoleId());
            roleMessageResponse.setFunctionNames(Joiner.on(",").join(functionMessages));
            responses.add(roleMessageResponse);
        }
        return responses;
    }

    @Override
    public Map<String, RoleMessageDao> getAllRoles() {
        List<RoleMessageDao> roleMessageDaos = roleMessageMapper.getAllRoles();
        Map<String, RoleMessageDao> roleMap = roleMessageDaos.stream().collect(
                Collectors.toMap(r -> r.getRoleId(), r -> r, (key1, key2) -> key2));
        return roleMap;
    }

    @Override
    public List<RoleLabelResponse> getAllRoleLabels() {
        List<RoleLabelResponse> roleLabelResponses = new ArrayList<>();
        List<RoleMessageDao> roleMessageDaos = roleMessageMapper.getAllRoles();
        List<String> roleNames = roleMessageDaos.stream().distinct().map(r->r.getRoleName()).collect(Collectors.toList());
        for (String roleName:roleNames){
            RoleLabelResponse roleLabelResponse = new RoleLabelResponse();
            roleLabelResponse.setLabel(roleName);
            roleLabelResponse.setValue(roleName);
            roleLabelResponses.add(roleLabelResponse);
        }
        return roleLabelResponses;
    }

    @Override
    public String getRoleIdByRoleName(String roleName) {
        return roleMessageMapper.getRoleIdByRoleName(roleName);
    }

    @Override
    public Boolean deleteRoleMessage(String roleId) {
        Integer result = roleMessageMapper.deleteRoleMessage(roleId);
        List<String> messages = authorityMessageMapper.getAuthoritysByRoleId(roleId);
        if (CollectionUtils.isEmpty(messages)&&result>=1){
            return true;
        }
        Integer deleteResult = authorityMessageMapper.deleteAuthorityByRoleId(roleId);
        if (result>=1&&deleteResult>=1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean addRoleMessage(AddRoleMessageRequest request) {
        String roleId = UUID.randomUUID().toString().replace("-","").toLowerCase();
        Integer result = roleMessageMapper.addRoleMessage(roleId,request.getRoleName(),request.getDescrible(),new Date(),new Date());
        if (CollectionUtils.isEmpty(request.getFunctionIds())&&result>=1){
            return true;
        }
        Integer insert=null;
        for (String functionId:request.getFunctionIds()){
            String authorityId = UUID.randomUUID().toString().replace("-","").toLowerCase();
             insert = authorityMessageMapper.insertAuthority(authorityId,roleId,functionId,new Date(),new Date());
        }
        if (insert>=1&&result>=1){
            return  true;
        }
        return false;
    }

    @Override
    public Boolean updateRoleMessage(UpdateRoleMessageRequest request) {
        Integer result = roleMessageMapper.updateRoleMessage(request.getRoleId(),request.getRoleName(),request.getDescrible(),new Date());
        if (result>=1&&CollectionUtils.isEmpty(request.getFunctionIds())){
            return true;
        }
        Integer dl = authorityMessageMapper.deleteAuthorityByRoleId(request.getRoleId());
        Integer in =null;
        for (String functionId:request.getFunctionIds()){
            String authorityId = UUID.randomUUID().toString().replace("-","").toLowerCase();
            in = authorityMessageMapper.insertAuthority(authorityId,request.getRoleId(),functionId,new Date(),new Date());
        }
        if (result>=1&&in>=1)
        {
            return true;
        }
        return false;
    }
}
