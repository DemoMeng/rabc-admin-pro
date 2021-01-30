package com.hlw.common.model.vo.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/5/6
 */
@Data
public class LogListVo implements Serializable {

    private static final long serialVersionUID = 4439494461135019778L;

    /**id主键*/
    private Long id;

    /**操作名称*/
    private String description;

    /**操作人姓名*/
    private String operatorName;

    /**创建时间*/
    private Date createTime;

    /**操作账号*/
    private String operateAccount;

    /**操作人角色*/
    private String operatorRole;

    /**结果*/
    private String result;
}
