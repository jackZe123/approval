package com.zhengze.usermanagement.facade.response;

import lombok.Data;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/13 19:13
 */
@Data
public class GetApprovalRecordListResponse {
    List<GetApprovalRecordResponse> getApprovalRecordResponseList;
}
