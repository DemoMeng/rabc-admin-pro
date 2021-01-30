package com.hlw.common.model.vo.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/4/24
 */
@Data
@Accessors(chain = true)
public class CurrentVo implements Serializable {


    /**
     * id
     */
    private Long id;

    /**
     * 登录名
     */
    private String loginName;


    /**
     * 姓名
     */
    private String name;


}
