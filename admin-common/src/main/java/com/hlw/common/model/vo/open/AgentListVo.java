package com.hlw.common.model.vo.open;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/5/14
 */
@Data
@ApiModel(description = "经销商列表返回实体")
public class AgentListVo implements Serializable {
    private static final long serialVersionUID = -6990946828713999414L;
    @ApiModelProperty(value = "id")
    private Long companyId;
    @ApiModelProperty(value = "经销商名称")
    private String companyName;
    @ApiModelProperty(value = "管理员")
    private String contactName;
    @ApiModelProperty(value = "所属行业")
    private String industry;
    @ApiModelProperty(value = "账号")
    private String account;
    @ApiModelProperty(value = "公司类型：0.一级 1.二级2.三级  3：非经销商公司（普通公司）")
    private String type;
    @ApiModelProperty(value = "提交日期")
    private Date createTime;
    @ApiModelProperty(value = "证书（签署）剩余")
    private int balance;
    @ApiModelProperty(value = "证书（签署）免费-剩余")
    private int giftBalance;
    @ApiModelProperty(value = "已购接口")
    private int serviceCount;
    @ApiModelProperty(value = "下级客户")
    private int childCount;
}
