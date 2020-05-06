package com.zhengze.usermanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhengze.roleauthoritymanagement.dao.RoleMessageDao;
import com.zhengze.usermanagement.common.BaseEntityResponse;
import com.zhengze.usermanagement.common.Result;
import com.zhengze.usermanagement.common.enums.IsPastEnum;
import com.zhengze.usermanagement.dao.ApprovalMessageDao;
import com.zhengze.usermanagement.dao.DepartmentMessageDao;
import com.zhengze.usermanagement.dao.UserMessageDao;
import com.zhengze.usermanagement.facade.request.*;
import com.zhengze.usermanagement.facade.response.*;
import com.zhengze.usermanagement.facade.response.dto.UserDto;
import com.zhengze.usermanagement.facade.response.dto.UserNameDto;
import com.zhengze.usermanagement.mapper.ApprovalMessageMapper;
import com.zhengze.usermanagement.mapper.DepartmentMessageMapper;
import com.zhengze.usermanagement.mapper.UserDepartmentMessageMapper;
import com.zhengze.usermanagement.mapper.UserMessageMapper;
import com.zhengze.usermanagement.remote.ActivitiService;
import com.zhengze.usermanagement.remote.RoleService;
import com.zhengze.usermanagement.service.UserService;
import com.zhengze.usermanagement.util.MD5Util;
import com.zhengze.usermanagement.util.Regex;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private ApprovalMessageMapper approvalMessageMapper;

    @Override
    public GetUserMessageListResponse getUserMessage() {
        GetUserMessageListResponse responses = new GetUserMessageListResponse();
        List<GetUserMessageResponse> userMessageDaos = userMessageMapper.getUserMessage();
        BaseEntityResponse<Map<String, RoleMessageDao>> roleMaps = roleService.getAllRoleMessage();
        for (GetUserMessageResponse response : userMessageDaos) {
            if (response.getStatus()!=null&&response.getStatus()==1){
                response.setStatu("在职");
            }else {
                response.setStatu("离职");
            }
            if (response.getRoleId()!=null&&roleMaps.getData().get(response.getRoleId())!=null){
                response.setRoleName(roleMaps.getData().get(response.getRoleId()).getRoleName());
            }
        }
        responses.setGetUserMessageResponses(userMessageDaos);
        return responses;
    }

    @Override
    public GetUserMessageResponse getUserMessageResponseByTelephone(String telephone) {
        GetUserMessageResponse response = userMessageMapper.getUserMessageByTelephone(telephone);
        if (response.getStatus()!=null&&response.getStatus()==1){
            response.setStatu("在职");
        }else {
            response.setStatu("离职");
        }
        return response;
    }

    @Override
    public List<UserDto> getUserLabels() {
        List<UserDto> userDtos = new ArrayList<>();
        List<GetUserMessageResponse> userMessageDaos = userMessageMapper.getUserMessage();
        for (GetUserMessageResponse response:userMessageDaos){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(response,userDto);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public Boolean insertMessage(InsertUserRequest request) {
        String userId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        Date createTime = new Date();
        Date updateTime = new Date();
        Boolean flag = Regex.checkPhone(request.getTelephone());
        if (!flag) {
            throw new RuntimeException("手机号格式错误");
        }
        Boolean password = checkUserPassword(request.getPassword());
        if (!password) {
            throw new RuntimeException("密码格式不对，密码长度必须在6-12位，包含数字和字母");
        }
        Integer result = userMessageMapper.insertUserMessage(userId, request.getUserName(), request.getPassword(), request.getTelephone(), null, createTime, updateTime);
        BaseEntityResponse<String> response = roleService.getRoleIdByRoleName(request.getRoleName());
        String roleId = response.getData();
        BDRoleRequest bdRole = new BDRoleRequest();
        bdRole.setRoleId(roleId);
        bdRole.setUserId(userId);
        Boolean bd =BDRoleByUserId(bdRole);
        if (bd&&result>=1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateMessage(UpdateUserRequest request) {
        Integer status = null;
        if (StringUtils.isNotEmpty(request.getStatu())&&"在职".equals(request.getStatu())){
            status=1;
        }else if (StringUtils.isNotEmpty(request.getStatu())&&"离职".equals(request.getStatu())){
            status=0;
        }
        String userName = request.getUserName();
        String telephone = request.getTelephone();
        String userId = request.getUserId();
        Integer result = userMessageMapper.updateUserMessage(userId,telephone,userName,status,new Date());
        String roleName = request.getRoleName();
        BaseEntityResponse<String> roleMessage = roleService.getRoleIdByRoleName(roleName);
        String roleId =roleMessage.getData();
        BDRoleRequest bdRole = new BDRoleRequest();
        bdRole.setUserId(userId);
        bdRole.setRoleId(roleId);
        Integer updateResult = userMessageMapper.updateUserRole(userId,roleId);
        if (result>=1&&updateResult>=1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateUserMessage(UpdateUserRequest request) {
        String userName = request.getUserName();
        String telephone = request.getTelephone();
        String userId = request.getUserId();
        String password = request.getPassword();
        Integer result = userMessageMapper.updateUserMessageByUserId(userId,telephone,userName,password,new Date());
        if (result>=1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteUser(DeleteUserRequest request) {
        Integer flag = userMessageMapper.deleteUser(request.getUserId());
        if (flag >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean BDRoleByUserId(BDRoleRequest request) {
        Integer flag = 0;
        Integer result = userMessageMapper.getRoleId(request.getUserId());
        if (result >= 1) {
            flag = userMessageMapper.updateUserRole(request.getUserId(), request.getRoleId());
        } else {
            flag = userMessageMapper.BDuserRole(request.getUserId(), request.getRoleId(), new Date(), new Date());
        }
        if (flag >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean addDepartmentMessage(AddDepartmentRequest request) {
        String departmentId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        Date createTime = new Date();
        Date updateTime = new Date();
        Integer result = departmentMessageMapper.addDepartmentMessage(departmentId, request.getDepartmentName(), request.getDescrible(), request.getManagerId(), request.getManagerName(), request.getGroupLeaderId(), request.getGroupLeaderName(), createTime, updateTime);
        if (result >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteDepartmentMessage(DeleteDepartmentRequest request) {
        Integer result = departmentMessageMapper.deleteDepartmentMessage(request.getDepartmentId());
        List<String> users = userDepartmentMessageMapper.getUserIdsByDepartmentId(request.getDepartmentId());
        if (CollectionUtils.isEmpty(users)){
            return true;
        }
        Integer deleteResult = userDepartmentMessageMapper.deleteUserDepartmentByUserId(request.getDepartmentId());
        if (result>=1&&deleteResult>=1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateDepartmentMessage(UpdateDepartmentRequest request) {
        Integer result = departmentMessageMapper.updateDepartmentMessage(request.getDepartmentId(),request.getDepartmentName(),request.getDescribe(),request.getManagerId(),request.getManagerName(),request.getGroupLeaderId(),request.getGroupLeaderName(),new Date());
        List<String> userIds = request.getUsers().stream().collect(Collectors.toList());
        List<String> users = userDepartmentMessageMapper.getUserIdsByDepartmentId(request.getDepartmentId());
        Integer updateResult = null;
        for (String userId:userIds){
            if (!users.contains(userId)){
                updateResult = userDepartmentMessageMapper.BDUserIdToDepartment(userId,request.getDepartmentId(),new Date(),new Date());
            }
        }
        for (String userId:users){
            if (!userIds.contains(userId)){
                updateResult = userDepartmentMessageMapper.deleteUserDepartment(request.getDepartmentId(),userId);
            }
        }
        if (result>=1&&updateResult>=1){
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean BDuserForDepartment(BDuserForDepartmentRequest request) {
        List<String> userIds = userDepartmentMessageMapper.getUserIdsByDepartmentId(request.getDepartmentId());
        List<String> BDuserIds = request.getUserIds();
        if (CollectionUtils.isEmpty(BDuserIds)) {
            return true;
        }
        Boolean result = true;
        for (String userId : BDuserIds) {
            if (userIds.contains(userId)) {
                continue;
            }
            Integer flag = userDepartmentMessageMapper.BDUserIdToDepartment(userId, request.getDepartmentId(), new Date(), new Date());
            if (flag >= 1) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public List<GetDepartmentMessageResponse> getDepartmentMessage() {
        List<GetDepartmentMessageResponse> responses = new ArrayList<GetDepartmentMessageResponse>();
        List<DepartmentMessageDao> daos = departmentMessageMapper.getDepartmentMessage();
        for (DepartmentMessageDao dao : daos) {
            GetDepartmentMessageResponse departmentMessageResponse = new GetDepartmentMessageResponse();
            BeanUtils.copyProperties(dao, departmentMessageResponse);
            if (dao.getCreateTime() != null) {
                departmentMessageResponse.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dao.getCreateTime()));
            }
            if (dao.getUpdateTime() != null) {
                departmentMessageResponse.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dao.getUpdateTime()));
            }
            departmentMessageResponse.setDescribe(dao.getDescrible());
            List<UserDto> userDtos = userDepartmentMessageMapper.getDepartmentUsers(dao.getDepartmentId());
            List<String> userNames = userDtos.stream().distinct().map(x->x.getUserName()).collect(Collectors.toList());
            String users = String.join(",",userNames);
            departmentMessageResponse.setUsers(users);
            responses.add(departmentMessageResponse);
        }
        return responses;
    }

    @Override
    public GetDepartmentMessageResponse getDeaprtmentMessageByDepartmentId(String departmentId) {
        GetDepartmentMessageResponse response = new GetDepartmentMessageResponse();
        DepartmentMessageDao dao = departmentMessageMapper.getDepartmentMessageByDepartmentId(departmentId);
        BeanUtils.copyProperties(dao, response);
        if (dao.getCreateTime() != null) {
            response.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dao.getCreateTime()));
        }
        if (dao.getUpdateTime() != null) {
            response.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dao.getUpdateTime()));
        }
        List<UserDto> userDtos = userDepartmentMessageMapper.getDepartmentUsers(dao.getDepartmentId());
        List<String> userNames = userDtos.stream().distinct().map(x->x.getUserName()).collect(Collectors.toList());
        String users = String.join(",",userNames);
        response.setUsers(users);
        return response;
    }

    @Override
    public Boolean userLogin(LoginRequest request) {
        if (StringUtils.isEmpty(request.getTelephone()) || StringUtils.isEmpty(request.getPassword()) || StringUtils.isEmpty(request.getRole())) {
            throw new RuntimeException("参数为空");
        }
        Integer result = userMessageMapper.userLogin(request.getTelephone(),request.getPassword(), request.getRole());
        if (result >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public String insertApprovalRecord(InsertApprovalRequest request) {
        String beUserId = "";
        if (StringUtils.isNotEmpty(request.getUserId())) {
            beUserId = getBeUserId(request.getUserId());
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("proDefId", "CGSP-01:3:4367d839bf8247afb423e96e6bf47f99");
            variables.put("bussName", "CGSP");
            variables.put("key", "CGSP-01");
            String userName = userMessageMapper.getUserNameByUserId(request.getUserId());
            String approvalid = activitiService.run(variables);
            Integer result = approvalMessageMapper.insertApprovalMessage(approvalid,request.getUserId(),request.getApprovalContent(),beUserId,new Date(),2,null,request.getGoAbroad(),request.getNature(),request.getGoAbroadTime(),request.getOutAbroadTime(),userName);
            if (result >= 1) {
                return approvalid;
            }
        }

        return null;
    }

    private Date getDate(String date) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }

    private String getBeUserId(String userId) {
        if (userId.equals("123456")){
            return "x";
        }
        String beUserId = "";
        String departmentId = userDepartmentMessageMapper.getDepartmentIdByUserId(userId);
        DepartmentMessageDao departmentMessageDao = null;
        departmentMessageDao = departmentMessageMapper.getDepartmentMessageByDepartmentId(departmentId);
        if (departmentMessageDao == null) {
            departmentMessageDao = departmentMessageMapper.getDepartmentMessageByGroupId(userId);
        }
        if (departmentMessageDao == null) {
            beUserId = "123456";
        } else if (!userId.equals(departmentMessageDao.getGroupLeaderId()) && !userId.equals(departmentMessageDao.getManagerId())) {
            beUserId = departmentMessageDao.getGroupLeaderId();
        } else if (userId.equals(departmentMessageDao.getGroupLeaderId())) {
            beUserId = departmentMessageDao.getManagerId();
        } else if (userId.equals(departmentMessageDao.getManagerId())) {
            beUserId = "123456";
        }
        return beUserId;
    }

    @Override
    public Boolean submitApprovalRecord(SubmitApprovalRequest request) {
     String beUserId= getBeUserId(request.getUserId());
     Map<String, Object> params = new HashMap<>();
     params.put("taskId",request.getApprovalId());
     params.put("dealType",request.getIsPast().toString());
     params.put("dealReason",request.getReason());
     params.put("dealUserId",beUserId);
     if (request.getIsPast()!=null&&request.getIsPast()==1){
         params.put("rejectElemKey","S00000");
     }
     Object object = activitiService.tasks(params);
     Date goBroadDate=null;
     Date outBroadDate=null;
     try {
            if (StringUtils.isNotEmpty(request.getGoAbroadTime())){
                goBroadDate = getDate(request.getGoAbroadTime());
            }
            if (StringUtils.isNotEmpty(request.getOutAbroadTime())){
                outBroadDate = getDate(request.getOutAbroadTime());
            }
     } catch (ParseException e) {
            e.printStackTrace();
     }
     Integer result1 = approvalMessageMapper.updateApprovalMessage(request.getApprovalId(),request.getUserId(),request.getIsPast(),request.getReason(),new Date());
     Integer result2 = approvalMessageMapper.submitApprovalMessage(request.getApprovalId(), request.getUserId(), request.getApprovalContent(), beUserId, new Date(), 2, null,request.getGoAbroad(),request.getNature(),goBroadDate,outBroadDate);
     if (result1>=1&&result2>=1){
         return true;
     }
     return false;
    }

    @Override
    public NotDealApprovalRecordListResponse getNotDealApprovals(String userId) {
        NotDealApprovalRecordListResponse response = new NotDealApprovalRecordListResponse();
        List<NotDealApprovalRecordResponse> responses = new ArrayList<>();
        List<ApprovalMessageDao> approvalMessageDaos = approvalMessageMapper.getNotDealApprovals(userId);
        List<GetUserMessageResponse> userMessageDaos = userMessageMapper.getUserMessage();
        Map<String,String>userMap=userMessageDaos.stream().collect(Collectors.toMap(GetUserMessageResponse::getUserId, GetUserMessageResponse::getUserName, (key1, key2) -> key2));
        for (ApprovalMessageDao dao:approvalMessageDaos){
            NotDealApprovalRecordResponse notDealApprovalRecordResponse = new NotDealApprovalRecordResponse();
            notDealApprovalRecordResponse.setApprovalId(dao.getApprovalId());
            notDealApprovalRecordResponse.setUserName(userMap.get(dao.getUserId()));
            notDealApprovalRecordResponse.setApprovalContent(dao.getApprovalContent());
            notDealApprovalRecordResponse.setCreateTime(dao.getCreateTime());
            notDealApprovalRecordResponse.setGoAbroad(dao.getGoAbroad());
            notDealApprovalRecordResponse.setGoAbroadTime(dao.getGoAbroadTime());
            notDealApprovalRecordResponse.setOutAbroadTime(dao.getOutAbroadTime());
            notDealApprovalRecordResponse.setNature(dao.getNature());
            responses.add(notDealApprovalRecordResponse);
        }
        response.setNotDealApprovalRecordResponses(responses);
        return response;
    }

    @Override
    public GetApprovalRecordListResponse getApprovalResponses(String approvalid) {
        GetApprovalRecordListResponse response = new GetApprovalRecordListResponse();
        List<GetApprovalRecordResponse> responses = new ArrayList<>();
        List<ApprovalMessageDao> daos = approvalMessageMapper.getApprovalMessages(approvalid);
        List<GetUserMessageResponse> userMessageDaos = userMessageMapper.getUserMessage();
        Map<String,String> userMap=userMessageDaos.stream().collect(Collectors.toMap(GetUserMessageResponse::getUserId, GetUserMessageResponse::getUserName, (key1, key2) -> key2));
        Map<Integer,String> isPastMap = IsPastEnum.getIsPartMap();
        for (ApprovalMessageDao dao:daos){
            GetApprovalRecordResponse getApprovalRecordResponse = new GetApprovalRecordResponse();
            BeanUtils.copyProperties(dao,getApprovalRecordResponse);
            if (dao.getIsPast()!=null){
                getApprovalRecordResponse.setIsPast(isPastMap.get(dao.getIsPast()));
            }
            if (StringUtils.isNotEmpty(dao.getUserId())){
                getApprovalRecordResponse.setUserName(userMap.get(dao.getUserId()));
            }
            if (StringUtils.isNotEmpty(dao.getBeUserId())){
                getApprovalRecordResponse.setBeUserName(userMap.get(dao.getBeUserId()));
            }
            responses.add(getApprovalRecordResponse);
        }
        response.setGetApprovalRecordResponseList(responses);
        return response;
    }

    @Override
    public GetApprovalResponse getApprovalByUserId(String userId) {
        GetApprovalResponse approvalResponse = new GetApprovalResponse();
        List<String> approvals = approvalMessageMapper.getApprovalIdByUserId(userId);
        List<String> newApprovals = approvalMessageMapper.getNewApprovalIdByUserId(userId);
        List<GetDetailApprovalResponse> approvalNewResponses = new ArrayList<>();
        List<GetDetailApprovalResponse> approvalResponses = new ArrayList<>();
        for (String approvalid:newApprovals){
            GetDetailApprovalResponse getDetailApprovalResponse = new GetDetailApprovalResponse();
            Integer isPast =1;
            Boolean flag = false;
            List<UserNameDto> userNameDtos = new ArrayList<>();
            List<ApprovalMessageDao> approvalMessageDaos = approvalMessageMapper.getApprovalMessages(approvalid);
            for (ApprovalMessageDao approvalMessageDao:approvalMessageDaos){
                if (StringUtils.isNotEmpty(approvalMessageDao.getUserName())){
                    UserNameDto userName= new UserNameDto();
                    userName.setBeUserName(approvalMessageDao.getUserName());
                    userName.setTitle("发起人:");
                    userName.setStatus("success");
                    userNameDtos.add(userName);
                    BeanUtils.copyProperties(approvalMessageDao,getDetailApprovalResponse);
                }
                if (approvalMessageDao.getIsPast().equals(0)){
                    isPast++;
                    UserNameDto userName = new UserNameDto();
                    userName.setTitle("审批人:");
                    userName.setStatus("success");
                    userName.setBeUserName(userMessageMapper.getUserNameByUserId(approvalMessageDao.getBeUserId()));
                    userNameDtos.add(userName);
                }else if (approvalMessageDao.getIsPast().equals(1)){
                    isPast++;
                    UserNameDto userName = new UserNameDto();
                    userName.setTitle("审批人:");
                    userName.setStatus("error");
                    userName.setBeUserName(userMessageMapper.getUserNameByUserId(approvalMessageDao.getBeUserId()));
                    userNameDtos.add(userName);
                    flag =true;
                }
            }
            getDetailApprovalResponse.setIsPast(isPast);
            if (isPast!=null&&isPast.equals(4)){
                getDetailApprovalResponse.setStatus("已审核");
            }else if (flag){
                getDetailApprovalResponse.setStatus("已驳回");
            }else {
                getDetailApprovalResponse.setStatus("待审核");
            }
            getDetailApprovalResponse.setUserNameDto(userNameDtos);
            approvalNewResponses.add(getDetailApprovalResponse);
        }
        for (String approvalid:approvals){
            GetDetailApprovalResponse getDetailApprovalResponse = new GetDetailApprovalResponse();
            Integer isPast =1;
            Boolean flag = false;
            List<UserNameDto> userNameDtos = new ArrayList<>();
            List<ApprovalMessageDao> approvalMessageDaos = approvalMessageMapper.getApprovalMessages(approvalid);
            for (ApprovalMessageDao approvalMessageDao:approvalMessageDaos){
                if (StringUtils.isNotEmpty(approvalMessageDao.getUserName())){
                    UserNameDto userNameDto = new UserNameDto();
                    userNameDto.setBeUserName(approvalMessageDao.getUserName());
                    userNameDto.setTitle("发起人:");
                    userNameDto.setStatus("success");
                    userNameDtos.add(userNameDto);
                    BeanUtils.copyProperties(approvalMessageDao,getDetailApprovalResponse);
                    getDetailApprovalResponse.setUserName(userMessageMapper.getUserNameByUserId(userId));
                }
                if (approvalMessageDao.getIsPast().equals(0)){
                    isPast++;
                    UserNameDto userNameDto = new UserNameDto();
                    userNameDto.setTitle("审批人:");
                    userNameDto.setBeUserName(userMessageMapper.getUserNameByUserId(approvalMessageDao.getBeUserId()));
                    userNameDto.setStatus("success");
                    userNameDtos.add(userNameDto);
                }else if (approvalMessageDao.getIsPast().equals(1)){
                    flag = true;
                    isPast++;
                    UserNameDto userNameDto = new UserNameDto();
                    userNameDto.setTitle("审批人:");
                    userNameDto.setBeUserName(userMessageMapper.getUserNameByUserId(approvalMessageDao.getBeUserId()));
                    userNameDto.setStatus("error");
                    userNameDtos.add(userNameDto);
                }
            }
            getDetailApprovalResponse.setIsPast(isPast);
            if (isPast!=null&&isPast.equals(4)){
                getDetailApprovalResponse.setStatus("已审核");
            }else if (flag){
                getDetailApprovalResponse.setStatus("已驳回");
            }else {
                getDetailApprovalResponse.setStatus("待审核");
            }
            getDetailApprovalResponse.setUserNameDto(userNameDtos);
            approvalResponses.add(getDetailApprovalResponse);
        }
        approvalResponse.setApprovals(approvalNewResponses);
        approvalResponse.setNewApprovals(approvalNewResponses);
        return approvalResponse;
    }

    @Override
    public GetApprovalRecordListResponse getApplyApprovalMessage() {
        GetApprovalRecordListResponse response = new GetApprovalRecordListResponse();
        List<GetApprovalRecordResponse> responses = new ArrayList<>();
        List<ApprovalMessageDao> daos = approvalMessageMapper.getApplyApprovalMessages();
        List<GetUserMessageResponse> userMessageDaos = userMessageMapper.getUserMessage();
        Map<String,String> userMap=userMessageDaos.stream().collect(Collectors.toMap(GetUserMessageResponse::getUserId, GetUserMessageResponse::getUserName, (key1, key2) -> key2));
        Map<Integer,String> isPastMap = IsPastEnum.getIsPartMap();
        for (ApprovalMessageDao dao:daos){
            GetApprovalRecordResponse getApprovalRecordResponse = new GetApprovalRecordResponse();
            BeanUtils.copyProperties(dao,getApprovalRecordResponse);
            if (dao.getIsPast()!=null){
                getApprovalRecordResponse.setIsPast(isPastMap.get(dao.getIsPast()));
            }
            if (StringUtils.isNotEmpty(dao.getUserId())){
                getApprovalRecordResponse.setUserName(userMap.get(dao.getUserId()));
            }
            if (StringUtils.isNotEmpty(dao.getBeUserId())){
                getApprovalRecordResponse.setBeUserName(userMap.get(dao.getBeUserId()));
            }
            responses.add(getApprovalRecordResponse);
        }
        response.setGetApprovalRecordResponseList(responses);
        return response;
    }

    @Override
    public GetApprovalResponse getCompletedApprovalByUseriD(String userId) {
        GetApprovalResponse response = new GetApprovalResponse();
        List<String> allApprovals = approvalMessageMapper.getNewApprovalIdByUserId(userId);
        List<GetDetailApprovalResponse> allApprovaResponses = new ArrayList<>();
        for (String approvalid:allApprovals){
            GetDetailApprovalResponse getDetailApprovalResponse = new GetDetailApprovalResponse();
            Integer isPast =1;
            List<UserNameDto> userNameDtos = new ArrayList<>();
            List<ApprovalMessageDao> approvalMessageDaos = approvalMessageMapper.getApprovalMessages(approvalid);
            if (approvalMessageDaos.size()<4){
                continue;
            }
            for (ApprovalMessageDao approvalMessageDao:approvalMessageDaos){
                if (StringUtils.isNotEmpty(approvalMessageDao.getUserName())){
                    UserNameDto userNameDto = new UserNameDto();
                    userNameDto.setTitle("发起人:");
                    userNameDto.setBeUserName(approvalMessageDao.getUserName());
                    userNameDtos.add(userNameDto);
                    BeanUtils.copyProperties(approvalMessageDao,getDetailApprovalResponse);
                    getDetailApprovalResponse.setUserName(userMessageMapper.getUserNameByUserId(userId));
                }
                if (approvalMessageDao.getIsPast().equals(0)){
                    isPast++;
                    UserNameDto userNameDto = new UserNameDto();
                    userNameDto.setTitle("审批人:");
                    userNameDto.setBeUserName(userMessageMapper.getUserNameByUserId(approvalMessageDao.getBeUserId()));
                    userNameDtos.add(userNameDto);
                }
            }
            getDetailApprovalResponse.setIsPast(isPast);
            getDetailApprovalResponse.setUserNameDto(userNameDtos);
            allApprovaResponses.add(getDetailApprovalResponse);
        }
        response.setNewApprovals(allApprovaResponses);
        List<String> Approvals = approvalMessageMapper.getApprovalIdByUserId(userId);
        List<GetDetailApprovalResponse> ApprovaResponses = new ArrayList<>();
        for (String approvalid:Approvals){
            GetDetailApprovalResponse getDetailApprovalResponse = new GetDetailApprovalResponse();
            Integer isPast =1;
            List<UserNameDto> userNameDtos = new ArrayList<>();
            List<ApprovalMessageDao> approvalMessageDaos = approvalMessageMapper.getApprovalMessages(approvalid);
            if (approvalMessageDaos.size()<4){
                continue;
            }
            for (ApprovalMessageDao approvalMessageDao:approvalMessageDaos){
                if (StringUtils.isNotEmpty(approvalMessageDao.getUserName())){
                    UserNameDto userNameDto = new UserNameDto();
                    userNameDto.setTitle("发起人:");
                    userNameDto.setBeUserName(approvalMessageDao.getUserName());
                    userNameDtos.add(userNameDto);
                    BeanUtils.copyProperties(approvalMessageDao,getDetailApprovalResponse);
                    getDetailApprovalResponse.setUserName(userMessageMapper.getUserNameByUserId(userId));
                }
                if (approvalMessageDao.getIsPast().equals(0)){
                    isPast++;
                    UserNameDto userNameDto = new UserNameDto();
                    userNameDto.setTitle("审批人:");
                    userNameDto.setBeUserName(userMessageMapper.getUserNameByUserId(approvalMessageDao.getBeUserId()));
                    userNameDtos.add(userNameDto);
                }
            }
            getDetailApprovalResponse.setIsPast(isPast);
            getDetailApprovalResponse.setUserNameDto(userNameDtos);
            ApprovaResponses.add(getDetailApprovalResponse);
        }
        response.setApprovals(ApprovaResponses);
        return response;
    }

    public Boolean checkUserPassword (String password){
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