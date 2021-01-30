package com.hlw.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlw.common.constants.CommonConstants;
import com.hlw.common.model.dto.system.RoleCheckDto;
import com.hlw.common.model.dto.system.RoleListDto;
import com.hlw.common.model.dto.system.RoleMenuDto;
import com.hlw.common.model.vo.system.*;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.core.CurrentTools;
import com.hlw.manage.core.exception.MissingPermissionException;
import com.hlw.manage.core.exception.ServicesException;
import com.hlw.manage.core.redis.RedisService;
import com.hlw.manage.system.entity.*;
import com.hlw.manage.system.mapper.HlwManageRoleMapper;
import com.hlw.manage.system.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
@Service
@Slf4j
public class HlwManageRoleServiceImpl extends ServiceImpl<HlwManageRoleMapper, HlwManageRole> implements IHlwManageRoleService {

    @Autowired
    private IHlwManageMenuService menuService;

    @Autowired
    private IHlwManageRoleMenuService roleMenuService;

    @Autowired
    private IHlwManageUserRoleService userRoleService;

    @Autowired
    private IHlwManageUserService userService;


    @Override
    public ResponseBean<IPage<RoleVo>> getAll(RoleListDto dto) {
        Page page = new Page(dto.getPageCurrent(),dto.getPageSize());
        IPage<RoleVo> res = baseMapper.getAllPage(page,dto);
        return new ResponseBean().SUCCESS(res);
    }

