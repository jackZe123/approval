package com.zhengze.usermanagement.service;

import com.zhengze.usermanagement.dao.UserMessageDao;
import com.zhengze.usermanagement.facade.request.*;
import com.zhengze.usermanagement.facade.response.GetDepartmentMessageResponse;
import com.zhengze.usermanagement.facade.response.GetUserMessageListResponse;
import com.zhengze.usermanagement.facade.response.GetUserMessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/4 13:51
 */

public interface UserService {
    GetUserMessageListResponse getUserMessage(GetUserMessageRequest request);
    Boolean insertMessage(InsertUserRequest request);
    Boolean deleteUser(DeleteUserRequest request);
    Boolean BDRoleByUserId(BDRoleRequest request);
    Boolean addDepartmentMessage(AddDepartmentRequest request);
    Boolean BDuserForDepartment(BDuserForDepartmentRequest request);
    List<GetDepartmentMessageResponse> getDepartmentMessage();
    GetDepartmentMessageResponse getDeaprtmentMessageByDepartmentId(String deaprtmentId);
    Boolean userLogin(LoginRequest request);
}
