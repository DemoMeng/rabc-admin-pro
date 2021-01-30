package com.hlw.common.model.dto.system;

import com.hlw.common.valids.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/4/26
 */
@Data
@ApiModel(description = "用户创建实体")
public class UserDto implements Serializable {

    private static final long serialVersionUID = -3379144787792482766L;

    /**用户id*/
    private Long userId;

    @NotNull(message = "姓名不能为空！")
    @ApiModelProperty(value = "姓名")
    private String name;

    @NotNull(message = "登录名不能为空！")
    @ApiModelProperty(value = "登录名")
    @Phone(message = "手机格式错误！")
    private String loginName;

    //@NotNull(message = "密码不能为空！")
    @ApiModelProperty(value = "密码")
    private String passWord;

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id")
    private List<Long> roleId;
}
