package com.hlw.common.model.vo.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/4/27
 */
@Data
public class MenuEditVo implements Serializable {
    private static final long serialVersionUID = -1843664026523482150L;


    /**id*/
    private Long id;

    /**父级id,使用‘,’分隔*/
    private String parentId;

    /**所有父级id,使用‘,’分隔*/
    private String parentIds;

    /**名称*/
    private String name;

    /**排序*/
    private BigDecimal sort;

    /**链接*/
    private String href;

    /**目标*/
    private String target;

    /**图标*/
    private String icon;

    /**是否在菜单中显示 0：显示 1：不显示*/
    private Integer isShow;

    /**前端路由*/
    private String route;

}
