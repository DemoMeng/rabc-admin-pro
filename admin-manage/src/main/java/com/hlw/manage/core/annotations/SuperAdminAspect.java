package com.hlw.manage.core.annotations;

import com.hlw.common.constants.CommonConstants;
import com.hlw.common.model.vo.system.CurrentRoleVo;
import com.hlw.manage.core.CurrentTools;
import com.hlw.manage.core.exception.MissingPermissionException;
import com.hlw.manage.core.exception.ServicesException;
import com.hlw.manage.core.redis.RedisService;
import com.hlw.manage.system.entity.HlwManageRole;
import com.hlw.manage.system.service.IHlwManageRoleService;
import com.hlw.manage.system.service.IHlwManageUserRoleService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//import com.hlw.manage.core.handler.DataSourceHandler;

/**
 * @author mqz
 * @description
 * @since 2020/5/7
 */
@Aspect
@Component
public class SuperAdminAspect {


    @Autowired
    private IHlwManageRoleService roleService;

    @Pointcut("@annotation(com.hlw.manage.core.annotations.SuperAdmin)")
    public void point() {
    }

    @Before("@annotation(superAdmin)")
    public void changeDataSource(JoinPoint point, SuperAdmin superAdmin) throws Throwable {
        Long currentId = CurrentTools.currentId();
        List<CurrentRoleVo> role = (List<CurrentRoleVo>) RedisService.get(CommonConstants.Current.CURRENT_ROLE+currentId);
        if(role == null){
            role = roleService.currentRole(currentId);
            //throw new MissingPermissionException("当前用户暂时没有角色");
        }
        if(role == null){
            throw new MissingPermissionException("当前用户暂时没有角色");
        }
        /**stream操作判断角色是否含有超级管理员*/
        long rs = role.stream().filter(obj -> obj.getId().intValue() == 1).count();
        if(rs == 0){
            throw new MissingPermissionException("当前用户不是超级管理员，无操作系统相关权限");
        }
    }

    @After("@annotation(superAdmin)")
    public void restoreDataSource(JoinPoint point, SuperAdmin superAdmin) {
    }

}
