package com.zhengze.usermanagement.controller;

import com.zhengze.usermanagement.common.BaseEntityResponse;
import com.zhengze.usermanagement.common.ResponseCode;
import com.zhengze.usermanagement.common.enums.BizErrorEnum;
import com.zhengze.usermanagement.dao.UserMessageDao;
import com.zhengze.usermanagement.facade.request.*;
import com.zhengze.usermanagement.facade.response.GetDepartmentMessageResponse;
import com.zhengze.usermanagement.facade.response.GetUserMessageResponse;
import com.zhengze.usermanagement.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/4 13:49
 */
@RestController
@RequestMapping("/user")
public class UserMessageController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户信息，包括角色
     * @return
     */
    @PostMapping("/getUserMessage")
    public BaseEntityResponse<List<GetUserMessageResponse>> getUserMessage(){
        BaseEntityResponse  <List<GetUserMessageResponse>> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            List<GetUserMessageResponse> userMessageDao = userService.getUserMessage();
            resp.setData(userMessageDao);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    /**
     * 新增用户信息
     * @param request
     * @return
     */
    @PostMapping("/insertUserMessage")
    public BaseEntityResponse<Boolean> insertUserMessage(@RequestBody InsertUserRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            Boolean flag = userService.insertMessage(request);
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<Boolean>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }

    /**
     * 删除用户
     * @param request
     * @return
     */
    @PostMapping("/deleteUser")
    public BaseEntityResponse<Boolean> deleteUser(@RequestBody DeleteUserRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            Boolean flag = userService.deleteUser(request);
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    /**
     * 为用户绑定角色
     * @param request
     * @return
     */
    @PostMapping("/BDRoleByUserId")
    public BaseEntityResponse<Boolean> BDRoleByUserId(@RequestBody BDRoleRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            Boolean flag = userService.BDRoleByUserId(request);
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    /**
     * 新增部门信息
     * @param request
     * @return
     */
    @PostMapping("AddDepartmentMessage")
    public BaseEntityResponse<Boolean> AddDepartmentMessage(@RequestBody AddDepartmentRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            Boolean flag = userService.addDepartmentMessage(request);
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<Boolean>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }
    /**
     *     为部门绑定用户
     */
    @PostMapping("BDuserForDepartment")
    public BaseEntityResponse<Boolean> BDuserForDepartment(@RequestBody BDuserForDepartmentRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null|| StringUtils.isEmpty(request.getDepartmentId())||request.getUserIds()!=null){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            Boolean flag = userService.BDuserForDepartment(request);
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<Boolean>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }

    /**
     * 获取所有部门信息和部门人员信息
     */
    @PostMapping("getDepartmentMessage")
    public BaseEntityResponse<List<GetDepartmentMessageResponse>> getDepartmentMessage(){
        BaseEntityResponse<List<GetDepartmentMessageResponse>> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            List<GetDepartmentMessageResponse> responses = new ArrayList<GetDepartmentMessageResponse>();
            responses = userService.getDepartmentMessage();
            resp.setData(responses);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<List<GetDepartmentMessageResponse>>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }

    /**
     * 获取单个部门信息和部门人员信息
     */
    @PostMapping("getDepartmentMessage")
    public BaseEntityResponse<GetDepartmentMessageResponse> getDepartmentMessageByDepengId(@RequestBody GetDepartmentRequest request){
        BaseEntityResponse<GetDepartmentMessageResponse> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null|| StringUtils.isEmpty(request.getDepartmentId())){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            GetDepartmentMessageResponse response = new GetDepartmentMessageResponse();
            response = userService.getDeaprtmentMessageByDepartmentId(request.getDepartmentId());
            resp.setData(response);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<GetDepartmentMessageResponse>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }

}
