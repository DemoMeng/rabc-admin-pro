package com.hlw.common.model.vo.open;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/7/17
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "服务策略列表返回实体")
public class ServicesStrategyListVo implements Serializable {
    private static final long serialVersionUID = -6721071672290996002L;

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "服务策略名称")
    private String name;
    @ApiModelProperty(value = "服务id")
    private Integer serviceId;
    @ApiModelProperty(value = "服务名称")
    private String serviceName;
    @ApiModelProperty(value = "策略名称")
    private String strategyName;
    @ApiModelProperty(value = "策略状态 0.未开启  1.已开启")
    private Integer status;

}
