package com.zhengze.roleauthoritymanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 16:44
 */
@Mapper
public interface AuthorityMessageMapper {
    public List<String> getAuthoritysByRoleId(@Param("roleId")String roleId);
    public Integer deleteAuthorityByRoleId(@Param("roleId")String roleId);
    public Integer insertAuthority(@Param("authorityId")String authorityId,@Param("roleId")String roleId,@Param("functionID")String functionId,@Param("createTime")Date createTime,@Param("updateTime") Date updateTime);
}
