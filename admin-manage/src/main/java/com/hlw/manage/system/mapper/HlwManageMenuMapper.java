package com.hlw.manage.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlw.common.model.dto.system.MenuListDto;
import com.hlw.common.model.vo.system.MenuChildListVo;
import com.hlw.common.model.vo.system.MenuInitVo;
import com.hlw.common.model.vo.system.MenuListVo;
import com.hlw.common.model.vo.system.RoleMenuEchoVo;
import com.hlw.manage.system.entity.HlwManageMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
public interface HlwManageMenuMapper extends BaseMapper<HlwManageMenu> {

    /**
     * 根据角色id获取到菜单
     * @param roleId
     * @return
     */
    List<String> getMenuByRoleId(Long roleId);


    /**
     * 获取所有的菜单
     * @param page
     * @param dto
     * @return
     */
    IPage<MenuListVo> getAllList(Page page, @Param("dto") MenuListDto dto);

    /**
     * 获取到父级别的菜单
     * @return
     */
    List<MenuListVo> getParentMenu(@Param("type") Integer type);

    /**
     * version 2.0
     * 用户登录之后，显示用户可见的所有菜单
     * @param roleIdList
     * @return
     */
    List<HlwManageMenu> getAllMenus(@Param("roleIdList") List<Long> roleIdList);
    /**
     * version 2.0
     * 用户登录之后，显示用户可见的所有菜单的父级id
     * @param roleIdList
     * @return
     */
    List<Long> getAllMenusParentId(@Param("roleIdList") List<Long> roleIdList);

    /***
     * version 2.0
     * 获取最后一个层级菜单
     * @param roleIdList
     * @param id
     * @return
     */
    List<MenuInitVo> getLastMenu(@Param("roleIdList")List<Long> roleIdList,@Param("id") Long id);

    List<MenuChildListVo> getChildByParentId(@Param("id") Long id);

    List<RoleMenuEchoVo> getFirstAndSecondMenu(@Param("flag") boolean isShowAdmin);

    List<RoleMenuEchoVo> getThirdAndFourMenu(@Param("id") Long parentId);
}
