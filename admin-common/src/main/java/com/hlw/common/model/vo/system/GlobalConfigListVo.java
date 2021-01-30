package com.hlw.common.model.vo.system;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/7/13
 */
@Data
@Accessors(chain = true)
public class GlobalConfigListVo implements Serializable {
    private static final long serialVersionUID = -8955498775173448131L;
    private Long id;
    @ApiModelProperty(value = "归类名称")
    private String name;
    @ApiModelProperty(value = "第一次审核门槛")
    private Integer levelBelong;
    @ApiModelProperty(value = "第一次审核门槛")
    private int num;
    @ApiModelProperty(value = "归属审核角色id")
    private Long byRoleId;
    @ApiModelProperty(value = "区别标识 （接口赠送：API  合同赠送 ：CONTRACT）")
    private String type;
    @ApiModelProperty(value = "启用状态 （ 0 ：启用  1：未启用）")
    private Integer status;

}
