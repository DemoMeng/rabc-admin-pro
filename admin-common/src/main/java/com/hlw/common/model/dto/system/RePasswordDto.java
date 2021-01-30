package com.hlw.common.model.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/30
 */
@Data
@ApiModel(description = "修改密码实体")
public class RePasswordDto implements Serializable {

    private static final long serialVersionUID = 1686457832490576773L;

    @NotNull(message = "登录名不能为空")
    @ApiModelProperty(value = "登录名")
    public String loginName;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @NotNull(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码")
    private String code;
}
