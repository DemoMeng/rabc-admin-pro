package com.hlw.manage.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlw.common.model.dto.system.LogListDto;
import com.hlw.common.model.vo.system.LogListVo;
import com.hlw.common.model.vo.system.RoleVo;
import com.hlw.common.model.vo.system.SearchVo;
import com.hlw.manage.system.entity.HlwManageLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mqz
 * @since 2020-04-21
 */
public interface HlwManageLogMapper extends BaseMapper<HlwManageLog> {


    /**
     * 获取筛选条件-角色
     * @return
     */
    List<SearchVo> searchRole();

    /**
     * 获取筛选条件-用户
     * @return
     */
    List<SearchVo> searchUser();



    IPage<LogListVo> getAllPage(Page page, @Param("dto") LogListDto dto);

    /**
     *
     * @param hlwManageLog
     */
    void add(@Param("dto") HlwManageLog hlwManageLog);
}
