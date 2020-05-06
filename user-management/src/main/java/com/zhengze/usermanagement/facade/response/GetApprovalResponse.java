package com.zhengze.usermanagement.facade.response;

import lombok.Data;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/18 15:39
 */
@Data
public class GetApprovalResponse {
    private List<GetDetailApprovalResponse> approvals;
    private List<GetDetailApprovalResponse> newApprovals;
}
