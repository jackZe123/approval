package com.zhengze.usermanagement.facade.request;

import lombok.Data;

import java.util.Date;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/12 15:56
 */
@Data
public class SubmitApprovalRequest {
    private String goAbroad;
    private String nature;
    private String goAbroadTime;
    private String outAbroadTime;
    private String userId;
    private String approvalId;
    private Integer isPast;
    private String reason;
    private String approvalContent;
}
