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
@ApiModel(description = "open图标统计返回实体-子")
public class ChartVo implements Serializable {

    private static final long serialVersionUID = -8544631824072052698L;

    @ApiModelProperty(value = "日期")
    private String date;

    @ApiModelProperty(value = "统计")
    private Integer count;
}
