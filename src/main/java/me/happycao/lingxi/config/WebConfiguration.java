package me.happycao.lingxi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/02
 * desc   : 静态资源设置
 * version: 1.0
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将所有/web/** 访问都映射到classpath:/webapp/ 目录下
        registry.addResourceHandler("/web/**")
                .addResourceLocations("classpath:/web/");

        // swagger2配置
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/js/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
