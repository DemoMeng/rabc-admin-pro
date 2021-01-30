package com.hlw.manage.core.annotations;

import com.hlw.manage.core.CurrentTools;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.*;

/**
 * @author mqz
 * @description
 * @since 2020/5/7
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequirePermission {
    String value() default "接口权限校验";
    String permission();
}
