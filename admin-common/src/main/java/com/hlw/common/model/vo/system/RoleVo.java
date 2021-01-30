package com.hlw.common.model.vo.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/4/26
 */
@Data
public class RoleVo implements Serializable {

    private static final long serialVersionUID = -4332788789657740090L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色类型
     */
    private String roleType;

    /**
     * 是否可用  0:可用 1不可用
     */
    private Integer useable;

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
     * 删除标记  0:正常  1:已删除
     */
    private Integer delFlag;
}
