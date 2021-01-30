package com.hlw.manage.system.mapper;

import com.hlw.manage.system.entity.HlwManageUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
public interface HlwManageUserRoleMapper extends BaseMapper<HlwManageUserRole> {

    String getByUserId(Long id);

    int isPermission(@Param("roleIdList") List<Long> roleIdList, @Param("permission") String permission);
}
