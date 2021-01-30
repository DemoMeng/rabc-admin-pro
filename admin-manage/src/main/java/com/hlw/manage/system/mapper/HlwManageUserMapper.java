package com.hlw.manage.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlw.common.model.dto.system.UserListDto;
import com.hlw.common.model.vo.system.UserListVo;
import com.hlw.manage.system.entity.HlwManageUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author mqz
 * @since 2020-04-24
 */
public interface HlwManageUserMapper extends BaseMapper<HlwManageUser> {

    /*IPage<UserListVo> getAll(Page page, @Param("dto") UserListDto dto);*/

    IPage<UserListVo> getAllList(Page page, @Param("dto") UserListDto dto);
}
