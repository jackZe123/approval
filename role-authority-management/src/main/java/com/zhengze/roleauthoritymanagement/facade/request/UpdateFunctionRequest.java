package com.zhengze.roleauthoritymanagement.facade.request;

import lombok.Data;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/7 11:30
 */
@Data
public class UpdateFunctionRequest {
    private String functionId;
    private String functionName;
    private String describe;
}
