package com.hlw.manage.system.service;

import com.hlw.common.model.dto.system.SendSmsDto;
import com.hlw.common.response.ResponseBean;

/**
 * @author mqz
 * @description
 * @since 2020/5/21
 */
public interface SmsService {

    /**
     * 发送验证码
     * @param dto
     * @return
     */
    ResponseBean sendMessage(SendSmsDto dto);
}
