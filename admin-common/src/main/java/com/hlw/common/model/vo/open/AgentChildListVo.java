package com.hlw.common.model.vo.open;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mqz
 * @description
 * @since 2020/5/18
 */
@Data
@ApiModel(description = "经销商下级客户返回实体")
public class AgentChildListVo implements Serializable {

    private static final long serialVersionUID = -2150433455921698438L;

    @ApiModelProperty(value = "公司id")
    private Long companyId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "管理员")
    private String contractName;

    @ApiModelProperty(value = "累计消费金额")
    private BigDecimal consumeAmount;

    @ApiModelProperty(value = "接口数量")
    private Integer serviceCount;






}
