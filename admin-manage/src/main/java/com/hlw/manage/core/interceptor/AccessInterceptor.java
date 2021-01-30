package com.hlw.manage.core.interceptor;

import com.hlw.manage.core.annotations.Tourist;
import com.hlw.manage.core.redis.RedisService;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author mqz
 * @description
 * @since 2020/4/20
 */
//@Configuration
public class AccessInterceptor implements HandlerInterceptor {
    /**
     * token过滤
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if(handler instanceof HandlerMethod){
            if(null == ((HandlerMethod) handler).getMethodAnnotation(Tourist.class)){
                String tokenKey = request.getHeader("token");
                if(StringUtils.isEmpty(tokenKey)){
                    returnJson(response,"{\"code\":433,\"msg\":\"token为空\"}");
                    return false;
                }
                String jwt = (String) RedisService.get(tokenKey);
                if(jwt == null){
                    returnJson(response,"{\"code\":434,\"msg\":\"token不存在\"}");
                    return false;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    /**
     * 返回json
     * @param response
     * @param json
     */
    private void returnJson(HttpServletResponse response, String json){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
        } finally {
            if (writer != null){
                writer.close();
            }
        }
    }




}
