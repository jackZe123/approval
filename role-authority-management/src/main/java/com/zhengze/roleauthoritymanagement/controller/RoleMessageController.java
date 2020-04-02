package com.zhengze.roleauthoritymanagement.controller;

import com.zhengze.roleauthoritymanagement.common.BaseEntityResponse;
import com.zhengze.roleauthoritymanagement.dao.RoleMessageDao;
import com.zhengze.roleauthoritymanagement.facade.response.RoleMessageResponse;
import com.zhengze.roleauthoritymanagement.service.RoleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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


}
