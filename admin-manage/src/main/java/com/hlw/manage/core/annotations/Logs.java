package com.hlw.manage.core.annotations;

import com.hlw.common.constants.CommonConstants;

import java.lang.annotation.*;

/**
 * @author mqz
 * @description
 * @since 2020/4/21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logs {

    String value() default "操作日志";

}
