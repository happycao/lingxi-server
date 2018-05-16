package me.happycao.lingxi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = {"me.happycao.lingxi.mapper", "me.happycao.lingxi.dao"})
public class Application {

    public static void main(String[] args){
        SpringApplication ap = new SpringApplication(Application.class);
        ap.setBannerMode(Banner.Mode.OFF);
        ap.run(args);
    }
}
