package com.hlw.common.model.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/7/13
 */
@Data
@Accessors(chain = true)
public class GlobalConfigEditDto implements Serializable {
    private static final long serialVersionUID = -8493210118078081376L;

    @NotNull(message = "id不能为空")
    private Long id;
    @ApiModelProperty(value = "数量")
    @NotNull(message = "数量不能为空")
    private Integer num;
    @ApiModelProperty(value = "归属审核角色id")
    @NotNull(message = "归属审核角色id不能为空")
    private Long byRoleId;
    @ApiModelProperty(value = "启用状态 （ 0 ：启用  1：未启用）")
    @NotNull(message = "启用状态不能为空")
    private Integer status;

}
