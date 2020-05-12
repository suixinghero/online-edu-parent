package com.online.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xujin
 * @package-name com.online.edu
 * @createtime 2020-04-02 16:25
 */
@SpringBootApplication
@MapperScan("com.online.edu.mapper")
@EnableTransactionManagement
@EnableEurekaClient
@EnableFeignClients
public class OnlineEduTeacherApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineEduTeacherApplication.class,args);
    }
}
