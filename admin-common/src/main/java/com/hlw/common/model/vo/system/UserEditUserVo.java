package com.hlw.common.model.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/26
 */
@Data
public class UserEditUserVo implements Serializable {
    private static final long serialVersionUID = 2975760794363718782L;
    /**用户id*/
    private Long id;
    /**用户名*/
    private String name;
    /**登录名*/
    private String longName;
    /**电话*/
    private String phone;
    /**手机号*/
    private String mobile;
    /**工号*/
    private String no;
}
