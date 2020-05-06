package com.zhengze.usermanagement.service;

import com.zhengze.usermanagement.dao.UserMessageDao;
import com.zhengze.usermanagement.facade.request.*;
import com.zhengze.usermanagement.facade.response.*;
import com.zhengze.usermanagement.facade.response.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/4 13:51
 */

public interface UserService {
    GetUserMessageListResponse getUserMessage();
    GetUserMessageResponse getUserMessageResponseByTelephone(String telephone);
    List<UserDto> getUserLabels();
    Boolean insertMessage(InsertUserRequest request);
    Boolean updateMessage(UpdateUserRequest request);
    Boolean updateUserMessage(UpdateUserRequest request);
    Boolean deleteUser(DeleteUserRequest request);
    Boolean BDRoleByUserId(BDRoleRequest request);
    Boolean addDepartmentMessage(AddDepartmentRequest request);
    Boolean deleteDepartmentMessage(DeleteDepartmentRequest request);
    Boolean updateDepartmentMessage(UpdateDepartmentRequest request);
    Boolean BDuserForDepartment(BDuserForDepartmentRequest request);
    List<GetDepartmentMessageResponse> getDepartmentMessage();
    GetDepartmentMessageResponse getDeaprtmentMessageByDepartmentId(String deaprtmentId);
    Boolean userLogin(LoginRequest request);
    String insertApprovalRecord(InsertApprovalRequest request);
    Boolean submitApprovalRecord(SubmitApprovalRequest request);
    NotDealApprovalRecordListResponse getNotDealApprovals(String userId);
    GetApprovalRecordListResponse getApprovalResponses(String approvalid);
    GetApprovalResponse getApprovalByUserId(String userId);
    GetApprovalRecordListResponse getApplyApprovalMessage();
    GetApprovalResponse getCompletedApprovalByUseriD(String userId);
}
