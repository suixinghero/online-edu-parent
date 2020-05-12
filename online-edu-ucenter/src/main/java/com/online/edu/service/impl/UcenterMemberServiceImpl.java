package com.online.edu.service.impl;

import com.online.edu.common.R;
import com.online.edu.po.UcenterMember;
import com.online.edu.mapper.UcenterMemberMapper;
import com.online.edu.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xujin
 * @since 2020-05-06
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public R queryRegisterCountOneDay(String day) {
        Integer registerCount = baseMapper.queryRegisterCountOneDay(day);
        return R.ok().data("registerCount",registerCount);
    }
}
