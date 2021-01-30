package com.hlw.common.model.dto.system;

import com.hlw.common.model.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mqz
 * @description
 * @since 2020/4/26
 */
@Data
@ApiModel(description = "用户列表实体")
public class UserListDto extends CommonDto implements Serializable {

    private static final long serialVersionUID = -3768662729828321369L;

    /** 搜索关键字 */
    @ApiModelProperty(name = "搜索关键字")
    private String keyword;


}
