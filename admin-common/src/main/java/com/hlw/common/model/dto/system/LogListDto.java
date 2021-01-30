package com.hlw.common.model.dto.system;

import com.hlw.common.model.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/4/26
 */
@Data
@ApiModel(description = "日志列表实体")
public class LogListDto extends CommonDto implements Serializable {

    private static final long serialVersionUID = -7876187912179956407L;

    @ApiModelProperty(name = "开始日期")
    private Date start;

    @ApiModelProperty(name = "结束日期")
    private Date end;

    @ApiModelProperty(name = "筛选-角色")
    private String roleName;

    @ApiModelProperty(name = "筛选-操作人")
    private String userName;

}
