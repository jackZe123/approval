package com.zhengze.usermanagement.facade.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/10 12:40
 */
@Data
public class InsertApprovalRequest {
    private String goAbroad;
    private String nature;
    private Date goAbroadTime;
    private Date outAbroadTime;
    private String userId;
    private String approvalContent;
    private Integer isPast;
    private String uid;
}
