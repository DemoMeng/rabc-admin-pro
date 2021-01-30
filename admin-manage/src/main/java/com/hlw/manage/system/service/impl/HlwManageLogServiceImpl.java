package com.hlw.manage.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlw.common.model.dto.system.LogListDto;
import com.hlw.common.model.vo.system.LogListVo;
import com.hlw.common.model.vo.system.RoleVo;
import com.hlw.common.model.vo.system.SearchVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.system.entity.HlwManageLog;
import com.hlw.manage.system.mapper.HlwManageLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hlw.manage.system.service.IHlwManageLogService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mqz
 * @since 2020-04-21
 */
@Service
public class HlwManageLogServiceImpl extends ServiceImpl<HlwManageLogMapper, HlwManageLog> implements IHlwManageLogService {

    @Override
    public ResponseBean<IPage<LogListVo>> getAll(LogListDto dto) {
        Page page = new Page(dto.getPageCurrent(),dto.getPageSize());
        IPage<LogListVo> res = baseMapper.getAllPage(page,dto);
        return new ResponseBean().SUCCESS(res);
    }

    @Override
    public ResponseBean getSearch() {
        Map<String,Object> result = new HashMap<>();
        List<SearchVo> role = baseMapper.searchRole();
        List<SearchVo> user = baseMapper.searchUser();
        result.put("roleList",role);
        result.put("userList",user);
        return new ResponseBean().SUCCESS(result);
    }

    @Override
    public void add(HlwManageLog hlwManageLog) {
        baseMapper.add(hlwManageLog);
    }
}
