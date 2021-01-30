package com.hlw.common.model.dto.open;

import com.hlw.common.model.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/5/14
 */

@Data
@ApiModel(description = "API列表请求实体")
public class ServicesListDto extends CommonDto implements Serializable {

    @ApiModelProperty(value = "查询关键字")
    private String keyword;

    @ApiModelProperty(hidden = true)
    private int status;

    @ApiModelProperty(value = "分组id")
    private Integer groupId;

}
