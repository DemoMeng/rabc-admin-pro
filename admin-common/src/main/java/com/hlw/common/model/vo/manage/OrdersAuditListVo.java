package com.hlw.common.model.vo.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/5/13
 */
@Data
@ApiModel(description = "订单审核列表返回实体")
public class OrdersAuditListVo implements Serializable {
    private static final long serialVersionUID = 2902024229693454722L;
    @ApiModelProperty(value = "申请人")
    private String applyName;
    @ApiModelProperty(value = "购买份数")
    private Long number;
    @ApiModelProperty(value = "购买价格")
    private BigDecimal price;
    @ApiModelProperty(value = "购买时间")
    private Date creatAt;
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "账号")
    private String phone;
    @ApiModelProperty(value = "等待审核状态 ： 0：不需要审核  1：等待审核")
    private String auditStatus;
}
