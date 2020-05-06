package com.zhengze.usermanagement.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/11 12:13
 */
@Data
public class ApprovalMessageDao {
    private Integer id;
    private String approvalId;
    private String userId;
    private String approvalContent;
    private String beUserId;
    private Date createTime;
    private Integer isPast;
    private String reason;
    private Date approvalTime;
    private String goAbroad;
    private String nature;
    private Date goAbroadTime;
    private Date outAbroadTime;
    private String userName;
}
