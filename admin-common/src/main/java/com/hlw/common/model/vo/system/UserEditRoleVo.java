package com.hlw.common.model.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/26
 */
@Data
public class UserEditRoleVo implements Serializable {
    private static final long serialVersionUID = -1046208214551110002L;

    /** 角色id */
    private Long roleId;

    /** 是否被选中 */
    private boolean checked = false;
}
