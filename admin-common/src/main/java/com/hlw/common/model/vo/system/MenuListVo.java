package com.hlw.common.model.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/4/27
 */
@Data
@ApiModel(description = "菜单列表")
public class MenuListVo implements Serializable {
    private static final long serialVersionUID = 7123206760241917638L;
    private Long id;
    @ApiModelProperty(value = "父级id")
    private Long parentId;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "链接")
    private String href;
    @ApiModelProperty(value = "目标")
    private String permission;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = " 所属类型 目录 或者是 菜单 ")
    private String type;
    @ApiModelProperty(value = "上级菜单名称")
    private String parentName;
    @ApiModelProperty(value = "归属类别 1：一级菜单 2：二级菜单 3：三级菜单 4：tab链  5：按钮")
    private Integer belongType;
    @ApiModelProperty(value = "前端路由")
    private String route;
}
