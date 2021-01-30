package com.hlw.manage.core.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mqz
 * @description
 * @since 2020/4/20
 */
//@Slf4j
//@Component
//public class SwaggerInterceptor implements HandlerInterceptor {
//
//    private Boolean disabledSwagger = false;
//
//    private String redirectUri = "http://127.0.0.1:9999/";
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        if(!disabledSwagger){
//            return Boolean.TRUE;
//        }
//        String uri = redirectUri;
//        if(uri == null || uri.trim().length() == 0)
//        {
//            uri = "/";
//        }
//        try {
//            response.sendRedirect(uri);
//        } catch (IOException e) {
//            log.error(String.format("Redirect to '%s' for swagger throw an exception : %s", uri, e.getMessage()), e);
//        }
//        return Boolean.FALSE;
//    }
//
//}
