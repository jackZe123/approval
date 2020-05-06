package com.zhengze.usermanagement.facade.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhengze.usermanagement.facade.response.dto.UserNameDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/19 11:16
 */
@Data
public class GetDetailApprovalResponse {
    private String approvalId;
    private String userId;
    private String userName;
    private String approvalContent;
    private List<UserNameDto> userNameDto;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Integer isPast;
    private String goAbroad;
    private String nature;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date goAbroadTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outAbroadTime;
    private String status;
}
