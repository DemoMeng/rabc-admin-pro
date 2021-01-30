package com.hlw.manage.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HlwManageMenu extends Model<HlwManageMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 归属类别 1：一级菜单 2：二级菜单 3：三级菜单 4:tab链  5：按钮
     */
    private Integer belongType;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private BigDecimal sort;

    /**
     * 链接
     */
    private String href;

    /**
     * 权限
     */
    private String permission;

    /**
     * 图标
     */
    private String icon;

//    /**
//     * 是否在菜单中显示 0：显示 1：不显示
//     */
//    private Integer isShow;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记 0：正常 1：已删除
     */
    private Integer delFlag;

    /**前端路由*/
    private String route;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
