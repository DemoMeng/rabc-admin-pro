package com.hlw.manage.core.annotations;

import java.lang.annotation.*;

/**
 * @author mqz
 * @description
 * @since 2020/5/9
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SuperAdmin {

    String value() default "超级管理员拥有该权限";

}
