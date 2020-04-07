package com.zhengze.roleauthoritymanagement.facade.request;

import lombok.Data;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/7 10:42
 */
@Data
public class FunctionRequest {
    private Integer pageIndex=1;
    private Integer pageSize=10;
}
