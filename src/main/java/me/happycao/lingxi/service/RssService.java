package me.happycao.lingxi.service;

import me.happycao.lingxi.model.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/05/16
 * desc   : 资源服务相关
 * version: 1.0
 */
public interface RssService {

    Result uploadPdf(MultipartFile[] files);

    Result uploadImage(MultipartFile[] files);
}
