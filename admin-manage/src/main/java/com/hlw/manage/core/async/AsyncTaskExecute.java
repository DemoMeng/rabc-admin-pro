package com.hlw.manage.core.async;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hlw.common.constants.CommonConstants;
import com.hlw.common.model.vo.system.CurrentRoleVo;
import com.hlw.common.utils.AddressUtils;
import com.hlw.manage.core.redis.RedisService;
import com.hlw.manage.system.entity.GlobalConfig;
import com.hlw.manage.system.entity.HlwManageUser;
import com.hlw.manage.system.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author mqz
 * @description 异步任务执行类
 * @since 2020/5/9
 */
@Component
@EnableScheduling
@Async
public class AsyncTaskExecute {

    private static final Logger log = LoggerFactory.getLogger(AsyncTaskExecute.class);

    @Autowired
    private IHlwManageRoleService roleService;
    @Autowired
    private IHlwManageUserService userService;
    @Autowired
    private IGlobalConfigService configService;



    @Value("${my.token.expire}")
    private long tokenExpireTime;



    @Async
    public void pushCurrentRole(Long id){
        List<CurrentRoleVo> roles = roleService.currentRole(id);
        RedisService.set(CommonConstants.Current.CURRENT_ROLE+id,roles,tokenExpireTime);
        /**存入roleId集合*/
        List<Long> roleIdList = new ArrayList<>();
        for (CurrentRoleVo vo:roles){
            roleIdList.add(vo.getId());
        }
        RedisService.set(CommonConstants.Current.CURRENT_ROLE_ID_LIST+id,roleIdList,tokenExpireTime);
        log.info("当前用户角色已经存入缓存中");
    }

    @Async
    public void updateLogin(Long currentId, Date date,String ip) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HlwManageUser user = new HlwManageUser()
                .setId(currentId)
                .setLoginDate(date)
                .setLoginIp(ip)
                .setArea(AddressUtils.getAreaWithGD(ip));
        userService.updateById(user);
        log.info("登录信息已更新");
    }

    @Async
    public void loadConfig() {
        GlobalConfig a1 = (GlobalConfig) RedisService.get(CommonConstants.Config.KEY_API_CONFIG_1);
        GlobalConfig a2 = (GlobalConfig) RedisService.get(CommonConstants.Config.KEY_API_CONFIG_2);
        GlobalConfig a3 = (GlobalConfig) RedisService.get(CommonConstants.Config.KEY_API_CONFIG_3);
        GlobalConfig c1 = (GlobalConfig) RedisService.get(CommonConstants.Config.KEY_CONTRACT_CONFIG_1);
        GlobalConfig c2 = (GlobalConfig) RedisService.get(CommonConstants.Config.KEY_CONTRACT_CONFIG_2);
        GlobalConfig c3 = (GlobalConfig) RedisService.get(CommonConstants.Config.KEY_CONTRACT_CONFIG_3);
        if(a1 == null){
            LambdaQueryWrapper<GlobalConfig> lam = new LambdaQueryWrapper<>();
            lam.eq(GlobalConfig::getStatus,CommonConstants.Config.Status.ON_0)
                    .eq(GlobalConfig::getType,CommonConstants.Config.Type.API)
                    .eq(GlobalConfig::getLevelBelong,CommonConstants.Config.LEVEL_BELONG_1);
            RedisService.set(CommonConstants.Config.KEY_API_CONFIG_1,configService.getOne(lam));
        }
        if(a2 == null){
            LambdaQueryWrapper<GlobalConfig> lam = new LambdaQueryWrapper<>();
            lam.eq(GlobalConfig::getStatus,CommonConstants.Config.Status.ON_0)
                    .eq(GlobalConfig::getType,CommonConstants.Config.Type.API)
                    .eq(GlobalConfig::getLevelBelong,CommonConstants.Config.LEVEL_BELONG_2);
            RedisService.set(CommonConstants.Config.KEY_API_CONFIG_2,configService.getOne(lam));
        }
        if(a3 == null){
            LambdaQueryWrapper<GlobalConfig> lam = new LambdaQueryWrapper<>();
            lam.eq(GlobalConfig::getStatus,CommonConstants.Config.Status.ON_0)
                    .eq(GlobalConfig::getType,CommonConstants.Config.Type.API)
                    .eq(GlobalConfig::getLevelBelong,CommonConstants.Config.LEVEL_BELONG_3);
            RedisService.set(CommonConstants.Config.KEY_API_CONFIG_3,configService.getOne(lam));
        }
        if(c1 == null){
            LambdaQueryWrapper<GlobalConfig> lam = new LambdaQueryWrapper<>();
            lam.eq(GlobalConfig::getStatus,CommonConstants.Config.Status.ON_0)
                    .eq(GlobalConfig::getType,CommonConstants.Config.Type.CONTRACT)
                    .eq(GlobalConfig::getLevelBelong,CommonConstants.Config.LEVEL_BELONG_1);
            RedisService.set(CommonConstants.Config.KEY_CONTRACT_CONFIG_1,configService.getOne(lam));
        }
        if(c2 == null){
            LambdaQueryWrapper<GlobalConfig> lam = new LambdaQueryWrapper<>();
            lam.eq(GlobalConfig::getStatus,CommonConstants.Config.Status.ON_0)
                    .eq(GlobalConfig::getType,CommonConstants.Config.Type.CONTRACT)
                    .eq(GlobalConfig::getLevelBelong,CommonConstants.Config.LEVEL_BELONG_2);
            RedisService.set(CommonConstants.Config.KEY_CONTRACT_CONFIG_2,configService.getOne(lam));
        }
        if(c3 == null){
            LambdaQueryWrapper<GlobalConfig> lam = new LambdaQueryWrapper<>();
            lam.eq(GlobalConfig::getStatus,CommonConstants.Config.Status.ON_0)
                    .eq(GlobalConfig::getType,CommonConstants.Config.Type.CONTRACT)
                    .eq(GlobalConfig::getLevelBelong,CommonConstants.Config.LEVEL_BELONG_3);
            RedisService.set(CommonConstants.Config.KEY_CONTRACT_CONFIG_3,configService.getOne(lam));
        }
    }


    /**菜单操作触发缓存更新*/
    public void removeRoleMenu() {
        log.info("【缓存清理】缓存的初始化菜单已成功被清理");
        String pattern = CommonConstants.Current.ROLE_MENU_LIST+"*";
        Set<String> keys = RedisService.scan(pattern);
        for(String key:keys){
            RedisService.del(key);
        }
    }

    /**菜单操作触发缓存更新*/
    public void deleteSaveRole() {
        log.info("【缓存清理】缓存的角色关联菜单已成功被清理");
        RedisService.del(CommonConstants.Current.ROLE_MENU_SAVE_LIST);
        String pattern = CommonConstants.Current.ROLE_MENU_EDIT_LIST+"*";
        Set<String> keys = RedisService.scan(pattern);
        for(String key:keys){
            RedisService.del(key);
        }
    }

    /**菜单操作触发缓存更新*/
    public void deleteChildMenu() {
        log.info("【缓存清理】缓存的子菜单已成功被清理");
        String pattern = CommonConstants.Current.MENU_CHILD_LIST+"*";
        Set<String> keys = RedisService.scan(pattern);
        for(String key:keys){
            RedisService.del(key);
        }
    }

}
