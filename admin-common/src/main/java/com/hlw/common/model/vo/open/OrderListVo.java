package com.hlw.common.model.vo.open;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/5/14
 */
@Data
@ApiModel(description = "订单列表返回实体")
public class OrderListVo implements Serializable {
    private static final long serialVersionUID = -6990946828713999414L;
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    @ApiModelProperty(value = "企业账号")
    private String account;
    @ApiModelProperty(value = "公司类型：0.一级 1.二级2.三级  3：非经销商公司（普通公司）")
    private String type;
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    @ApiModelProperty(value = "订单内容")
    private String orderDetail;
    @ApiModelProperty(value = "实付价格")
    private BigDecimal orderPrice;
    @ApiModelProperty(value = "下单时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "支付类型 0.线下打款 1.支付宝 2.微信")
    private int payType;
    @ApiModelProperty(value = "订单状态0.待支付 1.已支付")
    private int status;
    @ApiModelProperty(value = "等待审核状态 ： 0：不需要审核  1：等待审核  2:审核不通过 3：审核通过")
    private int auditStatus;
}
