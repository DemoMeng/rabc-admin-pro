package com.hlw.common.model.vo.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/5/9
 "*/
@Data
@ApiModel(description = "审核列表返回实体")
public class AuditAccountListVo implements Serializable {

    private static final long serialVersionUID = -1265824166080756991L;

    private Long id;

    @ApiModelProperty(value = "认证企业名称")
    private String identifyName;
    @ApiModelProperty(value = "管理员")
    private String manager;
    @ApiModelProperty(value = "账号")
    private String managerPhone;
    @ApiModelProperty(value = "公司类型")
    private String companyStatus;
    @ApiModelProperty(value = "创建时间")
    private Date creatAt;
    @ApiModelProperty(value = "0.未通过 1.通过 2. 审核中 3.认证第一步通过")
    private String statusZN;
    @ApiModelProperty(value = "账号，基础版关联user表，专业版关联employee表，然后取手机号")
    private String legalManName;
    @ApiModelProperty(value = "统一社会信用代码")
    private String organizationCode;
    @ApiModelProperty(value = "经办人姓名")
    private String resManName;
    @ApiModelProperty(value = "经办人身份证号")
    private String resManIdentify;
    @ApiModelProperty(value = "职务")
    private String resManPosition;
    @ApiModelProperty(value = "营业执照图片存储路径")
    private String businessLicenseUrl;
    @ApiModelProperty(value = "授权委托书存储路径")
    private String powerLetterUrl;
    @ApiModelProperty(value = "企业法人身份证号码")
    private String legalManIdentify;
    @ApiModelProperty(value = "0.未通过 1.通过 2. 审核中 3.认证第一步通过")
    private Integer status;

}
