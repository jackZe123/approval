package com.zhengze.usermanagement.facade.response;

import lombok.Data;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/7 12:50
 */
@Data
public class GetUserMessageListResponse {
    List<GetUserMessageResponse> getUserMessageResponses;
    Long total;
}
