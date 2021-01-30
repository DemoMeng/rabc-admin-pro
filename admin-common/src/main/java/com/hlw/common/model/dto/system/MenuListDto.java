package com.hlw.common.model.dto.system;

import com.hlw.common.model.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/27
 */
@Data
@ApiModel(description = "菜单列表实体")
public class MenuListDto extends CommonDto implements Serializable {

    private static final long serialVersionUID = 2706033945232173663L;

    @ApiModelProperty(value = "搜索关键字")
    private String keyword;

    @ApiModelProperty(value = "一级菜单：1，二级菜单:2 ,三级菜单:3  tab链:4  按钮:5")
    @NotNull(message = "类型不能为空！请参考接口文档传入正确参数")
    private Integer type;

}

