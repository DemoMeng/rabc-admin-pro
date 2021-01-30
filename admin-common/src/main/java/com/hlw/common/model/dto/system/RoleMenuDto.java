package com.hlw.common.model.dto.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/4/26
 */
@Data
@ApiModel(description = "角色菜单保存实体")
public class RoleMenuDto implements Serializable {
    private static final long serialVersionUID = 6569720587920181852L;

    @ApiModelProperty(value = "角色id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "编号")
    private String no;

    /** 菜单id */
    @ApiModelProperty(value = "菜单id集合")
    private List<String> ids;

}
