package com.hlw.common.model.vo.open;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/7/15
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "已购接口详情返回实体")
public class CustomerApiDetailVo implements Serializable {

    private static final long serialVersionUID = -5303728246415456191L;
    @ApiModelProperty(value = "公司id")
    private Integer companyId;
    @ApiModelProperty(value = "接口id")
    private Integer serviceId;
    @ApiModelProperty(value = "接口名称")
    private String serviceName;
    @ApiModelProperty(value = "余额")
    private Integer balance;
    @ApiModelProperty(value = "赠送余额")
    private Integer giftBalance;
    @ApiModelProperty(value = "开通时间")
    private Date createTime;
    @ApiModelProperty(value = "到期时间")
    private Date expireTime;


}
