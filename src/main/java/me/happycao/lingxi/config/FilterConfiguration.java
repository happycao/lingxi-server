package me.happycao.lingxi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author : happyc
 * e-mail  : bafs.jy@live.com
 * time    : 2018/10/02
 * desc    : 过滤器配置
 * version : 1.0
 */
@Configuration
public class FilterConfiguration {

    @Resource
    private ApiSecurityFilter apiSecurityFilter;

    @Bean
    public FilterRegistrationBean<ApiSecurityFilter> apiSecurityFilterRegistration() {
        FilterRegistrationBean<ApiSecurityFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(apiSecurityFilter);
        registration.setName("apiSecurityFilter");

        // 拦截规则
        registration.addUrlPatterns("/user/*");
        registration.addUrlPatterns("/feed/*");
        registration.addUrlPatterns("/rss/*");
        registration.addUrlPatterns("/future/*");
        registration.addUrlPatterns("/topic/*");
        registration.addUrlPatterns("/message/*");

        registration.setOrder(2);
        return registration;
    }
}
