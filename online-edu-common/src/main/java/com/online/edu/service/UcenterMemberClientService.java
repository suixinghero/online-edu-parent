package com.online.edu.service;

import com.online.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xujin
 * @createtime 2020-05-06 18:50
 * @description
 */
@FeignClient(value = "online-edu-ucenter")
public interface UcenterMemberClientService {
    //统计某一天的注册人数
    @GetMapping("/edu/ucenterMember/queryRegisterCountOneDay/{day}")
    R queryRegisterCountOneDay(@PathVariable("day")String day);
}
