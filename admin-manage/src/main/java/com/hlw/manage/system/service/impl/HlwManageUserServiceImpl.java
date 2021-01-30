package com.hlw.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlw.common.constants.CommonConstants;
import com.hlw.common.model.dto.system.*;
import com.hlw.common.model.vo.system.*;
import com.hlw.common.response.ResponseBean;
import com.hlw.common.utils.*;
import com.hlw.manage.core.CurrentTools;
import com.hlw.manage.core.async.AsyncTaskExecute;
import com.hlw.manage.core.exception.ServicesException;
import com.hlw.manage.core.redis.RedisService;
import com.hlw.manage.core.token.JwtTools;
import com.hlw.manage.system.entity.HlwManageUser;
import com.hlw.manage.system.entity.HlwManageUserRole;
import com.hlw.manage.system.mapper.HlwManageUserMapper;
import com.hlw.manage.system.service.IHlwManageRoleService;
import com.hlw.manage.system.service.IHlwManageUserRoleService;
import com.hlw.manage.system.service.IHlwManageUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author mqz
 * @since 2020-04-24
 */
@Service
public class HlwManageUserServiceImpl extends ServiceImpl<HlwManageUserMapper, HlwManageUser> implements IHlwManageUserService {

    private static final Logger logger = LoggerFactory.getLogger(HlwManageUserServiceImpl.class);


    @Autowired
    private IHlwManageUserRoleService userRoleService;
    @Autowired
    private IHlwManageRoleService roleService;
    @Autowired
    private AsyncTaskExecute taskService;

    @Value("${my.token.expire}")
    private long tokenExpireTime;

    /**静态变量，优化内存*/
    private static List<String> condition;
    static {
        List<String> loginTypeList = new ArrayList<>();
        loginTypeList.add(CommonConstants.Sms.LOGIN_TYPE_PWD);
        loginTypeList.add(CommonConstants.Sms.LOGIN_TYPE_SMS);
        condition = Collections.unmodifiableList(loginTypeList);
    }

    @Override
    public ResponseBean<LoginResultVo> login(LoginDto dto) {
        if(!condition.contains(dto.getType())){
            throw new ServicesException("登录方式错误");
        }
        LambdaQueryWrapper<HlwManageUser> qw = new LambdaQueryWrapper<>();
        qw.eq(HlwManageUser::getLoginName,dto.getLoginName())
                .eq(HlwManageUser::getDelFlag, CommonConstants.HlwManageUser.DeleteFlag.NORMAL)
                .eq(HlwManageUser::getEnable,CommonConstants.HlwManageUser.EnableFlag.ON_YES);
        HlwManageUser user = getOne(qw);
        if(user == null){
            throw new ServicesException("该账号不存在或已被禁用！");
        }
        //密码登录
        if(dto.getType().equals(CommonConstants.Sms.LOGIN_TYPE_PWD)){
            if(StringUtils.isEmpty(dto.getPassWord())){
                throw new ServicesException("请输入密码");
            }
            String inSecret = PwdUtils.getSecret(dto.getPassWord(),user.getSalt());
            if(!inSecret.equals(user.getPassword())){
                throw new ServicesException("密码错误！");
            }
        }
        //验证码登录
        if(dto.getType().equals(CommonConstants.Sms.LOGIN_TYPE_SMS)){
            if(StringUtils.isEmpty(dto.getCode())){
                throw new ServicesException("请输入验证码");
            }
            String key = CommonConstants.Sms.KEY_LOGIN+dto.getLoginName();
            String value = (String) RedisService.get(key);
            if(StringUtils.isEmpty(value)){
                throw new ServicesException("验证码已失效，请重新发送");
            }
            RedisService.del(key);
            if(!dto.getCode().equals(value)){
                throw new ServicesException("验证码不正确！");
            }
        }
        /**登录成功，返回token*/
        String token = UuidUtils.getUuid();
        /**挤登的情况*/
        String lastToken = (String) RedisService.get(CommonConstants.Current.CURRENT_USER_T+user.getId());
        if(lastToken != null){
            /**已存在登录的token*/
            RedisService.del(lastToken);/**删除已经登录的token*/
            RedisService.del(CommonConstants.Current.CURRENT_ROLE+user.getId());/**删除角色缓存*/
            RedisService.del(CommonConstants.Current.CURRENT_USER+user.getId());/**删除用户缓存*/
        }
        RedisService.set(CommonConstants.Current.CURRENT_USER_T+user.getId(),token);
        /**此处可以把user的id、roleId存入到缓存中*/
        String jwtResult = JwtTools.getJwt(token,String.valueOf(user.getId()),"roleId");
        RedisService.set(token,jwtResult,tokenExpireTime);
        /**把当前用户放入缓存中，过期时间为token时长，user清除敏感信息**/
        user.setPassword(null).setSalt(null);
        RedisService.set(CommonConstants.Current.CURRENT_USER+user.getId(),user,tokenExpireTime);
//        /**登录信息记入用户表*/
//        HlwManageUser manageUser = new HlwManageUser();
//        manageUser.setId(user.getId())
//                  .setLoginDate(new Date())
//                  .setLoginIp(WebCommonUtils.getIpAddr())
//                  .setArea(AddressUtils.getAreaByIp(manageUser.getLoginIp()));
//        updateById(manageUser);
        LoginResultVo vo = new LoginResultVo().setToken(token);
        List<CurrentRoleVo> role = roleService.currentRole(user.getId());
        vo.setRoles(role);
        return new ResponseBean().SUCCESS(vo,"登录成功");
    }

