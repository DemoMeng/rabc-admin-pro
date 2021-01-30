package com.hlw.manage.system.service;

import com.hlw.manage.system.entity.HlwManageUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
public interface IHlwManageUserRoleService extends IService<HlwManageUserRole> {

    /**
     * 通过用户id获取到角色名称
     * @param id
     * @return
     */
    String getByUserId(Long id);

    /**
     * version 2.0
     * 判断用户是否有该接口权限
     * @param permission
     * @return
     */
    boolean isPermission( String permission);
}
