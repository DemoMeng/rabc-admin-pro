package com.hlw.common.model.vo.system;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/5/6
 */
@Data
@Accessors(chain = true)
public class ForLog implements Serializable {
    private static final long serialVersionUID = -691014289267749270L;

    private Long currentId;

    /**操作账号*/
    private String operateAccount;

    /**操作结果*/
    private String result;

    /**操作名称*/
    private String operateName;

    /**操作人名称*/
    private String userName;

    /**角色名称*/
    private String roleName;

}
