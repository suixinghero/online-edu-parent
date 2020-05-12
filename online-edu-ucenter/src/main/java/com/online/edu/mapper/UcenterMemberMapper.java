package com.online.edu.mapper;

import com.online.edu.po.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author xujin
 * @since 2020-05-06
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    /**
     * 统计某一天的注册人数
     * @param day 某一天
     * @return 注册人数
     */
    Integer queryRegisterCountOneDay(String day);
}
