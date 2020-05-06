package com.zhengze.roleauthoritymanagement.facade.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhengze.roleauthoritymanagement.facade.response.dto.FunctionDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/17 17:08
 */
@Data
public class RoleMessageIdResponse {
    private String roleId;
    private String roleName;
    private String describle;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private List<FunctionDto> functionDtoList;
}
