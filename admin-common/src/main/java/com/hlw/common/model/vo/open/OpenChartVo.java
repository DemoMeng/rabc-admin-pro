package com.hlw.common.model.vo.open;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/5/27
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "open图标统计返回实体")
public class OpenChartVo implements Serializable {

    private static final long serialVersionUID = -8544631824072052698L;

    @ApiModelProperty(value = "类型区分： register:新增注册 ；auth：新增实名 ；finish 成交单 ；done ：成交金额")
    private String type;

    @ApiModelProperty(value = "子")
    private List<ChartVo> res;

}
