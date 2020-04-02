package com.zhengze.usermanagement.remote;

import com.zhengze.roleauthoritymanagement.dao.RoleMessageDao;
import com.zhengze.usermanagement.common.BaseEntityResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/8 21:11
 */
@FeignClient(name = "role-authority-management")
public interface RoleService {

    @PostMapping("/role/getAllRoleMessage")
    BaseEntityResponse<Map<String, RoleMessageDao>>  getAllRoleMessage();
}
