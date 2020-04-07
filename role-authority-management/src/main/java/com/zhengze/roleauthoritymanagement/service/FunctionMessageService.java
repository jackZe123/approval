package com.zhengze.roleauthoritymanagement.service;

import com.zhengze.roleauthoritymanagement.facade.request.FunctionRequest;
import com.zhengze.roleauthoritymanagement.facade.request.UpdateFunctionRequest;
import com.zhengze.roleauthoritymanagement.facade.response.FunctionMessage;
import com.zhengze.roleauthoritymanagement.facade.response.FunctionMessageResponse;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 17:40
 */
public interface FunctionMessageService {
    FunctionMessageResponse getPageFunctionMessages(FunctionRequest request);
    List<FunctionMessage> getFunctionMessages();
    Boolean updateFunctionMessage(UpdateFunctionRequest request);
}
