package com.zhengze.roleauthoritymanagement.mapper;

import com.zhengze.roleauthoritymanagement.facade.response.FunctionMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 16:50
 */
@Mapper
public interface FunctionMessageMapper {
    List<FunctionMessage> getFunctionMessages();
    Integer insertFunctionMessage(@Param("functionId")String functionId, @Param("functionName")String functionName,@Param("describe")String describe, @Param("createTime") Date createTime, @Param("updateTime")Date updateTime);
    Integer updateFunctionMessage(@Param("functionId")String functionId,@Param("functionName")String functionName,@Param("describe")String describe,@Param("updateTime")Date updateTime);
    Integer deleteFunctionMessage(@Param("functionId")String functionId);
    List<String> getFunctionMessagesByRoleId(@Param("roleId") String roleId);
}
