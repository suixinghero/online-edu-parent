package com.online.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.common.R;
import com.online.edu.po.StatisticsDaily;
import com.online.edu.mapper.StatisticsDailyMapper;
import com.online.edu.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.service.UcenterMemberClientService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author xujin
 * @since 2020-05-06
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UcenterMemberClientService ucenterMemberClientService;
    @Override
    public R addStatisticsDaily(String day) {
        QueryWrapper<StatisticsDaily> statisticsDailyWrapper = new QueryWrapper<>();
        statisticsDailyWrapper.eq("date_calculated",day);
        StatisticsDaily statisticsDailyByQuery = baseMapper.selectOne(statisticsDailyWrapper);
        if (statisticsDailyByQuery != null) {
            R result = ucenterMemberClientService.queryRegisterCountOneDay(day);
            //得到某一天的注册人数
            Integer registerNum = (Integer) result.getData().get("registerCount");
            Integer loginNum = RandomUtils.nextInt(100,200);
            Integer videoViewNum = RandomUtils.nextInt(100,200);
            Integer courseNum = RandomUtils.nextInt(100,200);
            StatisticsDaily statisticsDaily = StatisticsDaily.newBuilder().id(statisticsDailyByQuery.getId()).dateCalculated(day).registerNum(registerNum).loginNum(loginNum)
                    .videoViewNum(videoViewNum).courseNum(courseNum).build();
            int count = baseMapper.updateById(statisticsDaily);
            Assert.isTrue(count > 0,"更新网站统计日数据失败");
            return R.ok();
        }
        R result = ucenterMemberClientService.queryRegisterCountOneDay(day);
        //得到某一天的注册人数
        Integer registerNum = (Integer) result.getData().get("registerCount");
        Integer loginNum = RandomUtils.nextInt(100,200);
        Integer videoViewNum = RandomUtils.nextInt(100,200);
        Integer courseNum = RandomUtils.nextInt(100,200);
        StatisticsDaily statisticsDaily = StatisticsDaily.newBuilder().dateCalculated(day).registerNum(registerNum).loginNum(loginNum)
                .videoViewNum(videoViewNum).courseNum(courseNum).build();
        int count = baseMapper.insert(statisticsDaily);
        Assert.isTrue(count > 0,"添加网站统计日数据失败");
        return R.ok();
    }

    @Override
    public R queryChartData(String type, String start, String end) {
        QueryWrapper<StatisticsDaily> statisticsDailyWrapper = new QueryWrapper<>();
        statisticsDailyWrapper.between("date_calculated",start,end);
        statisticsDailyWrapper.orderByAsc("date_calculated");
        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(statisticsDailyWrapper);
        List<String> xData = new ArrayList<>();
        Optional.ofNullable(statisticsDailies).orElse(Collections.emptyList()).forEach(s->{
            xData.add(s.getDateCalculated());
        });
        List<Integer> yData = getYData(type,statisticsDailies);
        return R.ok().data("xData",xData).data("yData",yData);
    }
    private  List<Integer> getYData(String type,List<StatisticsDaily> statisticsDailies) {
        List<Integer> yData = new ArrayList<>();
        switch (type) {
            case "register_num":
                Optional.ofNullable(statisticsDailies).orElse(Collections.emptyList()).forEach(s->{
                    yData.add(s.getRegisterNum());
                });
                break;
            case "login_num":
                Optional.ofNullable(statisticsDailies).orElse(Collections.emptyList()).forEach(s->{
                    yData.add(s.getLoginNum());
                });
                break;
            case "video_view_num":
                Optional.ofNullable(statisticsDailies).orElse(Collections.emptyList()).forEach(s->{
                    yData.add(s.getVideoViewNum());
                });
                break;
            case "course_num":
                Optional.ofNullable(statisticsDailies).orElse(Collections.emptyList()).forEach(s->{
                    yData.add(s.getCourseNum());
                });
                break;
        }
        return yData;
    }
}
