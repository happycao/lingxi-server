package me.happycao.lingxi.service;

import me.happycao.lingxi.result.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/05/16
 * desc   : 资源服务相关
 * version: 1.0
 */
public interface RssService {

    Result uploadUserImage(MultipartFile[] files);

    Result uploadFeedImage(MultipartFile[] files);
}
