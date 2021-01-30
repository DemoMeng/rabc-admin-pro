package com.hlw.manage.system.service;

import com.hlw.manage.system.entity.HlwManageRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
public interface IHlwManageRoleMenuService extends IService<HlwManageRoleMenu> {

    /**
     *
     * @param id
     * @return
     */
    int removeByRoleId(Long id);

    void saveRoleMenu(Long roleIdForMenu, List<String> ids);
}
