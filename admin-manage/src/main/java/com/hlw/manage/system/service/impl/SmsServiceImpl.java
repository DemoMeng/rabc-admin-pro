package com.hlw.manage.system.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hlw.common.constants.CommonConstants;
import com.hlw.common.model.dto.system.SendSmsDto;
import com.hlw.common.response.ResponseBean;
import com.hlw.common.sms.SmsParam;
import com.hlw.common.utils.OkHttpUtils;
import com.hlw.manage.core.exception.ServicesException;
import com.hlw.manage.core.redis.RedisService;
import com.hlw.manage.system.service.SmsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Random;

/**
 * @author mqz
 * @description 发送验证码
 * @since 2020/5/21
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public ResponseBean sendMessage(SendSmsDto dto) {
        int code = new Random().nextInt(99999) + 10000;
        String msg;
        String key = null;
        switch (dto.getType()) {
            case 1:
                msg = CommonConstants.Sms.LOGIN_START + code + CommonConstants.Sms.LOGIN_END;
                key = CommonConstants.Sms.KEY_LOGIN+dto.getPhone();
                break;
            case 2:
                msg = CommonConstants.Sms.RE_PASSWORD_START + code + CommonConstants.Sms.RE_PASSWORD_END;
                key = CommonConstants.Sms.KEY_RE_PASSWORD+dto.getPhone();
                break;
            case 3:
                msg =CommonConstants.Sms.INIT_ACCOUNT_SAAS_START + dto.getPhone() + CommonConstants.Sms.INIT_ACCOUNT_SAAS_MIDDLE+dto.getPassword()+CommonConstants.Sms.INIT_ACCOUNT_SAAS_END;
                break;
            case 4:
                msg =CommonConstants.Sms.INIT_ACCOUNT_OPEN_START + dto.getPhone() + CommonConstants.Sms.INIT_ACCOUNT_OPEN_MIDDLE+dto.getPassword()+CommonConstants.Sms.INIT_ACCOUNT_OPEN_END;
                break;
            default:
                throw new ServicesException("验证码类型不能为空");
        }
         SmsParam param = SmsParam.getInstance(dto.getPhone(),msg);
         JSONObject json = JSONUtil.parseObj(param,true);
         String result;
         try {
            result = OkHttpUtils.post(CommonConstants.Sms.API,json.toString());
         } catch (IOException e) {
             e.printStackTrace();
             throw new ServicesException("验证码发送异常，请重试");
         }
         if(StringUtils.isEmpty(result)){
            throw new ServicesException("验证码发送异常，请重试");
         }
         JSONObject resultJSON = new JSONObject(result);
         String resultCode = resultJSON.getStr("code");
         if(!"0".equals(resultCode)){
             return new ResponseBean().ERROR("验证码发送失败，请重试");
         }
         if(key != null){
             RedisService.set(key,String.valueOf(code),CommonConstants.Sms.EXPIRED_TIME);
         }
        return new ResponseBean().SUCCESS(null,"验证码发送成功");
    }
}
