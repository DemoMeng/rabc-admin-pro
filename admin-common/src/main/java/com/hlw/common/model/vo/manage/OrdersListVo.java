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
@ApiModel(description = "订单列表返回实体")
public class OrdersListVo implements Serializable {
    private static final long serialVersionUID = 5091505423541839716L;

    @ApiModelProperty(value = "个人id")
    private Long userId;
    @ApiModelProperty(value = "用户姓名")
    private String userName;
    @ApiModelProperty(value = "公司id")
    private Long companyId;
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "购买份数")
    private Long number;
    @ApiModelProperty(value = "购买价格")
    private BigDecimal price;
    @ApiModelProperty(value = "购买时间")
    private Date creatAt;
    @ApiModelProperty(value = "购买套餐类型")
    private String productName;
    @ApiModelProperty(value = "订单支付方式")
    private String payWay;
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    @ApiModelProperty(value = "账号")
    private String phone;
    @ApiModelProperty(value = "系统版本 0 基础版  1 专业版")
    private String status;


}
