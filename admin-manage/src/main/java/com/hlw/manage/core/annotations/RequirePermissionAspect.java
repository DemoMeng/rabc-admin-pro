package com.hlw.manage.core.annotations;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hlw.common.constants.CommonConstants;
import com.hlw.manage.core.CurrentTools;
import com.hlw.manage.core.exception.MissingPermissionException;
import com.hlw.manage.core.exception.ServicesException;
//import com.hlw.manage.core.handler.DataSourceHandler;
import com.hlw.manage.core.redis.RedisService;
import com.hlw.manage.system.entity.HlwManageRole;
import com.hlw.manage.system.service.IHlwManageUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author mqz
 * @description
 * @since 2020/5/7
 */
@Slf4j
@Aspect
@Order(-11)
@Component
public class RequirePermissionAspect {

    @Autowired
    private IHlwManageUserRoleService urService;

    @Pointcut("@annotation(com.hlw.manage.core.annotations.RequirePermission)")
    public void point() {
    }

    @Before("@annotation(requirePermission)")
    public void changeDataSource(JoinPoint point, RequirePermission requirePermission) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map<String,String[]> ap  = request.getParameterMap();
        //String url = request.getRequestURL().toString();
        String permission = requirePermission.permission();
        boolean flag = urService.isPermission(permission);
        if(!flag){
            throw new ServicesException("当前用户没有该接口权限（🈲）");
        }
    }


    @After("@annotation(requirePermission)")
    public void restoreDataSource(JoinPoint point, RequirePermission requirePermission) {
    }

}

