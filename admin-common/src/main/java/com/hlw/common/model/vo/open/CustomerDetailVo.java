package com.hlw.common.model.vo.open;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/7/15
 */
@Data
@ApiModel(description = "客户列表明细返回实体")
public class CustomerDetailVo implements Serializable {

    private static final long serialVersionUID = -3863836676320612904L;

    @ApiModelProperty(value = "企业名称")
    private String companyName;
    @ApiModelProperty(value = "统一社会诚信代码")
    private String creditCode;
    @ApiModelProperty(value = "法人姓名")
    private String frName;
    @ApiModelProperty(value = "所属行业")
    private String industry;
    @ApiModelProperty(value = "账号")
    private String account;
    @ApiModelProperty(value = "企业邮箱")
    private String email;
    @ApiModelProperty(value = "对接人姓名")
    private String proxyName;
    @ApiModelProperty(value = "对接人手机")
    private String proxyPhone;
    @ApiModelProperty(value = "对接人身份证号码")
    private String proxyIdcard;
    @ApiModelProperty(value = "对接人职务")
    private String proxyPosition;
    @ApiModelProperty(value = "营业执照路径")
    private String businessLicenseImg;
    @ApiModelProperty(value = "手持身份证照片")
    private String handIdcardImg;
    @ApiModelProperty(value = "身份证照片-前")
    private String idcardF;
    @ApiModelProperty(value = "身份证照片-后")
    private String idcardB;



}
