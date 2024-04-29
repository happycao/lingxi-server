package me.happycao.lingxi;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author happyc
 */
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@EnableScheduling
@MapperScan(basePackages = {"me.happycao.lingxi.mapper", "me.happycao.lingxi.dao"})
public class LxApplication {

    public static void main(String[] args){
        SpringApplication ap = new SpringApplication(LxApplication.class);
        ap.setBannerMode(Banner.Mode.OFF);
        ap.run(args);
    }
}
