package com.hlw.manage.core.exception;

import com.hlw.common.enums.EnumExceptions;
import com.hlw.common.response.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.concurrent.TimeoutException;

/**
 * @author mqz
 * @description
 * @since 2020/4/20
 */
@ControllerAdvice
public class GlobalException {

    private final static Logger logger = LoggerFactory.getLogger(GlobalException.class);


    /**
     * 常规异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseBean error(Exception e){
        e.printStackTrace();
        logger.error(EnumExceptions.EXCEPTION.getMsg());
        return new ResponseBean().ERROR(EnumExceptions.EXCEPTION.getMsg());
    }


    /**
     * 空指针异常
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResponseBean error(NullPointerException e){
        e.printStackTrace();
        logger.error(EnumExceptions.NULL_POINT.getMsg());
        return new ResponseBean().ERROR(EnumExceptions.NULL_POINT.getMsg());
    }

    /**
     * 请求方式不予许
     * @param e
     * @return
     */
    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseBody
    public ResponseBean error(MethodNotAllowedException e){
        e.printStackTrace();
        logger.error(EnumExceptions.METHOD_NOT_ALLOW.getMsg());
        return new ResponseBean().ERROR(EnumExceptions.METHOD_NOT_ALLOW.getMsg());
    }

    /**
     * 请求方式不予许
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseBean error(HttpRequestMethodNotSupportedException e){
        e.printStackTrace();
        logger.error(EnumExceptions.METHOD_NOT_ALLOW.getMsg());
        return new ResponseBean().ERROR(EnumExceptions.METHOD_NOT_ALLOW.getMsg());
    }


    /**
     * 请求超时
     * @param e
     * @return
     */
    @ExceptionHandler(TimeoutException.class)
    @ResponseBody
    public ResponseBean error(TimeoutException e){
        e.printStackTrace();
        logger.error(EnumExceptions.TIMEOUT.getMsg());
        return new ResponseBean().ERROR(EnumExceptions.TIMEOUT.getMsg());
    }


    /**
     * 缺失参数
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseBean error(MissingServletRequestParameterException e){
        e.printStackTrace();
        logger.error(EnumExceptions.MISSING_PARAMETER.getMsg());
        return new ResponseBean().ERROR(EnumExceptions.MISSING_PARAMETER.getMsg());
    }



    /********************自定义异常***************************/

    /**
     * 自定义未登录异常
     * @param e
     * @return
     */
    @ExceptionHandler(WithoutLoginException.class)
    @ResponseBody
    public ResponseBean error(WithoutLoginException e){
        e.printStackTrace();
        logger.error(e.getMsg() == null ? EnumExceptions.WITHOUT_LOGIN.getMsg() :e.getMsg());
        return new ResponseBean().ERROR(e.getMsg() == null ? EnumExceptions.WITHOUT_LOGIN.getMsg() :e.getMsg());
    }


    /**
     * 自定义业务异常类
     * @param e
     * @return
     */
    @ExceptionHandler(ServicesException.class)
    @ResponseBody
    public ResponseBean error(ServicesException e){
        e.printStackTrace();
        logger.error(e.getMsg() == null ? EnumExceptions.SERVICES.getMsg() :e.getMsg());
        return new ResponseBean().ERROR(e.getMsg() == null ? EnumExceptions.SERVICES.getMsg() :e.getMsg());
    }


    /**
     * 数据源不存在
     * @param e
     * @return
     */
    @ExceptionHandler(DataSourceNotExistException.class)
    @ResponseBody
    public ResponseBean error(DataSourceNotExistException e){
        e.printStackTrace();
        logger.error(e.getMsg() == null? EnumExceptions.DATA_SOURCE_NOT_EXIST.getMsg() :e.getMsg());
        return new ResponseBean().ERROR(e.getMsg() == null? EnumExceptions.DATA_SOURCE_NOT_EXIST.getMsg() :e.getMsg());
    }

    /**
     * 参数异常
     * @param e
     * @return
     */
    @ExceptionHandler(ParamValidException.class)
    @ResponseBody
    public ResponseBean error(ParamValidException e){
        e.printStackTrace();
        logger.error(e.getMsg() == null? EnumExceptions.PARAM_VALID.getMsg() : e.getMsg());
        return new ResponseBean().ERROR(e.getMsg() == null? EnumExceptions.PARAM_VALID.getMsg() : e.getMsg());
    }

    /**
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MissingPermissionException.class)
    @ResponseBody
    public ResponseBean error(MissingPermissionException e){
        e.printStackTrace();
        logger.error(e.getMsg() == null? EnumExceptions.MISSING_PERMISSION.getMsg() : e.getMsg());
        return new ResponseBean().ERROR(e.getMsg() == null? EnumExceptions.MISSING_PERMISSION.getMsg() : e.getMsg());
    }

    /**
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseBean error(HttpMessageNotReadableException e){
        e.printStackTrace();
        logger.error(e.getMessage() == null? EnumExceptions.JSON_EXCEPTION.getMsg() : e.getMessage());
        return new ResponseBean().ERROR(e.getMessage() == null? EnumExceptions.JSON_EXCEPTION.getMsg() : e.getMessage());
    }

    /**
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ResponseBean error(HttpMediaTypeNotSupportedException e){
        e.printStackTrace();
        logger.error(e.getMessage() == null? EnumExceptions.HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION.getMsg() : e.getMessage());
        return new ResponseBean().ERROR(e.getMessage() == null? EnumExceptions.HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION.getMsg() : e.getMessage());
    }








}
