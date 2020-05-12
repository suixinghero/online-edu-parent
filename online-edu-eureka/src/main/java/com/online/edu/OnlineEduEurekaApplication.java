package com.online.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author xujin
 * @createtime 2020-05-05 18:44
 * @description
 */
@SpringBootApplication
@EnableEurekaServer
public class OnlineEduEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineEduEurekaApplication.class,args);
    }
}
