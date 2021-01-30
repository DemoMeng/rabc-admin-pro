package com.hlw.manage.core.annotations;

import java.lang.annotation.*;

/**
 * @author mqz
 * @description 免登陆注解
 * @since 2020/4/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Tourist {
}
