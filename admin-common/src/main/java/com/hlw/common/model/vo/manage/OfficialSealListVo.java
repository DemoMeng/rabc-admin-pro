package com.hlw.common.model.vo.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/5/11
 */
@Data
@ApiModel(description = "签章审核列表返回实体")
public class OfficialSealListVo implements Serializable {
    private static final long serialVersionUID = 8607153559463206603L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "提交时间")
    private Date creatAt;

    @ApiModelProperty(value = "用户类型")
    private String type;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "状态 是否为默认的 0:是 1：不是   2: 审核中, 3: 审核拒绝 4: 审核通过")
    private Integer status;



}
