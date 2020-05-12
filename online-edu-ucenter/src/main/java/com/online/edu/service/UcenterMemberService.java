package com.online.edu.service;

import com.online.edu.common.R;
import com.online.edu.po.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author xujin
 * @since 2020-05-06
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    /**
     * 统计某一天的注册人数
     * @param day 某一天
     * @return 通用返回对象
     */
    R queryRegisterCountOneDay(String day);
}
