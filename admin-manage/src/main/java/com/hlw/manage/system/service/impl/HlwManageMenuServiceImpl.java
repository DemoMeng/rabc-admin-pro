package com.hlw.manage.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.hlw.common.constants.CommonConstants;
import com.hlw.common.model.dto.system.MenuListDto;
import com.hlw.common.model.dto.system.MenuSaveDto;
import com.hlw.common.model.vo.system.*;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.core.CurrentTools;
import com.hlw.manage.core.async.AsyncTaskExecute;
import com.hlw.manage.core.exception.ServicesException;
import com.hlw.manage.core.redis.RedisService;
import com.hlw.manage.system.entity.HlwManageMenu;
import com.hlw.manage.system.entity.HlwManageUser;
import com.hlw.manage.system.mapper.HlwManageMenuMapper;
import com.hlw.manage.system.service.IHlwManageMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hlw.manage.system.service.IHlwManageRoleService;
import com.hlw.manage.system.service.IHlwManageUserRoleService;
import com.hlw.manage.system.service.IHlwManageUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
@Service
@Slf4j
public class HlwManageMenuServiceImpl extends ServiceImpl<HlwManageMenuMapper, HlwManageMenu> implements IHlwManageMenuService {

    @Autowired
    private IHlwManageUserService userService;
    @Autowired
    private IHlwManageUserRoleService userRoleService;
    @Autowired
    private IHlwManageRoleService roleService;
    @Autowired
    private AsyncTaskExecute taskService;
    @Value("${my.roleMenu.expire}")
    private long roleMenuExpireTime;


    @Override
    public List<String> getMenuByRoleId(Long roleId) {
        return baseMapper.getMenuByRoleId(roleId);
    }

    @Override
    public ResponseBean<IPage<MenuListVo>> getAllList(MenuListDto dto) {
        Page page = new Page(dto.getPageCurrent(),dto.getPageSize());
        IPage<MenuListVo> pageResult =baseMapper.getAllList(page,dto);
        return new ResponseBean().SUCCESS(pageResult);
    }

    @Override
    public ResponseBean<MenuEditVo> editEcho(Long mId) {
        HlwManageMenu menu = getById(mId);
        MenuEditVo vo = new MenuEditVo();
        BeanUtils.copyProperties(menu,vo);
        return new ResponseBean().SUCCESS(vo);
    }

    @Override
    public ResponseBean saveMenu(MenuSaveDto dto) {
        Long currentId = CurrentTools.currentId();
        HlwManageMenu menu = new HlwManageMenu();
        BeanUtils.copyProperties(dto,menu);
        String operateName = CommonConstants.UPDATE;
        if(menu.getId() == null){
            operateName = CommonConstants.NEW;
            menu.setCreateBy(currentId);
            menu.setCreateDate(new Date());
            menu.setDelFlag(CommonConstants.DELETE_FLAG_NORMAL);
            menu.setSort(new BigDecimal(1));
            //menu.setIsShow(0);
        }
        menu.setUpdateBy(currentId);
        menu.setUpdateDate(new Date());
        saveOrUpdate(menu);
        taskService.removeRoleMenu();
        taskService.deleteSaveRole();
        taskService.deleteChildMenu();
        /**日志切面*/
        HlwManageUser current = userService.getById(CurrentTools.currentId());
        String name = userRoleService.getByUserId(current.getId());
        ForLog log = new ForLog()
                .setOperateAccount(current.getLoginName())
                .setUserName(current.getName())
                .setRoleName(name)
                .setOperateName("系统管理"+operateName+"菜单")
                .setResult(operateName+"了菜单【"+menu.getName()+"】");
        return new ResponseBean().SUCCESS(log);
    }

    @Override
    public ResponseBean<List<MenuListVo>> parentMenuList(Integer type) {
        return new ResponseBean().SUCCESS(baseMapper.getParentMenu(type));
    }


