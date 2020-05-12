package com.online.edu;

import com.online.edu.properties.VodProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xujin
 * @createtime 2020-05-04 17:09
 * @description
 */
@SpringBootApplication
@EnableConfigurationProperties(VodProperties.class)
@EnableEurekaClient
@EnableFeignClients
public class OnlineEduVideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineEduVideoApplication.class,args);
    }
}