    @Override
    public ResponseBean roleMenuEcho(Long roleId) {
        LambdaQueryWrapper<HlwManageMenu> lqw = new LambdaQueryWrapper<>();
        lqw.eq(HlwManageMenu::getParentId,0L);
        List<HlwManageMenu> pMenu = menuService.list(lqw);
        List<String> menus = new ArrayList<>();
        if(roleId != null){
            menus = menuService.getMenuByRoleId(roleId);
        }
        boolean isCheck = CollectionUtils.isEmpty(menus);
        List<MenuTreeVo> result = new ArrayList<>();
        for(HlwManageMenu menu:pMenu){
            MenuTreeVo pM = new MenuTreeVo();
            LambdaQueryWrapper<HlwManageMenu> la = new LambdaQueryWrapper<>();
            la.eq(HlwManageMenu::getParentId,String.valueOf(menu.getId()));
            List<HlwManageMenu> sonList = menuService.list(la);
            List<MenuTreeVo> sons = new ArrayList<>();
            for(HlwManageMenu sm:sonList){
                MenuTreeVo sM = new MenuTreeVo();
                sM.setId(sm.getId());
                sM.setName(sm.getName());
                sM.setPId(menu.getId());
                if(!isCheck){
                    sM.setChecked(menus.contains(sm.getId()+sm.getName()));
                }
                sons.add(sM);
            }
            pM.setId(menu.getId());
            pM.setName(menu.getName());
            pM.setPId(0L);
            pM.setSons(sons);
            if(!isCheck){
                pM.setChecked(menus.contains(menu.getId()+menu.getName()));
            }
            result.add(pM);
        }
        return new ResponseBean().SUCCESS(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBean roleMenuSave(RoleMenuDto dto) {
        if(CollectionUtils.isEmpty(dto.getIds())){
            throw new ServicesException("参数不能为空！");
        }
        Long userId = CurrentTools.currentId();
        LambdaQueryWrapper<HlwManageUserRole> la = new LambdaQueryWrapper<>();
        la.eq(HlwManageUserRole::getUserId,userId);
        HlwManageUserRole ur = userRoleService.getOne(la);
        if(ur == null){
            throw new ServicesException("当前用户不拥有任何角色！");
        }
        LambdaQueryWrapper<HlwManageRoleMenu> lam = new LambdaQueryWrapper<>();
        lam.eq(HlwManageRoleMenu::getRoleId,ur.getRoleId());
        if(!roleMenuService.remove(lam)){
            throw new ServicesException("迁移角色失败！");
        }
        for(String id:dto.getIds()){
            HlwManageRoleMenu rm = new HlwManageRoleMenu();
            rm.setMenuId(Long.valueOf(id));
            rm.setRoleId(ur.getRoleId());
            roleMenuService.save(rm);
        }
        return new ResponseBean().SUCCESS();
    }

    /***
     * 产品要求这么多级菜单
     * @param id
     * @return
     */
    @Override
    public ResponseBean<RoleEditVo> echo(Long id) {
        /**新增-读取缓存*/
        RoleEditVo cacheResult  = (RoleEditVo) RedisService.get(CommonConstants.Current.ROLE_MENU_SAVE_LIST);
        String key = CommonConstants.Current.ROLE_MENU_SAVE_LIST;
        RoleEditVo vo = new RoleEditVo();
        HlwManageRole role = getById(id);
        if(role != null){
            vo.setId(role.getId());
            vo.setName(role.getName());
            vo.setNo(role.getNo());
            cacheResult = (RoleEditVo) RedisService.get(CommonConstants.Current.ROLE_MENU_EDIT_LIST+id);
        }
        if(cacheResult != null){
            log.info("【缓存读取】角色菜单关联数据返回");
            return new ResponseBean().SUCCESS(cacheResult);
        }
        /**获取菜单*/
        LambdaQueryWrapper<HlwManageMenu> lam = new LambdaQueryWrapper<>();
        lam.eq(HlwManageMenu::getBelongType,CommonConstants.HlwMangeMenu.BELONG_TYPE_1).eq(HlwManageMenu::getDelFlag,CommonConstants.DELETE_FLAG_NORMAL);
        List<HlwManageMenu> first = menuService.list(lam);
        List<String> currentRoleHaveMenus = new ArrayList<>();
        if(id != null){
            /**当前用户所拥有的菜单**/
            currentRoleHaveMenus = menuService.getMenuByRoleId(id);
            key = CommonConstants.Current.ROLE_MENU_EDIT_LIST+id;
        }
        boolean checked = CollectionUtils.isEmpty(currentRoleHaveMenus);
        List<RoleMenuEchoVo> listResult = new ArrayList<>();
        for(HlwManageMenu firstMenu:first){
            RoleMenuEchoVo f = new RoleMenuEchoVo();
            f.setId(firstMenu.getId());
            f.setName(firstMenu.getName());
            f.setPId(0L);
            if(!checked){
                f.setChecked(currentRoleHaveMenus.contains(firstMenu.getId()+firstMenu.getName()));
            }
            f.setBelongType(firstMenu.getBelongType());
            /**二级菜单*/
            LambdaQueryWrapper<HlwManageMenu> secondLa = new LambdaQueryWrapper<>();
            secondLa.eq(HlwManageMenu::getParentId,firstMenu.getId())
                    .eq(HlwManageMenu::getDelFlag,CommonConstants.DELETE_FLAG_NORMAL);
            List<HlwManageMenu> second = menuService.list(secondLa);
            List<RoleMenuEchoVo> secondVo = new ArrayList<>();
            for(HlwManageMenu secondMenu:second){
                RoleMenuEchoVo secondVos = new RoleMenuEchoVo();
                secondVos.setId(secondMenu.getId());
                secondVos.setName(secondMenu.getName());
                secondVos.setPId(firstMenu.getId());
                if(!checked){
                    secondVos.setChecked(currentRoleHaveMenus.contains(secondMenu.getId()+secondMenu.getName()));
                }
                secondVos.setBelongType(secondMenu.getBelongType());

                /**三级菜单*/
                LambdaQueryWrapper<HlwManageMenu> thirdLa = new LambdaQueryWrapper<>();
                thirdLa.eq(HlwManageMenu::getParentId,secondMenu.getId()).eq(HlwManageMenu::getDelFlag,CommonConstants.DELETE_FLAG_NORMAL);
                List<HlwManageMenu> third = menuService.list(thirdLa);
                List<RoleMenuEchoVo> thirdVo = new ArrayList<>();
                for(HlwManageMenu thirdMenu:third){
                    RoleMenuEchoVo thirdVos = new RoleMenuEchoVo();
                    thirdVos.setId(thirdMenu.getId());
                    thirdVos.setName(thirdMenu.getName());
                    thirdVos.setPId(secondMenu.getId());
                    if(!checked){
                        thirdVos.setChecked(currentRoleHaveMenus.contains(thirdMenu.getId()+thirdMenu.getName()));
                    }
                    thirdVos.setBelongType(thirdMenu.getBelongType());
                    /**四级菜单*/
                    LambdaQueryWrapper<HlwManageMenu> fourthLa = new LambdaQueryWrapper<>();
                    fourthLa.eq(HlwManageMenu::getParentId,thirdMenu.getId()).eq(HlwManageMenu::getDelFlag,CommonConstants.DELETE_FLAG_NORMAL);
                    List<HlwManageMenu> four = menuService.list(fourthLa);
                    List<RoleMenuEchoVo> fourVo = new ArrayList<>();
                    for(HlwManageMenu fourMenu:four){
                        RoleMenuEchoVo fourVos = new RoleMenuEchoVo();
                        fourVos.setId(fourMenu.getId());
                        fourVos.setName(fourMenu.getName());
                        fourVos.setPId(thirdMenu.getId());
                        if(!checked){
                            fourVos.setChecked(currentRoleHaveMenus.contains(fourMenu.getId()+fourMenu.getName()));
                        }
                        fourVos.setBelongType(fourMenu.getBelongType());
                        /**五级菜单*/
                        LambdaQueryWrapper<HlwManageMenu> fiveLa = new LambdaQueryWrapper<>();
                        fiveLa.eq(HlwManageMenu::getParentId,fourMenu.getId())
                                .eq(HlwManageMenu::getDelFlag,CommonConstants.DELETE_FLAG_NORMAL);
                        List<HlwManageMenu> five = menuService.list(fiveLa);
                        List<RoleMenuEchoVo> fiveVo = new ArrayList<>();
                        for(HlwManageMenu fiveMenu:five) {
                            RoleMenuEchoVo fiveVos = new RoleMenuEchoVo();
                            fiveVos.setId(fiveMenu.getId());
                            fiveVos.setName(fiveMenu.getName());
                            fiveVos.setPId(fourMenu.getId());
                            if (!checked) {
                                fiveVos.setChecked(currentRoleHaveMenus.contains(fiveMenu.getId() + fiveMenu.getName()));
                            }
                            fiveVos.setBelongType(fiveMenu.getBelongType());
                            fiveVo.add(fiveVos);
                        }
                        fourVos.setSons(fiveVo);
                        fourVo.add(fourVos);
                    }
                    thirdVos.setSons(fourVo);
                    thirdVo.add(thirdVos);
                }
                /**三级菜单放入到二级菜单中*/
                secondVos.setSons(thirdVo);
                /**二级菜单结果封装*/
                secondVo.add(secondVos);
            }

            /**二级菜单放入到一级菜单中*/
            f.setSons(secondVo);
            /**一级菜单放入到返回结果中*/
            listResult.add(f);
        }
        vo.setRoleMenu(listResult);
        log.info("【缓存击穿】从数据库读取角色菜单关联数据");
        RedisService.set(key,vo);
        return new ResponseBean().SUCCESS(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBean saveRole(RoleMenuDto dto) {
        if(CollectionUtils.isEmpty(dto.getIds())){
            throw new ServicesException("参数不能为空！");
        }
        String operateName = CommonConstants.UPDATE;
        HlwManageRole role = new HlwManageRole();
        role.setId(dto.getId());
        if(dto.getId() == null){
            operateName = CommonConstants.NEW;
            role.setCreateBy(CurrentTools.currentId());
            role.setCreateDate(new Date());
            role.setDelFlag(CommonConstants.DELETE_FLAG_NORMAL);
            role.setRoleType(CommonConstants.HlwManageRole.COMMON_ROLE);
            role.setUseable(CommonConstants.HlwManageRole.USABLE_YES_0);
        }
        role.setUpdateBy(CurrentTools.currentId());
        role.setUpdateDate(new Date());
        role.setName(dto.getName());
        role.setNo(dto.getNo());
        saveOrUpdate(role);
        /**保存角色菜单*/
        Long roleIdForMenu = dto.getId()==null?role.getId():dto.getId();
        if(roleIdForMenu != null){
            roleMenuService.removeByRoleId(roleIdForMenu);
        }
        roleMenuService.saveRoleMenu(roleIdForMenu,dto.getIds());
//        for(String id:dto.getIds()){
//            HlwManageRoleMenu rm = new HlwManageRoleMenu();
//            rm.setMenuId(Long.valueOf(id));
//            rm.setRoleId(roleIdForMenu);
//            roleMenuService.save(rm);
//        }
        /**日志切面*/
        HlwManageUser current = userService.getById(CurrentTools.currentId());
        String name = userRoleService.getByUserId(current.getId());
        ForLog log = new ForLog()
                .setOperateAccount(current.getLoginName())
                .setUserName(current.getName())
                .setRoleName(name)
                .setOperateName("系统管理"+operateName+"角色")
                .setResult(operateName+"了角色【"+role.getName()+"】");
        return new ResponseBean().SUCCESS(log);
    }

    @Override
    public ResponseBean check(RoleCheckDto dto) {

        if(!StringUtils.isEmpty(dto.getName())){
            LambdaQueryWrapper<HlwManageRole> laa = new LambdaQueryWrapper<>();
            laa.eq(HlwManageRole::getName,dto.getName());
            if(count(laa) > 0){
                throw new ServicesException("该名称已经存在");
            }
        }
        if(!StringUtils.isEmpty(dto.getNo())){
            LambdaQueryWrapper<HlwManageRole> laq = new LambdaQueryWrapper<>();
            laq.eq(HlwManageRole::getNo,dto.getNo());
            if(count(laq) > 0){
                throw new ServicesException("该编号已经存在");
            }
        }
        return new ResponseBean().SUCCESS(null,"该编号/角色名称可用");
    }

    @Override
    public ResponseBean removeRole(Long id) {
        if(id.intValue() == 1){
            throw new ServicesException("拳头￣へ￣警告：超级管理员不能删除！");
        }
        HlwManageRole role = getById(id);
        if(role == null){
            throw new ServicesException("id有误，预期结果未找到");
        }
        LambdaQueryWrapper<HlwManageUserRole> la = new LambdaQueryWrapper<>();
        la.eq(HlwManageUserRole::getRoleId,id);
        if(userRoleService.count(la) > 0){
            throw new ServicesException("未能删除成功，该角色下还有关联的用户，若要删除请将该角色和这些用户解除关联！");
        }
        HlwManageRole update = new HlwManageRole()
                .setId(role.getId())
                .setUpdateDate(new Date())
                .setUpdateBy(CurrentTools.currentId())
                .setDelFlag(CommonConstants.DELETE_FLAG_DELETED);
        if(updateById(update)){
            LambdaQueryWrapper<HlwManageUserRole> lla = new LambdaQueryWrapper<>();
            lla.eq(HlwManageUserRole::getUserId,id);
            userRoleService.remove(lla);
        }
        /**日志切面*/
        HlwManageUser current = userService.getById(CurrentTools.currentId());
        String name = userRoleService.getByUserId(current.getId());
        ForLog log = new ForLog()
                .setOperateAccount(current.getLoginName())
                .setUserName(current.getName())
                .setRoleName(name)
                .setOperateName("系统管理-删除角色")
                .setResult("删除了角色【"+role.getName()+"】");
        return new ResponseBean().SUCCESS(log);
    }

    @Override
    public List<CurrentRoleVo> currentRole(Long id) {
        Long current = id;
        if(id == null){
            current = CurrentTools.currentId();
        }
        List<CurrentRoleVo> role = baseMapper.getByUserId(current);
        if(role == null){
            throw new ServicesException("当前用户暂时没有角色");
        }
        return role;
    }

    @Override
    public List<Long> currentRoleIdList() {
        Long currentId = CurrentTools.currentId();
        List<Long> currentRoleIdList = (List<Long>) RedisService.get(CommonConstants.Current.CURRENT_ROLE_ID_LIST+currentId);
        if(currentRoleIdList == null){
            currentRoleIdList = new ArrayList<>();
            List<CurrentRoleVo> roleVos = currentRole(currentId);
            for(CurrentRoleVo v:roleVos){
                currentRoleIdList.add(v.getId());
            }
        }
        return currentRoleIdList;
    }

    @Override
    public ResponseBean<List<CurrentRoleVo>> current() {
        Long currentId = CurrentTools.currentId();
        List<CurrentRoleVo> role = (List<CurrentRoleVo>) RedisService.get(CommonConstants.Current.CURRENT_ROLE+currentId);
        if(role == null){
            role = currentRole(currentId);
        }
        return new ResponseBean<List<CurrentRoleVo>>().SUCCESS(role);
    }

    @Override
    public ResponseBean<RoleEditVo> echoBetter(Long id) {
        /**新增-读取缓存*/
        RoleEditVo cacheResult  = (RoleEditVo) RedisService.get(CommonConstants.Current.ROLE_MENU_SAVE_LIST);
        String key = CommonConstants.Current.ROLE_MENU_SAVE_LIST;
        RoleEditVo vo = new RoleEditVo();
        HlwManageRole role = getById(id);
        if(role != null){
            vo.setId(role.getId());
            vo.setName(role.getName());
            vo.setNo(role.getNo());
            cacheResult = (RoleEditVo) RedisService.get(CommonConstants.Current.ROLE_MENU_EDIT_LIST+id);
        }
        if(cacheResult != null){
            log.info("【缓存读取】角色菜单关联数据返回");
            return new ResponseBean().SUCCESS(cacheResult);
        }
        long start = System.currentTimeMillis();
        /**获取菜单*/
        boolean isShowAdmin = true;
        if(id != null && id.intValue() != 1){
            /**非超级管理员不给显示管理设置菜单*/
            isShowAdmin = false;
        }
        List<RoleMenuEchoVo> firstAndSecond = menuService.getFirstAndSecondMenu(isShowAdmin);
        List<String> currentRoleHaveMenus = new ArrayList<>();
        if(id != null){
            /**当前用户所拥有的菜单**/
            currentRoleHaveMenus = menuService.getMenuByRoleId(id);
            key = CommonConstants.Current.ROLE_MENU_EDIT_LIST+id;
        }
        boolean checked = CollectionUtils.isEmpty(currentRoleHaveMenus);
        for(RoleMenuEchoVo firstMenu:firstAndSecond){
            if(!checked) {
                firstMenu.setChecked(currentRoleHaveMenus.contains(firstMenu.getId() + firstMenu.getName()));
            }
            for(RoleMenuEchoVo secondMenu:firstMenu.getSons()){
                if(!checked){
                    secondMenu.setChecked(currentRoleHaveMenus.contains(secondMenu.getId()+secondMenu.getName()));
                }
                List<RoleMenuEchoVo> tfMenu = menuService.getThirdAndFourMenu(secondMenu.getId());
                for(RoleMenuEchoVo tf:tfMenu){
                    /**三级*/
                    if(!checked){
                        tf.setChecked(currentRoleHaveMenus.contains(tf.getId()+tf.getName()));
                    }
                    for(RoleMenuEchoVo four:tf.getSons()){
                        if (!checked) {
                            four.setChecked(currentRoleHaveMenus.contains(four.getId() + four.getName()));
                        }
                    }
                }
                secondMenu.setSons(tfMenu);
            }
        }
        vo.setRoleMenu(firstAndSecond);
        long end = System.currentTimeMillis();
        System.out.printf(String.format("总共耗时：%d%n", end - start));
        log.info("【缓存击穿】从数据库读取角色菜单关联数据");
        RedisService.set(key,vo);
        return new ResponseBean().SUCCESS(vo);
    }

}
