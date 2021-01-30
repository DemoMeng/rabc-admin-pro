package com.hlw.manage.core.confg;

import com.hlw.manage.core.interceptor.AccessInterceptor;
import com.hlw.manage.core.params.ParameterFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/4/20
 */
@Configuration
public class MyWebConfiguration implements WebMvcConfigurer {

//    @Bean
//    public AccessInterceptor accessInterceptor() {
//         return new AccessInterceptor();
//    }

    //@Order
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> ex = new ArrayList<>();
        ex.add("/doc.html");
        ex.add("/swagger-ui.html/**");
        ex.add("/swagger-resources/**");
        ex.add("v2/**");
        ex.add("/api-docs");
        registry.addInterceptor(new AccessInterceptor()).addPathPatterns("/**").excludePathPatterns(ex);
    }

//    @Bean
//    public FilterRegistrationBean parameterFilter() {
//        FilterRegistrationBean filter = new FilterRegistrationBean();
//        filter.setFilter(new ParameterFilter());
//        filter.addUrlPatterns("/*");
//        filter.setName("ParameterFilter");
//        filter.setDispatcherTypes(DispatcherType.REQUEST);
//        return filter;
//    }

}
