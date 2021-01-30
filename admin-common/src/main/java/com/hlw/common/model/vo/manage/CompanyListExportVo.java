package com.hlw.common.model.vo.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/5/12
 */
@Data
@ApiModel(description = "企业用户列表返回实体")
public class CompanyListExportVo implements Serializable {
    private static final long serialVersionUID = -8170740422875616910L;

    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "管理员")
    private String manager;
    @ApiModelProperty(value = "账号")
	private String account;
    @ApiModelProperty(value = "创建时间")
    private String creatat;
    @ApiModelProperty(value = "员工数")
    private String employeeCount;
    @ApiModelProperty(value = "剩余次数")
    private String AllLeftCounts;
    @ApiModelProperty(value = "总共免费次数")
    private String AllFreeCounts;
    @ApiModelProperty(value = "总共购买次数")
    private String AllBuyCounts;
    @ApiModelProperty(value = "企业诚信代码")
    private String AllConsumeCounts;
    @ApiModelProperty(value = "所属行业")
    private String industry;

}
