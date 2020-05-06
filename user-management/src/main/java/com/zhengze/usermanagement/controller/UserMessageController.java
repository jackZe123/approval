package com.zhengze.usermanagement.controller;

import com.zhengze.usermanagement.common.BaseEntityResponse;
import com.zhengze.usermanagement.common.Const;
import com.zhengze.usermanagement.common.ResponseCode;
import com.zhengze.usermanagement.common.enums.BizErrorEnum;
import com.zhengze.usermanagement.facade.request.*;
import com.zhengze.usermanagement.facade.response.*;
import com.zhengze.usermanagement.facade.response.dto.UserDto;
import com.zhengze.usermanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/4 13:49
 */
@Api(tags = "UserMessageController",description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserMessageController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户信息，包括角色
     * @return
     */
    @ApiOperation("获取所有用户信息，包含角色")
    @PostMapping("/getUserMessage")
    public BaseEntityResponse<GetUserMessageListResponse> getUserMessage(){
        BaseEntityResponse  <GetUserMessageListResponse> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            GetUserMessageListResponse userMessageDao = userService.getUserMessage();
            resp.setData(userMessageDao);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    /**
     * 根据手机号获取单个用户信息
     * @return
     */
    @ApiOperation("根据手机号获取单个用户信息")
    @PostMapping("/getUserMessageByTelephoneId")
    public BaseEntityResponse<GetUserMessageResponse> getUserMessageByTelephoneId(@RequestBody GetUserMessageByTelephoneId request, HttpServletRequest httpServletRequest){
        BaseEntityResponse  <GetUserMessageResponse> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            String telephone = request.getTelephone();

            GetUserMessageResponse userMessageDao = userService.getUserMessageResponseByTelephone(telephone);
            resp.setData(userMessageDao);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @ApiOperation("获取用户label")
    @PostMapping("/getUserLabels")
    public BaseEntityResponse<List<UserDto>> getUserLabels(){
        BaseEntityResponse  <List<UserDto>>  resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            List<UserDto>  userMessageDao = userService.getUserLabels();
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
    @ApiOperation("新增用户信息")
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
     * 修改用户信息
     * @param request
     * @return
     */
    @ApiOperation("修改用户信息")
    @PostMapping("/updateUserMessage")
    public BaseEntityResponse<Boolean> updateUserMessage(@RequestBody UpdateUserRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            Boolean flag = userService.updateMessage(request);
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<Boolean>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }

    /**
     * 用户修改自身信息
     * @param request
     * @return
     */
    @ApiOperation("用户修改自身信息")
    @PostMapping("/updateUserMessageByUserId")
    public BaseEntityResponse<Boolean> updateUserMessageByUserId(@RequestBody UpdateUserRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            Boolean flag = userService.updateUserMessage(request);
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
    @ApiOperation("删除用户")
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
     * 登录
     */
    @ApiOperation("登录")
    @PostMapping("/userLogin")
    public BaseEntityResponse<Boolean> userLogin(@RequestBody LoginRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            Boolean flag = userService.userLogin(request);
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<Boolean>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }


    /**
     * 为用户绑定角色
     * @param request
     * @return
     */
    @ApiOperation("为用户绑定角色")
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
    @ApiOperation("新增部门信息")
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
     * 更新部门信息
     * @param request
     * @return
     */
    @ApiOperation("更新部门信息")
    @PostMapping("updateDepartmentMessage")
    public BaseEntityResponse<Boolean> updateDepartmentMessage(@RequestBody UpdateDepartmentRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            Boolean flag = userService.updateDepartmentMessage(request);
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<Boolean>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }
    /**
     * 删除部门信息
     * @param request
     * @return
     */
    @ApiOperation("删除部门信息")
    @PostMapping("deleteDepartmentMessage")
    public BaseEntityResponse<Boolean> deleteDepartmentMessage(@RequestBody DeleteDepartmentRequest request){
        BaseEntityResponse  <Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            Boolean flag = userService.deleteDepartmentMessage(request);
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
    @ApiOperation("为部门绑定用户")
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
    @ApiOperation("获取所有部门信息和部门人员信息")
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
    @ApiOperation("获取单个部门信息和部门人员信息")
    @PostMapping("getDepartmentMessageByDepengId")
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

    /**
     * 新建审批
     */
    @ApiOperation("新建审批记录")
    @PostMapping("insertApprovalRecord")
    public BaseEntityResponse<String> insertApprovalRecord(@RequestBody InsertApprovalRequest request){
        BaseEntityResponse<String> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null|| StringUtils.isEmpty(request.getUserId())){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            String uid =userService.insertApprovalRecord(request);
            resp.setData(uid);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<String>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }

    /**提交审批
     *
     * @param request
     * @return
     */
    @ApiOperation("提交审批记录")
    @PostMapping("submitApprovalRecord")
    public BaseEntityResponse<Boolean> submitApprovalRecord(@RequestBody SubmitApprovalRequest request){
        BaseEntityResponse<Boolean> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null|| StringUtils.isEmpty(request.getUserId())){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
        Boolean flag = userService.submitApprovalRecord(request);
        resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<Boolean>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }
    /**
     * 获取待办审批记录
     *
     */
    @ApiOperation("获取待办审批记录")
    @PostMapping("getNotDealApprovalRecord")
    public BaseEntityResponse<NotDealApprovalRecordListResponse> getNotDealApprovalRecord(@RequestBody NotDealApprovalRecordRequest request){
        BaseEntityResponse<NotDealApprovalRecordListResponse> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null|| StringUtils.isEmpty(request.getUserId())){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            NotDealApprovalRecordListResponse response = userService.getNotDealApprovals(request.getUserId());
            resp.setData(response);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<NotDealApprovalRecordListResponse>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }

    /**
     * 获取单个审批记录
     *
     */
    @ApiOperation("获取单个审批记录整个流程")
    @PostMapping("getApprovalRecord")
    public BaseEntityResponse<GetApprovalRecordListResponse> getApprovalRecord(@RequestBody GetApprovalRecordRequest request){
        BaseEntityResponse<GetApprovalRecordListResponse> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null|| StringUtils.isEmpty(request.getApprovalId())){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
              GetApprovalRecordListResponse response = userService.getApprovalResponses(request.getApprovalId());
              resp.setData(response);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<GetApprovalRecordListResponse>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }

    /**
     * 根据userId多个审批记录Id
     *
     */
    @ApiOperation("根据userId多个审批记录Id")
    @PostMapping("getApprovalRecordIdByUserId")
    public BaseEntityResponse<GetApprovalResponse> getApprovalRecordIdByUserId(@RequestBody GetApprovalRequest request){
        BaseEntityResponse<GetApprovalResponse> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null|| StringUtils.isEmpty(request.getUserId())){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            GetApprovalResponse response = userService.getApprovalByUserId(request.getUserId());
            resp.setData(response);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<GetApprovalResponse>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }

    /**
     * 获取已完成的审批记录Id
     *
     */
    @ApiOperation("获取已完成的审批记录Id")
    @PostMapping("getCompletedApprovalRecordByUserId")
    public BaseEntityResponse<GetApprovalResponse>getCompletedApprovalRecordByUserId(@RequestBody GetApprovalRequest request){
        BaseEntityResponse<GetApprovalResponse> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        if (request==null|| StringUtils.isEmpty(request.getUserId())){
            resp.setMessage(ResponseCode.PARAM_IS_ERROR.getDesc());
            return  resp;
        }
        try {
            GetApprovalResponse response = userService.getCompletedApprovalByUseriD(request.getUserId());
            resp.setData(response);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<GetApprovalResponse>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }

    /**
     * 获取所有申请人信息
     *
     */
    @ApiOperation("获取所有申请人信息")
    @PostMapping("getApplyApprovalMessage")
    public BaseEntityResponse<GetApprovalRecordListResponse> getApplyApprovalMessage(){
        BaseEntityResponse<GetApprovalRecordListResponse> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            GetApprovalRecordListResponse response = userService.getApplyApprovalMessage();
            resp.setData(response);
        }catch (Exception e){
            e.printStackTrace();
            resp =  new BaseEntityResponse<GetApprovalRecordListResponse>(BizErrorEnum.CALLSERVICCE_ERROR.getCode(), e.getMessage());
        }
        return resp;
    }
}
