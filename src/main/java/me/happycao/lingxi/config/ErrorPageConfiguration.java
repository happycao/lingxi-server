package me.happycao.lingxi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author happyc
 * 自定义错误信息
 */
@Configuration
public class ErrorPageConfiguration {

    @Value("${server.servlet-path}")
    private String servletPath;

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, servletPath + "400"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, servletPath + "500"));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, servletPath + "404"));
        };
    }
}
