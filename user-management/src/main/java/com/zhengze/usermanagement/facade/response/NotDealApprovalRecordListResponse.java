package com.zhengze.usermanagement.facade.response;

import lombok.Data;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/13 17:56
 */
@Data
public class NotDealApprovalRecordListResponse {
    List<NotDealApprovalRecordResponse> notDealApprovalRecordResponses;
}
