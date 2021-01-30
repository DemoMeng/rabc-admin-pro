package com.hlw.common.model.vo.open;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author mqz
 * @description
 * @since 2020/5/14
 */
@Data
@ApiModel(description = "api管理列表返回实体")
public class ServicesListVo implements Serializable {
    private static final long serialVersionUID = 3585177993701780861L;
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "接口名称")
    private String name;
    @ApiModelProperty(value = "分组id")
    private String groupId;
    @ApiModelProperty(value = "分组名称")
    private String groupName;
    @ApiModelProperty(value = "接口描述")
    private String descript;
    @ApiModelProperty(value = "服务状态0.未上架 1.已上架")
    private int status;
}
