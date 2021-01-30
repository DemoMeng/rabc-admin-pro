package com.hlw.common.model.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/30
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "发送验证码实体")
public class SendSmsDto implements Serializable {

    private static final long serialVersionUID = 5814085604312309610L;

    @NotNull(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @NotNull(message = "验证码类别不能为空")
    @ApiModelProperty(value = "验证码类别，1：登录，2：找回密码")
    private int type;

    private String password;
}
