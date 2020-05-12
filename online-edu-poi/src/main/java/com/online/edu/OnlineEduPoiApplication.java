package com.online.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xujin
 * @createtime 2020-04-28 18:01
 * @description
 */
@SpringBootApplication
@MapperScan("com.online.edu.mapper")
@EnableTransactionManagement
@EnableEurekaClient
@EnableFeignClients
public class OnlineEduPoiApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineEduPoiApplication.class,args);
    }
}
