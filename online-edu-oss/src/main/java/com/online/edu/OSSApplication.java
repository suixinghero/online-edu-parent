package com.online.edu;

import com.online.edu.properties.OSSProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xujin
 * @createtime 2020-04-16 17:03
 * @description
 */
@SpringBootApplication
@EnableConfigurationProperties(OSSProperties.class)
@EnableEurekaClient
@EnableFeignClients
public class OSSApplication {
    public static void main(String[] args) {
        SpringApplication.run(OSSApplication.class,args);
    }
}
