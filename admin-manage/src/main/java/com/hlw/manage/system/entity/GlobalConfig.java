package com.hlw.manage.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mqz
 * @since 2020-07-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GlobalConfig extends Model<GlobalConfig> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 归类名称
     */
    private String name;

    /**
     * 1:第一级  2：第二级  3：第三级
     */
    private Integer levelBelong;
    /**
     * 数量
     * */
    private int num;
    /**
     * 归属审核角色id
     */
    private Long byRoleId;
    /**
     * 区别标识 （接口赠送：API  合同赠送 ：CONTRACT）
     */
    private String type;

    /**
     * 启用状态 （ 0 ：启用  1：未启用）
     */
    private Integer status;


    private Date createTime;

    private Long createBy;

    private Date updateTime;

    private Long updateBy;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
