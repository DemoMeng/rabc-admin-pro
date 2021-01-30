package com.hlw.common.model.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @author mqz
 * @description
 * @since 2020/5/6
 */
@Data
@ApiModel(description = "用户列表返回实体")
public class UserListVo implements Serializable {
    private static final long serialVersionUID = -3095295900352802259L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "账号")
    private String loginName;

    //private List<UserRoleListVo> roles;
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "是否启用 0：启用 1：不启用")
    private Integer enable;



}
