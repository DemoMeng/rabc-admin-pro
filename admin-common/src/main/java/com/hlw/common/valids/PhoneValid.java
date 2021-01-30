package com.hlw.common.valids;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mqz
 * @description
 * @since 2020/5/9
 */
public class PhoneValid implements ConstraintValidator<Phone, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String phone = "";
        if(value != null){
            phone = (String)value;
        }
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        context.disableDefaultConstraintViolation();
        if (phone.length() != 11) {
            /**重新添加错误提示语句*/
            context.buildConstraintViolationWithTemplate("手机号位数需为11位！").addConstraintViolation();
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                context.buildConstraintViolationWithTemplate("请输入正确格式的手机号！").addConstraintViolation();
            }
            return isMatch;
        }
    }
}
