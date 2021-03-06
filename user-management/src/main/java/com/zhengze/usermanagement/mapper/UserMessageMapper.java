package com.zhengze.usermanagement.mapper;

import com.zhengze.usermanagement.dao.UserMessageDao;
import com.zhengze.usermanagement.facade.response.GetUserMessageResponse;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/4 13:40
 */
@Mapper
public interface UserMessageMapper {
    public List<GetUserMessageResponse> getUserMessage();
    GetUserMessageResponse getUserMessageByTelephone(@Param("telephone") String telephone);
    public Integer insertUserMessage(@Param("userId")String userId, @Param("userName")String userName, @Param("password")String password, @Param("telephone")String telephone, @Param("status")Integer status, @Param("createTime")Date  createTime,@Param("updateTime")Date updateTime);
    public Integer deleteUser(@Param("userId")String userId);
    public Integer updateUserRole(@Param("userId")String userId,@Param("roleId")String roleId);
    public Integer  userLogin(@Param("telephone")String telephone,@Param("password")String password,@Param("roleName")String roleName);
    public Integer getRoleId(@Param("userid")String userid);
    public Integer BDuserRole(@Param("userid")String userid,@Param("roleid")String roleid,@Param("createtime")Date createtime,@Param("updatetime")Date updatetime);
    public Integer updateUserMessage(@Param("userid")String userid,@Param("telephone")String telephone,@Param("username")String username,@Param("status")Integer status,@Param("updateTime")Date updateTime);
    public String getUserNameByUserId(@Param("userid")String userid);
    public Integer updateUserMessageByUserId(@Param("userid")String userid,@Param("telephone")String telephone,@Param("username")String username,@Param("password")String password,@Param("updateTime")Date updateTime);
}