    @Override
    public ResponseBean<CurrentVo> current() {
        Long userId = CurrentTools.currentId();
        HlwManageUser user = (HlwManageUser) RedisService.get(CommonConstants.Current.CURRENT_USER+userId);
        if(user == null){
            user = getById(userId);
        }
        CurrentVo vo = new CurrentVo();
        BeanUtils.copyProperties(user,vo);
        return new ResponseBean().SUCCESS(vo);
    }

    @Override
    public ResponseBean<IPage<UserListVo>> getAll(UserListDto dto) {
        Page page = new Page(dto.getPageCurrent(),dto.getPageSize());
        IPage<UserListVo> res = baseMapper.getAllList(page,dto);
        return new ResponseBean().SUCCESS(res);
    }

    @Override
    public ResponseBean<UserEditUserVo> editEcho(Long userId) {
        HlwManageUser user = getById(userId);
        if(user == null){
            throw new ServicesException("id有误，预期结果未找到");
        }
        UserEditUserVo resultUser = new UserEditUserVo();
        BeanUtils.copyProperties(user,resultUser);
        return new ResponseBean().SUCCESS(resultUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBean saveUser(UserDto dto) {

        if(dto.getRoleId().contains(1L) && dto.getUserId() == null){
            LambdaQueryWrapper<HlwManageUserRole> la = new LambdaQueryWrapper<>();
            la.eq(HlwManageUserRole::getRoleId,1);
            if(userRoleService.count(la) >= 5 ){
                throw new ServicesException("超级管理员角色的成员已达到上限，（超管人数最多不多于5）");
            }
        }
        HlwManageUser user = new HlwManageUser();
        BeanUtils.copyProperties(dto,user);
        user.setId(dto.getUserId());
        String operateName = user.getId()==null?"新增":"编辑"; /**为日志切面处理逻辑*/
        if(user.getId() == null){
            LambdaQueryWrapper<HlwManageUser> la = new LambdaQueryWrapper<>();
            la.eq(HlwManageUser::getLoginName,dto.getLoginName())
                    .eq(HlwManageUser::getDelFlag,CommonConstants.HlwManageUser.DeleteFlag.NORMAL)
                    .eq(HlwManageUser::getLoginFlag,CommonConstants.HlwManageUser.LoginFlag.ALLOW);
            if(count(la) > 0){
                throw new ServicesException("该登录名已存在，请换一个再试！");
            }
            user.setCreateBy(CurrentTools.currentId());
            user.setCreateDate(new Date());
            user.setEnable(CommonConstants.HlwManageUser.EnableFlag.ON_YES);
            user.setDelFlag(CommonConstants.HlwManageUser.DeleteFlag.NORMAL);
            user.setLoginFlag(CommonConstants.HlwManageUser.LoginFlag.ALLOW);
            String salt = UuidUtils.getUuid();
            String secret = PwdUtils.getSecret(StringUtils.isEmpty(dto.getPassWord())?CommonConstants.HlwManageUser.INIT_PWD:dto.getPassWord(),salt);
            user.setPassword(secret);
            user.setSalt(salt);
        }
        user.setUpdateBy(CurrentTools.currentId());
        user.setUpdateDate(new Date());
        saveOrUpdate(user);
        LambdaQueryWrapper<HlwManageUserRole> lau = new LambdaQueryWrapper<>();
        lau.eq(HlwManageUserRole::getUserId,user.getId());
        userRoleService.remove(lau);
        for(Long roleId:dto.getRoleId()){
            HlwManageUserRole ur = new HlwManageUserRole();
            ur.setUserId(user.getId());
            ur.setRoleId(roleId);
            userRoleService.save(ur);
        }
        taskService.removeRoleMenu();
        taskService.deleteSaveRole();

        /**日志切面*/
        HlwManageUser current = getById(CurrentTools.currentId());
        String name = userRoleService.getByUserId(current.getId());
        ForLog log = new ForLog()
                .setOperateAccount(current.getLoginName())
                .setUserName(current.getName())
                .setRoleName(name)
                .setOperateName("系统管理-"+operateName+"成员")
                .setResult(operateName+"了姓名为【"+user.getName()+"】，账号为【"+user.getLoginName()+"】的成员");
        return new ResponseBean().SUCCESS(log);
    }

    @Override
    public ResponseBean out() {
        Long currentId = CurrentTools.currentId();
        boolean flag = CurrentTools.out();
        /**删除缓存数据*/
        RedisService.del(CommonConstants.Current.CURRENT_ROLE+currentId);
        /**删除当前登录用户*/
        RedisService.del(CommonConstants.Current.CURRENT_USER+currentId);
        logger.info("Is it success for User out ?  "+ flag);
        return new ResponseBean().SUCCESS(null,"退出成功，Hope To See You Again ！");
    }



    @Override
    public ResponseBean rePassword(RePasswordDto dto) {
        String key = CommonConstants.Sms.KEY_RE_PASSWORD+dto.getLoginName();
        String value = (String) RedisService.get(key);
        if(StringUtils.isEmpty(value)){
            throw new ServicesException("验证码已失效，请重新发送");
        }
        if(!dto.getCode().equals(value)){
            throw new ServicesException("验证码不正确！");
        }
        LambdaQueryWrapper<HlwManageUser> la = new LambdaQueryWrapper<>();
        la.eq(HlwManageUser::getLoginName,dto.getLoginName())
                .eq(HlwManageUser::getDelFlag,CommonConstants.HlwManageUser.DeleteFlag.NORMAL);
        HlwManageUser user = getOne(la);
        HlwManageUser rUser = new HlwManageUser().setId(user.getId());
        String salt = UuidUtils.getUuid();
        String secret = PwdUtils.getSecret(dto.getPassword(),salt);
        rUser.setSalt(salt);
        rUser.setUpdateDate(new Date());
        rUser.setCreateBy(user.getId());
        rUser.setPassword(secret);
        if(!updateById(rUser)){
            return new ResponseBean().ERROR("网络出错了，连接中断，请重新修改密码");
        }
        RedisService.del(key);
        //out();
        return new ResponseBean().SUCCESS(null,"修改成功，请重新登录！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBean enable(UserEnableDto dto) {
        HlwManageUser user = getById(dto.getId());
        if(user == null){
            throw new ServicesException("id有误，预期结果未找到");
        }
        HlwManageUser update = new HlwManageUser();
        update.setId(user.getId());
        update.setUpdateDate(new Date());
        update.setUpdateBy(CurrentTools.currentId());
        update.setEnable(user.getEnable().intValue() == CommonConstants.HlwManageUser.EnableFlag.OFF_NO?CommonConstants.HlwManageUser.EnableFlag.ON_YES:CommonConstants.HlwManageUser.EnableFlag.OFF_NO);
        updateById(update);
        /**日志切面*/
        HlwManageUser current = getById(CurrentTools.currentId());
        String name = userRoleService.getByUserId(current.getId());
        ForLog log = new ForLog()
                .setOperateAccount(current.getLoginName())
                .setUserName(current.getName())
                .setRoleName(name)
                .setOperateName("系统管理-修改成员状态")
                .setResult("修改了姓名为【"+user.getName()+"】，账号为【"+user.getLoginName()+"】的成员");
        return new ResponseBean().SUCCESS(log);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBean removeUser(Long userId) {
        HlwManageUser user = getById(userId);
        if(user == null){
            throw new ServicesException("id有误，预期结果未找到");
        }
        HlwManageUser update = new HlwManageUser();
        update.setId(user.getId());
        update.setUpdateDate(new Date());
        update.setUpdateBy(CurrentTools.currentId());
        update.setDelFlag(CommonConstants.HlwManageUser.DeleteFlag.DELETED);
        updateById(update);
        LambdaQueryWrapper<HlwManageUserRole> lau = new LambdaQueryWrapper<>();
        lau.eq(HlwManageUserRole::getUserId,user.getId());
        userRoleService.remove(lau);
        taskService.removeRoleMenu();
        taskService.deleteSaveRole();
        /**日志切面*/
        HlwManageUser current = getById(CurrentTools.currentId());
        String name = userRoleService.getByUserId(current.getId());
        ForLog log = new ForLog()
                .setOperateAccount(current.getLoginName())
                .setUserName(current.getName())
                .setRoleName(name)
                .setOperateName("系统管理-删除成员")
                .setResult("删除了姓名为【"+user.getName()+"】，账号为【"+user.getLoginName()+"】的成员");
        return new ResponseBean().SUCCESS(log);
    }

    @Override
    public ResponseBean info() {
        HlwManageUser user = (HlwManageUser) RedisService.get(CommonConstants.Current.CURRENT_USER+CurrentTools.currentId());
        if(user == null){
            user = getById(CurrentTools.currentId());
        }
        if(user == null){
            throw new ServicesException("当前登录用户为空");
        }
        Map<String,Object> result = new HashMap<>();
        result.put("area", user.getArea());
        result.put("date",user.getLoginDate());
        result.put("ip",user.getLoginIp());
        return new ResponseBean().SUCCESS(result);
    }

}
