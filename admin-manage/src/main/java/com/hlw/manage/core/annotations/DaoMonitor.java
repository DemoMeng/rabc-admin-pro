package com.hlw.manage.core.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author mqz
 * @date 2020/3/14 2:30 下午
 * @description
 */
@Aspect
@Component
public class DaoMonitor {

    //log4j中需要加入这个反射获取Logger对象，才能记录日志操作
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.hlw.manage.*.mapper..*.*(..))")
    public void daoLog() {
    }

    @Around("daoLog()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        String method = pjp.getSignature().toString();
        Long _startTime = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            Long _wasteTime = System.currentTimeMillis() - _startTime;
            if (_wasteTime > 50) {
                StringBuilder sb = new StringBuilder();
                sb.append("method=").append(method).append(",wasteTime=").append(_wasteTime);
                logger.info("@@@@@@@@@@@@@@@@@数据库接口请求的时间：（单位：毫秒/ms）@@@@@@@@@@@@@@@@@@@@@"+sb.toString());
            }
        }
    }
}
