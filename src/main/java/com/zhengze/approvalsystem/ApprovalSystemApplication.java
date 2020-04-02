package com.zhengze.approvalsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApprovalSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApprovalSystemApplication.class, args);
    }

}
