package com.hlw.common.model.vo.system;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/4/26
 */
@Data
@Accessors(chain = true)
public class MenuTreeVo implements Serializable {

    private static final long serialVersionUID = 1630084617568093376L;

    /** 节点ID */
    private Long id;

    /** 节点父ID */
    private Long pId;

    /** 节点名称 */
    private String name;

    /** 是否勾选 */
    private boolean checked = false;

    /** 子级 */
    List<MenuTreeVo> sons;

    /** 节点标题 */
    /**private String title;*/

    /** 是否展开 */
    /**private boolean open = false;*/

    /** 是否能勾选 */
    /**private boolean nocheck = false;*/

}
