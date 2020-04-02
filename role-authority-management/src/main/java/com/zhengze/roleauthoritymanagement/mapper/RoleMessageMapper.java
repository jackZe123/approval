package com.zhengze.roleauthoritymanagement.mapper;

import com.zhengze.roleauthoritymanagement.dao.RoleMessageDao;
import com.zhengze.roleauthoritymanagement.facade.response.RoleMessageResponse;
import org.apache.ibatis.annotations.Mapper;

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
}
