package com.zhengze.roleauthoritymanagement.controller;

import com.zhengze.roleauthoritymanagement.common.BaseEntityResponse;
import com.zhengze.roleauthoritymanagement.facade.request.DeleteFunctionRequest;
import com.zhengze.roleauthoritymanagement.facade.request.FunctionRequest;
import com.zhengze.roleauthoritymanagement.facade.request.GetFunctionMessageRequest;
import com.zhengze.roleauthoritymanagement.facade.request.UpdateFunctionRequest;
import com.zhengze.roleauthoritymanagement.facade.response.FunctionMessage;
import com.zhengze.roleauthoritymanagement.facade.response.FunctionMessageResponse;
import com.zhengze.roleauthoritymanagement.facade.response.GetFunctionMessageResponse;
import com.zhengze.roleauthoritymanagement.service.FunctionMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/7 17:45
 */
@Api(tags = "FunctionMessageController",description = "角色管理")
@RestController
@RequestMapping("/role/function")
public class FunctionMessageController {

    @Autowired
    private FunctionMessageService functionMessageService;

    @ApiOperation("分页获取功能信息")
    @PostMapping("/getPageFunctionMessage")
    public BaseEntityResponse<FunctionMessageResponse> getPageFunctionMessage(@RequestBody FunctionRequest request){
        BaseEntityResponse  <FunctionMessageResponse> resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            FunctionMessageResponse functionMessages = functionMessageService.getPageFunctionMessages(request);
            resp.setData(functionMessages);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @ApiOperation("获取功能信息")
    @PostMapping("/getFunctionMessage")
    public BaseEntityResponse<List<FunctionMessage>> getPageFunctionMessage(){
        BaseEntityResponse<List<FunctionMessage>>  resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            List<FunctionMessage> functionMessages = functionMessageService.getFunctionMessages();
            resp.setData(functionMessages);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @ApiOperation("更新功能信息")
    @PostMapping("/updateFunctionMessage")
    public BaseEntityResponse<Boolean> updateFunctionMessage(@RequestBody UpdateFunctionRequest request){
        BaseEntityResponse<Boolean>  resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            Boolean flag = functionMessageService.updateFunctionMessage(request);
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @ApiOperation("根据角色名称获取功能")
    @PostMapping("/getFunctionMessageByRoleName")
    public BaseEntityResponse<GetFunctionMessageResponse> getFunctionMessageByRoleName(@RequestBody GetFunctionMessageRequest request){
        BaseEntityResponse<GetFunctionMessageResponse>  resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            GetFunctionMessageResponse response = functionMessageService.getFunctionMessage(request.getRoleName());
            resp.setData(response);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @ApiOperation("删除功能信息")
    @PostMapping("/deleteFunctionMessage")
    public BaseEntityResponse<Boolean> deleteFunctionMessage(@RequestBody DeleteFunctionRequest request){
        BaseEntityResponse<Boolean>  resp = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            Boolean flag = functionMessageService.deleteFunctionMessage(request.getFunctionId());
            resp.setData(flag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }
}
