package com.hlw.common.model.dto.system;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Member;

/**
 * @author mqz
 * @description
 * @since 2020/4/24
 */
@Data
public class BornDto implements Serializable {

    private static final long serialVersionUID = 4951494789508753119L;

    @NotNull(message = "姓名不能为空！")
    private String name;

    @NotNull(message = "登录名不能为空！")
    private String loginName;

    @NotNull(message = "密码不能为空！")
    private String passWord;
}
