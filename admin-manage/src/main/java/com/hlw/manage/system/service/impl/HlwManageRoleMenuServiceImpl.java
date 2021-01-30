package com.hlw.manage.system.service.impl;

import com.hlw.manage.system.entity.HlwManageRoleMenu;
import com.hlw.manage.system.mapper.HlwManageRoleMenuMapper;
import com.hlw.manage.system.service.IHlwManageRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
@Service
public class HlwManageRoleMenuServiceImpl extends ServiceImpl<HlwManageRoleMenuMapper, HlwManageRoleMenu> implements IHlwManageRoleMenuService {

    @Override
    public int removeByRoleId(Long id) {
        return baseMapper.removeByRoleId(id);
    }

    @Override
    public void saveRoleMenu(Long roleIdForMenu, List<String> ids) {
        baseMapper.saveRoleMenu(roleIdForMenu,ids);
    }

}
