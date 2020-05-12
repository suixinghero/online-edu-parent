package com.online.edu.service;

import com.online.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xujin
 * @createtime 2020-05-07 17:32
 * @description
 */
@FeignClient(value = "online-edu-statistics")
public interface StatisticsDailyClientService {
    //添加网站统计日数据
    @PostMapping("/edu/statisticsDaily/addStatisticsDaily/{day}")
    R addStatisticsDaily(@PathVariable("day")String day);

    //通过日期范围得到图表数据
    @GetMapping("/edu/statisticsDaily/queryChartData/{type}/{start}/{end}")
    R queryChartData(@PathVariable("type")String type,
                            @PathVariable("start")String start,
                            @PathVariable("end")String end);
}
