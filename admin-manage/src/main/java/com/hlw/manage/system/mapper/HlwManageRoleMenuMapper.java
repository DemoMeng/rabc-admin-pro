package com.hlw.manage.system.mapper;

import com.hlw.manage.system.entity.HlwManageRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
public interface HlwManageRoleMenuMapper extends BaseMapper<HlwManageRoleMenu> {

    int removeByRoleId(Long id);

    void saveRoleMenu(@Param("roleId") Long roleIdForMenu, @Param("list") List<Long> ids);
}
