package com.hlw.manage.core.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author mqz
 * @date 2020/4/16
 * @description
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{

    @Value("${swagger.enable}")
    private boolean enable;


    @Bean(name = "manage")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo()).groupName("manage")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hlw.manage.manage"))
                .paths(PathSelectors.ant("/manage/**"))
                .build();
    }

    @Bean(name="open")
    public Docket createRestApiOther() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo()).groupName("open")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hlw.manage.open"))
                .paths(PathSelectors.ant("/open/**"))
                .build();
    }

    @Bean(name="system")
    public Docket createRestApiSystem() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo()).groupName("system")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hlw.manage.system"))
                .paths(PathSelectors.ant("/system/**"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("在线接口文档")
                .description("蒙大拿")
                .license("copyRight@mqz")
                .description("接口描述文档")
                .termsOfServiceUrl("http://localhost:9999/doc.html")
                .contact("13128507506")
                .version("1.0")
                .build();
    }

}
