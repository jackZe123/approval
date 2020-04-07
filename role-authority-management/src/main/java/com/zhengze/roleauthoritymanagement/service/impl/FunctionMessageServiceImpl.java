package com.zhengze.roleauthoritymanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.jdbc.FabricMySQLConnection;
import com.zhengze.roleauthoritymanagement.facade.request.FunctionRequest;
import com.zhengze.roleauthoritymanagement.facade.request.UpdateFunctionRequest;
import com.zhengze.roleauthoritymanagement.facade.response.FunctionMessage;
import com.zhengze.roleauthoritymanagement.facade.response.FunctionMessageResponse;
import com.zhengze.roleauthoritymanagement.mapper.FunctionMessageMapper;
import com.zhengze.roleauthoritymanagement.service.FunctionMessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.FunctionMapper;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 17:43
 */
@Service
public class FunctionMessageServiceImpl implements FunctionMessageService {

    @Autowired
    private FunctionMessageMapper functionMessageMapper;
    @Override
    public FunctionMessageResponse getPageFunctionMessages(FunctionRequest request) {
        FunctionMessageResponse messageResponse = new FunctionMessageResponse();
        PageHelper.startPage(request.getPageIndex(),request.getPageSize());
        List<FunctionMessage> List=functionMessageMapper.getFunctionMessages();
        PageInfo pageInfo = new PageInfo(List);
        messageResponse.setFunctionMessages(pageInfo.getList());
        messageResponse.setTotal(pageInfo.getTotal());
        return messageResponse;
    }

    @Override
    public List<FunctionMessage> getFunctionMessages() {
        return functionMessageMapper.getFunctionMessages();
    }

    @Override
    public Boolean updateFunctionMessage(UpdateFunctionRequest request) {
        if (StringUtils.isEmpty(request.getFunctionId())){
            String functionId= UUID.randomUUID().toString().replace("-","").toLowerCase();
            Integer result =  functionMessageMapper.insertFunctionMessage(functionId,request.getFunctionName(),request.getDescribe(),new Date(),new Date());
            if (result>=1){
                return true;
            }
            return false;
        }else {
            Integer result = functionMessageMapper.updateFunctionMessage(request.getFunctionId(),request.getFunctionName(),request.getDescribe(),new Date());
            if (result>=1){
                return true;
            }
            return false;
        }
    }
}
