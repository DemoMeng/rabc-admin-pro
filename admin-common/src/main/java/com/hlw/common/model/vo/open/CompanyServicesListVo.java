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
@ApiModel(description = "接口动态配置列表返回实体")
public class CompanyServicesListVo implements Serializable {

    private static final long serialVersionUID = 909790682463384914L;
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "公司id")
    private Integer companyId;
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "服务id")
    private Integer serviceId;
    @ApiModelProperty(value = "服务名称")
    private String serviceName;
    @ApiModelProperty(value = "服务策略表id")
    private String strategyId;
    @ApiModelProperty(value = "服务策略名称")
    private String strategyName;
    @ApiModelProperty(value = "服务策略class名")
    private String className;
    @ApiModelProperty(value = "状态0.关闭 1.正常")
    private Integer status;

}
