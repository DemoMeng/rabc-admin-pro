package com.hlw.common.model.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/5/7
 */
@Data
@ApiModel(description = "用户启用禁用实体")
public class UserEnableDto implements Serializable {

    private static final long serialVersionUID = -2596347167101539165L;

    @NotNull
    @ApiModelProperty(value = "用户id")
    private Long id;

//    @NotNull
//    @ApiModelProperty(value = "启用或者禁用标识 off:禁用  on:启用")
//    private String flag;
}
