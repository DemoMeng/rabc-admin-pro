package com.hlw.manage.core.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/28
 */
@Data
public class DataSourceNotExistException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2522728131469045613L;

    private String msg;

    public DataSourceNotExistException(){}

    public DataSourceNotExistException(String msg){
        this.msg = msg;
    }


}
