package com.hlw.common.model.dto.open;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/7/17
 */
@Data
@Accessors(chain = true)
public class ServicesEditDto implements Serializable {
    private static final long serialVersionUID = 4062109020340026247L;

    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private Integer id;
    @ApiModelProperty(value = "接口名称")
    @NotEmpty(message = "接口名称不能为空")
    private String name;
    @ApiModelProperty(value = "接口分类")
    @NotNull(message = "接口分类不能为空")
    private Integer groupId;

}
