package com.hlw.common.model.vo.open;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/5/27
 */
@Data
@ApiModel(description = "图标统计返回实体")
public class CustomerApiCallVo implements Serializable {

    private static final long serialVersionUID = 7855818852242297565L;
    @ApiModelProperty(value = "日期")
    private String date;
    @ApiModelProperty(value = "统计")
    private Integer count;
}
