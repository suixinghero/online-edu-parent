package com.online.edu.service.Front;

import com.online.edu.common.R;


/**
 * @author xujin
 * @createtime 2020-05-10 15:17
 * @description
 */

public interface VodUpLoadFrontService {
    /**
     * 通过视频ID获取播放凭证
     * @param id 视频ID
     * @return 通用返回对象
     */
    R getVideoPlayAuth(String id);
}
