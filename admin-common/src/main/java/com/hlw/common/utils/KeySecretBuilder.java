package com.hlw.common.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mqz
 * @description 获取key和secret
 * @since 2020/7/16
 */
public class KeySecretBuilder {

    @Data
    @Accessors(chain = true)
    public static class KeySecret{
        private String key;
        private String secret;
    }

    public static KeySecret get(){
        KeySecret ks = new KeySecret()
                .setKey(KeyUtils.getKey(10))
                .setSecret(UuidUtils.getUuid());
        return ks;
    }

    public static void main(String[] args) {
        for(int i =0;i<55;i++){
            KeySecret ke = get();
            System.out.println(ke);
        }
    }

}
