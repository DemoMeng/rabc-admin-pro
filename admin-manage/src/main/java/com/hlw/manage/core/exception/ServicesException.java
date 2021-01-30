package com.hlw.manage.core.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/20
 */
@Data
public class ServicesException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8332058098331820277L;

    private String msg;

    public ServicesException(){

    }

    public ServicesException(String msg){
        this.msg = msg;
    }

}
