package com.hlw.manage.core.annotations;

/**
 * @author mqz
 * @description
 * @since 2020/4/21
 */

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hlw.common.constants.CommonConstants;
import com.hlw.common.model.vo.system.ForLog;
import com.hlw.common.utils.AddressUtils;
import com.hlw.common.utils.WebCommonUtils;
import com.hlw.manage.core.CurrentTools;
import com.hlw.manage.system.entity.HlwManageLog;
import com.hlw.manage.system.service.IHlwManageLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
@Aspect
@Component
public class LogsAspect {

    @Autowired
    private IHlwManageLogService logService;

    private HlwManageLog hlwManageLog;


    @Pointcut("@annotation(com.hlw.manage.core.annotations.Logs)")
    public void pointCuts() {
    }

    @Before("pointCuts()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        hlwManageLog = new HlwManageLog()
                .setIp(WebCommonUtils.getIpAddr())
                .setRequestMethod(request.getMethod())
                .setCreateDate(new Date())
                .setRequestUri(request.getRequestURL().toString());
    }

    @AfterReturning(pointcut = "pointCuts()",returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
        Long currentId = CurrentTools.currentId();
        JSONObject jsonObject = JSONUtil.parseObj(ret);
        ForLog log = jsonObject.getBean("data", ForLog.class);
        hlwManageLog.setResponseData(jsonObject.getStr("data"))
                .setOperator(currentId)
                .setOperatorName(log.getUserName())
                .setRequestParams(WebCommonUtils.getRequestParams())
                .setOperateAccount(log.getOperateAccount())
                .setOperatorRole(log.getRoleName())
                .setDescription(log.getOperateName())
                .setResult(log.getResult());
        this.saveLogs();
    }

    public void saveLogs(){
        logService.save(hlwManageLog);
    }



}
