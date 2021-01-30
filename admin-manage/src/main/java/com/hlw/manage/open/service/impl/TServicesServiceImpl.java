package com.hlw.manage.open.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlw.common.constants.LogConstants;
import com.hlw.common.model.MessageBean;
import com.hlw.common.model.dto.open.ServicesEditDto;
import com.hlw.common.model.dto.open.ServicesListDto;
import com.hlw.common.model.dto.open.ServicesSaveDto;
import com.hlw.common.model.vo.open.ServicesListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.common.utils.WebCommonUtils;
import com.hlw.manage.core.CurrentTools;
import com.hlw.manage.core.message.MessageDealService;
import com.hlw.manage.open.entity.TServices;
import com.hlw.manage.open.mapper.TServicesMapper;
import com.hlw.manage.open.service.ITServicesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 服务表 服务实现类
 * </p>
 *
 * @author mqz
 * @since 2020-07-16
 */
@Service
public class TServicesServiceImpl extends ServiceImpl<TServicesMapper, TServices> implements ITServicesService {

    @Override
    public ResponseBean<IPage<ServicesListVo>> getList(ServicesListDto dto) {
        Page page = new Page(dto.getPageCurrent(),dto.getPageSize());
        IPage<ServicesListVo> result = baseMapper.getList(page,dto);
        return new ResponseBean().SUCCESS(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBean off(Integer id) {
        //TODO Your business
        return new ResponseBean().SUCCESS();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBean on(Integer id) {
        //TODO Your Business
        return new ResponseBean().SUCCESS();
    }

    @Override
    public ResponseBean edit(ServicesEditDto dto) {
        //TODO Your Business
        return new ResponseBean().SUCCESS();
    }

    @Override
    public ResponseBean removeServices(Integer id) {
        //TODO Your Business
        MessageBean mb = new MessageBean()
                .setIp(WebCommonUtils.getIpAddr())
                .setUserId(CurrentTools.currentId())
                .setResult(LogConstants.Open.Result.SERVICES_REMOVE)
                .setDescription(LogConstants.Open.Description.SERVICES_REMOVE)
                .setCreateDate(new Date());
        MessageDealService.deal(mb);
        return new ResponseBean().SUCCESS();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBean saveServices(ServicesSaveDto dto) {
        //TODO Your Business
        return new ResponseBean().SUCCESS();
    }
}
