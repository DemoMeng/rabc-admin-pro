package com.hlw.common.utils;

import java.util.UUID;

/**
 * @author mqz
 * @description
 * @since 2020/4/24
 */
public class UuidUtils {

    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
