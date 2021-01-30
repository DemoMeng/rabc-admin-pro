package com.hlw.common.model.vo.system;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/5/7
 */
@Data
public class RoleEditVo implements Serializable {

    private static final long serialVersionUID = -2054424329686269342L;

    /**角色id*/
    private Long id;

    /**岗位编号*/
    private String no;

    /**岗位名称*/
    private String name;

    /**菜单权限选择*/
    private List<RoleMenuEchoVo> roleMenu;

}
