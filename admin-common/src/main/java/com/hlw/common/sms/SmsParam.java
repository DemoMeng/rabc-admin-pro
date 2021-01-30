package com.hlw.common.sms;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

/**
 * @author mqz
 * @description
 * @since 2020/4/30
 */
@Data
public class SmsParam {

    private static SmsParam smsParam;

    private final String appId = "XXXX";

    private final String appSecret = "XXXXXX";

    private String phone;

    private String msg;

    private SmsParam(){}

    public static SmsParam getInstance(String phone, String msg){
        if (smsParam == null) {
            smsParam = new SmsParam();
        }
        smsParam.phone = phone;
        smsParam.msg = msg;
        return smsParam;
    }




}
