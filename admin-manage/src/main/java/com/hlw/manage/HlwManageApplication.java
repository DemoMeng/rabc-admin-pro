package com.hlw.manage;


import com.hlw.manage.core.handler.DataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.Executor;

/**
 * @author mqz
 * @since 2020年04月20日
 * @description
 */
@Configuration
@EnableAutoConfiguration
@MapperScan(basePackages = {"com.hlw.manage.**.mapper.**"})
@ComponentScan(basePackages = {"com.hlw.manage"})
@Import({DataSourceRegister.class})
@EnableSwagger2
@EnableAsync
@EnableScheduling
//@ServletComponentScan(basePackages = "com.hlw.manage.core.params")
public class HlwManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HlwManageApplication.class, args);
    }

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }


}
