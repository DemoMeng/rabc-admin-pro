package com.hlw.manage.core.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/5/9
 */
@Data
public class MissingPermissionException extends RuntimeException implements Serializable {


    private static final long serialVersionUID = -5591922722863423087L;

    String msg;

    public MissingPermissionException(){

    }

    public MissingPermissionException(String msg){
        this.msg = msg;
    }

}
