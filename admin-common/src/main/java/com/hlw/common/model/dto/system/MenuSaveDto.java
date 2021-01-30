package com.hlw.common.model.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mqz
 * @description
 * @since 2020/4/27
 */
@Data
@ApiModel(description = "菜单保存实体")
public class MenuSaveDto implements Serializable {
    private static final long serialVersionUID = -7157483020777721302L;

    /**id*/
    @ApiModelProperty(value = "id")
    private Long id;

    /**归属类别*/
    @ApiModelProperty(value = "父级id")
    @NotNull(message = "请传入父级id")
    private Long parentId;

    /**归属类别*/
    @ApiModelProperty(value = "归属类别 1：一级菜单 2：二级菜单 3：三级菜单 4：tab链  5：按钮")
    @NotNull(message = "请传入归属类别")
    private Integer belongType;

    /**名称*/
    @ApiModelProperty(value = "名称")
    @NotNull(message = "请输入名称")
    private String name;

    /**排序*/
    @ApiModelProperty(value = "排序")
    private BigDecimal sort;

    /**链接*/
    @ApiModelProperty(value = "链接地址")
    private String href;

    /**权限*/
    @ApiModelProperty(value = "权限")
    private String permission;

    /**图标*/
    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "前端路由")
    @NotNull(message = "请输入前端路由")
    private String route;

}
