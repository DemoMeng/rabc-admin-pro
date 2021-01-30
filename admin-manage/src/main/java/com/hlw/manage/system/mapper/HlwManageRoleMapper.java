package com.hlw.manage.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlw.common.model.dto.system.RoleListDto;
import com.hlw.common.model.vo.system.CurrentRoleVo;
import com.hlw.common.model.vo.system.RoleVo;
import com.hlw.manage.system.entity.HlwManageRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
public interface HlwManageRoleMapper extends BaseMapper<HlwManageRole> {

    /**
     * 获取所有角色
     * @param page
     * @param dto
     * @return
     */
    IPage<RoleVo> getAllPage(Page page,@Param("dto") RoleListDto dto);

    /**
     *
     * @param current
     * @return
     */
    List<CurrentRoleVo> getByUserId(Long current);
}
