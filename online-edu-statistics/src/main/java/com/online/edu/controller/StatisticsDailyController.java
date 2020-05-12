package com.online.edu.controller;


import com.alibaba.fastjson.JSON;
import com.online.edu.common.R;
import com.online.edu.req.TeacherAddReq;
import com.online.edu.service.StatisticsDailyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author xujin
 * @since 2020-05-06
 */
@RestController
@RequestMapping("/edu/statisticsDaily")
@CrossOrigin
public class StatisticsDailyController {
    private Logger logger = LoggerFactory.getLogger(StatisticsDailyController.class);
    @Autowired
    private StatisticsDailyService statisticsDailyService;
    //添加网站统计日数据
    @PostMapping("/addStatisticsDaily/{day}")
    public R addStatisticsDaily(@PathVariable("day")String day) {
        try {
            logger.info("添加网站统计日数据 开始。req:{} ", day);
            //查询结果
            R result = statisticsDailyService.addStatisticsDaily(day);
            logger.info("添加网站统计日数据 完成。req:{}  查询结果:result: {}", day, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("添加网站统计日数据 失败。req:{} 异常信息{}", day, e.getMessage());
            return R.error();
        }
    }

    //通过日期范围得到图表数据
    @GetMapping("/queryChartData/{type}/{start}/{end}")
    public R queryChartData(@PathVariable("type")String type,
                            @PathVariable("start")String start,
                            @PathVariable("end")String end) {
        try {
            logger.info("通过日期范围得到图表数据 开始。type:{},start{},end{} ", type,start,end);
            //查询结果
            R result = statisticsDailyService.queryChartData(type,start,end);
            logger.info("通过日期范围得到图表数据 完成。type:{},start{},end{}  查询结果:result: {}", type,start,end, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("通过日期范围得到图表数据 失败。type:{},start{},end{} 异常信息{}", type,start,end, e.getMessage());
            return R.error();
        }
    }
}

