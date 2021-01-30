package com.hlw.manage.core.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/20
 */
@Data
public class WithoutLoginException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5395414930731458355L;

    private String msg;

    public WithoutLoginException(){

    }

    public WithoutLoginException(String msg){
        this.msg = msg;
    }
}
