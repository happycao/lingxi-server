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

    @Value("${server.servlet-path}")
    private String servletPath;

    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(apiSecurityFilter);
        registration.setName("apiSecurityFilter");

        // 拦截规则
        registration.addUrlPatterns(servletPath + "/user/*");
        registration.addUrlPatterns(servletPath + "/feed/*");
        registration.addUrlPatterns(servletPath + "/rss/*");
        registration.addUrlPatterns(servletPath + "/future/*");
        registration.addUrlPatterns(servletPath + "/topic/*");
        registration.addUrlPatterns(servletPath + "/message/*");

        registration.setOrder(1);
        return registration;
    }
}
