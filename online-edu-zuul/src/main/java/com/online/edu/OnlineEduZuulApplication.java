package com.online.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author xujin
 * @createtime 2020-05-11 16:31
 * @description
 */
@SpringBootApplication
@EnableZuulProxy
public class OnlineEduZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineEduZuulApplication.class,args);
    }
}
