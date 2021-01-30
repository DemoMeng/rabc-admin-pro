package com.hlw.common.model.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/4/27
 */
@Data
@ApiModel(description = "登录成功后初始化用户所见菜单")
public class MenuInitVo implements Serializable {

    private static final long serialVersionUID = 7123206760241917638L;

    private Long id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "链接")
    private String href;
    @ApiModelProperty(value = "权限")
    private String permission;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "前端路由")
    private String route;
    @ApiModelProperty(value = "子集")
    private List<MenuInitVo> sons;

}
