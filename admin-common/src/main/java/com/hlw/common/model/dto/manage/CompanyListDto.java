package com.hlw.common.model.dto.manage;

import com.hlw.common.model.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/5/11
 */
@Data
@ApiModel(description = "企业用户列表实体")
public class CompanyListDto extends CommonDto implements Serializable {

    private static final long serialVersionUID = 7073733564706392578L;

    @ApiModelProperty(value = "查询关键字")
    private String keyword;

//    @ApiModelProperty(value = "0:基础版 1：专业版")
//    @NotNull(message = "账号类型不能为空")
    @ApiModelProperty(hidden = true)
    private Integer status;

    @ApiModelProperty(value = " less 大于 more 小于")
    private String flag;

//    @ApiModelProperty(value = " 份数-签署余额")
//    private Integer amount;





}
