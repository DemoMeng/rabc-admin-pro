package com.hlw.common.response;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/20
 */
@Data
@Accessors(chain = true)
public class ResponseBean<T> implements Serializable {
    private static final long serialVersionUID = 7962517191525428151L;

    /**
     * 状态码值
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 默认成功
     */
    public ResponseBean(){
        this.code = ResponseEnum.SUCCESS.getCode();
        this.msg = ResponseEnum.SUCCESS.getMsg();
        this.data = null;
    }

    /**
     * 带参成功
     * @param msg
     */
    public ResponseBean(String msg){
        this.code = ResponseEnum.SUCCESS.getCode();
        this.msg = msg;
        this.data = null;
    }

    /**
     *
     * @param code
     * @param msg
     */
    public ResponseBean(Integer code,String msg){
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    /**
     *
     * @param code
     * @param msg
     * @param data
     */
    public ResponseBean(Integer code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     *
     * @return
     */
    public ResponseBean<T> SUCCESS(){
        this.code = ResponseEnum.SUCCESS.getCode();
        this.msg = ResponseEnum.SUCCESS.getMsg();
        return this;
    }

    /**
     *
     * @return
     */
    public ResponseBean<T> SUCCESS(T data){
        this.code = ResponseEnum.SUCCESS.getCode();
        this.msg = ResponseEnum.SUCCESS.getMsg();
        this.data = data;
        return this;
    }

    /**
     *
     * @return
     */
    public ResponseBean<T> SUCCESS(T obj,String msg){
        this.code = ResponseEnum.SUCCESS.getCode();
        this.msg = msg;
        this.data = obj;
        return this;
    }

    /**
     *
     * @return
     */
    public ResponseBean<T> ERROR(){
        this.code = ResponseEnum.ERROR.getCode();
        this.msg = ResponseEnum.ERROR.getMsg();
        return this;
    }

    /**
     *
     * @param msg
     * @return
     */
    public ResponseBean<T> ERROR(String msg){
        this.code = ResponseEnum.ERROR.getCode();
        this.msg = msg == null ? ResponseEnum.ERROR.getMsg() :msg;
        return this;
    }






}
