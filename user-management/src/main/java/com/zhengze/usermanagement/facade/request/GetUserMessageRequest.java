package com.zhengze.usermanagement.facade.request;

import lombok.Data;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/7 12:55
 */
@Data
public class GetUserMessageRequest {
    private Integer pageIndex=1;
    private Integer pageSize=10;
}
