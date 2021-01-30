package com.hlw.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.constants.CommonConstants;
import com.hlw.common.model.dto.system.GlobalConfigEditDto;
import com.hlw.common.model.vo.system.ForLog;
import com.hlw.common.model.vo.system.GlobalConfigListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.core.CurrentTools;
import com.hlw.manage.core.redis.RedisService;
import com.hlw.manage.system.entity.GlobalConfig;
import com.hlw.manage.system.entity.HlwManageUser;
import com.hlw.manage.system.mapper.GlobalConfigMapper;
import com.hlw.manage.system.service.IGlobalConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hlw.manage.system.service.IHlwManageUserRoleService;
import com.hlw.manage.system.service.IHlwManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mqz
 * @since 2020-07-10
 */
@Service
public class GlobalConfigServiceImpl extends ServiceImpl<GlobalConfigMapper, GlobalConfig> implements IGlobalConfigService {

    @Autowired
    private IHlwManageUserService userService;
    @Autowired
    private IHlwManageUserRoleService userRoleService;

    @Override
    public ResponseBean<List<GlobalConfigListVo>> getAllList() {
        return new ResponseBean<List<GlobalConfigListVo>>().SUCCESS(baseMapper.getAllList());
    }

    @Override
    public ResponseBean edit(List<GlobalConfigEditDto> gc) {
        for(GlobalConfigEditDto v : gc){
            GlobalConfig update = new GlobalConfig()
                    .setId(v.getId())
                    .setByRoleId(v.getByRoleId())
                    .setNum(v.getNum())
                    .setStatus(v.getStatus())
                    .setUpdateBy(CurrentTools.currentId())
                    .setUpdateTime(new Date());
            updateById(update);
        }
        config();
        /**日志切面*/
        HlwManageUser current = userService.getById(CurrentTools.currentId());
        String name = userRoleService.getByUserId(current.getId());
        ForLog log = new ForLog()
                .setOperateAccount(current.getLoginName())
                .setUserName(current.getName())
                .setRoleName(name)
                .setOperateName("修改全局系统配置")
                .setResult("修改了全局的系统配置【"+gc.toString()+"】");
        return new ResponseBean().SUCCESS(log);
    }

    @Override
    public void config() {
            LambdaQueryWrapper<GlobalConfig> l1 = new LambdaQueryWrapper<>();
            l1.eq(GlobalConfig::getStatus,CommonConstants.Config.Status.ON_0)
                    .eq(GlobalConfig::getType,CommonConstants.Config.Type.API)
                    .eq(GlobalConfig::getLevelBelong,CommonConstants.Config.LEVEL_BELONG_1);
            RedisService.del(CommonConstants.Config.KEY_API_CONFIG_1);
            RedisService.set(CommonConstants.Config.KEY_API_CONFIG_1,getOne(l1));

            LambdaQueryWrapper<GlobalConfig> l2 = new LambdaQueryWrapper<>();
            l2.eq(GlobalConfig::getStatus,CommonConstants.Config.Status.ON_0)
                    .eq(GlobalConfig::getType,CommonConstants.Config.Type.API)
                    .eq(GlobalConfig::getLevelBelong,CommonConstants.Config.LEVEL_BELONG_2);
            RedisService.del(CommonConstants.Config.KEY_API_CONFIG_2);
            RedisService.set(CommonConstants.Config.KEY_API_CONFIG_2,getOne(l2));

            LambdaQueryWrapper<GlobalConfig> l3 = new LambdaQueryWrapper<>();
            l3.eq(GlobalConfig::getStatus,CommonConstants.Config.Status.ON_0)
                    .eq(GlobalConfig::getType,CommonConstants.Config.Type.API)
                    .eq(GlobalConfig::getLevelBelong,CommonConstants.Config.LEVEL_BELONG_3);
            RedisService.del(CommonConstants.Config.KEY_API_CONFIG_3);
            RedisService.set(CommonConstants.Config.KEY_API_CONFIG_3,getOne(l3));

            LambdaQueryWrapper<GlobalConfig> l4 = new LambdaQueryWrapper<>();
            l4.eq(GlobalConfig::getStatus,CommonConstants.Config.Status.ON_0)
                    .eq(GlobalConfig::getType,CommonConstants.Config.Type.CONTRACT)
                    .eq(GlobalConfig::getLevelBelong,CommonConstants.Config.LEVEL_BELONG_1);
            RedisService.del(CommonConstants.Config.KEY_CONTRACT_CONFIG_1);
            RedisService.set(CommonConstants.Config.KEY_CONTRACT_CONFIG_1,getOne(l4));

            LambdaQueryWrapper<GlobalConfig> l5 = new LambdaQueryWrapper<>();
            l5.eq(GlobalConfig::getStatus,CommonConstants.Config.Status.ON_0)
                    .eq(GlobalConfig::getType,CommonConstants.Config.Type.CONTRACT)
                    .eq(GlobalConfig::getLevelBelong,CommonConstants.Config.LEVEL_BELONG_2);
            RedisService.del(CommonConstants.Config.KEY_CONTRACT_CONFIG_2);
            RedisService.set(CommonConstants.Config.KEY_CONTRACT_CONFIG_2,getOne(l5));

            LambdaQueryWrapper<GlobalConfig> l6 = new LambdaQueryWrapper<>();
            l6.eq(GlobalConfig::getStatus,CommonConstants.Config.Status.ON_0)
                    .eq(GlobalConfig::getType,CommonConstants.Config.Type.CONTRACT)
                    .eq(GlobalConfig::getLevelBelong,CommonConstants.Config.LEVEL_BELONG_3);
            RedisService.del(CommonConstants.Config.KEY_CONTRACT_CONFIG_3);
            RedisService.set(CommonConstants.Config.KEY_CONTRACT_CONFIG_3,getOne(l6));
    }
}
