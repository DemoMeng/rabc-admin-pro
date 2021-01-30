package com.hlw.common.model.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/5/7
 */
@Data
@ApiModel(description = "角色名称，编号查重")
public class RoleCheckDto implements Serializable {
    private static final long serialVersionUID = 2778598010676852800L;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编号")
    private String no;
}
