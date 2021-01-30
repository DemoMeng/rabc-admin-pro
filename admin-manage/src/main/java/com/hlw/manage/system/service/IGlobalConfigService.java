package com.hlw.manage.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.model.dto.system.GlobalConfigEditDto;
import com.hlw.common.model.vo.system.GlobalConfigListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.system.entity.GlobalConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mqz
 * @since 2020-07-10
 */
public interface IGlobalConfigService extends IService<GlobalConfig> {

    ResponseBean<List<GlobalConfigListVo>> getAllList();

    ResponseBean edit(List<GlobalConfigEditDto> gc);

    void config();
}
