package com.zhengze.roleauthoritymanagement.mapper;

import com.zhengze.roleauthoritymanagement.dao.RoleMessageDao;
import com.zhengze.roleauthoritymanagement.facade.response.RoleMessageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 16:50
 */
@Mapper
public interface RoleMessageMapper {
    public List<RoleMessageResponse> getRoleMessages();

    public List<RoleMessageDao> getAllRoles();

    public String getRoleIdByRoleName(@Param("roleName") String roleName);

    public Integer deleteRoleMessage(@Param("roleId")String roleId);

    public Integer addRoleMessage(@Param("roleId")String roleId, @Param("roleName")String roleName, @Param("describle")String describle, @Param("createTime") Date createTime, @Param("updateTime")Date updateTime);

    public Integer updateRoleMessage(@Param("roleId")String roleId, @Param("roleName")String roleName, @Param("describle")String describle, @Param("updateTime")Date updateTime);
}
