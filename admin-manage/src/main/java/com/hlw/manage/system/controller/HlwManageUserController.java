package com.hlw.manage.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.model.dto.system.UserDto;
import com.hlw.common.model.dto.system.UserEnableDto;
import com.hlw.common.model.dto.system.UserListDto;
import com.hlw.common.model.vo.system.CurrentVo;
import com.hlw.common.model.vo.system.UserEditUserVo;
import com.hlw.common.model.vo.system.UserListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.core.annotations.Logs;
import com.hlw.manage.core.annotations.RequirePermission;
import com.hlw.manage.core.annotations.SuperAdmin;
import com.hlw.manage.core.annotations.TargetSource;
import com.hlw.manage.core.exception.ParamValidException;
import com.hlw.manage.system.service.IHlwManageUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mqz
 * @since 2020-04-24
 */
@RestController
@RequestMapping("/system/user")
@Api(tags = "用户控制")
public class HlwManageUserController {

    @Autowired
    private IHlwManageUserService userService;


    @GetMapping("/current")
    @ApiOperation(value = "当前登录用户")
    @TargetSource()
    public ResponseBean<CurrentVo> current(){
        return userService.current();
    }


    @PostMapping("/list")
    @ApiOperation(value = "用户列表")
    @TargetSource()
    @SuperAdmin()
    public ResponseBean<IPage<UserListVo>> userList(@RequestBody UserListDto dto){
        return userService.getAll(dto);
    }

    @PostMapping("/enable")
    @ApiOperation(value = "启用/禁用")
    @TargetSource()
    @Logs()
    @SuperAdmin()
    public ResponseBean enable(@RequestBody @Valid UserEnableDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ParamValidException(bindingResult.getFieldError().getDefaultMessage());
        }
        return userService.enable(dto);
    }

    @GetMapping("/remove")
    @ApiOperation(value = "删除")
    @TargetSource()
    @Logs()
    @SuperAdmin()
    public ResponseBean remove(@ApiParam(name = "userId",value = "用户id")
                               @RequestParam Long userId){
        return userService.removeUser(userId);
    }


    @GetMapping("/editEcho")
    @ApiOperation(value = "用户操作回显")
    @TargetSource()
    @SuperAdmin()
    public ResponseBean<UserEditUserVo> editEcho(@RequestParam @ApiParam(name = "userId", value = "用户id") Long userId){
        return userService.editEcho(userId);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存")
    @Logs()
    @TargetSource()
    @SuperAdmin()
    public ResponseBean save(@RequestBody @Valid UserDto dto,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ParamValidException(bindingResult.getFieldError().getDefaultMessage());
        }
        return userService.saveUser(dto);
    }









}
