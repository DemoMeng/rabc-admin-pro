package com.hlw.common.valids;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mqz
 * @description 自定义验证
 * @since 2020/5/9
 */
@Constraint(validatedBy = {PhoneValid.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {

    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
