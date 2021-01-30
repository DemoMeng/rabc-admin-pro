package com.hlw.common.model.vo.manage;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/5/13
 */
@Data
@ApiModel(description = "套餐列表返回实体")
public class ProductsListVo implements Serializable {

    private static final long serialVersionUID = -3141959821120867425L;

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "套餐名称")
    private String name;

    @ApiModelProperty(value = "可签署份数")
    private Integer number;

    @ApiModelProperty(value = "费用，单位¥")
    private BigDecimal price;

    @ApiModelProperty(value = "单价，单位¥")
    private BigDecimal priceUnit;

    @ApiModelProperty(value = "截止时间，单位年")
    private Integer deadLine;

    @ApiModelProperty(value = "套餐类型：0.个人 1.企业")
    private Integer type;

    @ApiModelProperty(value = "套餐类型：0.个人 1.企业")
    private String typeZN;

    @ApiModelProperty(value = "创建时间")
    private Date creatAt;

}
