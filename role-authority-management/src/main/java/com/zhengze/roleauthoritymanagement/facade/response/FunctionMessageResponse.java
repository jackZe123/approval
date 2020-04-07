package com.zhengze.roleauthoritymanagement.facade.response;

import lombok.Data;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/7 10:49
 */
@Data
public class FunctionMessageResponse {
    List<FunctionMessage> functionMessages;
    Long total;
}
