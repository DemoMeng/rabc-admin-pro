package com.hlw.common.model.vo.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/5/7
 */
@Data
@Accessors(chain = true)
public class RoleMenuEchoVo implements Serializable {

    private static final long serialVersionUID = -2898532104082619491L;

    @ApiModelProperty(value = "节点ID")
    private Long id;
    @ApiModelProperty(value = "节点父ID")
    private Long pId;
    @ApiModelProperty(value = "节点名称")
    private String name;
    @ApiModelProperty(value = "是否勾选")
    private boolean checked = false;
    @ApiModelProperty(value = "归属类别 1：一级菜单 2：二级菜单 3：三级菜单 4:tab链  5：按钮")
    private Integer belongType;
    @ApiModelProperty(value = "子级")
    List<RoleMenuEchoVo> sons;

}
