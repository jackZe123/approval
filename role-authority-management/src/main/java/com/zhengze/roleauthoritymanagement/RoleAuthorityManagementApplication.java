package com.zhengze.roleauthoritymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RoleAuthorityManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleAuthorityManagementApplication.class, args);
    }

}
