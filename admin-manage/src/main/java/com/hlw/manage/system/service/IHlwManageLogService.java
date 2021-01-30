package com.hlw.manage.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hlw.common.model.dto.system.LogListDto;
import com.hlw.common.model.vo.system.LogListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.system.entity.HlwManageLog;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mqz
 * @since 2020-04-21
 */
public interface IHlwManageLogService extends IService<HlwManageLog> {

    /**
     * 日志列表
     * @param dto
     * @return
     */
    ResponseBean<IPage<LogListVo>> getAll(LogListDto dto);

    /**
     * 获取筛选条件
     * @return
     */
    ResponseBean getSearch();

    /**
     *
     * @param hlwManageLog
     */
    void add(HlwManageLog hlwManageLog);
}
