package com.hlw.manage.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.model.dto.system.*;
import com.hlw.common.model.vo.system.CurrentVo;
import com.hlw.common.model.vo.system.LoginResultVo;
import com.hlw.common.model.vo.system.UserEditUserVo;
import com.hlw.common.model.vo.system.UserListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.system.entity.HlwManageUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author mqz
 * @since 2020-04-24
 */
public interface IHlwManageUserService extends IService<HlwManageUser> {

    /**
     * 登录
     * @param dto
     * @return
     */
    ResponseBean<LoginResultVo> login(LoginDto dto);

    /**
     * 当前用户
     * @return
     */
    ResponseBean<CurrentVo> current();

    /**
     * 用户列表
     * @param dto
     * @return
     */
    ResponseBean<IPage<UserListVo>> getAll(UserListDto dto);

    /**
     * 用户操作回显
     * @param userId
     * @return
     */
    ResponseBean<UserEditUserVo> editEcho(Long userId);

    /**
     * 保存用户
     * @param dto
     * @return
     */
    ResponseBean saveUser(UserDto dto);

    /**
     * 用户退出
     * @return
     */
    ResponseBean out();
    /**
     * 修改密码
     * @param dto
     * @return
     */
    ResponseBean rePassword(RePasswordDto dto);

    /**
     * 启用/禁用
     * @param dto
     * @return
     */
    ResponseBean enable(UserEnableDto dto);

    /**
     * 用户删除
     * @param userId
     * @return
     */
    ResponseBean removeUser(Long userId);


    /**
     * 登录信息
     * @return
     */
    ResponseBean info();
}
