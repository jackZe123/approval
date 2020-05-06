package com.zhengze.usermanagement.remote;

import com.zhengze.usermanagement.common.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/10 13:52
 */
@FeignClient(name = "activiti")
public interface ActivitiService {

    @PostMapping("/process/run/")
    public String run(@RequestBody Map<String, Object> variables);

    @PostMapping(value = "runtime/tasks/do")
    public Object tasks(@RequestBody Map<String, Object> params );
}
