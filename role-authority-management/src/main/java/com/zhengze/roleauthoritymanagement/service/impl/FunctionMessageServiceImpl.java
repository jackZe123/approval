package com.zhengze.roleauthoritymanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.jdbc.FabricMySQLConnection;
import com.zhengze.roleauthoritymanagement.facade.request.FunctionRequest;
import com.zhengze.roleauthoritymanagement.facade.request.UpdateFunctionRequest;
import com.zhengze.roleauthoritymanagement.facade.response.FunctionMessage;
import com.zhengze.roleauthoritymanagement.facade.response.FunctionMessageResponse;
import com.zhengze.roleauthoritymanagement.facade.response.GetFunctionMessageResponse;
import com.zhengze.roleauthoritymanagement.mapper.FunctionMessageMapper;
import com.zhengze.roleauthoritymanagement.mapper.RoleMessageMapper;
import com.zhengze.roleauthoritymanagement.service.FunctionMessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.FunctionMapper;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 17:43
 */
@Service
public class FunctionMessageServiceImpl implements FunctionMessageService {

    @Autowired
    private FunctionMessageMapper functionMessageMapper;

    @Autowired
    private RoleMessageMapper roleMessageMapper;

    @Override
    public FunctionMessageResponse getPageFunctionMessages(FunctionRequest request) {
        FunctionMessageResponse messageResponse = new FunctionMessageResponse();
        PageHelper.startPage(request.getPageIndex(),request.getPageSize());
        List<FunctionMessage> List=functionMessageMapper.getFunctionMessages();
        PageInfo pageInfo = new PageInfo(List);
        messageResponse.setFunctionMessages(pageInfo.getList());
        messageResponse.setTotal(pageInfo.getTotal());
        return messageResponse;
    }

    @Override
    public List<FunctionMessage> getFunctionMessages() {
        return functionMessageMapper.getFunctionMessages();
    }

    @Override
    public Boolean updateFunctionMessage(UpdateFunctionRequest request) {
        if (StringUtils.isEmpty(request.getFunctionId())||request.getFunctionId()==null){
            String functionId= UUID.randomUUID().toString().replace("-","").toLowerCase();
            Integer result =  functionMessageMapper.insertFunctionMessage(functionId,request.getFunctionName(),request.getDescribe(),new Date(),new Date());
            if (result>=1){
                return true;
            }
            return false;
        }else {
            Integer result = functionMessageMapper.updateFunctionMessage(request.getFunctionId(),request.getFunctionName(),request.getDescribe(),new Date());
            if (result>=1){
                return true;
            }
            return false;
        }
    }

    @Override
    public Boolean deleteFunctionMessage(String functionId) {
        Integer result = functionMessageMapper.deleteFunctionMessage(functionId);
        if (result>=1){
            return true;
        }
        return false;
    }

    @Override
    public GetFunctionMessageResponse getFunctionMessage(String roleName) {
        GetFunctionMessageResponse getFunctionMessageResponse = new GetFunctionMessageResponse();
        String roleId = roleMessageMapper.getRoleIdByRoleName(roleName);
        List<String> functionMessages = functionMessageMapper.getFunctionMessagesByRoleId(roleId);
        if (functionMessages.contains("功能管理")){
            getFunctionMessageResponse.setDom1(true);
        }
        if (functionMessages.contains("角色管理")){
            getFunctionMessageResponse.setDom2(true);
        }
        if (functionMessages.contains("用户管理")){
            getFunctionMessageResponse.setDom3(true);
        }
        if (functionMessages.contains("部门管理")){
            getFunctionMessageResponse.setDom4(true);
        }
        if (functionMessages.contains("个人信息")){
            getFunctionMessageResponse.setDom5(true);
        }
        if (functionMessages.contains("发起出国申请")){
            getFunctionMessageResponse.setDom6(true);
        }
        if (functionMessages.contains("查看审批进度")){
            getFunctionMessageResponse.setDom7(true);
        }
        if (functionMessages.contains("查看待审批进度")){
            getFunctionMessageResponse.setDom8(true);
        }
        if (functionMessages.contains("查看已完成审批进度")){
            getFunctionMessageResponse.setDom9(true);
        }
        if (functionMessages.contains("工作流管理")){
            getFunctionMessageResponse.setDom10(true);
        }
        return getFunctionMessageResponse;
    }
}
