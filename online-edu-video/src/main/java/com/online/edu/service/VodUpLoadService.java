package com.online.edu.service;

import com.aliyuncs.exceptions.ClientException;
import com.online.edu.common.R;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author xujin
 * @createtime 2020-05-05 16:06
 * @description
 */

public interface VodUpLoadService {
    /**
     * 上传视频到阿里云
     * @param file 上传文件项
     * @return 通用返回对象
     */
    R upLoadVod(MultipartFile file) throws IOException;

    /**
     * 删除阿里云端的视频
     * @param id 视频ID
     * @return 通用返回对象
     */
    R deleteVodById(String id) throws ClientException;

    /**
     * 批量删除阿里云端的视频
     * @param vodIds 视频ID的集合
     * @return 通用返回对象
     */
    R deleteVods(List<String> vodIds) throws ClientException;

    /**
     * 通过视频ID获取播放凭证
     * @param id 视频ID
     * @return 通用返回对象
     */
    R getVideoPlayAuth(String id) throws ClientException;

}
