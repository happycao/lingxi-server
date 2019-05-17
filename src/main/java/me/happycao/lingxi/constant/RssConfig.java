package me.happycao.lingxi.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/05/16
 * desc   : 资源服务相关
 * version: 1.0
 */
@Component
public class RssConfig {

    /**
     * 服务路径
     */
    @Value("${server.servlet-path}")
    private String servletPath;

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
    public String getUploadPath() {
        if (isWindows()) {
            return windowsPath + getServletPath();
        } else {
            return linuxPath + getServletPath();
        }
    }

    /**
     * 获取服务目录
     */
    private String getServletPath() {
        return servletPath;
    }

    /**
     * 用户图片目录
     */
    public String getUserPath() {
        return "/user/";
    }

    /**
     * 动态图片目录
     */
    public String getFeedPath() {
        return "/feed/";
    }

    /**
     * 当前系统是否为windows
     */
    private boolean isWindows() {
        String osName = getOsName();
        return osName.toLowerCase().contains("windows");
    }

    /**
     * 获取系统名称
     */
    private String getOsName() {
        return System.getProperties().getProperty("os.name");
    }

}
