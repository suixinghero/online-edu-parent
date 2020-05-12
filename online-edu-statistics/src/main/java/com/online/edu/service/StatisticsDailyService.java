package com.online.edu.service;

import com.online.edu.common.R;
import com.online.edu.po.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author xujin
 * @since 2020-05-06
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     * 添加网站统计日数据
     * @param day 某一天
     * @return 通用返回对象
     */
    R addStatisticsDaily(String day);

    /**
     * 通过日期范围得到图表数据
     * @param type 参数查询因子
     * @param start 开始日期
     * @param end 结束日期
     * @return 通用返回对象
     */
    R queryChartData(String type, String start, String end);
}
