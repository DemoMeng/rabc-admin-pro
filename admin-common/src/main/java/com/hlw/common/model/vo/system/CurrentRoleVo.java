package com.hlw.common.model.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/6/3
 */
@Data
@ApiModel(description = "当前登录用户所属角色返回实体")
public class CurrentRoleVo implements Serializable {


    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "名称")
    private String name ;


}
