package com.hlw.common.model.dto.manage;

import com.hlw.common.valids.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/5/12
 */
@Data
@ApiModel(description = "保存企业用户实体")
public class CompanySaveDto implements Serializable {

    private static final long serialVersionUID = 87406186662210771L;

    @ApiModelProperty(value = "公司名称")
    @NotNull(message = "公司名称不能为空")
    private String companyName;

    @ApiModelProperty(value = "社会诚信码")
    @NotNull(message = "社会诚信码不能为空")
    private String organizationCode;

    @ApiModelProperty(value = "营业执照")
    @NotNull(message = "营业执照不能为空")
    private String businessLicenseUrl;

    @ApiModelProperty(value = "申请人职务")
    private String positionName;

    @ApiModelProperty(value = "姓名")
    @NotNull(message = "公司名称不能为空")
    private String realName;

    @ApiModelProperty(value = "所属行业")
    private String industry;

    @ApiModelProperty(value = "身份证号")
    @NotNull(message = "身份证号不能为空")
    private String idCardCode;

    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    @Phone(message = "手机格式不正确")
    private String phone;

    @ApiModelProperty(value = "身份证图片url")
    @NotNull(message = "身份证照片不能为空")
    private String idCardUrl;

    @ApiModelProperty(value = "赠送份数")
    private Integer free;

//    @ApiModelProperty(value = "是否是经销商  0：非经销商  1:经销商")
//    @NotNull(message = "经销商标识不能为空")
    @ApiModelProperty(hidden = true)
    private Integer type;


}
