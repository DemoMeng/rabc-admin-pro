package com.hlw.common.model.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/24
 */
@Data
@ApiModel(description = "登录实体")
public class LoginDto implements Serializable {

    private static final long serialVersionUID = -386692092177684749L;

    @NotNull(message = "账号不能为空")
    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String passWord;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "登录方式  pwd: 密码登录 sms验证码登录")
    @NotNull(message = "登录方式不能为空")
    private String type;
}
