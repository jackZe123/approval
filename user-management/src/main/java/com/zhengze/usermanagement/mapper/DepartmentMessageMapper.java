package com.zhengze.usermanagement.mapper;

import com.zhengze.usermanagement.dao.DepartmentMessageDao;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/10 15:27
 */
@Mapper
public interface DepartmentMessageMapper {

    public Integer addDepartmentMessage(@Param("departmentId")String departmentId, @Param("departmentName")String departmentName, @Param("describle")String describle, @Param("managerId")String managerId, @Param("managerName")String managerName, @Param("groupLeaderId")String groupLeaderId, @Param("groupLeaderName")String groupLeaderName, @Param("createTime") Date createTime, @Param("updateTime")Date updateTime);

    public List<DepartmentMessageDao> getDepartmentMessage();

    public DepartmentMessageDao getDepartmentMessageByDepartmentId(@Param("departmentId")String departmentId);
}
