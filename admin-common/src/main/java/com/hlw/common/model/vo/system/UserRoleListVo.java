package com.hlw.common.model.vo.system;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/7/13
 */
@Data
@Accessors(chain = true)
public class UserRoleListVo implements Serializable {
    private static final long serialVersionUID = 5340908163843614819L;

    /**所属角色id*/
    private Long roleId;

    /**所属角色*/
    private String roleName;
}
