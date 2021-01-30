package com.hlw.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/26
 */
@Data
@ApiModel(value = "通用请求实体",description = "通用请求实体")
public class CommonDto implements Serializable {

    private static final long serialVersionUID = 6713962186978248149L;

    /** 每页数值 */
    @ApiModelProperty(value = "每页显示数量")
    private Integer pageSize = 10;

    /** 页码 */
    @ApiModelProperty(value = "页码")
    private Integer pageCurrent = 1;

}
