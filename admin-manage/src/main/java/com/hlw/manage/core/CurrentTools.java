package com.hlw.manage.core;

import com.aliyun.oss.ServiceException;
import com.hlw.common.constants.CommonConstants;
import com.hlw.common.model.vo.system.CurrentRoleVo;
import com.hlw.manage.core.exception.ServicesException;
import com.hlw.manage.core.exception.WithoutLoginException;
import com.hlw.manage.core.redis.RedisService;
import com.hlw.manage.core.token.JwtTools;
import com.hlw.manage.system.entity.HlwManageUser;
import com.hlw.manage.system.service.IHlwManageUserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mqz
 * @description 当前登录用户
 * @since 2020/4/21
 */
@Component
public class CurrentTools {

    public static Long currentId(){
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String tokenKey = request.getHeader("token");
        String jwt = (String)RedisService.get(tokenKey);
        Claims claims = JwtTools.toJwt(jwt);
        if(claims == null){
            throw new WithoutLoginException("用户未登录！");
        }
        return Long.valueOf(claims.getIssuer());
    }

    public static Long currentId(String token){
        if(StringUtils.isEmpty(token)){
            throw new WithoutLoginException("用户未登录！");
        }
        String jwt = (String)RedisService.get(token);
        Claims claims = JwtTools.toJwt(jwt);
        if(claims == null){
            throw new WithoutLoginException("用户未登录！");
        }
        return Long.valueOf(claims.getIssuer());
    }

    public static boolean out(){
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String tokenKey = request.getHeader("token");
        RedisService.del(tokenKey);
        String jwt = (String)RedisService.get(tokenKey);
        if(jwt == null){
            return true;
        }
        return false;
    }

    public static List<Long> currentRoleIdList(){
        Long currentId = CurrentTools.currentId();
        List<Long> currentRoleIdList = (List<Long>) RedisService.get(CommonConstants.Current.CURRENT_ROLE_ID_LIST+currentId);
        if(currentRoleIdList == null){
            throw new ServicesException("当前用户的角色集合不存在，请重新登录");
        }
        return currentRoleIdList;
    }


    public static HlwManageUser getCurrentUser(){
        HlwManageUser current = (HlwManageUser) RedisService.get(CommonConstants.Current.CURRENT_USER+CurrentTools.currentId());
        if(current == null){
            throw new ServiceException("登录已过期，请重新登录");
        }
        return current;
    }



}
