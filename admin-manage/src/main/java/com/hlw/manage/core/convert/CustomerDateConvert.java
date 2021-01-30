package com.hlw.manage.core.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/4/22
 */
public class CustomerDateConvert implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if ("".equals(source) || source == null) {
                return null;
            }
            try {
                return formatTime.parse(source);
            } catch (Exception e) {
                try {
                    return formatDate.parse(source);
                } catch (Exception e1) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
