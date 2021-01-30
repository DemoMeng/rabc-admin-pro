package com.hlw.common.model.vo.open;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/7/14
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "审核订单列表返回实体")
public class AuditOrderListVo implements Serializable {
    private static final long serialVersionUID = 7320881212395461967L;
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "订单内容")
    private String orderDetail;
    @ApiModelProperty(value = "申请人")
    private String applyName;
    @ApiModelProperty(value = "订单总价格")
    private BigDecimal orderPrice;
    @ApiModelProperty(value = "提交时间")
    private Date createTime;
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    @ApiModelProperty(value = "账号")
    private String account;

}
