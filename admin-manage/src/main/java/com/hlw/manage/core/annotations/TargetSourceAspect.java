package com.hlw.manage.core.annotations;

import com.hlw.manage.core.CurrentTools;
import com.hlw.manage.core.exception.DataSourceNotExistException;
import com.hlw.manage.core.handler.DataSourceHandler;
import com.hlw.manage.system.service.IHlwManageUserRoleService;
import com.hlw.manage.system.service.IHlwManageUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mqz
 * @description
 * @since 2020/4/27
 * @Order(-10) //保证该AOP在@Transactional之前执行
 */
@Aspect
@Order(-10)
@Component
public class TargetSourceAspect {

    private static Logger logger = LoggerFactory.getLogger(TargetSourceAspect.class);

    @Pointcut("@annotation(com.hlw.manage.core.annotations.TargetSource)")
    public void point() {
    }

    @Before("@annotation(targetSource)")
    public void changeDataSource(JoinPoint point, TargetSource targetSource) throws Throwable {
        /**获取当前的指定的数据源;*/
        String dsId = targetSource.value();
        /**如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。*/
        if (!DataSourceHandler.containsDataSource(dsId)) {
            logger.info("DataSource [{"+dsId+"}] Don't Exist，Use the default DataSource > " + targetSource.value() + point.getSignature());
            throw new DataSourceNotExistException("数据源{ "+dsId+" }不存在");
        } else {
            logger.info("Current Use DataSource : {"+dsId+"} > {"+dsId+"}" + targetSource.value() + point.getSignature());
            /**找到的话，那么设置到动态数据源上下文中。*/
            DataSourceHandler.setDataSourceRouterKey(targetSource.value());
        }
    }


    @After("@annotation(targetSource)")
    public void restoreDataSource(JoinPoint point, TargetSource targetSource) {
        logger.info("Remove The DataSource : { } > " + targetSource.value() + point.getSignature());
        DataSourceHandler.removeDataSourceRouterKey();
    }

}
