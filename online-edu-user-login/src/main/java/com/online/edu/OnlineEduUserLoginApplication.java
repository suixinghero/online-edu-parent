package com.online.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xujin
 * @createtime 2020-04-14 19:36
 * @description
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OnlineEduUserLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineEduUserLoginApplication.class,args);
    }
}
