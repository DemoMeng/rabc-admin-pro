package com.hlw.manage.system.service.impl;

import com.hlw.manage.core.CurrentTools;
import com.hlw.manage.system.entity.HlwManageUserRole;
import com.hlw.manage.system.mapper.HlwManageUserRoleMapper;
import com.hlw.manage.system.service.IHlwManageRoleService;
import com.hlw.manage.system.service.IHlwManageUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
@Service
public class HlwManageUserRoleServiceImpl extends ServiceImpl<HlwManageUserRoleMapper, HlwManageUserRole> implements IHlwManageUserRoleService {

    private static final Logger log = LoggerFactory.getLogger(HlwManageUserRoleServiceImpl.class);

    @Autowired
    private IHlwManageRoleService roleService;

    @Override
    public String getByUserId(Long id) {
        return baseMapper.getByUserId(id);
    }

    @Override
    public boolean isPermission(String permission) {
        List<Long> roleIdList = roleService.currentRoleIdList();
        int result = baseMapper.isPermission(roleIdList,permission);
        if(result == 0){
            log.info("+++++++++++++++++++当前用户【"+ CurrentTools.currentId() +"】的角色为【"+roleIdList.toString()+"】没有【"+permission+"权限】("+result+")+++++++++++++++++++++");
            return false;
        }
        log.info("===================当前用户【"+ CurrentTools.currentId() +"】的角色为【"+roleIdList.toString()+"】有【"+permission+"权限】("+result+")=====================");
        return true;
    }
}
