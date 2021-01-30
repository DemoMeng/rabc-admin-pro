package com.hlw.common.model.vo.open;

import com.hlw.common.model.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/7/14
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "审核账号列表返回实体")
public class AuditAccountListVo implements Serializable {
    private static final long serialVersionUID = -5588457271180323903L;
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "公司id")
    private Integer companyId;
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    @ApiModelProperty(value = "管理员姓名")
    private String managerName;
    @ApiModelProperty(value = "所属行业")
    private String industry;
    @ApiModelProperty(value = "账号")
    private String account;
    @ApiModelProperty(value = "账号类型: 公司类型：0.一级 1.二级2.三级")
    private String type;
    @ApiModelProperty(value = "提交日期")
    private Date createTime;
    @ApiModelProperty(value = "审核状态")
    private String auditStatus;
    @ApiModelProperty(value = "审核备注信息")
    private String mark;
    @ApiModelProperty(value = "法人姓名")
    private String frName;
    @ApiModelProperty(value = "统一社会诚信代码")
    private String creditCode;
    @ApiModelProperty(value = "企业邮箱")
    private String email;
    @ApiModelProperty(value = "对接人手机号码")
    private String proxyPhone;
    @ApiModelProperty(value = "营业执照路径")
    private String businessLicenseImg;
    @ApiModelProperty(value = "对接人身份证号码")
    private String proxyIdcard;
    @ApiModelProperty(value = "手持身份证照片")
    private String handIdcardImg;
}
