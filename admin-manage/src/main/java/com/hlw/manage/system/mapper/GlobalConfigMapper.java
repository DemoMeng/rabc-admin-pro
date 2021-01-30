package com.hlw.manage.system.mapper;

import com.hlw.common.model.vo.system.GlobalConfigListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.system.entity.GlobalConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mqz
 * @since 2020-07-10
 */
public interface GlobalConfigMapper extends BaseMapper<GlobalConfig> {

    List<GlobalConfigListVo> getAllList();
}
