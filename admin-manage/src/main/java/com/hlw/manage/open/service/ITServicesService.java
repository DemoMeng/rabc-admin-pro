package com.hlw.manage.open.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.model.dto.open.ServicesEditDto;
import com.hlw.common.model.dto.open.ServicesListDto;
import com.hlw.common.model.dto.open.ServicesSaveDto;
import com.hlw.common.model.vo.open.ServicesListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.open.entity.TServices;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务表 服务类
 * </p>
 *
 * @author mqz
 * @since 2020-07-16
 */
public interface ITServicesService extends IService<TServices> {

    ResponseBean<IPage<ServicesListVo>> getList(ServicesListDto dto);

    ResponseBean off(Integer id);

    ResponseBean on(Integer id);

    ResponseBean edit(ServicesEditDto dto);

    ResponseBean removeServices(Integer id);

    ResponseBean saveServices(ServicesSaveDto dto);
}
