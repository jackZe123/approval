package com.zhengze.usermanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhengze.roleauthoritymanagement.dao.RoleMessageDao;
import com.zhengze.usermanagement.common.BaseEntityResponse;
import com.zhengze.usermanagement.common.enums.BizErrorEnum;
import com.zhengze.usermanagement.common.exception.BizException;
import com.zhengze.usermanagement.dao.DepartmentMessageDao;
import com.zhengze.usermanagement.dao.UserMessageDao;
import com.zhengze.usermanagement.facade.request.*;
import com.zhengze.usermanagement.facade.response.GetDepartmentMessageResponse;
import com.zhengze.usermanagement.facade.response.GetUserMessageListResponse;
import com.zhengze.usermanagement.facade.response.GetUserMessageResponse;
import com.zhengze.usermanagement.facade.response.dto.UserDto;
import com.zhengze.usermanagement.mapper.DepartmentMessageMapper;
import com.zhengze.usermanagement.mapper.UserDepartmentMessageMapper;
import com.zhengze.usermanagement.mapper.UserMessageMapper;
import com.zhengze.usermanagement.remote.RoleService;
import com.zhengze.usermanagement.service.UserService;
import com.zhengze.usermanagement.util.MD5Util;
import com.zhengze.usermanagement.util.Regex;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/4 13:51
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMessageMapper userMessageMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DepartmentMessageMapper departmentMessageMapper;

    @Autowired
    private UserDepartmentMessageMapper userDepartmentMessageMapper;

    @Override
    public GetUserMessageListResponse getUserMessage(GetUserMessageRequest request) {
        GetUserMessageListResponse responses = new GetUserMessageListResponse();
        List<GetUserMessageResponse> userMessageDaos = userMessageMapper.getUserMessage();
        BaseEntityResponse<Map<String, RoleMessageDao>> roleMaps = roleService.getAllRoleMessage();
        for (GetUserMessageResponse response:userMessageDaos){
            response.setRoleName(roleMaps.getData().get(response.getRoleId()).getRoleName());
        }
        PageHelper.startPage(request.getPageIndex(),request.getPageSize());
        PageInfo pageInfo = new PageInfo(userMessageDaos);
        responses.setGetUserMessageResponses(pageInfo.getList());
        responses.setTotal(pageInfo.getTotal());
        return responses;
    }

    @Override
    public Boolean insertMessage(InsertUserRequest request) {
        String userId= UUID.randomUUID().toString().replace("-","").toLowerCase();
        Date createTime = new Date();
        Date updateTime = new Date();
        Boolean flag = Regex.checkPhone(request.getTelephone());
        if (!flag){
           throw new RuntimeException("手机号格式错误");
        }
        Boolean password = checkUserPassword(request.getPassword());
        if (!password){
            throw new RuntimeException("密码格式不对，密码长度必须在6-12位，包含数字和字母");
        }
        String md5_pwd = MD5Util.getMD5(request.getPassword());
        Integer result = userMessageMapper.insertUserMessage(userId,request.getUserName(),md5_pwd,request.getTelephone(),request.getStatus(),createTime,updateTime);
        if (result>=1){
            return  true;
        }
        return false;
    }

    @Override
    public Boolean deleteUser(DeleteUserRequest request) {
        Integer flag = userMessageMapper.deleteUser(request.getUserId());
        if (flag>=1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean BDRoleByUserId(BDRoleRequest request) {
        Integer flag = 0;
        Integer result = userMessageMapper.getRoleId(request.getUserId());
        if (result>=1){
            flag = userMessageMapper.updateUserRole(request.getUserId(),request.getRoleId());
        }else {
            flag = userMessageMapper.BDuserRole(request.getUserId(),request.getRoleId(),new Date(),new Date());
        }
        if (flag>=1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean addDepartmentMessage(AddDepartmentRequest request) {
        String departmentId= UUID.randomUUID().toString().replace("-","").toLowerCase();
        Date createTime = new Date();
        Date updateTime = new Date();
        Integer result = departmentMessageMapper.addDepartmentMessage(departmentId,request.getDepartmentName(),request.getDescrible(),request.getManagerId(),request.getManagerName(),request.getGroupLeaderId(),request.getGroupLeaderName(),createTime,updateTime);
        if (result>=1){
            return  true;
        }
        return false;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Boolean BDuserForDepartment(BDuserForDepartmentRequest request) {
        List<String> userIds = userDepartmentMessageMapper.getUserIdsByDepartmentId(request.getDepartmentId());
        List<String> BDuserIds = request.getUserIds();
        if (CollectionUtils.isEmpty(BDuserIds)){
            return true;
        }
        Boolean result = true;
        for (String userId:BDuserIds){
            if (userIds.contains(userId)){
                continue;
            }
            Integer flag = userDepartmentMessageMapper.BDUserIdToDepartment(userId,request.getDepartmentId(),new Date(),new Date());
            if (flag>=1){
                result =true;
            }else {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public List<GetDepartmentMessageResponse> getDepartmentMessage() {
        List<GetDepartmentMessageResponse> responses =new ArrayList<GetDepartmentMessageResponse>();
        List<DepartmentMessageDao> daos = departmentMessageMapper.getDepartmentMessage();
        for (DepartmentMessageDao dao:daos){
            GetDepartmentMessageResponse departmentMessageResponse = new GetDepartmentMessageResponse();
            BeanUtils.copyProperties(dao,departmentMessageResponse);
            if (dao.getCreateTime()!=null){
                departmentMessageResponse.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dao.getCreateTime()));
            }
            if (dao.getUpdateTime()!=null){
                departmentMessageResponse.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dao.getUpdateTime()));
            }
            List<UserDto> userDtos = userDepartmentMessageMapper.getDepartmentUsers(dao.getDepartmentId());
            departmentMessageResponse.setUsers(userDtos);
            responses.add(departmentMessageResponse);
        }
        return responses;
    }

    @Override
    public GetDepartmentMessageResponse getDeaprtmentMessageByDepartmentId(String departmentId) {
        GetDepartmentMessageResponse response = new GetDepartmentMessageResponse();
        DepartmentMessageDao dao =departmentMessageMapper.getDepartmentMessageByDepartmentId(departmentId);
        BeanUtils.copyProperties(dao,response);
        if (dao.getCreateTime()!=null){
            response.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dao.getCreateTime()));
        }
        if (dao.getUpdateTime()!=null){
            response.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dao.getUpdateTime()));
        }
        List<UserDto> userDtos = userDepartmentMessageMapper.getDepartmentUsers(dao.getDepartmentId());
        response.setUsers(userDtos);
        return response;
    }

    @Override
    public Boolean userLogin(LoginRequest request) {
        if (StringUtils.isEmpty(request.getTelephone())||StringUtils.isEmpty(request.getPassword())||StringUtils.isEmpty(request.getRole()))
        {
            throw new RuntimeException("参数为空");
        }
        String md5_password = MD5Util.getMD5(request.getPassword());
        Integer result = userMessageMapper.userLogin(request.getTelephone(),md5_password,request.getRole());
        if (result>=1){
            return true;
        }
        return false;
    }

    public Boolean checkUserPassword(String password) {
        if (password == null || password.length() == 0 || password == "") {
            return false;
        }
        if (password.length() < 6 || password.length() > 12) {
            return false;
        }
        if (!Regex.isLetterDigit(password)) {
            return false;
        }
        return true;
    }

    }
