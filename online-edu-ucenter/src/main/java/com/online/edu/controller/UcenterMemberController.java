package com.online.edu.controller;


import com.alibaba.fastjson.JSON;
import com.online.edu.common.R;
import com.online.edu.service.UcenterMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xujin
 * @since 2020-05-06
 */
@RestController
@RequestMapping("/edu/ucenterMember")
@CrossOrigin
public class UcenterMemberController {
    private Logger logger = LoggerFactory.getLogger(UcenterMemberController.class);
    @Autowired
    private UcenterMemberService ucenterMemberService;
    //统计某一天的注册人数
    @GetMapping("/queryRegisterCountOneDay/{day}")
    public R queryRegisterCountOneDay(@PathVariable("day")String day){
        try {
            logger.info("统计某一天的注册人数 开始。day{}",day);
            //查询结果
            R result = ucenterMemberService.queryRegisterCountOneDay(day);
            logger.info("统计某一天的注册人数 完成。查询结果:day{},result: {}", day,JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("统计某一天的注册人数 失败。day{},异常信息{}",day, e.getMessage());
            return R.error();
        }
    }
}

