package com.zhengze.roleauthoritymanagement.controller;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zhengze.roleauthoritymanagement.common.BaseEntityResponse;
import com.zhengze.roleauthoritymanagement.dao.RoleMessageDao;
import com.zhengze.roleauthoritymanagement.facade.request.AddRoleMessageRequest;
import com.zhengze.roleauthoritymanagement.facade.request.DeleteRoleMessageRequest;
import com.zhengze.roleauthoritymanagement.facade.request.GetRoleMessageByRoleIdRequest;
import com.zhengze.roleauthoritymanagement.facade.request.UpdateRoleMessageRequest;
import com.zhengze.roleauthoritymanagement.facade.response.FunctionMessage;
import com.zhengze.roleauthoritymanagement.facade.response.RoleLabelResponse;
import com.zhengze.roleauthoritymanagement.facade.response.RoleMessageIdResponse;
import com.zhengze.roleauthoritymanagement.facade.response.RoleMessageResponse;
import com.zhengze.roleauthoritymanagement.facade.response.dto.FunctionDto;
import com.zhengze.roleauthoritymanagement.mapper.FunctionMessageMapper;
import com.zhengze.roleauthoritymanagement.service.RoleMessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.el.FunctionMapper;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 17:45
 */
@RestController
@RequestMapping("/role")
public class RoleMessageController {

    @Autowired
    private RoleMessageService roleMessageService;

    @Autowired
    private FunctionMessageMapper functionMapper;

    @PostMapping("/getRoleMessage")
    public BaseEntityResponse<List<RoleMessageResponse>> getRoleMessage(){
        BaseEntityResponse  <List<RoleMessageResponse>> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            List<RoleMessageResponse> roleMessageResponses = roleMessageService.getRoleMessages();
            resp.setData(roleMessageResponses);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @PostMapping("/getRoleMessageByRoleId")
    public BaseEntityResponse<RoleMessageIdResponse> getRoleMessageByRoleId(@RequestBody GetRoleMessageByRoleIdRequest request){
        BaseEntityResponse  <RoleMessageIdResponse> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            List<RoleMessageResponse> roleMessageResponses = roleMessageService.getRoleMessages();
            List<FunctionMessage> functionMessages = functionMapper.getFunctionMessages();
            Map<String,String> functionMap=functionMessages.stream().collect(Collectors.toMap(FunctionMessage::getFunctionName, FunctionMessage::getFunctionId, (key1, key2) -> key2));
            RoleMessageIdResponse response = new RoleMessageIdResponse();
            for (RoleMessageResponse roleMessageResponse:roleMessageResponses){
                if (roleMessageResponse.getRoleId().equals(request.getRoleId())){
                    BeanUtils.copyProperties(roleMessageResponse,response);
                    List<FunctionDto> functionDtos = new ArrayList<>();
                    if (StringUtils.isNotEmpty(roleMessageResponse.getFunctionNames())){
                        for (String functionName: Arrays.asList(roleMessageResponse.getFunctionNames().split(","))){
                            FunctionDto functionDto = new FunctionDto();
                            functionDto.setFunctionId(functionMap.get(functionName));
                            functionDto.setFunctionName(functionName);
                            functionDtos.add(functionDto);
                        }
                    }
                    response.setFunctionDtoList(functionDtos);
                }
            }
            resp.setData(response);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @PostMapping("/getAllRoleMessage")
    public BaseEntityResponse<Map<String,RoleMessageDao>> getAllRoleMessage(){
        BaseEntityResponse  <Map<String,RoleMessageDao>> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            Map<String,RoleMessageDao> roleMessageResponses = roleMessageService.getAllRoles();
            resp.setData(roleMessageResponses);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @PostMapping("/getAllRoleLabels")
    public BaseEntityResponse<List<RoleLabelResponse>> getAllRoleLabels(){
        BaseEntityResponse  <List<RoleLabelResponse>> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            List<RoleLabelResponse> roleMessageResponses = roleMessageService.getAllRoleLabels();
            resp.setData(roleMessageResponses);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @PostMapping("/getRoleIdByRoleName")
    public BaseEntityResponse<String> getRoleIdByRoleName(@RequestBody String roleName){
        BaseEntityResponse  <String> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            String roleId = roleMessageService.getRoleIdByRoleName(roleName);
            resp.setData(roleId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @PostMapping("/deleteRoleMessage")
    public BaseEntityResponse<Boolean> deleteRoleMessage(@RequestBody DeleteRoleMessageRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            Boolean flag = roleMessageService.deleteRoleMessage(request.getRoleId());
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @PostMapping("/addRoleMessage")
    public BaseEntityResponse<Boolean> addRoleMessage(@RequestBody AddRoleMessageRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            Boolean flag = roleMessageService.addRoleMessage(request);
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @PostMapping("/updateRoleMessage")
    public BaseEntityResponse<Boolean> updateRoleMessage(@RequestBody UpdateRoleMessageRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            Boolean flag = roleMessageService.updateRoleMessage(request);
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }
}
