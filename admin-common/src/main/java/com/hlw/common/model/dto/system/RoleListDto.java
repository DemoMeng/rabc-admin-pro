package com.hlw.common.model.dto.system;

import com.hlw.common.model.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/26
 */
@Data
@ApiModel(value = "角色请求实体")
public class RoleListDto extends CommonDto implements Serializable{

    private static final long serialVersionUID = 537469318038294701L;

    /**
     * 关键词
     */
    @ApiModelProperty("查询关键词")
    private String keyword;

}
