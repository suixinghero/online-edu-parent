package com.online.edu.service.Front.impl;

import com.online.edu.common.R;
import com.online.edu.service.Front.VodUpLoadFrontService;
import com.online.edu.service.VodUpLoadClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author xujin
 * @createtime 2020-05-10 15:18
 * @description
 */
@Service
public class VodUpLoadFrontServiceImpl implements VodUpLoadFrontService {
    @Autowired
    private VodUpLoadClientService vodUpLoadClientService;
    @Override
    public R getVideoPlayAuth(String id) {
        R result = vodUpLoadClientService.getVideoPlayAuth(id);
        Assert.isTrue(result.getSuccess(),"获取视频凭证失败");
        return R.ok().data("playAuth",result.getData().get("playAuth"));
    }
}
