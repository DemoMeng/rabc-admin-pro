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
public class CompanyListVo implements Serializable {
    private static final long serialVersionUID = -8170740422875616910L;

    /**id */
    @ApiModelProperty(value = "id")
    private Long id;
    /** 姓名 */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**  管理员*/
    @ApiModelProperty(value = "管理员")
    private String manager;
    /** 状态 */
    @ApiModelProperty(value = "状态")
    private String status;
    /** 状态中文 */
    @ApiModelProperty(value = "状态中文")
    private String statusZN;
    /** 用户名 */
    @ApiModelProperty(value = "用户名")
    private String username;
    /** 账号 */
    @ApiModelProperty(value = "账号")
	private String account;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    private String creatat;
    /** 员工数 */
    @ApiModelProperty(value = "员工数")
    private String employeeCount;
    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private Long userId;
    /** 剩余次数*/
    @ApiModelProperty(value = "剩余次数")
    private String AllLeftCounts;
    /** 总共免费次数 */
    @ApiModelProperty(value = "总共免费次数")
    private String AllFreeCounts;
    @ApiModelProperty(value = "总共购买次数")
    private String AllBuyCounts;
    @ApiModelProperty(value = "企业诚信代码")
    private String AllConsumeCounts;
    @ApiModelProperty(value = "企业诚信代码")
    private String organizationcode;
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
    @ApiModelProperty(value = "身份证图片")
    private String idcardUrl;
    /** 经销商标识 */
    @ApiModelProperty(value = "经销商标识 0：否 1：是")
    private Byte agentFlag;
    @ApiModelProperty(value = "所属行业")
    private String industry;

}
