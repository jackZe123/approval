package com.zhengze.usermanagement.service;

import com.zhengze.usermanagement.dao.UserMessageDao;
import com.zhengze.usermanagement.facade.request.*;
import com.zhengze.usermanagement.facade.response.GetDepartmentMessageResponse;
import com.zhengze.usermanagement.facade.response.GetUserMessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/4 13:51
 */

public interface UserService {
    public List<GetUserMessageResponse> getUserMessage();
    public Boolean insertMessage(InsertUserRequest request);
    public Boolean deleteUser(DeleteUserRequest request);
    public Boolean BDRoleByUserId(BDRoleRequest request);
    public Boolean addDepartmentMessage(AddDepartmentRequest request);
    public Boolean BDuserForDepartment(BDuserForDepartmentRequest request);
    public List<GetDepartmentMessageResponse> getDepartmentMessage();
    public GetDepartmentMessageResponse getDeaprtmentMessageByDepartmentId(String deaprtmentId);
}
