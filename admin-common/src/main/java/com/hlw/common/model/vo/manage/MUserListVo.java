package com.hlw.common.model.vo.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mqz
 * @description
 * @since 2020/5/12
 */
@Data
@ApiModel(description = "个人用户列表返回实体")
public class MUserListVo implements Serializable {
    private static final long serialVersionUID = -4078345855094243622L;

    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "用户姓名")
    private String userName;
    @ApiModelProperty(value = "用户账号")
    private String phone;
    @ApiModelProperty(value = "认证手机")
    private String identityPhone;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "注册时间")
    private String createAt;
    @ApiModelProperty(value = "剩余可用合同份数")
    private Long AllLeftCounts;
    @ApiModelProperty(value = "累计赠送次数")
    private Long AllFreeCounts;
    @ApiModelProperty(value = "累计购买次数")
    private Long AllBuyCounts;
    @ApiModelProperty(value = "累计消费金额")
    private BigDecimal AllConsumeCounts;


}
