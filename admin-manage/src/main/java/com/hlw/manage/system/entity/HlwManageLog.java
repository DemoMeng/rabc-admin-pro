package com.hlw.manage.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mqz
 * @since 2020-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HlwManageLog extends Model<HlwManageLog> {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作IP地址
     */
    private String ip;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 返回数据
     */
    private String responseData;

    /**
     * 请求接口地址
     */
    private String requestUri;

    /**
     * 操作方式
     */
    private String requestMethod;

    /**
     * 接口中文释义
     */
    private String description;

    /**
     * 操作人id
     */
    private Long operator;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 创建时间
     */
    private Date createDate;

    /**操作账号*/
    private String operateAccount;

    /**操作人角色*/
    private String operatorRole;

    /**结果*/
    private String result;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
