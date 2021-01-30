package com.hlw.common.model.vo.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/5/13
 */
@Data
@ApiModel(description = "经销商列表返回实体")
public class AgentListVo implements Serializable {
    private static final long serialVersionUID = 7906018367292146329L;

    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "公司id")
    private Long companyId;
    @ApiModelProperty(value = "账号")
    private String phone;
    @ApiModelProperty(value = "管理员姓名")
    private String managerName;
    @ApiModelProperty(value = "注册时间")
    private String createAt;
    @ApiModelProperty(value = "剩余可用合同份数")
    private Long AllLeftCounts;
    @ApiModelProperty(value = "社会统一诚信码")
    private String organizationCode;
    @ApiModelProperty(value = "身份证号码")
    private String idCardCode;
    @ApiModelProperty(value = "身份证照")
    private String idCardUrl;
    @ApiModelProperty(value = "营业执照")
    private String businessLicenseUrl;


}
