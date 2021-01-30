package com.hlw.manage.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.model.dto.system.RoleCheckDto;
import com.hlw.common.model.dto.system.RoleListDto;
import com.hlw.common.model.dto.system.RoleMenuDto;
import com.hlw.common.model.vo.system.CurrentRoleVo;
import com.hlw.common.model.vo.system.RoleEditVo;
import com.hlw.common.model.vo.system.RoleVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.system.entity.HlwManageRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
public interface IHlwManageRoleService extends IService<HlwManageRole> {

    /**
     * 获取角色list
     * @param dto
     * @return
     */
    ResponseBean<IPage<RoleVo>> getAll(RoleListDto dto);
    /**
     * 角色菜单编辑回显
     * @param roleId
     * @return
     */
    ResponseBean roleMenuEcho(Long roleId);
    /**
     * 角色菜单保存
     * @param dto
     * @return
     */
    ResponseBean roleMenuSave(RoleMenuDto dto);

    /**
     * 编辑回显
     * @param id
     * @return
     */
    ResponseBean<RoleEditVo> echo(Long id);

    /**
     * 角色操作保存
     * @param dto
     * @return
     */
    ResponseBean saveRole(RoleMenuDto dto);

    /**
     * 角色名称，编号查重
     * @param dto
     * @return
     */
    ResponseBean check(RoleCheckDto dto);

    /**
     * 角色删除
     * @param id
     * @return
     */
    ResponseBean removeRole(Long id);

    /**
     * version 2.0
     * 当前用户的角色 （角色集合，用户有多个角色）
     * @return
     */
    List<CurrentRoleVo> currentRole(Long id);

    /**
     * version 2.0
     * 当前登录用户id集合
     * @return
     */
    List<Long> currentRoleIdList();


    /**
     * 当前用户所属角色返回实体
     * @return
     */
    ResponseBean<List<CurrentRoleVo>> current();

    ResponseBean<RoleEditVo> echoBetter(Long id);
}