    @Override
    public ResponseBean<List<MenuInitVo>> init() {
        List<Long> roleIdList = roleService.currentRoleIdList();
        /**加入缓存*/
        Long currentId = CurrentTools.currentId();
        Map<String,Object> cacheMenu = (Map<String, Object>) RedisService.get(CommonConstants.Current.ROLE_MENU_LIST+currentId);
        if(!CollectionUtils.isEmpty(cacheMenu)){
            log.info(String.format("【缓存读取】当前用户id【%s】，从缓存中获取初始化的菜单",currentId));
            return new ResponseBean().SUCCESS(cacheMenu);
        }
        List<Long> parentIds = baseMapper.getAllMenusParentId(roleIdList);
        List<HlwManageMenu> parentList = baseMapper.getAllMenus(roleIdList);
        Map<String,Object> rMap = new HashMap<>();
        List<MenuInitVo> result = new ArrayList<>();
        for(Long parent:parentIds){
            /**系统配置-为了符合原型设计迫于这样写法-实际上不规范*/
            if(parent.intValue() == 16){
                MenuInitVo vos = new MenuInitVo();
                HlwManageMenu pm = getById(parent);
                BeanUtils.copyProperties(pm,vos);
                vos.setSons(baseMapper.getLastMenu(roleIdList,parent));
                rMap.put("setting",vos);
                continue;
            }
            MenuInitVo vos = new MenuInitVo();
            HlwManageMenu pm = getById(parent);
            BeanUtils.copyProperties(pm,vos);
            List<MenuInitVo> sons = new ArrayList<>();
            for(HlwManageMenu menu:parentList){
                if(menu.getBelongType().intValue() ==2){
                    if(menu.getParentId().intValue() == parent.intValue()){
                        MenuInitVo son = new MenuInitVo();
                        BeanUtils.copyProperties(menu,son);
                        son.setSons(baseMapper.getLastMenu(roleIdList,menu.getId()));
                        sons.add(son);
                    }
                }
            }
            vos.setSons(sons);
            result.add(vos);
        }
        rMap.put("other",result);
        /**加入缓存*/
        RedisService.set(CommonConstants.Current.ROLE_MENU_LIST+currentId,rMap,roleMenuExpireTime);
        log.info(String.format("【缓存击穿】当前用户id【%s】，从数据库中获取初始化的菜单",currentId));
        return new ResponseBean().SUCCESS(rMap);
    }

    @Override
    public ResponseBean removeMenu(Long id) {
        HlwManageMenu menu = getById(id);
        if (menu == null) {
            throw new ServicesException("id有误，预期结果未找到");
        }
        HlwManageMenu update = new HlwManageMenu();
        update.setId(menu.getId());
        update.setUpdateDate(new Date());
        update.setUpdateBy(CurrentTools.currentId());
        update.setDelFlag(CommonConstants.DELETE_FLAG_DELETED);
        updateById(update);
        taskService.removeRoleMenu();
        taskService.deleteSaveRole();
        taskService.deleteChildMenu();
        return new ResponseBean().SUCCESS();
    }

    @Override
    public ResponseBean<List<MenuChildListVo>> getChild(Long id) {
        List<MenuChildListVo> cacheResult = (List<MenuChildListVo>) RedisService.get(CommonConstants.Current.MENU_CHILD_LIST+id);
        if(cacheResult != null){
            log.info(String.format("【缓存读取】当前菜单id【%s】，从缓存中获取初始化的菜单",id));
            return new ResponseBean().SUCCESS(cacheResult);
        }
        List<MenuChildListVo> list = baseMapper.getChildByParentId(id);
        for(MenuChildListVo v:list){
            List<MenuChildListVo> m4OR5 = baseMapper.getChildByParentId(v.getId());
            for(MenuChildListVo vv:m4OR5){
                vv.setChild(baseMapper.getChildByParentId(vv.getId()));
            }
            v.setChild(m4OR5);
        }
        /**加入缓存*/
        RedisService.set(CommonConstants.Current.MENU_CHILD_LIST+id,list);
        log.info(String.format("【缓存击穿】当前菜单id【%s】，从缓存中获取初始化的菜单",id));
        return new ResponseBean().SUCCESS(list);
    }

    @Override
    public List<RoleMenuEchoVo> getFirstAndSecondMenu(boolean isShowAdmin) {
        return baseMapper.getFirstAndSecondMenu(isShowAdmin);
    }

    @Override
    public List<RoleMenuEchoVo> getThirdAndFourMenu(Long id) {
        return baseMapper.getThirdAndFourMenu(id);
    }

}
