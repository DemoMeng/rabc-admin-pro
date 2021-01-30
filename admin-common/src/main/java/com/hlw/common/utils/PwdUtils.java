package com.hlw.common.utils;

import org.springframework.util.DigestUtils;

/**
 * @author mqz
 * @description
 * @since 2020/4/24
 */
public class PwdUtils {

    /**
     * 通过明文密码和盐值进行拼接生成密文
     * 用于验证或者生成
     * @param password
     * @param salt
     * @return
     */
    public static String getSecret(String password, String salt) {
        String key = salt+password;
        return DigestUtils.md5DigestAsHex((key.getBytes()));
    }
}
