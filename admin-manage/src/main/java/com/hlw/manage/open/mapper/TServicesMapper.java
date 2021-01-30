package com.hlw.manage.open.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlw.common.model.dto.open.ServicesListDto;
import com.hlw.common.model.vo.open.ServicesListVo;
import com.hlw.manage.open.entity.TServices;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务表 Mapper 接口
 * </p>
 *
 * @author mqz
 * @since 2020-07-16
 */
public interface TServicesMapper extends BaseMapper<TServices> {

    IPage<ServicesListVo> getList(@Param("page") Page page, @Param("dto") ServicesListDto dto);
}
