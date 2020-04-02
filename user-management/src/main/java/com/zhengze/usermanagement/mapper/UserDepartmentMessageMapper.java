package com.zhengze.usermanagement.mapper;

import com.zhengze.usermanagement.facade.response.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/10 16:31
 */
@Mapper
public interface UserDepartmentMessageMapper {
    List<String> getUserIdsByDepartmentId(@Param("departmentId") String departmentId);

    Integer BDUserIdToDepartment(@Param("userId")String userId, @Param("departmentId")String departmentId, @Param("createTime") Date createTime,@Param("updateTime")Date updateTime);

    List<UserDto> getDepartmentUsers(@Param("departmentId")String departmentId);
}
