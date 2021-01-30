package com.hlw.common.model.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/4/27
 */
@Data
@ApiModel(description = "子菜单列表")
public class MenuChildListVo implements Serializable {
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
    @ApiModelProperty(value = "归属类别 1：一级菜单 2：二级菜单 3：三级菜单 4：tab链  5：按钮")
    private Integer belongType;
    @ApiModelProperty(value = "前端路由")
    private String route;
    @ApiModelProperty(value = "子")
    private List<MenuChildListVo> child;

}
