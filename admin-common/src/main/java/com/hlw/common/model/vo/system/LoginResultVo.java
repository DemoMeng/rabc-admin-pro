package com.hlw.common.model.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/6/4
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "登录返回实体")
public class LoginResultVo implements Serializable {

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "当前用户角色")
    private List<CurrentRoleVo> roles;

}
