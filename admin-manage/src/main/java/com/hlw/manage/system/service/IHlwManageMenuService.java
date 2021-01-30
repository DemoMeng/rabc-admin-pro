package com.hlw.manage.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.model.dto.system.MenuListDto;
import com.hlw.common.model.dto.system.MenuSaveDto;
import com.hlw.common.model.vo.system.*;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.system.entity.HlwManageMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
public interface IHlwManageMenuService extends IService<HlwManageMenu> {

    /**
     * 根据角色id获取到菜单
     * @param roleId
     * @return
     */
    List<String> getMenuByRoleId(Long roleId);

    /**
     * 获取所有菜单列表
     * @param dto
     * @return
     */
    ResponseBean<IPage<MenuListVo>> getAllList(MenuListDto dto);

    /**
     * 菜单编辑回显
     * @param mId
     * @return
     */
    ResponseBean<MenuEditVo> editEcho(Long mId);

    /**
     * 保存菜单
     * @param dto
     * @return
     */
    ResponseBean saveMenu(MenuSaveDto dto);

    /**
     * 父级菜单列表
     * @return
     */
    ResponseBean<List<MenuListVo>> parentMenuList(Integer type);

    /**
     * 登录成功初始化菜单
     * @return
     */
    ResponseBean<List<MenuInitVo>> init();

    /**
     * 删除菜单
     * @param id
     * @return
     */
    ResponseBean removeMenu(Long id);

    ResponseBean<List<MenuChildListVo>> getChild(Long id);

    List<RoleMenuEchoVo> getFirstAndSecondMenu(boolean isShowAdmin);

    List<RoleMenuEchoVo> getThirdAndFourMenu(Long id);
}
