package com.hlw.common.response;

import lombok.Data;

/**
 * @author mqz
 * @description
 * @since 2020/4/20
 */
public enum ResponseEnum {

    SUCCESS(200,"请求成功"),
    ERROR(500,"请求失败"),
    NOT_FOUND(404,"所请求的资源不存在")
    ;

    private Integer code;
    private String msg;

    ResponseEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
