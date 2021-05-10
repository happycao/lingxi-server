package me.happycao.lingxi.config;

import me.happycao.lingxi.constant.RssConfig;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @author happyc
 * 解决The temporary upload location is not valid
 */
@Configuration
public class MultipartConfiguration {

    @Resource
    private RssConfig rssConfig;

    /**
     * 文件上传临时路径
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String property = System.getProperty("user.dir");
        String location = rssConfig.getUploadPath() + "/tmp";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            boolean mkdirs = tmpFile.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }
}
