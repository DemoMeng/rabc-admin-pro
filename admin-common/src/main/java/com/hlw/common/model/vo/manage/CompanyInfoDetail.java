package com.hlw.common.model.vo.manage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/7/10
 */
@Data
@Accessors(chain = true)
public class CompanyInfoDetail implements Serializable {

    private static final long serialVersionUID = -345914171114065221L;

    @ApiModelProperty(value = "企业名称")
    private String name;
    @ApiModelProperty(value = "企业诚信代码")
    private String organizationcode;
    @ApiModelProperty(value = "账号")
    private String account;
    @ApiModelProperty(value = "法人姓名")
    private String legalManName;
    @ApiModelProperty(value = "法人身份证号码")
    private String legalManIdentify;
    @ApiModelProperty(value = "法人手机号")
    private String legalManPhone;
    @ApiModelProperty(value = "法人职位")
    private String legalManPosition;
    @ApiModelProperty(value = "0: 法人 1: 非法人")
    private String type;
    @ApiModelProperty(value = "申请人手机")
    private String resManPhone;
    @ApiModelProperty(value = "申请人身份证")
    private String resManIdentify;
    @ApiModelProperty(value = "申请人姓名")
    private String resManName;
    @ApiModelProperty(value = "申请人职位")
    private String resManPosition;
    @ApiModelProperty(value = "营业执照")
    private String businessLicenseUrl;
    @ApiModelProperty(value = "用户身份证图片")
    private String idcardUrl;

}
