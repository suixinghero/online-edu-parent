package com.online.edu.service;

import com.online.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xujin
 * @createtime 2020-05-06 15:47
 * @description
 */
@FeignClient(value = "online-edu-login")
public interface LoginClientService {
    @PostMapping("/edu/user/login")
    R login();
    @GetMapping("/edu/user/info")
    R info();
}
