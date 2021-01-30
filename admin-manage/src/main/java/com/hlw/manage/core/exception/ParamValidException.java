package com.hlw.manage.core.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/30
 */
@Data
public class ParamValidException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -8171188744290010026L;

    private String msg;

    public ParamValidException(){}

    public ParamValidException(String msg){
        this.msg = msg;
    }
}
