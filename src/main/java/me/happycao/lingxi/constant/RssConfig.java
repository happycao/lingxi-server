package me.happycao.lingxi.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/05/16
 * desc   : 资源服务相关
 * version: 1.0
 */
@Component
public class RssConfig {

    public static final String PDF = "pdf/";
    public static final String IMAGE = "image/";

    /**
     * windows文件上传目录
     */
    @Value("${rss-path.windowsPath}")
    private String windowsPath;

    /**
     * linux文件上传目录
     */
    @Value("${rss-path.linuxPath}")
    private String linuxPath;

    /**
     * 获取文件上传目录，目前只考虑两种系统
     */
    public String getUploadPath(){
        String osName = getOsName();
        if (osName.toLowerCase().contains("windows")) {
            return windowsPath;
        } else {
            return linuxPath;
        }
    }

    /**
     * 获取系统名称
     */
    private String getOsName() {
        return System.getProperties().getProperty("os.name");
    }

}
